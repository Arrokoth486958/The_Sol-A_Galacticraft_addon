package mod.sol.blocks;

import micdoodle8.mods.galacticraft.core.blocks.BlockBossSpawner;
import mod.sol.TheSol;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import mod.sol.itemblock.ItemBlockBaseSol;
import mod.sol.tile.TileEntityDungeonSpawnerNeptune;
import mod.sol.tile.TileEntityDungeonSpawnerUranus;
import mod.sol.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBossSpawnerNeptune extends BlockBossSpawner implements IHasModel
{
	public BlockBossSpawnerNeptune(String assetName)
    {
        super(assetName);
        this.setUnlocalizedName(assetName);
		this.setRegistryName(assetName);

        SolBlocks.Blocks.add(this);
		SolItems.ITEMS.add(new ItemBlockBaseSol(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityDungeonSpawnerNeptune();
    }
    
    @Override
    public Block setCreativeTab(CreativeTabs tab) {
    	return null;
    }
    
    @Override
    public CreativeTabs getCreativeTabToDisplayOn() {
    	return null;
    }
    
    @Override
	public void registerModels() 
	{
		TheSol.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
    
    @Override
	@javax.annotation.Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}
