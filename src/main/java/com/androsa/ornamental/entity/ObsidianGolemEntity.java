package com.androsa.ornamental.entity;

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
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ObsidianGolemEntity extends AbstractGolemEntity {

    public ObsidianGolemEntity(EntityType<? extends ObsidianGolemEntity> entity, World world) {
        super(entity, world);
        this.stepHeight = 1.0F;
    }

    //TODO: AoE attack
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.4D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1.0D, 32.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (target) ->
                target instanceof IMob));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 200.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 18.0D);
    }

    @Override
    public void applyKnockback(float strength, double x, double z) {
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float multiplier) {
        float modifier = source.isExplosion() ? 0.3F : multiplier;
        return super.attackEntityFrom(source, modifier);
    }

    @Override
    protected int decreaseAirSupply(int time) {
        return time;
    }

    @Override
    protected void collideWithEntity(Entity target) {
        if (target instanceof IMob && this.getRNG().nextInt(20) == 0) {
            this.setAttackTarget((LivingEntity)target);
        }

        super.collideWithEntity(target);
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
    public void livingTick() {
        super.livingTick();

        if (horizontalMag(this.getMotion()) > (double)2.5000003E-7F && this.rand.nextInt(5) == 0) {
            int x = MathHelper.floor(this.getPosX());
            int y = MathHelper.floor(this.getPosY() - (double)0.2F);
            int z = MathHelper.floor(this.getPosZ());
            BlockPos pos = new BlockPos(x, y, z);
            BlockState blockstate = this.world.getBlockState(pos);
            if (!blockstate.isAir(this.world, pos)) {
                this.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate).setPos(pos),
                        this.getPosX() + ((double)this.rand.nextFloat() - 0.5D) * (double)this.getWidth(),
                        this.getPosY() + 0.1D,
                        this.getPosZ() + ((double)this.rand.nextFloat() - 0.5D) * (double)this.getWidth(),
                        4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D);
            }
        }
    }

    //processInteract
    @Override
    protected ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (item != Items.OBSIDIAN) {
            return ActionResultType.PASS;
        } else {
            float f = this.getHealth();
            this.heal(25.0F);
            if (this.getHealth() == f) {
                return ActionResultType.PASS;
            } else {
                float f1 = 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.ENTITY_IRON_GOLEM_REPAIR, 1.0F, f1);
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                return ActionResultType.func_233537_a_(this.world.isRemote);
            }
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 2.7F;
    }
}
