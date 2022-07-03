package mod.sol.util;

import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import micdoodle8.mods.galacticraft.planets.mars.entities.EntityCreeperBoss;
import mod.sol.TheSol;
import mod.sol.entities.boss.*;
import mod.sol.render.entity.RenderMercuryBossBlaze;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class SolEntityRegistry {
	public static void register() {
		//boss
		TheSol.registerEntityCreature(EntityMercuryBossBlaze.class, "mercury_blaze_boss", ColorUtil.to32BitColor(255, 63, 0, 0), ColorUtil.to32BitColor(255, 220, 0, 0));
		TheSol.registerEntityCreature(EntityJupiterBossGhast.class, "jupiter_ghast_boss", ColorUtil.to32BitColor(255, 127, 0, 0), ColorUtil.to32BitColor(255, 0, 0, 0));
		TheSol.registerEntityCreature(EntitySaturnBossStray.class, "saturn_stray_boss", ColorUtil.to32BitColor(255, 225, 255, 225), ColorUtil.to32BitColor(255, 0, 15, 255));
		TheSol.registerEntityCreature(EntityUranusBossSlime.class, "uranus_slime_boss", ColorUtil.to32BitColor(255, 5, 31, 127), ColorUtil.to32BitColor(255, 0, 0, 255));
		TheSol.registerEntityCreature(EntityNeptuneBossSpider.class, "neptune_spider_boss", ColorUtil.to32BitColor(255, 4, 4, 4), ColorUtil.to32BitColor(255, 127, 0, 16));
		TheSol.registerEntityCreature(EntityBossSilverfish.class, "silverfish_boss", ColorUtil.to32BitColor(255, 64, 64, 64), ColorUtil.to32BitColor(255, 127, 127, 127));
		TheSol.registerEntityCreature(EntityBossMagmaCube.class, "magmacube_boss", ColorUtil.to32BitColor(255, 127, 31, 31), ColorUtil.to32BitColor(255, 255, 127, 127));
	}
}
