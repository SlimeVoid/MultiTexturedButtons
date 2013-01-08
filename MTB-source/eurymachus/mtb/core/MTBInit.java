package eurymachus.mtb.core;

import java.io.File;

import slimevoid.lib.ICommonProxy;
import slimevoid.lib.ICore;
import slimevoid.lib.core.BlockRemover;
import slimevoid.lib.core.Core;
import slimevoid.lib.core.SlimevoidCore;
import slimevoid.lib.core.RecipeRemover;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.Configuration;
import eurymachus.mtb.tileentities.TileEntityMTButton;

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
		MTBCore.configurationProperties();
		SlimevoidCore.console(MTB.getModName(), "Removing Recipies...");
		RecipeRemover.registerItemRecipeToRemove(Block.woodenButton);
		RecipeRemover.registerItemRecipeToRemove(Block.stoneButton);
		RecipeRemover.removeCrafting();
		SlimevoidCore.console(MTB.getModName(), "Removing Blocks...");
		BlockRemover.removeVanillaBlock(Block.woodenButton);
		BlockRemover.removeVanillaBlock(Block.stoneButton);
		SlimevoidCore.console(MTB.getModName(), "Registering items...");
		MTBCore.addItems();
		SlimevoidCore.console(MTB.getModName(), "Registering blocks...");
		MTBCore.registerBlocks();
		MTB.getProxy().registerRenderInformation();
		SlimevoidCore.console(MTB.getModName(), "Naming items...");
		MTBCore.addItemNames();
		SlimevoidCore.console(MTB.getModName(), "Registering recipes...");
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
