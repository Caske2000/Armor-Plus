package net.caske2000.armorplus.items;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

/**
 * Created by Caske2000 on 28/03/2016.
 */
public class ArmorRecipe extends ShapedRecipes {
    public ArmorRecipe(ItemStack output, ItemStack[] items) {
        super(3, 3, items, output);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack itemstack = this.getRecipeOutput().copy();

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack1 = inv.getStackInSlot(i);

            if (itemstack1 != null && itemstack1.hasTagCompound())
                itemstack.setTagCompound(itemstack1.getTagCompound().copy());
        }

        return itemstack;
    }
}
