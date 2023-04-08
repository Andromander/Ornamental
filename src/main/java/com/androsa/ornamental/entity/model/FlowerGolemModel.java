package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.FlowerGolem;
import net.minecraft.client.model.geom.ModelPart;

public abstract class FlowerGolemModel<T extends FlowerGolem> extends AbstractGolemModel<T> {

    public FlowerGolemModel(ModelPart root) {
        super(root, true, true, true, true);
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        int attack = entity.getAttackTimer();
        if (attack > 0) {
            this.armR.xRot = -2.0F + 1.5F * this.triangleWave((float)attack - partialTicks, 10.0F);
            this.armL.xRot = -2.0F + 1.5F * this.triangleWave((float)attack - partialTicks, 10.0F);
        } else {
            int hold = entity.getHoldFlowerTick();
            if (hold > 0) {
                this.armR.xRot = -0.8F + 0.025F * this.triangleWave((float)hold, 70.0F);
                this.armL.xRot = 0.0F;
            } else {
                swingArms(limbSwing, limbSwingAmount);
            }
        }
    }

    public ModelPart getArmHoldingFlower() {
        return armR;
    }
}
