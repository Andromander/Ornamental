package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.QuartzGolem;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

//VanillaCopy of VexEntity.MoveHelperController, modified for QuartzGolemEntity
public class QuartzMoveController extends MoveControl {
    private final QuartzGolem parentEntity;

    public QuartzMoveController(QuartzGolem golem) {
        super(golem);
        this.parentEntity = golem;
    }

    public void tick() {
        if (this.operation == MoveControl.Operation.MOVE_TO) {
            Vec3 vec3d = new Vec3(this.wantedX - parentEntity.getX(), this.wantedY - parentEntity.getY(), this.wantedZ - parentEntity.getZ());
            double d0 = vec3d.length();
            if (d0 < parentEntity.getBoundingBox().getSize()) {
                this.operation = MoveControl.Operation.WAIT;
                parentEntity.setDeltaMovement(parentEntity.getDeltaMovement().scale(0.5D));
            } else {
                parentEntity.setDeltaMovement(parentEntity.getDeltaMovement().add(vec3d.scale(this.speedModifier * 0.05D / d0)));
                if (parentEntity.getTarget() == null) {
                    Vec3 vec3d1 = parentEntity.getDeltaMovement();
                    parentEntity.setYRot(-((float) Mth.atan2(vec3d1.x, vec3d1.z)) * (180F / (float)Math.PI));
                } else {
                    double d2 = parentEntity.getTarget().getX() - parentEntity.getX();
                    double d1 = parentEntity.getTarget().getZ() - parentEntity.getZ();
                    parentEntity.setYRot(-((float)Mth.atan2(d2, d1)) * (180F / (float)Math.PI));
                }
                parentEntity.yBodyRot = parentEntity.getYRot();
            }

        }
    }
}
