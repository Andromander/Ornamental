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
 * ClayGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ClayGolemModel<T extends OrnamentalGolem> extends AbstractGolemModel<T> {

    public ClayGolemModel(ModelPart root) {
        super(root, true, true, true);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(19, 24)
                        .addBox(-2.5F, -6.0F, -4.0F, 5, 6, 5),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(27, 13)
                        .addBox(-5.0F, 0.0F, -2.5F, 10, 6, 5),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(0, 14)
                        .addBox(0.0F, 0.0F, -1.5F, 3, 12, 3),
                PartPose.offset(5.0F, 0.5F, 0.0F));
        part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(12, 14)
                        .addBox(-3.0F, 0.0F, -1.5F, 3, 12, 3),
                PartPose.offset(-5.0F, 0.5F, 0.0F));
        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-4.0F, 0.0F, -2.5F, 8, 8, 5),
                PartPose.offset(0.0F, 6.0F, 0.0F));
        part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, 0.0F, -2.0F, 4, 10, 4),
                PartPose.offset(0.5F, 14.0F, 0.0F));
        part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(16, 0)
                        .addBox(-4.0F, 0.0F, -2.0F, 4, 10, 4),
                PartPose.offset(-0.5F, 14.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 48);
    }
}
