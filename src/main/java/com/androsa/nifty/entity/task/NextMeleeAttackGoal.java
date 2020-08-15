package com.androsa.nifty.entity.task;

import com.androsa.nifty.entity.NetheriteGolemEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class NextMeleeAttackGoal extends MeleeAttackGoal {

    public NextMeleeAttackGoal(NetheriteGolemEntity entity) {
        super(entity, 0.4F, false);
    }

    @Override
    public boolean shouldExecute() {
        if (((NetheriteGolemEntity)attacker).getFireballs() <= 0) {
            return super.shouldExecute();
        }
        return false;
    }
}
