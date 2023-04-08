package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.ObsidianGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * ObsidianGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
public class ObsidianGolemModel<T extends ObsidianGolem> extends AbstractGolemModel<T> {

    public ObsidianGolemModel(ModelPart root) {
        super(root, true, true, true, true);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition headdef = part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(64, 0)
                        .addBox(-4.0F, -9.0F, -4.5F, 8, 9, 7),
                PartPose.offset(0.0F, -13.0F, -4.0F));
        headdef.addOrReplaceChild("chin", CubeListBuilder.create()
                        .texOffs(59, 60)
                        .addBox(-5.0F, 0.0F, 0.0F, 10, 3, 5),
                PartPose.offset(0.0F, -2.0F, -5.5F));

        part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(0, 40)
                        .addBox(-11.0F, 0.0F, -5.0F, 22, 13, 10),
                PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition leftarmdef = part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(57, 16)
                        .addBox(0.0F, 0.0F, -3.5F, 6, 15, 7),
                PartPose.offset(11.0F, -12.0F, 0.0F));
        leftarmdef.addOrReplaceChild("lower_arm_left", CubeListBuilder.create()
                        .texOffs(0, 63)
                        .addBox(-4.0F, 0.0F, -4.5F, 8, 11, 9),
                PartPose.offset(3.0F, 15.0F, 0.0F));

        PartDefinition rightarmdef = part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(64, 38)
                        .addBox(-6.0F, 0.0F, -3.5F, 6, 15, 7),
                PartPose.offset(-11.0F, -12.0F, 0.0F));
        rightarmdef.addOrReplaceChild("lower_arm_right", CubeListBuilder.create()
                        .texOffs(34, 63)
                        .addBox(-4.0F, 0.0F, -4.5F, 8, 11, 9),
                PartPose.offset(-3.0F, 15.0F, 0.0F));

        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 20)
                        .addBox(-7.5F, 0.0F, -4.0F, 15, 12, 8),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, 0.0F, -4.0F, 8, 12, 8),
                PartPose.offset(1.5F, 12.0F, 0.0F));
        part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-8.0F, 0.0F, -4.0F, 8, 12, 8),
                PartPose.offset(-1.5F, 12.0F, 0.0F));

        return LayerDefinition.create(mesh, 96, 96);
    }
}
