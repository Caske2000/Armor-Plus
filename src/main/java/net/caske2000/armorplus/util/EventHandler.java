package net.caske2000.armorplus.util;

import net.caske2000.armorplus.ArmorPlus;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by Caske2000 on 28/03/2016.
 */
public class EventHandler
{
    @SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(TickEvent.PlayerTickEvent event)
    {
        if (!ArmorPlus.isWarnedVersionOutOfDate && event.player.worldObj.isRemote && !ArmorPlus.versionChecker.isLatestVersion())
        {
            ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL,
                    "https://github.com/Caske2000/Armor-Plus");
            ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
            ChatComponentText versionWarningChatComponent =
                    new ChatComponentText("Your Armor Plus Mod is not the latest version!  Click here to update.");
            versionWarningChatComponent.setChatStyle(clickableChatStyle);
            event.player.addChatMessage(versionWarningChatComponent);
            ArmorPlus.isWarnedVersionOutOfDate = true;
        }
    }
}
