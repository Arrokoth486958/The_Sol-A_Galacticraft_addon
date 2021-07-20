package mod.sol.planets.jupiter.moons.io.biome;

import micdoodle8.mods.galacticraft.planets.venus.VenusBlocks;
import micdoodle8.mods.galacticraft.planets.venus.blocks.BlockBasicVenus;
import mod.sol.init.SolBlocks;
import mod.sol.planets.pluto.world.gen.ChunkProviderPluto;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

public class BiomeAshLandIo extends BiomeIo
{
    public BiomeAshLandIo(BiomeProperties properties)
    {
        super(properties);
    	this.topBlock = SolBlocks.IO_ASH_BLOCK.getDefaultState();
    }

    @Override
    public void registerTypes(Biome b)
    {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
    }
}
