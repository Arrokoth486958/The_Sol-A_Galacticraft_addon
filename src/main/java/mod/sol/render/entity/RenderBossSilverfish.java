package mod.sol.render.entity;

import mod.sol.entities.boss.EntityBossSilverfish;
import mod.sol.util.Reference;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBossSilverfish extends RenderLiving<EntityBossSilverfish>
{
    private static final ResourceLocation SILVERFISH_TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/entities/silverfish.png");

    public RenderBossSilverfish(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSilverfish(), 2.4F);
    }

    protected float getDeathMaxRotation(EntityBossSilverfish entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBossSilverfish entity)
    {
        return SILVERFISH_TEXTURES;
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityBossSilverfish entitylivingbaseIn, float partialTickTime)
    {
        float f = 1.0F;
        float f1 = 4.5F;
        float f2 = 4.5F;
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.rotate((float) (Math.pow(entitylivingbaseIn.deathTicks, 2) / 5.0F + (Math.pow(entitylivingbaseIn.deathTicks, 2) / 5.0F - Math.pow(entitylivingbaseIn.deathTicks - 1, 2) / 5.0F) * partialTickTime), 0.0F, 1.0F, 0.0F);
    }
}