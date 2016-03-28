package net.caske2000.armorplus.items;

import net.caske2000.armorplus.lib.Reference;
import net.minecraft.item.Item;

/**
 * Created by Caske2000 on 5-3-2016.
 */
public class ItemArmorUpgrade extends Item
{
    private static final String[] types = Reference.Names.TYPES;

    public ItemArmorUpgrade(byte index)
    {
        super();
        setUnlocalizedName(Reference.Names.ARMOR_UPGRADE + types[index]);
        setCreativeTab(CreativeTab.ARMOR_TAB);
        setMaxStackSize(1);
    }
}
