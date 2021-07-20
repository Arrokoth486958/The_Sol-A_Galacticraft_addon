package mod.sol.init;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import mod.sol.planets.jupiter.moons.europa.dimension.WorldProviderEuropa;
import mod.sol.planets.jupiter.moons.io.dimension.WorldProviderIo;
import mod.sol.planets.mercury.dimension.WorldProviderMercury;
import mod.sol.planets.pluto.dimension.WorldProviderPluto;
import mod.sol.planets.saturn.moons.mimas.dimension.WorldProviderMimas;
import mod.sol.planets.saturn.moons.titan.dimension.WorldProviderTitan;
import mod.sol.planets.uranus.moon.ariel.dimension.WorldProviderAriel;
import mod.sol.util.Reference;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;

public class SolDimensions
{
	//planets
	public static DimensionType Mercury = GalacticraftRegistry.registerDimension("mercury", "_mercury", -(Reference.MOD_ID.hashCode() + 100), WorldProviderMercury.class, false);
	public static DimensionType Pluto = GalacticraftRegistry.registerDimension("pluto", "_pluto", -(Reference.MOD_ID.hashCode() + 900), WorldProviderPluto.class, false);
	//public static DimensionType KuiperBelt = GalacticraftRegistry.registerDimension("kuiper_belt", "_kuiper_belt", -(Reference.MOD_ID.hashCode() + 1000), WorldProviderKuiperBelt.class, false);
	//moons
	public static DimensionType Io = GalacticraftRegistry.registerDimension("io", "_io", -(Reference.MOD_ID.hashCode() + 501), WorldProviderIo.class, false);
	public static DimensionType Europa = GalacticraftRegistry.registerDimension("europa", "_europa", -(Reference.MOD_ID.hashCode() + 502), WorldProviderEuropa.class, false);
	
	public static DimensionType Mimas = GalacticraftRegistry.registerDimension("mimas", "_mimas", -(Reference.MOD_ID.hashCode() + 601), WorldProviderMimas.class, false);
	public static DimensionType Titan = GalacticraftRegistry.registerDimension("titan", "_titan", -(Reference.MOD_ID.hashCode() + 606), WorldProviderTitan.class, false);

	public static DimensionType Ariel = GalacticraftRegistry.registerDimension("ariel", "_ariel", -(Reference.MOD_ID.hashCode() + 701), WorldProviderAriel.class, false);
}