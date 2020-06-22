package com.androsa.nifty.entity;

import com.androsa.nifty.ModEntities;
import com.androsa.nifty.entity.projectile.ThrownNetherBrickEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class NetherBrickGolemEntity extends AbstractGolemEntity implements IRangedAttackMob {

    public NetherBrickGolemEntity(EntityType<? extends NetherBrickGolemEntity> entity, World world) {
        super(entity, world);
        this.stepHeight = 1.0F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.5D, 30, 20.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (target) ->
                target instanceof IMob));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
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
        return SoundEvents.BLOCK_STONE_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_STONE_BREAK;
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (item != Items.NETHER_BRICK) {
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
        this.playSound(SoundEvents.BLOCK_STONE_STEP, 1.0F, 1.0F);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity entity, float multiplier) {
        ThrownNetherBrickEntity brickentity = new ThrownNetherBrickEntity(ModEntities.THROWN_NETHER_BRICK.get(), this.world, this);
        double eye = entity.getEyeY() - (double)1.1F;
        double x = entity.getX() - this.getX();
        double y = eye - brickentity.getY();
        double z = entity.getZ() - this.getZ();
        float f = MathHelper.sqrt(x * x + z * z) * 0.2F;

        brickentity.shoot(x, y + (double)f, z, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(brickentity);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float multiplier) {
        float modifier = source.isMagicDamage() ? 0.5F : multiplier;
        return super.attackEntityFrom(source, modifier);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 1.7F;
    }
}
