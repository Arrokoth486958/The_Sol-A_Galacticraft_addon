package mod.sol.schematic;

import micdoodle8.mods.galacticraft.api.recipe.SchematicPage;
import mod.sol.client.gui.container.GuiSchematicTier6Rocket;
import mod.sol.init.SolItems;
import mod.sol.inventory.ContainerSchematicTier6Rocket;
import mod.sol.util.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SchematicRocketT6 extends SchematicPage
{
    @Override
    public int getPageID()
    {
        return 9 + Reference.MOD_ID.hashCode();
    }

    @Override
    public int getGuiID()
    {
        return 3 + Reference.MOD_ID.hashCode();
    }

    @Override
    public ItemStack getRequiredItem()
    {
        return new ItemStack(SolItems.SCHEMATIC_T6, 1, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiScreen getResultScreen(EntityPlayer player, BlockPos pos)
    {
        return new GuiSchematicTier6Rocket(player.inventory, pos);
    }

    @Override
    public Container getResultContainer(EntityPlayer player, BlockPos pos)
    {
        return new ContainerSchematicTier6Rocket(player.inventory, pos);
    }
}
