package mod.sol.planets.uranus.moon.ariel.dimension;

import java.util.LinkedList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import micdoodle8.mods.galacticraft.core.world.gen.ChunkProviderMoon;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.RoomTreasure;
import mod.sol.TheSol;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolDimensions;
import mod.sol.planets.uranus.moon.ariel.world.gen.ChunkProviderAriel;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderAriel extends WorldProviderSpace implements IGalacticraftWorldProvider, ISolarLevel
{
    @Override
    public DimensionType getDimensionType()
    {
        return SolDimensions.Ariel;
    }

    @Override
    public Vector3 getFogColor()
    {
        return new Vector3(0, 0, 0);
    }

    @Override
    public Vector3 getSkyColor()
    {
        return new Vector3(0, 0, 0);
    }

    @Override
    public boolean hasSunset()
    {
        return false;
    }

    @Override
    public long getDayLength()
    {
        return 192000L;
    }

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass()
    {
        return ChunkProviderAriel.class;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
        final float var2 = this.world.getCelestialAngle(par1);
        float var3 = 1.0F - (MathHelper.cos(var2 * Constants.twoPI) * 2.0F + 0.25F);

        if (var3 < 0.0F)
        {
            var3 = 0.0F;
        }

        if (var3 > 1.0F)
        {
            var3 = 1.0F;
        }

        return var3 * var3 * 0.5F + 0.3F;
    }

    @Override
    public boolean isSkyColored()
    {
        return false;
    }

    @Override
    public double getHorizon()
    {
        return 44.0D;
    }

    @Override
    public int getAverageGroundLevel()
    {
        return 68;
    }

    @Override
    public boolean canCoordinateBeSpawn(int var1, int var2)
    {
        return true;
    }

    //Overriding  so that beds do not explode on Moon
    @Override
    public boolean canRespawnHere()
    {
        if (EventHandlerGC.bedActivated)
        {
            EventHandlerGC.bedActivated = false;
            return true;
        }
        return false;
    }

    @Override
    public float getGravity()
    {
        return 0.062F;
    }

    @Override
    public double getFuelUsageMultiplier()
    {
        return 0.85D;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 0.33D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= this.getCelestialBody().getTierRequirement();
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.18F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return TheSol.moonAriel;
    }

    @Override
    public int getDungeonSpacing()
    {
        return 704;
    }

    @Override
    public ResourceLocation getDungeonChestType()
    {
        return RoomTreasure.MOONCHEST;
    }

    @Override
    public List<Block> getSurfaceBlocks()
    {
        List<Block> list = new LinkedList<>();
        list.add(SolBlocks.ARIEL_TURF);
        return list;
    }
}
