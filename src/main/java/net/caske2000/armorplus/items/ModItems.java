package net.caske2000.armorplus.items;

import net.caske2000.armorplus.lib.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Caske2000 on 5/02/2016.
 */
public final class ModItems
{
    public static Item armorUpgradeTemplate;
    public static Item armorUpgradeSpeed;
    public static Item armorUpgradeDeathProtection;
    public static Item armorUpgradeFeeder;
    public static Item armorUpgradeDigSpeed;
    public static Item armorUpgradePotionRemover;
    public static Item armorUpgradeEfficiency;
    public static Item armorUpgradeFlight;
    public static Item armorUpgradeNightVision;
    public static Item armorUpgradeFireResistance;
    public static Item armorUpgradeScuba;
    public static Item armorUpgradeStrength;

    public static Item customBootsIron;
    public static Item customLeggingsIron;
    public static Item customChestplateIron;
    public static Item customHelmetIron;
    
    public static Item customBootsDiamond;
    public static Item customLeggingsDiamond;
    public static Item customChestplateDiamond;
    public static Item customHelmetDiamond;

    public static Item customBootsAlloy;
    public static Item customLeggingsAlloy;
    public static Item customChestplateAlloy;
    public static Item customHelmetAlloy;

    public static Item manual;
    public static Item energyCore;
    public static Item alloy;

    private static final ItemArmor.ArmorMaterial IRONMat = EnumHelper.addArmorMaterial("IRONMat", "armorplus:ironmat", 15, new int[]{2, 6, 5, 2}, 9);
    private static final CustomArmorMaterial matIRON = new CustomArmorMaterial("iron", new int[] {1, 1, 0, 0});
    private static final ItemArmor.ArmorMaterial DIAMONDMat = EnumHelper.addArmorMaterial("DIAMONDMat", "armorplus:diamondmat", 33, new int[]{3, 8, 6, 3}, 10);
    private static final CustomArmorMaterial matDIAMOND = new CustomArmorMaterial("diamond", new int[] {10, 10, 10, 2});
    private static final ItemArmor.ArmorMaterial ALLOYMat = EnumHelper.addArmorMaterial("ALLOYMat", "armorplus:alloymat", 50, new int[]{3, 8, 6, 3}, 20);
    private static final CustomArmorMaterial matALLOY = new CustomArmorMaterial("alloy", new int[] {100, 100, 50, 4});

    public static void createItems()
    {
        // DON'T FORGET TO ADD THE ITEM TO THE RENDERREGISTER!
        GameRegistry.registerItem(armorUpgradeSpeed = new ItemArmorUpgrade((byte) 0, (byte) 3), Reference.Names.ARMOR_UPGRADE + Reference.Names.TYPES[0]);
        GameRegistry.registerItem(armorUpgradeDeathProtection = new ItemArmorUpgrade((byte) 1, (byte) 1), Reference.Names.ARMOR_UPGRADE + Reference.Names.TYPES[1]);
        GameRegistry.registerItem(armorUpgradeFeeder = new ItemArmorUpgrade((byte) 2, (byte) 0), Reference.Names.ARMOR_UPGRADE + Reference.Names.TYPES[2]);
        GameRegistry.registerItem(armorUpgradeDigSpeed = new ItemArmorUpgrade((byte) 3, (byte) 1), Reference.Names.ARMOR_UPGRADE + Reference.Names.TYPES[3]);
        GameRegistry.registerItem(armorUpgradePotionRemover = new ItemArmorUpgrade((byte) 4, (byte) 1), Reference.Names.ARMOR_UPGRADE + Reference.Names.TYPES[4]);
        GameRegistry.registerItem(armorUpgradeEfficiency = new ItemArmorUpgrade((byte) 5, (byte) 4), Reference.Names.ARMOR_UPGRADE + Reference.Names.TYPES[5]);
        GameRegistry.registerItem(armorUpgradeFlight = new ItemArmorUpgrade((byte) 6, (byte) 1), Reference.Names.ARMOR_UPGRADE + Reference.Names.TYPES[6]);
        GameRegistry.registerItem(armorUpgradeNightVision = new ItemArmorUpgrade((byte) 7, (byte) 0), Reference.Names.ARMOR_UPGRADE + Reference.Names.TYPES[7]);
        GameRegistry.registerItem(armorUpgradeFireResistance = new ItemArmorUpgrade((byte) 8, (byte) 2), Reference.Names.ARMOR_UPGRADE + Reference.Names.TYPES[8]);
        GameRegistry.registerItem(armorUpgradeScuba = new ItemArmorUpgrade((byte) 9, (byte) 0), Reference.Names.ARMOR_UPGRADE + Reference.Names.TYPES[9]);
        GameRegistry.registerItem(armorUpgradeStrength = new ItemArmorUpgrade((byte) 10, (byte) 1), Reference.Names.ARMOR_UPGRADE + Reference.Names.TYPES[10]);

        GameRegistry.registerItem(customHelmetIron = new ItemCustomArmor(Reference.Names.IRON_HELMET, IRONMat, matIRON, 1, 0), Reference.Names.IRON_HELMET);
        GameRegistry.registerItem(customChestplateIron = new ItemCustomArmor(Reference.Names.IRON_CHESTPLATE, IRONMat, matIRON, 1, 1), Reference.Names.IRON_CHESTPLATE);
        GameRegistry.registerItem(customLeggingsIron = new ItemCustomArmor(Reference.Names.IRON_LEGGINGS, IRONMat, matIRON, 2, 2), Reference.Names.IRON_LEGGINGS);
        GameRegistry.registerItem(customBootsIron = new ItemCustomArmor(Reference.Names.IRON_BOOTS, IRONMat, matIRON, 1, 3), Reference.Names.IRON_BOOTS);

        GameRegistry.registerItem(customHelmetDiamond = new ItemCustomArmor(Reference.Names.DIAMOND_HELMET, DIAMONDMat, matDIAMOND, 1, 0), Reference.Names.DIAMOND_HELMET);
        GameRegistry.registerItem(customChestplateDiamond = new ItemCustomArmor(Reference.Names.DIAMOND_CHESTPLATE, DIAMONDMat, matDIAMOND, 1, 1), Reference.Names.DIAMOND_CHESTPLATE);
        GameRegistry.registerItem(customLeggingsDiamond = new ItemCustomArmor(Reference.Names.DIAMOND_LEGGINGS, DIAMONDMat, matDIAMOND, 2, 2), Reference.Names.DIAMOND_LEGGINGS);
        GameRegistry.registerItem(customBootsDiamond = new ItemCustomArmor(Reference.Names.DIAMOND_BOOTS, DIAMONDMat, matDIAMOND, 1, 3), Reference.Names.DIAMOND_BOOTS);

        GameRegistry.registerItem(customHelmetAlloy = new ItemCustomArmor(Reference.Names.ALLOY_HELMET, ALLOYMat, matALLOY, 1, 0), Reference.Names.ALLOY_HELMET);
        GameRegistry.registerItem(customChestplateAlloy = new ItemCustomArmor(Reference.Names.ALLOY_CHESTPLATE, ALLOYMat, matALLOY, 1, 1), Reference.Names.ALLOY_CHESTPLATE);
        GameRegistry.registerItem(customLeggingsAlloy = new ItemCustomArmor(Reference.Names.ALLOY_LEGGINGS, ALLOYMat, matALLOY, 2, 2), Reference.Names.ALLOY_LEGGINGS);
        GameRegistry.registerItem(customBootsAlloy = new ItemCustomArmor(Reference.Names.ALLOY_BOOTS, ALLOYMat, matALLOY, 1, 3), Reference.Names.ALLOY_BOOTS);

        GameRegistry.registerItem(manual = new ItemManual(), Reference.Names.MANUAL);
        GameRegistry.registerItem(energyCore = new ItemEnergyCore(), Reference.Names.ENERGY_CORE);
        GameRegistry.registerItem(alloy = new Item().setUnlocalizedName(Reference.Names.ALLOY).setCreativeTab(CreativeTab.ARMOR_TAB), Reference.Names.ALLOY);
        GameRegistry.registerItem(armorUpgradeTemplate = new Item().setUnlocalizedName(Reference.Names.ARMOR_UPGRADE_TEMPLATE).setCreativeTab(CreativeTab.ARMOR_TAB), Reference.Names.ARMOR_UPGRADE_TEMPLATE);
    }
}
