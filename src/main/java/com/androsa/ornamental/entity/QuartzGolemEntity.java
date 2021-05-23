package com.androsa.ornamental.entity;

import com.androsa.ornamental.entity.task.FireballAttackGoal;
import com.androsa.ornamental.entity.task.QuartzMoveController;
import com.androsa.ornamental.entity.task.RandomFlyGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class QuartzGolemEntity extends AbstractGolemEntity {

    private static final DataParameter<Boolean> TARGETING = EntityDataManager.defineId(QuartzGolemEntity.class, DataSerializers.BOOLEAN);

    public QuartzGolemEntity(EntityType<? extends QuartzGolemEntity> entity, World world) {
        super(entity, world);
        this.moveControl = new QuartzMoveController(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FireballAttackGoal(this));
        this.goalSelector.addGoal(6, new RandomFlyGoal(this));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (target) ->
                target instanceof IMob));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 70.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.4D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TARGETING, false);
    }

    public boolean isTargeting() {
        return entityData.get(TARGETING);
    }

    public void setTargeting(boolean flag) {
        entityData.set(TARGETING, flag);
    }

    @Override
    public boolean causeFallDamage(float amount, float multiplier) {
        return false;
    }

    @Override
    protected void checkFallDamage(double amount, boolean onGround, BlockState state, BlockPos pos) {
    }

    //[VanillaCopy] FlyingEntity.travel so we can have a flying non-FlyingEntity
    @Override
    public void travel(Vector3d motion) {
        if (this.isInWater()) {
            this.moveRelative(0.02F, motion);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale((double)0.8F));
        } else if (this.isInLava()) {
            this.moveRelative(0.02F, motion);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
        } else {
            BlockPos ground = new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ());
            float f = 0.91F;
            if (this.onGround) {
                f = this.level.getBlockState(ground).getSlipperiness(this.level, ground, this) * 0.91F;
            }

            float f1 = 0.16277137F / (f * f * f);
            f = 0.91F;
            if (this.onGround) {
                f = this.level.getBlockState(ground).getSlipperiness(this.level, ground, this) * 0.91F;
            }

            this.moveRelative(this.onGround ? 0.1F * f1 : 0.02F, motion);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale((double)f));
        }

        this.animationSpeedOld = this.animationSpeed;
        double d1 = this.getX() - this.xo;
        double d0 = this.getZ() - this.zo;
        float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
        if (f2 > 1.0F) {
            f2 = 1.0F;
        }

        this.animationSpeed += (f2 - this.animationSpeed) * 0.4F;
        this.animationPosition += this.animationSpeed;
    }

    @Override
    protected int decreaseAirSupply(int time) {
        return time;
    }

    @Override
    public boolean onClimbable() {
        return false;
    }

    @Override
    protected void doPush(Entity target) {
        if (target instanceof IMob && this.getRandom().nextInt(20) == 0) {
            this.setTarget((LivingEntity)target);
        }

        super.doPush(target);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        setTargeting(target != null);
        super.setTarget(target);
    }

    @Override
    public boolean canAttackType(EntityType<?> target) {
        return target != EntityType.PLAYER && super.canAttackType(target);
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
    protected float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.6F;
    }

    @Override
    public void aiStep() {
        if (this.level.isClientSide && isTargeting()) {
            for(int i = 0; i < 2; ++i) {
                this.level.addParticle(ParticleTypes.FLAME, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }

        super.aiStep();
    }

    //processInteract
    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (item != Items.QUARTZ) {
            return ActionResultType.PASS;
        } else {
            float f = this.getHealth();
            this.heal(25.0F);
            if (this.getHealth() == f) {
                return ActionResultType.PASS;
            } else {
                float f1 = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, f1);
                if (!player.abilities.instabuild) {
                    itemstack.shrink(1);
                }

                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 2.0F;
    }
}
