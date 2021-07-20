package mod.sol.entities.boss;


import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.core.entities.EntityBossBase;
import micdoodle8.mods.galacticraft.core.entities.IBoss;
import micdoodle8.mods.galacticraft.core.tile.TileEntityDungeonSpawner;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import mod.sol.init.SolItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//public class EntityUranusBossSlime extends EntitySlime implements IEntityBreathable, IBoss
public class EntityUranusBossSlime {
    /*protected Random rand;

    public EntityUranusBossSlime(World worldIn)
    {
        super(worldIn);
        this.setSize(5, 5);
    }

    protected void initEntityAI()
    {
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(3, new EntityAIFindEntityNearest(this, EntityIronGolem.class));
    }

    @Override
    protected void setSlimeSize(int size, boolean resetHealth) {
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)(16 * 16));
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)(0.2F + 0.1F * (float)16));
    }

    @Override
    public int getSlimeSize() {
        return 16;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(600.0D * ConfigManagerCore.dungeonBossHealthMod);
    }

    @Override
    public int getChestTier() {
        return 7;
    }

    @Override
    public void dropKey() {
        this.entityDropItem(new ItemStack(SolItems.KEY_TIER_7, 1), 0.5F);
    }

    @Override
    public BossInfo.Color getHealthBarColor() {
        return BossInfo.Color.BLUE;
    }

    @Override
    public void onKillCommand()
    {
        this.setHealth(0.0F);
    }

    @Override
    public ItemStack getGuaranteedLoot(Random rand) {
        List<ItemStack> stackList = new LinkedList<>();
        stackList.addAll(GalacticraftRegistry.getDungeonLoot(7));
        return stackList.get(rand.nextInt(stackList.size())).copy();
    }

    @Override
    public boolean canBreath() {
        return true;
    }

    @Override
    public void onBossSpawned(TileEntityDungeonSpawner spawner) {

    }*/
}