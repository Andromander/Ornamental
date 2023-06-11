package com.androsa.ornamental.entity;

import com.androsa.ornamental.entity.projectile.ChargeBall;
import com.androsa.ornamental.registry.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class CopperGolem extends OrnamentalGolem {

    private static final EntityDataAccessor<Integer> EROSION_LEVEL = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EROSION_TIMER = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> CHARGED = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> CHARGE_TIMER = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CHARGES = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> RECHARGE_TIMER = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> TARGETING = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> WAXED = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.BOOLEAN);

    public CopperGolem(EntityType<? extends CopperGolem> entity, Level level) {
        super(entity, level);
        this.targetCreeper = false;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EROSION_LEVEL, 0);
        this.entityData.define(CHARGED, false);
        this.entityData.define(TARGETING, false);
        this.entityData.define(CHARGE_TIMER, 0);
        this.entityData.define(EROSION_TIMER, 1200);
        this.entityData.define(WAXED, false);
        this.entityData.define(CHARGES, 10);
        this.entityData.define(RECHARGE_TIMER, 100);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new ChargeBallAttackGoal(this));
        this.goalSelector.addGoal(5, new CopperAttackGoal(this));
        this.goalSelector.addGoal(6, new CopperWalkingGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (target) ->
                target instanceof Enemy));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 110.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D);
    }

    public int getErosion() {
        return this.entityData.get(EROSION_LEVEL);
    }

    public void setErosion(int level) {
        this.entityData.set(EROSION_LEVEL, Mth.clamp(level, 0, 3));
    }

    public boolean isCharged() {
        return this.entityData.get(CHARGED);
    }

    public void setCharged(boolean flag) {
        this.entityData.set(CHARGED, flag);
    }

    public boolean isTargeting() {
        return this.entityData.get(TARGETING);
    }

    public void setTargeting(boolean flag) {
        this.entityData.set(TARGETING, flag);
    }

    public int getCharges() {
        return this.entityData.get(CHARGES);
    }

    public void setCharges(int amount) {
        this.entityData.set(CHARGES, amount);
    }

    public int getChargeTimer() {
        return this.entityData.get(CHARGE_TIMER);
    }

    public void setChargeTimer(int time) {
        this.entityData.set(CHARGE_TIMER, time);
    }

    public int getErosionTimer() {
        return this.entityData.get(EROSION_TIMER);
    }

    public void setErosionTimer(int time) {
        this.entityData.set(EROSION_TIMER, time);
    }

    public boolean isWaxed() {
        return this.entityData.get(WAXED);
    }

    public void setWaxed(boolean flag) {
        this.entityData.set(WAXED, flag);
    }

    public int getRechargeTimer() {
        return this.entityData.get(RECHARGE_TIMER);
    }

    public void setRechargeTimer(int time) {
        this.entityData.set(RECHARGE_TIMER, time);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Erosion", getErosion());
        tag.putBoolean("Charged", isCharged());
        tag.putBoolean("Targeting", isTargeting());
        tag.putInt("Charges", getCharges());
        tag.putInt("ErosionTimer", getErosionTimer());
        tag.putInt("ChargeTimer", getChargeTimer());
        tag.putBoolean("Waxed", isWaxed());
        tag.putInt("RechargeTimer", getRechargeTimer());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setErosion(tag.getInt("Erosion"));
        this.setCharged(tag.getBoolean("Charged"));
        this.setTargeting(tag.getBoolean("Targeting"));
        this.setChargeTimer(tag.getInt("ChargeTimer"));
        this.setErosionTimer(tag.getInt("ErosionTimer"));
        this.setWaxed(tag.getBoolean("Waxed"));
        this.setCharges(tag.getInt("Charges"));
        this.setRechargeTimer(tag.getInt("RechargeTimer"));
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
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

    @Override
    public void thunderHit(ServerLevel world, LightningBolt bolt) {
        setChargeTimer(1200);
        setCharged(true);
        setErosionTimer(1200);
        setErosion(0);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if (getErosion() < 3) {
            setTargeting(target != null);
            super.setTarget(target);
        }
    }

    @Override
    public void aiStep() {
        double movement = this.getAttributeValue(Attributes.MOVEMENT_SPEED);
        switch (getErosion()) {
            case 1 -> movement *= 0.6D;
            case 2 -> movement *= 0.3D;
            case 3 -> movement *= 0.0D;
            default -> movement *= 1.0D;
        }
        setSpeed((float)movement);

        if (getErosion() < 3 && !isWaxed()) {
            if (getChargeTimer() <= 0) {
                if (getErosionTimer() > 0) {
                    if (isInWaterOrRain()) {
                        setErosionTimer(getErosionTimer() - 2);
                    } else {
                        setErosionTimer(getErosionTimer() - 1);
                    }
                } else {
                    if (random.nextInt(100) == 0 && getErosionTimer() <= 0) {
                        setErosionTimer(1200);
                        setErosion(getErosion() + 1);
                    }
                }
            }
        }

        if (getChargeTimer() > 0) {
            if (this.isCharged()) {
                for (int i = 0; i < 5; i++) {
                    level().addParticle(ModParticles.CHARGE_SPARK.get(), this.getRandomX(0.5F), this.getRandomY(), this.getRandomZ(0.5F), 0.0D, 0.0D, 0.0D);
                }
                setChargeTimer(getChargeTimer() - 1);
            }

            if (getCharges() < 10) {
                if (getRechargeTimer() <= 0) {
                    setCharges(getCharges() + 1);
                    setRechargeTimer(100);
                }
                setRechargeTimer(getRechargeTimer() - 1);
            }
        }

        if (getChargeTimer() <= 0) {
            setCharged(false);
        }

        super.aiStep();
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        this.attackTimer = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
        float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float multiplier = damage > 0.0F ? damage / 2.0F + (float)this.random.nextInt((int)damage) : 0.0F;
        switch (getErosion()) {
            case 1 -> multiplier *= 0.6F;
            case 2 -> multiplier *= 0.3F;
            case 3 -> multiplier *= 0.0F;
            default -> multiplier *= 1.0F;
        }
        if (isCharged())
            multiplier *= 2.0F;
        boolean flag = target.hurt(this.damageSources().mobAttack(this), multiplier);

        if (flag) {
            double x = isCharged() ? Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) : 0.0D;
            double z = isCharged() ? -Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) : 0.0D;
            double y = isCharged() ? 0.8D : 0.5D;
            target.setDeltaMovement(target.getDeltaMovement().add(x, y, z));
            this.doEnchantDamageEffects(this, target);
        }

        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() instanceof AxeItem) {
            if (isWaxed()) {
                setWaxed(false);
                this.level().playSound(player, blockPosition(), SoundEvents.AXE_WAX_OFF, getSoundSource(), 1.0F, 1.0F);
                return InteractionResult.sidedSuccess(this.level().isClientSide());
            } else if (getErosion() > 0) {
                setErosionTimer(1200);
                setErosion(getErosion() - 1);
                this.level().playSound(player, blockPosition(), SoundEvents.AXE_SCRAPE, getSoundSource(), 1.0F, 1.0F);
                itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
                return InteractionResult.sidedSuccess(this.level().isClientSide());
            }
        } else if (itemstack.is(Items.HONEYCOMB)) {
            if (!isWaxed()) {
                setWaxed(true);
                if (this.level().isClientSide()) {
                    this.level().playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D, SoundEvents.HONEYCOMB_WAX_ON, getSoundSource(), 1.0F, 1.0F, false);
                }
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide());
            }
        } else {
            return super.repairGolem(player, hand);
        }

        return InteractionResult.PASS;
    }

    @Override
    protected boolean canRepair(ItemStack stack) {
        return stack.is(Items.COPPER_INGOT);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 2.0F;
    }

    class CopperAttackGoal extends MeleeAttackGoal {

        public CopperAttackGoal(PathfinderMob mob) {
            super(mob, 1.0D, false);
        }

        @Override
        public boolean canUse() {
            return isStable(super.canUse());
        }

        private boolean isStable(boolean base) {
            if (CopperGolem.this.getErosion() < 3) {
                return base;
            }
            return false;
        }
    }

    class CopperWalkingGoal extends WaterAvoidingRandomStrollGoal {

        public CopperWalkingGoal(PathfinderMob mob) {
            super(mob, 0.6D);
        }

        @Override
        public boolean canUse() {
            return isStable(super.canUse());
        }

        private boolean isStable(boolean base) {
            if (CopperGolem.this.getErosion() < 3) {
                return base;
            }
            return false;
        }
    }

    static class ChargeBallAttackGoal extends Goal {
        private final CopperGolem parentEntity;
        public int attackTimer;

        public ChargeBallAttackGoal(CopperGolem entity) {
            this.parentEntity = entity;
            setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return this.parentEntity.getTarget() != null && this.parentEntity.getCharges() > 0 && this.parentEntity.isCharged();
        }

        @Override
        public void start() {
            this.attackTimer = 0;
        }

        @Override
        public void tick() {
            LivingEntity target = this.parentEntity.getTarget();
            this.parentEntity.getLookControl().setLookAt(target, 10.0F, (float)this.parentEntity.getMaxHeadXRot());
            if (target.distanceToSqr(this.parentEntity) < 4096.0D && this.parentEntity.hasLineOfSight(target)) {
                Level world = this.parentEntity.level();
                ++this.attackTimer;

                if (this.attackTimer == 20) {
                    Vec3 vec3 = this.parentEntity.getViewVector(1.0F);
                    double x = target.getX() - (this.parentEntity.getX() + vec3.x * 2.0D);
                    double y = target.getY(0.5D) - (0.5D + this.parentEntity.getY(0.5D));
                    double z = target.getZ() - (this.parentEntity.getZ() + vec3.z * 2.0D);

                    world.levelEvent(null, LevelEvent.SOUND_BLAZE_FIREBALL, parentEntity.blockPosition(), 0);
                    ChargeBall chargeball = new ChargeBall(world, this.parentEntity, x, y, z);
                    chargeball.setPos(chargeball.getX() + vec3.x * 2.0D, this.parentEntity.getY(0.5D) + 0.5D, chargeball.getZ() + vec3.z * 2.0D);
                    world.addFreshEntity(chargeball);
                    parentEntity.setCharges(parentEntity.getCharges() - 1);
                    this.attackTimer = -10;
                }
            } else if (this.attackTimer > 0) {
                --this.attackTimer;
            }
        }
    }
}
