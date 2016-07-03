package net.caske2000.armorplus.items;

import net.caske2000.armorplus.lib.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.SoundEvent;
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

    public static Item customSwordIron;
    public static Item customSwordDiamond;
    public static Item customSwordAlloy;

    public static Item manual;
    public static Item energyCore;
    public static Item alloy;

    private static final ItemArmor.ArmorMaterial IRONMat = EnumHelper.addArmorMaterial("IRONMat", "armorplus:ironmat", 15, new int[]{2, 6, 5, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f);
    private static final CustomArmorMaterial customIRONMat = new CustomArmorMaterial("iron", new int[] {1, 1, 0, 0});
    private static final Item.ToolMaterial toolIRONMat = EnumHelper.addToolMaterial("toolIRONMat", 2, 250, 6.0F, 2.0F, 14);
    private static final ItemArmor.ArmorMaterial DIAMONDMat = EnumHelper.addArmorMaterial("DIAMONDMat", "armorplus:diamondmat", 33, new int[]{3, 8, 6, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f);
    private static final CustomArmorMaterial customDIAMONDMat = new CustomArmorMaterial("diamond", new int[] {10, 10, 10, 2});
    private static final Item.ToolMaterial toolDIAMONDMat = EnumHelper.addToolMaterial("toolDIAMONDMat", 3, 1561, 8.0F, 3.0F, 10);
    private static final ItemArmor.ArmorMaterial ALLOYMat = EnumHelper.addArmorMaterial("ALLOYMat", "armorplus:alloymat", 50, new int[]{3, 8, 6, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f);
    private static final CustomArmorMaterial customALLOYMat = new CustomArmorMaterial("alloy", new int[] {100, 100, 50, 4});
    private static final Item.ToolMaterial toolALLOYMat = EnumHelper.addToolMaterial("toolALLOYMat", 3, 2000, 10.0F, 4.0F, 20);

    public static void createItems()
    {
        // DON'T FORGET TO ADD THE ITEM TO THE RENDERREGISTER!
        // 3 is helmet, 2 is plate, 1 is legs, 0 is boots and 4 is for all types of armor
        GameRegistry.register(armorUpgradeSpeed = new ItemArmorUpgrade((byte) 0, (byte) 0));
        GameRegistry.register(armorUpgradeDeathProtection = new ItemArmorUpgrade((byte) 1, (byte) 2));
        GameRegistry.register(armorUpgradeFeeder = new ItemArmorUpgrade((byte) 2, (byte) 3));
        GameRegistry.register(armorUpgradeDigSpeed = new ItemArmorUpgrade((byte) 3, (byte) 2));
        GameRegistry.register(armorUpgradePotionRemover = new ItemArmorUpgrade((byte) 4, (byte) 2));
        GameRegistry.register(armorUpgradeEfficiency = new ItemArmorUpgrade((byte) 5, (byte) 4));
        GameRegistry.register(armorUpgradeFlight = new ItemArmorUpgrade((byte) 6, (byte) 2));
        GameRegistry.register(armorUpgradeNightVision = new ItemArmorUpgrade((byte) 7, (byte) 3));
        GameRegistry.register(armorUpgradeFireResistance = new ItemArmorUpgrade((byte) 8, (byte) 1));
        GameRegistry.register(armorUpgradeScuba = new ItemArmorUpgrade((byte) 9, (byte) 3));
        GameRegistry.register(armorUpgradeStrength = new ItemArmorUpgrade((byte) 10, (byte) 2));

        GameRegistry.register(customHelmetIron = new ItemCustomArmor(Reference.IRON_HELMET, IRONMat, customIRONMat, 1, EntityEquipmentSlot.HEAD));
        GameRegistry.register(customChestplateIron = new ItemCustomArmor(Reference.IRON_CHESTPLATE, IRONMat, customIRONMat, 1, EntityEquipmentSlot.CHEST));
        GameRegistry.register(customLeggingsIron = new ItemCustomArmor(Reference.IRON_LEGGINGS, IRONMat, customIRONMat, 2, EntityEquipmentSlot.LEGS));
        GameRegistry.register(customBootsIron = new ItemCustomArmor(Reference.IRON_BOOTS, IRONMat, customIRONMat, 1, EntityEquipmentSlot.FEET));

        GameRegistry.register(customHelmetDiamond = new ItemCustomArmor(Reference.DIAMOND_HELMET, DIAMONDMat, customDIAMONDMat, 1, EntityEquipmentSlot.HEAD));
        GameRegistry.register(customChestplateDiamond = new ItemCustomArmor(Reference.DIAMOND_CHESTPLATE, DIAMONDMat, customDIAMONDMat, 1, EntityEquipmentSlot.CHEST));
        GameRegistry.register(customLeggingsDiamond = new ItemCustomArmor(Reference.DIAMOND_LEGGINGS, DIAMONDMat, customDIAMONDMat, 2, EntityEquipmentSlot.LEGS));
        GameRegistry.register(customBootsDiamond = new ItemCustomArmor(Reference.DIAMOND_BOOTS, DIAMONDMat, customDIAMONDMat, 1, EntityEquipmentSlot.FEET));

        GameRegistry.register(customHelmetAlloy = new ItemCustomArmor(Reference.ALLOY_HELMET, ALLOYMat, customALLOYMat, 1, EntityEquipmentSlot.HEAD));
        GameRegistry.register(customChestplateAlloy = new ItemCustomArmor(Reference.ALLOY_CHESTPLATE, ALLOYMat, customALLOYMat, 1, EntityEquipmentSlot.CHEST));
        GameRegistry.register(customLeggingsAlloy = new ItemCustomArmor(Reference.ALLOY_LEGGINGS, ALLOYMat, customALLOYMat, 2, EntityEquipmentSlot.LEGS));
        GameRegistry.register(customBootsAlloy = new ItemCustomArmor(Reference.ALLOY_BOOTS, ALLOYMat, customALLOYMat, 1, EntityEquipmentSlot.FEET));

        GameRegistry.register(customSwordIron = new ItemCustomSword(toolIRONMat, Reference.IRON_SWORD, customIRONMat));
        GameRegistry.register(customSwordDiamond = new ItemCustomSword(toolDIAMONDMat, Reference.DIAMOND_SWORD, customDIAMONDMat));
        GameRegistry.register(customSwordAlloy = new ItemCustomSword(toolALLOYMat, Reference.ALLOY_SWORD, customALLOYMat));

        GameRegistry.register(manual = new ItemManual());
        GameRegistry.register(energyCore = new ItemEnergyCore());
        GameRegistry.register(alloy = new Item().setUnlocalizedName(Reference.ALLOY).setRegistryName(Reference.ALLOY).setCreativeTab(CreativeTab.ARMOR_TAB));
        GameRegistry.register(armorUpgradeTemplate = new Item().setUnlocalizedName(Reference.ARMOR_UPGRADE_TEMPLATE).setRegistryName(Reference.ARMOR_UPGRADE_TEMPLATE).setCreativeTab(CreativeTab.ARMOR_TAB));
    }
}
