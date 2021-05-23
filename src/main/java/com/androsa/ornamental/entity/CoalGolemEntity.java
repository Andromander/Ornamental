package com.androsa.ornamental.entity;

import com.androsa.ornamental.entity.task.FirePanicGoal;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class CoalGolemEntity extends AbstractGolemEntity {

    private int explosionTimer = 20 * 5;

    public CoalGolemEntity(EntityType<? extends CoalGolemEntity> entity, World world) {
        super(entity, world);
        this.maxUpStep = 1.0F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FirePanicGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (target) ->
                target instanceof IMob && !(target instanceof CreeperEntity)));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2D);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (isOnFire()) {
            explosionTimer--;
        } else {
            explosionTimer = 20 * 5;
        }

        if (explosionTimer <= 0) {
            this.explode();
        }

        if (!ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
            return;
        }

        BlockState blockstate = Blocks.FIRE.defaultBlockState();

        if (this.isOnFire()) {
            for(int l = 0; l < 4; ++l) {
                int x = MathHelper.floor(this.getX() + (double)((float)(l % 2 * 2 - 1) * 0.25F));
                int y = MathHelper.floor(this.getY());
                int z = MathHelper.floor(this.getZ() + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockpos = new BlockPos(x, y, z);
                if (this.level.isEmptyBlock(blockpos) && blockstate.canSurvive(this.level, blockpos)) {
                    this.level.setBlockAndUpdate(blockpos, blockstate);
                }
            }
        }
    }

    private void explode() {
        if (!this.level.isClientSide) {
            Explosion.Mode mode = ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            this.dead = true;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, mode);
            this.remove();
        }
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

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SNOW_GOLEM_DEATH;
    }

    @Override
    protected float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 1.2F + 0.6F;
    }

    //processInteract
    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (item != Items.COAL) {
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
        return 1.9F;
    }
}
