package mod.sol.util.handler;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import mod.sol.blocks.*;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolFluid;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class RegistryHandler 
{

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(SolItems.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		SolFluid.registerFluids();
		event.getRegistry().registerAll(SolBlocks.Blocks.toArray(new Block[0]));
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onBlockMeshRegister(RegistryEvent.Register<Block> event)
	{
		RenderHandler.registerCustomMeshesAndStates();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : SolItems.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : SolBlocks.Blocks)
		{
			if (block instanceof BlockBaseAluminumOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(GCItems.basicItem, 1, 5), 1F);
			}
			if (block instanceof BlockBaseCopperOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(GCItems.basicItem, 1, 3), 1F);
			}
			if (block instanceof BlockBaseTinOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(GCItems.basicItem, 1, 4), 1F);
			}
			if (block instanceof BlockBaseIronOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(Items.IRON_INGOT, 1, 0), 1F);
			}
			if (block instanceof BlockBaseDeshOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(MarsItems.marsItemBasic, 1, 2), 1F);
			}
			if (block instanceof BlockBaseDiamondOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(Items.DIAMOND, 2, 0), 1F);
			}
			if (block instanceof BlockBaseIlmeniteOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(AsteroidsItems.basicItem, 1, 0), 1F);
			}
			if (block instanceof BlockBaseMagnesiumOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(SolItems.MAGNESIUM_INGOT), 1F);
			}
			if (block instanceof BlockBaseMagnetOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(SolItems.MAGNET_INGOT), 1F);
			}
			if (block instanceof BlockBaseManganeseOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(SolItems.MANGANESE_INGOT), 1F);
			}
			if (block instanceof BlockBaseSiliconOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(GCItems.basicItem, 1, 2), 1F);
			}
			if (block instanceof BlockBaseSulfurOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(SolItems.SULFUR_INGOT, 1, 0), 1F);
			}
			if (block instanceof BlockBaseLithiumOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(SolItems.LITHIUM_INGOT, 1, 0), 1F);
			}
			if (block instanceof BlockBaseVanadiumOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(SolItems.VANADIUM_INGOT, 1, 0), 1F);
			}
			if (block instanceof BlockBaseOsmiumOre) {
				GameRegistry.addSmelting(new ItemStack(block), new ItemStack(SolItems.OSMIUM_INGOT, 1, 0), 1F);
			}

			if(block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}
	}	
}
