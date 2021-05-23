package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.QuartzGolemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

//VanillaCopy of GhastEntity.FireballAttackGoal, modified for Quartz Golem
public class FireballAttackGoal extends Goal {
    private final QuartzGolemEntity parentEntity;
    public int attackTimer;

    public FireballAttackGoal(QuartzGolemEntity entity) {
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
        if (target.distanceToSqr(this.parentEntity) < 4096.0D && this.parentEntity.canSee(target)) {
            World world = this.parentEntity.level;
            ++this.attackTimer;

            if (this.attackTimer == 20) {
                double x = target.getX() - this.parentEntity.getX();
                double y = target.getY(0.5D) - (0.5D + this.parentEntity.getY(0.5D));
                double z = target.getZ() - this.parentEntity.getZ();
                world.levelEvent(null, Constants.WorldEvents.BLAZE_SHOOT_SOUND, parentEntity.blockPosition(), 0);
                SmallFireballEntity fireball = new SmallFireballEntity(world, this.parentEntity, x, y, z);
                fireball.setPos(this.parentEntity.getX(), this.parentEntity.getY(0.5D) + 0.5D, fireball.getZ());
                world.addFreshEntity(fireball);
                this.attackTimer = -40;
            }
        } else if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }
}
