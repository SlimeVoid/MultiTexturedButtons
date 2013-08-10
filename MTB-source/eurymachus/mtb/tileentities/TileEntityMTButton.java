package eurymachus.mtb.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;
import slimevoidlib.network.PacketUpdate;
import slimevoidlib.tileentity.TileEntityMT;
import eurymachus.mtb.network.packets.PacketUpdateMTButton;

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

	@Override
	public Packet getDescriptionPacket() {
		return getUpdatePacket();
	}

	@Override
	public Packet getUpdatePacket() {
		return new PacketUpdateMTButton(this).getPacket();
	}

	@Override
	public void handleUpdatePacket(World world, PacketUpdate packet) {
		this.setSensible(((PacketUpdateMTButton) packet).getSensible());
		super.handleUpdatePacket(world, packet);
	}
}