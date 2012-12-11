package mtb.core;

import mtb.network.MTBConnection;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import eurysmods.api.ICommonProxy;

@Mod(
		modid = "MultiTexturedButtons",
		name = "Multi-Textured Buttons",
		dependencies = "after:EurysCore",
		version = "2.0.0.0")
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		channels = { "MTB" },
		packetHandler = MTBConnection.class,
		connectionHandler = MTBConnection.class)
public class MultiTexturedButtons {
	@SidedProxy(
			clientSide = "mtb.proxy.ClientProxy",
			serverSide = "mtb.proxy.CommonProxy")
	public static ICommonProxy proxy;

	@Init
	public void MultiTexturedButtonsInit(FMLInitializationEvent event) {

	}

	@PreInit
	public void MultiTexturedButtonsPreInit(FMLPreInitializationEvent event) {

	}

	@PostInit
	public void MultiTexturedButtonsPostInit(FMLPostInitializationEvent event) {
		MTBInit.initialize(proxy);
	}
}