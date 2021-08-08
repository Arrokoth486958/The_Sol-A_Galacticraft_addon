package mod.sol;

import java.io.File;
import java.util.List;

import com.google.common.collect.ImmutableList;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.client.model.OBJLoaderGC;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderMoon;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedEnderman;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import micdoodle8.mods.galacticraft.core.wrappers.ModelTransformWrapper;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import micdoodle8.mods.galacticraft.planets.venus.ConfigManagerVenus;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import micdoodle8.mods.galacticraft.planets.venus.dimension.WorldProviderVenus;
import micdoodle8.mods.galacticraft.planets.venus.world.gen.BiomeVenus;
import mod.sol.api.galaxy.DwarfPlanet;
import mod.sol.api.galaxy.GasGiant;
import mod.sol.client.gui.screen.SolCelestialSelection;
import mod.sol.config.ConfigManagerSol;
import mod.sol.entities.boss.*;
import mod.sol.entities.rocket.*;
import mod.sol.items.*;
import mod.sol.planets.neptune.triton.biome.BiomeTriton;
import mod.sol.planets.neptune.triton.dimension.TeleportTypeTriton;
import mod.sol.planets.neptune.triton.dimension.WorldProviderTriton;
import mod.sol.planets.uranus.moon.ariel.biome.BiomeAriel;
import mod.sol.planets.uranus.moon.ariel.dimension.TeleportTypeAriel;
import mod.sol.planets.uranus.moon.ariel.dimension.WorldProviderAriel;
import mod.sol.recipe.*;
import mod.sol.render.entity.*;
import mod.sol.render.model.item.*;
import mod.sol.render.rocket.*;
import mod.sol.render.tile.*;
import mod.sol.schematic.*;
import mod.sol.tile.*;
import mod.sol.util.Reference;
import mod.sol.util.SolEntityRegistry;
import mod.sol.util.SolTreasureChestRegistry;
import mod.sol.util.handler.SolEventHandlerClient;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import mod.sol.entities.EntityHugeFireball;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolDimensions;
import mod.sol.init.SolItems;
import mod.sol.init.SolOreDict;
import mod.sol.planets.jupiter.moons.europa.biome.BiomeEuropa;
import mod.sol.planets.jupiter.moons.europa.dimension.TeleportTypeEuropa;
import mod.sol.planets.jupiter.moons.europa.dimension.WorldProviderEuropa;
import mod.sol.planets.jupiter.moons.io.biome.BiomeIo;
import mod.sol.planets.jupiter.moons.io.dimension.TeleportTypeIo;
import mod.sol.planets.jupiter.moons.io.dimension.WorldProviderIo;
import mod.sol.planets.mercury.biome.BiomeMercury;
import mod.sol.planets.mercury.dimension.TeleportTypeMercury;
import mod.sol.planets.mercury.dimension.WorldProviderMercury;
import mod.sol.planets.pluto.dimension.TeleportTypePluto;
import mod.sol.planets.pluto.dimension.WorldProviderPluto;
import mod.sol.planets.saturn.moons.mimas.biome.BiomeMimas;
import mod.sol.planets.saturn.moons.mimas.dimension.TeleportTypeMimas;
import mod.sol.planets.saturn.moons.mimas.dimension.WorldProviderMimas;
import mod.sol.planets.saturn.moons.titan.biome.BiomeTitan;
import mod.sol.planets.saturn.moons.titan.dimension.TeleportTypeTitan;
import mod.sol.planets.saturn.moons.titan.dimension.WorldProviderTitan;
import mod.sol.proxy.SolCommonProxy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, dependencies = "required-after:galacticraftcore; required-after:realistic_galaxy_map")
@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, dependencies = "required-after:galacticraftcore; required:jei")
public class TheSol
{
	public static final CreativeTabs ITEM_TAB = new CreativeTabs("sol_items")
	{
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(SolItems.ROCKET_T4);
		}
	};
	public static final CreativeTabs BLOCK_TAB = new CreativeTabs("sol_blocks")
	{
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(SolBlocks.MERCURY_DUNGEON_BRICK);
		}
	};

	static { FluidRegistry.enableUniversalBucket(); }

	public static File configSol;

	// solarsystem
	// gccore
	public static Planet planetVenus;
	//public static Planet planetMars;
	// planets
	public static Planet planetMercury;
	public static DwarfPlanet planetCeres;
	public static DwarfPlanet planetPluto;
	public static Planet planetKuiperBelt;
	public static DwarfPlanet planetHaumea;
	public static DwarfPlanet planetEris;
	public static DwarfPlanet planetMakemake;
	public static DwarfPlanet planetSedna;
	public static Planet planetOortCloud;
	// override
	public static Planet planetAsteroids;
	// fake
	public static GasGiant planetJupiter;
	public static GasGiant planetSaturn;
	public static GasGiant planetUranus;
	public static GasGiant planetNeptune;
	// moons
	// mars
	public static Moon moonPhobos;
	public static Moon moonDeimos;
	// jupiter
	public static Moon moonIo;
	public static Moon moonEuropa;
	public static Moon moonGanymede;
	public static Moon moonCallisto;
	// saturn
	public static Moon moonRingsOfSaturn;
	public static Moon moonMimas;
	public static Moon moonEnceladus;
	public static Moon moonTethys;
	public static Moon moonDione;
	public static Moon moonRhea;
	public static Moon moonTitan;
	// uranus
	public static Moon moonAriel;
	public static Moon moonUmbriel;
	public static Moon moonTitania;
	public static Moon moonOberon;
	// neptunus
	public static Moon moonTriton;
	// pluto
	public static Moon moonCharon;

	@Instance
	public static TheSol instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static SolCommonProxy proxy;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		System.out.print(event.getModState());

        OBJLoaderGC.instance.addDomain(Reference.MOD_ID);
		RenderingRegistry.registerEntityRenderingHandler(EntityTier4Rocket.class, (RenderManager manager) -> new RenderTier4Rocket(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityTier5Rocket.class, (RenderManager manager) -> new RenderTier5Rocket(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityTier6Rocket.class, (RenderManager manager) -> new RenderTier6Rocket(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityTier7Rocket.class, (RenderManager manager) -> new RenderTier7Rocket(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityTier8Rocket.class, (RenderManager manager) -> new RenderTier8Rocket(manager));
		MinecraftForge.EVENT_BUS.register(this);

        RenderingRegistry.registerEntityRenderingHandler(EntityHugeFireball.class, (RenderManager manager) -> new RenderHugeFireball(manager, 1));

        RenderingRegistry.registerEntityRenderingHandler(EntityMercuryBossBlaze.class, (RenderManager manager) -> new RenderMercuryBossBlaze(manager));
        RenderingRegistry.registerEntityRenderingHandler(EntityJupiterBossGhast.class, (RenderManager manager) -> new RenderJupiterBossGhast(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntitySaturnBossStray.class, (RenderManager manager) -> new RenderSaturnBossStray(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityUranusBossSlime.class, (RenderManager manager) -> new RenderUranusBossSlime(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityNeptuneBossSpider.class, (RenderManager manager) -> new RenderNeptuneBossSpider(manager));
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
    	/*TheSol.planetMars = (Planet) new Planet("mars").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.67F, 0.1F, 0.1F).setPhaseShift(0.1667F).setRelativeSize(0.5319F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.25F, 1.25F)).setRelativeOrbitTime(1.8811610076670317634173055859803F);
        TheSol.planetMars.setBiomeInfo(BiomeMars.marsFlat);
    	TheSol.planetMars.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/mars.png"));
        TheSol.planetMars.setDimensionInfo(ConfigManagerMars.dimensionIDMars, WorldProviderMars.class).setTierRequired(2);
        TheSol.planetMars.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.3F, 0.1F));
        TheSol.planetMars.atmosphereComponent(EnumAtmosphericGas.CO2).atmosphereComponent(EnumAtmosphericGas.ARGON).atmosphereComponent(EnumAtmosphericGas.NITROGEN);
        TheSol.planetMars.addMobInfo(new Biome.SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
        TheSol.planetMars.addMobInfo(new Biome.SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
        TheSol.planetMars.addMobInfo(new Biome.SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
        TheSol.planetMars.addMobInfo(new Biome.SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
        TheSol.planetMars.addMobInfo(new Biome.SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        TheSol.planetMars.addChecklistKeys("equip_oxygen_suit", "thermal_padding");
        GalaxyRegistry.registerPlanet(TheSol.planetMars);
        GalacticraftRegistry.registerTeleportType(WorldProviderMars.class, new TeleportTypeMars());*/

        TheSol.planetVenus = (Planet) new Planet("venus").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(2.0F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(0.75F, 0.75F)).setRelativeOrbitTime(0.61527929901423877327491785323111F);
        TheSol.planetVenus.setBiomeInfo(BiomeVenus.venusFlat, BiomeVenus.venusMountain, BiomeVenus.venusValley);
        TheSol.planetVenus.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/venus.png"));
        TheSol.planetVenus.setDimensionInfo(ConfigManagerVenus.dimensionIDVenus, WorldProviderVenus.class).setTierRequired(3);
        TheSol.planetVenus.setAtmosphere(new AtmosphereInfo(false, true, true, 5.0F, 0.3F, 54.0F));
        TheSol.planetVenus.atmosphereComponent(EnumAtmosphericGas.CO2).atmosphereComponent(EnumAtmosphericGas.NITROGEN);
        TheSol.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
        TheSol.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
        TheSol.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
        TheSol.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
        TheSol.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        TheSol.planetVenus.addChecklistKeys("equip_oxygen_suit", "equip_shield_controller", "thermal_padding_t2");
        GalaxyRegistry.registerPlanet(TheSol.planetVenus);
        GalacticraftRegistry.registerTeleportType(WorldProviderVenus.class, new TeleportTypeVenus());

		// fake planets
		TheSol.planetJupiter = (GasGiant) new GasGiant("jupiter").setParentSolarSystem(GalacticraftCore.solarSystemSol);
		TheSol.planetJupiter.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.0F, 2.0F)).setRelativeOrbitTime(11.861993428258488499452354874042F);
		TheSol.planetJupiter.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/jupiter.png"));

		TheSol.planetSaturn = (GasGiant) new GasGiant("saturn").setParentSolarSystem(GalacticraftCore.solarSystemSol);
		TheSol.planetSaturn.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(5.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.25F, 2.25F)).setRelativeOrbitTime(29.463307776560788608981380065717F);
		TheSol.planetSaturn.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/saturn.png"));

		TheSol.planetUranus = (GasGiant) new GasGiant("uranus").setParentSolarSystem(GalacticraftCore.solarSystemSol);
		TheSol.planetUranus.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.38F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.5F, 2.5F)).setRelativeOrbitTime(84.063526834611171960569550930997F);
		TheSol.planetUranus.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/uranus.png"));

		TheSol.planetNeptune = (GasGiant) new GasGiant("neptune").setParentSolarSystem(GalacticraftCore.solarSystemSol);
		TheSol.planetNeptune.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.0F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.75F, 2.75F)).setRelativeOrbitTime(164.84118291347207009857612267251F);
		TheSol.planetNeptune.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/neptune.png"));
		// override
		AsteroidsModule.planetAsteroids.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.65F, 1.65F));
//		AsteroidsModule.planetAsteroids = new Planet("asteroids").setParentSolarSystem(GalacticraftCore.solarSystemSol);
//		AsteroidsModule.planetAsteroids.setDimensionInfo(ConfigManagerAsteroids.dimensionIDAsteroids, WorldProviderAsteroids.class).setTierRequired(3);
//		AsteroidsModule.planetAsteroids.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.55F, 1.55F)).setRelativeOrbitTime(45.0F).setPhaseShift((float) (Math.random() * (2 * Math.PI)));
//		AsteroidsModule.planetAsteroids.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/asteroid.png"));
//		AsteroidsModule.planetAsteroids.setAtmosphere(new AtmosphereInfo(false, false, false, -1.5F, 0.05F, 0.0F));
//		AsteroidsModule.planetAsteroids.addChecklistKeys("equip_oxygen_suit", "craft_grapple_hook", "thermal_padding");
//		AsteroidsModule.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
//		AsteroidsModule.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
//		AsteroidsModule.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
//		AsteroidsModule.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
//		AsteroidsModule.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
//		TheSol.planetAsteroids = new Planet("asteroids").setParentSolarSystem(GalacticraftCore.solarSystemSol);
//		TheSol.planetAsteroids.setDimensionInfo(ConfigManagerAsteroids.dimensionIDAsteroids, WorldProviderAsteroids.class).setTierRequired(3);
//		TheSol.planetAsteroids.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.55F, 1.55F)).setRelativeOrbitTime(45.0F).setPhaseShift((float) (Math.random() * (2 * Math.PI)));
//		TheSol.planetAsteroids.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/asteroid.png"));
//		TheSol.planetAsteroids.setAtmosphere(new AtmosphereInfo(false, false, false, -1.5F, 0.05F, 0.0F));
//		TheSol.planetAsteroids.addChecklistKeys("equip_oxygen_suit", "craft_grapple_hook", "thermal_padding");
//		TheSol.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
//		TheSol.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
//		TheSol.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
//		TheSol.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
//		TheSol.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
		// planets
		// mercury
		TheSol.planetMercury = (Planet) new Planet("mercury").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(0.5F, 0.5F)).setRelativeOrbitTime(0.24096385542168674698795180722892F);
		TheSol.planetMercury.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/mercury.png"));
		TheSol.planetMercury.setAtmosphere(new AtmosphereInfo(false, false, false, 5.0F, 0.0F, 0.0F));
		TheSol.planetMercury.setRelativeSize(0.4312F);
		TheSol.planetMercury.setDimensionInfo(ConfigManagerSol.dimensionidMercury, WorldProviderMercury.class).setTierRequired(3).setBiomeInfo(BiomeMercury.mercuryFlat);
		TheSol.planetMercury.setAtmosphere(new AtmosphereInfo(false, false, false, 5.0F, 0.0F, 0.0F));
		TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
		TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
		TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
		TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
		TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
		TheSol.planetMercury.setDimensionSuffix("_mercury");
		// ceres
		TheSol.planetCeres = (DwarfPlanet) new DwarfPlanet("ceres").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(2.48F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.5F, 1.5F)).setRelativeOrbitTime(5.2433153256534542F);
		TheSol.planetCeres.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/ceres.png"));
		TheSol.planetCeres.setAtmosphere(new AtmosphereInfo(false, false, false, -1.7F, 0.0F, 0.0F));
		TheSol.planetCeres.setRelativeSize(0.1294F);
		TheSol.planetCeres.setDimensionSuffix("_ceres");
		// pluto
		TheSol.planetPluto = (DwarfPlanet) new DwarfPlanet("pluto").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(3.25F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.0F, 3.0F)).setRelativeOrbitTime(5.2433153256534542F);
		TheSol.planetPluto.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/pluto.png"));
		//TheSol.planetPluto.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
		TheSol.planetPluto.setRelativeSize(0.1294F);
//		TheSol.planetPluto.setDimensionInfo(ConfigManagerSol.dimensionidPluto, WorldProviderPluto.class).setTierRequired(8);
//		TheSol.planetPluto.setBiomeInfo(BiomePluto.plutoFlat, BiomePluto.plutoSnowfield);
//		TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
//		TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
//		TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
//		TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
//		TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
		TheSol.planetPluto.setDimensionSuffix("_pluto");
		// haumea
		TheSol.planetHaumea = (DwarfPlanet) new DwarfPlanet("haumea").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(3.92F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.65F, 3.65F)).setRelativeOrbitTime(15.1415926F);
		TheSol.planetHaumea.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/haumea.png"));
		TheSol.planetHaumea.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
		TheSol.planetHaumea.setRelativeSize(0.0294F);
		TheSol.planetHaumea.setDimensionSuffix("_haumea");
		// eris
		TheSol.planetEris = (DwarfPlanet) new DwarfPlanet("eris").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(2.42F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.4F, 3.4F)).setRelativeOrbitTime(16.1415926F);
		TheSol.planetEris.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/eris.png"));
		TheSol.planetEris.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
		TheSol.planetEris.setRelativeSize(0.0294F);
		TheSol.planetEris.setDimensionSuffix("_eris");
		// makemake
		TheSol.planetMakemake = (DwarfPlanet) new DwarfPlanet("makemake").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(9.81F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.8F, 3.8F)).setRelativeOrbitTime(11.1415926F);
		TheSol.planetMakemake.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/makemake.png"));
		TheSol.planetMakemake.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
		TheSol.planetMakemake.setRelativeSize(0.0294F);
		TheSol.planetMakemake.setDimensionSuffix("_makemake");
		// kuiperbelt
		TheSol.planetKuiperBelt = (Planet) new Planet("kuiper_belt").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) (Math.random() * (2 * Math.PI))).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.55F, 3.55F)).setRelativeOrbitTime(90.0F);
		TheSol.planetKuiperBelt.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/kuiper_belt.png"));
//		TheSol.planetKuiperBelt.setDimensionInfo(ConfigManagerSol.dimensionidKuiperBelt, WorldProviderKuiperBelt.class).setTierRequired(8);
//		TheSol.planetKuiperBelt.setBiomeInfo(BiomeKuiperBelt.kuiper_belt);
		TheSol.planetKuiperBelt.setDimensionSuffix("_kuiper_belt");
		// sedna
		TheSol.planetSedna = (DwarfPlanet) new DwarfPlanet("sedna").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(14.421412354F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(5F, 5F)).setRelativeOrbitTime(39.143442132456F);
		TheSol.planetSedna.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/sedna.png"));
		TheSol.planetSedna.setDimensionSuffix("_sedna");
		// oortcloud
		TheSol.planetOortCloud = (Planet) new Planet("oort_cloud").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) (Math.random() * (2 * Math.PI))).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(7.5F, 7.5F)).setRelativeOrbitTime(90.0F);
		TheSol.planetOortCloud.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/oort_cloud.png"));
		TheSol.planetOortCloud.setDimensionSuffix("_oort_cloud");
		// moons
		// phobos
		TheSol.moonPhobos = (Moon) new Moon("phobos").setParentPlanet(MarsModule.planetMars).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12F, 12F)).setRelativeOrbitTime(20.0F);
		TheSol.moonPhobos.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/phobos.png"));
		TheSol.moonPhobos.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonPhobos.setRelativeSize(0.4312F);
		TheSol.moonPhobos.setDimensionSuffix("_phobos");
		// deimos
		TheSol.moonDeimos = (Moon) new Moon("deimos").setParentPlanet(MarsModule.planetMars).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(0.25F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(20F, 20F)).setRelativeOrbitTime(45.68F);
		TheSol.moonDeimos.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/deimos.png"));
		TheSol.moonDeimos.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonDeimos.setRelativeSize(0.4312F);
		TheSol.moonDeimos.setDimensionSuffix("_deimos");
		// io
		TheSol.moonIo = (Moon) new Moon("io").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(17.69F);
		TheSol.moonIo.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/io.png"));
		TheSol.moonIo.setAtmosphere(new AtmosphereInfo(false, false, false, -2.0F, 0.0F, 0.0F));
		TheSol.moonIo.setRelativeSize(0.4312F);
		TheSol.moonIo.setBiomeInfo(BiomeIo.ioFlat, BiomeIo.ioAshLand, BiomeIo.ioSulfurField);
		TheSol.moonIo.setDimensionInfo(ConfigManagerSol.dimensionidIo, WorldProviderIo.class).setTierRequired(4);
		TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
		TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
		TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
		TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
		TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
		TheSol.moonIo.setDimensionSuffix("_io");
		// europa
		TheSol.moonEuropa = (Moon) new Moon("europa").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
		TheSol.moonEuropa.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/europa.png"));
		TheSol.moonEuropa.setAtmosphere(new AtmosphereInfo(false, false, false, -2.0F, 0.0F, 0.0F));
		TheSol.moonEuropa.setRelativeSize(0.4312F);
		TheSol.moonEuropa.setBiomeInfo(BiomeEuropa.europaFlat, BiomeEuropa.europaMountain, BiomeEuropa.europaValley);
		TheSol.moonEuropa.setDimensionInfo(ConfigManagerSol.dimensionidEuropa, WorldProviderEuropa.class).setTierRequired(4);
		TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
		TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
		TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
		TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
		TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
		TheSol.moonEuropa.setDimensionSuffix("_europa");
		// ganymede
		TheSol.moonGanymede = (Moon) new Moon("ganymede").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(71.54F);
		TheSol.moonGanymede.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/ganymede.png"));
		TheSol.moonGanymede.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonGanymede.setRelativeSize(0.4312F);
		TheSol.moonGanymede.setDimensionSuffix("_ganymede");
		// callisto
		TheSol.moonCallisto = (Moon) new Moon("callisto").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(25.5F, 25.5F)).setRelativeOrbitTime(166.89F);
		TheSol.moonCallisto.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/callisto.png"));
		TheSol.moonCallisto.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonCallisto.setRelativeSize(0.4312F);
		TheSol.moonCallisto.setDimensionSuffix("_callisto");
		// saturn
		// ringsofsaturn
		TheSol.moonRingsOfSaturn = (Moon) new Moon("rings_of_saturn").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(5.0F, 5.0F)).setRelativeOrbitTime(90.0F);
		TheSol.moonRingsOfSaturn.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/rings_of_saturn.png"));
		TheSol.moonRingsOfSaturn.setRelativeSize(0.4312F);
		TheSol.moonRingsOfSaturn.setDimensionSuffix("_rings_of_saturn");
		// mimas
		TheSol.moonMimas = (Moon) new Moon("mimas").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(9.375F);
		TheSol.moonMimas.setAtmosphere(new AtmosphereInfo(false, false, false, -3.0F, 0.0F, 0.0F));
		TheSol.moonMimas.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/mimas.png"));
		TheSol.moonMimas.setRelativeSize(0.4312F);
		TheSol.moonMimas.setBiomeInfo(BiomeMimas.mimasFlat);
		TheSol.moonMimas.setDimensionInfo(ConfigManagerSol.dimensionidMimas, WorldProviderMimas.class).setTierRequired(5);
		TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
		TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
		TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
		TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
		TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
		TheSol.moonMimas.setDimensionSuffix("_mimas");
		// enceladus
		TheSol.moonEnceladus= (Moon) new Moon("enceladus").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(13.70218F);
		TheSol.moonEnceladus.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/enceladus.png"));
		TheSol.moonEnceladus.setRelativeSize(0.4312F);
		TheSol.moonEnceladus.setDimensionSuffix("_enceladus");
		// tethys
		TheSol.moonTethys= (Moon) new Moon("tethys").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(16.5F, 16.5F)).setRelativeOrbitTime(18.87802F);
		TheSol.moonTethys.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/tethys.png"));
		TheSol.moonTethys.setRelativeSize(0.4312F);
		TheSol.moonTethys.setDimensionSuffix("_tethys");
		// dione
		TheSol.moonDione= (Moon) new Moon("dione").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(27.36915F);
		TheSol.moonDione.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/dione.png"));
		TheSol.moonDione.setRelativeSize(0.4312F);
		TheSol.moonDione.setDimensionSuffix("_dione");
		// rhea
		TheSol.moonRhea= (Moon) new Moon("rhea").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(23.5F, 23.5F)).setRelativeOrbitTime(45.18212F);
		TheSol.moonRhea.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/rhea.png"));
		TheSol.moonRhea.setRelativeSize(0.4312F);
		TheSol.moonRhea.setDimensionSuffix("_rhea");
		// titan
		TheSol.moonTitan= (Moon) new Moon("titan").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(26.5F, 26.5F)).setRelativeOrbitTime(159.45F);
		TheSol.moonTitan.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/titan.png"));
		TheSol.moonTitan.setAtmosphere(new AtmosphereInfo(false, false, false, -3.0F, 0.5F, 0.3F));
		TheSol.moonTitan.atmosphereComponent(EnumAtmosphericGas.METHANE);
		TheSol.moonTitan.setRelativeSize(0.4312F);
		TheSol.moonTitan.setBiomeInfo(BiomeTitan.titanFlat, BiomeTitan.titanMountain, BiomeTitan.titanOcean);
		TheSol.moonTitan.setDimensionInfo(ConfigManagerSol.dimensionidTitan, WorldProviderTitan.class).setTierRequired(5);
		TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
		TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
		TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
		TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
		TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
		TheSol.moonTitan.setDimensionSuffix("_titan");
		// uranus
		// ariel
		TheSol.moonAriel = (Moon) new Moon("ariel").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(17.69F);
		TheSol.moonAriel.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/ariel.png"));
		TheSol.moonAriel.setRelativeSize(0.4312F);
		TheSol.moonAriel.setBiomeInfo(BiomeAriel.arielFlat);
		TheSol.moonAriel.setDimensionInfo(ConfigManagerSol.dimensionidAriel, WorldProviderAriel.class).setTierRequired(6);
		TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
		TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
		TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
		TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
		TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
		TheSol.moonAriel.setDimensionSuffix("_ariel");
		// umbriel
		TheSol.moonUmbriel = (Moon) new Moon("umbriel").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
		TheSol.moonUmbriel.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/umbriel.png"));
		TheSol.moonUmbriel.setAtmosphere(new AtmosphereInfo(false, false, false, -2.5F, 0.0F, 0.0F));
		TheSol.moonUmbriel.setRelativeSize(0.4312F);
		TheSol.moonUmbriel.setDimensionSuffix("_umbriel");
		// titania
		TheSol.moonTitania = (Moon) new Moon("titania").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(71.54F);
		TheSol.moonTitania.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/titania.png"));
		TheSol.moonTitania.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonTitania.setRelativeSize(0.4312F);
		TheSol.moonTitania.setDimensionSuffix("_titania");
		// oberon
		TheSol.moonOberon = (Moon) new Moon("oberon").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(25.5F, 25.5F)).setRelativeOrbitTime(166.89F);
		TheSol.moonOberon.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/oberon.png"));
		TheSol.moonOberon.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonOberon.setRelativeSize(0.4312F);
		TheSol.moonOberon.setDimensionSuffix("_oberon");
		// neptune
		// triton
		TheSol.moonTriton = (Moon) new Moon("triton").setParentPlanet(TheSol.planetNeptune).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
		TheSol.moonTriton.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/triton.png"));
		TheSol.moonTriton.setAtmosphere(new AtmosphereInfo(false, false, false, -2.5F, 0.0F, 0.0F));
		TheSol.moonTriton.setBiomeInfo(BiomeTriton.tritonFlat);
		TheSol.moonTriton.setDimensionInfo(ConfigManagerSol.dimensionidTriton, WorldProviderTriton.class).setTierRequired(7);
		TheSol.moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
		TheSol.moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
		TheSol.moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
		TheSol.moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
		TheSol.moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
		TheSol.moonTriton.setRelativeSize(0.4312F);
		TheSol.moonTriton.setDimensionSuffix("_triton");
		// pluto
		// charon
		TheSol.moonCharon= (Moon) new Moon("charon").setParentPlanet(TheSol.planetPluto).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(13.5F, 13.5F)).setRelativeOrbitTime(63.87230F);
		TheSol.moonCharon.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/charon.png"));
		TheSol.moonCharon.setRelativeSize(7.0F);
		TheSol.moonCharon.setDimensionSuffix("_charon");
		// register
		// misc
        GalacticraftRegistry.registerRocketGui(WorldProviderMoon.class, new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/moon_rocket_gui.png"));
		// planets
    	GalaxyRegistry.registerPlanet(TheSol.planetMercury);
		GalacticraftRegistry.registerTeleportType(WorldProviderMercury.class, new TeleportTypeMercury());
		GalacticraftRegistry.registerRocketGui(WorldProviderMercury.class, new ResourceLocation(Reference.MOD_ID, "textures/gui/rocketgui/mercury_rocket_gui.png"));
		
		GalaxyRegistry.registerPlanet(TheSol.planetCeres);
		
		GalaxyRegistry.registerPlanet(TheSol.planetPluto);
		GalacticraftRegistry.registerTeleportType(WorldProviderPluto.class, new TeleportTypePluto());
		GalacticraftRegistry.registerRocketGui(WorldProviderMercury.class, new ResourceLocation(Reference.MOD_ID, "textures/gui/rocketgui/pluto_rocket_gui.png"));

		GalaxyRegistry.registerPlanet(TheSol.planetHaumea);

		GalaxyRegistry.registerPlanet(TheSol.planetMakemake);

		GalaxyRegistry.registerPlanet(TheSol.planetEris);

		GalaxyRegistry.registerPlanet(TheSol.planetKuiperBelt);

		GalaxyRegistry.registerPlanet(TheSol.planetSedna);

		GalaxyRegistry.registerPlanet(TheSol.planetOortCloud);
		// moons
    	GalaxyRegistry.registerMoon(TheSol.moonPhobos);
    	
		GalaxyRegistry.registerMoon(TheSol.moonDeimos);
		
    	GalaxyRegistry.registerMoon(TheSol.moonIo);
		GalacticraftRegistry.registerTeleportType(WorldProviderIo.class, new TeleportTypeIo());

    	GalaxyRegistry.registerMoon(TheSol.moonEuropa);
		GalacticraftRegistry.registerTeleportType(WorldProviderEuropa.class, new TeleportTypeEuropa());

    	GalaxyRegistry.registerMoon(TheSol.moonGanymede);

		GalaxyRegistry.registerMoon(TheSol.moonCallisto);
    	
    	GalaxyRegistry.registerMoon(TheSol.moonRingsOfSaturn);
		
    	GalaxyRegistry.registerMoon(TheSol.moonMimas);
		GalacticraftRegistry.registerTeleportType(WorldProviderMimas.class, new TeleportTypeMimas());

    	GalaxyRegistry.registerMoon(TheSol.moonEnceladus);

    	GalaxyRegistry.registerMoon(TheSol.moonTethys);

    	GalaxyRegistry.registerMoon(TheSol.moonDione);

    	GalaxyRegistry.registerMoon(TheSol.moonRhea);

    	GalaxyRegistry.registerMoon(TheSol.moonTitan);
		GalacticraftRegistry.registerTeleportType(WorldProviderTitan.class, new TeleportTypeTitan());
		
    	GalaxyRegistry.registerMoon(TheSol.moonAriel);
		GalacticraftRegistry.registerTeleportType(WorldProviderAriel.class, new TeleportTypeAriel());

		GalaxyRegistry.registerMoon(TheSol.moonUmbriel);
    	
    	GalaxyRegistry.registerMoon(TheSol.moonOberon);
    	
    	GalaxyRegistry.registerMoon(TheSol.moonTriton);
		GalacticraftRegistry.registerTeleportType(WorldProviderTriton.class, new TeleportTypeTriton());

		GalaxyRegistry.registerMoon(TheSol.moonCharon);
    	// fake planets
    	GalaxyRegistry.registerPlanet(TheSol.planetJupiter);
    	GalaxyRegistry.registerPlanet(TheSol.planetSaturn);
    	GalaxyRegistry.registerPlanet(TheSol.planetUranus);
    	GalaxyRegistry.registerPlanet(TheSol.planetNeptune);
    	// override
		//GalaxyRegistry.registerPlanet(TheSol.planetAsteroids);
		GalaxyRegistry.registerPlanet(AsteroidsModule.planetAsteroids);
    	// planets
        
    	// moons
        
        // rockets
        TheSol.registerNonMobEntity(EntityHugeFireball.class, "fireball_huge", 150, 1, false);

        TheSol.registerNonMobEntity(EntityTier4Rocket.class, "rocket_t4", 150, 1, false);
        TheSol.registerNonMobEntity(EntityTier5Rocket.class, "rocket_t5", 150, 1, false);
		TheSol.registerNonMobEntity(EntityTier6Rocket.class, "rocket_t6", 150, 1, false);
		TheSol.registerNonMobEntity(EntityTier7Rocket.class, "rocket_t7", 150, 1, false);
		TheSol.registerNonMobEntity(EntityTier8Rocket.class, "rocket_t8", 150, 1, false);
        // schematic
        SchematicRegistry.registerSchematicRecipe(new SchematicRocketT4());
        ItemSchematicTier4.registerSchematicItems();
        ItemSchematicTier4.registerTextures();
        SchematicRegistry.registerSchematicRecipe(new SchematicRocketT5());
        ItemSchematicTier5.registerSchematicItems();
        ItemSchematicTier5.registerTextures();
		SchematicRegistry.registerSchematicRecipe(new SchematicRocketT6());
		ItemSchematicTier6.registerSchematicItems();
        ItemSchematicTier6.registerTextures();
		SchematicRegistry.registerSchematicRecipe(new SchematicRocketT7());
		ItemSchematicTier7.registerSchematicItems();
		ItemSchematicTier7.registerTextures();
		SchematicRegistry.registerSchematicRecipe(new SchematicRocketT8());
		ItemSchematicTier8.registerSchematicItems();
		ItemSchematicTier8.registerTextures();
		RecipeManagerRocketsTier4.addUniversalRecipes();
        RecipeManagerRocketsTier5.addUniversalRecipes();
        RecipeManagerRocketsTier6.addUniversalRecipes();
		RecipeManagerRocketsTier7.addUniversalRecipes();
		RecipeManagerRocketsTier8.addUniversalRecipes();
    	// skyRegistry
        MinecraftForge.EVENT_BUS.register(new SolEventHandlerClient.TickHandlerClient());
    	// Recipe
    	SolRecipeCompressor.registryRecipe();
    	SolRecipeSmelting.registryRecipe();
    	// oreDict
    	SolOreDict.registerOres();
    	// chest
    	SolTreasureChestRegistry.registry();
    	
        GalacticraftRegistry.addDungeonLoot(4, new ItemStack(SolItems.SCHEMATIC_T4, 1));
        GalacticraftRegistry.addDungeonLoot(5, new ItemStack(SolItems.SCHEMATIC_T5, 1));
		GalacticraftRegistry.addDungeonLoot(6, new ItemStack(SolItems.SCHEMATIC_T6, 1));
		GalacticraftRegistry.addDungeonLoot(7, new ItemStack(SolItems.SCHEMATIC_T7, 1));
		GalacticraftRegistry.addDungeonLoot(8, new ItemStack(SolItems.SCHEMATIC_T8, 1));
        // entity
        SolEntityRegistry.register();
        // dungeon
        GameRegistry.registerTileEntity(TileEntityDungeonSpawnerMercury.class, "Sol Mercury Dungeon Spawner");
        GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_MERCURY);
        GameRegistry.registerTileEntity(TileEntityDungeonSpawnerJupiter.class, "Sol Jupiter Dungeon Spawner");
        GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_JUPITER);
		GameRegistry.registerTileEntity(TileEntityDungeonSpawnerSaturn.class, "Sol Saturn Dungeon Spawner");
		GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_SATURN);
		GameRegistry.registerTileEntity(TileEntityDungeonSpawnerUranus.class, "Sol Uranus Dungeon Spawner");
		GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_URANUS);
		GameRegistry.registerTileEntity(TileEntityDungeonSpawnerNeptune.class, "Sol Neptune Dungeon Spawner");
		GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_NEPTUNE);

		MinecraftForge.EVENT_BUS.register(new TheSol());
	}

	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
		SolDimensions.Mercury = WorldUtil.getDimensionTypeById(ConfigManagerSol.dimensionidMercury);

		SolDimensions.Io = WorldUtil.getDimensionTypeById(ConfigManagerSol.dimensionidIo);
		SolDimensions.Europa = WorldUtil.getDimensionTypeById(ConfigManagerSol.dimensionidEuropa);

		SolDimensions.Mimas = WorldUtil.getDimensionTypeById(ConfigManagerSol.dimensionidMimas);
		SolDimensions.Titan = WorldUtil.getDimensionTypeById(ConfigManagerSol.dimensionidTitan);

		SolDimensions.Ariel = WorldUtil.getDimensionTypeById(ConfigManagerSol.dimensionidAriel);

		SolDimensions.Triton = WorldUtil.getDimensionTypeById(ConfigManagerSol.dimensionidTriton);

		SolDimensions.Pluto = WorldUtil.getDimensionTypeById(ConfigManagerSol.dimensionidPluto);
		SolDimensions.KuiperBelt = WorldUtil.getDimensionTypeById(ConfigManagerSol.dimensionidKuiperBelt);

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier4.class, new TileEntityTreasureTier4ChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier5.class, new TileEntityTreasureTier5ChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier6.class, new TileEntityTreasureTier6ChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier7.class, new TileEntityTreasureTier7ChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier8.class, new TileEntityTreasureTier8ChestRenderer());
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onGuiOpenEvent(GuiOpenEvent event) {
//		if (Config.USE_CUSTOM_CELESTIAL_SELECTION) {
//			if (((event.getGui() instanceof GuiCelestialSelection))) {
//				if (event.getGui().getClass().getName().equalsIgnoreCase("asmodeuscore.core.astronomy.gui.screen.NewGuiCelestialSelection"))
//					MessageUtilities.throwCrashError("Please disable the following option: enableNewGalaxyMap in configs/AsmodeusCore/core.conf");
//				if (GameSettings.isKeyDown(micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient.galaxyMap)) {
//					event.setGui(new CustomCelestialSelection(true, ((GuiCelestialSelection) event.getGui()).possibleBodies, ((GuiCelestialSelection) event.getGui()).canCreateStations));
//				} else {
//					event.setGui(new CustomCelestialSelection(false, ((GuiCelestialSelection) event.getGui()).possibleBodies, ((GuiCelestialSelection) event.getGui()).canCreateStations));
//				}
//			}
//		}
		if (((event.getGui() instanceof GuiCelestialSelection))) {
			if (ConfigManagerSol.enableCustomGalaxymap) {
 				if (event.getGui().getClass().getName().equalsIgnoreCase("asmodeuscore.core.astronomy.gui.screen.NewGuiCelestialSelection"))
					System.err.println("Please disable Asmodeuscore's Galaxymap in configs/AsmodeusCore/core.conf");
				if (GameSettings.isKeyDown(micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient.galaxyMap)) {
					event.setGui(new SolCelestialSelection(true, ((GuiCelestialSelection) event.getGui()).possibleBodies, ((GuiCelestialSelection) event.getGui()).canCreateStations));
				} else {
					event.setGui(new SolCelestialSelection(false, ((GuiCelestialSelection) event.getGui()).possibleBodies, ((GuiCelestialSelection) event.getGui()).canCreateStations));
				}
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onModelBakeEvent(ModelBakeEvent event)
	{
		replaceModelDefault(event, "rocket_t4", "tier4rocket.obj", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemModelRocketT4.class, TRSRTransformation.identity());
		replaceModelDefault(event, "rocket_t5", "tier5rocket.obj", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemModelRocketT5.class, TRSRTransformation.identity());
		replaceModelDefault(event, "rocket_t6", "tier6rocket.obj", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemModelRocketT6.class, TRSRTransformation.identity());
		replaceModelDefault(event, "rocket_t7", "tier7rocket.obj", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemModelRocketT7.class, TRSRTransformation.identity());
		replaceModelDefault(event, "rocket_t8", "tier8rocket.obj", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemModelRocketT8.class, TRSRTransformation.identity());
	}

	private void replaceModelDefault(ModelBakeEvent event, String resLoc, String objLoc, List<String> visibleGroups, Class<? extends ModelTransformWrapper> clazz, IModelState parentState, String... variants)
	{
		ClientUtil.replaceModel(Reference.MOD_ID, event, resLoc, objLoc, visibleGroups, clazz, parentState, variants);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void loadTextures(TextureStitchEvent.Pre event)
	{
		registerTexture(event, "tier4rocket");
		registerTexture(event, "tier5rocket");
		registerTexture(event, "tier6rocket");
		registerTexture(event, "tier7rocket");
		registerTexture(event, "tier8rocket");
	}

	private void registerTexture(TextureStitchEvent.Pre event, String texture)
	{
		event.getMap().registerSprite(new ResourceLocation(Reference.MOD_ID + ":" + "rockets/" + texture));
	}

	public static void registerNonMobEntity(Class<? extends Entity> var0, String var1, int trackingDistance, int updateFreq, boolean sendVel)
    {
        ResourceLocation registryName = new ResourceLocation(Reference.MOD_ID, var1);
        EntityRegistry.registerModEntity(registryName, var0, var1, GCCoreUtil.nextInternalID(), TheSol.instance, trackingDistance, updateFreq, sendVel);
    }
	
	public static void registerEntityCreature(Class<? extends Entity> clazz, String name, int back, int fore)
    {
        TheSol.registerNonMobEntity(clazz, name, 80, 3, true);
        int nextEggID = GCCoreUtil.getNextValidID();
        if (nextEggID < 65536)
        {
            ResourceLocation resourcelocation = new ResourceLocation(Reference.MOD_ID, name);
            EntityList.ENTITY_EGGS.put(resourcelocation, new EntityList.EntityEggInfo(resourcelocation, back, fore));
        }
    }
}