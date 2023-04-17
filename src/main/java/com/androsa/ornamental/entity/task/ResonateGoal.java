package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.CalciteGolem;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class ResonateGoal extends Goal {

    private final CalciteGolem golem;

    public ResonateGoal(CalciteGolem golem) {
        this.golem = golem;

        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return this.golem.newlyTargeted() && this.golem.getTarget() != null && this.golem.getResonateTimer() > 0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.golem.getResonateTimer() > 0;
    }

    @Override
    public void start() {
        this.golem.getNavigation().stop();
        this.golem.setResonating(true);
    }

    @Override
    public void stop() {
        this.golem.setNewlyTargeted(false);
        this.golem.setResonating(false);
    }

    @Override
    public void tick() {
        this.golem.tickResonateTimer();
    }
}
