package mod.sol.schematic;

import micdoodle8.mods.galacticraft.api.recipe.SchematicPage;
import mod.sol.client.gui.container.GuiSchematicTier8Rocket;
import mod.sol.client.gui.container.GuiSchematicTier9Rocket;
import mod.sol.init.SolItems;
import mod.sol.inventory.ContainerSchematicTier8Rocket;
import mod.sol.inventory.ContainerSchematicTier9Rocket;
import mod.sol.util.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SchematicRocketT9 extends SchematicPage
{
    @Override
    public int getPageID()
    {
        return 12 + Reference.MOD_ID.hashCode();
    }

    @Override
    public int getGuiID()
    {
        return 6 + Reference.MOD_ID.hashCode();
    }

    @Override
    public ItemStack getRequiredItem()
    {
    	return new ItemStack(SolItems.SCHEMATIC_T9, 1, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiScreen getResultScreen(EntityPlayer player, BlockPos pos)
    {
        return new GuiSchematicTier9Rocket(player.inventory, pos);
    }

    @Override
    public Container getResultContainer(EntityPlayer player, BlockPos pos)
    {
        return new ContainerSchematicTier9Rocket(player.inventory, pos);
    }
}
