package com.androsa.ornamental.entity.model;
// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings

import com.androsa.ornamental.entity.CalciteGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class CalciteGolemModel<T extends CalciteGolem> extends AbstractGolemModel<T> {

	private final ModelPart neck;
	private final ModelPart collarL1;
	private final ModelPart collarL2;
	private final ModelPart collarL3;
	private final ModelPart collarL4;
	private final ModelPart collarL5;
	private final ModelPart collarR1;
	private final ModelPart collarR2;
	private final ModelPart collarR3;
	private final ModelPart collarR4;
	private final ModelPart collarR5;
	private final ModelPart chestL;
	private final ModelPart chestR;
	private final ModelPart forearmL;
	private final ModelPart forearmR;
	private final ModelPart handL;
	private final ModelPart handR;

	public CalciteGolemModel(ModelPart root) {
		super(root, false, false, true, false);

		this.neck = root.getChild("neck");
		this.head = neck.getChild("head");
		this.armL = root.getChild("upper_left_arm");
		this.forearmL = armL.getChild("lower_left_arm");
		this.handL = forearmL.getChild("left_hand");
		this.armR = root.getChild("upper_right_arm");
		this.forearmR = armR.getChild("lower_right_arm");
		this.handR = forearmR.getChild("right_hand");
		ModelPart collarL = root.getChild("left_collar");
		this.collarL1 = collarL.getChild("left_collar1");
		this.collarL2 = collarL.getChild("left_collar2");
		this.collarL3 = collarL.getChild("left_collar3");
		this.collarL4 = collarL.getChild("left_collar4");
		this.collarL5 = collarL.getChild("left_collar5");
		ModelPart collarR = root.getChild("right_collar");
		this.collarR1 = collarR.getChild("right_collar1");
		this.collarR2 = collarR.getChild("right_collar2");
		this.collarR3 = collarR.getChild("right_collar3");
		this.collarR4 = collarR.getChild("right_collar4");
		this.collarR5 = collarR.getChild("right_collar5");
		ModelPart torso = root.getChild("torso");
		this.chestL = torso.getChild("left_chest");
		this.chestR = torso.getChild("right_chest");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

		PartDefinition neck = part.addOrReplaceChild("neck", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F),
				PartPose.offset(0.0F, -12.0F, 0.5F));
		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(60, 42)
						.addBox(-1.5F, -4.0F, -2.5F, 4.0F, 4.0F, 4.0F),
				PartPose.offsetAndRotation(0.0F, -4.0F, -0.5F, 0.0F, 0.7854F, 0.0F));
		head.addOrReplaceChild("left_frill", CubeListBuilder.create()
						.texOffs(0, 51)
						.addBox(0.0F, -7.0F, 0.0F, 0.0F, 7.0F, 8.0F),
				PartPose.offsetAndRotation(2.5F, -4.0F, -2.5F, 0.0F, 0.0F, 0.3927F));
		head.addOrReplaceChild("right_frill", CubeListBuilder.create()
						.texOffs(34, 52)
						.addBox(0.0F, -7.0F, 0.0F, 0.0F, 7.0F, 8.0F),
				PartPose.offsetAndRotation(2.5F, -4.0F, -2.5F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition left_collar = part.addOrReplaceChild("left_collar", CubeListBuilder.create(),
				PartPose.offset(0.0F, -10.0F, 0.0F));
		left_collar.addOrReplaceChild("left_collar1", CubeListBuilder.create()
						.texOffs(36, 2)
						.addBox(-12.0F, -1.0F, -3.0F, 11.0F, 2.0F, 6.0F),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, -0.2967F, -0.1396F));
		left_collar.addOrReplaceChild("left_collar2", CubeListBuilder.create()
						.texOffs(34, 26)
						.addBox(-13.0F, -1.0F, -3.0F, 12.0F, 2.0F, 6.0F),
				PartPose.offset(0.0F, -2.0F, 0.0F));
		left_collar.addOrReplaceChild("left_collar3", CubeListBuilder.create()
						.texOffs(0, 32)
						.addBox(-14.0F, -1.0F, -3.0F, 13.0F, 2.0F, 6.0F),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.2967F, 0.1396F));
		left_collar.addOrReplaceChild("left_collar4", CubeListBuilder.create()
						.texOffs(0, 16)
						.addBox(-14.8978F, -1.1743F, -2.5896F, 14.0F, 2.0F, 6.0F),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.5934F, 0.2793F));
		left_collar.addOrReplaceChild("left_collar5", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-15.8978F, -1.1743F, -2.5896F, 15.0F, 2.0F, 6.0F),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.8901F, 0.4189F));

		PartDefinition right_collar = part.addOrReplaceChild("right_collar", CubeListBuilder.create(),
				PartPose.offset(0.0F, -10.0F, 0.0F));
		right_collar.addOrReplaceChild("right_collar1", CubeListBuilder.create()
						.texOffs(36, 2)
						.addBox(1.0F, -1.0F, -3.0F, 11.0F, 2.0F, 6.0F),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.2967F, 0.1396F));
		right_collar.addOrReplaceChild("right_collar2", CubeListBuilder.create()
						.texOffs(34, 26)
						.addBox(1.0F, -1.0F, -3.0F, 12.0F, 2.0F, 6.0F),
				PartPose.offset(0.0F, -2.0F, 0.0F));
		right_collar.addOrReplaceChild("right_collar3", CubeListBuilder.create()
						.texOffs(0, 32)
						.addBox(1.0F, -1.0F, -3.0F, 13.0F, 2.0F, 6.0F),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, -0.2967F, -0.1396F));
		right_collar.addOrReplaceChild("right_collar4", CubeListBuilder.create()
						.texOffs(0, 16)
						.addBox(0.8978F, -1.1743F, -2.5896F, 14.0F, 2.0F, 6.0F),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, -0.5934F, -0.2793F));
		right_collar.addOrReplaceChild("right_collar5", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(0.8978F, -1.1743F, -2.5896F, 15.0F, 2.0F, 6.0F),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, -0.8901F, -0.4189F));

		PartDefinition torso = part.addOrReplaceChild("torso", CubeListBuilder.create()
						.texOffs(0, 40)
						.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 13.0F, 6.0F),
				PartPose.offset(0.0F, -12.0F, -0.5F));
		torso.addOrReplaceChild("right_chest", CubeListBuilder.create()
						.texOffs(42, 48)
						.addBox(-6.0F, 0.0F, -3.0F, 6.0F, 6.0F, 6.0F),
				PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, -0.7854F, 0.0F));
		torso.addOrReplaceChild("left_chest", CubeListBuilder.create()
						.texOffs(24, 42)
						.addBox(0.0F, 0.0F, -3.0F, 6.0F, 6.0F, 6.0F),
				PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition upper_left_arm = part.addOrReplaceChild("upper_left_arm", CubeListBuilder.create()
						.texOffs(66, 50)
						.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F),
				PartPose.offsetAndRotation(7.0F, -12.0F, 0.5F, -0.8311F, -0.8249F, -0.0948F));
		PartDefinition lower_left_arm = upper_left_arm.addOrReplaceChild("lower_left_arm", CubeListBuilder.create()
						.texOffs(32, 67)
						.addBox(-0.5F, 0.0F, -2.0F, 1.0F, 12.0F, 2.0F),
				PartPose.offsetAndRotation(0.0F, 11.0F, 1.0F, -1.0472F, 0.0F, 0.0F));
		lower_left_arm.addOrReplaceChild("left_hand", CubeListBuilder.create()
						.texOffs(42, 42)
						.addBox(-1.5F, 0.0F, -0.5F, 3.0F, 4.0F, 1.0F),
				PartPose.offsetAndRotation(0.0F, 12.0F, -1.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition upper_right_arm = part.addOrReplaceChild("upper_right_arm", CubeListBuilder.create()
						.texOffs(66, 63)
						.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F),
				PartPose.offsetAndRotation(-7.0F, -12.0F, 0.5F, -0.8311F, 0.8249F, 0.0948F));
		PartDefinition lower_right_arm = upper_right_arm.addOrReplaceChild("lower_right_arm", CubeListBuilder.create()
						.texOffs(12, 66)
						.addBox(-0.5F, 0.0F, -2.0F, 1.0F, 12.0F, 2.0F),
				PartPose.offsetAndRotation(0.0F, 11.0F, 1.0F, -1.0472F, 0.0F, 0.0F));
		lower_right_arm.addOrReplaceChild("right_hand", CubeListBuilder.create()
						.texOffs(18, 40)
						.addBox(-1.5F, 0.0F, -0.5F, 3.0F, 4.0F, 1.0F),
				PartPose.offsetAndRotation(0.0F, 12.0F, -1.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition leg_left = part.addOrReplaceChild("leg_left", CubeListBuilder.create()
						.texOffs(50, 60)
						.addBox(-0.4812F, -0.5624F, -2.3562F, 2.0F, 9.0F, 6.0F),
				PartPose.offsetAndRotation(3.0F, 1.0F, 0.0F, -0.718F, -0.5455F, -0.0556F));
		leg_left.addOrReplaceChild("foreleg_left", CubeListBuilder.create()
						.texOffs(6, 66)
						.addBox(0.0143F, 0.1093F, -0.1494F, 1.0F, 17.0F, 2.0F),
				PartPose.offsetAndRotation(0.0F, 7.0F, 2.0F, 0.8722F, -0.0334F, 0.0281F));

		PartDefinition leg_right = part.addOrReplaceChild("leg_right", CubeListBuilder.create()
						.texOffs(18, 54)
						.addBox(-1.5188F, -0.5624F, -2.3562F, 2.0F, 9.0F, 6.0F),
				PartPose.offsetAndRotation(-3.0F, 1.0F, 0.0F, -0.718F, 0.5455F, 0.0556F));
		leg_right.addOrReplaceChild("foreleg_right", CubeListBuilder.create()
						.texOffs(6, 66)
						.addBox(-1.0143F, 0.1093F, -0.1494F, 1.0F, 17.0F, 2.0F),
				PartPose.offsetAndRotation(0.0F, 7.0F, 2.0F, 0.8722F, 0.0334F, -0.0281F));

		return LayerDefinition.create(mesh, 96, 96);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.legL.xRot = -1.5F * (this.triangleWave(limbSwing, 13.0F) * 0.5F) * limbSwingAmount - 0.718F;
		this.legR.xRot = 1.5F * (this.triangleWave(limbSwing, 13.0F) * 0.5F) * limbSwingAmount - 0.718F;

		if (entity.isResonating()) {
			this.resonate(ageInTicks);

			this.neck.yRot = netHeadYaw * ((float)Math.PI / 180F);
			this.neck.xRot = headPitch * ((float)Math.PI / 180F);
			this.armL.xRot = -1.6165F;
			this.armR.xRot = -1.6165F;
			this.forearmL.xRot = 0.0F;
			this.forearmR.xRot = 0.0F;
		} else {
			this.stopCollar();
			if (entity.getChargeState() == 1) {
				this.neck.xRot = 0.4363F;
				this.chestL.yRot = 1.0036F;
				this.chestR.yRot = -1.0036F;
				this.armL.yRot = 0.3096F;
				this.armR.yRot = -0.3096F;
				this.forearmL.xRot = -1.4835F;
				this.forearmR.xRot = -1.4835F;
				this.handL.xRot = 1.5708F;
				this.handR.xRot = 1.5708F;
			} else if (entity.getChargeState() == 2) {
				this.neck.xRot = -0.3927F;
				this.chestL.yRot = 0.5236F;
				this.chestR.yRot = -0.5236F;
				this.armL.xRot = -1.0929F;
				this.armL.yRot = -1.7412F;
				this.armR.xRot = -1.0929F;
				this.armR.yRot = 1.7412F;
				this.forearmL.xRot = 0.0F;
				this.forearmR.xRot = 0.0F;
				this.handL.xRot = 0.0F;
				this.handR.xRot = 0.0F;
			} else {
				this.neck.yRot = netHeadYaw * ((float)Math.PI / 180F);
				this.neck.xRot = headPitch * ((float)Math.PI / 180F);
				this.chestL.yRot = 0.7854F;
				this.chestR.yRot = -0.7854F;
				this.armL.xRot = -0.8311F;
				this.armL.yRot = -0.8249F;
				this.armR.xRot = -0.8311F;
				this.armR.yRot = 0.8249F;
				this.forearmL.xRot = -1.0472F;
				this.forearmR.xRot = -1.0472F;
				this.handL.xRot = 0.829F;
				this.handR.xRot = 0.829F;
			}
		}
	}

	private void resonate(float ageInTicks) {
		float f = ageInTicks * (float)Math.PI * 0.2F;

		this.collarL1.zRot = Mth.sin(f * 1.5F) * 0.2F - 0.1396F;
		this.collarL2.zRot = Mth.sin(f * 1.5F + 1.0F) * 0.2F;
		this.collarL3.zRot = Mth.sin(f * 1.5F + 2.0F) * 0.2F + 0.1396F;
		this.collarL4.zRot = Mth.sin(f * 1.5F + 3.0F) * 0.2F + 0.2793F;
		this.collarL5.zRot = Mth.sin(f * 1.5F + 4.0F) * 0.2F + 0.4189F;

		this.collarR1.zRot = -Mth.sin(f * 1.5F) * 0.2F + 0.1396F;
		this.collarR2.zRot = -Mth.sin(f * 1.5F + 1.0F) * 0.2F;
		this.collarR3.zRot = -Mth.sin(f * 1.5F + 2.0F) * 0.2F - 0.1396F;
		this.collarR4.zRot = -Mth.sin(f * 1.5F + 3.0F) * 0.2F - 0.2793F;
		this.collarR5.zRot = -Mth.sin(f * 1.5F + 4.0F) * 0.2F - 0.4189F;

		this.handL.xRot = Mth.sin(f * 4.0F) * 0.2F;
		this.handR.xRot = Mth.sin(f * 4.0F) * 0.2F;
	}

	private void stopCollar() {
		this.collarL1.zRot = -0.1396F;
		this.collarL2.zRot = 0.0F;
		this.collarL3.zRot = 0.1396F;
		this.collarL4.zRot = 0.2793F;
		this.collarL5.zRot = 0.4189F;

		this.collarR1.zRot = 0.1396F;
		this.collarR2.zRot = 0.0F;
		this.collarR3.zRot = -0.1396F;
		this.collarR4.zRot = -0.2793F;
		this.collarR5.zRot = -0.4189F;
	}
}