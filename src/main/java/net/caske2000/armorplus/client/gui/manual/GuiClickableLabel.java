package net.caske2000.armorplus.client.gui.manual;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

/**
 * Created by Caske2000 on 27/03/2016.
 */
class GuiClickableLabel extends GuiButton
{
    public GuiClickableLabel(int buttonId, int x, int y, String buttonText)
    {
        super(buttonId, x, y, Minecraft.getMinecraft().fontRendererObj.getStringWidth(buttonText) + 10, 20,  ChatFormatting.UNDERLINE + buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            this.mouseDragged(mc, mouseX, mouseY);

            this.drawCenteredString(mc.fontRendererObj, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, 0xFFFFFF);
        }
    }
}
