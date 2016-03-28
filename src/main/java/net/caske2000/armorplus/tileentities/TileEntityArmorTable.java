package net.caske2000.armorplus.tileentities;

import net.caske2000.armorplus.inventory.ContainerArmorTable;
import net.caske2000.armorplus.items.ItemArmorUpgrade;
import net.caske2000.armorplus.items.ItemCustomArmor;
import net.caske2000.armorplus.lib.Reference;
import net.caske2000.armorplus.util.ArmorTableRecipes;
import net.caske2000.armorplus.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.*;

import java.util.Random;

/**
 * Created by Caske2000 on 8-2-2016.
 */
public class TileEntityArmorTable extends TileEntityLockable implements ITickable, IInventory
{
    private ItemStack[] armorTableItemStacks = new ItemStack[3];
    private String customName;
    private short currentWorkTime = 0, totalWorkTime = 200;
    private final Random rand = new Random();

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerArmorTable(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.currentWorkTime;
            case 1:
                return this.totalWorkTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.currentWorkTime = (short) value;
                break;
            case 1:
                this.totalWorkTime = (short) value;
                break;
        }
    }

    @Override
    public int getFieldCount()
    {
        return 2;
    }

    @Override
    public String getGuiID()
    {
        return "armorplus:armorTable";
    }

    @Override
    public int getSizeInventory()
    {
        return armorTableItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        if (index < 0 || index >= this.getSizeInventory())
            return null;
        return this.armorTableItemStacks[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.getStackInSlot(index) != null)
        {
            ItemStack itemstack;

            if (this.getStackInSlot(index).stackSize <= count)
            {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, null);
                this.markDirty();
                return itemstack;
            } else
            {
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
    public ItemStack removeStackFromSlot(int index)
    {
        if (this.armorTableItemStacks[index] != null)
        {
            ItemStack itemstack = this.armorTableItemStacks[index];
            this.armorTableItemStacks[index] = null;
            return itemstack;
        } else
            return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        if (index < 0 || index >= this.getSizeInventory())
            return;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
            stack.stackSize = this.getInventoryStackLimit();

        if (stack != null && stack.stackSize == 0)
            stack = null;

        this.armorTableItemStacks[index] = stack;
        this.markDirty();
    }

    @Override
    public void update()
    {
        if (!this.worldObj.isRemote)
        {
            if (this.armorTableItemStacks[1] != null && this.armorTableItemStacks[0] != null)
            {
                if (this.armorTableItemStacks[1].getItem() instanceof ItemCustomArmor && this.armorTableItemStacks[0].getItem() instanceof ItemArmorUpgrade)
                {
                    if (NBTHelper.getShort(this.armorTableItemStacks[1], "UPGRADE_AMOUNT", (short) 0) < NBTHelper.getShort(this.armorTableItemStacks[1], "MAX_UPGRADE_AMOUNT", Reference.Numbers.MAX_UPGRADE_AMOUNT))
                    {
                        if (!this.armorTableItemStacks[1].getTagCompound().hasKey(this.armorTableItemStacks[0].getUnlocalizedName().substring(23)))
                        {
                            if (this.armorTableItemStacks[2] == null)
                            {
                                currentWorkTime++;
                                if (currentWorkTime >= totalWorkTime)
                                {
                                    currentWorkTime = 0;
                                    this.itemDone();
                                    this.markDirty();
                                }
                            } else
                                currentWorkTime = 0;
                        } else
                            currentWorkTime = 0;
                    } else
                        currentWorkTime = 0;
                } else
                    currentWorkTime = 0;
            } else
                currentWorkTime = 0;

        } else if (currentWorkTime > 0)
        {
            double xPos = rand.nextDouble();
            double zPos = rand.nextDouble();
            worldObj.spawnParticle(EnumParticleTypes.CRIT, pos.getX() + xPos, pos.getY() + 0.8D, pos.getZ() + zPos, 0.0D, 0.0D, 0.0D);
        }
    }

    private void itemDone()
    {
        this.armorTableItemStacks[2] = ArmorTableRecipes.getCraftingRecipe(this.armorTableItemStacks[0], this.armorTableItemStacks[1]);
        this.armorTableItemStacks[0] = null;
        this.armorTableItemStacks[1] = null;

        currentWorkTime = 0;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return index != 2;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < this.armorTableItemStacks.length; ++i)
            this.armorTableItemStacks[i] = null;
    }

    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.tileEntityArmorTable";
    }

    private String getCustomName()
    {
        return this.customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    @Override
    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.equals("");
    }

    @Override
    public IChatComponent getDisplayName()
    {
        return this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName());
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setShort("workTime", currentWorkTime);

        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.armorTableItemStacks.length; ++i)
        {
            if (this.armorTableItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte) i);
                this.armorTableItemStacks[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        nbt.setTag("Items", nbttaglist);

        if (this.hasCustomName())
            nbt.setString("ArmorTable", this.getCustomName());
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.armorTableItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot");
            if (j >= 0 && j < this.armorTableItemStacks.length)
                this.armorTableItemStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
        }

        currentWorkTime = nbt.getShort("workTime");
        totalWorkTime = 200;

        if (nbt.hasKey("ArmorTable", 8))
            this.setCustomName(nbt.getString("ArmorTable"));
    }

    @Override
    public void openInventory(EntityPlayer player)
    {

    }

    @Override
    public void closeInventory(EntityPlayer player)
    {

    }
}
