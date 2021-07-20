package mod.sol.schematic;

import micdoodle8.mods.galacticraft.api.recipe.SchematicPage;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.core.client.gui.GuiIdsCore;
import micdoodle8.mods.galacticraft.core.client.gui.container.GuiSchematicTier1Rocket;
import micdoodle8.mods.galacticraft.core.inventory.ContainerSchematicTier1Rocket;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import mod.sol.client.gui.container.GuiSchematicTier5Rocket;
import mod.sol.init.SolItems;
import mod.sol.inventory.ContainerSchematicTier5Rocket;
import mod.sol.util.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SchematicRocketT5 extends SchematicPage
{
    @Override
    public int getPageID()
    {
        return 8 + Reference.MOD_ID.hashCode();
    }

    @Override
    public int getGuiID()
    {
        return 2 + Reference.MOD_ID.hashCode();
    }

    @Override
    public ItemStack getRequiredItem()
    {
    	return new ItemStack(SolItems.SCHEMATIC_T5, 1, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiScreen getResultScreen(EntityPlayer player, BlockPos pos)
    {
        return new GuiSchematicTier5Rocket(player.inventory, pos);
    }

    @Override
    public Container getResultContainer(EntityPlayer player, BlockPos pos)
    {
        return new ContainerSchematicTier5Rocket(player.inventory, pos);
    }
}
