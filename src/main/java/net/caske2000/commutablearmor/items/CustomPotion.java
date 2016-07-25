package net.caske2000.commutablearmor.items;

import net.caske2000.commutablearmor.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Caske2000 on 29/03/2016.
 */
public class CustomPotion extends Potion {
    public static final CustomPotion speedPotion = (CustomPotion) new CustomPotion(new ResourceLocation(Reference.MODID + ":speed"), false, 8171462).setPotionName(Reference.MODID + "potion.speedPotion").registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2);
    public static final CustomPotion strengthPotion = (CustomPotion) new CustomPotion(new ResourceLocation(Reference.MODID + ":strength"), false, 8171462).setPotionName(Reference.MODID + "potion.strengthPotion").registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.5D, 2);

    protected CustomPotion(ResourceLocation location, boolean badEffect, int potionColor) {
        super(badEffect, potionColor);
    }

    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        // Don't render anything
    }

    @Override
    public boolean shouldRender(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean shouldRenderHUD(PotionEffect effect) {
        return false;
    }
}
