package com.androsa.ornamental.entity.model;
// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings


import com.androsa.ornamental.entity.MagmaGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class MagmaGolemModel<T extends MagmaGolem> extends AbstractGolemModel<T> {

	private final ModelPart flame;
	private final ModelPart flameRot;
	private final ModelPart finL;
	private final ModelPart finR;
	private final ModelPart flameL;
	private final ModelPart flameR;
	private final ModelPart braceL1;
	private final ModelPart braceL2;
	private final ModelPart braceL3;
	private final ModelPart braceR1;
	private final ModelPart braceR2;
	private final ModelPart braceR3;
	private final ModelPart clampL1;
	private final ModelPart clampL2;
	private final ModelPart clampL3;
	private final ModelPart clampR1;
	private final ModelPart clampR2;
	private final ModelPart clampR3;

	public MagmaGolemModel(ModelPart root) {
		super(root, true, true, true, true);

		this.flame = this.head.getChild("flame");
		this.flameRot = this.flame.getChild("flameRot");

		ModelPart torso = root.getChild("torso");
		this.finL = torso.getChild("left_fin");
		this.flameL = this.finL.getChild("left_flame");
		this.finR = torso.getChild("right_fin");
		this.flameR = this.finR.getChild("right_flame");

		ModelPart forearmL = this.armL.getChild("forearm_left");
		this.braceL1 = forearmL.getChild("left_brace1");
		this.braceL2 = forearmL.getChild("left_brace2");
		this.braceL3 = forearmL.getChild("left_brace3");

		ModelPart forearmR = this.armR.getChild("forearm_right");
		this.braceR1 = forearmR.getChild("right_brace1");
		this.braceR2 = forearmR.getChild("right_brace2");
		this.braceR3 = forearmR.getChild("right_brace3");

		ModelPart forelegL = this.legL.getChild("foreleg_left");
		this.clampL1 = forelegL.getChild("left_clamp1");
		this.clampL2 = forelegL.getChild("left_clamp2");
		this.clampL3 = forelegL.getChild("left_clamp3");

		ModelPart forelegR = this.legR.getChild("foreleg_right");
		this.clampR1 = forelegR.getChild("right_clamp1");
		this.clampR2 = forelegR.getChild("right_clamp2");
		this.clampR3 = forelegR.getChild("right_clamp3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

		PartDefinition head = part.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(32, 18)
						.addBox(-2.5F, -6.0F, -2.25F, 5.0F, 6.0F, 4.0F),
				PartPose.offset(0.0F, -8.0F, 0.75F));
		head.addOrReplaceChild("right_ear", CubeListBuilder.create()
						.texOffs(56, 41)
						.addBox(-2.0F, -5.0F, -1.0F, 3.0F, 5.0F, 0.0F),
				PartPose.offsetAndRotation(-2.5F, -1.0F, 0.5F, 0.0F, 0.0F, -0.3491F));
		head.addOrReplaceChild("left_ear", CubeListBuilder.create()
						.texOffs(30, 0)
						.addBox(-1.0F, -5.0F, -1.0F, 3.0F, 5.0F, 0.0F),
				PartPose.offsetAndRotation(2.5F, -1.0F, 0.5F, 0.0F, 0.0F, 0.3491F));
		head.addOrReplaceChild("spike_left", CubeListBuilder.create()
						.texOffs(0, 57)
						.addBox(-1.0F, -4.0F, -0.5F, 2.0F, 4.0F, 1.0F),
				PartPose.offsetAndRotation(1.75F, -4.5F, -2.0F, -0.1745F, -0.1735F, 0.7937F));
		head.addOrReplaceChild("spike_middle", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-1.0F, -5.0F, -0.5F, 2.0F, 5.0F, 1.0F),
				PartPose.offsetAndRotation(0.0F, -5.0F, -2.25F, -0.3491F, 0.0F, 0.0F));
		head.addOrReplaceChild("spike_right", CubeListBuilder.create()
						.texOffs(6, 57)
						.addBox(-1.0F, -4.0F, -0.5F, 2.0F, 4.0F, 1.0F),
				PartPose.offsetAndRotation(-1.75F, -4.5F, -2.0F, -0.1745F, 0.1735F, -0.7937F));
		PartDefinition flame = head.addOrReplaceChild("flame", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, -0.9553F, 0.0F, 0.0F));
		flame.addOrReplaceChild("flameRot", CubeListBuilder.create()
						.texOffs(32, 28)
						.addBox(-2.0F, -7.0F, -2.0F, 4.0F, 7.0F, 4.0F),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		part.addOrReplaceChild("neck", CubeListBuilder.create()
						.texOffs(12, 22)
						.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F),
				PartPose.offset(0.0F, -7.0F, 0.75F));

		PartDefinition torso = part.addOrReplaceChild("torso", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-6.0F, -3.0F, -4.25F, 12.0F, 6.0F, 6.0F),
				PartPose.offset(0.0F, -4.0F, 1.75F));
		PartDefinition right_fin = torso.addOrReplaceChild("right_fin", CubeListBuilder.create()
						.texOffs(28, 56)
						.addBox(-0.5F, -2.25F, -0.25F, 1.0F, 5.0F, 2.0F),
				PartPose.offset(-3.0F, -0.25F, 2.0F));
		right_fin.addOrReplaceChild("right_flame", CubeListBuilder.create()
						.texOffs(14, 5)
						.addBox(0.0F, -8.0F, -1.0F, 0.0F, 10.0F, 7.0F),
				PartPose.offset(0.0F, 0.5F, 1.25F));
		PartDefinition left_fin = torso.addOrReplaceChild("left_fin", CubeListBuilder.create()
						.texOffs(55, 55)
						.addBox(-0.5F, -2.25F, -0.25F, 1.0F, 5.0F, 2.0F),
				PartPose.offset(3.0F, -0.25F, 2.0F));
		left_fin.addOrReplaceChild("left_flame", CubeListBuilder.create()
						.texOffs(0, 5)
						.addBox(0.0F, -8.0F, -1.0F, 0.0F, 10.0F, 7.0F),
				PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition arm_left = part.addOrReplaceChild("arm_left", CubeListBuilder.create()
						.texOffs(32, 39)
						.addBox(0.0F, -0.5F, -1.0F, 3.0F, 7.0F, 3.0F),
				PartPose.offset(6.0F, -6.0F, 0.5F));
		PartDefinition forearm_left = arm_left.addOrReplaceChild("forearm_left", CubeListBuilder.create()
						.texOffs(36, 0)
						.addBox(-1.5F, 0.0F, -3.0F, 4.0F, 7.0F, 4.0F),
				PartPose.offset(1.5F, 6.5F, 1.5F));
		forearm_left.addOrReplaceChild("left_brace1", CubeListBuilder.create()
						.texOffs(12, 55)
						.addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 1.0F),
				PartPose.offset(0.5F, 2.0F, -2.5F));
		forearm_left.addOrReplaceChild("left_brace2", CubeListBuilder.create()
						.texOffs(52, 0)
						.addBox(0.0F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F),
				PartPose.offset(2.0F, 2.0F, -1.0F));
		forearm_left.addOrReplaceChild("left_brace3", CubeListBuilder.create()
						.texOffs(53, 35)
						.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 1.0F),
				PartPose.offset(0.5F, 2.0F, 0.5F));
		forearm_left.addOrReplaceChild("left_finger", CubeListBuilder.create()
						.texOffs(34, 57)
						.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F),
				PartPose.offsetAndRotation(1.2071F, 7.0F, -1.8358F, 0.0F, 0.7854F, 0.0F));
		forearm_left.addOrReplaceChild("left_thumb", CubeListBuilder.create()
						.texOffs(0, 22)
						.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F),
				PartPose.offset(-0.75F, 7.0F, -1.0F));

		PartDefinition arm_right = part.addOrReplaceChild("arm_right", CubeListBuilder.create()
						.texOffs(32, 39)
						.addBox(-3.0F, -0.5F, -1.0F, 3.0F, 7.0F, 3.0F),
				PartPose.offset(-6.0F, -6.0F, 0.5F));
		PartDefinition forearm_right = arm_right.addOrReplaceChild("forearm_right", CubeListBuilder.create()
						.texOffs(36, 0)
						.addBox(-2.5F, 0.0F, -3.0F, 4.0F, 7.0F, 4.0F),
				PartPose.offset(-1.5F, 6.5F, 1.5F));
		forearm_right.addOrReplaceChild("right_brace1", CubeListBuilder.create()
						.texOffs(12, 55)
						.addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 1.0F),
				PartPose.offset(-0.5F, 2.0F, -2.5F));
		forearm_right.addOrReplaceChild("right_brace2", CubeListBuilder.create()
						.texOffs(52, 0)
						.addBox(-1.0F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F),
				PartPose.offset(-2.0F, 2.0F, -1.0F));
		forearm_right.addOrReplaceChild("right_brace3", CubeListBuilder.create()
						.texOffs(53, 35)
						.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 1.0F),
				PartPose.offset(-0.5F, 2.0F, 0.5F));
		forearm_right.addOrReplaceChild("right_finger", CubeListBuilder.create()
						.texOffs(34, 57)
						.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F),
				PartPose.offsetAndRotation(-1.2071F, 7.0F, -1.8358F, 0.0F, -0.7854F, 0.0F));
		forearm_right.addOrReplaceChild("right_thumb", CubeListBuilder.create()
						.texOffs(0, 22)
						.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F),
				PartPose.offset(0.75F, 7.0F, -1.0F));

		part.addOrReplaceChild("waist", CubeListBuilder.create()
						.texOffs(16, 37).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 3.0F),
				PartPose.offset(0.0F, -1.0F, 2.0F));
		part.addOrReplaceChild("hip", CubeListBuilder.create()
						.texOffs(28, 12)
						.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 2.0F, 4.0F),
				PartPose.offset(0.0F, 5.0F, 0.5F));

		PartDefinition leg_left = part.addOrReplaceChild("leg_left", CubeListBuilder.create()
						.texOffs(13, 46)
						.addBox(-1.5F, 0.0F, -1.0F, 3.0F, 6.0F, 3.0F),
				PartPose.offset(2.0F, 7.0F, 0.0F));
		PartDefinition foreleg_left = leg_left.addOrReplaceChild("foreleg_left", CubeListBuilder.create()
						.texOffs(0, 22)
						.addBox(-1.5F, 0.0F, 0.0F, 4.0F, 11.0F, 4.0F),
				PartPose.offset(0.0F, 6.0F, -1.5F));
		foreleg_left.addOrReplaceChild("left_clamp1", CubeListBuilder.create()
						.texOffs(41, 49)
						.addBox(-1.5F, 0.0F, -0.5F, 3.0F, 7.0F, 1.0F),
				PartPose.offset(0.5F, 4.5F, 0.0F));
		foreleg_left.addOrReplaceChild("left_clamp2", CubeListBuilder.create()
						.texOffs(25, 46)
						.addBox(0.0F, 0.0F, -1.0F, 1.0F, 7.0F, 3.0F),
				PartPose.offset(2.0F, 4.5F, 1.5F));
		foreleg_left.addOrReplaceChild("left_clamp3", CubeListBuilder.create()
						.texOffs(33, 49)
						.addBox(-1.5F, 0.0F, 0.5F, 3.0F, 7.0F, 1.0F),
				PartPose.offset(0.5F, 4.5F, 3.0F));

		PartDefinition leg_right = part.addOrReplaceChild("leg_right", CubeListBuilder.create()
				.texOffs(0, 48)
				.addBox(-1.5F, 0.0F, -1.0F, 3.0F, 6.0F, 3.0F),
				PartPose.offset(-2.0F, 7.0F, 0.0F));
		PartDefinition foreleg_right = leg_right.addOrReplaceChild("foreleg_right", CubeListBuilder.create()
				.texOffs(16, 22)
				.addBox(-2.5F, 0.0F, 0.0F, 4.0F, 11.0F, 4.0F),
				PartPose.offset(0.0F, 6.0F, -1.5F));
		foreleg_right.addOrReplaceChild("right_clamp1", CubeListBuilder.create()
				.texOffs(50, 11)
				.addBox(-1.5F, 0.0F, -0.5F, 3.0F, 7.0F, 1.0F),
				PartPose.offset(-0.5F, 4.5F, 0.0F));
		foreleg_right.addOrReplaceChild("right_clamp2", CubeListBuilder.create()
				.texOffs(48, 25)
				.addBox(-1.0F, 0.0F, -1.0F, 1.0F, 7.0F, 3.0F),
				PartPose.offset(-2.0F, 4.5F, 1.5F));
		foreleg_right.addOrReplaceChild("right_clamp3", CubeListBuilder.create()
				.texOffs(49, 49)
				.addBox(-1.5F, 0.0F, 0.5F, 3.0F, 7.0F, 1.0F),
				PartPose.offset(-0.5F, 4.5F, 3.0F));

		return LayerDefinition.create(mesh, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (entity.getState() == 1) {
			float f = ageInTicks * (float)Math.PI * 0.2F;
			float xrot = Mth.sin(f * 2.0F) * 0.3F;

			this.flame.xRot = xrot - 0.9553F;
			this.flameRot.yRot = f;

			this.flameL.xRot = xrot;
			this.flameR.xRot = xrot;

			this.setFins(0.7854F);
			this.setBraces(0.0873F, 2.0F, 4.0F);
		} else {
			float f = ageInTicks * (float)Math.PI * 0.1F;
			float xrot = Mth.sin(f * 2.0F) * 0.3F;

			this.flame.xRot = xrot - 0.9553F;
			this.flameRot.yRot = f;

			this.flameL.xRot = xrot;
			this.flameR.xRot = xrot;

			this.setFins(0.0F);
			this.setBraces(0.0F, 2.5F, 4.5F);
		}
	}

	private void setFins(float val) {
		this.finL.yRot = val;
		this.finR.yRot = -val;
	}

	private void setBraces(float val, float brace, float clamp) {
		this.braceL1.y = brace;
		this.braceL1.xRot = -val;
		this.braceL2.y = brace;
		this.braceL2.zRot = -val;
		this.braceL3.y = brace;
		this.braceL3.xRot = val;
		this.braceR1.y = brace;
		this.braceR1.xRot = -val;
		this.braceR2.y = brace;
		this.braceR2.zRot = val;
		this.braceR3.y = brace;
		this.braceR3.xRot = val;

		this.clampL1.y = clamp;
		this.clampL1.xRot = -val;
		this.clampL2.y = clamp;
		this.clampL2.zRot = -val;
		this.clampL3.y = clamp;
		this.clampL3.xRot = val;
		this.clampR1.y = clamp;
		this.clampR1.xRot = -val;
		this.clampR2.y = clamp;
		this.clampR2.zRot = val;
		this.clampR3.y = clamp;
		this.clampR3.xRot = val;
	}
}