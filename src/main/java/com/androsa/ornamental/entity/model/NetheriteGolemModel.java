package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.NetheriteGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * NetheriteGolemModel - Androsa
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class NetheriteGolemModel<T extends NetheriteGolem> extends AbstractGolemModel<T> {
    public ModelPart legB;

    public NetheriteGolemModel(ModelPart root) {
        super(root, true, true, true);

        this.legB = root.getChild("leg_back");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition headdef = part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -4.0F, -5.0F, 7.0F, 5.0F, 5.0F),
                PartPose.offset(0.0F, -17.0F, -5.0F));
        headdef.addOrReplaceChild("forehead", CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(-3.5F, -4.0F, 0.0F, 7.0F, 4.0F, 8.0F),
                PartPose.offset(0.0F, -4.0F, -5.0F));

        part.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(46, 0)
                        .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, -17.5F, -6.0F, 0.7819074915776542F, 0.0F, 0.0F));
        part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(54, 0)
                        .addBox(-11.5F, 0.0F, 0.0F, 23.0F, 11.0F, 10.0F),
                PartPose.offset(0.0F, -15.0F, -5.0F));

        PartDefinition leftarmdef = part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(0, 10)
                        .addBox(0.0F, -1.0F, -2.5F, 5.0F, 14.0F, 5.0F),
                PartPose.offsetAndRotation(10.0F, -12.0F, 1.0F, -0.2708751078990032F, 0.0F, -0.45448375319828993F));
        PartDefinition leftforearmdef = leftarmdef.addOrReplaceChild("forearm_left", CubeListBuilder.create()
                        .texOffs(20, 12)
                        .addBox(0.0F, 0.0F, -3.5F, 7.0F, 14.0F, 7.0F),
                PartPose.offsetAndRotation(0.5F, 10.0F, 0.5F, -0.5235987755982988F, -0.2792526803190927F, 0.47123889803846897F));
        leftforearmdef.addOrReplaceChild("left_finger_left", CubeListBuilder.create()
                        .texOffs(48, 21)
                        .addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(6.0F, 13.0F, -4.0F, 0.2617993877991494F, 0.0F, 0.0F));
        leftforearmdef.addOrReplaceChild("right_finger_left", CubeListBuilder.create()
                        .texOffs(110, 0)
                        .addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(1.0F, 13.0F, -4.0F, 0.2617993877991494F, 0.0F, 0.0F));
        leftforearmdef.addOrReplaceChild("thumb_left", CubeListBuilder.create()
                        .texOffs(117, 18)
                        .addBox(-2.0F, 0.0F, -1.5F, 2.0F, 6.0F, 3.0F),
                PartPose.offset(0.0F, 13.0F, 2.0F));

        PartDefinition rightarmdef = part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(58, 21)
                        .addBox(-5.0F, -1.0F, -2.5F, 5.0F, 14.0F, 5.0F),
                PartPose.offsetAndRotation(-10.0F, -12.0F, 1.0F, -0.2708751078990032F, 0.0F, 0.45448375319828993F));
        PartDefinition rightforearmdef = rightarmdef.addOrReplaceChild("forearm_right", CubeListBuilder.create()
                        .texOffs(78, 21)
                        .addBox(-7.0F, 0.0F, -3.5F, 7.0F, 14.0F, 7.0F),
                PartPose.offsetAndRotation(-0.5F, 10.0F, 0.5F, -0.5235987755982988F, 0.2792526803190927F, -0.47123889803846897F));
        rightforearmdef.addOrReplaceChild("left_finger_right", CubeListBuilder.create()
                        .texOffs(114, 27)
                        .addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(-1.0F, 13.0F, -4.0F, 0.2617993877991494F, 0.0F, 0.0F));
        rightforearmdef.addOrReplaceChild("right_finger_right", CubeListBuilder.create()
                        .texOffs(106, 21)
                        .addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(-6.0F, 13.0F, -4.0F, 0.2617993877991494F, 0.0F, 0.0F));
        rightforearmdef.addOrReplaceChild("thumb_right", CubeListBuilder.create()
                        .texOffs(0, 29)
                        .addBox(0.0F, 0.0F, -1.5F, 2.0F, 6.0F, 3.0F),
                PartPose.offset(0.0F, 13.0F, 2.0F));

        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(2, 33)
                        .addBox(-7.0F, 0.0F, -4.0F, 14.0F, 10.0F, 8.0F),
                PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition leftlegdef = part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(70, 42)
                        .addBox(-3.0F, 0.0F, -12.0F, 6.0F, 5.0F, 12.0F),
                PartPose.offsetAndRotation(5.0F, 4.0F, -2.5F, 0.2617993877991494F, -0.40142572795869574F, 0.0F));
        leftlegdef.addOrReplaceChild("foreleg_left", CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 4.0F, -10.0F, -0.2617993877991494F, 0.0F, 0.0F));

        PartDefinition rightlegdef = part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(34, 39)
                        .addBox(-3.0F, 0.0F, -12.0F, 6.0F, 5.0F, 12.0F),
                PartPose.offsetAndRotation(-5.0F, 4.0F, -2.5F, 0.2617993877991494F, 0.40142572795869574F, 0.0F));
        rightlegdef.addOrReplaceChild("foreleg_right", CubeListBuilder.create()
                        .texOffs(106, 35)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 4.0F, -10.0F, -0.2617993877991494F, 0.0F, 0.0F));

        PartDefinition backlegdef = part.addOrReplaceChild("leg_back", CubeListBuilder.create()
                        .texOffs(16, 56)
                        .addBox(-3.0F, 0.0F, 0.0F, 6.0F, 5.0F, 11.0F),
                PartPose.offsetAndRotation(0.0F, 4.0F, 3.0F, -0.2617993877991494F, 0.0F, 0.0F));
        backlegdef.addOrReplaceChild("foreleg_back", CubeListBuilder.create()
                        .texOffs(106, 53)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 4.0F, 9.0F, 0.2617993877991494F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 80);
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

        float f3 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;

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
