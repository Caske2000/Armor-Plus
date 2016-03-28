package net.caske2000.armorplus.client.gui.manual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;

import java.io.IOException;

/**
 * Created by Caske2000 on 27/03/2016.
 */
public class ManualItemPage extends GuiPage
{

    public ManualItemPage(GuiManual gui)
    {
        super(gui);
    }

    @Override
    public void init()
    {
        buttons.clear();
        buttons.add(new GuiNextPageButton(0, gui.xPos + 24, gui.yPos + gui.ySize - 39, false));
    }

    @Override
    public void draw()
    {
        for (int i = 0; i < gui.itemInfo.getDescription().length; i++)
            gui.drawString(Minecraft.getMinecraft().fontRendererObj, gui.itemInfo.getDescription()[i], gui.xPos + 30, gui.yPos + 80 + i * 12, 0xFFFFFF);

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        RenderHelper.enableGUIStandardItemLighting();

        GlStateManager.scale(2, 2, 2);
        gui.drawString(Minecraft.getMinecraft().fontRendererObj, "§5§n" + gui.itemInfo.getName(), gui.xPos / 2 + 10, gui.yPos / 2 + 20, 0xFFFFFF);
        GlStateManager.scale(1.5, 1.5, 1.5);
        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(gui.itemInfo.getItemStack(), (gui.xPos + gui.xSize) / 3 - 25, gui.yPos / 3 + 10);

        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }

    @Override
    public void actionPerformed(GuiButton button)
    {
        if (button.id == 0)
            gui.changeLevel((short) 1);
    }
}
