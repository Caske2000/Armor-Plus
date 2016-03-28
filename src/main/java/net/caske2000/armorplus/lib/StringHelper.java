package net.caske2000.armorplus.lib;

import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

/**
 * Created by Caske2000 on 8-2-2016.
 */
public final class StringHelper
{
    public static String shiftForInfo()
    {
        return "§b§o" + localize("info.caske.hold") + " §r§e" + localize("info.caske.shift") + " §r" + "§b§o" + localize("info.caske.moreInfo");
    }

    public static boolean isShiftKeyDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static String localize(String var0)
    {
        return StatCollector.translateToLocal(var0);
    }
}
