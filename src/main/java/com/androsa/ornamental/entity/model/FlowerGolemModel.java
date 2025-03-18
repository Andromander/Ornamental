package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.model.renderstate.OfferingGolemRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public abstract class FlowerGolemModel extends AbstractGolemModel<OfferingGolemRenderState> {

    public FlowerGolemModel(ModelPart root) {
        super(root, true, true, true, true);
    }

    @Override
    protected void swingArms(OfferingGolemRenderState entity, float limbSwing, float limbSwingAmount) {
        int hold = entity.offerFlowerTick;
        if (hold > 0) {
            this.armR.xRot = -0.8F + 0.025F * Mth.triangleWave((float)hold, 70.0F);
            this.armL.xRot = 0.0F;
        } else {
            super.swingArms(entity, limbSwing, limbSwingAmount);
        }
    }

    public ModelPart getArmHoldingFlower() {
        return armR;
    }
}
