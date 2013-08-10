package eurymachus.mtb.network.packets;

import slimevoidlib.network.PacketPayload;
import slimevoidlib.network.PacketTileEntityMT;
import eurymachus.mtb.core.lib.CoreLib;
import eurymachus.mtb.tileentities.TileEntityMTButton;

public class PacketUpdateMTButton extends PacketTileEntityMT {

	public PacketUpdateMTButton() {
		super(CoreLib.MOD_CHANNEL);
	}

	public PacketUpdateMTButton(TileEntityMTButton tileentitymtbutton) {
		super(CoreLib.MOD_CHANNEL, tileentitymtbutton);
		this.payload = new PacketPayload(0, 0, 0, 1);
		this.setSensible(tileentitymtbutton.getSensible());
	}

	public void setSensible(boolean sensible) {
		this.payload.setBoolPayload(0, sensible);
	}

	public boolean getSensible() {
		return this.payload.getBoolPayload(0);
	}
}
