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
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATE_T4, 1), new ItemStack(AsteroidsItems.basicItem, 1, 6), new ItemStack(AsteroidsItems.basicItem, 1, 5));
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATE_T5, 1), new ItemStack(SolItems.COMPRESSED_SULFUR, 1, 0), new ItemStack(SolItems.REINFORCED_PLATE_T4, 1, 0));
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATE_T6, 1), new ItemStack(SolItems.COMPRESSED_MANGANESE, 1, 0), new ItemStack(SolItems.REINFORCED_PLATE_T5, 1, 0));
		//sulfur
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.SULFUR_SHARD, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1));
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_SULFUR, 1), new ItemStack(SolItems.SULFUR_INGOT, 1), new ItemStack(SolItems.SULFUR_INGOT, 1));
		//ash
		CompressorRecipes.addShapelessRecipe(new ItemStack(MarsItems.carbonFragments, 2), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1));
		CompressorRecipes.addShapelessRecipe(new ItemStack(Items.COAL, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1));
		//manganese
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_MANGANESE), new ItemStack(SolItems.MANGANESE_INGOT));
		//lithium
		CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_LITHIUM), new ItemStack(SolItems.LITHIUM_INGOT));
	}
}