package com.androsa.ornamental.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
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
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class BoneGolemEntity extends AbstractGolemEntity implements IRangedAttackMob {

    private static final DataParameter<Boolean> TARGETING = EntityDataManager.defineId(BoneGolemEntity.class, DataSerializers.BOOLEAN);

    public BoneGolemEntity(EntityType<? extends BoneGolemEntity> entity, World world) {
        super(entity, world);
        this.maxUpStep = 1.0F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.5D, 30, 20.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (target) ->
                target instanceof IMob));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
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
        setTargeting(target != null);
        super.setTarget(target);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Override
    protected float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.6F;
    }

    //processInteract
    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (!item.is(Tags.Items.BONES) || item != Items.BONE_MEAL) {
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
        this.playSound(SoundEvents.SKELETON_STEP, 1.0F, 1.0F);
    }

    @Override
    public ItemStack getProjectile(ItemStack stack) {
        return new ItemStack(Items.ARROW);
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float multiplier) {
        ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, Items.BOW)));
        AbstractArrowEntity abstractarrowentity = this.getArrow(itemstack, multiplier);

        double x = entity.getX() - this.getX();
        double y = entity.getY(0.3333333333333333D) - abstractarrowentity.getY();
        double z = entity.getZ() - this.getZ();
        double sqrt = (double) MathHelper.sqrt(x * x + z * z);
        abstractarrowentity.shoot(x, y + sqrt * (double)0.2F, z, 1.6F, (float)(14 - this.level.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(abstractarrowentity);
    }

    protected AbstractArrowEntity getArrow(ItemStack stack, float multiplier) {
        return ProjectileHelper.getMobArrow(this, stack, multiplier);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 2.7F;
    }
}
