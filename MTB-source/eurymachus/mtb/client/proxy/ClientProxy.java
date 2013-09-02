package eurymachus.mtb.client.proxy;

import net.minecraft.client.Minecraft;
import slimevoidlib.IPacketHandling;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eurymachus.mtb.client.network.ClientPacketHandler;
import eurymachus.mtb.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {

	@Override
	public String getMinecraftDir() {
		return Minecraft.getMinecraft().mcDataDir.getPath();
	}

	@Override
	public void registerRenderInformation() {
	}


	@SideOnly(Side.CLIENT)
	private static Minecraft mc = FMLClientHandler.instance().getClient();

	@Override
	public IPacketHandling getPacketHandler() {
		return new ClientPacketHandler();
	}
}
