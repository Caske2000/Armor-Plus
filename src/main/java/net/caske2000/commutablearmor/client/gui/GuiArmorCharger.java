package net.caske2000.commutablearmor.client.gui;

import net.caske2000.commutablearmor.inventory.ContainerArmorCharger;
import net.caske2000.commutablearmor.tileentities.TileEntityArmorCharger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Caske2000 on 26/03/2016.
 */
public class GuiArmorCharger extends GuiContainer {
    private final IInventory playerInv;
    private final TileEntityArmorCharger te;
    private ScaledResolution res;
    private String text;
    private int stringWidth;

    public GuiArmorCharger(IInventory playerInv, TileEntityArmorCharger te) {
        super(new ContainerArmorCharger(playerInv, te));

        this.playerInv = playerInv;
        this.te = te;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("commutablearmor:textures/gui/container/armorCharger_block.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        int l = (int) ((te.getField(0) * 51) / ((float) te.getMaxEnergyStored(EnumFacing.DOWN)));
        this.drawTexturedModalRect(this.guiLeft + 12, this.guiTop + 17 + (51 - l), 176, 0, 13, l);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        GlStateManager.pushMatrix();
        if (mouseX >= 12 + guiLeft && mouseX <= 25 + guiLeft && mouseY >= 17 + guiTop && mouseY <= 68 + guiTop) {
            text = String.valueOf(te.getField(0) + " / " + te.getMaxEnergyStored(EnumFacing.DOWN) + " RF");
            stringWidth = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);

            res = new ScaledResolution(Minecraft.getMinecraft());

            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();

            int newX = mouseX, newY = mouseY;

            if (newX + stringWidth > res.getScaledWidth())
                newX -= 28 + stringWidth;

            if (newY + 14 > res.getScaledHeight())
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

            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.text, (float) newX, (float) newY, -1);

            this.zLevel = 0.0F;
            Minecraft.getMinecraft().getRenderItem().zLevel = 0.0F;
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
        }
        GlStateManager.popMatrix();

        String s = this.te.getDisplayName().getFormattedText();
        this.fontRendererObj.drawString(s, 88 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);            //#404040
        this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 72, 4210752);      //#404040
    }
}
