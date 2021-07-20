package mod.sol.recipe;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.item.ElectricItemHelper;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SolRecipeSmelting {
	public static void registryRecipe() 
	{
		//mercury
		GameRegistry.addSmelting(SolBlocks.MERCURY_ALUMINUM_ORE, new ItemStack(GCItems.basicItem, 1, 5), 1F);
		GameRegistry.addSmelting(SolBlocks.MERCURY_COPPER_ORE, new ItemStack(GCItems.basicItem, 1, 3), 1F);
		GameRegistry.addSmelting(SolBlocks.MERCURY_DESH_ORE, new ItemStack(MarsItems.marsItemBasic, 1, 2), 1F);
		GameRegistry.addSmelting(SolBlocks.MERCURY_ILMENITE_ORE, new ItemStack(AsteroidsItems.basicItem, 1, 0), 1F);
		GameRegistry.addSmelting(SolBlocks.MERCURY_IRON_ORE, new ItemStack(Items.IRON_INGOT, 1, 0), 1F);
		GameRegistry.addSmelting(SolBlocks.MERCURY_SILICON_ORE, new ItemStack(GCItems.basicItem, 1, 2), 1F);
		GameRegistry.addSmelting(SolBlocks.MERCURY_TIN_ORE, new ItemStack(GCItems.basicItem, 1, 4), 1F);
		//jupiter
		//io
		GameRegistry.addSmelting(SolBlocks.IO_ALUMINUM_ORE, new ItemStack(GCItems.basicItem, 1, 5), 1F);
		GameRegistry.addSmelting(SolBlocks.IO_COPPER_ORE, new ItemStack(GCItems.basicItem, 1, 3), 1F);
		GameRegistry.addSmelting(SolBlocks.IO_IRON_ORE, new ItemStack(Items.IRON_INGOT, 1, 0), 1F);
		GameRegistry.addSmelting(SolBlocks.IO_SULFUR_ORE, new ItemStack(SolItems.SULFUR_INGOT, 1, 0), 1F);
		GameRegistry.addSmelting(SolItems.SULFUR_SHARD, new ItemStack(SolItems.SULFUR_INGOT, 1, 0), 1F);
		
		//saturn
		//mimas
		GameRegistry.addSmelting(SolBlocks.MIMAS_MAGANESE_ORE, new ItemStack(SolItems.MANGANESE_INGOT), 1F);
		//titan
		GameRegistry.addSmelting(SolBlocks.TITAN_MAGANESE_ORE, new ItemStack(SolItems.MANGANESE_INGOT), 1F);
	}
}
