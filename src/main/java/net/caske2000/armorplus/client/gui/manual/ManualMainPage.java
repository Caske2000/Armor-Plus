package net.caske2000.armorplus.client.gui.manual;

import net.caske2000.armorplus.blocks.ModBlocks;
import net.caske2000.armorplus.items.ModItems;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;

import java.io.IOException;

/**
 * Created by Caske2000 on 27/03/2016.
 */
public class ManualMainPage extends GuiPage
{
    private GuiTexturedButton blocksBtn, itemsBtn;

    public ManualMainPage(GuiManual gui)
    {
        super(gui);
    }

    @Override
    public void init()
    {
        buttons.clear();
        buttons.add(blocksBtn = new GuiTexturedButton(0, gui.xPos + (gui.xSize / 2 - 45) / 2, gui.yPos + (gui.ySize / 2 - 45) / 2, new ItemStack(ModBlocks.armorTable), "Blocks", 3));
        buttons.add(itemsBtn = new GuiTexturedButton(1, gui.xPos + (gui.xSize / 2 - 45) / 2 + gui.xSize / 2, gui.yPos + (gui.ySize / 2 - 45) / 2, new ItemStack(ModItems.customChestplateIron), "Items", 3));
    }

    @Override
    public void draw()
    {

    }

    @Override
    public void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 0)
        {
            gui.subjectPage.setIsBlocks(true);
            gui.changeLevel((short) 1);
        } else if (button.id == 1)
        {
            gui.subjectPage.setIsBlocks(false);
            gui.changeLevel((short) 1);
        }
    }
}
