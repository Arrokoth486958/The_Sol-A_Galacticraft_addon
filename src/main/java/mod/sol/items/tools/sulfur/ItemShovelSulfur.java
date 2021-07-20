package mod.sol.items.tools.sulfur;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.TheSol;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShovelSulfur extends ItemSpade implements ISortableItem, IHasModel
{
    public ItemShovelSulfur(ToolMaterial par2EnumToolMaterial)
    {
        super(par2EnumToolMaterial);
        
		SolItems.ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTab()
    {
        return TheSol.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta)
    {
        return EnumSortCategoryItem.TOOLS;
    }
    
    @Override
	public void registerModels() {
		TheSol.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
