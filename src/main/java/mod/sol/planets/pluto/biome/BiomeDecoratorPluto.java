package mod.sol.planets.pluto.biome;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.blocks.BlockBasicMoon;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import mod.sol.init.SolBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;

import java.util.Random;

public class BiomeDecoratorPluto extends BiomeDecorator
{
    private World world;
    private Random randomGenerator;

    private WorldGenerator dirtGen;
    private WorldGenerator copperGen;
    private WorldGenerator ironGen;
    private WorldGenerator tinGen;
    private WorldGenerator vanadiumGen;

    public BiomeDecoratorPluto()
    {
        this.copperGen = new WorldGenMinableMeta(SolBlocks.PLUTO_COPPER_ORE, 6, 0, true, SolBlocks.PLUTO_ROCK, 0);
        this.ironGen = new WorldGenMinableMeta(SolBlocks.PLUTO_IRON_ORE, 6, 0, true, SolBlocks.PLUTO_ROCK, 0);
        this.tinGen = new WorldGenMinableMeta(SolBlocks.PLUTO_TIN_ORE, 6, 0, true, SolBlocks.PLUTO_ROCK, 0);
        this.vanadiumGen = new WorldGenMinableMeta(SolBlocks.PLUTO_VANADIUM_ORE, 4, 0, true, SolBlocks.PLUTO_ROCK, 0);
        this.dirtGen = new WorldGenMinableMeta(SolBlocks.PLUTO_SUB_SURFACE_ROCK, 10, 0, true, SolBlocks.PLUTO_ROCK, 0);
    }

    @Override
    public void decorate(World worldIn, Random random, Biome p_180292_3_, BlockPos pos)
    {
        if (this.world != null)
        { 
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.world = worldIn;
            this.randomGenerator = random;
            this.chunkPos = pos;
            this.generateMercury();
            this.world = null;
            this.randomGenerator = null;
        }
    }

    private void genStandardOre(int amountPerChunk, WorldGenerator worldGenerator, int minY, int maxY)
    {
        for (int var5 = 0; var5 < amountPerChunk; ++var5)
        {
            BlockPos blockpos = this.chunkPos.add(this.randomGenerator.nextInt(16), this.randomGenerator.nextInt(maxY - minY) + minY, this.randomGenerator.nextInt(16));
            worldGenerator.generate(this.world, this.randomGenerator, blockpos);
        }
    }

    private void generateMercury()
    {
        MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Pre(this.world, this.randomGenerator, chunkPos));
        this.genStandardOre(20, this.dirtGen, 0, 200);
        this.genStandardOre(16, this.copperGen, 0, 60);
        this.genStandardOre(14, this.ironGen, 0, 60);
        this.genStandardOre(16, this.tinGen, 0, 60);
        this.genStandardOre(14, this.vanadiumGen, 0, 60);
        MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Post(this.world, this.randomGenerator, chunkPos));
    }
}
