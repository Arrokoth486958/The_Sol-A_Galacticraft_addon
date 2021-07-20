package mod.sol.render.entity;

import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.client.model.ModelEvolvedSkeletonBoss;
import micdoodle8.mods.galacticraft.core.client.render.entities.layer.LayerHeldItemEvolvedSkeletonBoss;
import micdoodle8.mods.galacticraft.core.entities.EntitySkeletonBoss;
import mod.sol.entities.boss.EntitySaturnBossStray;
import mod.sol.render.layer.LayerHeldItemSaturnBossSkeleton;
import mod.sol.render.model.entity.ModelSaturnBossStray;
import mod.sol.util.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSaturnBossStray extends RenderLiving<EntitySaturnBossStray>
{
    private static final ResourceLocation skeletonBossTexture = new ResourceLocation(Reference.MOD_ID, "textures/entities/strayboss.png");

    public RenderSaturnBossStray(RenderManager manager)
    {
        super(manager, new ModelSaturnBossStray(), 0.9F);
        this.addLayer(new LayerHeldItemSaturnBossSkeleton(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySaturnBossStray entity)
    {
        return RenderSaturnBossStray.skeletonBossTexture;
    }

    @Override
    protected void preRenderCallback(EntitySaturnBossStray entity, float partialTicks)
    {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
        GlStateManager.rotate((float) (Math.pow(entity.deathTicks, 2) / 5.0F + (Math.pow(entity.deathTicks, 2) / 5.0F - Math.pow(entity.deathTicks - 1, 2) / 5.0F) * partialTicks), 0.0F, 1.0F, 0.0F);
    }
}
