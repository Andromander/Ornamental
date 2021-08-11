package com.androsa.ornamental.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OrnamentalGolem extends AbstractGolem {

    protected int attackTimer;
    protected boolean targetCreeper;

    protected OrnamentalGolem(EntityType<? extends AbstractGolem> type, Level world) {
        super(type, world);
    }

    @Override
    protected int decreaseAirSupply(int time) {
        return time;
    }

    @Override
    protected void doPush(Entity target) {
        if (this.targetCreeper) {
            if (target instanceof Enemy && this.getRandom().nextInt(20) == 0) {
                this.setTarget((LivingEntity)target);
            }
        } else {
            if (target instanceof Enemy && !(target instanceof Creeper) && this.getRandom().nextInt(20) == 0) {
                this.setTarget((LivingEntity)target);
            }
        }

        super.doPush(target);
    }

    @Override
    public boolean canAttackType(EntityType<?> target) {
        if (targetCreeper) {
            return target != EntityType.PLAYER && super.canAttackType(target);
        }
        return target != EntityType.CREEPER && target != EntityType.PLAYER && super.canAttackType(target);
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
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        return this.repairGolem(player, hand);
    }

    protected InteractionResult repairGolem(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (this.canRepair(itemstack)) {
            float f = this.getHealth();
            this.heal(25.0F);
            if (this.getHealth() == f) {
                return InteractionResult.PASS;
            } else {
                float f1 = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, f1);
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        } else {
            return InteractionResult.PASS;
        }
    }

    protected boolean canRepair(ItemStack stack) {
        return false;
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
