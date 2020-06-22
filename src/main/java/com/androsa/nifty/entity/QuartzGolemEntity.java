package com.androsa.nifty.entity;

import com.androsa.nifty.entity.task.FireballAttackGoal;
import com.androsa.nifty.entity.task.QuartzMoveController;
import com.androsa.nifty.entity.task.RandomFlyGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class QuartzGolemEntity extends AbstractGolemEntity {

    private static final DataParameter<Boolean> TARGETING = EntityDataManager.createKey(QuartzGolemEntity.class, DataSerializers.BOOLEAN);

    public QuartzGolemEntity(EntityType<? extends QuartzGolemEntity> entity, World world) {
        super(entity, world);
        this.moveController = new QuartzMoveController(this);
    }

    //TODO: Shoot fire
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

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(70.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.4D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(TARGETING, false);
    }

    public boolean isTargeting() {
        return dataManager.get(TARGETING);
    }

    public void setTargeting(boolean flag) {
        dataManager.set(TARGETING, flag);
    }

    @Override
    public boolean handleFallDamage(float amount, float multiplier) {
        return false;
    }

    @Override
    protected void updateFallState(double amount, boolean onGround, BlockState state, BlockPos pos) {
    }

    //[VanillaCopy] FlyingEntity.travel so we can have a flying non-FlyingEntity
    @Override
    public void travel(Vec3d motion) {
        if (this.isInWater()) {
            this.moveRelative(0.02F, motion);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale((double)0.8F));
        } else if (this.isInLava()) {
            this.moveRelative(0.02F, motion);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.5D));
        } else {
            BlockPos ground = new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ());
            float f = 0.91F;
            if (this.onGround) {
                f = this.world.getBlockState(ground).getSlipperiness(this.world, ground, this) * 0.91F;
            }

            float f1 = 0.16277137F / (f * f * f);
            f = 0.91F;
            if (this.onGround) {
                f = this.world.getBlockState(ground).getSlipperiness(this.world, ground, this) * 0.91F;
            }

            this.moveRelative(this.onGround ? 0.1F * f1 : 0.02F, motion);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale((double)f));
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d1 = this.getX() - this.prevPosX;
        double d0 = this.getZ() - this.prevPosZ;
        float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
        if (f2 > 1.0F) {
            f2 = 1.0F;
        }

        this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    @Override
    protected int decreaseAirSupply(int time) {
        return time;
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    @Override
    protected void collideWithEntity(Entity target) {
        if (target instanceof IMob && this.getRNG().nextInt(20) == 0) {
            this.setAttackTarget((LivingEntity)target);
        }

        super.collideWithEntity(target);
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity target) {
        setTargeting(target != null);
        super.setAttackTarget(target);
    }

    @Override
    public boolean canAttack(EntityType<?> target) {
        return target != EntityType.PLAYER && super.canAttack(target);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    protected float getSoundPitch() {
        return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.6F;
    }

    @Override
    public void livingTick() {
        if (this.world.isRemote && isTargeting()) {
            for(int i = 0; i < 2; ++i) {
                this.world.addParticle(ParticleTypes.FLAME, this.getParticleX(0.5D), this.getRandomBodyY(), this.getParticleZ(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }

        super.livingTick();
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (item != Items.QUARTZ) {
            return false;
        } else {
            float f = this.getHealth();
            this.heal(25.0F);
            if (this.getHealth() == f) {
                return false;
            } else {
                float f1 = 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.field_226143_fP_, 1.0F, f1);
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                return true;
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
