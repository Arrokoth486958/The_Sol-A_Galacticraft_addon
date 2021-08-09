package mod.sol.planets.sedna.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeSedna extends BiomeGenBaseGC
{
    public static final Biome sednaFlat = new BiomeFlatSedna(new BiomeProperties("Sedna").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));

    BiomeSedna(BiomeProperties properties)
    {
        super(properties, true);
    }

    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        return getModdedBiomeDecorator(new BiomeDecoratorSedna());
    }

    @Override
    public float getSpawningChance()
    {
        return 0.1F;
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        super.genTerrainBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }
}
