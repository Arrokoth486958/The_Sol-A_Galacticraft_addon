package mod.sol.client.jei.tier9rocket;

import com.google.common.collect.Lists;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class Tier9RocketRecipeWrapper implements IRecipeWrapper
{
    @Nonnull
    private final INasaWorkbenchRecipe recipe;

    public Tier9RocketRecipeWrapper(@Nonnull INasaWorkbenchRecipe recipe)
    {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputs(ItemStack.class, Lists.newArrayList(this.recipe.getRecipeInput().values()));
        ingredients.setOutput(ItemStack.class, this.recipe.getRecipeOutput());
    }
}