package net.caske2000.armorplus.client.gui.manual;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;

/**
 * Created by Caske2000 on 27/03/2016.
 */
class GuiTexturedButton extends GuiButton
{
    private final ItemStack itemStack;
    private final int scale;
    private final int stringWidth;
    private final String text;
    private ScaledResolution res;

    public GuiTexturedButton(int buttonId, int x, int y, ItemStack itemStack, String text, int scale)
    {
        // The size of a scale = 1 item is 15x15 pixels
        super(buttonId, x, y, 15 * scale, 15 * scale, "");
        this.itemStack = itemStack;
        this.text = ChatFormatting.UNDERLINE + text;
        this.stringWidth = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
        this.scale = scale;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();
            RenderHelper.enableGUIStandardItemLighting();

            GlStateManager.scale(scale, scale, scale);
            mc.getRenderItem().renderItemIntoGUI(itemStack, xPosition / scale, yPosition / scale);

            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();

            if (getHoverState(this.hovered) == 2)
                drawHoveringText(mouseX, mouseY);

            // TODO remove this?
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }

    private void drawHoveringText(int x, int y)
    {
        res = new ScaledResolution(Minecraft.getMinecraft());

        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();

        int newX = x + 12, newY = y - 12;

        if (newX + stringWidth > res.getScaledWidth())
            newX -= 28 + stringWidth;

        if (newY + 8 + 6 > res.getScaledHeight())
            newY = res.getScaledHeight() - 14;

        this.zLevel = 300.0F;
        Minecraft.getMinecraft().getRenderItem().zLevel = 300.0F;
        int l = -267386864;
        this.drawGradientRect(newX - 3, newY - 4, newX + stringWidth + 3, newY - 3, l, l);
        this.drawGradientRect(newX - 3, newY + 8 + 3, newX + stringWidth + 3, newY + 8 + 4, l, l);
        this.drawGradientRect(newX - 3, newY - 3, newX + stringWidth + 3, newY + 8 + 3, l, l);
        this.drawGradientRect(newX - 4, newY - 3, newX - 3, newY + 8 + 3, l, l);
        this.drawGradientRect(newX + stringWidth + 3, newY - 3, newX + stringWidth + 4, newY + 8 + 3, l, l);
        int i1 = 1347420415;
        int j1 = (i1 & 16711422) >> 1 | i1 & -16777216;
        this.drawGradientRect(newX - 3, newY - 3 + 1, newX - 3 + 1, newY + 8 + 3 - 1, i1, j1);
        this.drawGradientRect(newX + stringWidth + 2, newY - 3 + 1, newX + stringWidth + 3, newY + 8 + 3 - 1, i1, j1);
        this.drawGradientRect(newX - 3, newY - 3, newX + stringWidth + 3, newY - 3 + 1, i1, i1);
        this.drawGradientRect(newX - 3, newY + 8 + 2, newX + stringWidth + 3, newY + 8 + 3, j1, j1);

        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.text, (float) newX, (float) newY, 0xFFAA00);

        this.zLevel = 0.0F;
        Minecraft.getMinecraft().getRenderItem().zLevel = 0.0F;
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
    }
}
