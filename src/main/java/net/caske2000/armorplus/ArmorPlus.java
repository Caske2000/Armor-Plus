package net.caske2000.armorplus;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.caske2000.armorplus.client.ArmorAchievements;
import net.caske2000.armorplus.client.gui.manual.GuiItemInfo;
import net.caske2000.armorplus.lib.Reference;
import net.caske2000.armorplus.proxy.CommonProxy;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION)
public class ArmorPlus
{
    @Mod.Instance
    public static ArmorPlus instance;

    @SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.SERVERPROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);

        ArmorAchievements.init();

        JsonParser parser = new JsonParser();
        BufferedReader br;

        try
        {
            br = new BufferedReader(new InputStreamReader(getClass().getClassLoader()
                    .getResourceAsStream("assets/armorplus/lang/manual_" + FMLCommonHandler.instance().getCurrentLanguage() + ".json"), "UTF-8"));
            if (!br.ready())
                br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("assets/armorplus/lang/manual_en_US.json"), "UTF-8"));

            Object obj = parser.parse(br);
            JsonObject json = (JsonObject) obj;

            JsonArray array = json.getAsJsonArray("Blocks");
            String[] description;

            for (int i = 0; i < array.size(); i++)
            {
                JsonObject object = (JsonObject) array.get(i);
                JsonArray descArray = object.getAsJsonArray("description");
                description = new String[descArray.size()];
                for (int j = 0; j < descArray.size(); j++)
                    description[j] = descArray.get(j).getAsString();
                Reference.blocks.add(new GuiItemInfo(description, object.get("displayItem").getAsString(), object.get("name").getAsString()));
            }

            array = json.getAsJsonArray("Items");

            for (int i = 0; i < array.size(); i++)
            {
                JsonObject object = (JsonObject) array.get(i);
                JsonArray descArray = object.getAsJsonArray("description");
                description = new String[descArray.size()];
                for (int j = 0; j < descArray.size(); j++)
                    description[j] = descArray.get(j).getAsString();
                Reference.items.add(new GuiItemInfo(description, object.get("displayItem").getAsString(), object.get("name").getAsString()));
            }
            br.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
