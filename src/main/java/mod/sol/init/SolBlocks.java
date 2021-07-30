package mod.sol.init;

import java.util.ArrayList;
import java.util.List;

import mod.sol.TheSol;
import mod.sol.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SolBlocks 
{
	public static final List<Block> Blocks = new ArrayList<Block>();
	//spawner
	public static Block BOSS_SPAWNER_MERCURY = new BlockBossSpawnerMercury("boss_spawner_mercury");
	public static Block BOSS_SPAWNER_JUPITER = new BlockBossSpawnerJupiter("boss_spawner_jupiter");
	public static Block BOSS_SPAWNER_SATURN = new BlockBossSpawnerSaturn("boss_spawner_saturn");
	public static Block BOSS_SPAWNER_URANUS = new BlockBossSpawnerUranus("boss_spawner_uranus");
	//fluid
	public static final Block METHANE_FLUID_BLOCK = new BlockFluidBase("liquid_methane", SolFluid.METHANE, Material.WATER);
	//machine
	//public static final Block STABLE_SOLAR = new BlockBase("stayble_solar", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(0.75F).setResistance(2F);
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
	public static final Block JUPITER_DUNGEON_BRICK = new BlockBase("jupiter_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
	public static final Block TREASURE_CHEST_T5 = new BlockTier5TreasureChest("treasure_t5");
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
	//saturn
	public static final Block SATURN_DUNGEON_BRICK = new BlockBase("saturn_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
	public static final Block TREASURE_CHEST_T6 = new BlockTier6TreasureChest("treasure_t6");
	//mimas
	public static final Block MIMAS_TURF = new BlockBase("mimas_turf", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(0.75F).setResistance(2F);
	public static final Block MIMAS_DIRT = new BlockBase("mimas_dirt", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(3F);
	public static final Block MIMAS_ROCK = new BlockBase("mimas_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(6F);
	public static final Block MIMAS_COPPER_ORE = new BlockBaseCopperOre("mimas_copper_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MIMAS_DESH_ORE = new BlockBaseDeshOre("mimas_desh_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MIMAS_IRON_ORE = new BlockBaseIronOre("mimas_iron_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block MIMAS_MAGANESE_ORE = new BlockBaseManganeseOre("mimas_manganese_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block MIMAS_TIN_ORE = new BlockBaseTinOre("mimas_tin_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	//titan
	public static final Block TITAN_SURFACE_ROCK = new BlockBase("titan_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block TITAN_SUB_SURFACE_ROCK = new BlockBase("titan_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
	public static final Block TITAN_ROCK = new BlockBase("titan_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
	public static final Block TITAN_ALUMINUM_ORE = new BlockBaseAluminumOre("titan_aluminum_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TITAN_DESH_ORE = new BlockBaseDeshOre("titan_desh_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TITAN_ILMENITE_ORE = new BlockBaseIlmeniteOre("titan_iron_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	public static final Block TITAN_MAGANESE_ORE = new BlockBaseManganeseOre("titan_manganese_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block TITAN_TIN_ORE = new BlockBaseTinOre("titan_tin_ore", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(8F);
	//uranus
	public static final Block URANUS_DUNGEON_BRICK = new BlockBase("uranus_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
	public static final Block TREASURE_CHEST_T7 = new BlockTier7TreasureChest("treasure_t7");
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
	//pluto
	public static final Block PLUTO_SURFACE_ROCK = new BlockBase("pluto_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
	public static final Block PLUTO_SUB_SURFACE_ROCK = new BlockBase("pluto_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
	public static final Block PLUTO_ROCK = new BlockBase("pluto_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
	public static final Block PLUTO_COPPER_ORE = new BlockBase("pluto_copper_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block PLUTO_IRON_ORE = new BlockBase("pluto_iron_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	public static final Block PLUTO_TIN_ORE = new BlockBase("pluto_tin_ore", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(3.5F).setResistance(6F);
	//misc
}