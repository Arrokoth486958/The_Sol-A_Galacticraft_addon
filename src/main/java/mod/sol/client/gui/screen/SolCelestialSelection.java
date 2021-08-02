package mod.sol.client.gui.screen;

import com.google.common.collect.Maps;
import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.api.galaxies.*;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.recipe.SpaceStationRecipe;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.util.*;
import mod.sol.api.galaxy.DwarfPlanet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.nio.FloatBuffer;
import java.util.*;

public class SolCelestialSelection extends GuiCelestialSelection {
    private static Random rand = new Random();

    public SolCelestialSelection(boolean mapMode, List<CelestialBody> possibleBodies, boolean canCreateStations) {
        super(mapMode, possibleBodies, canCreateStations);
    }

    /*@Override
    public void drawScreen(int mousePosX, int mousePosY, float partialTicks) {
        this.ticksSinceMenuOpenF += partialTicks;
        this.ticksTotalF += partialTicks;
        if (this.selectedBody != null) {
            this.ticksSinceSelectionF += partialTicks;
        }

        if (this.selectedBody == null && this.ticksSinceUnselectionF >= 0.0F) {
            this.ticksSinceUnselectionF += partialTicks;
        }

        if (Mouse.hasWheel()) {
            float wheel = (float) Mouse.getDWheel() / (this.selectedBody == null ? 500.0F : 250.0F);
            if (wheel != 0.0F) {
                if (this.selectedBody != null && (this.viewState != GuiCelestialSelection.EnumView.PREVIEW || this.isZoomed())) {
                    this.planetZoom = Math.min(Math.max(this.planetZoom + wheel, -4.9F), 5.0F);
                } else {
                    this.zoom = Math.min(Math.max(this.zoom + wheel * (this.zoom + 2.0F) / 10.0F, -1.0F), 3.0F);
                }
            }
        }

        GL11.glPushMatrix();
        GL11.glEnable(3042);
        Matrix4f camMatrix = new Matrix4f();
        Matrix4f.translate(new Vector3f(0.0F, 0.0F, -9000.0F), camMatrix, camMatrix);
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.m00 = 2.0F / (float) this.width;
        viewMatrix.m11 = 2.0F / (float) (-this.height);
        viewMatrix.m22 = -2.2222222E-4F;
        viewMatrix.m30 = -1.0F;
        viewMatrix.m31 = 1.0F;
        viewMatrix.m32 = -2.0F;
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        FloatBuffer fb = BufferUtils.createFloatBuffer(512);
        fb.rewind();
        viewMatrix.store(fb);
        fb.flip();
        GL11.glMultMatrix(fb);
        fb.clear();
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        fb.rewind();
        camMatrix.store(fb);
        fb.flip();
        fb.clear();
        GL11.glMultMatrix(fb);
        this.setBlackBackground();
        GL11.glPushMatrix();
        Matrix4f worldMatrix = this.setIsometric(partialTicks);
        float gridSize = 7000.0F;
        // this.drawGrid(gridSize, (float) (this.height / 3) / 3.5F);
        this.drawCircles();
        GL11.glPopMatrix();
        HashMap<CelestialBody, Matrix4f> matrixMap = this.drawCelestialBodies(worldMatrix);
        this.planetPosMap.clear();
        Iterator var10 = matrixMap.entrySet().iterator();

        while (var10.hasNext()) {
            Map.Entry<CelestialBody, Matrix4f> e = (Map.Entry) var10.next();
            Matrix4f planetMatrix = (Matrix4f) e.getValue();
            Matrix4f matrix0 = Matrix4f.mul(viewMatrix, planetMatrix, planetMatrix);
            int x = (int) Math.floor(((double) matrix0.m30 * 0.5D + 0.5D) * (double) Minecraft.getMinecraft().displayWidth);
            int y = (int) Math.floor((double) Minecraft.getMinecraft().displayHeight - ((double) matrix0.m31 * 0.5D + 0.5D) * (double) Minecraft.getMinecraft().displayHeight);
            Vector2f vec = new Vector2f((float) x, (float) y);
            Matrix4f scaleVec = new Matrix4f();
            scaleVec.m00 = matrix0.m00;
            scaleVec.m11 = matrix0.m11;
            scaleVec.m22 = matrix0.m22;
            Vector4f newVec = Matrix4f.transform(scaleVec, new Vector4f(2.0F, -2.0F, 0.0F, 0.0F), (Vector4f) null);
            float iconSize = newVec.y * ((float) Minecraft.getMinecraft().displayHeight / 2.0F) * (float) (e.getKey() instanceof Star ? 2 : 1) * (e.getKey() == this.selectedBody ? 1.5F : 1.0F);
            this.planetPosMap.put(e.getKey(), new Vector3f(vec.x, vec.y, iconSize));
        }

        this.drawSelectionCursor(fb, worldMatrix);

        try {
            this.drawButtons(mousePosX, mousePosY);
        } catch (Exception var20) {
            if (!this.errorLogged) {
                this.errorLogged = true;
                GCLog.severe("Problem identifying planet or dimension in an add on for Galacticraft!");
                GCLog.severe("(The problem is likely caused by a dimension ID conflict.  Check configs for dimension clashes.  You can also try disabling Mars space station in configs.)");
                var20.printStackTrace();
            }
        }
    }*/

    @Override
    public HashMap<CelestialBody, Matrix4f> drawCelestialBodies(Matrix4f worldMatrix) {
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        FloatBuffer fb = BufferUtils.createFloatBuffer(512);
        HashMap<CelestialBody, Matrix4f> matrixMap = Maps.newHashMap();
        Iterator var4 = this.bodiesToRender.iterator();

        while(var4.hasNext()) {
            CelestialBody body = (CelestialBody)var4.next();
            boolean hasParent = body instanceof IChildBody;
            float alpha = this.getAlpha(body);
            if (alpha > 0.0F) {
                GlStateManager.pushMatrix();
                Matrix4f worldMatrixLocal = this.setupMatrix(body, worldMatrix, fb, hasParent ? 0.25F : 1.0F);

//                if (!this.isZoomed() && !(body instanceof Moon) && !(body instanceof Satellite) && !(body instanceof Star)) {
//                    if(body.getBodyIcon() != null) {
//                        this.drawString(this.fontRenderer, body.getLocalizedName(), 5, 0, 14737632);
//                    }
//                } else if (this.isZoomed() && (body instanceof Moon) && !(body instanceof Satellite) && !(body instanceof Star)) {
//                    if(body.getBodyIcon() != null) {
//                        this.drawString(this.fontRenderer, body.getLocalizedName(), 5, 0, 14737632);
//                    }
//                }

                /*if (mc.world.provider instanceof WorldProviderSpace) {
                    if (((WorldProviderSpace) mc.world.provider).getCelestialBody() == body) {
                        this.mc.renderEngine.bindTexture(guiMain0);
                    }
                }*/

                if (!this.isZoomed() && !(body instanceof Moon) && !(body instanceof Satellite) && !(body instanceof Star) && !(body instanceof DwarfPlanet)) {
                    if (body.getBodyIcon() != null) {
                        this.drawCenteredString(this.fontRenderer, body.getLocalizedName(), 0 , 5, 14737632);
                    }
                }
                else if (this.isZoomed() && (body instanceof Moon) && !(body instanceof Satellite) && !(body instanceof Star) && !(body instanceof DwarfPlanet)) {
                    if (body.getBodyIcon() != null) {
                        this.drawCenteredString(this.fontRenderer, body.getLocalizedName(), 0 , 5, 14737632);
                    }
                }

                CelestialBodyRenderEvent.Pre preEvent = new CelestialBodyRenderEvent.Pre(body, body.getBodyIcon(), 16);
                MinecraftForge.EVENT_BUS.post(preEvent);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
                if (preEvent.celestialBodyTexture != null) {
                    this.mc.renderEngine.bindTexture(preEvent.celestialBodyTexture);
                    //this.drawCenteredString(fontRenderer, body.getLocalizedName(), (int) body.getRelativeSize(), (int) body.getRelativeSize() + 5, 14737632);
                }

                if (!preEvent.isCanceled()) {
                    int size = this.getWidthForCelestialBody(body);
                    this.drawTexturedModalRect((float)(-size / 2), (float)(-size / 2), (float)size, (float)size, 0.0F, 0.0F, (float)preEvent.textureSize, (float)preEvent.textureSize, false, false, (float)preEvent.textureSize, (float)preEvent.textureSize);
                    matrixMap.put(body, worldMatrixLocal);
                }

                CelestialBodyRenderEvent.Post postEvent = new CelestialBodyRenderEvent.Post(body);
                MinecraftForge.EVENT_BUS.post(postEvent);
                GlStateManager.popMatrix();
            }
        }

        return matrixMap;
    }

    @Override
    public void drawGrid(float gridSize, float gridScale) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBegin(1);
        gridSize += gridScale / 2.0F;

        /*for(float v = -gridSize; v <= gridSize; v += gridScale) {
            GL11.glVertex3f(v, -gridSize, -0.0F);
            GL11.glVertex3f(v, gridSize, -0.0F);
            GL11.glVertex3f(-gridSize, v, -0.0F);
            GL11.glVertex3f(gridSize, v, -0.0F);
        }*/

        GL11.glEnd();
    }

    public void drawStars(float gridSize, float gridScale) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBegin(1);
        gridSize += gridScale / 2.0F;

        /*for(float v = -gridSize; v <= gridSize; v += gridScale) {
            GL11.glVertex3f(v, -gridSize, -0.0F);
            GL11.glVertex3f(v, gridSize, -0.0F);
            GL11.glVertex3f(-gridSize, v, -0.0F);
            GL11.glVertex3f(gridSize, v, -0.0F);
        }*/

        GL11.glEnd();
    }

    @Override
    protected void drawSelectionCursor(FloatBuffer fb, Matrix4f worldMatrix) {
        GL11.glPushMatrix();
        float div;
        switch(this.selectionState) {
            case SELECTED:
                if (this.selectedBody != null) {
                    this.setupMatrix(this.selectedBody, worldMatrix, fb);
                    fb.clear();
                    GL11.glScalef(0.06666667F, 0.06666667F, 1.0F);
                    this.mc.renderEngine.bindTexture(guiMain0);
                    div = this.getZoomAdvanced() < 4.9F ? (float)(Math.sin((double)(this.ticksSinceSelectionF / 2.0F)) * 0.5D + 0.5D) : 1.0F;
                    GL11.glColor4f(1.0F, 1.0F, 0.0F, 1.0F * div);
                    int width = (int)Math.floor((double)this.getWidthForCelestialBody(this.selectedBody) / 2.0D * (this.selectedBody instanceof IChildBody ? 9.0D : 30.0D));
                    this.drawTexturedModalRect(-width, -width, width * 2, width * 2, 266, 29, 100, 100, false, false);
                }
                break;
            case ZOOMED:
                if (this.selectedBody != null) {
                    this.setupMatrix(this.selectedBody, worldMatrix, fb);
                    fb.clear();
                    div = this.zoom + 1.0F - this.planetZoom;
                    float scale = Math.max(0.3F, 1.5F / (this.ticksSinceSelectionF / 5.0F)) * 2.0F / div;
                    GL11.glScalef(scale, scale, 1.0F);
                    this.mc.renderEngine.bindTexture(guiMain0);
                    float colMod = this.getZoomAdvanced() < 4.9F ? (float)(Math.sin((double)(this.ticksSinceSelectionF / 1.0F)) * 0.5D + 0.5D) : 1.0F;
                    GL11.glColor4f(0.4F, 0.8F, 1.0F, 1.0F * colMod);
                    int width = this.getWidthForCelestialBody(this.selectedBody) * 13;
                    this.drawTexturedModalRect(-width, -width, width * 2, width * 2, 266, 29, 100, 100, false, false);
                }
        }

        GL11.glPopMatrix();
    }

    @Override
    public void drawButtons(int mousePosX, int mousePosY) {
        this.zLevel = 0.0F;
        boolean handledSliderPos = false;
        int LHS = BORDER_SIZE + BORDER_EDGE_SIZE;
        int RHS = this.width - LHS;
        int TOP = LHS;
        int BOT = this.height - LHS;
        String str;
        int menuTopLeft;
        int sliderPos;
        if (this.viewState == GuiCelestialSelection.EnumView.PROFILE) {
            this.mc.renderEngine.bindTexture(guiMain0);
            GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.width / 2 - 43, LHS, 86, 15, 266, 0, 172, 29, false, false);
            str = GCCoreUtil.translate("gui.message.catalog.name").toUpperCase();
            this.fontRenderer.drawString(str, this.width / 2 - this.fontRenderer.getStringWidth(str) / 2, LHS + this.fontRenderer.FONT_HEIGHT / 2, WHITE);
            if (this.selectedBody != null) {
                this.mc.renderEngine.bindTexture(guiMain0);
                if (mousePosX > LHS && mousePosX < LHS + 88 && mousePosY > LHS && mousePosY < LHS + 13) {
                    GL11.glColor3f(3.0F, 0.0F, 0.0F);
                } else {
                    GL11.glColor3f(0.9F, 0.2F, 0.2F);
                }

                this.drawTexturedModalRect(LHS, LHS, 88, 13, 0, 392, 148, 22, false, false);
                str = GCCoreUtil.translate("gui.message.back.name").toUpperCase();
                this.fontRenderer.drawString(str, LHS + 45 - this.fontRenderer.getStringWidth(str) / 2, LHS + this.fontRenderer.FONT_HEIGHT / 2 - 2, WHITE);
                this.mc.renderEngine.bindTexture(guiMain0);
                if (mousePosX > RHS - 88 && mousePosX < RHS && mousePosY > LHS && mousePosY < LHS + 13) {
                    GL11.glColor3f(0.0F, 3.0F, 0.0F);
                } else {
                    GL11.glColor3f(0.2F, 0.9F, 0.2F);
                }

                this.drawTexturedModalRect(RHS - 88, LHS, 88, 13, 0, 392, 148, 22, true, false);
                GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                this.drawTexturedModalRect(LHS, BOT - 13, 88, 13, 0, 392, 148, 22, false, true);
                this.drawTexturedModalRect(RHS - 88, BOT - 13, 88, 13, 0, 392, 148, 22, true, true);
                menuTopLeft = LHS - 115 + this.height / 2 - 4;
                int posX = LHS + Math.min((int)this.ticksSinceSelectionF * 10, 133) - 134;
                int posX2 = (int)((float)LHS + Math.min(this.ticksSinceSelectionF * 1.25F, 15.0F) - 15.0F);
                sliderPos = menuTopLeft + BORDER_EDGE_SIZE + this.fontRenderer.FONT_HEIGHT / 2 - 2;
                this.drawTexturedModalRect(posX, menuTopLeft + 12, 133, 196, 0, 0, 266, 392, false, false);
                str = GCCoreUtil.translate("gui.message.daynightcycle.name") + ":";
                this.fontRenderer.drawString(str, posX + 5, sliderPos + 14, CYAN);
                str = GCCoreUtil.translate("gui.message." + this.selectedBody.getName() + ".daynightcycle.0.name");
                this.fontRenderer.drawString(str, posX + 10, sliderPos + 25, WHITE);
                str = GCCoreUtil.translate("gui.message." + this.selectedBody.getName() + ".daynightcycle.1.name");
                if (!str.isEmpty()) {
                    this.fontRenderer.drawString(str, posX + 10, sliderPos + 36, WHITE);
                }

                str = GCCoreUtil.translate("gui.message.surfacegravity.name") + ":";
                this.fontRenderer.drawString(str, posX + 5, sliderPos + 50, CYAN);
                str = GCCoreUtil.translate("gui.message." + this.selectedBody.getName() + ".surfacegravity.0.name");
                this.fontRenderer.drawString(str, posX + 10, sliderPos + 61, WHITE);
                str = GCCoreUtil.translate("gui.message." + this.selectedBody.getName() + ".surfacegravity.1.name");
                if (!str.isEmpty()) {
                    this.fontRenderer.drawString(str, posX + 10, sliderPos + 72, WHITE);
                }

                str = GCCoreUtil.translate("gui.message.surfacecomposition.name") + ":";
                this.fontRenderer.drawString(str, posX + 5, sliderPos + 88, CYAN);
                str = GCCoreUtil.translate("gui.message." + this.selectedBody.getName() + ".surfacecomposition.0.name");
                this.fontRenderer.drawString(str, posX + 10, sliderPos + 99, WHITE);
                str = GCCoreUtil.translate("gui.message." + this.selectedBody.getName() + ".surfacecomposition.1.name");
                if (!str.isEmpty()) {
                    this.fontRenderer.drawString(str, posX + 10, sliderPos + 110, WHITE);
                }

                str = GCCoreUtil.translate("gui.message.atmosphere.name") + ":";
                this.fontRenderer.drawString(str, posX + 5, sliderPos + 126, CYAN);
                str = GCCoreUtil.translate("gui.message." + this.selectedBody.getName() + ".atmosphere.0.name");
                this.fontRenderer.drawString(str, posX + 10, sliderPos + 137, WHITE);
                str = GCCoreUtil.translate("gui.message." + this.selectedBody.getName() + ".atmosphere.1.name");
                if (!str.isEmpty()) {
                    this.fontRenderer.drawString(str, posX + 10, sliderPos + 148, WHITE);
                }

                str = GCCoreUtil.translate("gui.message.meansurfacetemp.name") + ":";
                this.fontRenderer.drawString(str, posX + 5, sliderPos + 165, CYAN);
                str = GCCoreUtil.translate("gui.message." + this.selectedBody.getName() + ".meansurfacetemp.0.name");
                this.fontRenderer.drawString(str, posX + 10, sliderPos + 176, WHITE);
                str = GCCoreUtil.translate("gui.message." + this.selectedBody.getName() + ".meansurfacetemp.1.name");
                if (!str.isEmpty()) {
                    this.fontRenderer.drawString(str, posX + 10, sliderPos + 187, WHITE);
                }

                this.mc.renderEngine.bindTexture(guiMain0);
                GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                this.drawTexturedModalRect(posX2, menuTopLeft + 12, 17, 199, 439, 0, 32, 399, false, false);
            }
        } else {
            this.mc.renderEngine.bindTexture(guiMain0);
            GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
            this.drawTexturedModalRect(LHS, LHS, 74, 11, 0, 392, 148, 22, false, false);
            str = GCCoreUtil.translate("gui.message.catalog.name").toUpperCase();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.fontRenderer.drawString(str, LHS + 40 - this.fontRenderer.getStringWidth(str) / 2, LHS + 1, WHITE);
            menuTopLeft = (int)Math.min(95.0F, this.ticksSinceMenuOpenF * 12.0F);
            boolean planetZoomedNotMoon = this.isZoomed() && !(this.selectedParent instanceof Planet);
            GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
            this.mc.renderEngine.bindTexture(guiMain0);
            this.drawTexturedModalRect(LHS - 95 + menuTopLeft, LHS + 12, 95, 41, 0, 436, 95, 41, false, false);
            str = planetZoomedNotMoon ? this.selectedBody.getLocalizedName() : this.getParentName();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.fontRenderer.drawString(str, LHS + 9 - 95 + menuTopLeft, LHS + 34, WHITE);
            GL11.glColor4f(1.0F, 1.0F, 0.0F, 1.0F);
            this.mc.renderEngine.bindTexture(guiMain0);
            this.drawTexturedModalRect(LHS + 2 - 95 + menuTopLeft, LHS + 14, 93, 17, 95, 436, 93, 17, false, false);
            str = planetZoomedNotMoon ? this.getParentName() : this.getGrandparentName();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.fontRenderer.drawString(str, LHS + 7 - 95 + menuTopLeft, LHS + 16, GREY3);
            GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
            List<CelestialBody> children = this.getChildren(planetZoomedNotMoon ? this.selectedBody : this.selectedParent);
            this.drawChildren(children, 0, 0, true);
            if (this.mapMode) {
                this.mc.renderEngine.bindTexture(guiMain0);
                GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
                this.mc.renderEngine.bindTexture(guiMain0);
                this.drawTexturedModalRect(RHS - 74, LHS, 74, 11, 0, 392, 148, 22, true, false);
                str = GCCoreUtil.translate("gui.message.exit.name").toUpperCase();
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.fontRenderer.drawString(str, RHS - 40 - this.fontRenderer.getStringWidth(str) / 2, LHS + 1, WHITE);
            }

            if (this.selectedBody != null) {
                this.mc.renderEngine.bindTexture(guiMain1);
                GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                int barY;
                int i;
                int xPos;
                int point;
                Satellite selectedSatellite;
                if (!(this.selectedBody instanceof Satellite)) {
                    this.drawTexturedModalRect(RHS - 96, LHS, 96, 139, 63, 0, 96, 139, false, false);
                } else {
                    selectedSatellite = (Satellite)this.selectedBody;
                    barY = ((Map)this.spaceStationMap.get(this.getSatelliteParentID(selectedSatellite))).size();
                    this.mc.renderEngine.bindTexture(guiMain1);
                    int max = Math.min(this.height / 2 / 14, barY);
                    this.drawTexturedModalRect(RHS - 95, LHS, 95, 53, this.selectedStationOwner.length() == 0 ? 95 : 0, 186, 95, 53, false, false);
                    if (this.spaceStationListOffset <= 0) {
                        GL11.glColor4f(0.65F, 0.65F, 0.65F, 1.0F);
                    } else {
                        GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                    }

                    this.drawTexturedModalRect(RHS - 85, LHS + 45, 61, 4, 0, 239, 61, 4, false, false);
                    if (max + this.spaceStationListOffset >= barY) {
                        GL11.glColor4f(0.65F, 0.65F, 0.65F, 1.0F);
                    } else {
                        GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                    }

                    this.drawTexturedModalRect(RHS - 85, LHS + 49 + max * 14, 61, 4, 0, 239, 61, 4, false, true);
                    GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                    if (((Map)this.spaceStationMap.get(this.getSatelliteParentID(selectedSatellite))).get(this.selectedStationOwner) == null) {
                        str = GCCoreUtil.translate("gui.message.select_ss.name");
                        this.drawSplitString(str, RHS - 47, LHS + 20, 91, WHITE, false, false);
                    } else {
                        str = GCCoreUtil.translate("gui.message.ss_owner.name");
                        this.fontRenderer.drawString(str, RHS - 85, LHS + 18, WHITE);
                        str = this.selectedStationOwner;
                        this.fontRenderer.drawString(str, RHS - 47 - this.fontRenderer.getStringWidth(str) / 2, LHS + 30, WHITE);
                    }

                    Iterator<Map.Entry<String, StationDataGUI>> it = ((Map)this.spaceStationMap.get(this.getSatelliteParentID(selectedSatellite))).entrySet().iterator();
                    i = 0;

                    for(int j = 0; it.hasNext() && i < max; ++j) {
                        Map.Entry<String, StationDataGUI> e = (Map.Entry)it.next();
                        if (j >= this.spaceStationListOffset) {
                            this.mc.renderEngine.bindTexture(guiMain0);
                            GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                            xPos = 0;
                            if (((String)e.getKey()).equalsIgnoreCase(this.selectedStationOwner)) {
                                xPos -= 5;
                            }

                            this.drawTexturedModalRect(RHS - 95 + xPos, TOP + 50 + i * 14, 93, 12, 95, 464, 93, 12, true, false);
                            str = "";
                            String str0 = ((GuiCelestialSelection.StationDataGUI)e.getValue()).getStationName();

                            for(point = 0; this.fontRenderer.getStringWidth(str) < 80 && point < str0.length(); ++point) {
                                str = str + str0.substring(point, point + 1);
                            }

                            if (this.fontRenderer.getStringWidth(str) >= 80) {
                                str = str.substring(0, str.length() - 3);
                                str = str + "...";
                            }

                            this.fontRenderer.drawString(str, RHS - 88 + xPos, TOP + 52 + i * 14, WHITE);
                            ++i;
                        }
                    }
                }

                boolean validInputMaterials;
                if (this.canCreateSpaceStation(this.selectedBody) && !(this.selectedBody instanceof Satellite)) {
                    GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                    this.mc.renderEngine.bindTexture(guiMain1);
                    sliderPos = Math.max(0, this.drawSplitString(GCCoreUtil.translate("gui.message.can_create_space_station.name"), 0, 0, 91, 0, true, true) - 2);
                    this.canCreateOffset = sliderPos * this.fontRenderer.FONT_HEIGHT;
                    this.drawTexturedModalRect(RHS - 95, TOP + 134, 93, 4, 159, 102, 93, 4, false, false);

                    for(barY = 0; barY < sliderPos; ++barY) {
                        this.drawTexturedModalRect(RHS - 95, TOP + 138 + barY * this.fontRenderer.FONT_HEIGHT, 93, this.fontRenderer.FONT_HEIGHT, 159, 106, 93, this.fontRenderer.FONT_HEIGHT, false, false);
                    }

                    this.drawTexturedModalRect(RHS - 95, TOP + 138 + this.canCreateOffset, 93, 43, 159, 106, 93, 43, false, false);
                    this.drawTexturedModalRect(RHS - 79, TOP + 129, 61, 4, 0, 170, 61, 4, false, false);
                    SpaceStationRecipe recipe = WorldUtil.getSpaceStationRecipe(this.selectedBody.getDimensionID());
                    if (recipe == null) {
                        this.drawSplitString(GCCoreUtil.translate("gui.message.cannot_create_space_station.name"), RHS - 48, TOP + 138, 91, WHITE, true, false);
                    } else {
                        GL11.glColor4f(0.0F, 1.0F, 0.1F, 1.0F);
                        validInputMaterials = true;
                        i = 0;
                        Iterator var44 = recipe.getInput().entrySet().iterator();

                        label412:
                        while(true) {
                            while(true) {
                                if (!var44.hasNext()) {
                                    if (!validInputMaterials && !this.mc.player.capabilities.isCreativeMode) {
                                        GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
                                    } else {
                                        GL11.glColor4f(0.0F, 1.0F, 0.1F, 1.0F);
                                    }

                                    this.mc.renderEngine.bindTexture(guiMain1);
                                    if (!this.mapMode && mousePosX >= RHS - 95 && mousePosX <= RHS && mousePosY >= TOP + 182 + this.canCreateOffset && mousePosY <= TOP + 182 + 12 + this.canCreateOffset) {
                                        this.drawTexturedModalRect(RHS - 95, TOP + 182 + this.canCreateOffset, 93, 12, 0, 174, 93, 12, false, false);
                                    }

                                    this.drawTexturedModalRect(RHS - 95, TOP + 182 + this.canCreateOffset, 93, 12, 0, 174, 93, 12, false, false);
                                    i = (int)((Math.sin((double)this.ticksSinceMenuOpenF / 5.0D) * 0.5D + 0.5D) * 255.0D);
                                    this.drawSplitString(GCCoreUtil.translate("gui.message.can_create_space_station.name"), RHS - 48, TOP + 137, 91, ColorUtil.to32BitColor(255, i, 255, i), true, false);
                                    if (!this.mapMode) {
                                        this.drawSplitString(GCCoreUtil.translate("gui.message.create_ss.name").toUpperCase(), RHS - 48, TOP + 185 + this.canCreateOffset, 91, WHITE, false, false);
                                    }
                                    break label412;
                                }

                                Map.Entry<Object, Integer> e = (Map.Entry)var44.next();
                                Object next = e.getKey();
                                xPos = (int)((double)(RHS - 95) + (double)(i * 93) / (double)recipe.getInput().size() + 5.0D);
                                int yPos = TOP + 154 + this.canCreateOffset;
                                int toRenderIndex;
                                int j2;
                                int k2;
                                int count;
                                int k;
                                if (next instanceof ItemStack) {
                                    point = this.getAmountInInventory((ItemStack)next);
                                    RenderHelper.enableGUIStandardItemLighting();
                                    ItemStack toRender = ((ItemStack)next).copy();
                                    this.itemRender.renderItemAndEffectIntoGUI(toRender, xPos, yPos);
                                    this.itemRender.renderItemOverlayIntoGUI(this.mc.fontRenderer, toRender, xPos, yPos, (String)null);
                                    RenderHelper.disableStandardItemLighting();
                                    GL11.glEnable(3042);
                                    if (mousePosX >= xPos && mousePosX <= xPos + 16 && mousePosY >= yPos && mousePosY <= yPos + 16) {
                                        GL11.glDepthMask(true);
                                        GL11.glEnable(2929);
                                        GL11.glPushMatrix();
                                        GL11.glTranslatef(0.0F, 0.0F, 300.0F);
                                        k = this.fontRenderer.getStringWidth(((ItemStack)next).getDisplayName());
                                        count = mousePosX - k / 2;
                                        toRenderIndex = mousePosY - 12;
                                        int i1 = 8;
                                        if (count + k > this.width) {
                                            count -= count - this.width + k;
                                        }

                                        if (toRenderIndex + i1 + 6 > this.height) {
                                            toRenderIndex = this.height - i1 - 6;
                                        }

                                        k = ColorUtil.to32BitColor(190, 0, 153, 255);
                                        this.drawGradientRect(count - 3, toRenderIndex - 4, count + k + 3, toRenderIndex - 3, k, k);
                                        this.drawGradientRect(count - 3, toRenderIndex + i1 + 3, count + k + 3, toRenderIndex + i1 + 4, k, k);
                                        this.drawGradientRect(count - 3, toRenderIndex - 3, count + k + 3, toRenderIndex + i1 + 3, k, k);
                                        this.drawGradientRect(count - 4, toRenderIndex - 3, count - 3, toRenderIndex + i1 + 3, k, k);
                                        this.drawGradientRect(count + k + 3, toRenderIndex - 3, count + k + 4, toRenderIndex + i1 + 3, k, k);
                                        j2 = ColorUtil.to32BitColor(170, 0, 153, 255);
                                        k2 = (j2 & 16711422) >> 1 | j2 & -16777216;
                                        this.drawGradientRect(count - 3, toRenderIndex - 3 + 1, count - 3 + 1, toRenderIndex + i1 + 3 - 1, j2, k2);
                                        this.drawGradientRect(count + k + 2, toRenderIndex - 3 + 1, count + k + 3, toRenderIndex + i1 + 3 - 1, j2, k2);
                                        this.drawGradientRect(count - 3, toRenderIndex - 3, count + k + 3, toRenderIndex - 3 + 1, j2, j2);
                                        this.drawGradientRect(count - 3, toRenderIndex + i1 + 2, count + k + 3, toRenderIndex + i1 + 3, k2, k2);
                                        this.fontRenderer.drawString(((ItemStack)next).getDisplayName(), count, toRenderIndex, WHITE);
                                        GL11.glPopMatrix();
                                    }

                                    str = "" + e.getValue();
                                    boolean valid = point >= (Integer)e.getValue();
                                    if (!valid && validInputMaterials) {
                                        validInputMaterials = false;
                                    }

                                    count = valid | this.mc.player.capabilities.isCreativeMode ? GREEN : RED;
                                    this.fontRenderer.drawString(str, xPos + 8 - this.fontRenderer.getStringWidth(str) / 2, TOP + 170 + this.canCreateOffset, count);
                                    break;
                                }

                                if (!(next instanceof Collection)) {
                                    break;
                                }

                                Collection<ItemStack> items = (Collection)next;
                                int amount = 0;

                                Iterator it;
                                ItemStack stack;
                                for(it = items.iterator(); it.hasNext(); amount += this.getAmountInInventory(stack)) {
                                    stack = (ItemStack)it.next();
                                }

                                RenderHelper.enableGUIStandardItemLighting();
                                it = items.iterator();
                                count = 0;
                                toRenderIndex = (int)this.ticksSinceMenuOpenF / 20 % items.size();

                                ItemStack toRender;
                                for(toRender = null; it.hasNext(); ++count) {
                                    stack = (ItemStack)it.next();
                                    if (count == toRenderIndex) {
                                        toRender = stack;
                                        break;
                                    }
                                }

                                if (toRender != null) {
                                    this.itemRender.renderItemAndEffectIntoGUI(toRender, xPos, yPos);
                                    this.itemRender.renderItemOverlayIntoGUI(this.mc.fontRenderer, toRender, xPos, yPos, (String)null);
                                    RenderHelper.disableStandardItemLighting();
                                    GL11.glEnable(3042);
                                    if (mousePosX >= xPos && mousePosX <= xPos + 16 && mousePosY >= yPos && mousePosY <= yPos + 16) {
                                        GL11.glDepthMask(true);
                                        GL11.glEnable(2929);
                                        GL11.glPushMatrix();
                                        GL11.glTranslatef(0.0F, 0.0F, 300.0F);
                                        k = this.fontRenderer.getStringWidth(toRender.getDisplayName());
                                        j2 = mousePosX - k / 2;
                                        k2 = mousePosY - 12;
                                        int i1 = 8;
                                        if (j2 + k > this.width) {
                                            j2 -= j2 - this.width + k;
                                        }

                                        if (k2 + i1 + 6 > this.height) {
                                            k2 = this.height - i1 - 6;
                                        }

                                        int j1 = ColorUtil.to32BitColor(190, 0, 153, 255);
                                        this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
                                        this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
                                        this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
                                        this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
                                        this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
                                        int k1 = ColorUtil.to32BitColor(170, 0, 153, 255);
                                        int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
                                        this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
                                        this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
                                        this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
                                        this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);
                                        this.fontRenderer.drawString(toRender.getDisplayName(), j2, k2, WHITE);
                                        GL11.glPopMatrix();
                                    }

                                    str = "" + e.getValue();
                                    boolean valid = amount >= (Integer)e.getValue();
                                    if (!valid && validInputMaterials) {
                                        validInputMaterials = false;
                                    }

                                    j2 = valid | this.mc.player.capabilities.isCreativeMode ? GREEN : RED;
                                    this.fontRenderer.drawString(str, xPos + 8 - this.fontRenderer.getStringWidth(str) / 2, TOP + 170 + this.canCreateOffset, j2);
                                    break;
                                }
                            }

                            ++i;
                        }
                    }
                }

                this.mc.renderEngine.bindTexture(guiMain0);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.3F - Math.min(0.3F, this.ticksSinceSelectionF / 50.0F));
                this.drawTexturedModalRect(LHS, TOP, 74, 11, 0, 392, 148, 22, false, false);
                str = GCCoreUtil.translate("gui.message.catalog.name").toUpperCase();
                this.fontRenderer.drawString(str, LHS + 40 - this.fontRenderer.getStringWidth(str) / 2, TOP + 1, WHITE);
                this.mc.renderEngine.bindTexture(guiMain0);
                GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                if (!(this.selectedBody instanceof Satellite)) {
                    this.drawTexturedModalRect(this.width / 2 - 47, TOP, 94, 11, 0, 414, 188, 22, false, false);
                } else {
                    if (this.selectedStationOwner.length() != 0 && this.selectedStationOwner.equalsIgnoreCase(PlayerUtil.getName(this.mc.player))) {
                        GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
                    } else {
                        GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
                    }

                    this.drawTexturedModalRect(this.width / 2 - 47, TOP, 94, 11, 0, 414, 188, 22, false, false);
                }

                if (this.selectedBody.getTierRequirement() >= 0 && !(this.selectedBody instanceof Satellite)) {
                    boolean canReach;
                    if (this.selectedBody.getReachable() && (this.possibleBodies == null || this.possibleBodies.contains(this.selectedBody))) {
                        canReach = true;
                        GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
                    } else {
                        canReach = false;
                        GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
                    }

                    this.drawTexturedModalRect(this.width / 2 - 30, TOP + 11, 30, 11, 0, 414, 60, 22, false, false);
                    this.drawTexturedModalRect(this.width / 2, TOP + 11, 30, 11, 128, 414, 60, 22, false, false);
                    str = GCCoreUtil.translateWithFormat("gui.message.tier.name", new Object[]{this.selectedBody.getTierRequirement() == 0 ? "?" : this.selectedBody.getTierRequirement()});
                    this.fontRenderer.drawString(str, this.width / 2 - this.fontRenderer.getStringWidth(str) / 2, TOP + 13, canReach ? GREY4 : RED3);
                }

                str = this.selectedBody.getLocalizedName();
                if (this.selectedBody instanceof Satellite) {
                    str = GCCoreUtil.translate("gui.message.rename.name").toUpperCase();
                }

                this.fontRenderer.drawString(str, this.width / 2 - this.fontRenderer.getStringWidth(str) / 2, TOP + 2, WHITE);
                this.mc.renderEngine.bindTexture(guiMain0);
                GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                this.drawTexturedModalRect(LHS + 4, TOP, 83, 12, 0, 477, 83, 12, false, false);
                if (!this.mapMode) {
                    if (this.selectedBody.getReachable() && (this.possibleBodies == null || this.possibleBodies.contains(this.selectedBody)) && (!(this.selectedBody instanceof Satellite) || !this.selectedStationOwner.equals(""))) {
                        GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
                    } else {
                        GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
                    }

                    this.mc.renderEngine.bindTexture(guiMain0);
                    this.drawTexturedModalRect(RHS - 74, TOP, 74, 11, 0, 392, 148, 22, true, false);
                    str = GCCoreUtil.translate("gui.message.launch.name").toUpperCase();
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    this.fontRenderer.drawString(str, RHS - 40 - this.fontRenderer.getStringWidth(str) / 2, TOP + 2, WHITE);
                }

                if (this.selectionState == GuiCelestialSelection.EnumSelection.SELECTED && !(this.selectedBody instanceof Satellite)) {
                    handledSliderPos = true;
                    sliderPos = this.zoomTooltipPos;
                    if (this.zoomTooltipPos != 38) {
                        sliderPos = Math.min((int)this.ticksSinceSelectionF * 2, 38);
                        this.zoomTooltipPos = sliderPos;
                    }

                    GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                    this.mc.renderEngine.bindTexture(guiMain0);
                    this.drawTexturedModalRect(RHS - 182, this.height - BORDER_SIZE - BORDER_EDGE_SIZE - sliderPos, 83, 38, 346, 436, 166, 76, true, false);
                    boolean flag0 = GalaxyRegistry.getSatellitesForCelestialBody(this.selectedBody).size() > 0;
                    validInputMaterials = this.selectedBody instanceof Planet && GalaxyRegistry.getMoonsForPlanet((Planet)this.selectedBody).size() > 0;
                    if (flag0 && validInputMaterials) {
                        this.drawSplitString(GCCoreUtil.translate("gui.message.click_again.0.name"), RHS - 182 + 41, this.height - BORDER_SIZE - BORDER_EDGE_SIZE + 2 - sliderPos, 79, GREY5, false, false);
                    } else if (!flag0 && validInputMaterials) {
                        this.drawSplitString(GCCoreUtil.translate("gui.message.click_again.1.name"), RHS - 182 + 41, this.height - BORDER_SIZE - BORDER_EDGE_SIZE + 6 - sliderPos, 79, GREY5, false, false);
                    } else if (flag0) {
                        this.drawSplitString(GCCoreUtil.translate("gui.message.click_again.2.name"), RHS - 182 + 41, this.height - BORDER_SIZE - BORDER_EDGE_SIZE + 6 - sliderPos, 79, GREY5, false, false);
                    } else {
                        this.drawSplitString(GCCoreUtil.translate("gui.message.click_again.3.name"), RHS - 182 + 41, this.height - BORDER_SIZE - BORDER_EDGE_SIZE + 11 - sliderPos, 79, GREY5, false, false);
                    }
                }

                if (this.selectedBody instanceof Satellite && this.renamingSpaceStation) {
                    this.drawDefaultBackground();
                    GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
                    this.mc.renderEngine.bindTexture(guiMain1);
                    this.drawTexturedModalRect(this.width / 2 - 90, this.height / 2 - 38, 179, 67, 159, 0, 179, 67, false, false);
                    this.drawTexturedModalRect(this.width / 2 - 90 + 4, this.height / 2 - 38 + 2, 171, 10, 159, 92, 171, 10, false, false);
                    this.drawTexturedModalRect(this.width / 2 - 90 + 8, this.height / 2 - 38 + 18, 161, 13, 159, 67, 161, 13, false, false);
                    this.drawTexturedModalRect(this.width / 2 - 90 + 17, this.height / 2 - 38 + 59, 72, 12, 159, 80, 72, 12, true, false);
                    this.drawTexturedModalRect(this.width / 2, this.height / 2 - 38 + 59, 72, 12, 159, 80, 72, 12, false, false);
                    str = GCCoreUtil.translate("gui.message.assign_name.name");
                    this.fontRenderer.drawString(str, this.width / 2 - this.fontRenderer.getStringWidth(str) / 2, this.height / 2 - 35, WHITE);
                    str = GCCoreUtil.translate("gui.message.apply.name");
                    this.fontRenderer.drawString(str, this.width / 2 - this.fontRenderer.getStringWidth(str) / 2 - 36, this.height / 2 + 23, WHITE);
                    str = GCCoreUtil.translate("gui.message.cancel.name");
                    this.fontRenderer.drawString(str, this.width / 2 + 36 - this.fontRenderer.getStringWidth(str) / 2, this.height / 2 + 23, WHITE);
                    if (this.renamingString == null) {
                        selectedSatellite = (Satellite)this.selectedBody;
                        String playerName = PlayerUtil.getName(this.mc.player);
                        this.renamingString = ((GuiCelestialSelection.StationDataGUI)((Map)this.spaceStationMap.get(this.getSatelliteParentID(selectedSatellite))).get(playerName)).getStationName();
                        if (this.renamingString == null) {
                            this.renamingString = ((GuiCelestialSelection.StationDataGUI)((Map)this.spaceStationMap.get(this.getSatelliteParentID(selectedSatellite))).get(playerName.toLowerCase())).getStationName();
                        }

                        if (this.renamingString == null) {
                            this.renamingString = "";
                        }
                    }

                    str = this.renamingString;
                    String str0 = this.renamingString;
                    if (this.ticksSinceMenuOpenF / 10.0F % 2.0F == 0.0F) {
                        str0 = str0 + "_";
                    }

                    this.fontRenderer.drawString(str0, this.width / 2 - this.fontRenderer.getStringWidth(str) / 2, this.height / 2 - 17, WHITE);
                }
            }
        }

        if (!handledSliderPos) {
            this.zoomTooltipPos = 0;
        }

    }
}
