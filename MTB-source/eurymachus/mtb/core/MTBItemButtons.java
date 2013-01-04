package eurymachus.mtb.core;

import net.minecraft.item.ItemStack;

public enum MTBItemButtons {
	iron,
	gold,
	diamond,
	polishedStone,
	cobbleStone,
	smoothStone;

	public ItemStack me;
	public int stackID;
	public String name;
	private int textureIndex;
	private float hardness;

	public static ItemStack getStack(int itemDamage) {
		for (MTBItemButtons itemstack : MTBItemButtons.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.me;
			}
		}
		return null;
	}

	public static int getTexture(int itemDamage) {
		for (MTBItemButtons itemstack : MTBItemButtons.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.textureIndex;
			}
		}
		return -1;
	}

	public static float getHardness(int itemDamage) {
		for (MTBItemButtons itemstack : MTBItemButtons.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.hardness;
			}
		}
		return 0.5F;
	}

	public void setTextureIndex(int textureIndex) {
		this.textureIndex = textureIndex;
	}

	public void setBlockHardness(float hardness) {
		this.hardness = hardness;
	}

	public static String[] getButtonNames() {
		String[] names = new String[MTBItemButtons.values().length];
		int i = 0;
		for (MTBItemButtons itemstack : MTBItemButtons.values()) {
			if (itemstack != null && itemstack.name != null && !itemstack.name
					.isEmpty()) {
				names[i] = itemstack.name;
			} else {
				names[i] = "";
			}
			i++;
		}
		return names;
	}
}
