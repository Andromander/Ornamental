package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.QuartzGolem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;

//VanillaCopy of GhastEntity.FireballAttackGoal, modified for Quartz Golem
public class FireballAttackGoal extends Goal {
    private final QuartzGolem parentEntity;
    public int attackTimer;

    public FireballAttackGoal(QuartzGolem entity) {
        this.parentEntity = entity;
    }

    @Override
    public boolean canUse() {
        return this.parentEntity.getTarget() != null;
    }

    @Override
    public void start() {
        this.attackTimer = 0;
    }

    @Override
    public void tick() {
        LivingEntity target = this.parentEntity.getTarget();
        this.parentEntity.getLookControl().setLookAt(target, 10.0F, (float)this.parentEntity.getMaxHeadXRot());
        if (target.distanceToSqr(this.parentEntity) < 4096.0D && this.parentEntity.hasLineOfSight(target)) {
            Level world = this.parentEntity.level();
            ++this.attackTimer;

            if (this.attackTimer == 20) {
                double x = target.getX() - this.parentEntity.getX();
                double y = target.getY(0.5D) - (0.5D + this.parentEntity.getY(0.5D));
                double z = target.getZ() - this.parentEntity.getZ();
                world.levelEvent(null, LevelEvent.SOUND_BLAZE_FIREBALL, parentEntity.blockPosition(), 0);
                SmallFireball fireball = new SmallFireball(world, this.parentEntity, x, y, z);
                fireball.setPos(this.parentEntity.getX(), this.parentEntity.getY(0.5D) + 0.5D, fireball.getZ());
                world.addFreshEntity(fireball);
                this.attackTimer = -40;
            }
        } else if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }
}
