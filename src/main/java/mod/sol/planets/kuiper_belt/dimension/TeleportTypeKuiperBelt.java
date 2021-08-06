package mod.sol.planets.kuiper_belt.dimension;

import java.util.Random;
import micdoodle8.mods.galacticraft.api.entity.IRocketType.EnumRocketType;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.planets.asteroids.blocks.AsteroidBlocks;
import micdoodle8.mods.galacticraft.planets.asteroids.entities.EntityEntryPod;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.blocks.MarsBlocks;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.ChunkProviderServer;

public class TeleportTypeKuiperBelt implements ITeleportType {
    public TeleportTypeKuiperBelt() {
    }

    public boolean useParachute() {
        return false;
    }

    public Vector3 getPlayerSpawnLocation(WorldServer world, EntityPlayerMP player) {
        if (player == null) {
            GCLog.severe("Null player when teleporting to Kuiper Belt!");
            return new Vector3(0.0D, 310.0D, 0.0D);
        } else {
            GCPlayerStats stats = GCPlayerStats.get(player);
            int x = MathHelper.floor(stats.getCoordsTeleportedFromX());
            int z = MathHelper.floor(stats.getCoordsTeleportedFromZ());
            int limit = ConfigManagerCore.otherPlanetWorldBorders - 2;
            if (limit > 20) {
                if (x > limit) {
                    z *= limit / x;
                    x = limit;
                } else if (x < -limit) {
                    z *= -limit / x;
                    x = -limit;
                }

                if (z > limit) {
                    x *= limit / z;
                    z = limit;
                } else if (z < -limit) {
                    x *= -limit / z;
                    z = -limit;
                }
            }

            int attemptCount = 0;
            this.preGenChunks(world, x >> 4, z >> 4);

            do {
                BlockVec3 bv3 = null;
                if (world.provider instanceof WorldProviderKuiperBelt) {
                    bv3 = ((WorldProviderKuiperBelt)world.provider).getClosestAsteroidXZ(x, 0, z, true);
                }

                if (bv3 != null) {
                    if (bv3.distanceSquared(new BlockVec3(x, 128, z)) > 25600) {
                        break;
                    }

                    if (ConfigManagerCore.enableDebug) {
                        GCLog.info("Testing asteroid at x" + bv3.x + " y" + bv3.y + " z" + bv3.z);
                    }

                    this.loadChunksAround(bv3.x, bv3.z, 2, world.getChunkProvider());
                    this.loadChunksAround(bv3.x, bv3.z, -3, world.getChunkProvider());
                    if (this.goodAsteroidEntry(world, bv3.x, bv3.y, bv3.z)) {
                        return new Vector3((double)bv3.x, 310.0D, (double)bv3.z);
                    }

                    if (this.goodAsteroidEntry(world, bv3.x + 2, bv3.y, bv3.z + 2)) {
                        return new Vector3((double)(bv3.x + 2), 310.0D, (double)(bv3.z + 2));
                    }

                    if (this.goodAsteroidEntry(world, bv3.x + 2, bv3.y, bv3.z - 2)) {
                        return new Vector3((double)(bv3.x + 2), 310.0D, (double)(bv3.z - 2));
                    }

                    if (this.goodAsteroidEntry(world, bv3.x - 2, bv3.y, bv3.z - 2)) {
                        return new Vector3((double)(bv3.x - 2), 310.0D, (double)(bv3.z - 2));
                    }

                    if (this.goodAsteroidEntry(world, bv3.x - 2, bv3.y, bv3.z + 2)) {
                        return new Vector3((double)(bv3.x - 2), 310.0D, (double)(bv3.z + 2));
                    }

                    if (ConfigManagerCore.enableDebug) {
                        GCLog.info("Removing drilled out asteroid at x" + bv3.x + " z" + bv3.z);
                    }

                    ((WorldProviderKuiperBelt)world.provider).removeAsteroid(bv3.x, bv3.y, bv3.z);
                }

                ++attemptCount;
            } while(attemptCount < 5);

            GCLog.info("Failed to find good large asteroid landing spot! Falling back to making a small one.");
            this.makeSmallLandingSpot(world, x, z);
            return new Vector3((double)x, 310.0D, (double)z);
        }
    }

    private boolean goodAsteroidEntry(World world, int x, int yorig, int z) {
        for(int k = 208; k > 48; --k) {
            if (!world.isAirBlock(new BlockPos(x, k, z)) && Math.abs(k - yorig) <= 20) {
                for(int y = k + 2; y < 256; ++y) {
                    if (world.getBlockState(new BlockPos(x, y, z)).getBlock() == AsteroidBlocks.blockBasic) {
                        world.setBlockToAir(new BlockPos(x, y, z));
                    }

                    if (world.getBlockState(new BlockPos(x - 1, y, z)).getBlock() == AsteroidBlocks.blockBasic) {
                        world.setBlockToAir(new BlockPos(x - 1, y, z));
                    }

                    if (world.getBlockState(new BlockPos(x, y, z - 1)).getBlock() == AsteroidBlocks.blockBasic) {
                        world.setBlockToAir(new BlockPos(x, y, z - 1));
                    }

                    if (world.getBlockState(new BlockPos(x - 1, y, z - 1)).getBlock() == AsteroidBlocks.blockBasic) {
                        world.setBlockToAir(new BlockPos(x - 1, y, z - 1));
                    }
                }

                if (ConfigManagerCore.enableDebug) {
                    GCLog.info("Found asteroid at x" + x + " z" + z);
                }

                return true;
            }
        }

        return false;
    }

    private void makeSmallLandingSpot(World world, int x, int z) {
        this.loadChunksAround(x, z, -1, (ChunkProviderServer)world.getChunkProvider());

        for(int k = 255; k > 48; --k) {
            if (!world.isAirBlock(new BlockPos(x, k, z))) {
                this.makePlatform(world, x, k - 1, z);
                return;
            }

            if (!world.isAirBlock(new BlockPos(x - 1, k, z))) {
                this.makePlatform(world, x - 1, k - 1, z);
                return;
            }

            if (!world.isAirBlock(new BlockPos(x - 1, k, z - 1))) {
                this.makePlatform(world, x - 1, k - 1, z - 1);
                return;
            }

            if (!world.isAirBlock(new BlockPos(x, k, z - 1))) {
                this.makePlatform(world, x, k - 1, z - 1);
                return;
            }
        }

        this.makePlatform(world, x, 48 + world.rand.nextInt(128), z);
    }

    private void loadChunksAround(int x, int z, int i, ChunkProviderServer cp) {
        cp.loadChunk(x >> 4, z >> 4);
        if (x + i >> 4 != x >> 4) {
            cp.loadChunk(x + i >> 4, z >> 4);
            if (z + i >> 4 != z >> 4) {
                cp.loadChunk(x >> 4, z + i >> 4);
                cp.loadChunk(x + i >> 4, z + i >> 4);
            }
        } else if (z + i >> 4 != z >> 4) {
            cp.loadChunk(x >> 4, z + i >> 4);
        }

    }

    private void makePlatform(World world, int x, int y, int z) {
        int xx;
        int zz;
        for(xx = -3; xx < 3; ++xx) {
            for(zz = -3; zz < 3; ++zz) {
                if ((xx != -3 || zz != -3 && zz != 2) && (xx != 2 || zz != -3 && zz != 2)) {
                    this.doBlock(world, x + xx, y, z + zz);
                }
            }
        }

        for(xx = -2; xx < 2; ++xx) {
            for(zz = -2; zz < 2; ++zz) {
                this.doBlock(world, x + xx, y - 1, z + zz);
            }
        }

        this.doBlock(world, x - 1, y - 2, z - 1);
        this.doBlock(world, x - 1, y - 2, z);
        this.doBlock(world, x, y - 2, z);
        this.doBlock(world, x, y - 2, z - 1);
    }

    private void doBlock(World world, int x, int y, int z) {
        int meta = (int)(world.rand.nextFloat() * 1.5F);
        if (world.isAirBlock(new BlockPos(x, y, z))) {
            world.setBlockState(new BlockPos(x, y, z), AsteroidBlocks.blockBasic.getStateFromMeta(meta), 2);
        }

    }

    public Vector3 getEntitySpawnLocation(WorldServer world, Entity entity) {
        return new Vector3(entity.posX, ConfigManagerCore.disableLander ? 250.0D : 900.0D, entity.posZ);
    }

    public Vector3 getParaChestSpawnLocation(WorldServer world, EntityPlayerMP player, Random rand) {
        return null;
    }

    private void preGenChunks(World w, int cx, int cz) {
        this.preGenChunk(w, cx, cz);

        for(int r = 1; r < 3; ++r) {
            int xmin = cx - r;
            int xmax = cx + r;
            int zmin = cz - r;
            int zmax = cz + r;

            for(int i = -r; i < r; ++i) {
                this.preGenChunk(w, xmin, cz + i);
                this.preGenChunk(w, xmax, cz - i);
                this.preGenChunk(w, cx - i, zmin);
                this.preGenChunk(w, cx + i, zmax);
            }
        }

    }

    private void preGenChunk(World w, int chunkX, int chunkZ) {
        w.getChunkFromChunkCoords(chunkX, chunkZ);
    }

    public void onSpaceDimensionChanged(World newWorld, EntityPlayerMP player, boolean ridingAutoRocket) {
        if (!ridingAutoRocket && player != null) {
            GCPlayerStats stats = GCPlayerStats.get(player);
            if (stats.getTeleportCooldown() <= 0) {
                if (player.capabilities.isFlying) {
                    player.capabilities.isFlying = false;
                }

                if (!newWorld.isRemote) {
                    EntityEntryPod entryPod = new EntityEntryPod(player);
                    boolean previous = CompatibilityManager.forceLoadChunks((WorldServer)newWorld);
                    entryPod.forceSpawn = true;
                    newWorld.spawnEntity(entryPod);
                    CompatibilityManager.forceLoadChunksEnd((WorldServer)newWorld, previous);
                }

                stats.setTeleportCooldown(10);
            }
        }

    }

    public void setupAdventureSpawn(EntityPlayerMP player) {
    }
}
