package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.QuartzGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * QuartzGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class QuartzGolemModel<T extends QuartzGolemEntity> extends AbstractGolemModel<T> {
    public ModelRenderer tail1;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer eye;

    public QuartzGolemModel() {
        super(64, 48, false);

        this.body = new ModelRenderer(this, 14, 30);
        this.body.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.body.addBox(-6.0F, 0.0F, -2.5F, 12, 4, 5, 0.0F);
        this.tail1 = new ModelRenderer(this, 43, 24);
        this.tail1.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.tail1.addBox(-3.4F, 0.0F, -1.5F, 7, 6, 3, 0.0F);
        this.setRotateAngle(tail1, 0.3490658503988659F, 0.0F, 0.0F);
        this.armL = new ModelRenderer(this, 46, 0);
        this.armL.setRotationPoint(8.0F, -3.5F, 0.0F);
        this.armL.addBox(0.0F, 0.0F, -2.0F, 3, 20, 4, 0.0F);
        this.torso = new ModelRenderer(this, 0, 0);
        this.torso.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.torso.addBox(-8.0F, 0.0F, -3.5F, 16, 9, 7, 0.0F);
        this.head = new ModelRenderer(this, 14, 16);
        this.head.setRotationPoint(0.0F, -4.0F, -1.5F);
        this.head.addBox(-3.0F, -8.0F, -3.5F, 6, 8, 6, 0.0F);
        this.tail3 = new ModelRenderer(this, 12, 39);
        this.tail3.setRotationPoint(0.0F, 6.5F, 0.0F);
        this.tail3.addBox(-1.5F, 0.0F, -1.0F, 3, 5, 2, 0.0F);
        this.setRotateAngle(tail3, 0.3490658503988659F, 0.0F, 0.0F);
        this.eye = new ModelRenderer(this, 32, 16);
        this.eye.setRotationPoint(0.0F, -4.0F, -5.0F);
        this.eye.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2, 0.0F);
        this.armR = new ModelRenderer(this, 0, 16);
        this.armR.setRotationPoint(-8.0F, -3.5F, 0.0F);
        this.armR.addBox(-3.0F, 0.0F, -2.0F, 3, 20, 4, 0.0F);
        this.tail2 = new ModelRenderer(this, 48, 33);
        this.tail2.setRotationPoint(0.0F, 5.5F, 0.0F);
        this.tail2.addBox(-2.0F, 0.0F, -1.0F, 4, 7, 2, 0.0F);
        this.setRotateAngle(tail2, 0.3490658503988659F, 0.0F, 0.0F);

        this.torso.addChild(this.body);
        this.body.addChild(this.tail1);
        this.tail2.addChild(this.tail3);
        this.head.addChild(this.eye);
        this.tail1.addChild(this.tail2);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.tail1.rotateAngleX = MathHelper.sin(ageInTicks * 0.067F) * 0.1F + 0.3490658503988659F;
        this.tail2.rotateAngleX = MathHelper.sin(ageInTicks * 0.067F) * 0.1F + 0.3490658503988659F;
        this.tail3.rotateAngleX = MathHelper.sin(ageInTicks * 0.067F) * 0.1F + 0.3490658503988659F;
    }

    @Override
    public void setLivingAnimations(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        //TODO: Wave arms gently because we are flying. Don't swing
        super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                head,
                torso,
                armL,
                armR
        );
    }
}
