package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.FlowerGolem;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.Villager;

import java.util.EnumSet;

/**
 * Copy of ShowVillagerFlowerGoal, but accepts any Golem entity that can offer a flower.
 */
public class OrnamentalGolemFlowerGoal extends Goal {

    private static final TargetingConditions IS_FRIENDLY = TargetingConditions.forNonCombat().range(6.0D);
    private final FlowerGolem golem;
    private Villager villager;
    private int lookTime;

    public OrnamentalGolemFlowerGoal(FlowerGolem entity) {
        this.golem = entity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.golem.level().isDay()) {
            return false;
        } else if (this.golem.getRandom().nextInt(8000) != 0) {
            return false;
        } else {
            this.villager = this.golem.level().getNearestEntity(Villager.class, IS_FRIENDLY, this.golem, this.golem.getX(), this.golem.getY(), this.golem.getZ(), this.golem.getBoundingBox().inflate(6.0D, 2.0D, 6.0D));
            return this.villager != null;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.lookTime > 0;
    }

    @Override
    public void start() {
        this.lookTime = 400;
        this.golem.setHoldingFlower(true);
    }

    @Override
    public void stop() {
        this.golem.setHoldingFlower(false);
        this.villager = null;
    }

    @Override
    public void tick() {
        this.golem.getLookControl().setLookAt(this.villager, 30.0F, 30.0F);
        --this.lookTime;
    }
}
