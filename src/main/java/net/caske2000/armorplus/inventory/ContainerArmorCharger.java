package net.caske2000.armorplus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Caske2000 on 25/03/2016.
 */
public class ContainerArmorCharger extends Container
{
    private final IInventory invCharger;
    private int energy;

    public ContainerArmorCharger(IInventory invPlayer, IInventory invCharger)
    {
        // TE: 0
        // PLAYER_LARGE: 1-27
        // HOTBAR: 28-36
        this.invCharger = invCharger;
        this.addSlotToContainer(new SlotArmor(invCharger, 0, 80, 35, true));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        }

        for (int k = 0; k < 9; ++k)
            this.addSlotToContainer(new Slot(invPlayer, k, 8 + k * 18, 142));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot)
    {
        ItemStack previous = null;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (current.stackSize == 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();

            if (fromSlot == 0) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 1, 37, true))
                    return null;
            } else {
                // From Player Inventory to TE Inventory
                if (this.inventorySlots.get(0).getStack() == null)
                {
                    this.inventorySlots.get(0).putStack(current);
                    slot.putStack(null);
                }
                //if (!this.mergeItemStack(current, 0, 0, false))
                //    return null;
            }

            if (current.stackSize == previous.stackSize)
                return null;
            slot.onPickupFromSlot(playerIn, current);
        }
        return previous;
    }

    @Override
    public void onCraftGuiOpened(ICrafting listener)
    {
        super.onCraftGuiOpened(listener);
        listener.sendAllWindowProperties(this, this.invCharger);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (ICrafting icrafting : this.crafters)
        {
            if (this.energy != this.invCharger.getField(0))
                icrafting.sendProgressBarUpdate(this, 0, this.invCharger.getField(0));
        }

        this.energy = this.invCharger.getField(0);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.invCharger.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.invCharger.isUseableByPlayer(playerIn);
    }
}
