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
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GoldGolemEntity extends FlowerGolemEntity {

    public GoldGolemEntity(EntityType<? extends GoldGolemEntity> entity, World world) {
        super(entity, world);
        this.maxUpStep = 1.5F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (entity) ->
                entity instanceof IMob && !(entity instanceof CreeperEntity)));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D);
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
    public void aiStep() {
        super.aiStep();

        if (getHorizontalDistanceSqr(this.getDeltaMovement()) > (double)2.5000003E-7F && this.random.nextInt(5) == 0) {
            int x = MathHelper.floor(this.getX());
            int y = MathHelper.floor(this.getY() - (double)0.2F);
            int z = MathHelper.floor(this.getZ());
            BlockPos pos = new BlockPos(x, y, z);
            BlockState blockstate = this.level.getBlockState(pos);
            if (!blockstate.isAir(this.level, pos)) {
                this.level.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate).setPos(pos),
                        this.getX() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(),
                        this.getY() + 0.1D,
                        this.getZ() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(),
                        4.0D * ((double)this.random.nextFloat() - 0.5D), 0.5D, ((double)this.random.nextFloat() - 0.5D) * 4.0D);
            }
        }
    }

    @Override
    public boolean canAttackType(EntityType<?> target) {
        return target != EntityType.CREEPER && target != EntityType.PLAYER && super.canAttackType(target);
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    public boolean doHurtTarget(Entity target) {
        this.attackTimer = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        float damage = this.getAttackDamage();
        float multiplier = damage > 0.0F ? damage / 2.0F + (float)this.random.nextInt((int)damage) : 0.0F;
        boolean flag = target.hurt(DamageSource.mobAttack(this), multiplier);
        if (flag) {
            target.setDeltaMovement(target.getDeltaMovement().add(0.0D, (double)0.5F, 0.0D));
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
        if (item != Items.GOLD_INGOT) {
            return ActionResultType.PASS;
        } else {
            float health = this.getHealth();
            this.heal(25.0F);
            if (this.getHealth() == health) {
                return ActionResultType.PASS;
            } else {
                float pitch = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, pitch);
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
        return 2.6F;
    }

    @Override
    public BlockState getFlower() {
        return Blocks.DANDELION.defaultBlockState();
    }

    @Override
    public double[] getFlowerPos() {
        return new double[]{-1.2, 1.1F, -1.0F};
    }
}
