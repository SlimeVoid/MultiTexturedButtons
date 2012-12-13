package mtb.core;

import java.io.File;

import mtb.tileentities.TileEntityMTButton;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.Configuration;
import eurysmods.api.ICommonProxy;
import eurysmods.api.ICore;
import eurysmods.core.BlockRemover;
import eurysmods.core.Core;
import eurysmods.core.EurysCore;
import eurysmods.core.RecipeRemover;

public class MTBInit {
	public static ICore MTB;
	private static boolean initialized = false;

	public static void initialize(ICommonProxy proxy) {
		if (initialized)
			return;
		initialized = true;
		MTB = new Core(proxy);
		MTB.setModName("MultiTexturedButtons");
		MTB.setModChannel("MTB");
		MTBCore.configFile = new File(
				MTBInit.MTB.getProxy().getMinecraftDir(),
					"config/MultiTexturedButtons.cfg");
		MTBCore.configuration = new Configuration(MTBCore.configFile);
		load();
	}

	public static void load() {
		EurysCore.console(MTB.getModName(), "Removing Recipies...");
		RecipeRemover.registerItemRecipeToRemove(Block.woodenButton);
		RecipeRemover.registerItemRecipeToRemove(Block.stoneButton);
		RecipeRemover.removeCrafting();
		EurysCore.console(MTB.getModName(), "Removing Blocks...");
		BlockRemover.removeVanillaBlock(Block.woodenButton);
		BlockRemover.removeVanillaBlock(Block.stoneButton);
		EurysCore.console(MTB.getModName(), "Registering items...");
		MTBCore.addItems();
		EurysCore.console(MTB.getModName(), "Registering blocks...");
		MTBCore.registerBlocks();
		MTB.getProxy().registerRenderInformation();
		EurysCore.console(MTB.getModName(), "Naming items...");
		MTBCore.addItemNames();
		EurysCore.console(MTB.getModName(), "Registering recipes...");
		MTBCore.addRecipes();
	}

	public static int getDamageValue(IBlockAccess blockAccess, int x, int y, int z) {
		TileEntity tileentity = blockAccess.getBlockTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityMTButton) {
			TileEntityMTButton tileentitymtbutton = (TileEntityMTButton) tileentity;
			return tileentitymtbutton.getTextureValue();
		}
		return 1000;
	}
}
