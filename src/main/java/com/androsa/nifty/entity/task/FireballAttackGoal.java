package com.androsa.nifty.entity.task;

import com.androsa.nifty.entity.QuartzGolemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.math.BlockPos;
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
    public boolean shouldExecute() {
        return this.parentEntity.getAttackTarget() != null;
    }

    @Override
    public void startExecuting() {
        this.attackTimer = 0;
    }

    @Override
    public void tick() {
        LivingEntity target = this.parentEntity.getAttackTarget();
        this.parentEntity.getLookController().setLookPositionWithEntity(target, 10.0F, (float)this.parentEntity.getVerticalFaceSpeed());
        if (target.getDistanceSq(this.parentEntity) < 4096.0D && this.parentEntity.canEntityBeSeen(target)) {
            World world = this.parentEntity.world;
            ++this.attackTimer;

            if (this.attackTimer == 20) {
                double x = target.getX() - this.parentEntity.getX();
                double y = target.getBodyY(0.5D) - (0.5D + this.parentEntity.getBodyY(0.5D));
                double z = target.getZ() - this.parentEntity.getZ();
                world.playEvent(null, Constants.WorldEvents.BLAZE_SHOOT_SOUND, new BlockPos(this.parentEntity), 0);
                SmallFireballEntity fireball = new SmallFireballEntity(world, this.parentEntity, x, y, z);
                fireball.setPosition(this.parentEntity.getX(), this.parentEntity.getBodyY(0.5D) + 0.5D, fireball.getZ());
                world.addEntity(fireball);
                this.attackTimer = -40;
            }
        } else if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }
}
