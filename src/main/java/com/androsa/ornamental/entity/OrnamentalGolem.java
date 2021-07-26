package com.androsa.ornamental.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OrnamentalGolem extends AbstractGolem {

    protected int attackTimer;

    protected OrnamentalGolem(EntityType<? extends AbstractGolem> type, Level world) {
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
    public boolean checkSpawnObstruction(LevelReader reader) {
        BlockPos entityPos = this.blockPosition();
        BlockPos posBelow = entityPos.below();
        BlockState stateBelow = reader.getBlockState(posBelow);
        if (!stateBelow.entityCanStandOn(reader, posBelow, this)) {
            return false;
        } else {
            for(int i = 1; i < 3; ++i) {
                BlockPos posAbove = entityPos.above(i);
                BlockState stateAbove = reader.getBlockState(posAbove);
                if (!NaturalSpawner.isValidEmptySpawnBlock(reader, posAbove, stateAbove, stateAbove.getFluidState(), this.getType())) {
                    return false;
                }
            }

            return NaturalSpawner.isValidEmptySpawnBlock(reader, entityPos, reader.getBlockState(entityPos), Fluids.EMPTY.defaultFluidState(), this.getType()) && reader.isUnobstructed(this);
        }
    }
}
