package mod.sol.render.entity;

import mod.sol.entities.boss.EntityUranusBossSlime;
import mod.sol.render.layer.LayerUranusBossSlimeGel;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderUranusBossSlime extends RenderLiving<EntityUranusBossSlime>
{
    private static final ResourceLocation SLIME_TEXTURES = new ResourceLocation("textures/entity/slime/slime.png");

    public RenderUranusBossSlime(RenderManager p_i47193_1_)
    {
        super(p_i47193_1_, new ModelSlime(16), 0.25F);
        this.addLayer(new LayerUranusBossSlimeGel(this));
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityUranusBossSlime entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.shadowSize = 3;
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityUranusBossSlime entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.999F;
        GlStateManager.scale(10F, 10F, 10F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityUranusBossSlime entity)
    {
        return SLIME_TEXTURES;
    }
}