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
		GameRegistry.addSmelting(SolItems.SULFUR_SHARD, new ItemStack(SolItems.SULFUR_INGOT, 1, 0), 1F);
		GameRegistry.addSmelting(SolBlocks.ARIEL_LITHIUM_ORE, new ItemStack(SolItems.MANGANESE_INGOT), 1F);
	}
}
