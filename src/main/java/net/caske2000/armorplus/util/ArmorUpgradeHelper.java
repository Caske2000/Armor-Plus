package net.caske2000.armorplus.util;

import net.caske2000.armorplus.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.Collection;

/**
 * Created by Caske2000 on 7-3-2016.
 */
public final class ArmorUpgradeHelper
{
    private static int extraCost = 0;
    private static Collection<PotionEffect> potionEffects;

    public static int onTick(String upgrade, EntityPlayer player)
    {
        extraCost = 0;
        if (upgrade.equals(Reference.Names.TYPES[0])) // SPEED
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 0, 0, false, false));
        else if (upgrade.equals(Reference.Names.TYPES[1])) // DEATH_PROTECTION
        {
            if (player.getHealth() <= 4)
            {
                player.heal(4);
                extraCost = 1000;
            }
        } else if (upgrade.equals(Reference.Names.TYPES[2])) // FEEDER
            player.addPotionEffect(new PotionEffect(Potion.saturation.id, 1, 0, false, false));
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
                    extraCost = 500;
                }
            }
        }
        return extraCost + Reference.Numbers.UPGRADE_ENERGY_COST;
    }
}
