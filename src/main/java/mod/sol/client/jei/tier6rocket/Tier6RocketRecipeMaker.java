package mod.sol.client.jei.tier6rocket;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.client.jei.tier1rocket.Tier1RocketRecipeMaker;
import mod.sol.util.RecipeUtilSol;

import java.util.ArrayList;
import java.util.List;

public class Tier6RocketRecipeMaker
{
    public static List<INasaWorkbenchRecipe> getRecipesList()
    {
        List<INasaWorkbenchRecipe> recipes = new ArrayList<>();

        int chestCount = -1;
        for (INasaWorkbenchRecipe recipe : RecipeUtilSol.getRocketT6Recipes())
        {
            int chests = Tier1RocketRecipeMaker.countChests(recipe);
            if (chests == chestCount)
                continue;
            chestCount = chests;
            recipes.add(recipe);
        }

        return recipes;
    }
}
