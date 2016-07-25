package net.caske2000.commutablearmor.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Caske2000 on 9-2-2016.
 */
public class ContainerArmorTable extends Container {
    private final IInventory invTable;
    private int currentWorkTime, totalWorkTime;

    public ContainerArmorTable(IInventory invPlayer, IInventory invTable) {
        // TE: 0-2
        // PLAYER_LARGE: 3-29
        // HOTBAR: 30-38
        this.invTable = invTable;
        this.addSlotToContainer(new SlotUpgrade(invTable, 0, 56, 17));
        this.addSlotToContainer(new SlotArmor(invTable, 1, 56, 53, true));
        this.addSlotToContainer(new SlotArmor(invTable, 2, 116, 35, false));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        }

        for (int k = 0; k < 9; ++k)
            this.addSlotToContainer(new Slot(invPlayer, k, 8 + k * 18, 142));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = null;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (current.stackSize == 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();

            if (fromSlot < 3) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 3, 39, true))
                    return null;
            } else {
                // From Player Inventory to TE Inventory
                if (!this.mergeItemStack(current, 0, 2, false))
                    return null;
            }

            if (current.stackSize == previous.stackSize)
                return null;
            slot.onPickupFromSlot(playerIn, current);
        }
        return previous;
    }

    @Override
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
        boolean success = false;
        int index = startIndex;

        if (useEndIndex)
            index = endIndex - 1;

        Slot slot;
        ItemStack stackInSlot;

        if (stack.isStackable()) {
            while (stack.stackSize > 0 && (!useEndIndex && index < endIndex || useEndIndex && index >= startIndex)) {
                slot = this.inventorySlots.get(index);
                stackInSlot = slot.getStack();

                if (stackInSlot != null && stackInSlot.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == stackInSlot.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, stackInSlot)) {
                    int l = stackInSlot.stackSize + stack.stackSize;
                    int maxsize = Math.min(stack.getMaxStackSize(), slot.getItemStackLimit(stack));

                    if (l <= maxsize) {
                        stack.stackSize = 0;
                        stackInSlot.stackSize = l;
                        slot.onSlotChanged();
                        success = true;
                    } else if (stackInSlot.stackSize < maxsize) {
                        stack.stackSize -= stack.getMaxStackSize() - stackInSlot.stackSize;
                        stackInSlot.stackSize = stack.getMaxStackSize();
                        slot.onSlotChanged();
                        success = true;
                    }
                }
                if (useEndIndex)
                    --index;
                else
                    ++index;
            }
        }

        if (stack.stackSize > 0) {
            if (useEndIndex)
                index = endIndex - 1;
            else
                index = startIndex;

            while (!useEndIndex && index < endIndex || useEndIndex && index >= startIndex && stack.stackSize > 0) {
                slot = this.inventorySlots.get(index);
                stackInSlot = slot.getStack();

                // Forge: Make sure to respect isItemValid in the slot.
                if (stackInSlot == null && slot.isItemValid(stack)) {
                    if (stack.stackSize < slot.getItemStackLimit(stack)) {
                        slot.putStack(stack.copy());
                        stack.stackSize = 0;
                        success = true;
                        break;
                    } else {
                        ItemStack newstack = stack.copy();
                        newstack.stackSize = slot.getItemStackLimit(stack);
                        slot.putStack(newstack);
                        stack.stackSize -= slot.getItemStackLimit(stack);
                        success = true;
                    }
                }
                if (useEndIndex)
                    --index;
                else
                    ++index;
            }
        }
        return success;
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.invTable);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.currentWorkTime != this.invTable.getField(0))
                icontainerlistener.sendProgressBarUpdate(this, 0, this.invTable.getField(0));

            if (this.totalWorkTime != this.invTable.getField(1))
                icontainerlistener.sendProgressBarUpdate(this, 1, this.invTable.getField(1));
        }

        this.currentWorkTime = this.invTable.getField(0);
        this.totalWorkTime = this.invTable.getField(1);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.invTable.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.invTable.isUseableByPlayer(playerIn);
    }
}
