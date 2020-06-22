package com.androsa.nifty.entity;

import com.androsa.nifty.entity.task.ShowVillagerFlowerNiftyGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Class for Golems that hold a flower
 */
public abstract class FlowerGolemEntity extends AbstractGolemEntity {

    protected int holdFlowerTimer;

    public FlowerGolemEntity(EntityType<? extends FlowerGolemEntity> entity, World world) {
        super(entity, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new ShowVillagerFlowerNiftyGoal(this));
    }

    public void setHoldingFlower(boolean holding) {
        if (holding) {
            this.holdFlowerTimer = 400;
            this.world.setEntityState(this, (byte)11);
        } else {
            this.holdFlowerTimer = 0;
            this.world.setEntityState(this, (byte)34);
        }
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (this.holdFlowerTimer > 0) {
            --this.holdFlowerTimer;
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte data) {
        if (data == 11) {
            this.holdFlowerTimer = 400;
        } else if (data == 34) {
            this.holdFlowerTimer = 0;
        } else {
            super.handleStatusUpdate(data);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getHoldFlowerTick() {
        return this.holdFlowerTimer;
    }

    public abstract BlockState getFlower();

    public abstract double[] getFlowerPos();
}
