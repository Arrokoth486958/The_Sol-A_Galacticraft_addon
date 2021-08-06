package mod.sol.planets.neptune.triton.biome;

import java.util.Random;

import mod.sol.planets.neptune.triton.world.gen.ChunkProviderTriton;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import mod.sol.planets.mercury.world.gen.ChunkProviderMercury;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeTriton extends BiomeGenBaseGC
{
    public static final Biome tritonFlat = new BiomeFlatTriton(new BiomeProperties("Triton").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));

    BiomeTriton(BiomeProperties properties)
    {
        super(properties, true);
    }

    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        return getModdedBiomeDecorator(new BiomeDecoratorTriton());
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
