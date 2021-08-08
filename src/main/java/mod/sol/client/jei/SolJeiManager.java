package mod.sol.client.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import mod.sol.client.jei.tier4rocket.Tier4RocketRecipeCategory;
import mod.sol.client.jei.tier4rocket.Tier4RocketRecipeMaker;
import mod.sol.client.jei.tier4rocket.Tier4RocketRecipeWrapper;
import mod.sol.client.jei.tier5rocket.Tier5RocketRecipeCategory;
import mod.sol.client.jei.tier5rocket.Tier5RocketRecipeMaker;
import mod.sol.client.jei.tier5rocket.Tier5RocketRecipeWrapper;
import mod.sol.client.jei.tier6rocket.Tier6RocketRecipeCategory;
import mod.sol.client.jei.tier6rocket.Tier6RocketRecipeMaker;
import mod.sol.client.jei.tier6rocket.Tier6RocketRecipeWrapper;
import mod.sol.client.jei.tier7rocket.Tier7RocketRecipeCategory;
import mod.sol.client.jei.tier7rocket.Tier7RocketRecipeMaker;
import mod.sol.client.jei.tier7rocket.Tier7RocketRecipeWrapper;
import mod.sol.client.jei.tier8rocket.Tier8RocketRecipeCategory;
import mod.sol.client.jei.tier8rocket.Tier8RocketRecipeMaker;
import mod.sol.client.jei.tier8rocket.Tier8RocketRecipeWrapper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@JEIPlugin
public class SolJeiManager extends BlankModPlugin
{
    public static final String ROCKET_T4_ID = "sol.rocketT4";
    public static final String ROCKET_T5_ID = "sol.rocketT5";
    public static final String ROCKET_T6_ID = "sol.rocketT6";
    public static final String ROCKET_T7_ID = "sol.rocketT7";
    public static final String ROCKET_T8_ID = "sol.rocketT8";
    public static final String ROCKET_T9_ID = "sol.rocketT9";
    public static final String ROCKET_T10_ID = "sol.rocketT10";

    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        registry.handleRecipes(INasaWorkbenchRecipe.class, Tier4RocketRecipeWrapper::new, SolJeiManager.ROCKET_T4_ID);
        registry.handleRecipes(INasaWorkbenchRecipe.class, Tier5RocketRecipeWrapper::new, SolJeiManager.ROCKET_T5_ID);
        registry.handleRecipes(INasaWorkbenchRecipe.class, Tier6RocketRecipeWrapper::new, SolJeiManager.ROCKET_T6_ID);
        registry.handleRecipes(INasaWorkbenchRecipe.class, Tier7RocketRecipeWrapper::new, SolJeiManager.ROCKET_T7_ID);
        registry.handleRecipes(INasaWorkbenchRecipe.class, Tier8RocketRecipeWrapper::new, SolJeiManager.ROCKET_T8_ID);

        registry.addRecipes(Tier4RocketRecipeMaker.getRecipesList(), SolJeiManager.ROCKET_T4_ID);
        registry.addRecipes(Tier5RocketRecipeMaker.getRecipesList(), SolJeiManager.ROCKET_T5_ID);
        registry.addRecipes(Tier6RocketRecipeMaker.getRecipesList(), SolJeiManager.ROCKET_T6_ID);
        registry.addRecipes(Tier7RocketRecipeMaker.getRecipesList(), SolJeiManager.ROCKET_T7_ID);
        registry.addRecipes(Tier8RocketRecipeMaker.getRecipesList(), SolJeiManager.ROCKET_T8_ID);

        registry.addRecipeCatalyst(new ItemStack(GCBlocks.nasaWorkbench), SolJeiManager.ROCKET_T4_ID);
        registry.addRecipeCatalyst(new ItemStack(GCBlocks.nasaWorkbench), SolJeiManager.ROCKET_T5_ID);
        registry.addRecipeCatalyst(new ItemStack(GCBlocks.nasaWorkbench), SolJeiManager.ROCKET_T6_ID);
        registry.addRecipeCatalyst(new ItemStack(GCBlocks.nasaWorkbench), SolJeiManager.ROCKET_T7_ID);
        registry.addRecipeCatalyst(new ItemStack(GCBlocks.nasaWorkbench), SolJeiManager.ROCKET_T8_ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new Tier4RocketRecipeCategory(guiHelper));
        registry.addRecipeCategories(new Tier5RocketRecipeCategory(guiHelper));
        registry.addRecipeCategories(new Tier6RocketRecipeCategory(guiHelper));
        registry.addRecipeCategories(new Tier7RocketRecipeCategory(guiHelper));
        registry.addRecipeCategories(new Tier8RocketRecipeCategory(guiHelper));
    }
}
