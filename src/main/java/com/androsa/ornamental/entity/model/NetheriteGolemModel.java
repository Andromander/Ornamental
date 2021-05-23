package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.NetheriteGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * NetheriteGolemModel - Androsa
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class NetheriteGolemModel<T extends NetheriteGolemEntity> extends AbstractGolemModel<T> {
    public ModelRenderer neck;
    public ModelRenderer legB;
    public ModelRenderer forehead;
    public ModelRenderer forearmL;
    public ModelRenderer fingerLR;
    public ModelRenderer fingerLL;
    public ModelRenderer thumbL;
    public ModelRenderer forearmR;
    public ModelRenderer fingerRR;
    public ModelRenderer fingerRL;
    public ModelRenderer thumbR;
    public ModelRenderer forelegR;
    public ModelRenderer forelegL;
    public ModelRenderer forelegB;

    public NetheriteGolemModel() {
        super(128, 80, true);

        this.legL = new ModelRenderer(this, 70, 42);
        this.legL.setPos(5.0F, 4.0F, -2.5F);
        this.legL.addBox(-3.0F, 0.0F, -12.0F, 6.0F, 5.0F, 12.0F, 0.0F);
        this.setRotateAngle(legL, 0.2617993877991494F, -0.40142572795869574F, 0.0F);

        this.neck = new ModelRenderer(this, 46, 0);
        this.neck.setPos(0.0F, -17.5F, -6.0F);
        this.neck.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
        this.setRotateAngle(neck, 0.7819074915776542F, 0.0F, 0.0F);

        this.thumbR = new ModelRenderer(this, 0, 29);
        this.thumbR.setPos(0.0F, 13.0F, 2.0F);
        this.thumbR.addBox(0.0F, 0.0F, -1.5F, 2.0F, 6.0F, 3.0F, 0.0F);

        this.head = new ModelRenderer(this, 0, 0);
        this.head.setPos(0.0F, -17.0F, -5.0F);
        this.head.addBox(-3.5F, -4.0F, -5.0F, 7.0F, 5.0F, 5.0F);

        this.legB = new ModelRenderer(this, 16, 56);
        this.legB.setPos(0.0F, 4.0F, 3.0F);
        this.legB.addBox(-3.0F, 0.0F, 0.0F, 6.0F, 5.0F, 11.0F, 0.0F);
        this.setRotateAngle(legB, -0.2617993877991494F, 0.0F, 0.0F);

        this.armL = new ModelRenderer(this, 0, 10);
        this.armL.setPos(10.0F, -12.0F, 1.0F);
        this.armL.addBox(0.0F, -1.0F, -2.5F, 5.0F, 14.0F, 5.0F, 0.0F);
        this.setRotateAngle(armL, -0.2708751078990032F, 0.0F, -0.45448375319828993F);

        this.forehead = new ModelRenderer(this, 24, 0);
        this.forehead.setPos(0.0F, -4.0F, -5.0F);
        this.forehead.addBox(-3.5F, -4.0F, 0.0F, 7.0F, 4.0F, 8.0F);

        this.thumbL = new ModelRenderer(this, 117, 18);
        this.thumbL.setPos(0.0F, 13.0F, 2.0F);
        this.thumbL.addBox(-2.0F, 0.0F, -1.5F, 2.0F, 6.0F, 3.0F, 0.0F);

        this.forelegL = new ModelRenderer(this, 0, 51);
        this.forelegL.setPos(0.0F, 4.0F, -10.0F);
        this.forelegL.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F);
        this.setRotateAngle(forelegL, -0.2617993877991494F, 0.0F, 0.0F);

        this.forelegB = new ModelRenderer(this, 106, 53);
        this.forelegB.setPos(0.0F, 4.0F, 9.0F);
        this.forelegB.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F);
        this.setRotateAngle(forelegB, 0.2617993877991494F, 0.0F, 0.0F);

        this.armR = new ModelRenderer(this, 58, 21);
        this.armR.setPos(-10.0F, -12.0F, 1.0F);
        this.armR.addBox(-5.0F, -1.0F, -2.5F, 5.0F, 14.0F, 5.0F, 0.0F);
        this.setRotateAngle(armR, -0.2708751078990032F, 0.0F, 0.45448375319828993F);

        this.legR = new ModelRenderer(this, 34, 39);
        this.legR.setPos(-5.0F, 4.0F, -2.5F);
        this.legR.addBox(-3.0F, 0.0F, -12.0F, 6.0F, 5.0F, 12.0F, 0.0F);
        this.setRotateAngle(legR, 0.2617993877991494F, 0.40142572795869574F, 0.0F);

        this.forearmL = new ModelRenderer(this, 20, 12);
        this.forearmL.setPos(0.5F, 10.0F, 0.5F);
        this.forearmL.addBox(0.0F, 0.0F, -3.5F, 7.0F, 14.0F, 7.0F, 0.0F);
        this.setRotateAngle(forearmL, -0.5235987755982988F, -0.2792526803190927F, 0.47123889803846897F);

        this.fingerLR = new ModelRenderer(this, 110, 0);
        this.fingerLR.setPos(1.0F, 13.0F, -4.0F);
        this.fingerLR.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 2.0F, 0.0F);
        this.setRotateAngle(fingerLR, 0.2617993877991494F, 0.0F, 0.0F);

        this.fingerRL = new ModelRenderer(this, 114, 27);
        this.fingerRL.setPos(-1.0F, 13.0F, -4.0F);
        this.fingerRL.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 2.0F, 0.0F);
        this.setRotateAngle(fingerRL, 0.2617993877991494F, 0.0F, 0.0F);

        this.torso = new ModelRenderer(this, 54, 0);
        this.torso.setPos(0.0F, -15.0F, -5.0F);
        this.torso.addBox(-11.5F, 0.0F, 0.0F, 23.0F, 11.0F, 10.0F, 0.0F);

        this.body = new ModelRenderer(this, 2, 33);
        this.body.setPos(0.0F, -4.0F, 0.0F);
        this.body.addBox(-7.0F, 0.0F, -4.0F, 14.0F, 10.0F, 8.0F, 0.0F);

        this.forearmR = new ModelRenderer(this, 78, 21);
        this.forearmR.setPos(-0.5F, 10.0F, 0.5F);
        this.forearmR.addBox(-7.0F, 0.0F, -3.5F, 7.0F, 14.0F, 7.0F, 0.0F);
        this.setRotateAngle(forearmR, -0.5235987755982988F, 0.2792526803190927F, -0.47123889803846897F);

        this.fingerLL = new ModelRenderer(this, 48, 21);
        this.fingerLL.setPos(6.0F, 13.0F, -4.0F);
        this.fingerLL.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 2.0F, 0.0F);
        this.setRotateAngle(fingerLL, 0.2617993877991494F, 0.0F, 0.0F);

        this.fingerRR = new ModelRenderer(this, 106, 21);
        this.fingerRR.setPos(-6.0F, 13.0F, -4.0F);
        this.fingerRR.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 2.0F, 0.0F);
        this.setRotateAngle(fingerRR, 0.2617993877991494F, 0.0F, 0.0F);

        this.forelegR = new ModelRenderer(this, 106, 35);
        this.forelegR.setPos(0.0F, 4.0F, -10.0F);
        this.forelegR.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F);
        this.setRotateAngle(forelegR, -0.2617993877991494F, 0.0F, 0.0F);

        this.forearmR.addChild(this.thumbR);
        this.head.addChild(this.forehead);
        this.forearmL.addChild(this.thumbL);
        this.legL.addChild(this.forelegL);
        this.legB.addChild(this.forelegB);
        this.armL.addChild(this.forearmL);
        this.forearmL.addChild(this.fingerLR);
        this.forearmR.addChild(this.fingerRL);
        this.armR.addChild(this.forearmR);
        this.forearmL.addChild(this.fingerLL);
        this.forearmR.addChild(this.fingerRR);
        this.legR.addChild(this.forelegR);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(
                head,
                neck,
                torso,
                armL,
                armR,
                body,
                legL,
                legR,
                legB
        );
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);

        this.legL.zRot = 0.0F;
        this.legR.zRot = 0.0F;

        this.legL.yRot = -0.40142572795869574F;
        this.legR.yRot = 0.40142572795869574F;

        this.legB.xRot = -0.2617993877991494F;

        float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;

        this.legL.yRot -= f3;
        this.legR.yRot -= -f3;

        this.legB.xRot -= f3;

        this.legL.zRot -= f7;
        this.legR.zRot -= -f7;
    }

    @Override
    protected void swingArms(float limbSwing, float limbSwingAmount) {
        this.armR.xRot = (-0.2F + 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount + -0.2708751078990032F;
        this.armL.xRot = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount + -0.2708751078990032F;
    }
}
