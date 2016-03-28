package net.caske2000.armorplus.client.gui.manual;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

/**
 * Created by Caske2000 on 27/03/2016.
 */
public class GuiManual extends GuiScreen
{
    private static final ResourceLocation MANUAL_TEXTURES = new ResourceLocation("armorplus", "textures/gui/manualBackground.png");

    protected int xPos, drawXPos, yPos, drawYPos, xSize = 335, drawXSize = Math.round(xSize / 1.5F), ySize = 246, drawYSize = Math.round(ySize / 1.5F);

    protected ManualSubjectPage subjectPage;
    protected GuiItemInfo itemInfo;

    private GuiPage[] menus = new GuiPage[] {new ManualMainPage(this), subjectPage = new ManualSubjectPage(this, false), new ManualItemPage(this)};
    // 0 = Main Page    1 = Subject Page    2 = Item
    private short menuLevel = 0;

    @Override
    public void initGui()
    {
        super.initGui();
        this.buttonList.clear();

        xPos = (this.width - this.xSize) / 2;
        yPos = (this.height - this.ySize) / 2;

        drawXPos = Math.round(xPos / 1.5F);
        drawYPos = Math.round(yPos / 1.5F);

        menus[menuLevel].init();
        for(GuiButton button : menus[menuLevel].buttons)
            this.buttonList.add(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(MANUAL_TEXTURES);
        GlStateManager.pushMatrix();
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
        this.drawTexturedModalRect(drawXPos, drawYPos, 0, 0, drawXSize, drawYSize);
        GlStateManager.popMatrix();

        menus[menuLevel].draw();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        menus[menuLevel].actionPerformed(button);
    }

    protected void changeLevel(short level)
    {
        if (level < 0 || level > menus.length)
            throw new IllegalArgumentException("You can only change to a manual level x, where x ∈ [0, 2]");
        menuLevel = level;
        this.initGui();
    }
}
