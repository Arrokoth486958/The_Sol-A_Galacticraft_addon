package mod.sol.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import mod.sol.TheSol;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import mod.sol.itemblock.ItemBlockBaseSol;
import mod.sol.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBaseSiliconOre extends Block implements IHasModel
{
	
	public BlockBaseSiliconOre(String name, Material material, String toolClass, int harvestLevel, CreativeTabs tab)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHarvestLevel(toolClass, harvestLevel);
		setCreativeTab(tab);

		SolBlocks.Blocks.add(this);
		SolItems.ITEMS.add(new ItemBlockBaseSol(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(GCItems.basicItem, 3, 2));
	}

	@Override
	public void registerModels() 
	{
		TheSol.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
