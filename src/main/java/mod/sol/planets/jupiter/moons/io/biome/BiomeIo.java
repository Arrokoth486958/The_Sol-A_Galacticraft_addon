package mod.sol.planets.jupiter.moons.io.biome;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import micdoodle8.mods.galacticraft.planets.venus.VenusBlocks;
import micdoodle8.mods.galacticraft.planets.venus.blocks.BlockBasicVenus;
import mod.sol.planets.mercury.world.gen.ChunkProviderMercury;
import mod.sol.planets.pluto.world.gen.ChunkProviderPluto;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeSnow;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeIo extends BiomeGenBaseGC
{
    public static final Biome ioFlat = new BiomeFlatIo(new BiomeProperties("Io Flat").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
    public static final Biome ioAshLand = new BiomeAshLandIo(new BiomeProperties("Io Ash Land").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
    public static final Biome ioSulfurField = new BiomeSulfurFieldIo(new BiomeProperties("Io Sulfur Field").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));

    BiomeIo(BiomeProperties properties)
    {
        super(properties, true);
    }

    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        return getModdedBiomeDecorator(new BiomeDecoratorIo());
    }

    @Override
    public float getSpawningChance()
    {
        return 0.1F;
    }
}
