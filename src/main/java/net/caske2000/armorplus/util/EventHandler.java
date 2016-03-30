package net.caske2000.armorplus.util;

import net.caske2000.armorplus.ArmorPlus;
import net.caske2000.armorplus.items.ItemCustomArmor;
import net.minecraft.event.ClickEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by Caske2000 on 28/03/2016.
 */
public class EventHandler
{
    private boolean couldFly = false;

    @SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(TickEvent.PlayerTickEvent event)
    {
        if (!ArmorPlus.isWarnedVersionOutOfDate && event.player.worldObj.isRemote && !ArmorPlus.versionChecker.isLatestVersion())
        {
            ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL,
                    "https://github.com/Caske2000/Armor-Plus");
            ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
            ChatComponentText versionWarningChatComponent =
                    new ChatComponentText(EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.RED + "Your Commutable Armor Mod is not the latest version!  Click here to update.");
            versionWarningChatComponent.setChatStyle(clickableChatStyle);
            event.player.addChatMessage(versionWarningChatComponent);
            ArmorPlus.isWarnedVersionOutOfDate = true;
        }

        if (event.player.getCurrentArmor(2) != null)
        {
            if (event.player.getCurrentArmor(2).getItem() instanceof ItemCustomArmor)
            {
                if (event.player.getCurrentArmor(2).getTagCompound().getBoolean("FLIGHT"))
                {
                    if (event.player.getCurrentArmor(2).getTagCompound().getInteger("ENERGY") > 3000)
                    {
                        if (event.player.capabilities.allowFlying == false)
                        {
                            event.player.capabilities.allowFlying = true;
                            couldFly = true;
                            event.player.sendPlayerAbilities();
                            return;
                        }
                    }
                }
            }
        }

        if (couldFly)
        {
            event.player.capabilities.allowFlying = false;
            event.player.capabilities.isFlying = false;
            couldFly = false;
            event.player.sendPlayerAbilities();
        }
    }
}
