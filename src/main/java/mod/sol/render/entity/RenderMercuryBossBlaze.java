package mod.sol.render.entity;

import org.lwjgl.opengl.GL11;

import micdoodle8.mods.galacticraft.planets.mars.entities.EntityCreeperBoss;
import mod.sol.entities.boss.EntityMercuryBossBlaze;
import mod.sol.util.Reference;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMercuryBossBlaze extends RenderLiving<EntityMercuryBossBlaze>
{
    private static final ResourceLocation BLAZE_TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/entities/mercury_blaze_boss.png");

    public RenderMercuryBossBlaze(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBlaze(), 2F);
    }

    @Override
    protected void preRenderCallback(EntityMercuryBossBlaze entity, float partialTicks)
    {
        GL11.glScalef(4.0F, 4.0F, 4.0F);
        GlStateManager.rotate((float) (Math.pow(entity.deathTicks, 2) / 5.0F + (Math.pow(entity.deathTicks, 2) / 5.0F - Math.pow(entity.deathTicks - 1, 2) / 5.0F) * partialTicks), 0.0F, 1.0F, 0.0F);
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityMercuryBossBlaze entity)
    {
        return BLAZE_TEXTURES;
    }
}