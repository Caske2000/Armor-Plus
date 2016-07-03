package net.caske2000.armorplus.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Caske2000 on 26/03/2016.
 */
public class NBTHelper
{
    private static void initNBTTagCompound(ItemStack itemStack)
    {
        if (itemStack.getTagCompound() == null)
            itemStack.setTagCompound(new NBTTagCompound());
    }

    public static NBTTagCompound getNBT(ItemStack itemStack)
    {
        initNBTTagCompound(itemStack);

        return itemStack.getTagCompound();
    }

    public static void setInteger(ItemStack itemStack, String keyName, int keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setInteger(keyName, keyValue);
    }

    public static int getInteger(ItemStack itemStack, String keyName, int defaultValue)
    {
        initNBTTagCompound(itemStack);

        if (!itemStack.getTagCompound().hasKey(keyName))
            itemStack.getTagCompound().setInteger(keyName, defaultValue);

        return itemStack.getTagCompound().getInteger(keyName);
    }

    public static void setShort(ItemStack itemStack, String keyName, short keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setShort(keyName, keyValue);
    }

    public static short getShort(ItemStack itemStack, String keyName, short defaultValue)
    {
        initNBTTagCompound(itemStack);

        if (!itemStack.getTagCompound().hasKey(keyName))
            itemStack.getTagCompound().setShort(keyName, defaultValue);

        return itemStack.getTagCompound().getShort(keyName);
    }

    public static ItemStack setDefaultEnergyTag(ItemStack stack, int energy)
    {
        if (stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setInteger("ENERGY", energy);
        return stack;
    }
}
