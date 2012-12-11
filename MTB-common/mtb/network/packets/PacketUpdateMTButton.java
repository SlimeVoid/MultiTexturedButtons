package mtb.network.packets;

import mtb.core.MTBInit;
import mtb.tileentities.TileEntityMTButton;
import eurysmods.network.packets.core.PacketPayload;
import eurysmods.network.packets.core.PacketTileEntityMT;

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
