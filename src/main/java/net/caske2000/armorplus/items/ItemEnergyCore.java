package net.caske2000.armorplus.items;

import cofh.api.energy.ItemEnergyContainer;
import net.caske2000.armorplus.lib.Reference;
import net.caske2000.armorplus.lib.StringHelper;
import net.caske2000.armorplus.util.NBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

/**
 * Created by Caske2000 on 28/03/2016.
 */
public class ItemEnergyCore extends ItemEnergyContainer
{
    public ItemEnergyCore()
    {
        super(Reference.Numbers.CORE_MAX_ENERGY, Reference.Numbers.CORE_MAX_TRANSFER);
        setUnlocalizedName(Reference.Names.ENERGY_CORE);
        setCreativeTab(CreativeTab.ARMOR_TAB);
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> list, boolean advanced)
    {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);

        if (StringHelper.isShiftKeyDown())
        {
            list.add(StringHelper.localize("info.caske.energyCore"));
            list.add(StringHelper.localize("info.caske.energy") + ": " + stack.getTagCompound().getInteger("ENERGY") + " / " + capacity + " RF");
            list.add(StringHelper.localize("info.caske.io") + ": " + maxExtract + " RF/t");
        } else
            list.add(StringHelper.shiftForInfo());
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        // Clamp double x ∈ [0.0D, 1.0D]
        return Math.min(Math.max(1 - (stack.getTagCompound().getInteger("ENERGY") / ((double) capacity)), 0.0D), 1.0D);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        list.add(NBTHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
        list.add(NBTHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), capacity));
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if(!container.hasTagCompound()) {
            container.setTagCompound(new NBTTagCompound());
        }

        int energy = container.getTagCompound().getInteger("ENERGY");
        int energyReceived = Math.min(this.capacity - energy, Math.min(this.maxReceive, maxReceive));
        if(!simulate) {
            energy += energyReceived;
            container.getTagCompound().setInteger("ENERGY", energy);
        }

        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if(container.getTagCompound() != null && container.getTagCompound().hasKey("ENERGY")) {
            int energy = container.getTagCompound().getInteger("ENERGY");
            int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
            if(!simulate) {
                energy -= energyExtracted;
                container.getTagCompound().setInteger("ENERGY", energy);
            }

            return energyExtracted;
        } else {
            return 0;
        }
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return container.getTagCompound() != null && container.getTagCompound().hasKey("ENERGY")?container.getTagCompound().getInteger("ENERGY") : 0;
    }
    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }
}
