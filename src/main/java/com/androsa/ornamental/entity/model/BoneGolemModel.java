package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.BoneGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * BoneGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class BoneGolemModel<T extends BoneGolem> extends AbstractGolemModel<T> {

    public BoneGolemModel(ModelPart root) {
        super(root, true, true, false);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(53, 15)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4),
                PartPose.offset(0.0F, -19.0F, 0.0F));

        PartDefinition leftarmdef = part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(58, 0)
                        .addBox(0.0F, -1.5F, -2.5F, 5, 5, 5),
                PartPose.offset(6.9F, -17.0F, 0.0F));
        PartDefinition upperleftarmdef = leftarmdef.addOrReplaceChild("upper_arm_left", CubeListBuilder.create()
                        .texOffs(42, 14)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2),
                PartPose.offset(2.5F, 3.5F, 0.0F));
        PartDefinition lowerleftarmdef = upperleftarmdef.addOrReplaceChild("lower_arm_left", CubeListBuilder.create()
                        .texOffs(55, 27)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4),
                PartPose.offset(0.0F, 6.0F, 0.0F));
        lowerleftarmdef.addOrReplaceChild("claw_left1", CubeListBuilder.create()
                        .texOffs(88, 0)
                        .addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2),
                PartPose.offsetAndRotation(1.5F, 8.0F, -1.5F, 0.0F, -0.7853981633974483F, 0.0F));
        lowerleftarmdef.addOrReplaceChild("claw_left2", CubeListBuilder.create()
                        .texOffs(48, 25)
                        .addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2),
                PartPose.offset(0.0F, 8.0F, -1.5F));
        lowerleftarmdef.addOrReplaceChild("claw_left3", CubeListBuilder.create()
                        .texOffs(20, 31)
                        .addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2),
                PartPose.offsetAndRotation(-1.5F, 8.0F, -1.5F, 0.0F, 0.7853981633974483F, 0.0F));
        lowerleftarmdef.addOrReplaceChild("claw_left4", CubeListBuilder.create()
                        .texOffs(26, 32)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 7, 2),
                PartPose.offset(0.0F, 8.0F, 1.5F));

        PartDefinition rightarmdef = part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(73, 5)
                        .addBox(-5.0F, -1.5F, -2.5F, 5, 5, 5),
                PartPose.offset(-6.9F, -17.0F, 0.0F));
        PartDefinition upperrightarmdef = rightarmdef.addOrReplaceChild("upper_arm_right", CubeListBuilder.create()
                        .texOffs(71, 34)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2),
                PartPose.offset(-2.5F, 3.5F, 0.0F));
        PartDefinition lowerrightarmdef = upperrightarmdef.addOrReplaceChild("lower_arm_right", CubeListBuilder.create()
                        .texOffs(79, 34)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4),
                PartPose.offset(0.0F, 6.0F, 0.0F));
        lowerrightarmdef.addOrReplaceChild("claw_right1", CubeListBuilder.create()
                        .texOffs(32, 35)
                        .addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2),
                PartPose.offsetAndRotation(1.5F, 8.0F, -1.5F, 0.0F, -0.7853981633974483F, 0.0F));
        lowerrightarmdef.addOrReplaceChild("claw_right2", CubeListBuilder.create()
                        .texOffs(38, 35)
                        .addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2),
                PartPose.offset(0.0F, 8.0F, -1.5F));
        lowerrightarmdef.addOrReplaceChild("claw_right3", CubeListBuilder.create()
                        .texOffs(44, 35)
                        .addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2),
                PartPose.offsetAndRotation(-1.5F, 8.0F, -1.5F, 0.0F, 0.7853981633974483F, 0.0F));
        lowerrightarmdef.addOrReplaceChild("claw_right4", CubeListBuilder.create()
                        .texOffs(50, 39)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 7, 2),
                PartPose.offset(0.0F, 8.0F, 1.5F));

        part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(-7.0F, 0.0F, -2.5F, 14, 7, 5),
                PartPose.offset(0.0F, -17.1F, 0.0F));
        part.addOrReplaceChild("spine", CubeListBuilder.create()
                        .texOffs(50, 0)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2),
                PartPose.offset(0.0F, -20.0F, 2.0F));
        part.addOrReplaceChild("waist", CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 7, 2),
                PartPose.offset(0.0F, -3.0F, 0.0F));

        PartDefinition leftlegdef = part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, 0.0F, -1.5F, 3, 10, 3),
                PartPose.offset(2.5F, 0.0F, 0.0F));
        PartDefinition lowerleftlegdef = leftlegdef.addOrReplaceChild("lower_leg_left", CubeListBuilder.create()
                        .texOffs(75, 15)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 14, 5),
                PartPose.offset(1.5F, 10.0F, 0.0F));
        lowerleftlegdef.addOrReplaceChild("toe_left1", CubeListBuilder.create()
                        .texOffs(24, 7)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 1, 8),
                PartPose.offsetAndRotation(1.2F, 14.0F, -2.0F, 0.0F, -0.2617993877991494F, 0.0F));
        lowerleftlegdef.addOrReplaceChild("toe_left2", CubeListBuilder.create()
                        .texOffs(33, 8)
                        .addBox(-1.0F, -1.0F, -5.0F, 2, 1, 5),
                PartPose.offsetAndRotation(-1.2F, 14.0F, -2.0F, 0.0F, 0.2617993877991494F, 0.0F));
        lowerleftlegdef.addOrReplaceChild("toe_left3", CubeListBuilder.create()
                        .texOffs(30, 17)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 1, 8),
                PartPose.offsetAndRotation(0.0F, 7.0F, 2.5F, -1.1344640137963142F, 0.0F, 0.0F));

        PartDefinition rightlegdef = part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(12, 0)
                        .addBox(-3.0F, 0.0F, -1.5F, 3, 10, 3),
                PartPose.offset(-2.5F, 0.0F, 0.0F));
        PartDefinition lowerrightlegdef = rightlegdef.addOrReplaceChild("lower_leg_right", CubeListBuilder.create()
                        .texOffs(0, 25)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 14, 5),
                PartPose.offset(-1.5F, 10.0F, 0.0F));
        lowerrightlegdef.addOrReplaceChild("toe_right1", CubeListBuilder.create()
                        .texOffs(20, 25)
                        .addBox(-1.0F, -1.0F, -5.0F, 2, 1, 5),
                PartPose.offsetAndRotation(1.2F, 14.0F, -2.0F, 0.0F, -0.2617993877991494F, 0.0F));
        lowerrightlegdef.addOrReplaceChild("toe_right2", CubeListBuilder.create()
                        .texOffs(29, 26)
                        .addBox(-1.0F, -1.0F, -5.0F, 2, 1, 5),
                PartPose.offsetAndRotation(-1.2F, 14.0F, -2.0F, 0.0F, 0.2617993877991494F, 0.0F));
        lowerrightlegdef.addOrReplaceChild("toe_right3", CubeListBuilder.create()
                        .texOffs(35, 26)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 1, 8),
                PartPose.offsetAndRotation(0.0F, 7.0F, 2.5F, -1.1344640137963142F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 96, 64);
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
