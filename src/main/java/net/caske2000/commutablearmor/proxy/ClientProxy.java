package net.caske2000.commutablearmor.proxy;

import net.caske2000.commutablearmor.CommutableArmor;
import net.caske2000.commutablearmor.render.*;
import net.caske2000.commutablearmor.tileentities.TileEntityArmorCharger;
import net.caske2000.commutablearmor.tileentities.TileEntityArmorTable;
import net.caske2000.commutablearmor.util.VersionChecker;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Caske2000 on 5/02/2016.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        MinecraftForge.EVENT_BUS.register(HudHandler.instance);

        ItemRenderRegister.registerItemRenderer();
        BlockRenderRegister.registerBlockRenderer();

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArmorTable.class, new ArmorTableRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArmorCharger.class, new ArmorChargerRenderer());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        CommutableArmor.versionChecker = new VersionChecker();
        Thread versionCheckThread = new Thread(CommutableArmor.versionChecker, "Version Check");
        versionCheckThread.start();
        super.postInit(event);
    }
}
