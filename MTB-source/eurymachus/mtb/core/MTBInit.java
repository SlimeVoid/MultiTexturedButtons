package eurymachus.mtb.core;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.Configuration;
import slimevoidlib.core.SlimevoidCore;
import slimevoidlib.core.SlimevoidLib;
import slimevoidlib.util.BlockRemover;
import slimevoidlib.util.RecipeRemover;
import eurymachus.mtb.core.lib.CoreLib;
import eurymachus.mtb.tileentities.TileEntityMTButton;

public class MTBInit {
	private static boolean initialized = false;

	public static void initialize() {
		if (initialized)
			return;
		initialized = true;
		MTBCore.configFile = new File(
				SlimevoidLib.proxy.getMinecraftDir(),
					"config/MultiTexturedButtons.cfg");
		MTBCore.configuration = new Configuration(MTBCore.configFile);
		load();
	}

	public static void load() {
		MTBCore.configurationProperties();
		SlimevoidCore.console(CoreLib.MOD_NAME, "Removing Recipies...");
		RecipeRemover.registerItemRecipeToRemove(Block.woodenButton);
		RecipeRemover.registerItemRecipeToRemove(Block.stoneButton);
		RecipeRemover.removeCrafting();
		SlimevoidCore.console(CoreLib.MOD_NAME, "Removing Blocks...");
		BlockRemover.removeVanillaBlock(Block.woodenButton);
		BlockRemover.removeVanillaBlock(Block.stoneButton);
		SlimevoidCore.console(CoreLib.MOD_NAME, "Registering items...");
		MTBCore.addItems();
		SlimevoidCore.console(CoreLib.MOD_NAME, "Registering blocks...");
		MTBCore.registerBlocks();
		MultiTexturedButtons.proxy.registerRenderInformation();
		SlimevoidCore.console(CoreLib.MOD_NAME, "Naming items...");
		MTBCore.addItemNames();
		SlimevoidCore.console(CoreLib.MOD_NAME, "Registering recipes...");
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
