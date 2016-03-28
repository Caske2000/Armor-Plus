package net.caske2000.armorplus.client.gui.manual;

import net.minecraft.client.gui.GuiButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caske2000 on 27/03/2016.
 */
public abstract class GuiPage
{
    protected GuiManual gui;
    protected List<GuiButton> buttons = new ArrayList<GuiButton>();

    public GuiPage(GuiManual gui)
    {
        this.gui = gui;
    }

    public abstract void init();
    public abstract void draw();
    public abstract void actionPerformed(GuiButton button) throws IOException;
}
