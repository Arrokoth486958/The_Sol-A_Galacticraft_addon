package mod.sol.init;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import mod.sol.config.ConfigManagerSol;
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
	public static DimensionType Mercury = GalacticraftRegistry.registerDimension("mercury", "_mercury", ConfigManagerSol.dimensionidMercury, WorldProviderMercury.class, false);
	public static DimensionType Pluto = GalacticraftRegistry.registerDimension("pluto", "_pluto", ConfigManagerSol.dimensionidPluto, WorldProviderPluto.class, false);
	//public static DimensionType KuiperBelt = GalacticraftRegistry.registerDimension("kuiper_belt", "_kuiper_belt", -(Reference.MOD_ID.hashCode() + 1000), WorldProviderKuiperBelt.class, false);
	//moons
	public static DimensionType Io = GalacticraftRegistry.registerDimension("io", "_io", ConfigManagerSol.dimensionidIo, WorldProviderIo.class, false);
	public static DimensionType Europa = GalacticraftRegistry.registerDimension("europa", "_europa", ConfigManagerSol.dimensionidEuropa, WorldProviderEuropa.class, false);
	
	public static DimensionType Mimas = GalacticraftRegistry.registerDimension("mimas", "_mimas", ConfigManagerSol.dimensionidMimas, WorldProviderMimas.class, false);
	public static DimensionType Titan = GalacticraftRegistry.registerDimension("titan", "_titan", ConfigManagerSol.dimensionidTitan, WorldProviderTitan.class, false);

	public static DimensionType Ariel = GalacticraftRegistry.registerDimension("ariel", "_ariel", ConfigManagerSol.dimensionidAriel, WorldProviderAriel.class, false);
}