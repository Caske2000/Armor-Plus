package net.caske2000.armorplus.items;

import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by Caske2000 on 28/03/2016.
 */
public class CustomArmorMaterial
{
    private String name;
    private final int[] energyData;

    public CustomArmorMaterial(String name, int[] energyData)
    {
        this.name = name;
        this.energyData = energyData;
    }

    public String getName()
    {
        return name;
    }

    public int getMaxEnergyMultiplier()
    {
        return energyData[0];
    }

    public int getMaxTransferMultiplier()
    {
        return energyData[1];
    }

    public int getExtraEnergyPerDamage()
    {
        return energyData[2];
    }

    public short getExtraMaxUpgradeAmount()
    {
        return (short) energyData[3];
    }
}
