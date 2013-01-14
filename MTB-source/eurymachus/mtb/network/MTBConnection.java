package eurymachus.mtb.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import slimevoid.lib.INetworkConnection;
import cpw.mods.fml.common.network.Player;
import eurymachus.mtb.core.MTBInit;

public class MTBConnection implements INetworkConnection {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		MTBInit.MTB.getProxy().onPacketData(manager, packet, player);
	}

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionClosed(INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
		// TODO Auto-generated method stub

	}
}
