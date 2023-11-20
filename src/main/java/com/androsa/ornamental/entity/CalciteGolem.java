package com.androsa.ornamental.entity;

import com.androsa.ornamental.entity.task.EchoAttackGoal;
import com.androsa.ornamental.entity.task.ResonateGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.jetbrains.annotations.Nullable;

public class CalciteGolem extends OrnamentalGolem {

    private static final EntityDataAccessor<Boolean> NEWLY_TARGETED = SynchedEntityData.defineId(CalciteGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> RESONATING = SynchedEntityData.defineId(CalciteGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> CHARGE_STATE = SynchedEntityData.defineId(CalciteGolem.class, EntityDataSerializers.INT);
    private int resonateTimer = 60;

    public CalciteGolem(EntityType<? extends AbstractGolem> type, Level world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(NEWLY_TARGETED, false);
        this.entityData.define(RESONATING, false);
        this.entityData.define(CHARGE_STATE, 0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setNewlyTargeted(tag.getBoolean("NewlyTargeted"));
        this.setResonating(tag.getBoolean("IsResonating"));
        this.setChargeState(tag.getInt("ChargeState"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("NewlyTargeted", this.newlyTargeted());
        tag.putBoolean("IsResonating", this.isResonating());
        tag.putInt("ChargeState", this.getChargeState());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new ResonateGoal(this));
        this.goalSelector.addGoal(2, new EchoAttackGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (target) ->
                target instanceof Warden));
    }

    public boolean newlyTargeted() {
        return this.entityData.get(NEWLY_TARGETED);
    }

    public void setNewlyTargeted(boolean flag) {
        this.entityData.set(NEWLY_TARGETED, flag);
    }

    public boolean isResonating() {
        return this.entityData.get(RESONATING);
    }

    public void setResonating(boolean flag) {
        this.entityData.set(RESONATING, flag);
    }

    public int getChargeState() {
        return this.entityData.get(CHARGE_STATE);
    }

    public void setChargeState(int state) {
        state = Mth.clamp(state, 0, 2);
        this.entityData.set(CHARGE_STATE, state);
    }

    public int getResonateTimer() {
        return this.resonateTimer;
    }

    public void tickResonateTimer() {
        this.resonateTimer--;
    }

    public void resetResonateTimer() {
        this.resonateTimer = 60;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 70.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(NeoForgeMod.STEP_HEIGHT.get(), 1.2F);
    }

    @Override
    public void setTarget(@Nullable LivingEntity entity) {
        if (entity instanceof Warden || entity == null) {
            if (entity != null && !this.newlyTargeted()) {
                this.setNewlyTargeted(true);
            } else if (entity == null && this.getResonateTimer() <= 0) {
                this.resetResonateTimer();
            }
            super.setTarget(entity);
        }
    }

    @Override
    public boolean hasLineOfSight(Entity target) {
        if (target.level() != this.level()) {
            return false;
        } else {
            Vec3 vec3 = new Vec3(this.getX(), this.getEyeY(), this.getZ());
            Vec3 vec31 = new Vec3(target.getX(), target.getEyeY(), target.getZ());
            return vec31.distanceTo(vec3) < 128.0D;
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.isResonating()) {
            if (this.level().getGameTime() % 3 == 0) {
                this.playSound(SoundEvents.AMETHYST_CLUSTER_FALL, 5.0F, 10.0F);
            }
        }

        if (this.level().isClientSide) {
            if (this.getChargeState() == 1) {
                for(int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.CRIT, this.getX() + (random.nextFloat() - 0.5F), this.getY(0.65F) + (random.nextFloat() - 0.5F), this.getZ() + (random.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
                    this.level().addParticle(ParticleTypes.ELECTRIC_SPARK, this.getX(), this.getY(0.65F), this.getZ(), 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.CALCITE_HIT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CALCITE_BREAK;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.CALCITE_STEP, 1.0F, 1.0F);
    }

    @Override
    public boolean isSteppingCarefully() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.SONIC_BOOM)) {
            if (source.getEntity() instanceof Warden && this.getTarget() != source.getEntity()) {
                this.setTarget((LivingEntity) source.getEntity());
            }
            this.setChargeState(1);
            return false;
        }

        if (source.is(DamageTypes.MOB_ATTACK) && source.getEntity() instanceof Warden) {
            amount *= 0.5F;
            source.getEntity().hurt(this.level().damageSources().mobAttack(this), amount);
            if (this.getChargeState() == 1) {
                amount *= 0.5F;
            }
        }

        return super.hurt(source, amount);
    }

    @Override
    protected boolean canRepair(ItemStack stack) {
        return stack.is(Items.CALCITE);
    }
}
