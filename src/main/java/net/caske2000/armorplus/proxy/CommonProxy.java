package net.caske2000.armorplus.proxy;

import com.google.common.collect.Maps;
import net.caske2000.armorplus.ArmorPlus;
import net.caske2000.armorplus.blocks.ModBlocks;
import net.caske2000.armorplus.client.gui.GuiHandler;
import net.caske2000.armorplus.items.ArmorRecipe;
import net.caske2000.armorplus.items.ModItems;
import net.caske2000.armorplus.tileentities.TileEntityArmorCharger;
import net.caske2000.armorplus.tileentities.TileEntityArmorTable;
import net.caske2000.armorplus.util.EventHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Map;

/**
 * Created by Caske2000 on 5/02/2016.
 */
public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        ModItems.createItems();
        ModBlocks.createBlocks();
    }

    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(new EventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(ArmorPlus.instance, new GuiHandler());
        GameRegistry.registerTileEntity(TileEntityArmorTable.class, "armorTable_tile_entity");
        GameRegistry.registerTileEntity(TileEntityArmorCharger.class, "armorCharger_tile_entity");

        // Crafting recipes
        GameRegistry.addRecipe(new ItemStack(ModBlocks.armorTable), new Object[]{"IXI", "LVL", "IXI", 'I', Blocks.redstone_block, 'V', ModItems.energyCore, 'X', Items.iron_chestplate, 'L', Blocks.redstone_torch});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.armorCharger), new Object[]{"IXI", "LVL", "IXI", 'I', Blocks.redstone_block, 'V', ModItems.energyCore, 'X', Items.comparator, 'L', Blocks.redstone_torch});

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.manual), new Object[]{Items.book, Items.redstone, Blocks.lever});
        GameRegistry.addRecipe(new ItemStack(ModItems.energyCore), new Object[]{"IXI", "LVL", "IXI", 'I', Blocks.redstone_block, 'V', Items.comparator, 'X', Items.diamond, 'L', Blocks.redstone_torch});

        addRecipe(new ItemStack(ModItems.customBootsIron), new Object[]{"   ", "IVI", "I I", 'I', Items.iron_ingot, 'V', ModItems.energyCore});
        addRecipe(new ItemStack(ModItems.customLeggingsIron), new Object[]{"IVI", "I I", "I I", 'I', Items.iron_ingot, 'V', ModItems.energyCore});
        addRecipe(new ItemStack(ModItems.customChestplateIron), new Object[]{"I I", "IVI", "III", 'I', Items.iron_ingot, 'V', ModItems.energyCore});
        addRecipe(new ItemStack(ModItems.customHelmetIron), new Object[]{"IVI", "I I", "   ", 'I', Items.iron_ingot, 'V', ModItems.energyCore});

        addRecipe(new ItemStack(ModItems.customBootsDiamond), new Object[]{"   ", "IVI", "I I", 'I', Items.diamond, 'V', ModItems.energyCore});
        addRecipe(new ItemStack(ModItems.customLeggingsDiamond), new Object[]{"IVI", "I I", "I I", 'I', Items.diamond, 'V', ModItems.energyCore});
        addRecipe(new ItemStack(ModItems.customChestplateDiamond), new Object[]{"I I", "IVI", "III", 'I', Items.diamond, 'V', ModItems.energyCore});
        addRecipe(new ItemStack(ModItems.customHelmetDiamond), new Object[]{"IVI", "I I", "   ", 'I', Items.diamond, 'V', ModItems.energyCore});

        // TODO Create Alloy
        addRecipe(new ItemStack(ModItems.customBootsAlloy), new Object[]{"   ", "IVI", "I I", 'I', Blocks.obsidian, 'V', ModItems.energyCore});
        addRecipe(new ItemStack(ModItems.customLeggingsAlloy), new Object[]{"IVI", "I I", "I I", 'I', Blocks.obsidian, 'V', ModItems.energyCore});
        addRecipe(new ItemStack(ModItems.customChestplateAlloy), new Object[]{"I I", "IVI", "III", 'I', Blocks.obsidian, 'V', ModItems.energyCore});
        addRecipe(new ItemStack(ModItems.customHelmetAlloy), new Object[]{"IVI", "I I", "   ", 'I', Blocks.obsidian, 'V', ModItems.energyCore});
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    }

    private void addRecipe(ItemStack stack, Object... recipeComponents)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipeComponents[i] instanceof String[])
        {
            String[] astring = (String[])(recipeComponents[i++]);

            for (int l = 0; l < astring.length; ++l)
            {
                String s2 = astring[l];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }
        else
        {
            while (recipeComponents[i] instanceof String)
            {
                String s1 = (String)recipeComponents[i++];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }

        Map<Character, ItemStack> map;

        for (map = Maps.<Character, ItemStack>newHashMap(); i < recipeComponents.length; i += 2)
        {
            Character character = (Character)recipeComponents[i];
            ItemStack itemstack = null;

            if (recipeComponents[i + 1] instanceof Item)
                itemstack = new ItemStack((Item)recipeComponents[i + 1]);
            else if (recipeComponents[i + 1] instanceof Block)
                itemstack = new ItemStack((Block)recipeComponents[i + 1], 1, 32767);
            else if (recipeComponents[i + 1] instanceof ItemStack)
                itemstack = (ItemStack)recipeComponents[i + 1];

            map.put(character, itemstack);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (map.containsKey(Character.valueOf(c0)))
                aitemstack[i1] = (map.get(Character.valueOf(c0))).copy();
            else
                aitemstack[i1] = null;
        }

        ArmorRecipe shapedrecipes = new ArmorRecipe(stack, aitemstack);
        GameRegistry.addRecipe(shapedrecipes);
    }
}
