package net.caske2000.armorplus.proxy;

import com.google.common.collect.Maps;
import net.caske2000.armorplus.ArmorPlus;
import net.caske2000.armorplus.blocks.ModBlocks;
import net.caske2000.armorplus.handler.GuiHandler;
import net.caske2000.armorplus.items.ArmorRecipe;
import net.caske2000.armorplus.items.ModItems;
import net.caske2000.armorplus.tileentities.TileEntityArmorCharger;
import net.caske2000.armorplus.tileentities.TileEntityArmorTable;
import net.caske2000.armorplus.handler.EventHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;

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
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(ArmorPlus.instance, new GuiHandler());
        GameRegistry.registerTileEntity(TileEntityArmorTable.class, "armorTable_tile_entity");
        GameRegistry.registerTileEntity(TileEntityArmorCharger.class, "armorCharger_tile_entity");

        // Crafting recipes
        RecipeSorter.register("armorplus:armorrecipe", ArmorRecipe.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped");

        GameRegistry.addRecipe(new ItemStack(ModBlocks.armorTable), "IXI", "LVL", "IXI", 'I', Blocks.REDSTONE_BLOCK, 'V', ModItems.energyCore, 'X', Items.IRON_CHESTPLATE, 'L', Blocks.REDSTONE_TORCH);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.armorCharger), "IXI", "LVL", "IXI", 'I', Blocks.REDSTONE_BLOCK, 'V', ModItems.energyCore, 'X', Items.COMPARATOR, 'L', Blocks.REDSTONE_TORCH);

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.manual), Items.BOOK, Items.REDSTONE, Blocks.LEVER);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alloy), Blocks.OBSIDIAN, ModItems.energyCore);
        GameRegistry.addRecipe(new ItemStack(ModItems.energyCore), "IXI", "LVL", "IXI", 'I', Blocks.REDSTONE_BLOCK, 'V', Items.COMPARATOR, 'X', Items.DIAMOND, 'L', Blocks.REDSTONE_TORCH);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradeTemplate), ModItems.energyCore, Items.BOOK, Items.DIAMOND);

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradeSpeed), ModItems.armorUpgradeTemplate, Items.DIAMOND, Items.SUGAR);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradeDeathProtection), ModItems.armorUpgradeTemplate, Items.DIAMOND, Items.DIAMOND_HOE);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradeFeeder), ModItems.armorUpgradeTemplate, Items.DIAMOND, Items.GOLDEN_APPLE);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradeDigSpeed), ModItems.armorUpgradeTemplate, Items.DIAMOND, Items.DIAMOND_PICKAXE);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradePotionRemover), ModItems.armorUpgradeTemplate, Items.DIAMOND, Items.GHAST_TEAR);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradeEfficiency), ModItems.armorUpgradeTemplate, Items.DIAMOND, Blocks.REDSTONE_BLOCK, Items.GOLD_INGOT);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradeFlight), ModItems.armorUpgradeTemplate, Items.DIAMOND, Items.GOLDEN_APPLE, Blocks.DIAMOND_BLOCK);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradeNightVision), ModItems.armorUpgradeTemplate, Items.DIAMOND, Items.GOLDEN_CARROT);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradeFireResistance), ModItems.armorUpgradeTemplate, Items.DIAMOND, Items.BLAZE_ROD);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradeScuba), ModItems.armorUpgradeTemplate, Items.DIAMOND, Items.REEDS);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorUpgradeStrength), ModItems.armorUpgradeTemplate, Items.DIAMOND, Items.BLAZE_POWDER);

        addRecipe(new ItemStack(ModItems.customBootsIron), "   ", "IVI", "I I", 'I', Items.IRON_INGOT, 'V', ModItems.energyCore);
        addRecipe(new ItemStack(ModItems.customLeggingsIron), "IVI", "I I", "I I", 'I', Items.IRON_INGOT, 'V', ModItems.energyCore);
        addRecipe(new ItemStack(ModItems.customChestplateIron), "I I", "IVI", "III", 'I', Items.IRON_INGOT, 'V', ModItems.energyCore);
        addRecipe(new ItemStack(ModItems.customHelmetIron), "IVI", "I I", "   ", 'I', Items.IRON_INGOT, 'V', ModItems.energyCore);

        addRecipe(new ItemStack(ModItems.customBootsDiamond), "   ", "IVI", "I I", 'I', Items.DIAMOND, 'V', ModItems.energyCore);
        addRecipe(new ItemStack(ModItems.customLeggingsDiamond), "IVI", "I I", "I I", 'I', Items.DIAMOND, 'V', ModItems.energyCore);
        addRecipe(new ItemStack(ModItems.customChestplateDiamond), "I I", "IVI", "III", 'I', Items.DIAMOND, 'V', ModItems.energyCore);
        addRecipe(new ItemStack(ModItems.customHelmetDiamond), "IVI", "I I", "   ", 'I', Items.DIAMOND, 'V', ModItems.energyCore);

        // TODO Create Alloy
        addRecipe(new ItemStack(ModItems.customBootsAlloy), "   ", "IVI", "I I", 'I', ModItems.alloy, 'V', ModItems.energyCore);
        addRecipe(new ItemStack(ModItems.customLeggingsAlloy), "IVI", "I I", "I I", 'I', ModItems.alloy, 'V', ModItems.energyCore);
        addRecipe(new ItemStack(ModItems.customChestplateAlloy), "I I", "IVI", "III", 'I', ModItems.alloy, 'V', ModItems.energyCore);
        addRecipe(new ItemStack(ModItems.customHelmetAlloy), "IVI", "I I", "   ", 'I', ModItems.alloy, 'V', ModItems.energyCore);
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

            for (String s2 : astring)
            {
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

        for (map = Maps.newHashMap(); i < recipeComponents.length; i += 2)
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

            if (map.containsKey(c0))
                aitemstack[i1] = (map.get(c0)).copy();
            else
                aitemstack[i1] = null;
        }

        ArmorRecipe shapedrecipes = new ArmorRecipe(stack, aitemstack);
        GameRegistry.addRecipe(shapedrecipes);
    }
}
