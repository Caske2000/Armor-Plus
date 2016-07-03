package net.caske2000.armorplus.inventory;

import net.caske2000.armorplus.items.ItemArmorUpgrade;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Caske2000 on 6-3-2016.
 */
class SlotUpgrade extends Slot {
    public SlotUpgrade(IInventory inv, int index, int xPos, int yPos) {
        super(inv, index, xPos, yPos);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemArmorUpgrade;
    }
}
