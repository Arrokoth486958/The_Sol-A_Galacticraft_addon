package mod.sol.planets.kuiper_belt.world.gen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.api.world.ChunkProviderBase;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.perlin.NoiseModule;
import micdoodle8.mods.galacticraft.core.perlin.generator.Billowed;
import micdoodle8.mods.galacticraft.core.perlin.generator.Gradient;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.planets.asteroids.ConfigManagerAsteroids;
import micdoodle8.mods.galacticraft.planets.asteroids.blocks.AsteroidBlocks;
import micdoodle8.mods.galacticraft.planets.asteroids.dimension.WorldProviderAsteroids;
import micdoodle8.mods.galacticraft.planets.asteroids.world.gen.base.MapGenAbandonedBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockFlower.EnumFlowerColor;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockTallGrass.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class ChunkProviderKuiperBelt extends ChunkProviderBase {
    final Block ASTEROID_STONE;
    final byte ASTEROID_STONE_META_0;
    final byte ASTEROID_STONE_META_1;
    final byte ASTEROID_STONE_META_2;
    final Block DIRT;
    final byte DIRT_META;
    final Block GRASS;
    final byte GRASS_META;
    final Block LIGHT;
    final byte LIGHT_META;
    EnumType GRASS_TYPE;
    final BlockFlower FLOWER;
    final Block LAVA;
    final byte LAVA_META;
    final Block WATER;
    final byte WATER_META;
    private final Random rand;
    private final World world;
    private final NoiseModule asteroidDensity;
    private final NoiseModule asteroidTurbulance;
    private final NoiseModule asteroidSkewX;
    private final NoiseModule asteroidSkewY;
    private final NoiseModule asteroidSkewZ;
    private final SpecialKuiperBeltBlockHandler coreHandler;
    private final SpecialKuiperBeltBlockHandler shellHandler;
    private static final int CHUNK_SIZE_X = 16;
    private static final int CHUNK_SIZE_Y = 256;
    private static final int CHUNK_SIZE_Z = 16;
    private static final int MAX_ASTEROID_RADIUS = 25;
    private static final int MIN_ASTEROID_RADIUS = 5;
    private static final int MAX_ASTEROID_SKEW = 8;
    private static final int MIN_ASTEROID_Y = 48;
    private static final int MAX_ASTEROID_Y = 208;
    private static final int ASTEROID_CHANCE = 800;
    private static final int ASTEROID_CORE_CHANCE = 2;
    private static final int ASTEROID_SHELL_CHANCE = 2;
    private static final int MIN_BLOCKS_PER_CHUNK = 50;
    private static final int MAX_BLOCKS_PER_CHUNK = 200;
    private static final int ILMENITE_CHANCE = 400;
    private static final int IRON_CHANCE = 300;
    private static final int ALUMINUM_CHANCE = 250;
    private static final int RANDOM_BLOCK_FADE_SIZE = 32;
    private static final int FADE_BLOCK_CHANCE = 5;
    private static final int NOISE_OFFSET_SIZE = 256;
    private static final float MIN_HOLLOW_SIZE = 0.6F;
    private static final float MAX_HOLLOW_SIZE = 0.8F;
    private static final int HOLLOW_CHANCE = 10;
    private static final int MIN_RADIUS_FOR_HOLLOW = 15;
    private static final float HOLLOW_LAVA_SIZE = 0.12F;
    private static final int TREE_CHANCE = 2;
    private static final int TALL_GRASS_CHANCE = 2;
    private static final int FLOWER_CHANCE = 2;
    private static final int WATER_CHANCE = 2;
    private static final int LAVA_CHANCE = 2;
    private static final int GLOWSTONE_CHANCE = 20;
    private LinkedList<ChunkProviderKuiperBelt.AsteroidData> largeAsteroids;
    private int largeCount;
    private static HashSet<BlockVec3> chunksDone = new HashSet();
    private int largeAsteroidsLastChunkX;
    private int largeAsteroidsLastChunkZ;
    private final MapGenAbandonedBase dungeonGenerator;

    public ChunkProviderKuiperBelt(World par1World, long par2, boolean par4) {
        this.ASTEROID_STONE = AsteroidBlocks.blockBasic;
        this.ASTEROID_STONE_META_0 = 0;
        this.ASTEROID_STONE_META_1 = 1;
        this.ASTEROID_STONE_META_2 = 2;
        this.DIRT = Blocks.DIRT;
        this.DIRT_META = 0;
        this.GRASS = Blocks.GRASS;
        this.GRASS_META = 0;
        this.LIGHT = Blocks.GLOWSTONE;
        this.LIGHT_META = 0;
        this.GRASS_TYPE = EnumType.GRASS;
        this.FLOWER = Blocks.RED_FLOWER;
        this.LAVA = Blocks.LAVA;
        this.LAVA_META = 0;
        this.WATER = Blocks.WATER;
        this.WATER_META = 0;
        this.largeAsteroids = new LinkedList();
        this.largeCount = 0;
        this.dungeonGenerator = new MapGenAbandonedBase();
        this.world = par1World;
        this.rand = new Random(par2);
        this.asteroidDensity = new Billowed(this.rand.nextLong(), 2, 0.25F);
        this.asteroidDensity.setFrequency(0.009F);
        this.asteroidDensity.amplitude = 0.6F;
        this.asteroidTurbulance = new Gradient(this.rand.nextLong(), 1, 0.2F);
        this.asteroidTurbulance.setFrequency(0.08F);
        this.asteroidTurbulance.amplitude = 0.5F;
        this.asteroidSkewX = new Gradient(this.rand.nextLong(), 1, 1.0F);
        this.asteroidSkewX.amplitude = 8.0F;
        this.asteroidSkewX.frequencyX = 0.005F;
        this.asteroidSkewY = new Gradient(this.rand.nextLong(), 1, 1.0F);
        this.asteroidSkewY.amplitude = 8.0F;
        this.asteroidSkewY.frequencyY = 0.005F;
        this.asteroidSkewZ = new Gradient(this.rand.nextLong(), 1, 1.0F);
        this.asteroidSkewZ.amplitude = 8.0F;
        this.asteroidSkewZ.frequencyZ = 0.005F;
        this.coreHandler = new SpecialKuiperBeltBlockHandler();
        SpecialKuiperBeltBlockHandler var10000 = this.coreHandler;
        Block var10003 = this.ASTEROID_STONE;
        this.getClass();
        var10000.addBlock(new SpecialKuiperBeltBlock(var10003, (byte)2, 5, 0.3D));
        var10000 = this.coreHandler;
        var10003 = this.ASTEROID_STONE;
        this.getClass();
        var10000.addBlock(new SpecialKuiperBeltBlock(var10003, (byte)1, 7, 0.3D));
        var10000 = this.coreHandler;
        var10003 = this.ASTEROID_STONE;
        this.getClass();
        var10000.addBlock(new SpecialKuiperBeltBlock(var10003, (byte)0, 11, 0.25D));
        if (!ConfigManagerAsteroids.disableAluminumGen) {
            this.coreHandler.addBlock(new SpecialKuiperBeltBlock(this.ASTEROID_STONE, (byte)3, 5, 0.2D));
        }

        if (!ConfigManagerAsteroids.disableIlmeniteGen) {
            this.coreHandler.addBlock(new SpecialKuiperBeltBlock(this.ASTEROID_STONE, (byte)4, 4, 0.15D));
        }

        if (!ConfigManagerAsteroids.disableIronGen) {
            this.coreHandler.addBlock(new SpecialKuiperBeltBlock(this.ASTEROID_STONE, (byte)5, 3, 0.2D));
        }

        if (ConfigManagerCore.enableSiliconOreGen) {
            this.coreHandler.addBlock(new SpecialKuiperBeltBlock(GCBlocks.basicBlock, (byte)8, 2, 0.15D));
        }

        this.coreHandler.addBlock(new SpecialKuiperBeltBlock(GCBlocks.basicBlock, (byte)12, 2, 0.13D));
        this.coreHandler.addBlock(new SpecialKuiperBeltBlock(Blocks.DIAMOND_ORE, (byte)0, 1, 0.1D));
        this.shellHandler = new SpecialKuiperBeltBlockHandler();
        var10000 = this.shellHandler;
        var10003 = this.ASTEROID_STONE;
        this.getClass();
        var10000.addBlock(new SpecialKuiperBeltBlock(var10003, (byte)0, 1, 0.15D));
        var10000 = this.shellHandler;
        var10003 = this.ASTEROID_STONE;
        this.getClass();
        var10000.addBlock(new SpecialKuiperBeltBlock(var10003, (byte)1, 3, 0.15D));
        var10000 = this.shellHandler;
        var10003 = this.ASTEROID_STONE;
        this.getClass();
        var10000.addBlock(new SpecialKuiperBeltBlock(var10003, (byte)2, 1, 0.15D));
        this.shellHandler.addBlock(new SpecialKuiperBeltBlock(AsteroidBlocks.blockDenseIce, (byte)0, 1, 0.15D));
    }

    public void generateTerrain(int chunkX, int chunkZ, ChunkPrimer primer, boolean flagDataOnly) {
        this.largeAsteroids.clear();
        this.largeCount = 0;
        Random random = new Random();
        boolean asteroidChance = true;
        boolean rangeY = true;
        boolean rangeSize = true;

        for(int i = chunkX - 3; i < chunkX + 3; ++i) {
            int minX = i * 16;
            int maxX = minX + 16;

            for(int k = chunkZ - 3; k < chunkZ + 3; ++k) {
                int minZ = k * 16;
                int maxZ = minZ + 16;

                for(int x = minX; x < maxX; x += 2) {
                    for(int z = minZ; z < maxZ; z += 2) {
                        if ((double)this.randFromPointPos(x, z) < ((double)this.asteroidDensity.getNoise((float)x, (float)z) + 0.4D) / 800.0D) {
                            random.setSeed((long)(x + z * 3067));
                            int y = random.nextInt(160) + 48;
                            int size = random.nextInt(20) + 5;
                            this.generateAsteroid(random, x, y, z, chunkX << 4, chunkZ << 4, size, primer, flagDataOnly);
                            ++this.largeCount;
                        }
                    }
                }
            }
        }

    }

    private void generateAsteroid(Random rand, int asteroidX, int asteroidY, int asteroidZ, int chunkX, int chunkZ, int size, ChunkPrimer primer, boolean flagDataOnly) {
        SpecialKuiperBeltBlock core = this.coreHandler.getBlock(rand, size);
        SpecialKuiperBeltBlock shell = null;
        if (rand.nextInt(2) == 0) {
            shell = this.shellHandler.getBlock(rand, size);
        }

        boolean isHollow = false;
        float hollowSize = rand.nextFloat() * 0.19999999F + 0.6F;
        if (rand.nextInt(10) == 0 && size >= 15) {
            isHollow = true;
            shell = new SpecialKuiperBeltBlock(AsteroidBlocks.blockDenseIce, (byte)0, 1, 0.15D);
        }

        ((WorldProviderAsteroids)this.world.provider).addAsteroid(asteroidX, asteroidY, asteroidZ, size, isHollow ? -1 : core.index);
        int xMin = this.clamp(Math.max(chunkX, asteroidX - size - 8 - 2) - chunkX, 0, 16);
        int zMin = this.clamp(Math.max(chunkZ, asteroidZ - size - 8 - 2) - chunkZ, 0, 16);
        int yMin = asteroidY - size - 8 - 2;
        int yMax = asteroidY + size + 8 + 2;
        int xMax = this.clamp(Math.min(chunkX + 16, asteroidX + size + 8 + 2) - chunkX, 0, 16);
        int zMax = this.clamp(Math.min(chunkZ + 16, asteroidZ + size + 8 + 2) - chunkZ, 0, 16);
        int xSize = xMax - xMin;
        int ySize = yMax - yMin;
        int zSize = zMax - zMin;
        if (xSize > 0 && ySize > 0 && zSize > 0) {
            float noiseOffsetX = this.randFromPoint(asteroidX, asteroidY, asteroidZ) * 256.0F + (float)chunkX;
            float noiseOffsetY = this.randFromPoint(asteroidX * 7, asteroidY * 11, asteroidZ * 13) * 256.0F;
            float noiseOffsetZ = this.randFromPoint(asteroidX * 17, asteroidY * 23, asteroidZ * 29) * 256.0F + (float)chunkZ;
            this.setOtherAxisFrequency(1.0F / ((float)size * 2.0F / 2.0F));
            float[] sizeXArray = new float[ySize * zSize];
            float[] sizeZArray = new float[xSize * ySize];
            float[] sizeYArray = new float[xSize * zSize];

            int x;
            int terrainY;
            for(x = 0; x < xSize; ++x) {
                x = x * zSize;
                float xxx = (float)x + noiseOffsetX;

                for(terrainY = 0; terrainY < zSize; ++terrainY) {
                    sizeYArray[x + terrainY] = this.asteroidSkewY.getNoise(xxx, (float)terrainY + noiseOffsetZ);
                }
            }

            ChunkProviderKuiperBelt.AsteroidData asteroidData = new ChunkProviderKuiperBelt.AsteroidData(isHollow, sizeYArray, xMin, zMin, xMax, zMax, zSize, size, asteroidX, asteroidY, asteroidZ);
            this.largeAsteroids.add(asteroidData);
            this.largeAsteroidsLastChunkX = chunkX;
            this.largeAsteroidsLastChunkZ = chunkZ;
            if (!flagDataOnly) {
                int terrainYY;
                int xx;
                float xxx;
                for(x = 0; x < ySize; ++x) {
                    xx = x * zSize;
                    xxx = (float)x + noiseOffsetY;

                    for(terrainYY = 0; terrainYY < zSize; ++terrainYY) {
                        sizeXArray[xx + terrainYY] = this.asteroidSkewX.getNoise(xxx, (float)terrainYY + noiseOffsetZ);
                    }
                }

                for(x = 0; x < xSize; ++x) {
                    xx = x * ySize;
                    xxx = (float)x + noiseOffsetX;

                    for(terrainYY = 0; terrainYY < ySize; ++terrainYY) {
                        sizeZArray[xx + terrainYY] = this.asteroidSkewZ.getNoise(xxx, (float)terrainYY + noiseOffsetY);
                    }
                }

                double shellThickness = 0.0D;
                terrainY = 0;
                terrainYY = 0;
                IBlockState asteroidShell = null;
                if (shell != null) {
                    asteroidShell = shell.block.getStateFromMeta(shell.meta);
                    shellThickness = 1.0D - shell.thickness;
                }

                IBlockState asteroidCore = core.block.getStateFromMeta(core.meta);
                Block var10000 = this.ASTEROID_STONE;
                this.getClass();
                IBlockState asteroidRock0 = var10000.getStateFromMeta(0);
                var10000 = this.ASTEROID_STONE;
                this.getClass();
                IBlockState asteroidRock1 = var10000.getStateFromMeta(1);
                IBlockState airBlock = Blocks.AIR.getDefaultState();
                var10000 = this.DIRT;
                this.getClass();
                IBlockState dirtBlock = var10000.getStateFromMeta(0);
                var10000 = this.GRASS;
                this.getClass();
                IBlockState grassBlock = var10000.getStateFromMeta(0);

                Block var10004;
                int indexXY;
                int indexXZ;
                int distanceX;
                int z;
                int y;
                float sizeZ;
                float distance;
                for(x = xMax - 1; x >= xMin; --x) {
                    indexXY = (x - xMin) * ySize - yMin;
                    indexXZ = (x - xMin) * zSize - zMin;
                    distanceX = asteroidX - (x + chunkX);
                    z = x * 256 << 4;
                    xx = (x + chunkX);

                    for(z = zMin; z < zMax; ++z) {
                        float sizeY;
                        if (isHollow) {
                            sizeY = sizeYArray[indexXZ + z];
                            terrainY = this.getTerrainHeightFor(sizeY, asteroidY, size);
                            terrainYY = this.getTerrainHeightFor(sizeY, asteroidY - 1, size);
                        }

                        sizeY = (float)size + sizeYArray[indexXZ + z];
                        sizeY *= sizeY;
                        y = asteroidZ - (z + chunkZ);
                        int indexBase = z | z * 256;
                        sizeZ = (float)(z + chunkZ);

                        for(y = yMin; y < yMax; ++y) {
                            distance = (float)distanceX / ((float)size + sizeXArray[(y - yMin) * zSize + z - zMin]);
                            float dSizeZ = (float)y / ((float)size + sizeZArray[indexXY + y]);
                            distance *= distance;
                            dSizeZ *= dSizeZ;
                            int distanceY = asteroidY - y;
                            distanceY *= distanceY;
                            distance = distance + (float)distanceY / sizeY + dSizeZ;
                            float distanceAbove = distance;
                            distance += this.asteroidTurbulance.getNoise(xx, (float)y, sizeZ);
                            int var68;
                            if (isHollow && distance <= hollowSize) {
                                distanceAbove += this.asteroidTurbulance.getNoise(xx, (float)(y + 1), sizeZ);
                                if (distanceAbove <= 1.0F && y - 1 == terrainYY) {
                                    var68 = indexBase | y + 1;
                                    int var10002 = y + 1;
                                    var10004 = this.LIGHT;
                                    this.getClass();
                                    primer.setBlockState(x, var10002, z, var10004.getStateFromMeta(0));
                                }
                            }

                            if (distance <= 1.0F) {
                                var68 = indexBase | y;
                                if (isHollow && distance <= hollowSize) {
                                    if (y == terrainY) {
                                        primer.setBlockState(x, y, z, grassBlock);
                                    } else if (y < terrainY) {
                                        primer.setBlockState(x, y, z, dirtBlock);
                                    } else {
                                        primer.setBlockState(x, y, z, airBlock);
                                    }
                                } else if ((double)distance <= core.thickness) {
                                    if (rand.nextBoolean()) {
                                        primer.setBlockState(x, y, z, asteroidCore);
                                    } else {
                                        primer.setBlockState(x, y, z, asteroidRock0);
                                    }
                                } else if (shell != null && (double)distance >= shellThickness) {
                                    primer.setBlockState(x, y, z, asteroidShell);
                                } else {
                                    primer.setBlockState(x, y, z, asteroidRock1);
                                }
                            }
                        }
                    }
                }

                if (isHollow) {
                    shellThickness = 0.0D;
                    if (shell != null) {
                        shellThickness = 1.0D - shell.thickness;
                    }

                    for(x = xMin; x < xMax; ++x) {
                        indexXY = (x - xMin) * ySize - yMin;
                        indexXZ = (x - xMin) * zSize - zMin;
                        distanceX = asteroidX - (x + chunkX);
                        distanceX *= distanceX;

                        for(z = zMin; z < zMax; ++z) {
                            float var69 = sizeYArray[indexXZ + z];
                            float sizeY = (float)size + sizeYArray[indexXZ + z];
                            sizeY *= sizeY;
                            int distanceZ = asteroidZ - (z + chunkZ);
                            distanceZ *= distanceZ;

                            for(y = yMin; y < yMax; ++y) {
                                float sizeX = (float)size + sizeXArray[(y - yMin) * zSize + z - zMin];
                                sizeZ = (float)size + sizeZArray[indexXY + y];
                                sizeX *= sizeX;
                                sizeZ *= sizeZ;
                                y = asteroidY - y;
                                y *= y;
                                distance = (float)distanceX / sizeX + (float)y / sizeY + (float)distanceZ / sizeZ;
                                distance += this.asteroidTurbulance.getNoise((float)(x + chunkX), (float)y, (float)(z + chunkZ));
                                if (distance <= 1.0F) {
                                    IBlockState state = primer.getBlockState(x, y, z);
                                    IBlockState stateAbove = primer.getBlockState(x, y + 1, z);
                                    if (Blocks.AIR == stateAbove.getBlock() && (state.getBlock() == this.ASTEROID_STONE || state.getBlock() == this.GRASS) && this.rand.nextInt(20) == 0) {
                                        var10004 = this.LIGHT;
                                        this.getClass();
                                        primer.setBlockState(x, y, z, var10004.getStateFromMeta(0));
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    private final void setOtherAxisFrequency(float frequency) {
        this.asteroidSkewX.frequencyY = frequency;
        this.asteroidSkewX.frequencyZ = frequency;
        this.asteroidSkewY.frequencyX = frequency;
        this.asteroidSkewY.frequencyZ = frequency;
        this.asteroidSkewZ.frequencyX = frequency;
        this.asteroidSkewZ.frequencyY = frequency;
    }

    private final int clamp(int x, int min, int max) {
        if (x < min) {
            x = min;
        } else if (x > max) {
            x = max;
        }

        return x;
    }

    private final double clamp(double x, double min, double max) {
        if (x < min) {
            x = min;
        } else if (x > max) {
            x = max;
        }

        return x;
    }

    private final int getTerrainHeightFor(float yMod, int asteroidY, int asteroidSize) {
        return (int)((float)(asteroidY - asteroidSize / 4) + yMod * 1.5F);
    }

    private final int getTerrainHeightAt(int x, int z, float[] yModArray, int xMin, int zMin, int zSize, int asteroidY, int asteroidSize) {
        int index = (x - xMin) * zSize - zMin;
        if (index < yModArray.length && index >= 0) {
            float yMod = yModArray[index];
            return this.getTerrainHeightFor(yMod, asteroidY, asteroidSize);
        } else {
            return 1;
        }
    }

    public Chunk generateChunk(int par1, int par2) {
        ChunkPrimer primer = new ChunkPrimer();
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        this.generateTerrain(par1, par2, primer, false);
        if (this.world.provider instanceof WorldProviderAsteroids && ((WorldProviderAsteroids)this.world.provider).checkHasAsteroids()) {
            this.dungeonGenerator.generate(this.world, par1, par2, primer);
        }

        Chunk var4 = new Chunk(this.world, primer, par1, par2);
        byte[] biomesArray = var4.getBiomeArray();
        byte b = (byte)Biome.getIdForBiome(BiomeAdaptive.biomeDefault);

        for(int i = 0; i < biomesArray.length; ++i) {
            biomesArray[i] = b;
        }

        this.generateSkylightMap(var4, par1, par2);
        return var4;
    }

    private int getIndex(int x, int y, int z) {
        return x * 256 * 16 | z * 256 | y;
    }

    private String timeString(long time1, long time2) {
        int ms100 = (int)((time2 - time1) / 10000L);
        int msdecimal = ms100 % 100;
        String msd = (ms100 < 10 ? "0" : "") + ms100;
        return "" + ms100 / 100 + "." + msd + "ms";
    }

    private float randFromPoint(int x, int y, int z) {
        int n = x + z * 57 + y * 571;
        n ^= n << 13;
        n = n * (n * n * 15731 + 789221) + 1376312589 & 2147483647;
        return 1.0F - (float)n / 1.07374182E9F;
    }

    private float randFromPoint(int x, int z) {
        int n = x + z * 57;
        n ^= n << 13;
        n = n * (n * n * 15731 + 789221) + 1376312589 & 2147483647;
        return 1.0F - (float)n / 1.07374182E9F;
    }

    private float randFromPointPos(int x, int z) {
        int n = x + z * 57;
        n ^= n << 13;
        n = n * (n * n * 15731 + 789221) + 1376312589 & 1073741823;
        return 1.0F - (float)n / 1.07374182E9F;
    }

    public void populate(int chunkX, int chunkZ) {
        int x = chunkX << 4;
        int z = chunkZ << 4;
        if (chunksDone.add(new BlockVec3(x, 0, z))) {
            this.rand.setSeed(this.world.getSeed());
            long var7 = this.rand.nextLong() / 2L * 2L + 1L;
            long var9 = this.rand.nextLong() / 2L * 2L + 1L;
            this.rand.setSeed((long)chunkX * var7 + (long)chunkZ * var9 ^ this.world.getSeed());
            int zMin;
            int i;
            int k;
            if (this.rand.nextBoolean()) {
                double density = (double)this.asteroidDensity.getNoise((float)(chunkX * 16), (float)(chunkZ * 16)) * 0.54D;
                double numOfBlocks = this.clamp((double)this.randFromPoint(chunkX, chunkZ), 0.4D, 1.0D) * 200.0D * density + 50.0D;
                zMin = this.rand.nextInt(2);
                int yRange = 160;
                x += 4;
                z += 4;

                for(i = 0; (double)i < numOfBlocks; ++i) {
                    i = this.rand.nextInt(yRange) + 48;
                    if (zMin == i / 16 % 2) {
                        k = x + this.rand.nextInt(16);
                        int pz = z + this.rand.nextInt(16);
                        Block block = this.ASTEROID_STONE;
                        this.getClass();
                        int meta = 1;
                        if (this.rand.nextInt(400) == 0) {
                            meta = 4;
                            if (ConfigManagerAsteroids.disableIlmeniteGen) {
                                continue;
                            }
                        } else if (this.rand.nextInt(300) == 0) {
                            meta = 5;
                            if (ConfigManagerAsteroids.disableIronGen) {
                                continue;
                            }
                        } else if (this.rand.nextInt(250) == 0) {
                            meta = 3;
                            if (ConfigManagerAsteroids.disableAluminumGen) {
                                continue;
                            }
                        }

                        this.world.setBlockState(new BlockPos(k, i, pz), block.getStateFromMeta(meta), 2);
                        boolean count = true;
                        if (!(this.world.getBlockState(new BlockPos(k - 1, i, pz)).getBlock() instanceof BlockAir)) {
                            count = true;
                        } else if (!(this.world.getBlockState(new BlockPos(k - 2, i, pz)).getBlock() instanceof BlockAir)) {
                            count = true;
                        } else if (!(this.world.getBlockState(new BlockPos(k - 3, i, pz)).getBlock() instanceof BlockAir)) {
                            count = true;
                        } else if (!(this.world.getBlockState(new BlockPos(k - 4, i, pz)).getBlock() instanceof BlockAir)) {
                            count = true;
                        }
                    }
                }
            }

            if (this.largeAsteroidsLastChunkX != chunkX || this.largeAsteroidsLastChunkZ != chunkZ) {
                this.generateTerrain(chunkX, chunkZ, (ChunkPrimer)null, true);
            }

            this.rand.setSeed((long)chunkX * var7 + (long)chunkZ * var9 ^ this.world.getSeed());
            if (!this.largeAsteroids.isEmpty()) {
                Iterator var25 = (new ArrayList(this.largeAsteroids)).iterator();

                label119:
                while(true) {
                    ChunkProviderKuiperBelt.AsteroidData asteroidIndex;
                    do {
                        if (!var25.hasNext()) {
                            break label119;
                        }

                        asteroidIndex = (ChunkProviderKuiperBelt.AsteroidData)var25.next();
                    } while(!asteroidIndex.isHollow);

                    float[] sizeYArray = asteroidIndex.sizeYArray;
                    int xMin = asteroidIndex.xMinArray;
                    zMin = asteroidIndex.zMinArray;
                    int zSize = asteroidIndex.zSizeArray;
                    int asteroidY = asteroidIndex.asteroidYArray;
                    int asteroidSize = asteroidIndex.asteroidSizeArray;
                    boolean treesdone = false;
                    if (ConfigManagerCore.challengeAsteroidPopulation || this.rand.nextInt(2) == 0) {
                        i = this.rand.nextInt(3);
                        if (i == 1) {
                            boolean var33 = false;
                        }

                        IBlockState log = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, net.minecraft.block.BlockPlanks.EnumType.OAK);
                        IBlockState leaves = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, net.minecraft.block.BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, false);
                        WorldGenTrees wg = new WorldGenTrees(false, 2, log, leaves, false);

                        for(int tries = 0; tries < 5; ++tries) {
                            i = this.rand.nextInt(16) + x + 8;
                            k = this.rand.nextInt(16) + z + 8;
                            if (wg.generate(this.world, this.rand, new BlockPos(i, this.getTerrainHeightAt(i - x, k - z, sizeYArray, xMin, zMin, zSize, asteroidY, asteroidSize), k))) {
                                break;
                            }
                        }

                        treesdone = true;
                    }

                    if (!treesdone || this.rand.nextInt(2) == 0) {
                        i = this.rand.nextInt(16) + x + 8;
                        k = this.rand.nextInt(16) + z + 8;
                        (new WorldGenTallGrass(this.GRASS_TYPE)).generate(this.world, this.rand, new BlockPos(i, this.getTerrainHeightAt(i - x, k - z, sizeYArray, xMin, zMin, zSize, asteroidY, asteroidSize), k));
                    }

                    if (this.rand.nextInt(2) == 0) {
                        i = this.rand.nextInt(16) + x + 8;
                        k = this.rand.nextInt(16) + z + 8;
                        int[] types = new int[]{2, 4, 5, 7};
                        (new WorldGenFlowers(this.FLOWER, EnumFlowerType.getType(EnumFlowerColor.RED, types[this.rand.nextInt(types.length)]))).generate(this.world, this.rand, new BlockPos(i, this.getTerrainHeightAt(i - x, k - z, sizeYArray, xMin, zMin, zSize, asteroidY, asteroidSize), k));
                    }

                    if (this.rand.nextInt(2) == 0) {
                        i = this.rand.nextInt(16) + x + 8;
                        k = this.rand.nextInt(16) + z + 8;
                        (new WorldGenLakes(this.LAVA)).generate(this.world, this.rand, new BlockPos(i, this.getTerrainHeightAt(i - x, k - z, sizeYArray, xMin, zMin, zSize, asteroidY, asteroidSize), k));
                    }

                    if (this.rand.nextInt(2) == 0) {
                        i = this.rand.nextInt(16) + x + 8;
                        k = this.rand.nextInt(16) + z + 8;
                        (new WorldGenLakes(this.WATER)).generate(this.world, this.rand, new BlockPos(i, this.getTerrainHeightAt(i - x, k - z, sizeYArray, xMin, zMin, zSize, asteroidY, asteroidSize), k));
                    }
                }
            }

            for(int xx = 0; xx < 16; ++xx) {
                int var10000 = x + xx;

                for(int zz = 0; zz < 16; ++zz) {
                    var10000 = z + zz;

                    for(zMin = 16; zMin < 240; ++zMin) {
                    }
                }
            }

            this.dungeonGenerator.generateStructure(this.world, this.rand, new ChunkPos(chunkX, chunkZ));
        }
    }

    public void recreateStructures(Chunk chunk, int x, int z) {
        this.dungeonGenerator.generate(this.world, x, z, (ChunkPrimer)null);
    }

    public void generateSkylightMap(Chunk chunk, int cx, int cz) {
        boolean flag = this.world.provider.hasSkyLight();

        int i;
        for(i = 0; i < 16; ++i) {
            if (chunk.getBlockStorageArray()[i] == null) {
                chunk.getBlockStorageArray()[i] = new ExtendedBlockStorage(i << 4, flag);
            }
        }

        i = chunk.getTopFilledSegment();
        chunk.setHeightMap(new int[2147483647]);
        //chunk.heightMapMinimum = 2147483647;

        int yMin;
        for(int j = 0; j < 16; ++j) {
            for(int k = 0; k < 16; ++k) {
                //chunk.precipitationHeightMap[j + (k << 4)] = -999;

                for(yMin = i + 15; yMin > 0; --yMin) {
                    if (chunk.getBlockLightOpacity(new BlockPos(j, yMin - 1, k)) != 0) {
                        //chunk.heightMap[k << 4 | j] = yMin;
                        if (yMin < chunk.getLowestHeight()) {
                        }
                        break;
                    }
                }
            }
        }

        Iterator var16 = this.largeAsteroids.iterator();

        while(var16.hasNext()) {
            ChunkProviderKuiperBelt.AsteroidData a = (ChunkProviderKuiperBelt.AsteroidData)var16.next();
            yMin = a.asteroidYArray - a.asteroidSizeArray;
            int yMax = a.asteroidYArray + a.asteroidSizeArray;
            int xMin = a.xMinArray;
            if (yMin < 0) {
                yMin = 0;
            }

            if (yMax > 255) {
                yMax = 255;
            }

            if (xMin == 0) {
                xMin = 1;
            }

            for(int x = a.xMax - 1; x >= xMin; --x) {
                for(int z = a.zMinArray; z < a.zMax; ++z) {
                    for(int y = yMin; y < yMax; ++y) {
                        if (chunk.getBlockState(x - 1, y, z).getBlock() instanceof BlockAir && !(chunk.getBlockState(x, y, z).getBlock() instanceof BlockAir)) {
                            int count = 2;
                            if (x > 1 && chunk.getBlockState(x - 2, y, z).getBlock() instanceof BlockAir) {
                                count += 2;
                            }

                            if (x > 2) {
                                if (chunk.getBlockState(x - 3, y, z).getBlock() instanceof BlockAir) {
                                    count += 2;
                                }

                                if (chunk.getBlockState(x - 3, y + 1, z).getBlock() instanceof BlockAir) {
                                    ++count;
                                }

                                if (chunk.getBlockState(x - 3, y + 1, z).getBlock() instanceof BlockAir) {
                                    ++count;
                                }

                                if (z > 0 && chunk.getBlockState(x - 3, y, z - 1).getBlock() instanceof BlockAir) {
                                    ++count;
                                }

                                if (z < 15 && chunk.getBlockState(x - 3, y, z + 1).getBlock() instanceof BlockAir) {
                                    ++count;
                                }
                            }

                            if (x > 3) {
                                if (chunk.getBlockState(x - 4, y, z).getBlock() instanceof BlockAir) {
                                    count += 2;
                                }

                                if (chunk.getBlockState(x - 4, y + 1, z).getBlock() instanceof BlockAir) {
                                    ++count;
                                }

                                if (chunk.getBlockState(x - 4, y + 1, z).getBlock() instanceof BlockAir) {
                                    ++count;
                                }

                                if (z > 0 && !(chunk.getBlockState(x - 4, y, z - 1).getBlock() instanceof BlockAir)) {
                                    ++count;
                                }

                                if (z < 15 && !(chunk.getBlockState(x - 4, y, z + 1).getBlock() instanceof BlockAir)) {
                                    ++count;
                                }
                            }

                            if (count > 12) {
                                count = 12;
                            }

                            if (count > 12) {
                                count = 12;
                            }

                            chunk.setBlockState(new BlockPos(x - 1, y, z), GCBlocks.brightAir.getStateFromMeta(13 - count));
                            ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[y >> 4];
                            if (extendedblockstorage != null) {
                                extendedblockstorage.setBlockLight(x - 1, y & 15, z, count + 2);
                            }
                        }
                    }
                }
            }
        }

        chunk.setModified(true);
    }

    public void resetBase() {
        this.dungeonGenerator.reset();
    }

    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.world.getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    public BlockVec3 isLargeAsteroidAt(int x0, int z0) {
        for(int i0 = 0; i0 <= 32; ++i0) {
            for(int i1 = -i0; i1 <= i0; ++i1) {
                int xToCheck = (x0 >> 4) + i0;
                int zToCheck = (z0 >> 4) + i1;
                if (this.isLargeAsteroidAt0(xToCheck * 16, zToCheck * 16)) {
                    return new BlockVec3(xToCheck * 16, 0, zToCheck * 16);
                }

                xToCheck = (x0 >> 4) + i0;
                zToCheck = (z0 >> 4) - i1;
                if (this.isLargeAsteroidAt0(xToCheck * 16, zToCheck * 16)) {
                    return new BlockVec3(xToCheck * 16, 0, zToCheck * 16);
                }

                xToCheck = (x0 >> 4) - i0;
                zToCheck = (z0 >> 4) + i1;
                if (this.isLargeAsteroidAt0(xToCheck * 16, zToCheck * 16)) {
                    return new BlockVec3(xToCheck * 16, 0, zToCheck * 16);
                }

                xToCheck = (x0 >> 4) - i0;
                zToCheck = (z0 >> 4) - i1;
                if (this.isLargeAsteroidAt0(xToCheck * 16, zToCheck * 16)) {
                    return new BlockVec3(xToCheck * 16, 0, zToCheck * 16);
                }
            }
        }

        return null;
    }

    private boolean isLargeAsteroidAt0(int x0, int z0) {
        for(int x = x0; x < x0 + 16; x += 2) {
            for(int z = z0; z < z0 + 16; z += 2) {
                if ((double)Math.abs(this.randFromPoint(x, z)) < ((double)this.asteroidDensity.getNoise((float)x, (float)z) + 0.4D) / 800.0D) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void reset() {
        chunksDone.clear();
    }

    private class AsteroidData {
        public boolean isHollow;
        public float[] sizeYArray;
        public int xMinArray;
        public int zMinArray;
        public int xMax;
        public int zMax;
        public int zSizeArray;
        public int asteroidSizeArray;
        public int asteroidXArray;
        public int asteroidYArray;
        public int asteroidZArray;

        public AsteroidData(boolean hollow, float[] sizeYArray2, int xMin, int zMin, int xmax, int zmax, int zSize, int size, int asteroidX, int asteroidY, int asteroidZ) {
            this.isHollow = hollow;
            this.sizeYArray = (float[])sizeYArray2.clone();
            this.xMinArray = xMin;
            this.zMinArray = zMin;
            this.xMax = xmax;
            this.zMax = zmax;
            this.zSizeArray = zSize;
            this.asteroidSizeArray = size;
            this.asteroidXArray = asteroidX;
            this.asteroidYArray = asteroidY;
            this.asteroidZArray = asteroidZ;
        }
    }
}
