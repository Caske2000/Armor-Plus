package net.caske2000.armorplus.lib;

import net.caske2000.armorplus.client.gui.manual.GuiItemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caske2000 on 8-2-2016.
 */
public final class Reference
{
    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus Mod";
    public static final String VERSION = "0.1";

    public static final String CLIENTPROXY = "net.caske2000.armorplus.proxy.ClientProxy";
    public static final String SERVERPROXY = "net.caske2000.armorplus.proxy.ServerProxy";

    public static final List<GuiItemInfo> blocks = new ArrayList<GuiItemInfo>(), items = new ArrayList<GuiItemInfo>();

    public static final class Names
    {
        public static final String ARMOR_UPGRADE = "armorUpgrade_item_";
        public static final String MANUAL = "manual_item";
        public static final String ENERGY_CORE = "energyCore_item";
        
        public static final String IRON_HELMET = "customHelmetIron_item";
        public static final String IRON_CHESTPLATE = "customChestplateIron_item";
        public static final String IRON_LEGGINGS = "customLeggingsIron_item";
        public static final String IRON_BOOTS = "customBootsIron_item";
        
        public static final String DIAMOND_HELMET = "customHelmetDiamond_item";
        public static final String DIAMOND_CHESTPLATE = "customChestplateDiamond_item";
        public static final String DIAMOND_LEGGINGS = "customLeggingsDiamond_item";
        public static final String DIAMOND_BOOTS = "customBootsDiamond_item";

        public static final String ALLOY_HELMET = "customHelmetAlloy_item";
        public static final String ALLOY_CHESTPLATE = "customChestplateAlloy_item";
        public static final String ALLOY_LEGGINGS = "customLeggingsAlloy_item";
        public static final String ALLOY_BOOTS = "customBootsAlloy_item";
        
        public static final String[] TYPES = { "SPEED", "DEATH_PROTECTION", "FEEDER" , "DIG_SPEED", "POTION_REMOVER", "EFFICIENCY"};
    }

    public static final class Numbers
    {
        public static final int CHARGER_MAX_ENERGY = 50000;
        public static final int CHARGER_MAX_TRANSFER = 1000;

        public static final int CORE_MAX_ENERGY = 10000;
        public static final int CORE_MAX_TRANSFER = 100;

        public static final int UPGRADE_ENERGY_COST = 10;
        public static final short MAX_UPGRADE_AMOUNT = 1;

        public static final int ENERGY_PER_DAMAGE = 50;
        public static final int ARMOR_MAX_ENERGY = 10000;
        public static final int ARMOR_MAX_TRANSFER = 100;
    }
}
