package com.androsa.ornamental.entity;

import com.androsa.ornamental.entity.task.HotMeleeAttackGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
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
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class MagmaGolem extends OrnamentalGolem {

    private static final EntityDataAccessor<Integer> STATE_ID = SynchedEntityData.defineId(MagmaGolem.class, EntityDataSerializers.INT);
    private static final UUID HEATED_SPEED_UUID = UUID.fromString("82C7A9E0-8621-4284-895F-33F41F129263");
    private static final UUID COOLED_SPEED_UUID = UUID.fromString("335C7BCE-E224-443A-B8D5-888DFFF1E80E");
    private static final AttributeModifier HEATED_SPEED_MODIFIER = new AttributeModifier(HEATED_SPEED_UUID, "Heated speed boost", 0.25D, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier COOLED_SPEED_MODIFIER = new AttributeModifier(COOLED_SPEED_UUID, "Cooled speed nerf", -0.25D, AttributeModifier.Operation.ADDITION);
    private int cooldownTimer = 20 * 20;

    public MagmaGolem(EntityType<MagmaGolem> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 90.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.55D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new HotMeleeAttackGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (target) ->
                target instanceof Enemy && !(target instanceof Creeper)));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE_ID, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("StateID", this.getState());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setState(tag.getInt("StateID"));
    }

    public int getState() {
        return this.entityData.get(STATE_ID);
    }

    public void setState(int state) {
        AttributeInstance instance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (state < 0 || state > 2) {
            state = 0;
        }
        if (state == 0 || state == 2) {
            if (instance.hasModifier(HEATED_SPEED_MODIFIER)) {
                instance.removeModifier(HEATED_SPEED_MODIFIER);
            }
        } else {
            if (!instance.hasModifier(HEATED_SPEED_MODIFIER)) {
                instance.addTransientModifier(HEATED_SPEED_MODIFIER);
            }
        }
        if (state == 0 || state == 1) {
            if (instance.hasModifier(COOLED_SPEED_MODIFIER)) {
                instance.removeModifier(COOLED_SPEED_MODIFIER);
            }
        } else {
            if (!instance.hasModifier(COOLED_SPEED_MODIFIER)) {
                instance.addTransientModifier(COOLED_SPEED_MODIFIER);
            }
        }
        this.entityData.set(STATE_ID, state);
    }

    @Override
    public void setTarget(@Nullable LivingEntity entity) {
        if (entity != null) {
            if (this.getState() != 2 && this.getState() != 1) {
                this.setState(1);
                this.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 1.0F);
            }
        } else {
            if (this.getState() != 2 && this.getState() != 0) {
                this.setState(0);
            }
        }

        super.setTarget(entity);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.isInWaterOrRain() && !this.isInLava()) {
            this.cooldownTimer = 20 * 20;
            if (this.getState() != 2) {
                this.setState(2);
                this.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE, 1.0F, 1.0F);
            }
        } else {
            this.cooldownTimer--;
        }

        if (this.isInLava()) {
            this.cooldownTimer = 0;
        }

        if (this.cooldownTimer <= 0) {
            if (this.getState() == 2) {
                this.setState(0);
                this.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 1.0F);
            }
        }

        if (this.level().isClientSide) {
            if (this.getState() == 0) {
                for(int i = 0; i < 1; ++i) {
                    this.level().addParticle(ParticleTypes.SMALL_FLAME, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
                }
            } else if (this.getState() == 1) {
                for(int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.FLAME, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        float modifier = this.getState() == 2 && source.is(DamageTypes.MOB_ATTACK) ? amount * 0.5F : amount;
        return super.hurt(source, modifier);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        this.attackTimer = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
        boolean flag = target.hurt(this.damageSources().mobAttack(this), 0.0F);
        if (flag) {
            target.setDeltaMovement(target.getDeltaMovement().add(0.0D, 0.3F, 0.0D));
            this.doEnchantDamageEffects(this, target);
        }
        if (this.getState() == 1) {
            target.setSecondsOnFire(5);
        }

        return flag;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.STONE_STEP, 0.75F, 1.0F);
    }

    @Override
    protected boolean canRepair(ItemStack stack) {
        return stack.is(Items.MAGMA_CREAM);
    }
}
