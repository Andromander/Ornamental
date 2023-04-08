package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.CopperGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * CopperGolem - Androsa
 * Created using Tabula 7.0.0
 */
public class CopperGolemModel<T extends CopperGolem> extends AbstractGolemModel<T> {
    public ModelPart antennaL;
    public ModelPart antennaR;
    public ModelPart backCellL;
    public ModelPart backCellR;
    public ModelPart shoulerL;
    public ModelPart armbladeL;
    public ModelPart fingerLL;
    public ModelPart fingerRL;
    public ModelPart shoulerR;
    public ModelPart armbladeR;
    public ModelPart fingerLR;
    public ModelPart fingerRR;
    public ModelPart thumbL;
    public ModelPart thumbR;
    public ModelPart legbladeL;
    public ModelPart legbladeR;

    public CopperGolemModel(ModelPart root) {
        super(root, true, true, true, true);

        this.antennaL = this.head.getChild("antenna_left");
        this.antennaR = this.head.getChild("antenna_right");
        ModelPart torso = root.getChild("torso");
        this.backCellL = torso.getChild("back_cell_left");
        this.backCellR = torso.getChild("back_cell_right");
        this.shoulerL = this.armL.getChild("shoulder_left");
        ModelPart forearmL = armL.getChild("forearm_left");
        this.armbladeL = forearmL.getChild("arm_blade_left");
        this.fingerLL = forearmL.getChild("left_finger_left");
        this.fingerRL = forearmL.getChild("right_finger_left");
        this.thumbL = forearmL.getChild("thumb_left");
        this.shoulerR = this.armR.getChild("shoulder_right");
        ModelPart forearmR = armR.getChild("forearm_right");
        this.armbladeR = forearmR.getChild("arm_blade_right");
        this.fingerLR = forearmR.getChild("left_finger_right");
        this.fingerRR = forearmR.getChild("right_finger_right");
        this.thumbR = forearmR.getChild("thumb_right");
        ModelPart forelegL = this.legL.getChild("foreleg_left");
        this.legbladeL = forelegL.getChild("leg_blade_left");
        ModelPart forelegR = this.legR.getChild("foreleg_right");
        this.legbladeR = forelegR.getChild("leg_blade_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition headpart = part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, -8.0F, -3.0F, 6, 8, 5),
                PartPose.offset(0.0F, -23.0F, -2.5F));
        headpart.addOrReplaceChild("antenna_left", CubeListBuilder.create()
                        .texOffs(66, 0)
                        .addBox(0.0F, -5.0F, -0.5F, 3, 5, 1),
                PartPose.offset(1.0F, -2.0F, 0.0F));
        headpart.addOrReplaceChild("antenna_right", CubeListBuilder.create()
                        .texOffs(43, 28)
                        .addBox(-3.0F, -5.0F, -0.5F, 3, 5, 1),
                PartPose.offset(-1.0F, -2.0F, 0.0F));

        part.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(22, 0)
                        .addBox(-1.5F, 0.0F, -1.5F, 3, 5, 3),
                PartPose.offset(0.0F, -25.0F, 0.0F));

        PartDefinition torsopart = part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(25, 0)
                        .addBox(-8.0F, 0.0F, -4.5F, 16, 8, 9),
                PartPose.offset(0.0F, -20.0F, 0.0F));
        torsopart.addOrReplaceChild("back_cell_left", CubeListBuilder.create()
                        .texOffs(51, 28)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 9, 4),
                PartPose.offset(4.0F, 1.5F, 2.5F));
        torsopart.addOrReplaceChild("back_cell_right", CubeListBuilder.create()
                        .texOffs(67, 28)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 9, 4),
                PartPose.offset(-4.0F, 1.5F, 2.5F));

        PartDefinition leftarmpart = part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(75, 0)
                        .addBox(0.0F, 0.0F, -2.0F, 4, 10, 4),
                PartPose.offset(8.0F, -19.0F, 0.0F));
        leftarmpart.addOrReplaceChild("shoulder_left", CubeListBuilder.create()
                        .texOffs(32, 39)
                        .addBox(0.0F, -4.0F, 0.0F, 5, 4, 6),
                PartPose.offset(0.0F, 2.0F, -2.5F));
        PartDefinition leftforearmpart = leftarmpart.addOrReplaceChild("forearm_left", CubeListBuilder.create()
                        .texOffs(16, 29)
                        .addBox(0.0F, 0.0F, 0.0F, 5, 10, 6),
                PartPose.offset(0.0F, 10.0F, -2.0F));
        leftforearmpart.addOrReplaceChild("arm_blade_left", CubeListBuilder.create()
                        .texOffs(80, 38)
                        .addBox(0.0F, -8.0F, -1.5F, 4, 8, 3),
                PartPose.offset(2.0F, 8.5F, 3.0F));
        leftforearmpart.addOrReplaceChild("left_finger_left", CubeListBuilder.create()
                        .texOffs(83, 28)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2),
                PartPose.offset(1.3F, 9.0F, 1.3F));
        leftforearmpart.addOrReplaceChild("right_finger_left", CubeListBuilder.create()
                        .texOffs(39, 17)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2),
                PartPose.offset(3.7F, 9.0F, 1.3F));
        leftforearmpart.addOrReplaceChild("thumb_left", CubeListBuilder.create()
                        .texOffs(32, 29)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2),
                PartPose.offset(2.5F, 9.0F, 4.7F));

        PartDefinition rightarmpart = part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(-4.0F, 0.0F, -2.0F, 4, 10, 4),
                PartPose.offset(-8.0F, -19.0F, 0.0F));
        rightarmpart.addOrReplaceChild("shoulder_right", CubeListBuilder.create()
                        .texOffs(54, 41)
                        .addBox(-5.0F, -4.0F, 0.0F, 5, 4, 6),
                PartPose.offset(0.0F, 2.0F, -2.5F));
        PartDefinition rightforearmpart = rightarmpart.addOrReplaceChild("forearm_right", CubeListBuilder.create()
                        .texOffs(0, 41)
                        .addBox(-5.0F, 0.0F, 0.0F, 5, 10, 6),
                PartPose.offset(0.0F, 10.0F, -2.0F));
        rightforearmpart.addOrReplaceChild("arm_blade_right", CubeListBuilder.create()
                        .texOffs(22, 49)
                        .addBox(-4.0F, -8.0F, -1.5F, 4, 8, 3),
                PartPose.offset(-2.0F, 8.5F, 3.0F));
        rightforearmpart.addOrReplaceChild("left_finger_right", CubeListBuilder.create()
                        .texOffs(83, 33)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2),
                PartPose.offset(-1.3F, 9.0F, 1.3F));
        rightforearmpart.addOrReplaceChild("right_finger_right", CubeListBuilder.create()
                        .texOffs(38, 34)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2),
                PartPose.offset(-3.7F, 9.0F, 1.3F));
        rightforearmpart.addOrReplaceChild("thumb_right", CubeListBuilder.create()
                        .texOffs(70, 41)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2),
                PartPose.offset(-2.5F, 9.0F, 4.7F));

        part.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(16, 17)
                        .addBox(-4.5F, 0.0F, -2.5F, 9, 7, 5),
                PartPose.offset(0.0F, -12.0F, 0.0F));
        part.addOrReplaceChild("waist", CubeListBuilder.create()
                        .texOffs(44, 17)
                        .addBox(-5.0F, 0.0F, -3.0F, 10, 5, 6),
                PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition leftlegpart = part.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(76, 14)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4),
                PartPose.offset(3.0F, 0.0F, 0.0F));
        PartDefinition leftforelegpart = leftlegpart.addOrReplaceChild("foreleg_left", CubeListBuilder.create()
                        .texOffs(36, 49)
                        .addBox(0.0F, 0.0F, 0.0F, 5, 14, 7),
                PartPose.offset(-2.0F, 10.0F, -2.0F));
        leftforelegpart.addOrReplaceChild("leg_blade_left", CubeListBuilder.create()
                        .texOffs(60, 51)
                        .addBox(0.0F, 0.0F, -1.5F, 4, 11, 3),
                PartPose.offset(2.0F, 0.5F, 3.0F));
        leftforelegpart.addOrReplaceChild("left_toe_left", CubeListBuilder.create()
                        .texOffs(73, 48)
                        .addBox(0.0F, -3.0F, -3.0F, 2, 3, 3),
                PartPose.offset(3.0F, 14.0F, 0.0F));
        leftforelegpart.addOrReplaceChild("right_toe_left", CubeListBuilder.create()
                        .texOffs(83, 49)
                        .addBox(0.0F, -3.0F, -3.0F, 2, 3, 3),
                PartPose.offset(0.0F, 14.0F, 0.0F));

        PartDefinition rightlegpart = part.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(0, 27)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4),
                PartPose.offset(-3.0F, 0.0F, 0.0F));
        PartDefinition rightforelegpart = rightlegpart.addOrReplaceChild("foreleg_right", CubeListBuilder.create()
                        .texOffs(0, 57)
                        .addBox(-5.0F, 0.0F, 0.0F, 5, 14, 7),
                PartPose.offset(2.0F, 10.0F, -2.0F));
        rightforelegpart.addOrReplaceChild("leg_blade_right", CubeListBuilder.create()
                        .texOffs(74, 61)
                        .addBox(-4.0F, 0.0F, -1.5F, 4, 11, 3),
                PartPose.offset(-2.0F, 0.5F, 3.0F));
        rightforelegpart.addOrReplaceChild("left_toe_right", CubeListBuilder.create()
                        .texOffs(74, 54)
                        .addBox(-2.0F, -3.0F, -3.0F, 2, 3, 3),
                PartPose.offset(0.0F, 14.0F, 0.0F));
        rightforelegpart.addOrReplaceChild("right_toe_right", CubeListBuilder.create()
                        .texOffs(84, 55)
                        .addBox(-2.0F, -3.0F, -3.0F, 2, 3, 3),
                PartPose.offset(-3.0F, 14.0F, 0.0F));

        return LayerDefinition.create(mesh, 96, 96);
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (entity.isTargeting() && entity.isCharged() && entity.getCharges() > 0) {
            this.armR.xRot = -1.5F;
            this.armL.xRot = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        } else {
            super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        }
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.isCharged()) {
            setChargedRotations();
        } else if (entity.getErosion() >= 3) {
            armL.y = -17.0F;
            armR.y = -17.0F;
        } else {
            resetRotations();
        }
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    private void resetRotations() {
        antennaL.zRot = 0.0F;
        antennaL.y = -2.0F;
        antennaR.zRot = 0.0F;
        antennaR.y = -2.0F;
        shoulerL.xRot = 0.0F;
        shoulerR.xRot = 0.0F;
        backCellL.setPos(4.0F, 1.5F, 2.5F);
        backCellL.xRot = 0.0F;
        backCellR.setPos(-4.0F, 1.5F, 2.5F);
        backCellR.xRot = 0.0F;
        armL.y = -19.0F;
        armR.y = -19.0F;
        armbladeL.zRot = 0.0F;
        armbladeR.zRot = 0.0F;
        fingerLL.setPos(1.3F, 9.0F, 1.3F);
        fingerRL.setPos(3.7F, 9.0F, 1.3F);
        thumbL.setPos(2.5F, 9.0F, 4.7F);
        fingerLR.setPos(-1.3F, 9.0F, 1.3F);
        fingerRR.setPos(-3.7F, 9.0F, 1.3F);
        thumbR.setPos(-2.5F, 9.0F, 4.7F);
        legbladeL.zRot = 0F;
        legbladeR.zRot = 0F;
    }

    private void setChargedRotations() {
        antennaL.zRot = 0.4F;
        antennaL.y = -3.0F;
        antennaR.zRot = -0.4F;
        antennaR.y = -3.0F;
        shoulerL.xRot = 0.4F;
        shoulerR.xRot = 0.4F;
        backCellL.setPos(4.0F, 1.0F, 4.5F);
        backCellL.xRot = 0.3F;
        backCellR.setPos(-4.0F, 1.0F, 4.5F);
        backCellR.xRot = 0.3F;
        armL.y = -19.0F;
        armR.y = -19.0F;
        armbladeL.zRot = 0.35F;
        armbladeR.zRot = -0.35F;
        fingerLL.setPos(0.8F, 10.0F, 0.8F);
        fingerRL.setPos(4.2F, 10.0F, 0.8F);
        thumbL.setPos(2.5F, 10.0F, 5.2F);
        fingerLR.setPos(-0.8F, 10.0F, 0.8F);
        fingerRR.setPos(-4.2F, 10.0F, 0.8F);
        thumbR.setPos(-2.5F, 10.0F, 5.2F);
        legbladeL.zRot = -0.25F;
        legbladeR.zRot = 0.25F;
    }
}
