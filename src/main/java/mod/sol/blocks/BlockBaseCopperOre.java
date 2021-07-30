package mod.sol.blocks;

import micdoodle8.mods.galacticraft.core.GCItems;
import mod.sol.TheSol;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import mod.sol.itemblock.ItemBlockBaseSol;
import mod.sol.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBaseCopperOre extends Block implements IHasModel
{
	public BlockBaseCopperOre(String name, Material material, String toolClass, int harvestLevel, CreativeTabs tab) {
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setHarvestLevel(toolClass, harvestLevel);
		this.setCreativeTab(tab);

		SolBlocks.Blocks.add(this);
		SolItems.ITEMS.add(new ItemBlockBaseSol(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() 
	{
		TheSol.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
