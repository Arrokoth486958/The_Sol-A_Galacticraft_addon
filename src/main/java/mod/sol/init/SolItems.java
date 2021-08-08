package mod.sol.init;

import java.util.ArrayList;
import java.util.List;

import mod.sol.TheSol;
import mod.sol.items.*;
import mod.sol.items.armor.ItemMagnetBoots;
import mod.sol.items.armor.manganese.ItemArmorManganese;
import mod.sol.items.armor.sulfur.ItemArmorSulfur;
import mod.sol.items.rocket.ItemTier4Rocket;
import mod.sol.items.rocket.ItemTier5Rocket;
import mod.sol.items.rocket.ItemTier6Rocket;
import mod.sol.items.rocket.ItemTier7Rocket;
import mod.sol.items.tools.*;
import mod.sol.items.tools.ItemHoeBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class SolItems
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	// unused
	// public static final Item TRANSLATION_INFO = new ItemBase("translation_info", TheSol.ITEM_TAB);
	// rocket stuff
	// t4
	public static final Item REINFORCED_PLATE_T4 = new ItemBase("reinforced_plate_t4", TheSol.ITEM_TAB);
	public static final Item ROCKET_FINS_T4 = new ItemBase("rocket_fins_t4", TheSol.ITEM_TAB);
	public static final Item ENGINE_BOOSTER_T4 = new ItemBase("engine_booster_t4", TheSol.ITEM_TAB);
	public static final Item KEY_TIER_4 = new ItemKeyTier4("key_t4");
	public static final Item SCHEMATIC_T4 = new ItemSchematicTier4("schematic_rocket_t4");
	public static final Item ROCKET_T4 = new ItemTier4Rocket("rocket_t4");
	// t5
	public static final Item ROCKET_ENGINE_T5 = new ItemBase("rocket_engine_t5", TheSol.ITEM_TAB);
	public static final Item REINFORCED_PLATE_T5 = new ItemBase("reinforced_plate_t5", TheSol.ITEM_TAB);
	public static final Item NOSE_CONE_T5 = new ItemBase("nose_cone_t5", TheSol.ITEM_TAB);
	public static final Item ROCKET_FINS_T5 = new ItemBase("rocket_fins_t5", TheSol.ITEM_TAB);
	public static final Item ENGINE_BOOSTER_T5 = new ItemBase("engine_booster_t5", TheSol.ITEM_TAB);
	public static final Item KEY_TIER_5 = new ItemKeyTier5("key_t5");
	public static final Item SCHEMATIC_T5 = new ItemSchematicTier5("schematic_rocket_t5");
	public static final Item ROCKET_T5 = new ItemTier5Rocket("rocket_t5");
	// t6
	public static final Item ROCKET_ENGINE_T6 = new ItemBase("rocket_engine_t6", TheSol.ITEM_TAB);
	public static final Item REINFORCED_PLATE_T6 = new ItemBase("reinforced_plate_t6", TheSol.ITEM_TAB);
	public static final Item NOSE_CONE_T6 = new ItemBase("nose_cone_t6", TheSol.ITEM_TAB);
	public static final Item ROCKET_FINS_T6 = new ItemBase("rocket_fins_t6", TheSol.ITEM_TAB);
	public static final Item ENGINE_BOOSTER_T6 = new ItemBase("engine_booster_t6", TheSol.ITEM_TAB);
	public static final Item KEY_TIER_6 = new ItemKeyTier6("key_t6");
	public static final Item SCHEMATIC_T6 = new ItemSchematicTier6("schematic_rocket_t6");
	public static final Item ROCKET_T6 = new ItemTier6Rocket("rocket_t6");
	// t7
	public static final Item ROCKET_ENGINE_T7 = new ItemBase("rocket_engine_t7", TheSol.ITEM_TAB);
	public static final Item REINFORCED_PLATE_T7 = new ItemBase("reinforced_plate_t7", TheSol.ITEM_TAB);
	public static final Item NOSE_CONE_T7 = new ItemBase("nose_cone_t7", TheSol.ITEM_TAB);
	public static final Item ROCKET_FINS_T7 = new ItemBase("rocket_fins_t7", TheSol.ITEM_TAB);
	public static final Item ENGINE_BOOSTER_T7 = new ItemBase("engine_booster_t7", TheSol.ITEM_TAB);
	public static final Item KEY_TIER_7 = new ItemKeyTier7("key_t7");
	public static final Item SCHEMATIC_T7 = new ItemSchematicTier7("schematic_rocket_t7");
	public static final Item ROCKET_T7 = new ItemTier7Rocket("rocket_t7");
	// t8
	// public static final Item ROCKET_T8 = new ItemTier8Rocket("rocket_t8");
	// battery
	public static final Item ADVANCED_BATTERY_TIER_1 = new ItemAdvancedBattery("advanced_battery_t1", 1);
	public static final Item ADVANCED_BATTERY_TIER_2 = new ItemAdvancedBattery("advanced_battery_t2", 2);
	// oxygen tank
	// public static final Item ADVANCED_OXYGEN_TANK = new ItemOxygenTankAdvanced(4, "advanced_oxygen_tank");
	// crafting material
	// sulfur
	public static final Item SULFUR_INGOT = new ItemBase("ingot_sulfur", TheSol.ITEM_TAB);
	public static final Item SULFUR_SHARD = new ItemBase("shard_sulfur", TheSol.ITEM_TAB);
	public static final Item COMPRESSED_SULFUR = new ItemBase("compressed_sulfur", TheSol.ITEM_TAB);
	public static final Item SULFUR_STICK = new ItemBase("stick_sulfur", TheSol.ITEM_TAB);
	// manganese
	public static final Item MANGANESE_INGOT = new ItemBase("ingot_manganese", TheSol.ITEM_TAB);
	public static final Item COMPRESSED_MANGANESE = new ItemBase("compressed_manganese", TheSol.ITEM_TAB);
	public static final Item MANGANESE_STICK = new ItemBase("stick_manganese", TheSol.ITEM_TAB);
	// lithium
	public static final Item LITHIUM_INGOT = new ItemBase("ingot_lithium", TheSol.ITEM_TAB);
	public static final Item COMPRESSED_LITHIUM = new ItemBase("compressed_lithium", TheSol.ITEM_TAB);
	// magnet
	public static final Item MAGNET_INGOT = new ItemBase("ingot_magnet", TheSol.ITEM_TAB);
	public static final Item COMPRESSED_MAGNET = new ItemBase("compressed_magnet", TheSol.ITEM_TAB);
	// armor
	public static final ArmorMaterial ARMOR_SULFUR  = EnumHelper.addArmorMaterial("SULFUR", "", 42, new int[] { 5, 8, 9, 5 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F);
	public static final Item SULFUR_HELMET = new ItemArmorSulfur(SolItems.ARMOR_SULFUR, 7, EntityEquipmentSlot.HEAD).setUnlocalizedName("sulfur_helmet").setRegistryName("sulfur_helmet");
	public static final Item SULFUR_CHESTPLATE = new ItemArmorSulfur(SolItems.ARMOR_SULFUR, 7, EntityEquipmentSlot.CHEST).setUnlocalizedName("sulfur_chestplate").setRegistryName("sulfur_chestplate");
	public static final Item SULFUR_LEGGINGS = new ItemArmorSulfur(SolItems.ARMOR_SULFUR, 7, EntityEquipmentSlot.LEGS).setUnlocalizedName("sulfur_leggings").setRegistryName("sulfur_leggings");
	public static final Item SULFUR_BOOTS = new ItemArmorSulfur(SolItems.ARMOR_SULFUR, 7, EntityEquipmentSlot.FEET).setUnlocalizedName("sulfur_boots").setRegistryName("sulfur_boots");

	public static final ArmorMaterial ARMOR_MANGANESE  = EnumHelper.addArmorMaterial("MANGANESE", "", 42, new int[] { 6, 9, 10, 6 }, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
	public static final Item MANGANESE_HELMET = new ItemArmorManganese(SolItems.ARMOR_MANGANESE, 7, EntityEquipmentSlot.HEAD).setUnlocalizedName("manganese_helmet").setRegistryName("manganese_helmet");
	public static final Item MANGANESE_CHESTPLATE = new ItemArmorManganese(SolItems.ARMOR_MANGANESE, 7, EntityEquipmentSlot.CHEST).setUnlocalizedName("manganese_chestplate").setRegistryName("manganese_chestplate");
	public static final Item MANGANESE_LEGGINGS = new ItemArmorManganese(SolItems.ARMOR_MANGANESE, 7, EntityEquipmentSlot.LEGS).setUnlocalizedName("manganese_leggings").setRegistryName("manganese_leggings");
	public static final Item MANGANESE_BOOTS = new ItemArmorManganese(SolItems.ARMOR_MANGANESE, 7, EntityEquipmentSlot.FEET).setUnlocalizedName("manganese_boots").setRegistryName("manganese_boots");

	// tools
	public static final ToolMaterial TOOL_SULFUR = EnumHelper.addToolMaterial("SULFUR", 3, 780, 8.0F, 6.5F, 12);
	public static final Item SULFUR_AXE = new ItemAxeBase(SolItems.TOOL_SULFUR).setUnlocalizedName("sulfur_axe").setRegistryName("sulfur_axe");
	public static final Item SULFUR_HOE = new ItemHoeBase(SolItems.TOOL_SULFUR).setUnlocalizedName("sulfur_hoe").setRegistryName("sulfur_hoe");
	public static final Item SULFUR_PICKAXE = new ItemPickaxeBase(SolItems.TOOL_SULFUR).setUnlocalizedName("sulfur_pickaxe").setRegistryName("sulfur_pickaxe");
	public static final Item SULFUR_SHOVEL = new ItemShovelBase(SolItems.TOOL_SULFUR).setUnlocalizedName("sulfur_shovel").setRegistryName("sulfur_shovel");
	public static final Item SULFUR_SWORD = new ItemSwordBase(SolItems.TOOL_SULFUR).setUnlocalizedName("sulfur_sword").setRegistryName("sulfur_sword");

	public static final ToolMaterial TOOL_MANGANESE = EnumHelper.addToolMaterial("MANGANESE", 4, 2200, 4.0F, 8F, 8);
	public static final Item MANGANESE_AXE = new ItemAxeBase(SolItems.TOOL_MANGANESE).setUnlocalizedName("manganese_axe").setRegistryName("manganese_axe");
	public static final Item MANGANESE_HOE = new ItemHoeBase(SolItems.TOOL_MANGANESE).setUnlocalizedName("manganese_hoe").setRegistryName("manganese_hoe");
	public static final Item MANGANESE_PICKAXE = new ItemPickaxeBase(SolItems.TOOL_MANGANESE).setUnlocalizedName("manganese_pickaxe").setRegistryName("manganese_pickaxe");
	public static final Item MANGANESE_SHOVEL = new ItemShovelBase(SolItems.TOOL_MANGANESE).setUnlocalizedName("manganese_shovel").setRegistryName("manganese_shovel");
	public static final Item MANGANESE_SWORD = new ItemSwordBase(SolItems.TOOL_MANGANESE).setUnlocalizedName("manganese_sword").setRegistryName("manganese_sword");

	// magnet boots
	public static final Item MAGNET_BOOTS = new ItemMagnetBoots("magnet_boots", ArmorMaterial.IRON, 7);
}
