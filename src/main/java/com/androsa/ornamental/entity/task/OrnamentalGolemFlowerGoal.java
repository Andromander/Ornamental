package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.FlowerGolemEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.merchant.villager.VillagerEntity;

import java.util.EnumSet;

/**
 * Copy of ShowVillagerFlowerGoal, but accepts any Golem entity that can offer a flower.
 */
public class OrnamentalGolemFlowerGoal extends Goal {

    private static final EntityPredicate IS_FRIENDLY = (new EntityPredicate()).setDistance(6.0D).allowFriendlyFire().allowInvulnerable();
    private final FlowerGolemEntity golem;
    private VillagerEntity villager;
    private int lookTime;

    public OrnamentalGolemFlowerGoal(FlowerGolemEntity entity) {
        this.golem = entity;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean shouldExecute() {
        if (!this.golem.world.isDaytime()) {
            return false;
        } else if (this.golem.getRNG().nextInt(8000) != 0) {
            return false;
        } else {
            this.villager = this.golem.world.getClosestEntityWithinAABB(VillagerEntity.class, IS_FRIENDLY, this.golem, this.golem.getPosX(), this.golem.getPosY(), this.golem.getPosZ(), this.golem.getBoundingBox().grow(6.0D, 2.0D, 6.0D));
            return this.villager != null;
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.lookTime > 0;
    }

    @Override
    public void startExecuting() {
        this.lookTime = 400;
        this.golem.setHoldingFlower(true);
    }

    @Override
    public void resetTask() {
        this.golem.setHoldingFlower(false);
        this.villager = null;
    }

    @Override
    public void tick() {
        this.golem.getLookController().setLookPositionWithEntity(this.villager, 30.0F, 30.0F);
        --this.lookTime;
    }
}
