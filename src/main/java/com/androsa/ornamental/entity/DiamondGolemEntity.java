package com.androsa.ornamental.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DiamondGolemEntity extends FlowerGolemEntity {

    public DiamondGolemEntity(EntityType<? extends DiamondGolemEntity> entity, World world) {
        super(entity, world);
        this.maxUpStep = 0.5F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (target) ->
                target instanceof IMob && !(target instanceof CreeperEntity)));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.45D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 20.0D);
    }

    @Override
    protected int decreaseAirSupply(int time) {
        return time;
    }

    @Override
    protected void doPush(Entity target) {
        if (target instanceof IMob && !(target instanceof CreeperEntity) && this.getRandom().nextInt(20) == 0) {
            this.setTarget((LivingEntity)target);
        }

        super.doPush(target);
    }

    @Override
    public boolean canAttackType(EntityType<?> target) {
        return target != EntityType.CREEPER && target != EntityType.PLAYER && super.canAttackType(target);
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        this.attackTimer = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        float damage = this.getAttackDamage();
        float mul = damage > 0.0F ? damage / 2.0F + (float)this.random.nextInt((int)damage) : 0.0F;
        boolean flag = target.hurt(DamageSource.mobAttack(this), mul);
        if (flag) {
            target.setDeltaMovement(target.getDeltaMovement().add(0.0D, (double)0.3F, 0.0D));
            this.doEnchantDamageEffects(this, target);
        }

        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
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

    //processInteract
    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (item != Items.DIAMOND) {
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
    public float getEyeHeight(Pose pose) {
        return 1.1F;
    }

    @Override
    public BlockState getFlower() {
        return Blocks.BLUE_ORCHID.defaultBlockState();
    }

    @Override
    public double[] getFlowerPos() {
        return new double[]{-0.60D, 0.2D, -0.75D};
    }
}
