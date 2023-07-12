package com.androsa.ornamental.entity;

import com.androsa.ornamental.registry.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.*;

public class AmethystGolem extends OrnamentalGolem {

    private static final EntityDataAccessor<Boolean> CASTING = SynchedEntityData.defineId(AmethystGolem.class, EntityDataSerializers.BOOLEAN);

    public AmethystGolem(EntityType<? extends AmethystGolem> entity, Level level) {
        super(entity, level);
        this.setMaxUpStep(1.2F);
        this.targetCreeper = false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new AmethystCastGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (target) ->
                target instanceof Enemy));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CASTING, false);
    }

    public boolean isCasting() {
        return this.entityData.get(CASTING);
    }

    public void setCasting(boolean flag) {
        this.entityData.set(CASTING, flag);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Casting", isCasting());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(CASTING, tag.getBoolean("Casting"));
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.AMETHYST_BLOCK_HIT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.AMETHYST_BLOCK_BREAK;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, 1.0F);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (isCasting()) {
            Vec3 look = this.getLookAngle();

            double dist = 1.0F;
            double posX = this.getX() + look.x * dist;
            double posY = this.getY() + 2.2D + look.y * dist;
            double posZ = this.getZ() + look.z * dist;

            for (int i = 0; i < 2; i++) {
                double velX = look.x;
                double velY = look.y;
                double velZ = look.z;
                double spread = 5.0D + this.getRandom().nextDouble() * 2.5D;
                double velocity = 0.15D + this.getRandom().nextDouble() * 0.15D;

                velX += this.getRandom().nextGaussian() * 0.03D * spread;
                velY += this.getRandom().nextGaussian() * 0.03D * spread;
                velZ += this.getRandom().nextGaussian() * 0.03D * spread;
                velX *= velocity;
                velY *= velocity;
                velZ *= velocity;

                level().addParticle(ModParticles.AMETHYST_CAST.get(), posX, posY, posZ, velX, velY, velZ);
            }

            this.playSound(SoundEvents.AMETHYST_CLUSTER_BREAK, 1.0F, 1.0F);
        }
    }

    public void castAttack(Entity target) {
        target.hurt(this.damageSources().magic(), 4.0F);
    }

    @Override
    protected boolean canRepair(ItemStack stack) {
        return stack.is(Items.AMETHYST_SHARD);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 2.0F;
    }

    public static class AmethystCastGoal extends Goal {

        private final AmethystGolem caster;
        private LivingEntity target;
        private final float range = 6.0F;
        private float time;
        private final int duration = 100;

        private double castX;
        private double castY;
        private double castZ;

        public AmethystCastGoal(AmethystGolem caster) {
            this.caster = caster;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        @Override
        public boolean canUse() {
            this.target = this.caster.getTarget();

            if (this.target == null
                    || this.caster.distanceTo(target) > range
                    || !this.caster.getSensing().hasLineOfSight(target)
                    || !EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.ENTITY_STILL_ALIVE).test(target)) {
                return false;
            } else {
                castX = target.getX();
                castY = target.getY() + target.getEyeHeight();
                castZ = target.getZ();

                return this.caster.getRandom().nextFloat() < 0.4F;
            }
        }

        @Override
        public void start() {
            this.time = this.duration;
            this.caster.setCasting(true);
        }

        @Override
        public boolean canContinueToUse() {
            return this.time > 0
                    && this.caster.isAlive()
                    && this.target.isAlive()
                    && this.caster.distanceTo(target) <= this.range
                    && this.caster.getSensing().hasLineOfSight(target)
                    && EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE).test(target);
        }

        @Override
        public void tick() {
            this.time--;

            //one controls where to look, the other controls facing
            this.caster.getLookControl().setLookAt(castX, castY, castZ, 100.0F, 100.0F);
            this.faceVec(castX, castY, castZ, 100.0F, 100.0F);

            if ((this.duration - this.time) > 5) {
                Entity target = this.getTarget();

                if (target != null) {
                    this.caster.castAttack(target);
                }
            }
        }

        @Override
        public void stop() {
            this.time = 0;
            this.target = null;
            this.caster.setCasting(false);
        }

        //Even though the Goal has a target, this is to get the target based on look
        private Entity getTarget() {
            Entity pointedEntity = null;
            double range = 30.0D;
            double offset = 5.0D;
            Vec3 srcVec = new Vec3(this.caster.getX(), this.caster.getY() + 0.25, this.caster.getZ());
            Vec3 lookVec = this.caster.getViewVector(1.0F);
            Vec3 destVec = srcVec.add(lookVec.x * range, lookVec.y * range, lookVec.z * range);
            float var9 = 3.0F;
            List<Entity> possibleList = this.caster.level().getEntities(this.caster, this.caster.getBoundingBox().move(lookVec.x * offset, lookVec.y * offset, lookVec.z * offset).inflate(var9));
            double hitDist = 0;

            for (Entity possibleEntity : possibleList) {
                if (possibleEntity.isPickable() && possibleEntity != this.caster && EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE).test(possibleEntity)) {
                    float borderSize = possibleEntity.getPickRadius();
                    AABB collisionBB = possibleEntity.getBoundingBox().inflate(borderSize, borderSize, borderSize);
                    Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);

                    if (collisionBB.contains(srcVec)) {
                        if (0.0D < hitDist || hitDist == 0.0D) {
                            pointedEntity = possibleEntity;
                            hitDist = 0.0D;
                        }
                    } else if (interceptPos.isPresent()) {
                        double possibleDist = srcVec.distanceTo(interceptPos.get());

                        if (possibleDist < hitDist || hitDist == 0.0D) {
                            pointedEntity = possibleEntity;
                            hitDist = possibleDist;
                        }
                    }
                }
            }
            return pointedEntity;

        }

        public void faceVec(double x, double y, double z, float yawConstraint, float pitchConstraint) {
            double xPos = x - caster.getX();
            double zPos = z - caster.getZ();
            double yPos = (caster.getY() + 0.25) - y;

            double distance = Mth.sqrt((float) (xPos * xPos + zPos * zPos));
            float xyAngle = (float) ((Math.atan2(zPos, xPos) * 180D) / Math.PI) - 90F;
            float zdAngle = (float) (-((Math.atan2(yPos, distance) * 180D) / Math.PI));
            caster.setXRot(-updateRotation(caster.getXRot(), zdAngle, pitchConstraint));
            caster.setYRot(updateRotation(caster.getYRot(), xyAngle, yawConstraint));

        }

        private float updateRotation(float current, float target, float maxDelta) {
            float delta = Mth.clamp(Mth.wrapDegrees(target - current), -maxDelta, maxDelta);

            return current + delta;
        }
    }
}
