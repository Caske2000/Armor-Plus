package net.caske2000.commutablearmor.items;

import cofh.api.energy.IEnergyContainerItem;
import net.caske2000.commutablearmor.lib.Reference;
import net.caske2000.commutablearmor.util.NBTHelper;
import net.caske2000.commutablearmor.util.StringHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Caske2000 on 31/03/2016.
 */
public class ItemCustomSword extends ItemSword implements IEnergyContainerItem {
    private int maxEnergy, maxTransfer, energyPerHit;
    private byte maxUpgradeAmount, upgradeAmount;

    public ItemCustomSword(ToolMaterial material, String unlocalizedName, CustomArmorMaterial customMaterial) {
        super(material);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(unlocalizedName);
        setCreativeTab(CreativeTab.ARMOR_TAB);
        this.maxEnergy = Reference.ITEM_MAX_ENERGY * customMaterial.getMaxEnergyMultiplier();
        this.maxTransfer = Reference.ITEM_MAX_TRANSFER * customMaterial.getMaxTransferMultiplier();
        this.energyPerHit = Reference.ENERGY_PER_DAMAGE + customMaterial.getExtraEnergyPerDamage();
        this.maxUpgradeAmount = (byte) (Reference.MAX_UPGRADE_AMOUNT + customMaterial.getExtraMaxUpgradeAmount());
        this.upgradeAmount = 0;
        setMaxStackSize(1);
    }


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        extractEnergy(stack, getEnergyPerHit(stack), false);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entity) {
        if ((double) state.getBlockHardness(world, pos) != 0.0D)
            extractEnergy(stack, getEnergyPerHit(stack), false);
        return true;
    }

    // region Energy Stuff
    @Override
    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        int stored = stack.getTagCompound().getInteger("ENERGY");
        int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

        if (!simulate) {
            stored += receive;
            stack.getTagCompound().setInteger("ENERGY", stored);
        }
        return receive;
    }

    @Override
    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        int stored = stack.getTagCompound().getInteger("ENERGY");
        int extract = Math.min(maxExtract, stored);

        if (!simulate) {
            stored -= extract;
            stack.getTagCompound().setInteger("ENERGY", stored);
        }
        return extract;
    }

    @Override
    public int getEnergyStored(ItemStack stack) {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        return stack.getTagCompound().getInteger("ENERGY");
    }

    @Override
    public int getMaxEnergyStored(ItemStack stack) {
        return maxEnergy;
    }
    // endregion

    private int getEnergyPerHit(ItemStack stack) {
        int unbrLvl = MathHelper.clamp_int(EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(34), stack), 0, 4);
        return energyPerHit * (5 - unbrLvl) / 5;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        if (stack.getTagCompound().getInteger("MAX_ENERGY") == 0)
            NBTHelper.setInteger(stack, "MAX_ENERGY", maxEnergy);

        if (!stack.getTagCompound().hasKey("UPGRADE_AMOUNT"))
            NBTHelper.setShort(stack, "UPGRADE_AMOUNT", upgradeAmount);
        if (!stack.getTagCompound().hasKey("MAX_UPGRADE_AMOUNT"))
            NBTHelper.setShort(stack, "MAX_UPGRADE_AMOUNT", maxUpgradeAmount);

        if (StringHelper.isShiftKeyDown()) {
            list.add(StringHelper.localize("info.caske.energy") + ": " + stack.getTagCompound().getInteger("ENERGY") + " / " + maxEnergy + " RF");
            list.add(StringHelper.localize("info.caske.io") + ": " + maxTransfer + " RF/t");
            list.add(StringHelper.localize("info.caske.upgrade") + ": " + stack.getTagCompound().getShort("UPGRADE_AMOUNT") + "/" + stack.getTagCompound().getShort("MAX_UPGRADE_AMOUNT"));
        } else
            list.add(StringHelper.shiftForInfo());
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        // Clamp double x ∈ [0.0D, 1.0D]
        return Math.min(Math.max(1 - (stack.getTagCompound().getInteger("ENERGY") / ((double) maxEnergy)), 0.0D), 1.0D);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
        list.add(NBTHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
        list.add(NBTHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
    }

    @Override
    public void onCreated(ItemStack itemStack, World worldIn, EntityPlayer playerIn) {
        itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setShort("UPGRADE_AMOUNT", upgradeAmount);
        itemStack.getTagCompound().setShort("MAX_UPGRADE_AMOUNT", maxUpgradeAmount);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }
}
