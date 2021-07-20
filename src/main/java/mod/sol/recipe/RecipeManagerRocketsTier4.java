package mod.sol.recipe;

import java.util.HashMap;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import mod.sol.init.SolItems;
import mod.sol.util.RecipeUtilSol;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeManagerRocketsTier4 {
	public static void addUniversalRecipes()
    {
		HashMap<Integer, ItemStack> input = new HashMap<>();
        ItemStack plateTier4 = new ItemStack(SolItems.REINFORCED_PLATE_T4);
        ItemStack rocketFinsTier4 = new ItemStack(SolItems.ROCKET_FINS_T4);
        input.put(1, new ItemStack(AsteroidsItems.heavyNoseCone));
        input.put(2, plateTier4);
        input.put(3, plateTier4);
        input.put(4, plateTier4);
        input.put(5, plateTier4);
        input.put(6, plateTier4);
        input.put(7, plateTier4);
        input.put(8, plateTier4);
        input.put(9, plateTier4);
        input.put(10, plateTier4);
        input.put(11, plateTier4);
        input.put(12, new ItemStack(SolItems.ENGINE_BOOSTER_T4, 1));
        input.put(13, rocketFinsTier4);
        input.put(14, rocketFinsTier4);
        input.put(15, new ItemStack(AsteroidsItems.basicItem, 1, 1));
        input.put(16, new ItemStack(SolItems.ENGINE_BOOSTER_T4));
        input.put(17, rocketFinsTier4);
        input.put(18, rocketFinsTier4);
        input.put(22, ItemStack.EMPTY);
        input.put(19, ItemStack.EMPTY);
        input.put(20, ItemStack.EMPTY);
        input.put(21, ItemStack.EMPTY);
        // RecipeUtilSol.addT4RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T4, 1, 0), input));
        
        NonNullList<ItemStack> woodChests = OreDictionary.getOres("chestWood");
        HashMap<Integer, ItemStack> input2;

        for (ItemStack woodChest : woodChests)
        {
        	for (int i = 0; i < 4; ++i) {
            	input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(22, new ItemStack(AsteroidsItems.tier3Rocket, 1, i));
                RecipeUtilSol.addT4RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T4, 1, 0), input2));
            }
        	
            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, woodChest);
                input2.put(20, ItemStack.EMPTY);
                input2.put(21, ItemStack.EMPTY);
                input2.put(22, new ItemStack(AsteroidsItems.tier3Rocket, 1, i));
                RecipeUtilSol.addT4RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T4, 1, 1), input2));
            }
            
            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, ItemStack.EMPTY);
                input2.put(20, woodChest);
                input2.put(21, ItemStack.EMPTY);
                input2.put(22, new ItemStack(AsteroidsItems.tier3Rocket, 1, i));
                RecipeUtilSol.addT4RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T4, 1, 1), input2));
            }

            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, ItemStack.EMPTY);
                input2.put(20, ItemStack.EMPTY);
                input2.put(21, woodChest);
                input2.put(22, new ItemStack(AsteroidsItems.tier3Rocket, 1, i));
                RecipeUtilSol.addT4RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T4, 1, 1), input2));
            }

            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, woodChest);
                input2.put(20, woodChest);
                input2.put(21, ItemStack.EMPTY);
                input2.put(22, new ItemStack(AsteroidsItems.tier3Rocket, 1, i));
                RecipeUtilSol.addT4RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T4, 1, 2), input2));
            }

            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, woodChest);
                input2.put(20, ItemStack.EMPTY);
                input2.put(21, woodChest);
                input2.put(22, new ItemStack(AsteroidsItems.tier3Rocket, 1, i));
                RecipeUtilSol.addT4RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T4, 1, 2), input2));
            }

            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, ItemStack.EMPTY);
                input2.put(20, woodChest);
                input2.put(21, woodChest);
                input2.put(22, new ItemStack(AsteroidsItems.tier3Rocket, 1, i));
                RecipeUtilSol.addT4RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T4, 1, 2), input2));
            }

            for (int i = 0; i < 4; ++i) {
            	input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, woodChest);
                input2.put(20, woodChest);
                input2.put(21, woodChest);
                input2.put(22, new ItemStack(AsteroidsItems.tier3Rocket, 1, i));
                RecipeUtilSol.addT4RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T4, 1, 3), input2));
            }
        }
    }
}
