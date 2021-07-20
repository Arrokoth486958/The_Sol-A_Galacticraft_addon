package mod.sol.planets.jupiter.moons.io.biome;

import java.util.Random;

import micdoodle8.mods.galacticraft.planets.venus.VenusBlocks;
import mod.sol.init.SolBlocks;
import mod.sol.planets.mercury.world.gen.ChunkProviderMercury;
import mod.sol.planets.pluto.world.gen.ChunkProviderPluto;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeFlatIo extends BiomeIo
{
    public BiomeFlatIo(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = SolBlocks.IO_SURFACE_ROCK.getDefaultState();
    }
    
    @Override
    public void registerTypes(Biome b)
    {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
    }
}
