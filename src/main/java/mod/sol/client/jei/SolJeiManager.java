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
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@JEIPlugin
public class SolJeiManager extends BlankModPlugin
{
    public static final String ROCKET_T4_ID = "sol.rocketT4";

    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        registry.handleRecipes(INasaWorkbenchRecipe.class, Tier4RocketRecipeWrapper::new, SolJeiManager.ROCKET_T4_ID);

        registry.addRecipes(Tier4RocketRecipeMaker.getRecipesList(), SolJeiManager.ROCKET_T4_ID);

        registry.addRecipeCatalyst(new ItemStack(GCBlocks.nasaWorkbench), SolJeiManager.ROCKET_T4_ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new Tier4RocketRecipeCategory(guiHelper));
    }
}
