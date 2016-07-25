package net.caske2000.commutablearmor.items;

import net.caske2000.commutablearmor.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Caske2000 on 26/03/2016.
 */
public class CreativeTab {
    public static final CreativeTabs ARMOR_TAB = new CreativeTabs(Reference.MODID) {
        @Override
        public Item getTabIconItem() {
            return ModItems.armorUpgradeSpeed;
        }
    };
}
