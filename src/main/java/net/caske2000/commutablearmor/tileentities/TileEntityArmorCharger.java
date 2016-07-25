package net.caske2000.commutablearmor.tileentities;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.caske2000.commutablearmor.inventory.ContainerArmorCharger;
import net.caske2000.commutablearmor.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Random;

/**
 * Created by Caske2000 on 25/03/2016.
 */
public class TileEntityArmorCharger extends TileEntityLockable implements IEnergyProvider, IEnergyReceiver, IInventory, ITickable {
    private final int maxEnergy = Reference.CHARGER_MAX_ENERGY;
    private final int maxTransfer = Reference.CHARGER_MAX_TRANSFER;
    private final Random rand = new Random();
    private int energy;
    private ItemStack armorChargerItemStack = null;
    private String customName;

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerArmorCharger(playerInventory, this);
    }

    @Override
    public void update() {
        if (!this.worldObj.isRemote) {
            if (this.armorChargerItemStack != null) {
                if (this.armorChargerItemStack.getItem() instanceof IEnergyContainerItem) {
                    IEnergyContainerItem item = (IEnergyContainerItem) this.armorChargerItemStack.getItem();
                    int e = Math.min(Math.min(energy, maxTransfer), item.getMaxEnergyStored(this.armorChargerItemStack) -
                            item.getEnergyStored(this.armorChargerItemStack));
                    item.receiveEnergy(this.armorChargerItemStack, e, false);
                    extractEnergy(null, e, false);
                }
            }
        } else if (this.armorChargerItemStack != null) {
            double xPos = rand.nextDouble();
            double zPos = rand.nextDouble();
            worldObj.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX() + xPos, pos.getY() + 0.5D, pos.getZ() + zPos, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public String getGuiID() {
        return "commutablearmor:armorCharger";
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);

        NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(0);
        if (nbttagcompound.getByte("Slot") == 0)
            this.armorChargerItemStack = ItemStack.loadItemStackFromNBT(nbttagcompound);

        energy = nbt.getInteger("Energy");

        if (nbt.hasKey("ArmorCharger", 8))
            this.setCustomName(nbt.getString("ArmorCharger"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Energy", energy);

        NBTTagList nbttaglist = new NBTTagList();
        if (this.armorChargerItemStack != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setByte("Slot", (byte) 0);
            this.armorChargerItemStack.writeToNBT(nbttagcompound);
            nbttaglist.appendTag(nbttagcompound);
        }

        nbt.setTag("Items", nbttaglist);

        if (this.hasCustomName())
            nbt.setString("ArmorCharger", this.getCustomName());

        return nbt;
    }

    @Override
    public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(this.energy, Math.min(this.maxTransfer, maxExtract));
        if (!simulate)
            this.energy -= energyExtracted;

        return energyExtracted;
    }

    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {
        int energyReceived = Math.min(this.maxEnergy - this.energy, Math.min(this.maxTransfer, maxReceive));
        if (!simulate)
            this.energy += energyReceived;

        return energyReceived;
    }

    @Override
    public int getEnergyStored(EnumFacing facing) {
        if (this.energy > this.maxEnergy)
            this.energy = this.maxEnergy;
        else if (this.energy < 0)
            this.energy = 0;
        return energy;
    }

    @Override
    public int getMaxEnergyStored(EnumFacing facing) {
        return this.maxEnergy;
    }

    /*@Override
    public ConnectOverride overridePipeConnection(IPipeTile.PipeType type, EnumFacing with) {
        return type == IPipeTile.PipeType.POWER && with == EnumFacing.DOWN ? ConnectOverride.CONNECT : ConnectOverride.DISCONNECT;
    }*/

    @Override
    public boolean canConnectEnergy(EnumFacing facing) {
        return true;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index != 0)
            return null;
        return this.armorChargerItemStack;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).stackSize <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, null);
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).stackSize <= 0)
                    this.setInventorySlotContents(index, null);
                else
                    this.setInventorySlotContents(index, this.getStackInSlot(index));

                this.markDirty();
                return itemstack;
            }
        } else
            return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (index == 0) {
            ItemStack itemstack = this.armorChargerItemStack;
            this.armorChargerItemStack = null;
            return itemstack;
        } else
            return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index != 0)
            return;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
            stack.stackSize = this.getInventoryStackLimit();

        if (stack != null && stack.stackSize == 0)
            stack = null;

        this.armorChargerItemStack = stack;
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return id == 0 ? this.energy : 0;
    }

    @Override
    public void setField(int id, int value) {
        if (id == 0)
            this.energy = value;
    }

    @Override
    public int getFieldCount() {
        return 1;
    }

    @Override
    public void clear() {
        this.armorChargerItemStack = null;
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.tileEntityArmorCharger";
    }

    private String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.equals("");
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }
}
