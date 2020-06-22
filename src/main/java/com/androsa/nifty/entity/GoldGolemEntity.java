package com.androsa.nifty.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GoldGolemEntity extends FlowerGolemEntity {

    public GoldGolemEntity(EntityType<? extends GoldGolemEntity> entity, World world) {
        super(entity, world);
        this.stepHeight = 1.5F;
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

    @Override
    protected void registerAttributes() {
        //Frail, Fast, Limber, Weak
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
    }

    @Override
    protected int decreaseAirSupply(int time) {
        return time;
    }

    @Override
    protected void collideWithEntity(Entity target) {
        if (target instanceof IMob && !(target instanceof CreeperEntity) && this.getRNG().nextInt(20) == 0) {
            this.setAttackTarget((LivingEntity)target);
        }

        super.collideWithEntity(target);
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (horizontalMag(this.getMotion()) > (double)2.5000003E-7F && this.rand.nextInt(5) == 0) {
            int x = MathHelper.floor(this.getX());
            int y = MathHelper.floor(this.getY() - (double)0.2F);
            int z = MathHelper.floor(this.getZ());
            BlockPos pos = new BlockPos(x, y, z);
            BlockState blockstate = this.world.getBlockState(pos);
            if (!blockstate.isAir(this.world, pos)) {
                this.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate).setPos(pos),
                        this.getX() + ((double)this.rand.nextFloat() - 0.5D) * (double)this.getWidth(),
                        this.getY() + 0.1D,
                        this.getZ() + ((double)this.rand.nextFloat() - 0.5D) * (double)this.getWidth(),
                        4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D);
            }
        }
    }

    @Override
    public boolean canAttack(EntityType<?> target) {
        return target != EntityType.CREEPER && target != EntityType.PLAYER && super.canAttack(target);
    }

    private float getAttackDamage() {
        return (float)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
    }

    public boolean attackEntityAsMob(Entity target) {
        this.attackTimer = 10;
        this.world.setEntityState(this, (byte)4);
        float damage = this.getAttackDamage();
        float multiplier = damage > 0.0F ? damage / 2.0F + (float)this.rand.nextInt((int)damage) : 0.0F;
        boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), multiplier);
        if (flag) {
            target.setMotion(target.getMotion().add(0.0D, (double)0.5F, 0.0D));
            this.applyEnchantments(this, target);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
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
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (item != Items.GOLD_INGOT) {
            return false;
        } else {
            float health = this.getHealth();
            this.heal(25.0F);
            if (this.getHealth() == health) {
                return false;
            } else {
                float pitch = 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.field_226143_fP_, 1.0F, pitch);
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                return true;
            }
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 2.6F;
    }

    @Override
    public BlockState getFlower() {
        return Blocks.DANDELION.getDefaultState();
    }

    @Override
    public double[] getFlowerPos() {
        return new double[]{-1.2, 1.1F, -1.0F};
    }
}
