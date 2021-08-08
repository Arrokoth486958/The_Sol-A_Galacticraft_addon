package mod.sol.client.jei.tier5rocket;

import com.google.common.collect.Lists;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class Tier5RocketRecipeWrapper implements IRecipeWrapper
{
    @Nonnull
    private final INasaWorkbenchRecipe recipe;

    public Tier5RocketRecipeWrapper(@Nonnull INasaWorkbenchRecipe recipe)
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