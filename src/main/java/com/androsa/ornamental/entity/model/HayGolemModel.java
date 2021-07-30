package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.HayGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * HayGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class HayGolemModel<T extends HayGolem> extends AbstractGolemModel<T> {

    public HayGolemModel(ModelPart root) {
        super(root, true, true, false);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 35)
                        .addBox(-2.5F, -6.0F, -3.0F, 5, 6, 5),
                PartPose.offset(0.0F, -10.0F, -1.0F));
        part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(0, 24)
                        .addBox(-5.0F, 0.0F, -2.5F, 10, 6, 5),
                PartPose.offset(0.0F, -10.0F, 0.0F));
        part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(30, 24)
                        .addBox(0.0F, -1.5F, -1.5F, 12, 3, 3),
                PartPose.offset(5.0F, -8.0F, 0.0F));
        part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(30, 30)
                        .addBox(-12.0F, -1.5F, -1.5F, 12, 3, 3),
                PartPose.offset(-5.0F, -8.0F, 0.0F));
        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(24, 13)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 6, 5),
                PartPose.offset(0.0F, -4.0F, 0.0F));
        part.addOrReplaceChild("skirt", CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(-3.5F, 0.0F, -3.5F, 7, 6, 7),
                PartPose.offset(0.0F, 2.0F, 0.0F));
        part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, 0.0F, -2.0F, 2, 16, 4),
                PartPose.offset(0.0F, 8.0F, 0.0F));
        part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(12, 0)
                        .addBox(-2.0F, 0.0F, -2.0F, 2, 16, 4),
                PartPose.offset(0.0F, 8.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 48);
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (entity.isOnFire()) {
            armR.zRot = 0.7F;
            armL.zRot = -0.7F;
        } else {
            armR.zRot = 0.0F;
            armL.zRot = 0.0F;
        }
    }
}
