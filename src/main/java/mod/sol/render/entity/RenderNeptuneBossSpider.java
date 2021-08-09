package mod.sol.render.entity;

import mod.sol.entities.boss.EntityNeptuneBossSpider;
import mod.sol.render.layer.LayerNeptuneBossSpiderEye;
import mod.sol.util.Reference;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNeptuneBossSpider<T extends EntityNeptuneBossSpider> extends RenderLiving<T>
{
    private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/entities/spider.png");

    public RenderNeptuneBossSpider(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSpider(), 3.0F);
        this.addLayer(new LayerNeptuneBossSpiderEye(this));
    }

    @Override
    protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime) {
        float f = 0.999F;
        GlStateManager.scale(3F, 3F, 3F);
        GlStateManager.rotate((float) (Math.pow(entitylivingbaseIn.deathTicks, 2) / 5.0F + (Math.pow(entitylivingbaseIn.deathTicks, 2) / 5.0F - Math.pow(entitylivingbaseIn.deathTicks - 1, 2) / 5.0F) * partialTickTime), 0.0F, 1.0F, 0.0F);
    }

    protected float getDeathMaxRotation(T entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(T entity)
    {
        return SPIDER_TEXTURES;
    }
}