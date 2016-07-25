package net.caske2000.commutablearmor.render;

import net.caske2000.commutablearmor.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Caske2000 on 5/02/2016.
 */
public final class ItemRenderRegister {
    public static void registerItemRenderer() {
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

        reg(ModItems.customSwordIron);
        reg(ModItems.customSwordDiamond);
        reg(ModItems.customSwordAlloy);

        reg(ModItems.armorUpgradeSpeed);
        reg(ModItems.armorUpgradeDeathProtection);
        reg(ModItems.armorUpgradeFeeder);
        reg(ModItems.armorUpgradeDigSpeed);
        reg(ModItems.armorUpgradePotionRemover);
        reg(ModItems.armorUpgradeEfficiency);
        reg(ModItems.armorUpgradeFlight);
        reg(ModItems.armorUpgradeNightVision);
        reg(ModItems.armorUpgradeFireResistance);
        reg(ModItems.armorUpgradeScuba);
        reg(ModItems.armorUpgradeStrength);

        reg(ModItems.manual);
        reg(ModItems.energyCore);
        reg(ModItems.alloy);
        reg(ModItems.armorUpgradeTemplate);
    }

    private static void reg(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}