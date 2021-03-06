package mod.sol.recipe;

import micdoodle8.mods.galacticraft.api.recipe.CompressorRecipes;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class SolRecipeCompressor {
	public static void registryRecipe() 
	{
		//plates
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATE_T4, 2), new ItemStack(AsteroidsItems.basicItem, 1, 6), new ItemStack(AsteroidsItems.basicItem, 1, 5));
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATE_T5, 2), new ItemStack(SolItems.COMPRESSED_SULFUR, 1, 0), new ItemStack(SolItems.REINFORCED_PLATE_T4, 1, 0));
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATE_T6, 2), new ItemStack(SolItems.COMPRESSED_MANGANESE, 1, 0), new ItemStack(SolItems.REINFORCED_PLATE_T5, 1, 0));
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATE_T7, 2), new ItemStack(SolItems.COMPRESSED_LITHIUM, 1, 0), new ItemStack(SolItems.REINFORCED_PLATE_T6, 1, 0));
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATE_T8, 2), new ItemStack(SolItems.COMPRESSED_MAGNESIUM, 1, 0), new ItemStack(SolItems.REINFORCED_PLATE_T7, 1, 0));
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATE_T9, 2), new ItemStack(SolItems.COMPRESSED_VANADIUM, 1, 0), new ItemStack(SolItems.REINFORCED_PLATE_T8, 1, 0));
		//sulfur
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.SULFUR_SHARD, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1));
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_SULFUR, 1), new ItemStack(SolItems.SULFUR_INGOT, 1), new ItemStack(SolItems.SULFUR_INGOT, 1));
		//ash
		CompressorRecipes.addShapelessRecipe(new ItemStack(MarsItems.carbonFragments, 2), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1));
		CompressorRecipes.addShapelessRecipe(new ItemStack(Items.COAL, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1));
		//manganese
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_MANGANESE), new ItemStack(SolItems.MANGANESE_INGOT), new ItemStack(SolItems.MANGANESE_INGOT));
		//lithium
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_LITHIUM), new ItemStack(SolItems.LITHIUM_INGOT), new ItemStack(SolItems.LITHIUM_INGOT));
		//magnet
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_MAGNET), new ItemStack(SolItems.MAGNET_INGOT), new ItemStack(SolItems.MAGNET_INGOT));
		//magnesium
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_MAGNESIUM), new ItemStack(SolItems.MAGNESIUM_INGOT), new ItemStack(SolItems.MAGNESIUM_INGOT));
		//vanadium
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_VANADIUM), new ItemStack(SolItems.VANADIUM_INGOT), new ItemStack(SolItems.VANADIUM_INGOT));
		//osmium
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_OSMIUM), new ItemStack(SolItems.OSMIUM_INGOT), new ItemStack(SolItems.OSMIUM_INGOT));
	}
}