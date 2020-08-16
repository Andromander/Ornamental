package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.RedstoneGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * RedstoneGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class RedstoneGolemModel<T extends RedstoneGolemEntity> extends AbstractGolemModel<T> {
    public ModelRenderer body1;
    public ModelRenderer legL1;
    public ModelRenderer legL2;
    public ModelRenderer legL3;
    public ModelRenderer legR1;
    public ModelRenderer legR2;
    public ModelRenderer legR3;
    public ModelRenderer body2;
    public ModelRenderer body3;

    public RedstoneGolemModel() {
        super(64, 48, false);

        this.legL2 = new ModelRenderer(this, 30, 4);
        this.legL2.setRotationPoint(4.0F, 18.0F, 0.0F);
        this.legL2.addBox(0.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(legL2, 0.0F, 0.0F, 0.5235987755982988F);
        this.legR1 = new ModelRenderer(this, 28, 16);
        this.legR1.setRotationPoint(-4.0F, 18.0F, -4.0F);
        this.legR1.addBox(0.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(legR1, 0.0F, 2.356194490192345F, -0.7853981633974483F);
        this.body3 = new ModelRenderer(this, 24, 24);
        this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body3.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
        this.legL3 = new ModelRenderer(this, 0, 16);
        this.legL3.setRotationPoint(4.0F, 18.0F, 4.0F);
        this.legL3.addBox(0.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(legL3, 0.0F, -0.7853981633974483F, 0.7853981633974483F);
        this.body1 = new ModelRenderer(this, 0, 0);
        this.body1.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.body1.addBox(-5.0F, 0.0F, -5.0F, 10, 6, 10, 0.0F);
        this.legR3 = new ModelRenderer(this, 28, 20);
        this.legR3.setRotationPoint(-4.0F, 18.0F, 4.0F);
        this.legR3.addBox(0.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(legR3, 0.0F, 3.9269908169872414F, -0.7853981633974483F);
        this.body2 = new ModelRenderer(this, 0, 24);
        this.body2.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.body2.addBox(-3.0F, -8.0F, -3.0F, 6, 8, 6, 0.0F);
        this.head = new ModelRenderer(this, 36, 24);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.5F, -5.0F, -2.5F, 5, 5, 5, 0.0F);
        this.legL1 = new ModelRenderer(this, 30, 0);
        this.legL1.setRotationPoint(4.0F, 18.0F, -4.0F);
        this.legL1.addBox(0.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(legL1, 0.0F, 0.7853981633974483F, 0.7853981633974483F);
        this.legR2 = new ModelRenderer(this, 0, 20);
        this.legR2.setRotationPoint(-4.0F, 18.0F, 0.0F);
        this.legR2.addBox(0.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(legR2, 0.0F, 3.141592653589793F, -0.5235987755982988F);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);

        this.legL1.rotateAngleZ = 0.7853981633974483F;
        this.legR1.rotateAngleZ = -0.7853981633974483F;
        this.legL2.rotateAngleZ = 0.5235987755982988F;
        this.legR2.rotateAngleZ = -0.5235987755982988F;
        this.legL3.rotateAngleZ = 0.7853981633974483F;
        this.legR3.rotateAngleZ = -0.7853981633974483F;

        this.legL1.rotateAngleY = 0.7853981633974483F;
        this.legR1.rotateAngleY = 2.356194490192345F;
        this.legL2.rotateAngleY = 0.0F;
        this.legR2.rotateAngleY = 3.141592653589793F;
        this.legL3.rotateAngleY = -0.7853981633974483F;
        this.legR3.rotateAngleY = 3.9269908169872414F;

        float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;

        this.legL1.rotateAngleY -= f3;
        this.legR1.rotateAngleY -= -f3;
        this.legL2.rotateAngleY -= f4;
        this.legR2.rotateAngleY -= -f4;
        this.legL3.rotateAngleY -= f5;
        this.legR3.rotateAngleY -= -f5;

        this.legL1.rotateAngleZ -= f7;
        this.legR1.rotateAngleZ -= -f7;
        this.legL2.rotateAngleZ -= f8;
        this.legR2.rotateAngleZ -= -f8;
        this.legL3.rotateAngleZ -= f9;
        this.legR3.rotateAngleZ -= -f9;
    }

    @Override
    public void setLivingAnimations(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {

    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                legL2,
                legR1,
                body3,
                legL3,
                body1,
                legR3,
                body2,
                head,
                legL1,
                legR2
        );
    }
}
