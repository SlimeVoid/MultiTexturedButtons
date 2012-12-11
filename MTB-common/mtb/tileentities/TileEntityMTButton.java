package mtb.tileentities;

import eurysmods.network.packets.core.PacketPayload;
import eurysmods.network.packets.core.PacketUpdate;
import eurysmods.tileentities.TileEntityMT;
import mtb.network.packets.PacketUpdateMTButton;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class TileEntityMTButton extends TileEntityMT {	
	protected boolean isSensible;

	public boolean getSensible() {
		return this.isSensible;
	}

	public void setSensible(boolean sensible) {
		this.isSensible = sensible;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setBoolean("sensible", this.getSensible());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		this.setSensible(nbttagcompound.getBoolean("sensible"));
	}

	public Packet getDescriptionPacket() {
		return getUpdatePacket();
	}

	public Packet getUpdatePacket() {
		return new PacketUpdateMTButton(this).getPacket();
	}

	@Override
	public void handleUpdatePacket(World world, PacketUpdate packet) {
		this.setSensible(((PacketUpdateMTButton)packet).getSensible());
		super.handleUpdatePacket(world, packet);
	}
}