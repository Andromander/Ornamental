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
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean shouldExecute() {
        return !parentEntity.getMoveHelper().isUpdating() && parentEntity.getRNG().nextInt(7) == 0;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return false;
    }

    @Override
    public void startExecuting() {
        BlockPos pos = parentEntity.getPosition();

        for(int i = 0; i < 3; ++i) {
            BlockPos blockpos1 = pos.add(parentEntity.getRNG().nextInt(15) - 7, parentEntity.getRNG().nextInt(11) - 5, parentEntity.getRNG().nextInt(15) - 7);
            if (parentEntity.world.isAirBlock(blockpos1)) {
                parentEntity.getMoveHelper().setMoveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25D);
                if (parentEntity.getAttackTarget() == null) {
                    parentEntity.getLookController().setLookPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                }
                break;
            }
        }
    }
}
