package mod.sol;

import java.util.List;

import com.google.common.collect.ImmutableList;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.model.OBJLoaderGC;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedEnderman;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import micdoodle8.mods.galacticraft.core.wrappers.ModelTransformWrapper;
import micdoodle8.mods.galacticraft.planets.asteroids.ConfigManagerAsteroids;
import micdoodle8.mods.galacticraft.planets.asteroids.dimension.WorldProviderAsteroids;
import micdoodle8.mods.galacticraft.planets.mars.ConfigManagerMars;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import micdoodle8.mods.galacticraft.planets.mars.dimension.TeleportTypeMars;
import micdoodle8.mods.galacticraft.planets.mars.world.gen.BiomeMars;
import micdoodle8.mods.galacticraft.planets.venus.ConfigManagerVenus;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import micdoodle8.mods.galacticraft.planets.venus.world.gen.BiomeVenus;
import mod.sol.entities.boss.EntityUranusBossSlime;
import mod.sol.planets.uranus.moon.ariel.biome.BiomeAriel;
import mod.sol.planets.uranus.moon.ariel.dimension.TeleportTypeAriel;
import mod.sol.planets.uranus.moon.ariel.dimension.WorldProviderAriel;
import mod.sol.render.entity.*;
import mod.sol.render.model.item.ItemModelRocketT4;
import mod.sol.render.model.item.ItemModelRocketT5;
import mod.sol.render.model.item.ItemModelRocketT6;
import mod.sol.render.rocket.RenderTier4Rocket;
import mod.sol.render.rocket.RenderTier5Rocket;
import mod.sol.render.rocket.RenderTier6Rocket;
import mod.sol.render.tile.TileEntityTreasureTier7ChestRenderer;
import mod.sol.tile.*;
import mod.sol.util.Reference;
import mod.sol.util.SolEntityRegistry;
import mod.sol.util.SolTreasureChestRegistry;
import mod.sol.util.handler.SolEventHandlerClient;
import mod.realistic_galaxy_map.RealisticGalaxyMap;
import mod.realistic_galaxy_map.api.client.galaxy.MoonRealistic;
import mod.realistic_galaxy_map.api.client.galaxy.PlanetRealistic;
import mod.realistic_galaxy_map.api.client.galaxy.StarRealistic;
import mod.realistic_galaxy_map.dimension.WorldProviderRealisticMars;
import mod.realistic_galaxy_map.dimension.WorldProviderRealisticMoon;
import mod.realistic_galaxy_map.dimension.WorldProviderRealisticVenus;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
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
import mod.sol.entities.boss.EntityJupiterBossGhast;
import mod.sol.entities.boss.EntityMercuryBossBlaze;
import mod.sol.entities.boss.EntitySaturnBossStray;
import mod.sol.entities.rocket.EntityTier4Rocket;
import mod.sol.entities.rocket.EntityTier5Rocket;
import mod.sol.entities.rocket.EntityTier6Rocket;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolDimensions;
import mod.sol.init.SolItems;
import mod.sol.init.SolOreDict;
import mod.sol.items.ItemSchematicTier4;
import mod.sol.items.ItemSchematicTier5;
import mod.sol.items.ItemSchematicTier6;
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
import mod.sol.recipe.RecipeManagerRocketsTier4;
import mod.sol.recipe.RecipeManagerRocketsTier5;
import mod.sol.recipe.RecipeManagerRocketsTier6;
import mod.sol.recipe.SolRecipeCompressor;
import mod.sol.recipe.SolRecipeSmelting;
import mod.sol.render.tile.TileEntityTreasureTier4ChestRenderer;
import mod.sol.render.tile.TileEntityTreasureTier5ChestRenderer;
import mod.sol.render.tile.TileEntityTreasureTier6ChestRenderer;
import mod.sol.schematic.SchematicRocketT4;
import mod.sol.schematic.SchematicRocketT5;
import mod.sol.schematic.SchematicRocketT6;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, dependencies = "required-after:galacticraftcore; required-after:realistic_galaxy_map")
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
	
	// solarsystem
    // planets
	public static PlanetRealistic planetMercury;
	public static PlanetRealistic planetCeres;
	public static PlanetRealistic planetPluto;
	public static PlanetRealistic planetKuiperBelt;
	public static PlanetRealistic planetSedna;
	public static PlanetRealistic planetOortCloud;
	// override
	public static PlanetRealistic planetAsteroids;
	// fake
	public static Planet planetJupiter;
	public static Planet planetSaturn;
	public static Planet planetUranus;
	public static Planet planetNeptune;
	// moons
	// mars
	public static MoonRealistic moonPhobos;
	public static MoonRealistic moonDeimos;
	// jupiter
	public static MoonRealistic moonIo;
	public static MoonRealistic moonEuropa;
	public static MoonRealistic moonGanymede;
	public static MoonRealistic moonCallisto;
	// saturn
	public static MoonRealistic moonRingsOfSaturn;
	public static MoonRealistic moonMimas;
	public static MoonRealistic moonEnceladus;
	public static MoonRealistic moonTethys;
	public static MoonRealistic moonDione;
	public static MoonRealistic moonRhea;
	public static MoonRealistic moonTitan;
	// uranus
	public static MoonRealistic moonAriel;
	public static MoonRealistic moonUmbriel;
	public static MoonRealistic moonTitania;
	public static MoonRealistic moonOberon;
	// neptunus
	public static MoonRealistic moonTriton;
	// pluto
	public static MoonRealistic moonCharon;

	@Instance
	public static TheSol instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static SolCommonProxy proxy;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		RealisticGalaxyMap.disableoverrideDefaultPlanets = true;
		RealisticGalaxyMap.disableDynamicTierSystem = true;
        OBJLoaderGC.instance.addDomain(Reference.MOD_ID);
		RenderingRegistry.registerEntityRenderingHandler(EntityTier4Rocket.class, (RenderManager manager) -> new RenderTier4Rocket(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityTier5Rocket.class, (RenderManager manager) -> new RenderTier5Rocket(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityTier6Rocket.class, (RenderManager manager) -> new RenderTier6Rocket(manager));
		MinecraftForge.EVENT_BUS.register(this);

        RenderingRegistry.registerEntityRenderingHandler(EntityHugeFireball.class, (RenderManager manager) -> new RenderHugeFireball(manager, 1));

        RenderingRegistry.registerEntityRenderingHandler(EntityMercuryBossBlaze.class, (RenderManager manager) -> new RenderMercuryBossBlaze(manager));
        RenderingRegistry.registerEntityRenderingHandler(EntityJupiterBossGhast.class, (RenderManager manager) -> new RenderJupiterBossGhast(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntitySaturnBossStray.class, (RenderManager manager) -> new RenderSaturnBossStray(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityUranusBossSlime.class, (RenderManager manager) -> new RenderUranusBossSlime(manager));
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		StarRealistic starSol = (StarRealistic) new StarRealistic("sol").setParentSolarSystem(GalacticraftCore.solarSystemSol).setTierRequired(-1);
        starSol.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/sun.png"));
        starSol.setDynamicScaleDistance(0, 0);
        GalacticraftCore.solarSystemSol.setMainStar(starSol);
        
    	RealisticGalaxyMap.planetMars = (PlanetRealistic) new PlanetRealistic("mars").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.67F, 0.1F, 0.1F).setPhaseShift(0.1667F).setRelativeSize(0.5319F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.25F, 1.25F)).setRelativeOrbitTime(1.8811610076670317634173055859803F);
        RealisticGalaxyMap.planetMars.setBiomeInfo(BiomeMars.marsFlat);
    	RealisticGalaxyMap.planetMars.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/mars.png"));
        RealisticGalaxyMap.planetMars.setDimensionInfo(ConfigManagerMars.dimensionIDMars, WorldProviderRealisticMars.class).setTierRequired(2);
        RealisticGalaxyMap.planetMars.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.3F, 0.1F));
        RealisticGalaxyMap.planetMars.atmosphereComponent(EnumAtmosphericGas.CO2).atmosphereComponent(EnumAtmosphericGas.ARGON).atmosphereComponent(EnumAtmosphericGas.NITROGEN);
        RealisticGalaxyMap.planetMars.addMobInfo(new Biome.SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
        RealisticGalaxyMap.planetMars.addMobInfo(new Biome.SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
        RealisticGalaxyMap.planetMars.addMobInfo(new Biome.SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
        RealisticGalaxyMap.planetMars.addMobInfo(new Biome.SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
        RealisticGalaxyMap.planetMars.addMobInfo(new Biome.SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        RealisticGalaxyMap.planetMars.addChecklistKeys("equip_oxygen_suit", "thermal_padding");
        RealisticGalaxyMap.planetMars.setDynamicTierRequired(8);
        GalaxyRegistry.registerPlanet(RealisticGalaxyMap.planetMars);
        GalacticraftRegistry.registerTeleportType(WorldProviderRealisticMars.class, new TeleportTypeMars());

        RealisticGalaxyMap.planetVenus = (PlanetRealistic) new PlanetRealistic("venus").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(2.0F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(0.75F, 0.75F)).setRelativeOrbitTime(0.61527929901423877327491785323111F);
        RealisticGalaxyMap.planetVenus.setBiomeInfo(BiomeVenus.venusFlat, BiomeVenus.venusMountain, BiomeVenus.venusValley);
        RealisticGalaxyMap.planetVenus.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/venus.png"));
        RealisticGalaxyMap.planetVenus.setDimensionInfo(ConfigManagerVenus.dimensionIDVenus, WorldProviderRealisticVenus.class).setTierRequired(3);
        RealisticGalaxyMap.planetVenus.setAtmosphere(new AtmosphereInfo(false, true, true, 5.0F, 0.3F, 54.0F));
        RealisticGalaxyMap.planetVenus.atmosphereComponent(EnumAtmosphericGas.CO2).atmosphereComponent(EnumAtmosphericGas.NITROGEN);
        RealisticGalaxyMap.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
        RealisticGalaxyMap.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
        RealisticGalaxyMap.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
        RealisticGalaxyMap.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
        RealisticGalaxyMap.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        RealisticGalaxyMap.planetVenus.addChecklistKeys("equip_oxygen_suit", "equip_shield_controller", "thermal_padding_t2");
        RealisticGalaxyMap.planetVenus.setDynamicTierRequired(3);
        GalaxyRegistry.registerPlanet(RealisticGalaxyMap.planetVenus);
        GalacticraftRegistry.registerTeleportType(WorldProviderRealisticVenus.class, new TeleportTypeVenus());
		
		// fake planets
		TheSol.planetJupiter = (Planet) new Planet("jupiter").setParentSolarSystem(GalacticraftCore.solarSystemSol);
		TheSol.planetJupiter.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.0F, 2.0F)).setRelativeOrbitTime(11.861993428258488499452354874042F);
		TheSol.planetJupiter.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/jupiter.png"));
		
		TheSol.planetSaturn = (Planet) new Planet("saturn").setParentSolarSystem(GalacticraftCore.solarSystemSol);
		TheSol.planetSaturn.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(5.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.25F, 2.25F)).setRelativeOrbitTime(29.463307776560788608981380065717F);
		TheSol.planetSaturn.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/saturn.png"));
		
		TheSol.planetUranus = (Planet) new Planet("uranus").setParentSolarSystem(GalacticraftCore.solarSystemSol);
		TheSol.planetUranus.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.38F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.5F, 2.5F)).setRelativeOrbitTime(84.063526834611171960569550930997F);
		TheSol.planetUranus.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/uranus.png"));

		TheSol.planetNeptune = (Planet) new Planet("neptune").setParentSolarSystem(GalacticraftCore.solarSystemSol);
		TheSol.planetNeptune.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.0F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.75F, 2.75F)).setRelativeOrbitTime(164.84118291347207009857612267251F);
		TheSol.planetNeptune.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/neptune.png"));
		// override
        TheSol.planetAsteroids = (PlanetRealistic) new PlanetRealistic("asteroids").setParentSolarSystem(GalacticraftCore.solarSystemSol);
        TheSol.planetAsteroids.setDimensionInfo(ConfigManagerAsteroids.dimensionIDAsteroids, WorldProviderAsteroids.class).setTierRequired(3);
        TheSol.planetAsteroids.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.55F, 1.55F)).setRelativeOrbitTime(45.0F).setPhaseShift((float) (Math.random() * (2 * Math.PI)));
        TheSol.planetAsteroids.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/asteroid.png"));
        TheSol.planetAsteroids.setAtmosphere(new AtmosphereInfo(false, false, false, -1.5F, 0.05F, 0.0F));
        TheSol.planetAsteroids.addChecklistKeys("equip_oxygen_suit", "craft_grapple_hook", "thermal_padding");
		TheSol.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
		TheSol.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
		TheSol.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
		TheSol.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
		TheSol.planetAsteroids.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
		TheSol.planetAsteroids.setDynamicTierRequired(9);
        // planets
		// mercury
		TheSol.planetMercury = (PlanetRealistic) new PlanetRealistic("mercury").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(0.5F, 0.5F)).setRelativeOrbitTime(0.24096385542168674698795180722892F);
		TheSol.planetMercury.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/mercury.png"));
		TheSol.planetMercury.setAtmosphere(new AtmosphereInfo(false, false, false, 5.0F, 0.0F, 0.0F));
		TheSol.planetMercury.setRelativeSize(0.4312F);
		TheSol.planetMercury.setDimensionInfo(-(Reference.MOD_ID.hashCode() + 100), WorldProviderMercury.class).setTierRequired(3).setBiomeInfo(BiomeMercury.mercuryFlat);
		TheSol.planetMercury.setAtmosphere(new AtmosphereInfo(false, false, false, 5.0F, 0.0F, 0.0F));
		TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
		TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
		TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
		TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
		TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
		TheSol.planetMercury.setDimensionSuffix("_mercury");
		TheSol.planetMercury.setDynamicTierRequired(2);
		// pluto
		TheSol.planetPluto = (PlanetRealistic) new PlanetRealistic("pluto").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(3.25F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.0F, 3.0F)).setRelativeOrbitTime(5.2433153256534542F);
		TheSol.planetPluto.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/pluto.png"));
		//TheSol.planetPluto.setAtmosphere(new AtmosphereInfo(false, false, false, -5.0F, 0.0F, 0.0F));
		TheSol.planetPluto.setRelativeSize(0.1294F);
		/*TheSol.planetPluto.setDimensionInfo(-(Reference.MOD_ID.hashCode() + 900), WorldProviderPluto.class).setTierRequired(8);
		TheSol.planetPluto.setBiomeInfo(BiomePluto.plutoFlat, BiomePluto.plutoSnowfield);
		TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
		TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
		TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
		TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
		TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));*/
		TheSol.planetPluto.setDimensionSuffix("_pluto");
		TheSol.planetPluto.setDynamicTierRequired(14);
		// kuiperbelt
		TheSol.planetKuiperBelt = (PlanetRealistic) new PlanetRealistic("kuiper_belt").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) (Math.random() * (2 * Math.PI))).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.55F, 3.55F)).setRelativeOrbitTime(90.0F);
		TheSol.planetKuiperBelt.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/kuiper_belt.png"));
		TheSol.planetKuiperBelt.setDimensionSuffix("_kuiper_belt");
		// sedna
		TheSol.planetSedna = (PlanetRealistic) new PlanetRealistic("sedna").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(7F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(5F, 5F)).setRelativeOrbitTime(64.143442132456F);
		TheSol.planetSedna.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/sedna.png"));
		TheSol.planetSedna.setDimensionSuffix("_sedna");
		// oortcloud
		TheSol.planetOortCloud = (PlanetRealistic) new PlanetRealistic("oort_cloud").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) (Math.random() * (2 * Math.PI))).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(7.5F, 7.5F)).setRelativeOrbitTime(90.0F);
		TheSol.planetOortCloud.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/oort_cloud.png"));
		TheSol.planetOortCloud.setDimensionSuffix("_oort_cloud");
		// ceres
		TheSol.planetCeres = (PlanetRealistic) new PlanetRealistic("ceres").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(3.25F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.35F, 1.35F)).setRelativeOrbitTime(5.2433153256534542F);
		TheSol.planetCeres.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/ceres.png"));
		TheSol.planetCeres.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.planetCeres.setRelativeSize(0.1294F);
		TheSol.planetCeres.setDimensionSuffix("_ceres");
		TheSol.planetCeres.setDynamicTierRequired(9);
		// moons
		// phobos
		TheSol.moonPhobos = (MoonRealistic) new MoonRealistic("phobos").setParentPlanet(MarsModule.planetMars).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12F, 12F)).setRelativeOrbitTime(20.0F);
		TheSol.moonPhobos.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/phobos.png"));
		TheSol.moonPhobos.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonPhobos.setRelativeSize(0.4312F);
		TheSol.moonPhobos.setDimensionSuffix("_phobos");
		TheSol.moonPhobos.setDynamicTierRequired(8);
		// deimos
		TheSol.moonDeimos = (MoonRealistic) new MoonRealistic("deimos").setParentPlanet(MarsModule.planetMars).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(0.25F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(20F, 20F)).setRelativeOrbitTime(45.68F);
		TheSol.moonDeimos.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/deimos.png"));
		TheSol.moonDeimos.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonDeimos.setRelativeSize(0.4312F);
		TheSol.moonDeimos.setDimensionSuffix("_deimos");
		TheSol.moonDeimos.setDynamicTierRequired(8);
		// io
		TheSol.moonIo = (MoonRealistic) new MoonRealistic("io").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(17.69F);
		TheSol.moonIo.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/io.png"));
		TheSol.moonIo.setAtmosphere(new AtmosphereInfo(false, false, false, -2.0F, 0.0F, 0.0F));
		TheSol.moonIo.setRelativeSize(0.4312F);
		TheSol.moonIo.setBiomeInfo(BiomeIo.ioFlat, BiomeIo.ioAshLand, BiomeIo.ioSulfurField);
		TheSol.moonIo.setDimensionInfo(-(Reference.MOD_ID.hashCode() + 501), WorldProviderIo.class).setTierRequired(4);
		TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
		TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
		TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
		TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
		TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
		TheSol.moonIo.setDimensionSuffix("_io");
		TheSol.moonIo.setDynamicTierRequired(11);
		// europa
		TheSol.moonEuropa = (MoonRealistic) new MoonRealistic("europa").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
		TheSol.moonEuropa.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/europa.png"));
		TheSol.moonEuropa.setAtmosphere(new AtmosphereInfo(false, false, false, -2.0F, 0.0F, 0.0F));
		TheSol.moonEuropa.setRelativeSize(0.4312F);
		TheSol.moonEuropa.setBiomeInfo(BiomeEuropa.europaFlat, BiomeEuropa.europaMountain, BiomeEuropa.europaValley);
		TheSol.moonEuropa.setDimensionInfo(-(Reference.MOD_ID.hashCode() + 502), WorldProviderEuropa.class).setTierRequired(4);
		TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
		TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
		TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
		TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
		TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
		TheSol.moonEuropa.setDimensionSuffix("_europa");
		TheSol.moonEuropa.setDynamicTierRequired(11);
		// ganymede
		TheSol.moonGanymede = (MoonRealistic) new MoonRealistic("ganymede").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(71.54F);
		TheSol.moonGanymede.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/ganymede.png"));
		TheSol.moonGanymede.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonGanymede.setRelativeSize(0.4312F);
		TheSol.moonGanymede.setDimensionSuffix("_ganymede");
		TheSol.moonGanymede.setDynamicTierRequired(11);
		// callisto
		TheSol.moonCallisto = (MoonRealistic) new MoonRealistic("callisto").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(25.5F, 25.5F)).setRelativeOrbitTime(166.89F);
		TheSol.moonCallisto.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/callisto.png"));
		TheSol.moonCallisto.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonCallisto.setRelativeSize(0.4312F);
		TheSol.moonCallisto.setDimensionSuffix("_callisto");
		TheSol.moonCallisto.setDynamicTierRequired(11);
		// saturn
		// ringsofsaturn
		TheSol.moonRingsOfSaturn = (MoonRealistic) new MoonRealistic("rings_of_saturn").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(5.0F, 5.0F)).setRelativeOrbitTime(90.0F);
		TheSol.moonRingsOfSaturn.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/rings_of_saturn.png"));
		TheSol.moonRingsOfSaturn.setRelativeSize(0.4312F);
		TheSol.moonRingsOfSaturn.setDimensionSuffix("_rings_of_saturn");
		TheSol.moonRingsOfSaturn.setDynamicTierRequired(12);
		// mimas
		TheSol.moonMimas = (MoonRealistic) new MoonRealistic("mimas").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(9.375F);
		TheSol.moonMimas.setAtmosphere(new AtmosphereInfo(false, false, false, -3.0F, 0.0F, 0.0F));
		TheSol.moonMimas.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/mimas.png"));
		TheSol.moonMimas.setRelativeSize(0.4312F);
		TheSol.moonMimas.setBiomeInfo(BiomeMimas.mimasFlat);
		TheSol.moonMimas.setDimensionInfo(-(Reference.MOD_ID.hashCode() + 601), WorldProviderMimas.class).setTierRequired(5);
		TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
		TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
		TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
		TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
		TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
		TheSol.moonMimas.setDimensionSuffix("_mimas");
		TheSol.moonMimas.setDynamicTierRequired(12);
		// enceladus
		TheSol.moonEnceladus= (MoonRealistic) new MoonRealistic("enceladus").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(13.70218F);
		TheSol.moonEnceladus.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/enceladus.png"));
		TheSol.moonEnceladus.setRelativeSize(0.4312F);
		TheSol.moonEnceladus.setDimensionSuffix("_enceladus");
		TheSol.moonEnceladus.setDynamicTierRequired(12);
		// tethys
		TheSol.moonTethys= (MoonRealistic) new MoonRealistic("tethys").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(16.5F, 16.5F)).setRelativeOrbitTime(18.87802F);
		TheSol.moonTethys.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/tethys.png"));
		TheSol.moonTethys.setRelativeSize(0.4312F);
		TheSol.moonTethys.setDimensionSuffix("_tethys");
		TheSol.moonTethys.setDynamicTierRequired(12);
		// dione
		TheSol.moonDione= (MoonRealistic) new MoonRealistic("dione").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(27.36915F);
		TheSol.moonDione.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/dione.png"));
		TheSol.moonDione.setRelativeSize(0.4312F);
		TheSol.moonDione.setDimensionSuffix("_dione");
		TheSol.moonDione.setDynamicTierRequired(12);
		// rhea
		TheSol.moonRhea= (MoonRealistic) new MoonRealistic("rhea").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(23.5F, 23.5F)).setRelativeOrbitTime(45.18212F);
		TheSol.moonRhea.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/rhea.png"));
		TheSol.moonRhea.setRelativeSize(0.4312F);
		TheSol.moonRhea.setDimensionSuffix("_rhea");
		TheSol.moonRhea.setDynamicTierRequired(12);
		// titan
		TheSol.moonTitan= (MoonRealistic) new MoonRealistic("titan").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(26.5F, 26.5F)).setRelativeOrbitTime(159.45F);
		TheSol.moonTitan.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/titan.png"));
		TheSol.moonTitan.setAtmosphere(new AtmosphereInfo(false, false, false, -3.0F, 0.5F, 0.3F));
		TheSol.moonTitan.atmosphereComponent(EnumAtmosphericGas.METHANE);
		TheSol.moonTitan.setRelativeSize(0.4312F);
		TheSol.moonTitan.setBiomeInfo(BiomeTitan.titanFlat, BiomeTitan.titanMountain, BiomeTitan.titanOcean);
		TheSol.moonTitan.setDimensionInfo(-(Reference.MOD_ID.hashCode() + 606), WorldProviderTitan.class).setTierRequired(5);
		TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
		TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
		TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
		TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
		TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
		TheSol.moonTitan.setDimensionSuffix("_titan");
		TheSol.moonTitan.setDynamicTierRequired(12);
		// uranus
		// ariel
		TheSol.moonAriel = (MoonRealistic) new MoonRealistic("ariel").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(17.69F);
		TheSol.moonAriel.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/ariel.png"));
		TheSol.moonAriel.setRelativeSize(0.4312F);
		TheSol.moonAriel.setBiomeInfo(BiomeAriel.arielFlat);
		TheSol.moonAriel.setDimensionInfo(-(Reference.MOD_ID.hashCode() + 701), WorldProviderAriel.class).setTierRequired(6);
		TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
		TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
		TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
		TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
		TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
		TheSol.moonAriel.setDimensionSuffix("_ariel");
		TheSol.moonAriel.setDynamicTierRequired(12);
		// umbriel
		TheSol.moonUmbriel = (MoonRealistic) new MoonRealistic("umbriel").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
		TheSol.moonUmbriel.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/umbriel.png"));
		TheSol.moonUmbriel.setAtmosphere(new AtmosphereInfo(false, false, false, -2.5F, 0.0F, 0.0F));
		TheSol.moonUmbriel.setRelativeSize(0.4312F);
		TheSol.moonUmbriel.setDimensionSuffix("_umbriel");
		TheSol.moonUmbriel.setDynamicTierRequired(12);
		// titania
		TheSol.moonTitania = (MoonRealistic) new MoonRealistic("titania").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(71.54F);
		TheSol.moonTitania.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/titania.png"));
		TheSol.moonTitania.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonTitania.setRelativeSize(0.4312F);
		TheSol.moonTitania.setDimensionSuffix("_titania");
		TheSol.moonTitania.setDynamicTierRequired(12);
		// oberon
		TheSol.moonOberon = (MoonRealistic) new MoonRealistic("oberon").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(25.5F, 25.5F)).setRelativeOrbitTime(166.89F);
		TheSol.moonOberon.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/oberon.png"));
		TheSol.moonOberon.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
		TheSol.moonOberon.setRelativeSize(0.4312F);
		TheSol.moonOberon.setDimensionSuffix("_oberon");
		TheSol.moonOberon.setDynamicTierRequired(12);
		// neptune
		// triton
		TheSol.moonTriton = (MoonRealistic) new MoonRealistic("triton").setParentPlanet(TheSol.planetNeptune).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
		TheSol.moonTriton.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/triton.png"));
		TheSol.moonTriton.setAtmosphere(new AtmosphereInfo(false, false, false, -2.5F, 0.0F, 0.0F));
		TheSol.moonTriton.setRelativeSize(0.4312F);
		TheSol.moonTriton.setDimensionSuffix("_triton");
		TheSol.moonTriton.setDynamicTierRequired(13);
		// pluto
		// charon
		TheSol.moonCharon= (MoonRealistic) new MoonRealistic("charon").setParentPlanet(TheSol.planetPluto).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(13.5F, 13.5F)).setRelativeOrbitTime(63.87230F);
		TheSol.moonCharon.setBodyIcon(new ResourceLocation(Reference.MOD_ID, "textures/planets/charon.png"));
		TheSol.moonCharon.setRelativeSize(7.0F);
		TheSol.moonCharon.setDimensionSuffix("_charon");
		TheSol.moonCharon.setDynamicTierRequired(14);
		// register
		// misc
        GalacticraftRegistry.registerRocketGui(WorldProviderRealisticMoon.class, new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/moon_rocket_gui.png"));
		// planets
    	GalaxyRegistry.registerPlanet(TheSol.planetMercury);
		GalacticraftRegistry.registerTeleportType(WorldProviderMercury.class, new TeleportTypeMercury());
		GalacticraftRegistry.registerRocketGui(WorldProviderMercury.class, new ResourceLocation(Reference.MOD_ID, "textures/gui/rocketgui/mercury_rocket_gui.png"));
		
		GalaxyRegistry.registerPlanet(TheSol.planetCeres);
		
		GalaxyRegistry.registerPlanet(TheSol.planetPluto);
		GalacticraftRegistry.registerTeleportType(WorldProviderPluto.class, new TeleportTypePluto());
		GalacticraftRegistry.registerRocketGui(WorldProviderMercury.class, new ResourceLocation(Reference.MOD_ID, "textures/gui/rocketgui/pluto_rocket_gui.png"));
		
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
    	
    	GalaxyRegistry.registerMoon(TheSol.moonCharon);
    	// fake planets
    	GalaxyRegistry.registerPlanet(TheSol.planetJupiter);
    	GalaxyRegistry.registerPlanet(TheSol.planetSaturn);
    	GalaxyRegistry.registerPlanet(TheSol.planetUranus);
    	GalaxyRegistry.registerPlanet(TheSol.planetNeptune);
    	// override
        GalaxyRegistry.registerPlanet(TheSol.planetAsteroids);
    	// planets
        
    	// moons
        
        // rockets
        TheSol.registerNonMobEntity(EntityHugeFireball.class, "fireball_huge", 150, 1, false);

        TheSol.registerNonMobEntity(EntityTier4Rocket.class, "rocket_t4", 150, 1, false);
        TheSol.registerNonMobEntity(EntityTier5Rocket.class, "rocket_t5", 150, 1, false);
        TheSol.registerNonMobEntity(EntityTier6Rocket.class, "rocket_t6", 150, 1, false);
        // TheSol.registerNonMobEntity(EntityTier8Rocket.class, "rocket_t8", 150, 1, false);
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
        RecipeManagerRocketsTier4.addUniversalRecipes();
        RecipeManagerRocketsTier5.addUniversalRecipes();
        RecipeManagerRocketsTier6.addUniversalRecipes();
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

		MinecraftForge.EVENT_BUS.register(new TheSol());
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
		SolDimensions.Mercury = WorldUtil.getDimensionTypeById(-(Reference.MOD_ID.hashCode() + 100));
		SolDimensions.Pluto = WorldUtil.getDimensionTypeById(-(Reference.MOD_ID.hashCode() + 900));

		SolDimensions.Io = WorldUtil.getDimensionTypeById(-(Reference.MOD_ID.hashCode() + 501));
		SolDimensions.Europa = WorldUtil.getDimensionTypeById(-(Reference.MOD_ID.hashCode() + 502));

		SolDimensions.Mimas = WorldUtil.getDimensionTypeById(-(Reference.MOD_ID.hashCode() + 601));
		SolDimensions.Titan = WorldUtil.getDimensionTypeById(-(Reference.MOD_ID.hashCode() + 606));

		SolDimensions.Ariel = WorldUtil.getDimensionTypeById(-(Reference.MOD_ID.hashCode() + 701));

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier4.class, new TileEntityTreasureTier4ChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier5.class, new TileEntityTreasureTier5ChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier6.class, new TileEntityTreasureTier6ChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier7.class, new TileEntityTreasureTier7ChestRenderer());
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onModelBakeEvent(ModelBakeEvent event)
	{
		replaceModelDefault(event, "rocket_t4", "tier4rocket.obj", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemModelRocketT4.class, TRSRTransformation.identity());
		replaceModelDefault(event, "rocket_t5", "tier5rocket.obj", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemModelRocketT5.class, TRSRTransformation.identity());
		replaceModelDefault(event, "rocket_t6", "tier6rocket.obj", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemModelRocketT6.class, TRSRTransformation.identity());
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