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

public class RecipeManagerRocketsTier6 {
	public static void addUniversalRecipes()
    {
		HashMap<Integer, ItemStack> input = new HashMap<>();
        ItemStack plate = new ItemStack(SolItems.REINFORCED_PLATE_T6);
        ItemStack rocketFins = new ItemStack(SolItems.ROCKET_FINS_T6);
        input.put(1, new ItemStack(SolItems.NOSE_CONE_T6));
        input.put(2, plate);
        input.put(3, plate);
        input.put(4, plate);
        input.put(5, plate);
        input.put(6, plate);
        input.put(7, plate);
        input.put(8, plate);
        input.put(9, plate);
        input.put(10, plate);
        input.put(11, plate);
        input.put(12, new ItemStack(SolItems.ENGINE_BOOSTER_T6));
        input.put(13, rocketFins);
        input.put(14, rocketFins);
        input.put(15, new ItemStack(SolItems.ROCKET_ENGINE_T6));
        input.put(16, new ItemStack(SolItems.ENGINE_BOOSTER_T6));
        input.put(17, rocketFins);
        input.put(18, rocketFins);
        input.put(19, ItemStack.EMPTY);
        input.put(20, ItemStack.EMPTY);
        input.put(21, ItemStack.EMPTY);
        input.put(22, ItemStack.EMPTY);
    
        NonNullList<ItemStack> woodChests = OreDictionary.getOres("chestWood");
        HashMap<Integer, ItemStack> input2;

        for (ItemStack woodChest : woodChests)
        {
        	for (int i = 0; i < 4; ++i) {
            	input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(22, new ItemStack(SolItems.ROCKET_T5, 1, i));
                RecipeUtilSol.addT6RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T6, 1, 0), input2));
            }
        	
            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, woodChest);
                input2.put(20, ItemStack.EMPTY);
                input2.put(21, ItemStack.EMPTY);
                input2.put(22, new ItemStack(SolItems.ROCKET_T5, 1, i));
                RecipeUtilSol.addT6RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T6, 1, 1), input2));
            }
            
            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, ItemStack.EMPTY);
                input2.put(20, woodChest);
                input2.put(21, ItemStack.EMPTY);
                input2.put(22, new ItemStack(SolItems.ROCKET_T5, 1, i));
                RecipeUtilSol.addT6RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T6, 1, 1), input2));
            }

            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, ItemStack.EMPTY);
                input2.put(20, ItemStack.EMPTY);
                input2.put(21, woodChest);
                input2.put(22, new ItemStack(SolItems.ROCKET_T5, 1, i));
                RecipeUtilSol.addT6RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T6, 1, 1), input2));
            }

            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, woodChest);
                input2.put(20, woodChest);
                input2.put(21, ItemStack.EMPTY);
                input2.put(22, new ItemStack(SolItems.ROCKET_T5, 1, i));
                RecipeUtilSol.addT6RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T6, 1, 2), input2));
            }

            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, woodChest);
                input2.put(20, ItemStack.EMPTY);
                input2.put(21, woodChest);
                input2.put(22, new ItemStack(SolItems.ROCKET_T5, 1, i));
                RecipeUtilSol.addT6RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T6, 1, 2), input2));
            }

            for (int i = 0; i < 4; ++i) {
                input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, ItemStack.EMPTY);
                input2.put(20, woodChest);
                input2.put(21, woodChest);
                input2.put(22, new ItemStack(SolItems.ROCKET_T5, 1, i));
                RecipeUtilSol.addT6RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T6, 1, 2), input2));
            }

            for (int i = 0; i < 4; ++i) {
            	input2 = new HashMap<Integer, ItemStack>(input);
                input2.put(19, woodChest);
                input2.put(20, woodChest);
                input2.put(21, woodChest);
                input2.put(22, new ItemStack(SolItems.ROCKET_T5, 1, i));
                RecipeUtilSol.addT6RocketRecipe(new NasaWorkbenchRecipe(new ItemStack(SolItems.ROCKET_T6, 1, 3), input2));
            }
        }
    }
}
