package net.caske2000.armorplus.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Caske2000 on 8-2-2016.
 */
public class ModBlocks
{
    public static Block armorTable;
    public static Block armorCharger;

    public static void createBlocks()
    {
        // DON'T FORGET TO ADD THE BLOCK TO THE RENDERREGISTER!
        GameRegistry.registerBlock(armorTable = new BlockArmorTable(), "armorTable_block");
        GameRegistry.registerBlock(armorCharger = new BlockArmorCharger(), "armorCharger_block");
    }
}
