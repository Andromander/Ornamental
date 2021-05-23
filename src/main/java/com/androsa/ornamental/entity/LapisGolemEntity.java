package com.androsa.ornamental.entity;

import com.androsa.ornamental.entity.projectile.LapisBulletEntity;
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
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class LapisGolemEntity extends AbstractGolemEntity implements IRangedAttackMob {

    private static final DataParameter<Boolean> TARGETING = EntityDataManager.defineId(LapisGolemEntity.class, DataSerializers.BOOLEAN);

    public LapisGolemEntity(EntityType<? extends LapisGolemEntity> entity, World world) {
        super(entity, world);
        this.maxUpStep = 1.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.5D, 15, 20.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (target) ->
                target instanceof IMob));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 70.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.7D);
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
    protected int decreaseAirSupply(int time) {
        return time;
    }

    @Override
    protected void doPush(Entity target) {
        if (target instanceof IMob && this.getRandom().nextInt(20) == 0) {
            this.setTarget((LivingEntity)target);
        }

        super.doPush(target);
    }

    @Override
    public boolean canAttackType(EntityType<?> target) {
        return target != EntityType.PLAYER && super.canAttackType(target);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if (target == null) {
            setTargeting(false);
        } else {
            setTargeting(true);
        }
        super.setTarget(target);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    //processInteract
    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (item != Items.LAPIS_LAZULI) {
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
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float multiplier) {
        LapisBulletEntity bullet = new LapisBulletEntity(this.level, this);
        double eye = entity.getEyeY() - (double)1.1F;
        double x = entity.getX() - this.getX();
        double y = eye - bullet.getY();
        double z = entity.getZ() - this.getZ();
        float f = MathHelper.sqrt(x * x + z * z) * 0.2F;

        bullet.shoot(x, y + (double)f, z, 1.6F, 12.0F);
        this.playSound(SoundEvents.CROSSBOW_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(bullet);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 2.1F;
    }
}
