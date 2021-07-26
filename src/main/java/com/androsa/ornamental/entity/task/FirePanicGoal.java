package com.androsa.ornamental.entity.task;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;

public class FirePanicGoal extends PanicGoal {

    public FirePanicGoal(PathfinderMob creature, double speed) {
        super(creature, speed);
    }

    @Override
    public boolean canUse() {
        return mob.isOnFire() && super.canUse();
    }
}
