package mod.sol.planets.saturn.moons.titan.biome;

import micdoodle8.mods.galacticraft.planets.venus.VenusBlocks;
import micdoodle8.mods.galacticraft.planets.venus.blocks.BlockBasicVenus;
import mod.sol.init.SolBlocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

public class BiomeGenTitanMountain extends BiomeTitan
{
    public BiomeGenTitanMountain(BiomeProperties properties)
    {
        super(properties);
    }

    @Override
    public void registerTypes(Biome b)
    {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int p_180622_4_, int p_180622_5_, double p_180622_6_)
    {
        this.topBlock = SolBlocks.TITAN_SURFACE_ROCK.getDefaultState();
        this.fillerBlock = SolBlocks.TITAN_SUB_SURFACE_ROCK.getDefaultState();
        super.generateBiomeTerrainVenus(worldIn, rand, chunkPrimerIn, p_180622_4_, p_180622_5_, p_180622_6_);
    }
}
