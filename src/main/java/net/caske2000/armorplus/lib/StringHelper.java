package net.caske2000.armorplus.lib;

import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import org.lwjgl.input.Keyboard;

import java.awt.*;

/**
 * Created by Caske2000 on 8-2-2016.
 */
public final class StringHelper
{
    public static String shiftForInfo()
    {
        return EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + localize("info.caske.hold") + EnumChatFormatting.RESET + "" + EnumChatFormatting.YELLOW + localize("info.caske.shift") + EnumChatFormatting.RESET + "" + EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + localize("info.caske.moreInfo");
    }

    public static boolean isShiftKeyDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static String localize(String var0)
    {
        return StatCollector.translateToLocal(var0);
    }
}
