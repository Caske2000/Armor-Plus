package net.caske2000.armorplus.util;

import net.caske2000.armorplus.items.CustomPotion;
import net.caske2000.armorplus.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Collection;

/**
 * Created by Caske2000 on 7-3-2016.
 */
public final class ArmorUpgradeHelper
{
    private static int extraCost = 0;
    private static Collection<PotionEffect> potionEffects;

    public static int onTick(String upgrade, EntityPlayer player, World world)
    {
        extraCost = 0;
        if (upgrade.equals(Reference.Names.TYPES[0])) // SPEED
            player.addPotionEffect(new PotionEffect(CustomPotion.speedPotion.id, 0, 0, false, false));
        else if (upgrade.equals(Reference.Names.TYPES[1])) // DEATH_PROTECTION
        {
            if (player.getHealth() <= 2)
            {
                player.heal(4);
                extraCost = 4000;
            }
        } else if (upgrade.equals(Reference.Names.TYPES[2])) // FEEDER
            player.getFoodStats().addStats(1, 1.0F);
        else if (upgrade.equals(Reference.Names.TYPES[3])) // DIG_SPEED
            player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 1, 0, false, false));
        else if (upgrade.equals(Reference.Names.TYPES[4])) // POTION_REMOVER
        {
            potionEffects = player.getActivePotionEffects();
            for (PotionEffect effect : potionEffects)
            {
                int id = effect.getPotionID();
                if (Potion.potionTypes[id].isBadEffect())
                {
                    player.removePotionEffect(id);
                    extraCost = 700;
                }
            }
        } else if (upgrade.equals(Reference.Names.TYPES[6])) // FLIGHT
            extraCost = 200;
        else if (world.isDaytime() && upgrade.equals(Reference.Names.TYPES[7])) // NIGHT_VISION
            player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 3, 0, false, false));
        else if (upgrade.equals(Reference.Names.TYPES[8])) // FIRE_RESISTANCE
            player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 1, 0, false, false));
        else if (upgrade.equals(Reference.Names.TYPES[9])) // SCUBA
            player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 1, 0, false, false));
        else if (upgrade.equals(Reference.Names.TYPES[10])) // STRENGTH
            player.addPotionEffect(new PotionEffect(CustomPotion.strengthPotion.id, 1, 0, false, false));

        return extraCost + Reference.Numbers.UPGRADE_ENERGY_COST;
    }
}
