package com.androsa.ornamental.entity;

import com.androsa.ornamental.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;

public class ClayGolem extends OrnamentalGolem {

    public ClayGolem(EntityType<? extends ClayGolem> entity, Level world) {
        super(entity, world);
        this.maxUpStep = 1.0F;
        this.targetCreeper = false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6D, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (target) ->
                target instanceof Enemy && !(target instanceof Creeper)));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 0.0D);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        this.attackTimer = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        boolean flag = target.hurt(this.damageSources().mobAttack(this), 0.0F);
        if (flag) {
            target.setDeltaMovement(target.getDeltaMovement().add(0.0D, 0.3F, 0.0D));
            this.doEnchantDamageEffects(this, target);
        }

        return flag;
    }

    @Override
    public void aiStep() {
        if (this.isOnFire()) {
            if (!this.level.isClientSide()) {
                BrickGolem brick = ModEntities.BRICK_GOLEM.get().create(this.level);
                brick.copyPosition(this);
                brick.finalizeSpawn((ServerLevel)this.level, this.level.getCurrentDifficultyAt(brick.blockPosition()), MobSpawnType.CONVERSION, null, null);
                brick.setNoAi(this.isNoAi());
                if (this.hasCustomName()) {
                    brick.setCustomName(this.getCustomName());
                    brick.setCustomNameVisible(this.isCustomNameVisible());
                }

                if (this.isPersistenceRequired()) {
                    brick.setPersistenceRequired();
                }

                brick.setInvulnerable(this.isInvulnerable());
                this.level.addFreshEntity(brick);
                this.discard();
            }

            this.level.levelEvent(null, LevelEvent.SOUND_BLAZE_FIREBALL, this.blockPosition(), 0);
        }
        super.aiStep();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GRAVEL_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GRAVEL_BREAK;
    }

    @Override
    protected boolean canRepair(ItemStack stack) {
        return stack.is(Items.CLAY_BALL);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.GRAVEL_STEP, 1.0F, 1.0F);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 1.9F;
    }
}
