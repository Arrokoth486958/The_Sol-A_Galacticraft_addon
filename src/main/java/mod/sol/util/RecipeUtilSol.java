package mod.sol.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import mod.sol.inventory.*;
import net.minecraft.item.ItemStack;

public class RecipeUtilSol {
	private static List<INasaWorkbenchRecipe> rocketBenchT4Recipes = new ArrayList<INasaWorkbenchRecipe>();
	private static List<INasaWorkbenchRecipe> rocketBenchT5Recipes = new ArrayList<INasaWorkbenchRecipe>();
    private static List<INasaWorkbenchRecipe> rocketBenchT6Recipes = new ArrayList<INasaWorkbenchRecipe>();
    private static List<INasaWorkbenchRecipe> rocketBenchT7Recipes = new ArrayList<INasaWorkbenchRecipe>();
    private static List<INasaWorkbenchRecipe> rocketBenchT8Recipes = new ArrayList<INasaWorkbenchRecipe>();
    private static List<INasaWorkbenchRecipe> rocketBenchT9Recipes = new ArrayList<INasaWorkbenchRecipe>();
    private static List<INasaWorkbenchRecipe> rocketBenchT10Recipes = new ArrayList<INasaWorkbenchRecipe>();

    public static void addT4RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        RecipeUtilSol.rocketBenchT4Recipes.add(recipe);
    }
    
    public static void removeT4RocketRecipe(INasaWorkbenchRecipe recipe)
    {
    	RecipeUtilSol.rocketBenchT4Recipes.remove(recipe);
    }
    
    public static void removeAllT4RocketRecipes()
    {
    	RecipeUtilSol.rocketBenchT4Recipes.clear();
    }
    
    public static List<INasaWorkbenchRecipe> getRocketT4Recipes()
    {
        return RecipeUtilSol.rocketBenchT4Recipes;
    }
    
    public static void addT5RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        RecipeUtilSol.rocketBenchT5Recipes.add(recipe);
    }
    
    public static void removeT5RocketRecipe(INasaWorkbenchRecipe recipe)
    {
    	RecipeUtilSol.rocketBenchT5Recipes.remove(recipe);
    }
    
    public static void removeAllT5RocketRecipes()
    {
    	RecipeUtilSol.rocketBenchT5Recipes.clear();
    }
    
    public static List<INasaWorkbenchRecipe> getRocketT5Recipes()
    {
        return RecipeUtilSol.rocketBenchT5Recipes;
    }

    public static void addT6RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        RecipeUtilSol.rocketBenchT6Recipes.add(recipe);
    }

    public static void removeT6RocketRecipe(INasaWorkbenchRecipe recipe)
    {
    	RecipeUtilSol.rocketBenchT6Recipes.remove(recipe);
    }

    public static void removeAllT6RocketRecipes()
    {
    	RecipeUtilSol.rocketBenchT6Recipes.clear();
    }

    public static List<INasaWorkbenchRecipe> getRocketT6Recipes()
    {
        return RecipeUtilSol.rocketBenchT6Recipes;
    }

    public static List<INasaWorkbenchRecipe> getRocketT7Recipes()
    {
        return RecipeUtilSol.rocketBenchT7Recipes;
    }


    public static void addT7RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        RecipeUtilSol.rocketBenchT7Recipes.add(recipe);
    }

    public static void removeT7RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        RecipeUtilSol.rocketBenchT7Recipes.remove(recipe);
    }

    public static void removeAllT7RocketRecipes()
    {
        RecipeUtilSol.rocketBenchT7Recipes.clear();
    }

    public static List<INasaWorkbenchRecipe> getRocketT8Recipes()
    {
        return RecipeUtilSol.rocketBenchT8Recipes;
    }


    public static void addT8RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        RecipeUtilSol.rocketBenchT8Recipes.add(recipe);
    }

    public static void removeT8RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        RecipeUtilSol.rocketBenchT8Recipes.remove(recipe);
    }

    public static void removeAllT8RocketRecipes()
    {
        RecipeUtilSol.rocketBenchT8Recipes.clear();
    }

    public static List<INasaWorkbenchRecipe> getRocketT9Recipes()
    {
        return RecipeUtilSol.rocketBenchT9Recipes;
    }


    public static void addT9RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        RecipeUtilSol.rocketBenchT9Recipes.add(recipe);
    }

    public static void removeT9RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        RecipeUtilSol.rocketBenchT9Recipes.remove(recipe);
    }

    public static void removeAllT9RocketRecipes()
    {
        RecipeUtilSol.rocketBenchT9Recipes.clear();
    }

    public static List<INasaWorkbenchRecipe> getRocketT10Recipes()
    {
        return RecipeUtilSol.rocketBenchT10Recipes;
    }


    public static void addT10RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        RecipeUtilSol.rocketBenchT10Recipes.add(recipe);
    }

    public static void removeT10RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        RecipeUtilSol.rocketBenchT10Recipes.remove(recipe);
    }

    public static void removeAllT10RocketRecipes()
    {
        RecipeUtilSol.rocketBenchT10Recipes.clear();
    }

    @Nonnull
    public static ItemStack findMatchingSpaceshipT4Recipe(InventorySchematicTier4Rocket inventoryRocketBench)
    {
        for (INasaWorkbenchRecipe recipe : RecipeUtilSol.getRocketT4Recipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }

        return ItemStack.EMPTY;
    }
    
	@Nonnull
    public static ItemStack findMatchingSpaceshipT5Recipe(InventorySchematicTier5Rocket inventoryRocketBench)
    {
        for (INasaWorkbenchRecipe recipe : RecipeUtilSol.getRocketT5Recipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }

        return ItemStack.EMPTY;
    }
	
	@Nonnull
    public static ItemStack findMatchingSpaceshipT6Recipe(InventorySchematicTier6Rocket inventoryRocketBench)
    {
        for (INasaWorkbenchRecipe recipe : RecipeUtilSol.getRocketT6Recipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }

        return ItemStack.EMPTY;
    }

    @Nonnull
    public static ItemStack findMatchingSpaceshipT7Recipe(InventorySchematicTier7Rocket inventoryRocketBench)
    {
        for (INasaWorkbenchRecipe recipe : RecipeUtilSol.getRocketT7Recipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }

        return ItemStack.EMPTY;
    }

    @Nonnull
    public static ItemStack findMatchingSpaceshipT8Recipe(InventorySchematicTier8Rocket inventoryRocketBench)
    {
        for (INasaWorkbenchRecipe recipe : RecipeUtilSol.getRocketT8Recipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }

        return ItemStack.EMPTY;
    }

    @Nonnull
    public static ItemStack findMatchingSpaceshipT9Recipe(InventorySchematicTier9Rocket inventoryRocketBench)
    {
        for (INasaWorkbenchRecipe recipe : RecipeUtilSol.getRocketT9Recipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }

        return ItemStack.EMPTY;
    }

    @Nonnull
    public static ItemStack findMatchingSpaceshipT10Recipe(InventorySchematicTier10Rocket inventoryRocketBench)
    {
        for (INasaWorkbenchRecipe recipe : RecipeUtilSol.getRocketT10Recipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }

        return ItemStack.EMPTY;
    }
}
