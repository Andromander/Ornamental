package com.androsa.nifty.entity;

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
    public void livingTick() {
        super.livingTick();

        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte data) {
        if (data == 4) {
            this.attackTimer = 10;
            this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else {
            super.handleStatusUpdate(data);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    public boolean isNotColliding(IWorldReader reader) {
        BlockPos blockpos = new BlockPos(this);
        BlockPos posBelow = blockpos.down();
        BlockState blockstate = reader.getBlockState(posBelow);
        if (!blockstate.isTopSolid(reader, posBelow, this)) {
            return false;
        } else {
            for(int i = 1; i < 3; ++i) {
                BlockPos posAbove = blockpos.up(i);
                BlockState blockstate1 = reader.getBlockState(posAbove);
                if (!WorldEntitySpawner.isSpawnableSpace(reader, posAbove, blockstate1, blockstate1.getFluidState())) {
                    return false;
                }
            }

            return WorldEntitySpawner.isSpawnableSpace(reader, blockpos, reader.getBlockState(blockpos), Fluids.EMPTY.getDefaultState()) && reader.intersectsEntities(this);
        }
    }
}
