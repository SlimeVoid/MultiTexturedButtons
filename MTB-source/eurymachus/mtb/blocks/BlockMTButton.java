package eurymachus.mtb.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockButton;
import net.minecraft.block.StepSound;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import slimevoid.lib.IContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eurymachus.mtb.core.MTBBlocks;
import eurymachus.mtb.core.MTBInit;
import eurymachus.mtb.core.MTBItemButtons;
import eurymachus.mtb.core.MTBItemSensibleButtons;

public class BlockMTButton extends BlockButton implements IContainer {
	Class<? extends TileEntity> mtButtonEntityClass;

	/**
	 * The Multi-Textured Button Class
	 * 
	 * @param blockId id of the block
	 * @param buttonClass tileentity associated with the block
	 * @param hardness default hardness
	 * @param sound stepping sound to use
	 * @param disableStats should disable stats?
	 * @param requiresSelfNotify requires the block to notify self?
	 * @param sensible is sensitive to arrows
	 * @param blockName the given name of the block 
	 */
	public BlockMTButton(int blockId, Class<? extends TileEntity> buttonClass, float hardness, StepSound sound, boolean disableStats, boolean requiresSelfNotify, boolean sensible, String blockName) {
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

	@Override
	public int getBlockTexture(IBlockAccess blockaccess, int x, int y, int z, int side) {
		int texture = -1;
		if (blockaccess.getBlockId(x, y, z) == MTBBlocks.mtButton.id) {
			texture = MTBItemButtons.getTexture(MTBInit.getDamageValue(
					blockaccess,
					x,
					y,
					z));
		} else {
			texture = MTBItemSensibleButtons.getTexture(MTBInit.getDamageValue(
					blockaccess,
					x,
					y,
					z));
		}
		if (texture >= 0) {
			return texture;
		}
		return 1;
	}

	@Override
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
	public TileEntity createNewTileEntity(World world) {
		try {
			return this.mtButtonEntityClass.newInstance();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return createNewTileEntity(world);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int a, int b) {
		ItemStack itemstack = new ItemStack(
				this,
				1,
				MTBInit.getDamageValue(world, x, y, z));
		EntityItem entityitem = new EntityItem(world, x, y, z, itemstack);
		world.spawnEntityInWorld(entityitem);
		super.breakBlock(world, x, y, z, a, b);
		world.removeBlockTileEntity(x, y, z);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
