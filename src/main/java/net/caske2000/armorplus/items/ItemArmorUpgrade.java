package net.caske2000.armorplus.items;

import net.caske2000.armorplus.lib.Reference;
import net.caske2000.armorplus.util.StringHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Caske2000 on 5-3-2016.
 */
public class ItemArmorUpgrade extends Item
{
    private static final String[] types = Reference.TYPES;
    private byte armorType;

    /**
     * @param  index  the index of the upgrade type in the Reference.TYPES array
     * @param  armorType the type of armor this upgrade is applied to 0 is helmet, 1 is plate, 2 is legs, 3 is boots and 4 is for all types of armor
     */
    public ItemArmorUpgrade(byte index, byte armorType)
    {
        super();
        this.armorType = armorType;
        setUnlocalizedName(Reference.ARMOR_UPGRADE + types[index]);
        setRegistryName(Reference.ARMOR_UPGRADE + types[index]);
        setCreativeTab(CreativeTab.ARMOR_TAB);
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> list, boolean advanced)
    {
        list.add(StringHelper.localize("info.caske.upgrade_" + armorType));
    }

    public byte getArmorType()
    {
        return armorType;
    }
}
