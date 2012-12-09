package mtb.core;

import net.minecraft.src.Item;

public enum MTBItems {
	mtbItemButton;

	public Item me;
	private int id;
	public String name;

	public void setID(int id) {
		this.id = id;
	}

	public int offsetID() {
		return this.id - 256;
	}

	public int getID() {
		return this.id;
	}
}
