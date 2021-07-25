package mod.sol.planets.mercury;

import java.util.List;

import com.google.common.collect.ImmutableList;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.core.wrappers.ModelTransformWrapper;
import micdoodle8.mods.galacticraft.planets.IPlanetsModuleClient;
import mod.sol.entities.rocket.EntityTier4Rocket;
import mod.sol.init.SolItems;
import mod.sol.render.model.item.ItemModelRocketT4;
import mod.sol.render.rocket.RenderTier4Rocket;
import mod.sol.util.Reference;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MercuryModuleClient implements IPlanetsModuleClient {
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityTier4Rocket.class, (RenderManager manager) -> new RenderTier4Rocket(manager));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void registerVariants()
    {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(Reference.MOD_ID + ":" + "rocket_t4", "inventory");
        for (int i = 0; i < 5; ++i)
        {
            ModelLoader.setCustomModelResourceLocation(SolItems.ROCKET_T4, i, modelResourceLocation);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onModelBakeEvent(ModelBakeEvent event)
    {
    	replaceModelDefault(event, "rocket_t4", "tier4rocket.obj", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemModelRocketT4.class, TRSRTransformation.identity());
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
    }

    private void registerTexture(TextureStitchEvent.Pre event, String texture)
    {
        event.getMap().registerSprite(new ResourceLocation(Reference.MOD_ID + ":" + "rockets/" + texture));
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        MercuryModuleClient clientEventHandler = new MercuryModuleClient();
        MinecraftForge.EVENT_BUS.register(clientEventHandler);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
    }

    public static void registerBlockRenderers()
    {
    }

    private void addPlanetVariants(String name, String... variants)
    {
        Item itemBlockVariants = Item.REGISTRY.getObject(new ResourceLocation(Reference.MOD_ID, name));
        ResourceLocation[] variants0 = new ResourceLocation[variants.length];
        for (int i = 0; i < variants.length; ++i)
        {
            variants0[i] = new ResourceLocation(Reference.MOD_ID + ":" + variants[i]);
        }
        ModelBakery.registerItemVariants(itemBlockVariants, variants0);
    }

    @Override
    public void getGuiIDs(List<Integer> idList)
    {
    }

    @Override
    public Object getGuiElement(Side side, int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public void spawnParticle(String particleID, Vector3 position, Vector3 motion, Object... extraData)
    {
    }
}
