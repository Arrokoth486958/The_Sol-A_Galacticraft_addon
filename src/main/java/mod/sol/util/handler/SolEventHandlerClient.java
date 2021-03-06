package mod.sol.util.handler;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import mod.sol.planets.neptune.triton.dimension.WorldProviderTriton;
import mod.sol.planets.neptune.triton.sky.SkyProviderTriton;
import mod.sol.planets.sedna.dimension.WorldProviderSedna;
import mod.sol.planets.sedna.sky.SkyProviderSedna;
import mod.sol.planets.uranus.moon.ariel.dimension.WorldProviderAriel;
import mod.sol.planets.uranus.moon.ariel.sky.SkyProviderAriel;
import org.lwjgl.opengl.GL11;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import mod.sol.TheSol;
import mod.sol.planets.jupiter.moons.europa.dimension.WorldProviderEuropa;
import mod.sol.planets.jupiter.moons.europa.sky.SkyProviderEuropa;
import mod.sol.planets.jupiter.moons.io.dimension.WorldProviderIo;
import mod.sol.planets.jupiter.moons.io.sky.SkyProviderIo;
import mod.sol.planets.mercury.dimension.WorldProviderMercury;
import mod.sol.planets.mercury.sky.SkyProviderMercury;
import mod.sol.planets.pluto.dimension.WorldProviderPluto;
import mod.sol.planets.pluto.sky.SkyProviderPluto;
import mod.sol.planets.saturn.moons.mimas.dimension.WorldProviderMimas;
import mod.sol.planets.saturn.moons.mimas.sky.SkyProviderMimas;
import mod.sol.planets.saturn.moons.titan.dimension.WorldProviderTitan;
import mod.sol.planets.saturn.moons.titan.sky.SkyProviderTitan;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SolEventHandlerClient {
	public static class TickHandlerClient
    {
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onClientTick(ClientTickEvent event)
        {
            final Minecraft minecraft = FMLClientHandler.instance().getClient();

            final WorldClient world = minecraft.world;

            if (world != null)
            {
                if (world.provider instanceof WorldProviderMercury)
                {
                    if (world.provider.getSkyRenderer() == null)
                    {
                        world.provider.setSkyRenderer(new SkyProviderMercury((IGalacticraftWorldProvider) world.provider));
                    }

                    if (world.provider.getCloudRenderer() == null)
                    {
                        world.provider.setCloudRenderer(new CloudRenderer());
                    }
                }
                if (world.provider instanceof WorldProviderIo)
                {
                    if (world.provider.getSkyRenderer() == null)
                    {
                        world.provider.setSkyRenderer(new SkyProviderIo());
                    }

                    if (world.provider.getCloudRenderer() == null)
                    {
                        world.provider.setCloudRenderer(new CloudRenderer());
                    }
                }
                if (world.provider instanceof WorldProviderEuropa)
                {
                    if (world.provider.getSkyRenderer() == null)
                    {
                        world.provider.setSkyRenderer(new SkyProviderEuropa());
                    }

                    if (world.provider.getCloudRenderer() == null)
                    {
                        world.provider.setCloudRenderer(new CloudRenderer());
                    }
                }
                if (world.provider instanceof WorldProviderMimas)
                {
                    if (world.provider.getSkyRenderer() == null)
                    {
                        world.provider.setSkyRenderer(new SkyProviderMimas());
                    }

                    if (world.provider.getCloudRenderer() == null)
                    {
                        world.provider.setCloudRenderer(new CloudRenderer());
                    }
                }
                if (world.provider instanceof WorldProviderTitan)
                {
                    if (world.provider.getSkyRenderer() == null)
                    {
                        world.provider.setSkyRenderer(new SkyProviderTitan((IGalacticraftWorldProvider) world.provider));
                    }

                    if (world.provider.getCloudRenderer() == null)
                    {
                        world.provider.setCloudRenderer(new CloudRenderer());
                    }
                }
                if (world.provider instanceof WorldProviderAriel)
                {
                    if (world.provider.getSkyRenderer() == null)
                    {
                        world.provider.setSkyRenderer(new SkyProviderAriel());
                    }

                    if (world.provider.getCloudRenderer() == null)
                    {
                        world.provider.setCloudRenderer(new CloudRenderer());
                    }
                }
                if (world.provider instanceof WorldProviderTriton)
                {
                    if (world.provider.getSkyRenderer() == null)
                    {
                        world.provider.setSkyRenderer(new SkyProviderTriton());
                    }

                    if (world.provider.getCloudRenderer() == null)
                    {
                        world.provider.setCloudRenderer(new CloudRenderer());
                    }
                }
                if (world.provider instanceof WorldProviderPluto)
                {
                    if (world.provider.getSkyRenderer() == null)
                    {
                        world.provider.setSkyRenderer(new SkyProviderPluto());
                    }

                    if (world.provider.getCloudRenderer() == null)
                    {
                        world.provider.setCloudRenderer(new CloudRenderer());
                    }
                }
                if (world.provider instanceof WorldProviderSedna)
                {
                    if (world.provider.getSkyRenderer() == null)
                    {
                        world.provider.setSkyRenderer(new SkyProviderSedna());
                    }

                    if (world.provider.getCloudRenderer() == null)
                    {
                        world.provider.setCloudRenderer(new CloudRenderer());
                    }
                }
            }
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRingRender(CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre renderEvent)
        {
            CelestialBody body = renderEvent.celestialBody;
            if (body.equals(TheSol.planetCeres) || body.equals(TheSol.planetPluto) || body.equals(TheSol.planetSedna) || body.equals(TheSol.planetHaumea) || body.equals(TheSol.planetMakemake) || body.equals(TheSol.planetEris)) {
                renderEvent.setCanceled(true);
            }
        	else if (body.equals(GalacticraftCore.planetOverworld))
            {
                float alpha = 1.0F;
                GuiScreen screen = FMLClientHandler.instance().getClient().currentScreen;
                if (screen instanceof GuiCelestialSelection)
                {
                    alpha = ((GuiCelestialSelection) screen).getAlpha(body);
                    GL11.glColor4f(0.0F, 0.7F, 0.0F, alpha / 2.0F);
                }
                else
                {
                    GL11.glColor4f(0.1F, 0.3F, 0.1F, 1.0F);
                }
                renderEvent.setCanceled(true);
                GL11.glBegin(GL11.GL_LINE_LOOP);

                final float theta = Constants.twoPI / 90;
                final float cos = MathHelper.cos(theta);
                final float sin = MathHelper.sin(theta);

                float min = 68.0F;
                float max = 82.0F;

                float x = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y = 0;

                float temp;
                for (int i = 0; i < 90; i++)
                {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glBegin(GL11.GL_LINE_LOOP);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;

                for (int i = 0; i < 90; i++)
                {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glColor4f(0.0F, 0.7F, 0.0F, alpha / 10.0F);
                GL11.glBegin(GL11.GL_QUADS);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;
                float x2 = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y2 = 0;

                for (int i = 0; i < 90; i++)
                {
                    GL11.glVertex2f(x2, y2);
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                    temp = x2;
                    x2 = cos * x2 - sin * y2;
                    y2 = sin * temp + cos * y2;

                    GL11.glVertex2f(x, y);
                    GL11.glVertex2f(x2, y2);
                }

                GL11.glEnd();
            }
        	else if (body.equals(TheSol.planetKuiperBelt))
            {
                float alpha = 1.0F;
                GuiScreen screen = FMLClientHandler.instance().getClient().currentScreen;
                if (screen instanceof GuiCelestialSelection)
                {
                    alpha = ((GuiCelestialSelection) screen).getAlpha(body);
                    GL11.glColor4f(0.7F, 0.0F, 0.0F, alpha / 2.0F);
                }
                else
                {
                    GL11.glColor4f(0.3F, 0.1F, 0.1F, 1.0F);
                }
                renderEvent.setCanceled(true);
                GL11.glBegin(GL11.GL_LINE_LOOP);

                final float theta = Constants.twoPI / 90;
                final float cos = MathHelper.cos(theta);
                final float sin = MathHelper.sin(theta);

                float min = 66.0F;
                float max = 84.0F;

                float x = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y = 0;

                float temp;
                for (int i = 0; i < 90; i++)
                {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glBegin(GL11.GL_LINE_LOOP);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;

                for (int i = 0; i < 90; i++)
                {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glColor4f(0.7F, 0.0F, 0.0F, alpha / 10.0F);
                GL11.glBegin(GL11.GL_QUADS);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;
                float x2 = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y2 = 0;

                for (int i = 0; i < 90; i++)
                {
                    GL11.glVertex2f(x2, y2);
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                    temp = x2;
                    x2 = cos * x2 - sin * y2;
                    y2 = sin * temp + cos * y2;

                    GL11.glVertex2f(x, y);
                    GL11.glVertex2f(x2, y2);
                }

                GL11.glEnd();
            }
        	else if (body.equals(TheSol.planetOortCloud))
            {
                float alpha = 1.0F;
                GuiScreen screen = FMLClientHandler.instance().getClient().currentScreen;
                if (screen instanceof GuiCelestialSelection)
                {
                    alpha = ((GuiCelestialSelection) screen).getAlpha(body);
                    GL11.glColor4f(0.7F, 0.0F, 0.0F, alpha / 2.0F);
                }
                else
                {
                    GL11.glColor4f(0.3F, 0.1F, 0.1F, 1.0F);
                }
                renderEvent.setCanceled(true);
                GL11.glBegin(GL11.GL_LINE_LOOP);

                final float theta = Constants.twoPI / 90;
                final float cos = MathHelper.cos(theta);
                final float sin = MathHelper.sin(theta);

                float min = 62.0F;
                float max = 90.0F;

                float x = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y = 0;

                float temp;
                for (int i = 0; i < 90; i++)
                {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glBegin(GL11.GL_LINE_LOOP);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;

                for (int i = 0; i < 90; i++)
                {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glColor4f(0.7F, 0.0F, 0.0F, alpha / 10.0F);
                GL11.glBegin(GL11.GL_QUADS);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;
                float x2 = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y2 = 0;

                for (int i = 0; i < 90; i++)
                {
                    GL11.glVertex2f(x2, y2);
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                    temp = x2;
                    x2 = cos * x2 - sin * y2;
                    y2 = sin * temp + cos * y2;

                    GL11.glVertex2f(x, y);
                    GL11.glVertex2f(x2, y2);
                }

                GL11.glEnd();
            }
            else if (body.equals(TheSol.moonRingsOfSaturn))
            {
                renderEvent.setCanceled(true);
            }
        }

        /*@SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void renderBodyName(CelestialBodyRenderEvent.Post event)
        {
            List<CelestialBody> fontsToRender = Lists.newArrayList();
            final Minecraft minecraft = FMLClientHandler.instance().getClient();
            final FontRenderer fontRenderer = minecraft.fontRenderer;

            if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiCelestialSelection && !(fontsToRender.contains((Object) event.celestialBody)))
            {
                float size = ((GuiCelestialSelection) FMLClientHandler.instance().getClient().currentScreen).getWidthForCelestialBody(event.celestialBody) / 6.0F;
                ((GuiCelestialSelection) FMLClientHandler.instance().getClient().currentScreen).drawCenteredString(fontRenderer, event.celestialBody.getLocalizedName(), (int) size, (int) size + 5, 14737632);
                fontsToRender.add(event.celestialBody);
            }
        }*/

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onBodyRender(CelestialBodyRenderEvent.Pre renderEvent)
        {
            CelestialBody body = renderEvent.celestialBody;
            if (body.equals(TheSol.planetKuiperBelt))
            {
                GL11.glRotatef(ClientUtil.getClientTimeTotal() / 10.0F % 360, 0, 0, 1);
            }
            else if (body.equals(TheSol.planetOortCloud))
            {
                GL11.glRotatef(ClientUtil.getClientTimeTotal() / 10.0F % 360, 0, 0, 1);
            }
            else if (body.equals(TheSol.moonRingsOfSaturn))
            {
                GL11.glRotatef(ClientUtil.getClientTimeTotal() / 10.0F % 360, 0, 0, 1);
            }
        }
        
        @SubscribeEvent
        public void onRenderPlanetPost(CelestialBodyRenderEvent.Post event)
        {
            final Minecraft minecraft = FMLClientHandler.instance().getClient();

            if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiCelestialSelection)
            {
                if (event.celestialBody == TheSol.planetSaturn)
                {
                	FMLClientHandler.instance().getClient().renderEngine.bindTexture(ClientProxyCore.saturnRingTexture);
                    float size = ((GuiCelestialSelection)FMLClientHandler.instance().getClient().currentScreen).getWidthForCelestialBody(event.celestialBody) / 6.0F;
                    ((GuiCelestialSelection) FMLClientHandler.instance().getClient().currentScreen).drawTexturedModalRect(-7.5F * size, -1.75F * size, 15.0F * size, 3.5F * size, 0, 0, 30, 7, false, false, 32, 32);
                }
                else if (event.celestialBody == TheSol.planetUranus)
                {
                	FMLClientHandler.instance().getClient().renderEngine.bindTexture(ClientProxyCore.uranusRingTexture);
                    float size = ((GuiCelestialSelection)FMLClientHandler.instance().getClient().currentScreen).getWidthForCelestialBody(event.celestialBody) / 6.0F;
                    ((GuiCelestialSelection) FMLClientHandler.instance().getClient().currentScreen).drawTexturedModalRect(-1.75F * size, -7.0F * size, 3.5F * size, 14.0F * size, 0, 0, 7, 28, false, false, 32, 32);
                }
            }
        }
    }
}
