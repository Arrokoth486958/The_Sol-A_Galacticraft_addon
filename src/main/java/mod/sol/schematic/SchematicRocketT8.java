package mod.sol.schematic;

import micdoodle8.mods.galacticraft.api.recipe.SchematicPage;
import mod.sol.client.gui.container.GuiSchematicTier7Rocket;
import mod.sol.client.gui.container.GuiSchematicTier8Rocket;
import mod.sol.init.SolItems;
import mod.sol.inventory.ContainerSchematicTier7Rocket;
import mod.sol.inventory.ContainerSchematicTier8Rocket;
import mod.sol.util.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SchematicRocketT8 extends SchematicPage
{
    @Override
    public int getPageID()
    {
        return 11 + Reference.MOD_ID.hashCode();
    }

    @Override
    public int getGuiID()
    {
        return 5 + Reference.MOD_ID.hashCode();
    }

    @Override
    public ItemStack getRequiredItem()
    {
    	return new ItemStack(SolItems.SCHEMATIC_T8, 1, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiScreen getResultScreen(EntityPlayer player, BlockPos pos)
    {
        return new GuiSchematicTier8Rocket(player.inventory, pos);
    }

    @Override
    public Container getResultContainer(EntityPlayer player, BlockPos pos)
    {
        return new ContainerSchematicTier8Rocket(player.inventory, pos);
    }
}
