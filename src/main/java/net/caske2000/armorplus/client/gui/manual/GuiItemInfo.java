package net.caske2000.armorplus.client.gui.manual;

import net.caske2000.armorplus.lib.Reference;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Caske2000 on 28/03/2016.
 */
public final class GuiItemInfo
{
    private final String name;
    private final String[] description;
    private final ItemStack itemStack;

    public GuiItemInfo(String[] description, String displayItem, String name)
    {
        this.description = description;
        this.itemStack = new ItemStack(GameRegistry.findItem(Reference.MODID, displayItem));
        this.name = name;
    }

    public String[] getDescription()
    {
        return description;
    }

    public ItemStack getItemStack()
    {
        return itemStack;
    }

    public String getName()
    {
        return name;
    }
}
