package com.androsa.ornamental.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AbstractGolemEntity extends GolemEntity {

    protected int attackTimer;

    protected AbstractGolemEntity(EntityType<? extends GolemEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte data) {
        if (data == 4) {
            this.attackTimer = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else {
            super.handleEntityEvent(data);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader reader) {
        BlockPos entityPos = this.blockPosition();
        BlockPos posBelow = entityPos.below();
        BlockState stateBelow = reader.getBlockState(posBelow);
        if (!stateBelow.entityCanStandOn(reader, posBelow, this)) {
            return false;
        } else {
            for(int i = 1; i < 3; ++i) {
                BlockPos posAbove = entityPos.above(i);
                BlockState stateAbove = reader.getBlockState(posAbove);
                if (!WorldEntitySpawner.isValidEmptySpawnBlock(reader, posAbove, stateAbove, stateAbove.getFluidState(), this.getType())) {
                    return false;
                }
            }

            return WorldEntitySpawner.isValidEmptySpawnBlock(reader, entityPos, reader.getBlockState(entityPos), Fluids.EMPTY.defaultFluidState(), this.getType()) && reader.isUnobstructed(this);
        }
    }
}
