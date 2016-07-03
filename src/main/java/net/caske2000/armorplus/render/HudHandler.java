package net.caske2000.armorplus.render;

import net.caske2000.armorplus.items.ItemCustomArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Caske2000 on 26/03/2016.
 */
public class HudHandler extends GuiScreen
{
    public static final HudHandler instance = new HudHandler();
    private static final ResourceLocation WARNING_SIGN = new ResourceLocation("armorplus", "textures/gui/manualBackground.png");
    private static final Minecraft mc = Minecraft.getMinecraft();

    private void checkHUD()
    {
        if ((mc.inGameHasFocus || (mc.currentScreen != null && (mc.currentScreen instanceof GuiChat))) && !mc.gameSettings.showDebugInfo)
        {
            ItemStack stack;
            for (int i = 0; i < 4; i++)
            {
                stack = mc.thePlayer.inventory.armorItemInSlot(i);
                if (stack != null)
                {
                    if (stack.getItem() instanceof ItemCustomArmor)
                    {
                        if (((ItemCustomArmor) stack.getItem()).isWarning())
                        {
                            drawWarningSign();
                            return;
                        }
                    }
                }
            }
        }
    }

    private void drawWarningSign()
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(WARNING_SIGN);
        this.drawTexturedModalRect(5, 5, 224, 0, 32, 32);
    }

    @SubscribeEvent
    public void RenderGameOverlayEvent(RenderGameOverlayEvent event)
    {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR)
            checkHUD();
    }
}
