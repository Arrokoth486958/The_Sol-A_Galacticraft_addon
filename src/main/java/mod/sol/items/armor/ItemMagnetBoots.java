package mod.sol.items.armor;

import micdoodle8.mods.galacticraft.api.item.IArmorGravity;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderSpaceStation;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerHandler;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.TheSol;
import mod.sol.client.jei.SolJeiManager;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import mod.sol.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;

public class ItemMagnetBoots extends ItemArmor implements IArmorGravity, ISortableItem, IHasModel {
    private final ArmorMaterial material;

    public ItemMagnetBoots(String name, ArmorMaterial materialIn, int renderIndexIn) {
        super(materialIn, renderIndexIn, EntityEquipmentSlot.FEET);
        this.material = materialIn;
        this.setCreativeTab(TheSol.ITEM_TAB);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);

        SolItems.ITEMS.add(this);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        if (this.material == ArmorMaterial.IRON)
        {
            if (stack.getItem() == SolItems.MAGNET_BOOTS)
            {
                return Reference.MOD_ID + ":textures/model/magnet_boots.png";
            }
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public int gravityOverrideIfLow(EntityPlayer p) {
        if (p.world.provider instanceof WorldProviderSpaceStation) {
            Material material0 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 0, p.posZ - 0)).getMaterial();
            Material material1 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 1, p.posZ - 0)).getMaterial();
            Material material2 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 2, p.posZ - 0)).getMaterial();
            if (material0 == Material.IRON || material0 == Material.ANVIL || material1 == Material.IRON || material1 == Material.ANVIL || material2 == Material.IRON || material2 == Material.ANVIL)
                return -1000;
            else
                return 0;
        }
        else {
            Material material0 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 0, p.posZ - 0)).getMaterial();
            Material material1 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 1, p.posZ - 0)).getMaterial();
            Material material2 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 2, p.posZ - 0)).getMaterial();
            if (material0 == Material.IRON || material0 == Material.ANVIL || material1 == Material.IRON || material1 == Material.ANVIL || material2 == Material.IRON || material2 == Material.ANVIL)
                return 1000;
            else
                return 0;
        }
    }

    @Override
    public int gravityOverrideIfHigh(EntityPlayer p) {
        if (p.world.provider instanceof WorldProviderSpaceStation) {
            Material material0 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 0, p.posZ - 0)).getMaterial();
            Material material1 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 1, p.posZ - 0)).getMaterial();
            Material material2 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 2, p.posZ - 0)).getMaterial();
            if (material0 == Material.IRON || material0 == Material.ANVIL || material1 == Material.IRON || material1 == Material.ANVIL || material2 == Material.IRON || material2 == Material.ANVIL)
                return -1000;
            else
                return 0;
        }
        else {
            Material material0 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 0, p.posZ - 0)).getMaterial();
            Material material1 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 1, p.posZ - 0)).getMaterial();
            Material material2 = p.world.getBlockState(new BlockPos(p.posX - 0, (int) p.posY - 2, p.posZ - 0)).getMaterial();
            if (material0 == Material.IRON || material0 == Material.ANVIL || material1 == Material.IRON || material1 == Material.ANVIL || material2 == Material.IRON || material2 == Material.ANVIL)
                return 1000;
            else
                return 0;
        }
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.ARMOR;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return repair.getItem() == SolItems.MEGNET_INGOT;
    }

    @Override
    public void registerModels() {
        TheSol.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
