package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.QuartzGolemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;

//[VanillaCopy] VexEntity.RandomFlyingGoal modified to accept a Golem
public class RandomFlyGoal extends Goal {
    private final QuartzGolemEntity parentEntity;

    public RandomFlyGoal(QuartzGolemEntity entity) {
        this.parentEntity = entity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return !parentEntity.getMoveControl().hasWanted() && parentEntity.getRandom().nextInt(7) == 0;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void start() {
        BlockPos pos = parentEntity.blockPosition();

        for(int i = 0; i < 3; ++i) {
            BlockPos blockpos1 = pos.offset(parentEntity.getRandom().nextInt(15) - 7, parentEntity.getRandom().nextInt(11) - 5, parentEntity.getRandom().nextInt(15) - 7);
            if (parentEntity.level.isEmptyBlock(blockpos1)) {
                parentEntity.getMoveControl().setWantedPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25D);
                if (parentEntity.getTarget() == null) {
                    parentEntity.getLookControl().setLookAt((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                }
                break;
            }
        }
    }
}
