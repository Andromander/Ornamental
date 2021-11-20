package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.QuartzGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * QuartzGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
public class QuartzGolemModel<T extends QuartzGolem> extends AbstractGolemModel<T> {
    public ModelPart tail1;
    public ModelPart tail2;
    public ModelPart tail3;

    public QuartzGolemModel(ModelPart root) {
        super(root, true, false, false);

        ModelPart torso = root.getChild("torso");
        ModelPart body = torso.getChild("body");
        this.tail1 = body.getChild("tail1");
        this.tail2 = tail1.getChild("tail2");
        this.tail3 = tail2.getChild("tail3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition headdef = part.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(14, 16)
                        .addBox(-3.0F, -8.0F, -3.5F, 6, 8, 6),
                PartPose.offset(0.0F, -4.0F, -1.5F));
        headdef.addOrReplaceChild("eye", CubeListBuilder.create()
                        .texOffs(32, 16)
                        .addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2),
                PartPose.offset(0.0F, -4.0F, -5.0F));

        PartDefinition torsodef = part.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-8.0F, 0.0F, -3.5F, 16, 9, 7),
                PartPose.offset(0.0F, -4.0F, 0.0F));
        PartDefinition bodydef = torsodef.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(14, 30)
                        .addBox(-6.0F, 0.0F, -2.5F, 12, 4, 5),
                PartPose.offset(0.0F, 9.0F, 0.0F));
        PartDefinition tail1def = bodydef.addOrReplaceChild("tail1", CubeListBuilder.create()
                        .texOffs(43, 24)
                        .addBox(-3.4F, 0.0F, -1.5F, 7, 6, 3),
                PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.3490658503988659F, 0.0F, 0.0F));
        PartDefinition tail2def = tail1def.addOrReplaceChild("tail2", CubeListBuilder.create()
                        .texOffs(48, 33)
                        .addBox(-2.0F, 0.0F, -1.0F, 4, 7, 2),
                PartPose.offsetAndRotation(0.0F, 5.5F, 0.0F, 0.3490658503988659F, 0.0F, 0.0F));
        tail2def.addOrReplaceChild("tail3", CubeListBuilder.create()
                        .texOffs(12, 39)
                        .addBox(-1.5F, 0.0F, -1.0F, 3, 5, 2),
                PartPose.offsetAndRotation(0.0F, 6.5F, 0.0F, 0.3490658503988659F, 0.0F, 0.0F));

        part.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(46, 0)
                        .addBox(0.0F, 0.0F, -2.0F, 3, 20, 4),
                PartPose.offset(8.0F, -3.5F, 0.0F));
        part.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(-3.0F, 0.0F, -2.0F, 3, 20, 4),
                PartPose.offset(-8.0F, -3.5F, 0.0F));

        return LayerDefinition.create(mesh, 64, 48);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.tail1.xRot = Mth.sin(ageInTicks * 0.067F) * 0.1F + 0.3490658503988659F;
        this.tail2.xRot = Mth.sin(ageInTicks * 0.067F) * 0.1F + 0.3490658503988659F;
        this.tail3.xRot = Mth.sin(ageInTicks * 0.067F) * 0.1F + 0.3490658503988659F;
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        //TODO: Wave arms gently because we are flying. Don't swing
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
    }
}
