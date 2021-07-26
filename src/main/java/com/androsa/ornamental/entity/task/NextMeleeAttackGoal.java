package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.NetheriteGolem;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class NextMeleeAttackGoal extends MeleeAttackGoal {

    public NextMeleeAttackGoal(NetheriteGolem entity) {
        super(entity, 0.4F, false);
    }

    @Override
    public boolean canUse() {
        if (((NetheriteGolem)mob).getFireballs() <= 0) {
            return super.canUse();
        }
        return false;
    }
}
