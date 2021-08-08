package mod.sol.blocks;

import micdoodle8.mods.galacticraft.core.blocks.BlockTier1TreasureChest;
import mod.sol.TheSol;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import mod.sol.itemblock.ItemBlockBaseSol;
import mod.sol.tile.TileEntityTreasureChestTier7;
import mod.sol.tile.TileEntityTreasureChestTier8;
import mod.sol.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTier8TreasureChest extends BlockTier1TreasureChest implements IHasModel {

	public BlockTier8TreasureChest(String name)
	{
		super(name);
		setUnlocalizedName(name);
		setRegistryName(name);

		SolBlocks.Blocks.add(this);
		SolItems.ITEMS.add(new ItemBlockBaseSol(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return TheSol.BLOCK_TAB;
    }
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityTreasureChestTier8();
    }
	
	@Override
	public void registerModels() 
	{
		TheSol.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
