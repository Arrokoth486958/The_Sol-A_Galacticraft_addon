package mod.sol.init;

import mod.sol.init.SolBlocks;
import net.minecraftforge.oredict.OreDictionary;

public class SolOreDict 
{
	public static void registerOres()
	{
		//ore
		//aluminum
		OreDictionary.registerOre("oreAluminum", SolBlocks.MERCURY_ALUMINUM_ORE);
		OreDictionary.registerOre("oreAluminum", SolBlocks.IO_ALUMINUM_ORE);
		//aluminium
		OreDictionary.registerOre("oreAluminium", SolBlocks.MERCURY_ALUMINUM_ORE);
		OreDictionary.registerOre("oreAluminium", SolBlocks.IO_ALUMINUM_ORE);
		//naturalAluminum
		OreDictionary.registerOre("oreNaturalAluminum", SolBlocks.MERCURY_ALUMINUM_ORE);
		OreDictionary.registerOre("oreNaturalAluminum", SolBlocks.IO_ALUMINUM_ORE);
		//copper
		OreDictionary.registerOre("oreCopper", SolBlocks.MERCURY_COPPER_ORE);
		OreDictionary.registerOre("oreCopper", SolBlocks.IO_COPPER_ORE);
		//tin
		OreDictionary.registerOre("oreTin", SolBlocks.MERCURY_TIN_ORE);
		//sulfur
		OreDictionary.registerOre("oreSulfur", SolBlocks.IO_SULFUR_ORE);
		//ingot
		//sulfur
		OreDictionary.registerOre("ingotSulfur", SolItems.SULFUR_INGOT);
		//compressed
		//sulfur
		OreDictionary.registerOre("compressedSulfur", SolItems.COMPRESSED_SULFUR);
		//lithium
		OreDictionary.registerOre("compressedLithium", SolItems.COMPRESSED_LITHIUM);
		//shard
		//sulfur
		OreDictionary.registerOre("shardSulfur", SolItems.SULFUR_SHARD);
	}
}
