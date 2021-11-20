package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.RedstoneGolem;
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
 * RedstoneGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
public class RedstoneGolemModel<T extends RedstoneGolem> extends AbstractGolemModel<T> {
    public ModelPart legL1;
    public ModelPart legL2;
    public ModelPart legL3;
    public ModelPart legR1;
    public ModelPart legR2;
    public ModelPart legR3;

    public RedstoneGolemModel(ModelPart root) {
        super(root, false, false, false);

        this.legL1 = root.getChild("leg_left1");
        this.legL2 = root.getChild("leg_left2");
        this.legL3 = root.getChild("leg_left3");
        this.legR1 = root.getChild("leg_right1");
        this.legR2 = root.getChild("leg_right2");
        this.legR3 = root.getChild("leg_right3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(36, 24)
                        .addBox(-2.5F, -5.0F, -2.5F, 5, 5, 5),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        part.addOrReplaceChild("body1", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-5.0F, 0.0F, -5.0F, 10, 6, 10),
                PartPose.offset(0.0F, 14.0F, 0.0F));
        part.addOrReplaceChild("body2", CubeListBuilder.create()
                        .texOffs(0, 24)
                        .addBox(-3.0F, -8.0F, -3.0F, 6, 8, 6),
                PartPose.offset(0.0F, 14.0F, 0.0F));
        part.addOrReplaceChild("body3", CubeListBuilder.create()
                        .texOffs(24, 24)
                        .addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        part.addOrReplaceChild("leg_left1", CubeListBuilder.create()
                        .texOffs(30, 0)
                        .addBox(0.0F, -1.0F, -1.0F, 12, 2, 2),
                PartPose.offsetAndRotation(4.0F, 18.0F, -4.0F, 0.0F, 0.7853981633974483F, 0.7853981633974483F));
        part.addOrReplaceChild("leg_left2", CubeListBuilder.create()
                        .texOffs(30, 4)
                        .addBox(0.0F, -1.0F, -1.0F, 12, 2, 2),
                PartPose.offsetAndRotation(4.0F, 18.0F, 0.0F, 0.0F, 0.0F, 0.5235987755982988F));
        part.addOrReplaceChild("leg_left3", CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(0.0F, -1.0F, -1.0F, 12, 2, 2),
                PartPose.offsetAndRotation(4.0F, 18.0F, 4.0F, 0.0F, -0.7853981633974483F, 0.7853981633974483F));
        part.addOrReplaceChild("leg_right1", CubeListBuilder.create()
                        .texOffs(28, 16)
                        .addBox(0.0F, -1.0F, -1.0F, 12, 2, 2),
                PartPose.offsetAndRotation(-4.0F, 18.0F, -4.0F, 0.0F, 2.356194490192345F, -0.7853981633974483F));
        part.addOrReplaceChild("leg_right2", CubeListBuilder.create()
                        .texOffs(0, 20)
                        .addBox(0.0F, -1.0F, -1.0F, 12, 2, 2),
                PartPose.offsetAndRotation(-4.0F, 18.0F, 0.0F, 0.0F, 3.141592653589793F, -0.5235987755982988F));
        part.addOrReplaceChild("leg_right3", CubeListBuilder.create()
                        .texOffs(28, 20)
                        .addBox(0.0F, -1.0F, -1.0F, 12, 2, 2),
                PartPose.offsetAndRotation(-4.0F, 18.0F, 4.0F, 0.0F, 3.9269908169872414F, -0.7853981633974483F));


        return LayerDefinition.create(mesh, 64, 48);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);

        this.legL1.zRot = 0.7853981633974483F;
        this.legR1.zRot = -0.7853981633974483F;
        this.legL2.zRot = 0.5235987755982988F;
        this.legR2.zRot = -0.5235987755982988F;
        this.legL3.zRot = 0.7853981633974483F;
        this.legR3.zRot = -0.7853981633974483F;

        this.legL1.yRot = 0.7853981633974483F;
        this.legR1.yRot = 2.356194490192345F;
        this.legL2.yRot = 0.0F;
        this.legR2.yRot = 3.141592653589793F;
        this.legL3.yRot = -0.7853981633974483F;
        this.legR3.yRot = 3.9269908169872414F;

        float f3 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f4 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f5 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f8 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f9 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;

        this.legL1.yRot -= f3;
        this.legR1.yRot -= -f3;
        this.legL2.yRot -= f4;
        this.legR2.yRot -= -f4;
        this.legL3.yRot -= f5;
        this.legR3.yRot -= -f5;

        this.legL1.zRot -= f7;
        this.legR1.zRot -= -f7;
        this.legL2.zRot -= f8;
        this.legR2.zRot -= -f8;
        this.legL3.zRot -= f9;
        this.legR3.zRot -= -f9;
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
    }
}
