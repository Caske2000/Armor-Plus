package net.caske2000.armorplus.lib;

import net.caske2000.armorplus.items.ItemArmorUpgrade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Caske2000 on 6-3-2016.
 */
public final class ArmorTableRecipes
{
    public static ItemStack getCraftingRecipe(ItemStack upgrade, ItemStack armor)
    {
        if (upgrade == null || armor == null)
            throw new NullPointerException("The upgrade or armor used for crafting returned a null.");

        ItemStack result = armor.copy();

        if (upgrade.getItem() instanceof ItemArmorUpgrade)
        {
            if (!result.hasTagCompound())
                result.setTagCompound(new NBTTagCompound());
            result.getTagCompound().setBoolean(upgrade.getUnlocalizedName().substring(23), true);
            result.getTagCompound().setShort("UPGRADE_AMOUNT", (short) (result.getTagCompound().getShort("UPGRADE_AMOUNT") + 1));
        }

        return result;
    }
}
