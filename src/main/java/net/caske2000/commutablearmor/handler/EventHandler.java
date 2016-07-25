package net.caske2000.commutablearmor.handler;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.caske2000.commutablearmor.CommutableArmor;
import net.caske2000.commutablearmor.items.ItemCustomArmor;
import net.caske2000.commutablearmor.util.VersionChecker;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by Caske2000 on 28/03/2016.
 */
public class EventHandler {

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onEvent(TickEvent.PlayerTickEvent event) {
        if (!CommutableArmor.isWarnedVersionOutOfDate && event.player.worldObj.isRemote && !VersionChecker.isLatestVersion()) {
            ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL,
                    "https://github.com/Caske2000/Armor-Plus");
            Style clickableChatStyle = new Style().setClickEvent(versionCheckChatClickEvent);
            TextComponentString versionWarningChatComponent =
                    new TextComponentString(ChatFormatting.UNDERLINE + "" + ChatFormatting.RED + "Your Commutable Armor Mod is not the latest version!  Click here to update.");
            versionWarningChatComponent.setStyle(clickableChatStyle);
            event.player.addChatMessage(versionWarningChatComponent);
            CommutableArmor.isWarnedVersionOutOfDate = true;
        }

        if (event.player.inventory.armorItemInSlot(2) != null) {
            if (event.player.inventory.armorItemInSlot(2).getItem() instanceof ItemCustomArmor) {
                if (event.player.inventory.armorItemInSlot(2).getTagCompound().getBoolean("FLIGHT")) {
                    if (event.player.inventory.armorItemInSlot(2).getTagCompound().getInteger("ENERGY") > 3000) {
                        if (event.player.capabilities.allowFlying == false) {
                            event.player.capabilities.allowFlying = true;
                            event.player.sendPlayerAbilities();
                            return;
                        } else
                            return;
                    }
                }
            }
        }

        if (!event.player.isCreative()) {
            event.player.capabilities.allowFlying = false;
            event.player.capabilities.isFlying = false;
            event.player.sendPlayerAbilities();
        }
    }
}
