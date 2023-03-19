package com.androsa.ornamental.entity;

import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.entity.projectile.NetherBrick;
import com.androsa.ornamental.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class NetherBrickGolem extends OrnamentalGolem implements RangedAttackMob {

    public NetherBrickGolem(EntityType<? extends NetherBrickGolem> entity, Level world) {
        super(entity, world);
        this.maxUpStep = 1.0F;
        this.targetCreeper = true;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.5D, 30, 20.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (target) ->
                target instanceof Enemy));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.STONE_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.STONE_BREAK;
    }

    @Override
    protected boolean canRepair(ItemStack stack) {
        return stack.is(Items.NETHER_BRICK);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.STONE_STEP, 1.0F, 1.0F);
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float multiplier) {
        NetherBrick brick = new NetherBrick(ModEntities.THROWN_NETHER_BRICK.get(), this.level, this);
        double eye = entity.getEyeY() - (double)1.1F;
        double x = entity.getX() - this.getX();
        double y = eye - brick.getY();
        double z = entity.getZ() - this.getZ();
        double f = Math.sqrt(x * x + z * z) * 0.2F;

        brick.shoot(x, y + f, z, 1.6F, 12.0F);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(brick);
    }

    @Override
    public boolean hurt(DamageSource source, float multiplier) {
        float modifier = source.is(ModTags.DamageTypes.NETHER_BRICK_GOLEM_RESIST) ? 0.5F : multiplier;
        return super.hurt(source, modifier);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 1.7F;
    }
}
