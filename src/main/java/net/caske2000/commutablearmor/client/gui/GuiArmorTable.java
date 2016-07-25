package net.caske2000.commutablearmor.client.gui;

import net.caske2000.commutablearmor.inventory.ContainerArmorTable;
import net.caske2000.commutablearmor.tileentities.TileEntityArmorTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Caske2000 on 5-3-2016.
 */
public class GuiArmorTable extends GuiContainer {
    private final IInventory playerInv;
    private final TileEntityArmorTable te;

    public GuiArmorTable(IInventory playerInv, TileEntityArmorTable te) {
        super(new ContainerArmorTable(playerInv, te));

        this.playerInv = playerInv;
        this.te = te;

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("commutablearmor:textures/gui/container/armorTable_block.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;

        int l = this.getWorkProgressScaled();
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
    }

    private int getWorkProgressScaled() {
        int i = this.te.getField(0);
        int j = this.te.getField(1);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.te.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, 88 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);            //#404040
        this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 72, 4210752);      //#404040
    }
}
