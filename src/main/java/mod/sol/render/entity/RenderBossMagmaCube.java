package mod.sol.render.entity;

import mod.sol.entities.boss.EntityBossMagmaCube;
import mod.sol.render.model.entity.ModelBossMagmaCube;
import mod.sol.util.Reference;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBossMagmaCube extends RenderLiving<EntityBossMagmaCube>
{
    private static final ResourceLocation MAGMA_CUBE_TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/entities/magmacube.png");

    public RenderBossMagmaCube(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelBossMagmaCube(), 0.25F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBossMagmaCube entity)
    {
        return MAGMA_CUBE_TEXTURES;
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityBossMagmaCube entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.999F;
        GlStateManager.scale(10F, 10F, 10F);
        GlStateManager.rotate((float) (Math.pow(entitylivingbaseIn.deathTicks, 2) / 5.0F + (Math.pow(entitylivingbaseIn.deathTicks, 2) / 5.0F - Math.pow(entitylivingbaseIn.deathTicks - 1, 2) / 5.0F) * partialTickTime), 0.0F, 1.0F, 0.0F);
    }
}