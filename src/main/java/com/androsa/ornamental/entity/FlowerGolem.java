package com.androsa.ornamental.entity;

import com.androsa.ornamental.entity.task.OrnamentalGolemFlowerGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Class for Golems that hold a flower
 */
public abstract class FlowerGolem extends OrnamentalGolem {

    protected int holdFlowerTimer;

    public FlowerGolem(EntityType<? extends FlowerGolem> entity, Level world) {
        super(entity, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new OrnamentalGolemFlowerGoal(this));
    }

    public void setHoldingFlower(boolean holding) {
        if (holding) {
            this.holdFlowerTimer = 400;
            this.level().broadcastEntityEvent(this, (byte)11);
        } else {
            this.holdFlowerTimer = 0;
            this.level().broadcastEntityEvent(this, (byte)34);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.holdFlowerTimer > 0) {
            --this.holdFlowerTimer;
        }
    }

    @Override
    public void handleEntityEvent(byte data) {
        if (data == 11) {
            this.holdFlowerTimer = 400;
        } else if (data == 34) {
            this.holdFlowerTimer = 0;
        } else {
            super.handleEntityEvent(data);
        }
    }

    public int getHoldFlowerTick() {
        return this.holdFlowerTimer;
    }

    public abstract BlockState getFlower();

    public abstract double[] getFlowerPos();
}
