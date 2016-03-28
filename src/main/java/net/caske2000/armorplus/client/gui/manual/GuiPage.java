package net.caske2000.armorplus.client.gui.manual;

import net.minecraft.client.gui.GuiButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caske2000 on 27/03/2016.
 */
abstract class GuiPage
{
    final GuiManual gui;
    final List<GuiButton> buttons = new ArrayList<GuiButton>();

    GuiPage(GuiManual gui)
    {
        this.gui = gui;
    }

    public abstract void init();
    public abstract void draw();
    public abstract void actionPerformed(GuiButton button);
}
