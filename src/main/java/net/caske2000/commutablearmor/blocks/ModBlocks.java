package net.caske2000.commutablearmor.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Caske2000 on 8-2-2016.
 */
public class ModBlocks {
    public static Block armorTable;
    public static Block armorCharger;

    public static ItemBlock armorTableItem;
    public static ItemBlock armorChargerItem;

    public static void createBlocks() {
        // DON'T FORGET TO ADD THE BLOCK TO THE RENDERREGISTER!
        GameRegistry.register(armorTable = new BlockArmorTable());
        GameRegistry.register(armorCharger = new BlockArmorCharger());

        armorTableItem = new ItemBlock(armorTable);
        armorChargerItem = new ItemBlock(armorCharger);

        GameRegistry.register(armorTableItem.setRegistryName(armorTable.getRegistryName()));
        GameRegistry.register(armorChargerItem.setRegistryName(armorCharger.getRegistryName()));
    }
}
