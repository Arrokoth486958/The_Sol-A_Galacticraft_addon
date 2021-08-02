package mod.sol.client.gui.screen;

import com.google.common.collect.Maps;
import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.IChildBody;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
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
}
