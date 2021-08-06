package mod.sol.planets.kuiper_belt.dimension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.RoomTreasure;
import micdoodle8.mods.galacticraft.planets.GCPlanetDimensions;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;
import micdoodle8.mods.galacticraft.planets.asteroids.world.gen.ChunkProviderAsteroids;
import mod.sol.TheSol;
import mod.sol.init.SolDimensions;
import mod.sol.planets.kuiper_belt.world.gen.ChunkProviderKuiperBelt;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderKuiperBelt extends WorldProviderSpace implements ISolarLevel {
    private HashSet<WorldProviderKuiperBelt.AsteroidData> asteroids = new HashSet();
    private boolean dataNotLoaded = true;
    private KuiperBeltSaveData datafile;
    private double solarMultiplier = -1.0D;

    public WorldProviderKuiperBelt() {
    }

    public CelestialBody getCelestialBody() {
        return TheSol.planetKuiperBelt;
    }

    public Vector3 getFogColor() {
        return new Vector3(0.0D, 0.0D, 0.0D);
    }

    public Vector3 getSkyColor() {
        return new Vector3(0.0D, 0.0D, 0.0D);
    }

    public boolean hasSunset() {
        return false;
    }

    public long getDayLength() {
        return 0L;
    }

    public boolean isDaytime() {
        return true;
    }

    public Class<? extends IChunkGenerator> getChunkProviderClass() {
        return ChunkProviderAsteroids.class;
    }

    public float calculateCelestialAngle(long par1, float par3) {
        return 0.25F;
    }

    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1) {
        return 1.0F;
    }

    public double getHorizon() {
        return 44.0D;
    }

    public int getAverageGroundLevel() {
        return 96;
    }

    public boolean canCoordinateBeSpawn(int var1, int var2) {
        return true;
    }

    public boolean canRespawnHere() {
        if (EventHandlerGC.bedActivated) {
            EventHandlerGC.bedActivated = false;
            return true;
        } else {
            return false;
        }
    }

    public float getGravity() {
        return 0.072F;
    }

    public double getMeteorFrequency() {
        return 10.0D;
    }

    public double getFuelUsageMultiplier() {
        return 0.9D;
    }

    public boolean canSpaceshipTierPass(int tier) {
        return tier >= 3;
    }

    public float getFallDamageModifier() {
        return 0.1F;
    }

    public void addAsteroid(int x, int y, int z, int size, int core) {
        WorldProviderKuiperBelt.AsteroidData coords = new WorldProviderKuiperBelt.AsteroidData(x, y, z, size, core);
        if (!this.asteroids.contains(coords)) {
            if (this.dataNotLoaded) {
                this.loadAsteroidSavedData();
            }

            if (!this.asteroids.contains(coords)) {
                this.addToNBT(this.datafile.datacompound, coords);
                this.asteroids.add(coords);
            }
        }

    }

    public void removeAsteroid(int x, int y, int z) {
        WorldProviderKuiperBelt.AsteroidData coords = new WorldProviderKuiperBelt.AsteroidData(x, y, z);
        if (this.asteroids.contains(coords)) {
            this.asteroids.remove(coords);
            if (this.dataNotLoaded) {
                this.loadAsteroidSavedData();
            }

            this.writeToNBT(this.datafile.datacompound);
        }

    }

    private void loadAsteroidSavedData() {
        this.datafile = (KuiperBeltSaveData)this.world.loadData(KuiperBeltSaveData.class, "../sol/SolKuiperBeltData");
        if (this.datafile == null) {
            this.datafile = new KuiperBeltSaveData("");
            this.world.setData("../sol/SolKuiperBeltData", this.datafile);
            this.writeToNBT(this.datafile.datacompound);
        } else {
            this.readFromNBT(this.datafile.datacompound);
        }

        this.dataNotLoaded = false;
    }

    private void readFromNBT(NBTTagCompound nbt) {
        NBTTagList coordList = nbt.getTagList("coords", 10);
        if (coordList.tagCount() > 0) {
            for(int j = 0; j < coordList.tagCount(); ++j) {
                NBTTagCompound tag1 = coordList.getCompoundTagAt(j);
                if (tag1 != null) {
                    this.asteroids.add(WorldProviderKuiperBelt.AsteroidData.readFromNBT(tag1));
                }
            }
        }

    }

    private void writeToNBT(NBTTagCompound nbt) {
        NBTTagList coordList = new NBTTagList();
        Iterator var3 = this.asteroids.iterator();

        while(var3.hasNext()) {
            WorldProviderKuiperBelt.AsteroidData coords = (WorldProviderKuiperBelt.AsteroidData)var3.next();
            NBTTagCompound tag = new NBTTagCompound();
            coords.writeToNBT(tag);
            coordList.appendTag(tag);
        }

        nbt.setTag("coords", coordList);
        this.datafile.markDirty();
    }

    private void addToNBT(NBTTagCompound nbt, WorldProviderKuiperBelt.AsteroidData coords) {
        NBTTagList coordList = nbt.getTagList("coords", 10);
        NBTTagCompound tag = new NBTTagCompound();
        coords.writeToNBT(tag);
        coordList.appendTag(tag);
        nbt.setTag("coords", coordList);
        this.datafile.markDirty();
    }

    public boolean checkHasAsteroids() {
        if (this.dataNotLoaded) {
            this.loadAsteroidSavedData();
        }

        return this.asteroids.size() != 0;
    }

    public BlockVec3 getClosestAsteroidXZ(int x, int y, int z, boolean mark) {
        if (!this.checkHasAsteroids()) {
            return null;
        } else {
            BlockVec3 result = null;
            WorldProviderKuiperBelt.AsteroidData resultRoid = null;
            int lowestDistance = 2147483647;
            Iterator var8 = this.asteroids.iterator();

            while(true) {
                WorldProviderKuiperBelt.AsteroidData test;
                do {
                    if (!var8.hasNext()) {
                        if (result == null) {
                            return null;
                        }

                        if (mark) {
                            resultRoid.sizeAndLandedFlag |= 128;
                            this.writeToNBT(this.datafile.datacompound);
                        }

                        result = result.clone();
                        result.sideDoneBits = resultRoid.sizeAndLandedFlag & 127;
                        return result;
                    }

                    test = (WorldProviderKuiperBelt.AsteroidData)var8.next();
                } while(mark && (test.sizeAndLandedFlag & 128) > 0);

                int dx = x - test.centre.x;
                int dz = z - test.centre.z;
                int a = dx * dx + dz * dz;
                if (a < lowestDistance) {
                    lowestDistance = a;
                    result = test.centre;
                    resultRoid = test;
                }
            }
        }
    }

    public ArrayList<BlockVec3> getClosestAsteroidsXZ(int x, int y, int z, int facing, int count) {
        if (!this.checkHasAsteroids()) {
            return null;
        } else {
            TreeMap<Integer, BlockVec3> targets = new TreeMap();
            Iterator var7 = this.asteroids.iterator();

            while(true) {
                BlockVec3 test;
                label59:
                while(true) {
                    if (!var7.hasNext()) {
                        int max = Math.max(count, targets.size());
                        if (max <= 0) {
                            return null;
                        }

                        ArrayList<BlockVec3> returnValues = new ArrayList();
                        int i = 0;
                        int offset = 6;
                        Iterator var18 = targets.values().iterator();

                        while(var18.hasNext()) {
                            BlockVec3 target = (BlockVec3)var18.next();
                            BlockVec3 coords = target.clone();
                            GCLog.debug("Found nearby asteroid at " + target.toString());
                            switch(facing) {
                                case 2:
                                    coords.z += offset;
                                    break;
                                case 3:
                                    coords.z -= offset;
                                    break;
                                case 4:
                                    coords.x += offset;
                                    break;
                                case 5:
                                    coords.x -= offset;
                            }

                            returnValues.add(coords);
                            ++i;
                            if (i >= count) {
                                break;
                            }
                        }

                        return returnValues;
                    }

                    WorldProviderKuiperBelt.AsteroidData roid = (WorldProviderKuiperBelt.AsteroidData)var7.next();
                    test = roid.centre;
                    switch(facing) {
                        case 2:
                            if (z - 16 < test.z) {
                                break;
                            }
                            break label59;
                        case 3:
                            if (z + 16 > test.z) {
                                break;
                            }
                            break label59;
                        case 4:
                            if (x - 16 < test.x) {
                                break;
                            }
                            break label59;
                        case 5:
                            if (x + 16 > test.x) {
                                break;
                            }
                        default:
                            break label59;
                    }
                }

                int dx = x - test.x;
                int dz = z - test.z;
                int a = dx * dx + dz * dz;
                if (a < 262144) {
                    targets.put(a, test);
                }
            }
        }
    }

    public int getActualHeight() {
        return 256;
    }

    protected void init() {
        super.init();
        this.nether = true;
    }

    public double getSolarEnergyMultiplier() {
        if (this.solarMultiplier < 0.0D) {
            double s = (double)this.getSolarSize();
            this.solarMultiplier = s * s * s * ConfigManagerCore.spaceStationEnergyScalar;
        }

        return this.solarMultiplier;
    }

    public int getDungeonSpacing() {
        return 576;
    }

    public DimensionType getDimensionType() {
        return SolDimensions.KuiperBelt;
    }

    public float getArrowGravity() {
        return 0.002F;
    }

    public ResourceLocation getDungeonChestType() {
        return RoomTreasure.MOONCHEST;
    }

    public boolean hasSkyLight() {
        return false;
    }

    public List<Block> getSurfaceBlocks() {
        return null;
    }

    private static class AsteroidData {
        protected BlockVec3 centre;
        protected int sizeAndLandedFlag = 15;
        protected int coreAndSpawnedFlag = -2;

        public AsteroidData(int x, int y, int z) {
            this.centre = new BlockVec3(x, y, z);
        }

        public AsteroidData(int x, int y, int z, int size, int core) {
            this.centre = new BlockVec3(x, y, z);
            this.sizeAndLandedFlag = size;
            this.coreAndSpawnedFlag = core;
        }

        public AsteroidData(BlockVec3 bv) {
            this.centre = bv;
        }

        public int hashCode() {
            return this.centre != null ? this.centre.hashCode() : 0;
        }

        public boolean equals(Object o) {
            BlockVec3 vector;
            if (o instanceof WorldProviderKuiperBelt.AsteroidData) {
                vector = ((WorldProviderKuiperBelt.AsteroidData)o).centre;
                return this.centre.x == vector.x && this.centre.y == vector.y && this.centre.z == vector.z;
            } else if (!(o instanceof BlockVec3)) {
                return false;
            } else {
                vector = (BlockVec3)o;
                return this.centre.x == vector.x && this.centre.y == vector.y && this.centre.z == vector.z;
            }
        }

        public NBTTagCompound writeToNBT(NBTTagCompound tag) {
            tag.setInteger("x", this.centre.x);
            tag.setInteger("y", this.centre.y);
            tag.setInteger("z", this.centre.z);
            tag.setInteger("coreAndFlag", this.coreAndSpawnedFlag);
            tag.setInteger("sizeAndFlag", this.sizeAndLandedFlag);
            return tag;
        }

        public static WorldProviderKuiperBelt.AsteroidData readFromNBT(NBTTagCompound tag) {
            BlockVec3 tempVector = new BlockVec3();
            tempVector.x = tag.getInteger("x");
            tempVector.y = tag.getInteger("y");
            tempVector.z = tag.getInteger("z");
            WorldProviderKuiperBelt.AsteroidData roid = new WorldProviderKuiperBelt.AsteroidData(tempVector);
            if (tag.hasKey("coreAndFlag")) {
                roid.coreAndSpawnedFlag = tag.getInteger("coreAndFlag");
            }

            if (tag.hasKey("sizeAndFlag")) {
                roid.sizeAndLandedFlag = tag.getInteger("sizeAndFlag");
            }

            return roid;
        }
    }
}
