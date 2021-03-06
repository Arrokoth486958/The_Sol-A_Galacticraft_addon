package mod.sol.util;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.ItemBlockDesc;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.planets.GalacticraftPlanets;
import micdoodle8.mods.galacticraft.planets.mars.blocks.MarsBlocks;
import micdoodle8.mods.galacticraft.planets.mars.tile.TileEntityTreasureChestMars;
import mod.sol.init.SolBlocks;
import mod.sol.tile.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SolTreasureChestRegistry {
	public static void registry() {
        // registerBlock(SolBlocks.TREASURE_CHEST_T4, ItemBlockDesc.class);
        // ClientUtil.registerBlockJson(Reference.MOD_ID, SolBlocks.TREASURE_CHEST_T4);
        GameRegistry.registerTileEntity(TileEntityTreasureChestTier4.class, "Sol Tier 4 Treasure Chest");
        GameRegistry.registerTileEntity(TileEntityTreasureChestTier5.class, "Sol Tier 5 Treasure Chest");
        GameRegistry.registerTileEntity(TileEntityTreasureChestTier6.class, "Sol Tier 6 Treasure Chest");
        GameRegistry.registerTileEntity(TileEntityTreasureChestTier7.class, "Sol Tier 7 Treasure Chest");
        GameRegistry.registerTileEntity(TileEntityTreasureChestTier8.class, "Sol Tier 8 Treasure Chest");
        GameRegistry.registerTileEntity(TileEntityTreasureChestTier9.class, "Sol Tier 9 Treasure Chest");
        GameRegistry.registerTileEntity(TileEntityTreasureChestTier10.class, "Sol Tier 10 Treasure Chest");
	}
	
	private static void registerBlock(Block block, Class<? extends ItemBlock> itemClass)
    {
        GCBlocks.registerBlock(block, itemClass);
    }
}
