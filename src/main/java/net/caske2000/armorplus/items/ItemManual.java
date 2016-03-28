package net.caske2000.armorplus.items;

import net.caske2000.armorplus.ArmorPlus;
import net.caske2000.armorplus.client.gui.GuiHandler;
import net.caske2000.armorplus.lib.Reference;
import net.caske2000.armorplus.lib.StringHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Caske2000 on 27/03/2016.
 */
class ItemManual extends Item
{
    public ItemManual()
    {
        super();
        setUnlocalizedName(Reference.Names.MANUAL);
        setCreativeTab(CreativeTab.ARMOR_TAB);
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> list, boolean advanced)
    {
        list.add(StringHelper.localize("info.caske.manual"));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (world.isRemote)
            player.openGui(ArmorPlus.instance, GuiHandler.MANUAL_GUI, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
        return super.onItemRightClick(itemStack, world, player);
    }
}
