package eurymachus.mtb.network.packets;

import slimevoid.lib.network.PacketPayload;
import slimevoid.lib.network.PacketTileEntityMT;
import eurymachus.mtb.core.MTBInit;
import eurymachus.mtb.tileentities.TileEntityMTButton;

public class PacketUpdateMTButton extends PacketTileEntityMT {

	public PacketUpdateMTButton() {
		super(MTBInit.MTB.getModChannel());
	}

	public PacketUpdateMTButton(TileEntityMTButton tileentitymtbutton) {
		super(MTBInit.MTB.getModChannel(), tileentitymtbutton);
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
