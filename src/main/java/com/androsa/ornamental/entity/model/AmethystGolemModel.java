package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.AmethystGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * AmethystGolem - Androsa
 * Created using Tabula 7.0.0
 */
public class AmethystGolemModel<T extends AmethystGolem> extends AbstractGolemModel<T> {
    public ModelPart jaw;
    public ModelPart fingerLL;
    public ModelPart fingerRL;
    public ModelPart fingerLR;
    public ModelPart fingerRR;

    private boolean reset = true;

    public AmethystGolemModel(ModelPart root) {
        super(root, true, true, true, false);

        this.jaw = this.head.getChild("jaw");
        this.fingerLL = this.armL.getChild("left_finger_left");
        this.fingerRL = this.armL.getChild("right_finger_left");
        this.fingerLR = this.armR.getChild("left_finger_right");
        this.fingerRR = this.armR.getChild("right_finger_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition headpart = part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.0F, -4.0F, -2.0F, 4, 4, 4),
                PartPose.offset(0.0F, -12.9F, -1.5F));
        headpart.addOrReplaceChild("face", CubeListBuilder.create()
                        .texOffs(56, 0)
                        .addBox(-1.5F, -1.5F, -1.5F, 4, 4, 2),
                PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, 0.0F, 0.0F, 0.7853981633974483F));
        headpart.addOrReplaceChild("horn_left", CubeListBuilder.create()
                        .texOffs(51, 27)
                        .addBox(0.0F, 0.0F, 0.0F, 7, 4, 2),
                PartPose.offsetAndRotation(0.0F, -4.0F, -3.0F, 0.0F, -0.3490658503988659F, -0.7853981633974483F));
        headpart.addOrReplaceChild("horn_right", CubeListBuilder.create()
                        .texOffs(22, 29)
                        .addBox(-7.0F, 0.0F, 0.0F, 7, 4, 2),
                PartPose.offsetAndRotation(0.0F, -4.0F, -3.0F, 0.0F, 0.3490658503988659F, 0.7853981633974483F));
        headpart.addOrReplaceChild("jaw", CubeListBuilder.create()
                        .texOffs(35, 30)
                        .addBox(-2.0F, 0.0F, -5.0F, 4, 2, 5),
                PartPose.offset(0.0F, 0.0F, 2.0F));

        PartDefinition torsopart = part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(16, 0)
                        .addBox(-4.0F, -5.0F, -2.0F, 8, 6, 4),
                PartPose.offset(0.0F, -5.0F, -0.5F));
        torsopart.addOrReplaceChild("top_shoulder_left", CubeListBuilder.create()
                        .texOffs(0, 35)
                        .addBox(0.0F, 0.0F, 0.0F, 14, 4, 1),
                PartPose.offsetAndRotation(0.5F, -4.5F, -1.5F, 0.0F, 0.0F, -0.6981317007977318F));
        torsopart.addOrReplaceChild("mid_shoulder_left", CubeListBuilder.create()
                        .texOffs(30, 37)
                        .addBox(0.0F, 0.0F, 0.0F, 12, 3, 1),
                PartPose.offsetAndRotation(1.0F, -3.0F, -0.5F, 0.0F, 0.0F, -0.4363323129985824F));
        torsopart.addOrReplaceChild("low_shoulder_left", CubeListBuilder.create()
                        .texOffs(0, 40)
                        .addBox(0.0F, 0.0F, 0.0F, 10, 2, 1),
                PartPose.offsetAndRotation(1.5F, -1.5F, 0.5F, 0.0F, 0.0F, -0.17453292519943295F));
        torsopart.addOrReplaceChild("top_shoulder_right", CubeListBuilder.create()
                        .texOffs(22, 41)
                        .addBox(-14.0F, 0.0F, 0.0F, 14, 4, 1),
                PartPose.offsetAndRotation(-0.5F, -4.5F, -1.5F, 0.0F, 0.0F, 0.6981317007977318F));
        torsopart.addOrReplaceChild("mid_shoulder_right", CubeListBuilder.create()
                        .texOffs(0, 46)
                        .addBox(-12.0F, 0.0F, 0.0F, 12, 3, 1),
                PartPose.offsetAndRotation(-1.0F, -3.0F, -0.5F, 0.0F, 0.0F, 0.4363323129985824F));
        torsopart.addOrReplaceChild("low_shoulder_right", CubeListBuilder.create()
                        .texOffs(0, 43)
                        .addBox(-10.0F, 0.0F, 0.0F, 10, 2, 1),
                PartPose.offsetAndRotation(-1.5F, -1.5F, 0.5F, 0.0F, 0.0F, 0.17453292519943295F));

        PartDefinition leftarmpart = part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(40, 0)
                        .addBox(0.0F, 7.5F, -2.0F, 4, 7, 4),
                PartPose.offset(9.0F, -8.0F, 0.0F));
        leftarmpart.addOrReplaceChild("arm_frill_left", CubeListBuilder.create()
                        .texOffs(52, 37)
                        .addBox(0.0F, 0.0F, 0.0F, 4, 2, 4),
                PartPose.offset(0.0F, 5.5F, -2.0F));
        leftarmpart.addOrReplaceChild("left_finger_left", CubeListBuilder.create()
                        .texOffs(0, 15)
                        .addBox(-0.5F, 0.0F, -1.0F, 1, 4, 2),
                PartPose.offsetAndRotation(3.0F, 14.0F, -1.0F, 0.3490658503988659F, 0.0F, 0.0F));
        leftarmpart.addOrReplaceChild("right_finger_left", CubeListBuilder.create()
                        .texOffs(53, 18)
                        .addBox(-0.5F, 0.0F, -1.0F, 1, 4, 2),
                PartPose.offsetAndRotation(1.0F, 14.0F, -1.0F, 0.3490658503988659F, 0.0F, 0.0F));

        PartDefinition rightarmdef = part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(52, 7)
                        .addBox(-4.0F, 7.5F, -2.0F, 4, 7, 4),
                PartPose.offset(-9.0F, -8.0F, 0.0F));
        rightarmdef.addOrReplaceChild("arm_frill_right", CubeListBuilder.create()
                        .texOffs(48, 43)
                        .addBox(0.0F, 0.0F, 0.0F, 4, 2, 4),
                PartPose.offset(-4.0F, 5.5F, -2.0F));
        rightarmdef.addOrReplaceChild("left_finger_right", CubeListBuilder.create()
                        .texOffs(59, 18)
                        .addBox(-0.5F, 0.0F, -1.0F, 1, 4, 2),
                PartPose.offsetAndRotation(-1.0F, 14.0F, -1.0F, 0.3490658503988659F, 0.0F, 0.0F));
        rightarmdef.addOrReplaceChild("right_finger_right", CubeListBuilder.create()
                        .texOffs(16, 20)
                        .addBox(-0.5F, 0.0F, -1.0F, 1, 4, 2),
                PartPose.offsetAndRotation(-3.0F, 14.0F, -1.0F, 0.3490658503988659F, 0.0F, 0.0F));

        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 8)
                        .addBox(-2.0F, 0.0F, -1.5F, 4, 4, 3),
                PartPose.offset(0.0F, -4.0F, -0.5F));
        part.addOrReplaceChild("waist", CubeListBuilder.create()
                        .texOffs(9, 10)
                        .addBox(-4.5F, 0.0F, -2.5F, 9, 5, 5),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftlegpart = part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(31, 14)
                        .addBox(0.0F, 0.0F, -3.5F, 5, 9, 6),
                PartPose.offset(1.5F, 2.0F, 0.5F));
        leftlegpart.addOrReplaceChild("foreleg_left", CubeListBuilder.create()
                        .texOffs(26, 46)
                        .addBox(0.0F, 0.0F, 0.0F, 3, 13, 3),
                PartPose.offset(0.0F, 9.0F, -3.5F));

        PartDefinition rightlegpart = part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(0, 20)
                        .addBox(-5.0F, 0.0F, -3.5F, 5, 9, 6),
                PartPose.offset(-1.5F, 2.0F, 0.5F));
        rightlegpart.addOrReplaceChild("foreleg_right", CubeListBuilder.create()
                        .texOffs(38, 46)
                        .addBox(-3.0F, 0.0F, 0.0F, 3, 13, 3),
                PartPose.offset(0.0F, 9.0F, -3.5F));

        return LayerDefinition.create(mesh, 70, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.isCasting()) {
            armL.xRot = -1.5F;
            armR.xRot = -1.5F;
            fingerLL.xRot = -1.0F;
            fingerRL.xRot = -1.0F;
            fingerLR.xRot = -1.0F;
            fingerRR.xRot = -1.0F;
            jaw.xRot = 0.5F;
            head.z = -3.0F;

            this.reset = false;
        } else if (!this.reset) {
            armL.xRot = 0.0F;
            armR.xRot = 0.0F;
            fingerLL.xRot = 0.3490658503988659F;
            fingerRL.xRot = 0.3490658503988659F;
            fingerLR.xRot = 0.3490658503988659F;
            fingerRR.xRot = 0.3490658503988659F;
            jaw.xRot = 0.0F;
            head.z = -1.5F;

            this.reset = true;
        } else {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}
