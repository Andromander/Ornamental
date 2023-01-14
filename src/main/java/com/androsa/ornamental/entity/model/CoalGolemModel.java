package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.CoalGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * CoalGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
public class CoalGolemModel<T extends CoalGolem> extends AbstractGolemModel<T> {

    public CoalGolemModel(ModelPart root) {
        super(root, true, true, true);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 19)
                        .addBox(-2.5F, -6.0F, -3.0F, 5, 6, 5),
                PartPose.offset(0.0F, -2.0F, -1.5F));
        part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(19, 13)
                        .addBox(-5.0F, 0.0F, -2.5F, 10, 6, 5),
                PartPose.offset(0.0F, -2.0F, 0.0F));
        part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(44, 0)
                        .addBox(0.0F, 0.0F, -1.0F, 2, 14, 2),
                PartPose.offset(5.0F, -1.0F, 0.0F));
        part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(52, 0)
                        .addBox(-2.0F, 0.0F, -1.0F, 2, 14, 2),
                PartPose.offset(-5.0F, -1.0F, 0.0F));
        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(-3.0F, 0.0F, -2.0F, 6, 9, 4),
                PartPose.offset(0.0F, 4.0F, 0.0F));
        part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, 0.0F, -1.5F, 3, 11, 3),
                PartPose.offset(1.0F, 13.0F, 0.0F));
        part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(12, 0)
                        .addBox(-3.0F, 0.0F, -1.5F, 3, 11, 3),
                PartPose.offset(-1.0F, 13.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (entity.isOnFire()) {
            armR.zRot = 2.3F;
            armL.zRot = -2.3F;
        } else {
            armR.zRot = 0.0F;
            armL.zRot = 0.0F;
            swingArms(limbSwing, limbSwingAmount);
        }
    }
}
