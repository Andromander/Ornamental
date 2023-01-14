package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.projectile.ChargeBall;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class ChargeBallRenderer<T extends ChargeBall> extends EntityRenderer<T> {
    private static final ResourceLocation LOCATION = new ResourceLocation(OrnamentalMod.MODID, "textures/particle/charge_spark.png");
    private static final RenderType RENDER = RenderType.entityCutoutNoCull(LOCATION);

    public ChargeBallRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected int getBlockLightLevel(T entity, BlockPos pos) {
        return 15;
    }

    @Override
    public void render(T entity, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
        stack.pushPose();
        stack.scale(1.0F, 1.0F, 1.0F);
        stack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        stack.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose lastpose = stack.last();
        Matrix4f pose = lastpose.pose();
        Matrix3f normal = lastpose.normal();
        VertexConsumer consumer = buffer.getBuffer(RENDER);

        vertex(consumer, pose, normal, light, 0.0F, 0, 0, 1);
        vertex(consumer, pose, normal, light, 1.0F, 0, 1, 1);
        vertex(consumer, pose, normal, light, 1.0F, 1, 1, 0);
        vertex(consumer, pose, normal, light, 0.0F, 1, 0, 0);

        stack.popPose();
        super.render(entity, yaw, partialTicks, stack, buffer, light);
    }

    private static void vertex(VertexConsumer consumer, Matrix4f pose, Matrix3f normal, int light, float x, int y, int texX, int texY) {
        consumer.vertex(pose, x - 0.5F, y - 0.25F, 0.0F)
                .color(255, 255, 255, 255)
                .uv(texX, texY)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(normal, 0.0F, 1.0F, 0.0F)
                .endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return LOCATION;
    }
}
