package eurymachus.mtb.core;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import eurymachus.mtb.blocks.BlockMTButton;
import eurymachus.mtb.items.ItemMTButton;
import eurymachus.mtb.tileentities.TileEntityMTButton;

public class MTBCore {
	public static File configFile;
	public static Configuration configuration;
	public static boolean initialized = false;

	public static void initialize() {
		if (initialized)
			return;
		initialized = true;
		MTBInit.initialize();
	}

	public static void addItems() {
		MTBBlocks.mtButton.me = new BlockMTButton(
				MTBBlocks.mtButton.id,
					TileEntityMTButton.class,
					0.5F,
					Block.soundStoneFootstep,
					true,
					true,
					false,
					"mtButton");
		MTBBlocks.mtSensibleButton.me = new BlockMTButton(
				MTBBlocks.mtSensibleButton.id,
					TileEntityMTButton.class,
					0.5F,
					Block.soundStoneFootstep,
					true,
					true,
					true,
					"mtSensibleButton");
		GameRegistry.registerTileEntity(TileEntityMTButton.class, "mtButton");
		for (MTBItemButtons button : MTBItemButtons.values()) {
			button.me = new ItemStack(MTBBlocks.mtButton.me, 1, button.stackID);
		}
		for (MTBItemSensibleButtons sbutton : MTBItemSensibleButtons.values()) {
			sbutton.me = new ItemStack(MTBBlocks.mtSensibleButton.me, 1, sbutton.stackID);
		}
	}

	public static void registerBlocks() {
		for (MTBBlocks block : MTBBlocks.values()) {
			if (block != null && block.me != null) {
				GameRegistry.registerBlock(block.me, ItemMTButton.class, block.name);
			}
		}
	}

	public static void addItemNames() {
		for (MTBItemButtons button : MTBItemButtons.values()) {
			if (button != null && button.me != null && button.name != null && !button.name
					.isEmpty()) {
				LanguageRegistry.addName(button.me, button.name);
			}
		}
		for (MTBItemSensibleButtons sbutton : MTBItemSensibleButtons.values()) {
			if (sbutton != null && sbutton.me != null && sbutton.name != null && !sbutton.name
					.isEmpty()) {
				LanguageRegistry.addName(sbutton.me, sbutton.name);
			}
		}
	}

	public static void addRecipes() {

		GameRegistry.addRecipe(MTBItemSensibleButtons.oakPlank.me, new Object[] {
				"X",
				Character.valueOf('X'),
				new ItemStack(Block.planks, 1, 0) });

		GameRegistry.addRecipe(MTBItemSensibleButtons.sprucePlank.me, new Object[] {
				"X",
				Character.valueOf('X'),
				new ItemStack(Block.planks, 1, 1) });

		GameRegistry.addRecipe(MTBItemSensibleButtons.birchPlank.me, new Object[] {
				"X",
				Character.valueOf('X'),
				new ItemStack(Block.planks, 1, 2) });

		GameRegistry.addRecipe(MTBItemSensibleButtons.junglePlank.me, new Object[] {
				"X",
				Character.valueOf('X'),
				new ItemStack(Block.planks, 1, 3) });

		GameRegistry.addRecipe(MTBItemButtons.polishedStone.me, new Object[] {
				"X",
				Character.valueOf('X'),
				Block.stoneSingleSlab });

		GameRegistry.addRecipe(MTBItemButtons.cobbleStone.me, new Object[] {
				"X",
				"X",
				Character.valueOf('X'),
				Block.cobblestone });

		GameRegistry.addRecipe(MTBItemButtons.smoothStone.me, new Object[] {
				"X",
				"X",
				Character.valueOf('X'),
				Block.stone });

		GameRegistry.addRecipe(MTBItemButtons.iron.me, new Object[] {
				"X",
				"Y",
				"X",
				Character.valueOf('X'),
				Item.ingotIron,
				Character.valueOf('Y'),
				Block.stoneButton });

		GameRegistry.addRecipe(MTBItemButtons.gold.me, new Object[] {
				"X",
				"Y",
				"X",
				Character.valueOf('X'),
				Item.ingotGold,
				Character.valueOf('Y'),
				MTBItemButtons.iron.me });

		GameRegistry.addRecipe(MTBItemButtons.diamond.me, new Object[] {
				"X",
				"Y",
				"X",
				Character.valueOf('X'),
				Item.diamond,
				Character.valueOf('Y'),
				MTBItemButtons.gold.me });

		FurnaceRecipes.smelting().addSmelting(
				MTBBlocks.mtButton.id,
				MTBItemButtons.iron.stackID,
				new ItemStack(Item.ingotIron, 2),
				1);
		FurnaceRecipes.smelting().addSmelting(
				MTBBlocks.mtButton.id,
				MTBItemButtons.gold.stackID,
				new ItemStack(Item.ingotGold, 2),
				2);
		FurnaceRecipes.smelting().addSmelting(
				MTBBlocks.mtButton.id,
				MTBItemButtons.diamond.stackID,
				new ItemStack(Item.diamond, 2),
				3);
	}

	public static int configurationProperties() {
		configuration.load();
		MTBBlocks.mtButton.id = configuration.get(
				Configuration.CATEGORY_BLOCK,
				"mtButton",
				Block.stoneButton.blockID).getInt();
		MTBBlocks.mtButton.name = "Multi-Textured Button";
		MTBBlocks.mtSensibleButton.id = configuration.get(
				Configuration.CATEGORY_BLOCK,
				"mtSensibleButton",
				Block.woodenButton.blockID).getInt();
		MTBBlocks.mtSensibleButton.name = "Multi-Textured Sensible Button";
		nonsensibleButtons();
		sensibleButtons();
		configuration.save();
		return MTBBlocks.mtButton.id;
	}
	
	public static void nonsensibleButtons() {
		MTBItemButtons.smoothStone.name = "Smooth Stone Button";
		MTBItemButtons.smoothStone.stackID = 0;
		MTBItemButtons.smoothStone.setTextureLocation("stone");
		MTBItemButtons.smoothStone.setBlockHardness(0.8F);
		MTBItemButtons.polishedStone.name = "Polished Stone Button";
		MTBItemButtons.polishedStone.stackID = 1;
		MTBItemButtons.polishedStone.setTextureLocation("stone_slab_top");
		MTBItemButtons.polishedStone.setBlockHardness(1.0F);
		MTBItemButtons.cobbleStone.name = "Cobblestone Button";
		MTBItemButtons.cobbleStone.stackID = 2;
		MTBItemButtons.cobbleStone.setTextureLocation("cobblestone");
		MTBItemButtons.cobbleStone.setBlockHardness(1.0F);
		MTBItemButtons.iron.name = "Iron-Clad Button";
		MTBItemButtons.iron.stackID = 3;
		MTBItemButtons.iron.setTextureLocation("iron_block");
		MTBItemButtons.iron.setBlockHardness(2.5F);
		MTBItemButtons.gold.name = "Gold-Plated Button";
		MTBItemButtons.gold.stackID = 4;
		MTBItemButtons.gold.setTextureLocation("gold_block");
		MTBItemButtons.gold.setBlockHardness(1.5F);
		MTBItemButtons.diamond.name = "Diamond-Encrusted Button";
		MTBItemButtons.diamond.stackID = 5;
		MTBItemButtons.diamond.setTextureLocation("diamond_block");
		MTBItemButtons.diamond.setBlockHardness(2.5F);
		
	}
	
	public static void sensibleButtons() {
		MTBItemSensibleButtons.oakPlank.name = "Oak Wood Button";
		MTBItemSensibleButtons.oakPlank.stackID = 0;
		MTBItemSensibleButtons.oakPlank.setTextureLocation("planks_oak");
		MTBItemSensibleButtons.oakPlank.setSensible(true);
		MTBItemSensibleButtons.oakPlank.setBlockHardness(0.5F);
		MTBItemSensibleButtons.sprucePlank.name = "Spruce Wood Button";
		MTBItemSensibleButtons.sprucePlank.stackID = 1;
		MTBItemSensibleButtons.sprucePlank.setTextureLocation("planks_spruce");
		MTBItemSensibleButtons.sprucePlank.setSensible(true);
		MTBItemSensibleButtons.sprucePlank.setBlockHardness(0.5F);
		MTBItemSensibleButtons.birchPlank.name = "Birch Wood Button";
		MTBItemSensibleButtons.birchPlank.stackID = 2;
		MTBItemSensibleButtons.birchPlank.setTextureLocation("planks_birch");
		MTBItemSensibleButtons.birchPlank.setSensible(true);
		MTBItemSensibleButtons.birchPlank.setBlockHardness(0.5F);
		MTBItemSensibleButtons.junglePlank.name = "Jungle Wood Button";
		MTBItemSensibleButtons.junglePlank.stackID = 3;
		MTBItemSensibleButtons.junglePlank.setTextureLocation("planks_jungle");
		MTBItemSensibleButtons.junglePlank.setSensible(true);
		MTBItemSensibleButtons.junglePlank.setBlockHardness(0.5F);
	}
}
