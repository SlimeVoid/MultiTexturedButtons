package mtb.blocks;

import java.util.List;
import java.util.Random;

import mtb.core.MTBBlocks;
import mtb.core.MTBInit;
import mtb.core.MTBItemButtons;
import mtb.core.MTBItemSensibleButtons;
import net.minecraft.src.Block;
import net.minecraft.src.BlockButton;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EnumGameType;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StepSound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import eurysmods.api.IContainer;

public class BlockMTButton extends BlockButton implements IContainer {
	Class mtButtonEntityClass;

	public BlockMTButton(int blockId, Class buttonClass, float hardness, StepSound sound, boolean disableStats, boolean requiresSelfNotify, boolean sensible, String blockName) {
		super(blockId, 0, sensible);
		this.setBlockName(blockName);
		this.isBlockContainer = true;
		mtButtonEntityClass = buttonClass;
		setHardness(hardness);
		setStepSound(sound);
		if (disableStats) {
			disableStats();
		}
		if (requiresSelfNotify) {
			setRequiresSelfNotify();
		}
	}

	public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		int texture = -1;
		if (par1IBlockAccess.getBlockId(par2, par3, par4) == MTBBlocks.mtButton.id) {
			texture = MTBItemButtons.getTexture(MTBInit.getDamageValue(
					par1IBlockAccess,
					par2,
					par3,
					par4));
		} else {
			texture = MTBItemSensibleButtons.getTexture(MTBInit.getDamageValue(
					par1IBlockAccess,
					par2,
					par3,
					par4));
		}
		if (texture >= 0) {
			return texture;
		}
		return 1;
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		world.setBlockTileEntity(
				x,
				y,
				z,
				this.createTileEntity(world, world.getBlockMetadata(x, y, z)));
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		if (this.blockID == MTBBlocks.mtButton.id) {
			return MTBItemButtons.getTexture(meta);
		} else {
			return MTBItemSensibleButtons.getTexture(meta);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World par1World) {
		try {
			return (TileEntity) this.mtButtonEntityClass.newInstance();
		} catch (Exception var3) {
			throw new RuntimeException(var3);
		}
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return createNewTileEntity(world);
	}

	/**
	 * Called whenever the block is removed.
	 */
	@Override
	public void breakBlock(World world, int i, int j, int k, int a, int b) {
		if (world.getWorldInfo().getGameType() != EnumGameType.CREATIVE) {
			Block block;
			if (world.getBlockId(i, j, k) == MTBBlocks.mtButton.id) {
				block = MTBBlocks.mtButton.me;
			} else {
				block = MTBBlocks.mtSensibleButton.me;
			}
			ItemStack itemstack = new ItemStack(
					block,
					1,
					MTBInit.getDamageValue(world, i, j, k));
			EntityItem entityitem = new EntityItem(world, i, j, k, new ItemStack(
					itemstack.itemID,
						1,
						itemstack.getItemDamage()));
			world.spawnEntityInWorld(entityitem);
		}
		super.breakBlock(world, i, j, k, a, b);
		world.removeBlockTileEntity(i, j, k);
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		if (world.getBlockId(x, y, z) == MTBBlocks.mtButton.id) {
			return MTBItemButtons.getHardness(MTBInit
					.getDamageValue(world, x, y, z));
		} else {
			return MTBItemSensibleButtons.getHardness(MTBInit
					.getDamageValue(world, x, y, z));
		}
	}

	@SideOnly(Side.CLIENT)
	
	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	@Override
	public void getSubBlocks(int blockId, CreativeTabs creativeTabs, List blockList) {
		if (blockId == MTBBlocks.mtButton.id) {
			for (MTBItemButtons button : MTBItemButtons.values()) {
				if (button.stackID >= 0) {
					blockList.add(new ItemStack(blockId, 1, button.stackID));
				}
			}
		} else if (blockId == MTBBlocks.mtSensibleButton.id) {
			for (MTBItemSensibleButtons button : MTBItemSensibleButtons.values()) {
				if (button.stackID >= 0) {
					blockList.add(new ItemStack(blockId, 1, button.stackID));
				}
			}
		}
	}
}
