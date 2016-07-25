package net.caske2000.commutablearmor.achievements;

import net.caske2000.commutablearmor.blocks.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

/**
 * Created by Caske2000 on 25/03/2016.
 */
public class ArmorAchievements {
    public static Achievement tinkerer;
    public static Achievement charger;

    public static void init() {
        tinkerer = new Achievement("achievement.commutablearmor_tinkerer", "commutablearmor_tinkerer", 0, 0, new ItemStack(ModBlocks.armorTable), null).setSpecial().registerStat();
        charger = new Achievement("achievement.commutablearmor_charger", "commutablearmor_charger", 2, 0, new ItemStack(ModBlocks.armorCharger), tinkerer).registerStat();
        AchievementPage.registerAchievementPage(new AchievementPage("Commutable Armor", tinkerer, charger));
    }
}
