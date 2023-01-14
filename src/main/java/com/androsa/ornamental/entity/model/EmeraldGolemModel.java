package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.EmeraldGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * ModelIronGolem - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class EmeraldGolemModel<T extends EmeraldGolem> extends FlowerGolemModel<T> {

    public EmeraldGolemModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition headdef = part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(10, 23)
                        .addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8),
                PartPose.offset(0.0F, -5.0F, -2.0F));
        headdef.addOrReplaceChild("nose", CubeListBuilder.create()
                        .texOffs(76, 0)
                        .addBox(-1.0F, 0.0F, -5.5F, 2, 4, 2),
                PartPose.offset(0.0F, -5.0F, -2.0F));

        part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(46, 27)
                        .addBox(-7.5F, 0.0F, -5.0F, 15, 10, 10),
                PartPose.offset(0.0F, 10.0F, 0.0F));
        part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(7.5F, -2.5F, -3.0F, 4, 26, 5),
                PartPose.offset(0.0F, -4.0F, 0.0F));
        part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(38, 0)
                        .addBox(-11.5F, -2.5F, -3.0F, 4, 26, 5),
                PartPose.offset(0.0F, -4.0F, 0.0F));
        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(76, 0)
                        .addBox(-7.5F, -2.0F, -5.0F, 15, 17, 10),
                PartPose.offset(0.0F, -5.0F, 0.0F));
        part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(56, 0)
                        .addBox(-2.5F, -3.0F, -3.0F, 5, 14, 5),
                PartPose.offset(-4.0F, 13.0F, 0.5F));
        part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(18, 0)
                        .addBox(-2.5F, -3.0F, -3.0F, 5, 14, 5)
                        .mirror(),
                PartPose.offset(4.0F, 13.0F, 0.5F));

        return LayerDefinition.create(mesh, 128, 64);
    }
}
