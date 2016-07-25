package net.caske2000.commutablearmor.util;

import net.caske2000.commutablearmor.items.CustomPotion;
import net.caske2000.commutablearmor.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.Collection;

/**
 * Created by Caske2000 on 7-3-2016.
 */
public final class ArmorUpgradeHelper {

    public static int onTick(String upgrade, EntityPlayer player, World world) {
        int extraCost = 0;
        if (upgrade.equals(Reference.TYPES[0])) // SPEED
            player.addPotionEffect(new PotionEffect(CustomPotion.speedPotion, 0, 0, false, false));
        else if (upgrade.equals(Reference.TYPES[1])) // DEATH_PROTECTION
        {
            if (player.getHealth() <= 2) {
                player.heal(4);
                extraCost = 4000;
            }
        } else if (upgrade.equals(Reference.TYPES[2])) // FEEDER
            player.getFoodStats().addStats(1, 1.0F);
        else if (upgrade.equals(Reference.TYPES[3])) // DIG_SPEED
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(3), 1, 0, false, false));
        else if (upgrade.equals(Reference.TYPES[4])) // POTION_REMOVER
        {
            Collection<PotionEffect> potionEffects = player.getActivePotionEffects();
            for (PotionEffect effect : potionEffects) {
                if (effect.getPotion().isBadEffect()) {
                    player.removePotionEffect(effect.getPotion());
                    extraCost = 700;
                }
            }
        } else if (upgrade.equals(Reference.TYPES[6])) // FLIGHT
            extraCost = 200;
        else if (world.isDaytime() && upgrade.equals(Reference.TYPES[7])) // NIGHT_VISION
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 3, 0, false, false));
        else if (upgrade.equals(Reference.TYPES[8])) // FIRE_RESISTANCE
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 1, 0, false, false));
        else if (upgrade.equals(Reference.TYPES[9])) // SCUBA
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(13), 1, 0, false, false));
        else if (upgrade.equals(Reference.TYPES[10])) // STRENGTH
            player.addPotionEffect(new PotionEffect(CustomPotion.strengthPotion, 1, 0, false, false));

        return extraCost + Reference.UPGRADE_ENERGY_COST;
    }
}
