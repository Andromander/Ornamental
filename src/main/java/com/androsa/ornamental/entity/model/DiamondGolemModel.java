package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.DiamondGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * DiamondGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
public class DiamondGolemModel<T extends DiamondGolem> extends FlowerGolemModel<T> {

    public DiamondGolemModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8)
                        .texOffs(0, 0).addBox(-1.0F, -2.0F, -6.0F, 2, 4, 2),
                PartPose.offset(0.0F, 8.5F, -0.5F));
        part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(26, 10)
                        .addBox(-6.0F, 0.0F, -1.5F, 12, 7, 6),
                PartPose.offset(0.0F, 8.0F, -0.5F));
        part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(0.0F, 0.0F, -1.5F, 3, 13, 3),
                PartPose.offset(6.0F, 8.5F, 1.0F));
        part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(12, 16)
                        .addBox(-3.0F, 0.0F, -1.5F, 3, 13, 3),
                PartPose.offset(-6.0F, 8.5F, 1.0F));
        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(-3.5F, 0.0F, -2.0F, 7, 4, 4),
                PartPose.offset(0.0F, 15.0F, 1.0F));
        part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(46, 0)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4),
                PartPose.offset(2.4F, 19.0F, 1.0F));
        part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(24, 23)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4),
                PartPose.offset(-2.3F, 19.0F, 1.0F));

        return LayerDefinition.create(mesh, 64, 48);
    }
}
