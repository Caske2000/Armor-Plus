package net.caske2000.armorplus.render;

import net.caske2000.armorplus.items.ModItems;
import net.caske2000.armorplus.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Caske2000 on 5/02/2016.
 */
public final class ItemRenderRegister
{
    public static void registerItemRenderer()
    {
        reg(ModItems.customHelmetIron);
        reg(ModItems.customChestplateIron);
        reg(ModItems.customLeggingsIron);
        reg(ModItems.customBootsIron);
        
        reg(ModItems.customHelmetDiamond);
        reg(ModItems.customChestplateDiamond);
        reg(ModItems.customLeggingsDiamond);
        reg(ModItems.customBootsDiamond);

        reg(ModItems.customHelmetAlloy);
        reg(ModItems.customChestplateAlloy);
        reg(ModItems.customLeggingsAlloy);
        reg(ModItems.customBootsAlloy);

        reg(ModItems.armorUpgradeSpeed);
        reg(ModItems.armorUpgradeDeathProtection);
        reg(ModItems.armorUpgradeFeeder);
        reg(ModItems.armorUpgradeDigSpeed);
        reg(ModItems.armorUpgradePotionRemover);
        reg(ModItems.armorUpgradeEfficiency);
        reg(ModItems.armorUpgradeFlight);
        reg(ModItems.armorUpgradeNightVision);

        reg (ModItems.manual);
        reg (ModItems.energyCore);
        reg (ModItems.alloy);
    }

    private static void reg(Item item)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
