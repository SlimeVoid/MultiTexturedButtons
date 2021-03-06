package eurymachus.mtb.core;

import net.minecraft.item.ItemStack;

public enum MTBItemSensibleButtons {
	oakPlank,
	sprucePlank,
	birchPlank,
	junglePlank;

	public ItemStack me;
	public int stackID;
	public String name;
	private int textureIndex;
	private boolean sensible;
	private float hardness;

	public static ItemStack getStack(int itemDamage) {
		for (MTBItemSensibleButtons itemstack : MTBItemSensibleButtons.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.me;
			}
		}
		return null;
	}

	public static int getTexture(int itemDamage) {
		for (MTBItemSensibleButtons itemstack : MTBItemSensibleButtons.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.textureIndex;
			}
		}
		return -1;
	}

	public static boolean getSensible(int itemDamage) {
		for (MTBItemSensibleButtons itemstack : MTBItemSensibleButtons.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.sensible;
			}
		}
		return false;
	}

	public static float getHardness(int itemDamage) {
		for (MTBItemSensibleButtons itemstack : MTBItemSensibleButtons.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.hardness;
			}
		}
		return 0.5F;
	}

	public void setTextureIndex(int textureIndex) {
		this.textureIndex = textureIndex;
	}

	public void setSensible(boolean sensible) {
		this.sensible = sensible;
	}

	public void setBlockHardness(float hardness) {
		this.hardness = hardness;
	}

	public static String[] getButtonNames() {
		String[] names = new String[MTBItemSensibleButtons.values().length];
		int i = 0;
		for (MTBItemSensibleButtons itemstack : MTBItemSensibleButtons.values()) {
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
