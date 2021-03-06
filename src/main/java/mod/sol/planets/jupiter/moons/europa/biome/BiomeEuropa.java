package mod.sol.planets.jupiter.moons.europa.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import mod.sol.init.SolBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

public class BiomeEuropa extends BiomeGenBaseGC
{
    public static final Biome europaFlat = new BiomeGenFlatEuropa(new BiomeProperties("Europa Flat").setRainfall(0.0F).setRainDisabled().setBaseHeight(0.6F).setHeightVariation(0.4F).setTemperature(4.0F));
    public static final Biome europaMountain = new BiomeGenEuropaMountain(new BiomeProperties("Europa Mountain").setRainfall(0.0F).setRainDisabled().setBaseHeight(1.7F).setHeightVariation(1.0F).setTemperature(4.0F));
    public static final Biome europaValley = new BiomeGenEuropaValley(new BiomeProperties("Europa Valley").setRainfall(0.0F).setRainDisabled().setBaseHeight(-0.4F).setHeightVariation(0.2F).setTemperature(4.0F));

    BiomeEuropa(BiomeProperties properties)
    {
        super(properties, true);
    }

    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        return new BiomeDecoratorEuropa();
    }

    @Override
    public float getSpawningChance()
    {
        return 0.01F;
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int p_180622_4_, int p_180622_5_, double p_180622_6_)
    {
    }

    public final void generateBiomeTerrainVenus(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int p_180628_4_, int p_180628_5_, double p_180628_6_)
    {
        int i = worldIn.getSeaLevel();
        IBlockState topBlock = this.topBlock;
        IBlockState fillerBlock = this.fillerBlock;
        IBlockState stoneBlock = SolBlocks.EUROPA_ROCK.getDefaultState();
        int j = -1;
        int k = (int)(p_180628_6_ / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = p_180628_4_ & 15;
        int i1 = p_180628_5_ & 15;

        for (int j1 = 255; j1 >= 0; --j1)
        {
            if (j1 <= rand.nextInt(5))
            {
                chunkPrimerIn.setBlockState(i1, j1, l, Blocks.BEDROCK.getDefaultState());
            }
            else
            {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

                if (iblockstate2.getMaterial() == Material.AIR)
                {
                    j = -1;
                }
                else if (iblockstate2.getBlock() == SolBlocks.EUROPA_ROCK || iblockstate2.getBlock() == SolBlocks.EUROPA_SUB_SURFACE_ROCK || iblockstate2.getBlock() == SolBlocks.EUROPA_SURFACE_ROCK)
                {
                    if (j == -1)
                    {
                        if (k <= 0)
                        {
                            topBlock = null;
                            fillerBlock = stoneBlock;
                        }
                        else if (j1 >= i - 4 && j1 <= i + 1)
                        {
                            topBlock = this.topBlock;
                            fillerBlock = this.fillerBlock;
                        }

                        j = k;

                        if (j1 >= i - 1)
                        {
                            chunkPrimerIn.setBlockState(i1, j1, l, topBlock);
                        }
                        else if (j1 < i - 7 - k)
                        {
                            topBlock = null;
                            fillerBlock = stoneBlock;
                        }
                        else
                        {
                            chunkPrimerIn.setBlockState(i1, j1, l, fillerBlock);
                        }
                    }
                    else if (j > 0)
                    {
                        --j;
                        chunkPrimerIn.setBlockState(i1, j1, l, fillerBlock);
                    }
                }
            }
        }
    }
}
