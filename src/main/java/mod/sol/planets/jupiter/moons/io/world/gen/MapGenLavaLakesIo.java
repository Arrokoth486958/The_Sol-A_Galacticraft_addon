package mod.sol.planets.jupiter.moons.io.world.gen;

import java.util.Random;
import micdoodle8.mods.galacticraft.planets.venus.VenusBlocks;
import micdoodle8.mods.galacticraft.planets.venus.blocks.BlockBasicVenus;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MapGenLavaLakesIo extends WorldGenerator {
    public MapGenLavaLakesIo() {
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for(position = position.add(-8, 0, -8); position.getY() > 5 && worldIn.isAirBlock(position); position = position.down()) {
        }

        if (position.getY() <= 4) {
            return false;
        } else {
            position = position.down(4);
            boolean[] aboolean = new boolean[2048];
            int i = rand.nextInt(4) + 4;

            int j2;
            for(j2 = 0; j2 < i; ++j2) {
                double d0 = rand.nextDouble() * 6.0D + 3.0D;
                double d1 = rand.nextDouble() * 4.0D + 2.0D;
                double d2 = rand.nextDouble() * 6.0D + 3.0D;
                double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for(int l = 1; l < 15; ++l) {
                    for(int i1 = 1; i1 < 15; ++i1) {
                        for(int j1 = 1; j1 < 7; ++j1) {
                            double d6 = ((double)l - d3) / (d0 / 2.0D);
                            double d7 = ((double)j1 - d4) / (d1 / 2.0D);
                            double d8 = ((double)i1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                            if (d9 < 1.0D) {
                                aboolean[(l * 16 + i1) * 8 + j1] = true;
                            }
                        }
                    }
                }
            }

            int k4;
            IBlockState state;
            int k3;
            boolean flag1;
            for(j2 = 0; j2 < 16; ++j2) {
                for(k3 = 0; k3 < 16; ++k3) {
                    for(k4 = 0; k4 < 8; ++k4) {
                        flag1 = !aboolean[(j2 * 16 + k3) * 8 + k4] && (j2 < 15 && aboolean[((j2 + 1) * 16 + k3) * 8 + k4] || j2 > 0 && aboolean[((j2 - 1) * 16 + k3) * 8 + k4] || k3 < 15 && aboolean[(j2 * 16 + k3 + 1) * 8 + k4] || k3 > 0 && aboolean[(j2 * 16 + (k3 - 1)) * 8 + k4] || k4 < 7 && aboolean[(j2 * 16 + k3) * 8 + k4 + 1] || k4 > 0 && aboolean[(j2 * 16 + k3) * 8 + (k4 - 1)]);
                        if (flag1) {
                            state = worldIn.getBlockState(position.add(j2, k4, k3));
                            Material material = state.getMaterial();
                            if (k4 >= 4 && material.isLiquid()) {
                                return false;
                            }

                            if (k4 < 4 && !material.isSolid() && worldIn.getBlockState(position.add(j2, k4, k3)).getBlock() != Blocks.LAVA) {
                                return false;
                            }
                        }
                    }
                }
            }

            for(j2 = 0; j2 < 16; ++j2) {
                for(k3 = 0; k3 < 16; ++k3) {
                    for(k4 = 0; k4 < 8; ++k4) {
                        if (aboolean[(j2 * 16 + k3) * 8 + k4]) {
                            worldIn.setBlockState(position.add(j2, k4, k3), k4 >= 4 ? Blocks.AIR.getDefaultState() : Blocks.LAVA.getDefaultState(), 2);
                        }
                    }
                }
            }

            for(j2 = 0; j2 < 16; ++j2) {
                for(k3 = 0; k3 < 16; ++k3) {
                    for(k4 = 4; k4 < 8; ++k4) {
                        if (aboolean[(j2 * 16 + k3) * 8 + k4]) {
                            BlockPos blockpos = position.add(j2, k4 - 1, k3);
                            if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && worldIn.getLightFor(EnumSkyBlock.SKY, position.add(j2, k4, k3)) > 0) {
                                Biome biomegenbase = worldIn.getBiome(blockpos);
                                if (biomegenbase.topBlock.getBlock() == Blocks.MYCELIUM) {
                                    worldIn.setBlockState(blockpos, Blocks.MYCELIUM.getDefaultState(), 2);
                                } else {
                                    worldIn.setBlockState(blockpos, Blocks.GRASS.getDefaultState(), 2);
                                }
                            }
                        }
                    }
                }
            }

            for(j2 = 0; j2 < 16; ++j2) {
                for(k3 = 0; k3 < 16; ++k3) {
                    for(k4 = 0; k4 < 8; ++k4) {
                        flag1 = !aboolean[(j2 * 16 + k3) * 8 + k4] && (j2 < 15 && aboolean[((j2 + 1) * 16 + k3) * 8 + k4] || j2 > 0 && aboolean[((j2 - 1) * 16 + k3) * 8 + k4] || k3 < 15 && aboolean[(j2 * 16 + k3 + 1) * 8 + k4] || k3 > 0 && aboolean[(j2 * 16 + (k3 - 1)) * 8 + k4] || k4 < 7 && aboolean[(j2 * 16 + k3) * 8 + k4 + 1] || k4 > 0 && aboolean[(j2 * 16 + k3) * 8 + (k4 - 1)]);
                        state = worldIn.getBlockState(position.add(j2, k4, k3));
                        if (flag1 && (k4 < 4 || rand.nextInt(2) != 0) && state.getMaterial().isSolid()) {
                            worldIn.setBlockState(position.add(j2, k4, k3), VenusBlocks.venusBlock.getDefaultState().withProperty(BlockBasicVenus.BASIC_TYPE_VENUS, BlockBasicVenus.EnumBlockBasicVenus.ROCK_MAGMA), 2);
                        }
                    }
                }
            }

            return true;
        }
    }
}
