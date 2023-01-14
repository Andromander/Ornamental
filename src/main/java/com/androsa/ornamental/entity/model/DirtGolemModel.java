package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.DirtGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * DirtGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
public class DirtGolemModel<T extends DirtGolem> extends AbstractGolemModel<T> {

    public DirtGolemModel(ModelPart root) {
        super(root, true, true, true);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-1.5F, -3.0F, -2.0F, 3, 3, 3),
                PartPose.offset(0.0F, 15.0F, 0.0F));
        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(16, 0)
                        .addBox(-2.5F, 0.0F, -1.5F, 5, 6, 3),
                PartPose.offset(0.0F, 15.0F, 0.0F));
        part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(44, 0)
                        .addBox(0.0F, 0.0F, -1.0F, 2, 4, 2),
                PartPose.offset(2.5F, 15.5F, 0.0F));
        part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(52, 0)
                        .addBox(-2.0F, 0.0F, -1.0F, 2, 4, 2),
                PartPose.offset(-2.5F, 15.5F, 0.0F));
        part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, 0.0F, -1.0F, 2, 3, 2),
                PartPose.offset(0.5F, 21.0F, 0.0F));
        part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(8, 0)
                        .addBox(-2.0F, 0.0F, -1.0F, 2, 3, 2),
                PartPose.offset(-0.5F, 21.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    public ModelPart getHead() {
        return head;
    }
}
