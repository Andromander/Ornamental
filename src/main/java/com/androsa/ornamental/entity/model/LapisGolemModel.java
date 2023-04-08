package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.LapisGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * LapisGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
public class LapisGolemModel<T extends LapisGolem> extends AbstractGolemModel<T> {

    public LapisGolemModel(ModelPart root) {
        super(root, true, true, true, false);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition headdef = part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(14, 33)
                        .addBox(-3.5F, -8.0F, -2.5F, 7, 8, 6),
                PartPose.offset(0.0F, -4.8F, -3.0F));
        headdef.addOrReplaceChild("nose", CubeListBuilder.create()
                        .texOffs(48, 0)
                        .addBox(-1.0F, 0.0F, -2.0F, 2, 4, 2),
                PartPose.offset(0.0F, -3.0F, -2.5F));

        part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(0, 17)
                        .addBox(-6.5F, 0.0F, -3.5F, 13, 9, 7),
                PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition leftarmdef = part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(40, 21)
                        .addBox(0.0F, 0.0F, -2.0F, 3, 10, 4),
                PartPose.offset(6.5F, -4.5F, 0.0F));
        leftarmdef.addOrReplaceChild("lower_arm_left", CubeListBuilder.create()
                        .texOffs(40, 35)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 10, 5),
                PartPose.offset(1.5F, 10.0F, 0.0F));

        PartDefinition rightarmdef = part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(0, 33)
                        .addBox(-3.0F, 0.0F, -2.0F, 3, 10, 4),
                PartPose.offset(-6.5F, -4.5F, 0.0F));
        rightarmdef.addOrReplaceChild("lower_arm_right", CubeListBuilder.create()
                        .texOffs(0, 47)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 10, 5),
                PartPose.offset(-1.5F, 10.0F, 0.0F));

        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.5F, 0.0F, -2.5F, 9, 12, 5),
                PartPose.offset(0.0F, 4.0F, 0.0F));
        part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(43, 8)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5),
                PartPose.offset(3.0F, 16.0F, 0.0F));
        part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(28, 0)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5),
                PartPose.offset(-3.0F, 16.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (entity.isTargeting()) {
            this.armR.xRot = -1.5F;
            this.armL.xRot = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        } else {
            swingArms(limbSwing, limbSwingAmount);
        }
    }
}
