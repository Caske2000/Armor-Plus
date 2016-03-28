package net.caske2000.armorplus.client.gui.manual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Caske2000 on 27/03/2016.
 */
public class GuiNextPageButton extends GuiButton
{
    private final boolean isNextBtn;
    private final ResourceLocation TEXTURE = new ResourceLocation("armorplus", "textures/gui/manualBackground.png");

    public GuiNextPageButton(int id, int x, int y, boolean isNextBtn)
    {
        super(id, x, y, 16, 14, "");
        this.isNextBtn = isNextBtn;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            mc.getTextureManager().bindTexture(TEXTURE);
            int x = 223, y = 75;

            if (!isNextBtn)
                x += 17;

            if (this.hovered)
                y += 14;

            this.drawTexturedModalRect(this.xPosition, this.yPosition, x, y, this.width, this.height);
        }
    }
}
