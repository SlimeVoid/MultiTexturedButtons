package mtb.proxy;

import mtb.core.MTBInit;
import mtb.core.MTBItemButtons;
import mtb.network.ClientPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import eurysmods.api.IPacketHandling;

public class ClientProxy extends CommonProxy {

	@Override
	public String getMinecraftDir() {
		return Minecraft.getMinecraftDir().toString();
	}

	@Override
	public void registerRenderInformation() {
	}

	@Override
	public void displayTileEntityGui(EntityPlayer entityplayer, TileEntity tileentity) {
		if (!entityplayer.worldObj.isRemote)
			super.displayTileEntityGui(entityplayer, tileentity);
	}

	@SideOnly(Side.CLIENT)
	private static Minecraft mc = ModLoader.getMinecraftInstance();

	@Override
	public int getMouseOver() {
		if (mc.objectMouseOver != null) {
			int xPosition = mc.objectMouseOver.blockX;
			int yPosition = mc.objectMouseOver.blockY;
			int zPosition = mc.objectMouseOver.blockZ;
			return MTBInit.getDamageValue(
					mc.theWorld,
					xPosition,
					yPosition,
					zPosition);
		}
		return 0;
	}

	@Override
	public int getBelowPlayer(EntityPlayer player) {
		int playerX = (int) player.posX;
		int playerY = (int) player.posY;
		int playerZ = (int) player.posZ;
		return MTBInit.getDamageValue(
				mc.theWorld,
				playerX,
				playerY - 1,
				playerZ);
	}

	@Override
	public int getAtPlayer(EntityPlayer player) {
		int playerX = (int) player.posX;
		int playerY = (int) player.posY;
		int playerZ = (int) player.posZ;
		return MTBInit.getDamageValue(mc.theWorld, playerX, playerY, playerZ);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		if (side != 0) {
			int texture = MTBItemButtons.getTexture(meta);
			return texture;
		}
		return getBlockTextureFromMetadata(meta);
	}

	@Override
	public int getBlockTextureFromMetadata(int meta) {
		if (meta > 1) {
			return MTBItemButtons.getTexture(meta);
		}
		int texture = -1;
		EntityPlayer player = mc.thePlayer;
		if (player.onGround) {
			texture = getMouseOver();
		}
		if (texture == -1 && player.isAirBorne) {
			texture = getMouseOver();
		}
		if (texture == -1 && player.isAirBorne) {
			texture = getBelowPlayer(player);
		}
		if (texture == -1 && player.isAirBorne) {
			texture = getAtPlayer(player);
		}
		texture = MTBItemButtons.getTexture(texture);
		if (texture == -1)
			texture = 22;
		return texture;
	}

	@Override
	public IPacketHandling getPacketHandler() {
		return new ClientPacketHandler();
	}
}
