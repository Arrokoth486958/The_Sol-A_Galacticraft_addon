package mod.sol.util.handler;

import mod.sol.init.SolBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;

public class RenderHandler {
	public static void registerCustomMeshesAndStates() {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(SolBlocks.METHANE_FLUID_BLOCK), stack -> new ModelResourceLocation("sol:liquid_methane", "fluid"));
		
		ModelLoader.setCustomStateMapper(SolBlocks.METHANE_FLUID_BLOCK, new StateMapperBase() {
			
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("sol:liquid_methane", "fluid");
			}
		});
	}
}
