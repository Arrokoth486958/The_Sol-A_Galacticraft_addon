package mod.sol.init;

import java.util.ArrayList;
import java.util.List;

import mod.sol.TheSol;
import mod.sol.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SolBlocks {
	public static final List<Block> Blocks = new ArrayList<Block>();
	//spawner
	public static Block BOSS_SPAWNER_MERCURY = new BlockBossSpawnerMercury("boss_spawner_mercury");
	public static Block BOSS_SPAWNER_JUPITER = new BlockBossSpawnerJupiter("boss_spawner_jupiter");
	public static Block BOSS_SPAWNER_SATURN = new BlockBossSpawnerSaturn("boss_spawner_saturn");
	public static Block BOSS_SPAWNER_URANUS = new BlockBossSpawnerUranus("boss_spawner_uranus");
	public static Block BOSS_SPAWNER_NEPTUNE = new BlockBossSpawnerNeptune("boss_spawner_neptune");
	public static Block BOSS_SPAWNER_PLUTO = new BlockBossSpawnerPluto("boss_spawner_pluto");
	public static Block BOSS_SPAWNER_SEDNA = new BlockBossSpawnerSedna("boss_spawner_sedna ");
	//fluid
	public static final Block METHANE_FLUID_BLOCK = new BlockFluidBase("liquid_methane", SolFluid.METHANE, Material.WATER);
	//machine
	//public static final Block STABLE_SOLAR = new BlockBase("stayble_solar", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(0.75F).setResistance(2F);
	//misc
	public static final Block IRON_DECORATION_BLOCK = new BlockBase("iron_decoration_block", Material.IRON, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(4F);
	public static final Block IRON_WALL_BLOCK = new BlockBase("iron_wall_block", Material.IRON, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(4F);
	//mercury
	public static final Block MERCURY_TURF = new BlockBase("mercury_turf", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(0.75F).setResistance(2F);
	public static final Block MERCURY_DIRT = new BlockBase("mercury_dirt", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(3F);
	public static final Block MERCURY_ROCK = new BlockBase("mercury_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(6F);
	public static final Block MERCURY_ALUMINUM_ORE = new BlockBaseAluminumOre("mercury_aluminum_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MERCURY_COPPER_ORE = new BlockBaseCopperOre("mercury_copper_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MERCURY_DESH_ORE = new BlockBaseDeshOre("mercury_desh_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MERCURY_ILMENITE_ORE = new BlockBaseIlmeniteOre("mercury_ilmenite_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MERCURY_IRON_ORE = new BlockBaseIronOre("mercury_iron_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MERCURY_SILICON_ORE = new BlockBaseSiliconOre("mercury_silicon_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MERCURY_TIN_ORE = new BlockBaseTinOre("mercury_tin_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MERCURY_DUNGEON_BRICK = new BlockBase("mercury_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
	public static final Block TREASURE_CHEST_T4 = new BlockTier4TreasureChest("treasure_t4");
	//jupiter
	//io
	public static final Block IO_SURFACE_ROCK = new BlockBase("io_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block IO_SULFUR_BLOCK = new BlockBase("io_sulfur_block", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(2F);
	public static final Block IO_ASH_BLOCK = new BlockBase("io_ash_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(2F);
	public static final Block IO_SUB_SURFACE_ROCK = new BlockBase("io_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
	public static final Block IO_ROCK = new BlockBase("io_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
	public static final Block IO_ALUMINUM_ORE = new BlockBaseAluminumOre("io_aluminum_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block IO_COPPER_ORE = new BlockBaseCopperOre("io_copper_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block IO_IRON_ORE = new BlockBaseIronOre("io_iron_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block IO_SULFUR_ORE = new BlockBaseSulfurOre("io_sulfur_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	//europa
	public static final Block EUROPA_SURFACE_ROCK = new BlockBase("europa_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block EUROPA_SUB_SURFACE_ROCK = new BlockBase("europa_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
	public static final Block EUROPA_ROCK = new BlockBase("europa_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
	public static final Block EUROPA_ALUMINUM_ORE = new BlockBaseAluminumOre("europa_aluminum_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block EUROPA_COPPER_ORE = new BlockBaseCopperOre("europa_copper_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block EUROPA_IRON_ORE = new BlockBaseIronOre("europa_iron_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	//jupiter misc
	public static final Block JUPITER_DUNGEON_BRICK = new BlockBase("jupiter_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
	public static final Block TREASURE_CHEST_T5 = new BlockTier5TreasureChest("treasure_t5");
	//saturn
	//mimas
	public static final Block MIMAS_TURF = new BlockBase("mimas_turf", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(0.75F).setResistance(2F);
	public static final Block MIMAS_DIRT = new BlockBase("mimas_dirt", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(3F);
	public static final Block MIMAS_ROCK = new BlockBase("mimas_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(6F);
	public static final Block MIMAS_COPPER_ORE = new BlockBaseCopperOre("mimas_copper_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MIMAS_DESH_ORE = new BlockBaseDeshOre("mimas_desh_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MIMAS_IRON_ORE = new BlockBaseIronOre("mimas_iron_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MIMAS_MAGANESE_ORE = new BlockBaseManganeseOre("mimas_manganese_ore", Material.ROCK, "pickaxe", 2, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block MIMAS_TIN_ORE = new BlockBaseTinOre("mimas_tin_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	//titan
	public static final Block TITAN_SURFACE_ROCK = new BlockBase("titan_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block TITAN_SUB_SURFACE_ROCK = new BlockBase("titan_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
	public static final Block TITAN_ROCK = new BlockBase("titan_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
	public static final Block TITAN_ALUMINUM_ORE = new BlockBaseAluminumOre("titan_aluminum_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TITAN_DESH_ORE = new BlockBaseDeshOre("titan_desh_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TITAN_DIAMOND_ORE = new BlockBaseDiamondOre("titan_diamond_ore", Material.ROCK, "pickaxe", 3, TheSol.BLOCK_TAB).setHardness(3.75F).setResistance(8F);
	public static final Block TITAN_ILMENITE_ORE = new BlockBaseIlmeniteOre("titan_iron_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TITAN_MAGANESE_ORE = new BlockBaseManganeseOre("titan_manganese_ore", Material.ROCK, "pickaxe", 2, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block TITAN_TIN_ORE = new BlockBaseTinOre("titan_tin_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	//saturn misc
	public static final Block SATURN_DUNGEON_BRICK = new BlockBase("saturn_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
	public static final Block TREASURE_CHEST_T6 = new BlockTier6TreasureChest("treasure_t6");
	//uranus
	//ariel
	public static final Block ARIEL_TURF = new BlockBase("ariel_turf", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block ARIEL_DIRT = new BlockBase("ariel_dirt", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
	public static final Block ARIEL_ROCK = new BlockBase("ariel_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
	public static final Block ARIEL_ALUMINUM_ORE = new BlockBaseAluminumOre("ariel_aluminum_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block ARIEL_DESH_ORE = new BlockBaseDeshOre("ariel_desh_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block ARIEL_IRON_ORE = new BlockBaseIronOre("ariel_iron_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block ARIEL_ILMENITE_ORE = new BlockBaseIlmeniteOre("ariel_ilmenite_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block ARIEL_LITHIUM_ORE = new BlockBaseLithiumOre("ariel_lithium_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block ARIEL_TIN_ORE = new BlockBaseTinOre("ariel_tin_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block CARBON_DIOXIDE = new BlockGas("carbon_dioxide", TheSol.BLOCK_TAB).setHardness(0.0F).setResistance(0F);
	//uranus misc
	public static final Block URANUS_DUNGEON_BRICK = new BlockBase("uranus_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
	public static final Block TREASURE_CHEST_T7 = new BlockTier7TreasureChest("treasure_t7");
	//triton
	public static final Block TRITON_SOFT_ROCK = new BlockBase("triton_soft_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block TRITON_SURFACE_ROCK = new BlockBase("triton_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block TRITON_TURF = new BlockBase("triton_turf", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block TRITON_DIRT = new BlockBase("triton_dirt", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
	public static final Block TRITON_ROCK = new BlockBase("triton_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
	public static final Block TRITON_ALUMINUM_ORE = new BlockBaseAluminumOre("triton_aluminum_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TRITON_DESH_ORE = new BlockBaseDeshOre("triton_desh_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TRITON_IRON_ORE = new BlockBaseIronOre("triton_iron_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TRITON_ILMENITE_ORE = new BlockBaseIlmeniteOre("triton_ilmenite_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TRITON_LITHIUM_ORE = new BlockBaseLithiumOre("triton_lithium_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block TRITON_MAGNESIUM_ORE = new BlockBaseMagnesiumOre("triton_magnesium_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TRITON_MAGNET_ORE = new BlockBaseMagnetOre("triton_magnet_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.25F).setResistance(8F);
	public static final Block TRITON_SILICON_ORE = new BlockBaseSiliconOre("triton_silicon_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TRITON_TIN_ORE = new BlockBaseTinOre("triton_tin_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	//uranus misc
	public static final Block NEPTUNE_DUNGEON_BRICK = new BlockBase("neptune_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
	public static final Block TREASURE_CHEST_T8 = new BlockTier8TreasureChest("treasure_t8");
	//pluto
	public static final Block PLUTO_SURFACE_ROCK = new BlockBase("pluto_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block PLUTO_SUB_SURFACE_ROCK = new BlockBase("pluto_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
	public static final Block PLUTO_ROCK = new BlockBase("pluto_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
	public static final Block PLUTO_COPPER_ORE = new BlockBaseCopperOre("pluto_copper_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block PLUTO_IRON_ORE = new BlockBaseIronOre("pluto_iron_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block PLUTO_TIN_ORE = new BlockBaseTinOre("pluto_tin_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block PLUTO_VANADIUM_ORE = new BlockBaseVanadiumOre("pluto_vanadium_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(4F).setResistance(8F);
	public static final Block PLUTO_DUNGEON_BRICK = new BlockBase("pluto_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
	public static final Block TREASURE_CHEST_T9 = new BlockTier9TreasureChest("treasure_t9");
	//pluto
	public static final Block SEDNA_TURF = new BlockBase("sedna_turf", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block SEDNA_SURFACE_ROCK = new BlockBase("sedna_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block SEDNA_SUB_SURFACE_ROCK = new BlockBase("sedna_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
	public static final Block SEDNA_ROCK = new BlockBase("sedna_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
	public static final Block SEDNA_COPPER_ORE = new BlockBaseCopperOre("sedna_copper_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block SEDNA_IRON_ORE = new BlockBaseIronOre("sedna_iron_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block SEDNA_OSMIUM_ORE = new BlockBaseOsmiumOre("sedna_osmium_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block SEDNA_TIN_ORE = new BlockBaseTinOre("sedna_tin_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block SEDNA_VANADIUM_ORE = new BlockBaseVanadiumOre("sedna_vanadium_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(4F).setResistance(8F);
	public static final Block SEDNA_DUNGEON_BRICK = new BlockBase("sedna_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
	public static final Block TREASURE_CHEST_T10 = new BlockTier10TreasureChest("treasure_t10");
}