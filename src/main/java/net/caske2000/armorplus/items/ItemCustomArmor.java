package net.caske2000.armorplus.items;

import cofh.api.energy.IEnergyContainerItem;
import net.caske2000.armorplus.lib.Reference;
import net.caske2000.armorplus.lib.StringHelper;
import net.caske2000.armorplus.util.ArmorUpgradeHelper;
import net.caske2000.armorplus.util.NBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import java.util.List;

/**
 * Created by Caske2000 on 8-2-2016.
 */
public class ItemCustomArmor extends ItemArmor implements IEnergyContainerItem, ISpecialArmor
{
    private final CustomArmorMaterial customMaterial;
    private final int maxEnergy, maxTransfer, energyPerDamage;
    private final short upgradeAmount;
    private final short maxUpgradeAmount;
    private int timer = 0;
    private int tmpCost;
    // TODO stop checking isEfficient every tick, save it in NBT
    private boolean warning, isEfficient = false;

    public ItemCustomArmor(String unlocalizedName, ArmorMaterial material, CustomArmorMaterial customMaterial, int renderIndex, int armorType)
    {
        super(material, renderIndex, armorType);
        setUnlocalizedName(unlocalizedName);
        setCreativeTab(CreativeTab.ARMOR_TAB);
        this.customMaterial = customMaterial;
        this.maxEnergy = Reference.Numbers.ARMOR_MAX_ENERGY * customMaterial.getMaxEnergyMultiplier();
        this.maxTransfer = Reference.Numbers.ARMOR_MAX_TRANSFER * customMaterial.getMaxTransferMultiplier();
        this.energyPerDamage = Reference.Numbers.ENERGY_PER_DAMAGE + customMaterial.getExtraEnergyPerDamage();
        this.maxUpgradeAmount = (short) (Reference.Numbers.MAX_UPGRADE_AMOUNT + customMaterial.getExtraMaxUpgradeAmount());
        this.upgradeAmount = 0;
        setMaxStackSize(1);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        String layer;
        if (slot == 2)
            layer = "_layer_2.png"; //2 should be the slot for legs
        else
            layer = "_layer_1.png";
        return Reference.MODID + ":models/armor/" + customMaterial.getName() + layer;
    }

    @Override
    public void onCreated(ItemStack itemStack, World worldIn, EntityPlayer playerIn)
    {
        itemStack.setTagCompound(new NBTTagCompound());
        for (String upgrade : Reference.Names.TYPES)
            itemStack.getTagCompound().setBoolean(upgrade, false);
        itemStack.getTagCompound().setShort("UPGRADE_AMOUNT", upgradeAmount);
        itemStack.getTagCompound().setShort("MAX_UPGRADE_AMOUNT", maxUpgradeAmount);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        warning = NBTHelper.getInteger(itemStack, "ENERGY", 0) < 5000;

        if (timer > 60)
            timer = 0;
        tmpCost = 0;
        if (((ItemCustomArmor) itemStack.getItem()).getEnergyStored(itemStack) < 500 * upgradeAmount)
            return;
        for (String upgrade : Reference.Names.TYPES)
        {
            if (itemStack.getTagCompound().getBoolean(upgrade))
            {
                if (upgrade.equals(Reference.Names.TYPES[5]))
                    isEfficient = true;
                else
                    tmpCost += ArmorUpgradeHelper.onTick(upgrade, player);
            }
        }
        if (isEfficient)
            tmpCost -= tmpCost / 10.0F;
        extractEnergy(itemStack, tmpCost, timer != 0);
        timer++;
        isEfficient = false;
    }

    // region Energy Stuff
    @Override
    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate)
    {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        int stored = stack.getTagCompound().getInteger("ENERGY");
        int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

        if (!simulate)
        {
            stored += receive;
            stack.getTagCompound().setInteger("ENERGY", stored);
        }
        return receive;
    }

    @Override
    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate)
    {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        int stored = stack.getTagCompound().getInteger("ENERGY");
        int extract = Math.min(maxExtract, stored);

        if (!simulate)
        {
            stored -= extract;
            stack.getTagCompound().setInteger("ENERGY", stored);
        }
        return extract;
    }

    @Override
    public int getEnergyStored(ItemStack stack)
    {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        return stack.getTagCompound().getInteger("ENERGY");
    }

    @Override
    public int getMaxEnergyStored(ItemStack itemStack)
    {
        return maxEnergy;
    }
    // endregion

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check)
    {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        if (stack.getTagCompound().getInteger("MAX_ENERGY") == 0)
            NBTHelper.setInteger(stack, "MAX_ENERGY", maxEnergy);

        if (!stack.getTagCompound().hasKey("UPGRADE_AMOUNT"))
            NBTHelper.setShort(stack, "UPGRADE_AMOUNT", upgradeAmount);
        if (!stack.getTagCompound().hasKey("MAX_UPGRADE_AMOUNT"))
            NBTHelper.setShort(stack, "MAX_UPGRADE_AMOUNT", maxUpgradeAmount);

        if (StringHelper.isShiftKeyDown())
        {
            list.add(StringHelper.localize("info.caske.armor." + customMaterial.getName()));
            list.add(StringHelper.localize("info.caske.energy") + ": " + stack.getTagCompound().getInteger("ENERGY") + " / " + maxEnergy + " RF");
            list.add(StringHelper.localize("info.caske.io") + ": " + maxTransfer + " RF/t");
            list.add(StringHelper.localize("info.caske.upgrade") + ": " + stack.getTagCompound().getShort("UPGRADE_AMOUNT") + "/" + stack.getTagCompound().getShort("MAX_UPGRADE_AMOUNT"));
        } else
            list.add(StringHelper.shiftForInfo());
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        // Clamp double x ∈ [0.0D, 1.0D]
        return Math.min(Math.max(1 - (stack.getTagCompound().getInteger("ENERGY") / ((double) maxEnergy)), 0.0D), 1.0D);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        list.add(NBTHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
        list.add(NBTHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
    }

    // region ISpecialArmor stuff
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
    {
        return new ArmorProperties(0, getArmorMaterial().getDamageReductionAmount(armorType) * 0.025, getEnergyStored(armor) / 3);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        return getEnergyStored(armor) >= getEnergyPerDamage(armor) ? getAbsorptionRatio() : 0;
    }

    private int getAbsorptionRatio()
    {
        switch (armorType)
        {
            case 0:
                return 3;
            case 1:
                return 8;
            case 2:
                return 6;
            case 3:
                return 3;
            default:
                return 0;
        }
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
    {
        extractEnergy(stack, damage * getEnergyPerDamage(stack), false);
    }

    private int getEnergyPerDamage(ItemStack stack)
    {
        int unbrLvl = MathHelper.clamp_int(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
        return energyPerDamage * (5 - unbrLvl) / 5;
    }
    // endregion

    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }

    public boolean isWarning()
    {
        return warning;
    }
}
