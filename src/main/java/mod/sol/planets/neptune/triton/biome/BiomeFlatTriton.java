package mod.sol.planets.neptune.triton.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeFlatTriton extends BiomeTriton
{
    public BiomeFlatTriton(BiomeProperties properties)
    {
        super(properties);
    }

    public void registerTypes(Biome b)
    {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
    }
}
