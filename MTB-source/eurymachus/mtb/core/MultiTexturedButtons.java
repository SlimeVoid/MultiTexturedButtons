package eurymachus.mtb.core;

import slimevoidlib.ICommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import eurymachus.mtb.core.lib.CoreLib;
import eurymachus.mtb.network.MTBConnection;

@Mod(
		modid = CoreLib.MOD_ID,
		name = CoreLib.MOD_NAME,
		dependencies = CoreLib.MOD_DEPENDENCIES,
		version = CoreLib.MOD_VERSION)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		channels = { CoreLib.MOD_CHANNEL },
		packetHandler = MTBConnection.class,
		connectionHandler = MTBConnection.class)
public class MultiTexturedButtons {
	@SidedProxy(
			clientSide = CoreLib.CLIENT_PROXY,
			serverSide = CoreLib.COMMON_PROXY)
	public static ICommonProxy proxy;

	@EventHandler
	public void MultiTexturedButtonsPreInit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public void MultiTexturedButtonsInit(FMLInitializationEvent event) {
	}

	@EventHandler
	public void MultiTexturedButtonsPostInit(FMLPostInitializationEvent event) {
		MTBInit.initialize();
	}
}