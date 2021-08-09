package mod.sol.planets.sedna.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeFlatSedna extends BiomeSedna
{
    public BiomeFlatSedna(BiomeProperties properties)
    {
        super(properties);
    }

    public void registerTypes(Biome b)
    {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
    }
}
