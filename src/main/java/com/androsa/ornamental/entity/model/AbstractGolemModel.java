package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.AbstractGolemEntity;
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
        this.texWidth = width;
        this.texHeight = height;
        this.useTimer = useTimer;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.legL.xRot = -1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.legR.xRot = 1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.legL.yRot = 0.0F;
        this.legR.yRot = 0.0F;
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        int attack = entity.getAttackTimer();
        if (attack > 0 && useTimer) {
            this.armR.xRot = -2.0F + 1.5F * this.triangleWave((float)attack - partialTicks, 10.0F);
            this.armL.xRot = -2.0F + 1.5F * this.triangleWave((float)attack - partialTicks, 10.0F);
        } else {
            swingArms(limbSwing, limbSwingAmount);
        }
    }

    protected void swingArms(float limbSwing, float limbSwingAmount) {
        this.armR.xRot = (-0.2F + 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        this.armL.xRot = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
    }

    protected float triangleWave(float limbSwing, float amount) {
        return (Math.abs(limbSwing % amount - amount * 0.5F) - amount * 0.25F) / (amount * 0.25F);
    }
}
