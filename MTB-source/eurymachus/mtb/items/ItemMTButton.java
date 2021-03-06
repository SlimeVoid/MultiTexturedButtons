package eurymachus.mtb.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import eurymachus.mtb.core.MTBBlocks;
import eurymachus.mtb.core.MTBItemButtons;
import eurymachus.mtb.core.MTBItemSensibleButtons;
import eurymachus.mtb.tileentities.TileEntityMTButton;

public class ItemMTButton extends ItemBlock {
	private final String[] buttonNames;
	private final Block blockRef;

	public ItemMTButton(int i) {
		super(i);
		if (i == (MTBBlocks.mtButton.id - 256)) {
			this.blockRef =  MTBBlocks.mtButton.me;
			this.buttonNames = MTBItemButtons.getButtonNames();
		} else {
			this.blockRef =  MTBBlocks.mtSensibleButton.me;
			this.buttonNames = MTBItemSensibleButtons.getButtonNames();
		}
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setNoRepair();
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return (new StringBuilder())
				.append(super.getItemName())
					.append(".")
					.append(buttonNames[itemstack.getItemDamage()])
					.toString();
	}

	public int filterData(int i) {
		return i;
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@Override
	public int getIconFromDamage(int par1) {
		return this.blockRef.getBlockTextureFromSideAndMetadata(0, par1);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float a, float b, float c) {
		Block button = this.blockRef;

		switch (l) {
		case 0:
			j--;
			break;
		case 1:
			j++;
			break;
		case 2:
			k--;
			break;
		case 3:
			k++;
			break;
		case 4:
			i--;
			break;
		case 5:
			i++;
			break;
		}

		if (itemstack.stackSize == 0 || !entityplayer.canPlayerEdit(
				i,
				j,
				k,
				l,
				itemstack) || (j == 255 && button.blockMaterial.isSolid()))
			return false;

		if (world.canPlaceEntityOnSide(
				button.blockID,
				i,
				j,
				k,
				false,
				l,
				entityplayer)) {
			int meta = this.getMetadata(itemstack.getItemDamage());
			int data = button.onBlockPlaced(world, i, j, k, l, a, b, c, meta); /*onBlockPlaced*/
			if (this.placeBlockAt(
					itemstack,
					entityplayer,
					world,
					i,
					j,
					k,
					l,
					a,
					b,
					c,
					data)) {
				if (world.getBlockId(i, j, k) == button.blockID) {
					button.onBlockPlacedBy(world, i, j, k, entityplayer);
					TileEntity tileentity = world.getBlockTileEntity(i, j, k);

					if (tileentity != null && tileentity instanceof TileEntityMTButton) {
						TileEntityMTButton tileentitymtbutton = (TileEntityMTButton) tileentity;
						tileentitymtbutton.setTextureValue(itemstack
								.getItemDamage());
						tileentitymtbutton.onInventoryChanged();
					}
				}

				world.playSoundEffect(
						(i + 0.5F),
						(j + 0.5F),
						(k + 0.5F),
						button.stepSound.getStepSound(),
						(button.stepSound.getVolume() + 1.0F) / 2.0F,
						button.stepSound.getPitch() * 0.8F);
				--itemstack.stackSize;

				return true;
			}
		}
		return false;
	}
}