package com.androsa.nifty.entity.model;

import com.androsa.nifty.entity.AbstractGolemEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Abstract class holding basic parts: Head, Torso, Body, 2 Arms, and 2 Legs. These are only fields
 * Not all fields are required. Special cases can ignore certain fields
 */
@OnlyIn(Dist.CLIENT)
public abstract class AbstractGolemModel<T extends AbstractGolemEntity> extends SegmentedModel<T> {

    public ModelRenderer legL;
    public ModelRenderer legR;
    public ModelRenderer body;
    public ModelRenderer torso;
    public ModelRenderer armL;
    public ModelRenderer armR;
    public ModelRenderer head;

    private boolean useTimer;

    public AbstractGolemModel(int width, int height, boolean useTimer) {
        this.textureWidth = width;
        this.textureHeight = height;
        this.useTimer = useTimer;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.legL.rotateAngleX = -1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.legR.rotateAngleX = 1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.legL.rotateAngleY = 0.0F;
        this.legR.rotateAngleY = 0.0F;
    }

    @Override
    public void setLivingAnimations(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        int attack = entity.getAttackTimer();
        if (attack > 0 && useTimer) {
            this.armR.rotateAngleX = -2.0F + 1.5F * this.triangleWave((float)attack - partialTicks, 10.0F);
            this.armL.rotateAngleX = -2.0F + 1.5F * this.triangleWave((float)attack - partialTicks, 10.0F);
        } else {
            swingArms(limbSwing, limbSwingAmount);
        }
    }

    protected void swingArms(float limbSwing, float limbSwingAmount) {
        this.armR.rotateAngleX = (-0.2F + 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        this.armL.rotateAngleX = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
    }

    protected float triangleWave(float limbSwing, float amount) {
        return (Math.abs(limbSwing % amount - amount * 0.5F) - amount * 0.25F) / (amount * 0.25F);
    }
}
