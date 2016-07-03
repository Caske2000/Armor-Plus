package net.caske2000.armorplus.client.gui.manual;

import net.caske2000.armorplus.blocks.ModBlocks;
import net.caske2000.armorplus.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Caske2000 on 27/03/2016.
 */
public class ManualMainPage extends GuiPage
{
    private String webpage = "https://github.com/Caske2000/Commutable-Armor";

    public ManualMainPage(GuiManual gui)
    {
        super(gui);
    }

    @Override
    public void init()
    {
        buttons.clear();
        buttons.add(new GuiTexturedButton(0, gui.xPos + (gui.xSize / 2 - 45) / 2, gui.yPos + (gui.ySize / 2 - 45) / 2, new ItemStack(ModBlocks.armorTable), "Blocks", 3));
        buttons.add(new GuiTexturedButton(1, gui.xPos + (gui.xSize / 2 - 45) / 2 + gui.xSize / 2, gui.yPos + (gui.ySize / 2 - 45) / 2, new ItemStack(ModItems.customChestplateIron), "Items", 3));
        buttons.add(new GuiButton(2, gui.xPos + gui.xSize / 2 - 100, gui.yPos + gui.ySize - 39, "Click here to visit the WIKI"));
    }

    @Override
    public void draw()
    {

    }

    @Override
    public void actionPerformed(GuiButton button)
    {
        if (button.id == 0)
        {
            gui.subjectPage.setIsBlocks(true);
            gui.changeLevel((short) 1);
        } else if (button.id == 1)
        {
            gui.subjectPage.setIsBlocks(false);
            gui.changeLevel((short) 1);
        } else if (button.id == 2)
        {
            try
            {
                URI uri = new URI(webpage);
                String s = uri.getScheme();

                if (s == null)
                    throw new URISyntaxException(webpage, "Missing protocol");

                if (!s.toLowerCase().contains("http"))
                    throw new URISyntaxException(webpage, "Unsupported protocol: " + s.toLowerCase());

                Class<?> oclass = Class.forName("java.awt.Desktop");
                Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null);
                oclass.getMethod("browse", new Class[]{URI.class}).invoke(object, uri);

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
