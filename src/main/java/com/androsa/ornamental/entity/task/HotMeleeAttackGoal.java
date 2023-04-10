package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.MagmaGolem;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class HotMeleeAttackGoal extends MeleeAttackGoal {

    public HotMeleeAttackGoal(MagmaGolem entity) {
        super(entity, 0.4F, false);
    }

    @Override
    public boolean canUse() {
        if (((MagmaGolem) mob).getState() == 1) {
            return super.canUse();
        }
        return false;
    }
}
