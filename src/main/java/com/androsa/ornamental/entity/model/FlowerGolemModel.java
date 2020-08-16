package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.FlowerGolemEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class FlowerGolemModel<T extends FlowerGolemEntity> extends AbstractGolemModel<T> {

    public FlowerGolemModel(int width, int height) {
        super(width, height, true);
    }

    @Override
    public void setLivingAnimations(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        int attack = entity.getAttackTimer();
        if (attack > 0) {
            this.armR.rotateAngleX = -2.0F + 1.5F * this.triangleWave((float)attack - partialTicks, 10.0F);
            this.armL.rotateAngleX = -2.0F + 1.5F * this.triangleWave((float)attack - partialTicks, 10.0F);
        } else {
            int hold = entity.getHoldFlowerTick();
            if (hold > 0) {
                this.armR.rotateAngleX = -0.8F + 0.025F * this.triangleWave((float)hold, 70.0F);
                this.armL.rotateAngleX = 0.0F;
            } else {
                swingArms(limbSwing, limbSwingAmount);
            }
        }
    }

    public ModelRenderer getArmHoldingRose() {
        return armR;
    }
}
