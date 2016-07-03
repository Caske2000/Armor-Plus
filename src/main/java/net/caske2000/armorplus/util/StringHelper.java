package net.caske2000.armorplus.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.translation.LanguageMap;
import org.lwjgl.input.Keyboard;

import java.awt.*;

/**
 * Created by Caske2000 on 8-2-2016.
 */
public final class StringHelper
{
    public static String shiftForInfo()
    {
        return ChatFormatting.AQUA + "" + ChatFormatting.ITALIC + localize("info.caske.hold") + ChatFormatting.RESET + "" + ChatFormatting.YELLOW + localize("info.caske.shift") + ChatFormatting.RESET + "" + ChatFormatting.AQUA + "" + ChatFormatting.ITALIC + localize("info.caske.moreInfo");
    }

    public static boolean isShiftKeyDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static String localize(String string)
    {
        return I18n.format(string);
    }
}
