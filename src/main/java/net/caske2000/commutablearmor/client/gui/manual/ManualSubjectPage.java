package net.caske2000.commutablearmor.client.gui.manual;

import net.caske2000.commutablearmor.lib.Reference;
import net.minecraft.client.gui.GuiButton;

/**
 * Created by Caske2000 on 27/03/2016.
 */
public class ManualSubjectPage extends GuiPage {
    private boolean isBlocks;

    public ManualSubjectPage(GuiManual gui, boolean isBlocks) {
        super(gui);
        this.isBlocks = isBlocks;
    }

    @Override
    public void init() {
        buttons.clear();
        buttons.add(new GuiNextPageButton(0, gui.xPos + 24, gui.yPos + gui.ySize - 39, false));
        int i = 1;
        if (isBlocks) {
            for (GuiItemInfo info : Reference.blocks) {
                buttons.add(new GuiClickableLabel(i, gui.xPos + 15, gui.yPos + 15 + 12 * i, info.getName()));
                i++;
            }
        } else {
            for (GuiItemInfo info : Reference.items) {
                buttons.add(new GuiClickableLabel(i, gui.xPos + 15, gui.yPos + 15 + 12 * i, info.getName()));
                i++;
            }
        }

    }

    @Override
    public void draw() {

    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button.id == 0)
            gui.changeLevel((short) 0);
        else if (button.id > 0) {
            if (isBlocks)
                gui.itemInfo = Reference.blocks.get(button.id - 1);
            else
                gui.itemInfo = Reference.items.get(button.id - 1);
            gui.changeLevel((short) 2);
        }
    }

    public void setIsBlocks(boolean isBlocks) {
        this.isBlocks = isBlocks;
    }
}
