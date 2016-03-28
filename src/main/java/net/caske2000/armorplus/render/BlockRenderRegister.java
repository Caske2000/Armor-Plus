package net.caske2000.armorplus.render;

import net.caske2000.armorplus.blocks.ModBlocks;
import net.caske2000.armorplus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Caske2000 on 8-2-2016.
 */
public class BlockRenderRegister
{
    public static void registerBlockRenderer()
    {
        reg(ModBlocks.armorTable);
        reg(ModBlocks.armorCharger);
    }

    private static void reg(Block block)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }
}
