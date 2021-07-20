package mod.sol.planets.jupiter.moons.io.layer;

import micdoodle8.mods.galacticraft.planets.venus.VenusModule;
import micdoodle8.mods.miccore.IntCache;
import mod.sol.TheSol;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerIoBiomes extends GenLayer
{
    private static final Biome[] biomes = TheSol.moonIo.biomesToGenerate.toArray(new Biome[0]);

    public GenLayerIoBiomes(long l, GenLayer parent)
    {
        super(l);
        this.parent = parent;
    }

    public GenLayerIoBiomes(long l)
    {
        super(l);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth)
    {
        int[] dest = IntCache.getIntCache(width * depth);

        for (int k = 0; k < depth; ++k)
        {
            for (int i = 0; i < width; ++i)
            {
                initChunkSeed(x + i, z + k);
                dest[i + k * width] = Biome.getIdForBiome(biomes[nextInt(biomes.length)]);
            }
        }

        return dest;
    }
}
