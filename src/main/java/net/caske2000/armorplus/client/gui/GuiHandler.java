package net.caske2000.armorplus.client.gui;

import net.caske2000.armorplus.client.gui.manual.GuiManual;
import net.caske2000.armorplus.inventory.ContainerArmorCharger;
import net.caske2000.armorplus.inventory.ContainerArmorTable;
import net.caske2000.armorplus.tileentities.TileEntityArmorCharger;
import net.caske2000.armorplus.tileentities.TileEntityArmorTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Caske2000 on 5-3-2016.
 */
public class GuiHandler implements IGuiHandler
{
    public static final int ARMOR_TABLE_GUI = 0, ARMOR_CHARGER_GUI = 1, MANUAL_GUI = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == ARMOR_TABLE_GUI)
            return new ContainerArmorTable(player.inventory, (TileEntityArmorTable) world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == ARMOR_CHARGER_GUI)
            return new ContainerArmorCharger(player.inventory, (TileEntityArmorCharger) world.getTileEntity(new BlockPos(x, y, z)));

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == ARMOR_TABLE_GUI)
            return new GuiArmorTable(player.inventory, (TileEntityArmorTable) world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == ARMOR_CHARGER_GUI)
            return new GuiArmorCharger(player.inventory, (TileEntityArmorCharger) world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == MANUAL_GUI)
            return new GuiManual();

        return null;
    }
}
