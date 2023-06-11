package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.CalciteGolem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class EchoAttackGoal extends Goal {

    private final CalciteGolem golem;
    private LivingEntity target;
    private int chargeTimer;
    private int explodeTime;

    public EchoAttackGoal(CalciteGolem golem) {
        this.golem = golem;

        this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return this.golem.getTarget() instanceof Warden;
    }

    @Override
    public boolean canContinueToUse() {
        return this.chargeTimer >= 0 && this.explodeTime <= 30;
    }

    @Override
    public void start() {
        this.golem.getNavigation().stop();
        this.golem.setChargeState(1);
        this.target = this.golem.getTarget();
        this.chargeTimer = 30;
        this.explodeTime = 0;
    }

    @Override
    public void stop() {
        this.golem.setChargeState(0);
    }

    @Override
    public void tick() {
        //I stole this from my own mod
        chargeTimer--;
        if (chargeTimer <= 0) {
            this.golem.setChargeState(2);

            if (explodeTime == 0) {
                this.target.playSound(SoundEvents.WARDEN_SONIC_BOOM, 1.0F, 1.5F);
                Vec3 explosion = new Vec3(target.getX(), target.getY(), target.getZ());
                Vec3 direction = target.position().subtract(explosion).normalize();
                this.target.hurt(golem.level().damageSources().magic(), 100.0F);
                this.target.setDeltaMovement(direction.x(), direction.y() + 0.2F, direction.z());
            }

            if (explodeTime < 20) {
                if (!golem.level().isClientSide()) {
                    for (int i = 0; i < 10; i++) {
                        ((ServerLevel)golem.level()).sendParticles(ParticleTypes.ELECTRIC_SPARK, target.getRandomX(1.0D), target.getRandomY(), target.getRandomZ(1.0D), 5, (target.getRandom().nextDouble()) - 0.5D, target.getRandom().nextDouble() * 0.5D, (target.getRandom().nextDouble()) - 0.5D, 0.5D);
                    }
                }
            }

            explodeTime++;
        }
    }
}
