package mod.sol.planets.neptune.triton.world.gen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import micdoodle8.mods.galacticraft.api.world.ChunkProviderBase;
import micdoodle8.mods.galacticraft.core.perlin.NoiseModule;
import micdoodle8.mods.galacticraft.core.perlin.generator.Gradient;
import micdoodle8.mods.galacticraft.core.world.gen.EnumCraterSize;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonConfiguration;
import mod.sol.init.SolBlocks;
import mod.sol.planets.uranus.world.gen.MapGenDungeonUranus;
import mod.sol.planets.uranus.world.gen.RoomBossUranus;
import mod.sol.planets.uranus.world.gen.RoomTreasureUranus;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.List;
import java.util.Random;

public class ChunkProviderTriton extends ChunkProviderBase
{
    public static final IBlockState BLOCK_TOP_DEFAULT = SolBlocks.TRITON_TURF.getDefaultState();
    public static final IBlockState BLOCK_TOP_LIGHT = SolBlocks.TRITON_SOFT_ROCK.getDefaultState();
    public static final IBlockState BLOCK_TOP_DARK = SolBlocks.TRITON_SURFACE_ROCK.getDefaultState();
    public static final IBlockState BLOCK_FILL = SolBlocks.TRITON_DIRT.getDefaultState();
    public static final IBlockState BLOCK_LOWER = SolBlocks.TRITON_ROCK.getDefaultState();

    private final Random rand;

    private final NoiseModule noiseGen1;
    private final NoiseModule noiseGen2;
    private final NoiseModule noiseGen3;
    private final NoiseModule noiseGen4;

    private final World world;

    private final MapGenDungeonUranus dungeonGenerator = new MapGenDungeonUranus(new DungeonConfiguration(SolBlocks.URANUS_DUNGEON_BRICK.getDefaultState(), 25, 8, 16, 5, 6, RoomBossUranus.class, RoomTreasureUranus.class));

    private Biome[] biomesForGeneration = { BiomeAdaptive.biomeDefault };

    private final MapGenBaseMeta caveGenerator = new MapGenCavesTriton();

    private static final int CRATER_PROB = 300;

    // DO NOT CHANGE
    private static final int MID_HEIGHT = 63;
    private static final int CHUNK_SIZE_X = 16;
    private static final int CHUNK_SIZE_Y = 128;
    private static final int CHUNK_SIZE_Z = 16;

    public ChunkProviderTriton(World par1World, long par2, boolean par4)
    {
        this.world = par1World;
        this.rand = new Random(par2);
        this.noiseGen1 = new Gradient(this.rand.nextLong(), 4, 0.20F);
        this.noiseGen2 = new Gradient(this.rand.nextLong(), 4, 0.20F);
        this.noiseGen3 = new Gradient(this.rand.nextLong(), 1, 0.20F);
        this.noiseGen4 = new Gradient(this.rand.nextLong(), 1, 0.20F);
    }

    public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer)
    {
        this.noiseGen1.setFrequency(0.0125F);
        this.noiseGen2.setFrequency(0.010F);
        this.noiseGen3.setFrequency(0.0075F);
        this.noiseGen4.setFrequency(0.01F);

        for (int x = 0; x < ChunkProviderTriton.CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < ChunkProviderTriton.CHUNK_SIZE_Z; z++)
            {
                final double d = this.noiseGen1.getNoise(x + chunkX * 16, z + chunkZ * 16) * 48;
                final double d2 = this.noiseGen2.getNoise(x + chunkX * 16, z + chunkZ * 16) * 64;
                double d3 = this.noiseGen3.getNoise(x + chunkX * 16, z + chunkZ * 16) - 0.1;
                d3 *= 8;

                double yDev;

                if (d3 < 0.0D)
                {
                    yDev = d;
                }
                else if (d3 > 1.0D)
                {
                    yDev = d2;
                }
                else
                {
                    yDev = d + (d2 - d) * d3;
                }

                for (int y = 0; y < ChunkProviderTriton.CHUNK_SIZE_Y; y++)
                {
                    if (y < ChunkProviderTriton.MID_HEIGHT + yDev)
                    {
                        primer.setBlockState(x, y, z, BLOCK_LOWER);
                    }
                }
            }
        }
    }

    public void replaceBlocksForBiome(int par1, int par2, ChunkPrimer primer, Biome[] par4ArrayOfBiome)
    {
        final int var5 = 20;
        for (int var8 = 0; var8 < 16; ++var8)
        {
            for (int var9 = 0; var9 < 16; ++var9)
            {
                final int var12 = (int) (this.noiseGen4.getNoise(var8 + par1 * 16, var9 * par2 * 16) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var13 = -1;
                IBlockState state0 = BLOCK_TOP_DEFAULT;
                IBlockState state1 = BLOCK_FILL;

                for (int var16 = 127; var16 >= 0; --var16)
                {
                    if (var16 >= rand.nextInt(4) + 60)
                        state0 = BLOCK_TOP_LIGHT;
                    else if (var16 >= rand.nextInt(6) + 52)
                        state0 = BLOCK_TOP_DARK;
                    else
                        state0 = BLOCK_TOP_DEFAULT;
                    final int index = this.getIndex(var8, var16, var9);

                    if (var16 <= this.rand.nextInt(5))
                    {
                        primer.setBlockState(var8, var16, var9, Blocks.BEDROCK.getDefaultState());
                    }
                    else
                    {
                        IBlockState var18 = primer.getBlockState(var8, var16, var9);
                        if (Blocks.AIR == var18.getBlock())
                        {
                            var13 = -1;
                        }
                        else if (var18 == BLOCK_LOWER)
                        {
                            if (var13 == -1)
                            {
                                if (var12 <= 0)
                                {
                                    state0 = Blocks.AIR.getDefaultState();
                                    state1 = BLOCK_LOWER;
                                }
                                else if (var16 >= var5 - -16 && var16 <= var5 + 1)
                                {
                                    state0 = BLOCK_FILL;
                                }

                                var13 = var12;

                                if (var16 >= var5 - 1)
                                {
                                    primer.setBlockState(var8, var16, var9, state0);
                                }
                                else if (var16 < var5 - 1 && var16 >= var5 - 2)
                                {
                                    primer.setBlockState(var8, var16, var9, state1);
                                }
                            }
                            else if (var13 > 0)
                            {
                                --var13;
                                primer.setBlockState(var8, var16, var9, state1);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Chunk generateChunk(int x, int z)
    {
        this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(x, z, chunkprimer);
        this.createCraters(x, z, chunkprimer);
        this.replaceBlocksForBiome(x, z, chunkprimer, null);

        this.caveGenerator.generate(this.world, x, z, chunkprimer);

        this.dungeonGenerator.generate(this.world, x, z, chunkprimer);

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();
        final byte b = (byte) Biome.getIdForBiome( BiomeAdaptive.biomeDefault );
        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = b;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void createCraters(int chunkX, int chunkZ, ChunkPrimer primer)
    {
        for (int cx = chunkX - 2; cx <= chunkX + 2; cx++)
        {
            for (int cz = chunkZ - 2; cz <= chunkZ + 2; cz++)
            {
                for (int x = 0; x < ChunkProviderTriton.CHUNK_SIZE_X; x++)
                {
                    for (int z = 0; z < ChunkProviderTriton.CHUNK_SIZE_Z; z++)
                    {
                        if (Math.abs(this.randFromPoint(cx * 16 + x, (cz * 16 + z) * 1000)) < this.noiseGen4.getNoise(x * ChunkProviderTriton.CHUNK_SIZE_X + x, cz * ChunkProviderTriton.CHUNK_SIZE_Z + z) / ChunkProviderTriton.CRATER_PROB)
                        {
                            final Random random = new Random(cx * 16 + x + (cz * 16 + z) * 5000);
                            final EnumCraterSize cSize = EnumCraterSize.sizeArray[random.nextInt(EnumCraterSize.sizeArray.length)];
                            final int size = random.nextInt(cSize.MAX_SIZE - cSize.MIN_SIZE) + cSize.MIN_SIZE;
                            this.makeCrater(cx * 16 + x, cz * 16 + z, chunkX * 16, chunkZ * 16, size, primer);
                        }
                    }
                }
            }
        }
    }

    private void makeCrater(int craterX, int craterZ, int chunkX, int chunkZ, int size, ChunkPrimer primer)
    {
        for (int x = 0; x < ChunkProviderTriton.CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < ChunkProviderTriton.CHUNK_SIZE_Z; z++)
            {
                double xDev = craterX - (chunkX + x);
                double zDev = craterZ - (chunkZ + z);
                if (xDev * xDev + zDev * zDev < size * size)
                {
                    xDev /= size;
                    zDev /= size;
                    final double sqrtY = xDev * xDev + zDev * zDev;
                    double yDev = sqrtY * sqrtY * 6;
                    yDev = 5 - yDev;
                    int helper = 0;
                    for (int y = 127; y > 0; y--)
                    {
                        if (Blocks.AIR != primer.getBlockState(x, y, z).getBlock() && helper <= yDev)
                        {
                            primer.setBlockState(x, y, z, Blocks.AIR.getDefaultState());
                            helper++;
                        }
                        if (helper > yDev)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }

    private int getIndex(int x, int y, int z)
    {
        return (x * 16 + z) * 256 + y;
    }

    private double randFromPoint(int x, int z)
    {
        int n;
        n = x + z * 57;
        n = n << 13 ^ n;
        return 1.0 - (n * (n * n * 15731 + 789221) + 1376312589 & 0x7fffffff) / 1073741824.0;
    }

    @Override
    public void populate(int x, int z)
    {
        BlockFalling.fallInstantly = true;
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biomegenbase = this.world.getBiome(blockpos.add(16, 0, 16));
        this.rand.setSeed(this.world.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) x * k + (long) z * l ^ this.world.getSeed());

        this.dungeonGenerator.generateStructure(this.world, this.rand, new ChunkPos(x, z));

        biomegenbase.decorate(this.world, this.rand, new BlockPos(i, 0, j));
        BlockFalling.fallInstantly = false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        Biome biomegenbase = this.world.getBiome(pos);
        return biomegenbase.getSpawnableList(creatureType);
    }

    @Override
    public void recreateStructures(Chunk chunk, int x, int z)
    {
        this.dungeonGenerator.generate(this.world, x, z, null);
    }
}
