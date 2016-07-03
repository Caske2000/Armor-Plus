package net.caske2000.armorplus.inventory;

import net.caske2000.armorplus.items.ItemCustomArmor;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Caske2000 on 6-3-2016.
 */
class SlotArmor extends Slot {
    private final boolean isInput;

    public SlotArmor(IInventory inv, int index, int xPos, int yPos, boolean isInput) {
        super(inv, index, xPos, yPos);
        this.isInput = isInput;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return (stack.getItem() instanceof ItemCustomArmor && isInput);
    }

}
