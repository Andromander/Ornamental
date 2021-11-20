package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.OrnamentalGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * NetherBrickGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
public class NetherBrickGolemModel<T extends OrnamentalGolem> extends AbstractGolemModel<T> {

    public NetherBrickGolemModel(ModelPart root) {
        super(root, true, true, false);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition headdef = part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(28, 25)
                        .addBox(-2.5F, -3.0F, -3.0F, 5, 3, 5),
                PartPose.offset(0.0F, -3.0F, -1.0F));
        PartDefinition lefthorndef = headdef.addOrReplaceChild("lower_horn_left", CubeListBuilder.create()
                        .texOffs(48, 25)
                        .addBox(0.0F, -8.0F, -1.0F, 1, 8, 2),
                PartPose.offsetAndRotation(1.3F, -4.0F, -1.0F, 0.7853981633974483F, -0.4363323129985824F, 0.0F));
        lefthorndef.addOrReplaceChild("upper_horn_left", CubeListBuilder.create()
                        .texOffs(50, 35)
                        .addBox(0.0F, 0.0F, 0.0F, 1, 2, 5),
                PartPose.offset(0.0F, -8.0F, 1.0F));
        PartDefinition righthorndef = headdef.addOrReplaceChild("lower_horn_right", CubeListBuilder.create()
                        .texOffs(54, 25)
                        .addBox(-1.0F, -8.0F, -1.0F, 1, 8, 2),
                PartPose.offsetAndRotation(-1.3F, -4.0F, -1.0F, 0.7853981633974483F, 0.4363323129985824F, 0.0F));
        righthorndef.addOrReplaceChild("upper_horn_right", CubeListBuilder.create()
                        .texOffs(0, 37)
                        .addBox(-1.0F, 0.0F, 0.0F, 1, 2, 5),
                PartPose.offset(0.0F, -8.0F, 1.0F));
        headdef.addOrReplaceChild("forehead", CubeListBuilder.create()
                        .texOffs(28, 33)
                        .addBox(-2.5F, -3.0F, -3.0F, 5, 3, 6),
                PartPose.offset(0.0F, -3.0F, -1.0F));

        part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(26, 11)
                        .addBox(-6.0F, 0.0F, -3.0F, 12, 8, 6),
                PartPose.offset(0.0F, -3.0F, 0.0F));
        part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(0, 17)
                        .addBox(0.0F, 0.0F, -2.0F, 3, 16, 4),
                PartPose.offset(6.0F, -3.3F, 0.0F));
        part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(14, 21)
                        .addBox(-3.0F, 0.0F, -2.0F, 3, 16, 4),
                PartPose.offset(-6.0F, -3.3F, 0.0F));
        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-4.0F, 0.0F, -2.5F, 8, 6, 5),
                PartPose.offset(0.0F, 5.0F, 0.0F));
        part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, 0.0F, -2.0F, 4, 13, 4),
                PartPose.offset(1.0F, 11.0F, 0.0F));
        part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(16, 0)
                        .addBox(-4.0F, 0.0F, -2.0F, 4, 13, 4),
                PartPose.offset(-1.0F, 11.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 48);
    }
}
