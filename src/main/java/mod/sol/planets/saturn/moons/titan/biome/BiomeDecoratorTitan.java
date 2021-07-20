package mod.sol.planets.saturn.moons.titan.biome;

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

public class BiomeDecoratorTitan extends BiomeDecorator
{
    private World world;
    private Random randomGenerator;

    private WorldGenerator dirtGen;
    private WorldGenerator aluminumGen;
    private WorldGenerator deshGen;
    private WorldGenerator ilmeniteGen;
    private WorldGenerator maganeseGen;
    private WorldGenerator tinGen;

    public BiomeDecoratorTitan()
    {
        this.aluminumGen = new WorldGenMinableMeta(SolBlocks.TITAN_ALUMINUM_ORE, 6, 0, true, SolBlocks.TITAN_ROCK, 0);
        this.deshGen = new WorldGenMinableMeta(SolBlocks.TITAN_DESH_ORE, 6, 0, true, SolBlocks.TITAN_ROCK, 0);
        this.ilmeniteGen = new WorldGenMinableMeta(SolBlocks.TITAN_ILMENITE_ORE, 6, 0, true, SolBlocks.TITAN_ROCK, 0);
        this.maganeseGen = new WorldGenMinableMeta(SolBlocks.TITAN_MAGANESE_ORE, 4, 0, true, SolBlocks.TITAN_ROCK, 0);
        this.tinGen = new WorldGenMinableMeta(SolBlocks.TITAN_TIN_ORE, 6, 0, true, SolBlocks.TITAN_ROCK, 0);
        this.dirtGen = new WorldGenMinableMeta(SolBlocks.TITAN_SUB_SURFACE_ROCK, 10, 0, true, SolBlocks.TITAN_ROCK, 0);
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
            this.generateIo();
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

    private void generateIo()
    {
        MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Pre(this.world, this.randomGenerator, chunkPos));
        this.genStandardOre(20, this.dirtGen, 0, 200);
        this.genStandardOre(20, this.aluminumGen, 0, 100);
        this.genStandardOre(16, this.deshGen, 0, 80);
        this.genStandardOre(22, this.ilmeniteGen, 0, 120);
        this.genStandardOre(14, this.maganeseGen, 0, 40);
        this.genStandardOre(20, this.tinGen, 0, 100);
        MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Post(this.world, this.randomGenerator, chunkPos));
    }
}
