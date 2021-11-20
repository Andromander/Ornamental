package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.GoldGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * GoldGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
public class GoldGolemModel<T extends GoldGolem> extends FlowerGolemModel<T> {

    public GoldGolemModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(91, 19).addBox(-3.5F, -12.0F, -5.5F, 7, 9, 7)
                        .texOffs(68, 0).addBox(-1.0F, -5.0F, -7.5F, 2, 4, 2),
                PartPose.offset(0.0F, -7.0F, -2.0F));
        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(68, 0).addBox(-8.0F, -2.0F, -5.0F, 16, 10, 9)
                        .texOffs(63, 19).addBox(-4.5F, 8.0F, -3.0F, 9, 7, 5, new CubeDeformation(0.5F)),
                PartPose.offset(0.0F, -9.0F, 0.0F));
        part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(8.0F, -2.5F, -3.0F, 4, 30, 4),
                PartPose.offset(0.0F, -7.0F, 0.4F));
        part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(34, 0)
                        .addBox(-12.0F, -2.5F, -3.0F, 4, 30, 4),
                PartPose.offset(0.0F, -7.0F, 0.5F));
        part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(50, 0)
                        .addBox(-3.5F, -3.0F, -2.5F, 5, 18, 4),
                PartPose.offset(-2.5F, 9.0F, 0.0F));
        part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(16, 0)
                        .addBox(-3.5F, -3.0F, -2.5F, 5, 18, 4)
                        .mirror(),
                PartPose.offset(4.5F, 9.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 64);
    }
}
