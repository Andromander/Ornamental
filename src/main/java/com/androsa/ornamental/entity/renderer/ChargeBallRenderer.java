package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.projectile.ChargeBall;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

public class ChargeBallRenderer extends EntityRenderer<ChargeBall, EntityRenderState> {
    private static final Identifier LOCATION = Identifier.fromNamespaceAndPath(OrnamentalMod.MODID, "textures/particle/charge_spark.png");
    private static final RenderType RENDER = RenderTypes.entityCutoutNoCull(LOCATION);

    public ChargeBallRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    @Override
    protected int getBlockLightLevel(ChargeBall entity, BlockPos pos) {
        return 15;
    }

    @Override
    public void submit(EntityRenderState entity, PoseStack stack, SubmitNodeCollector buffer, CameraRenderState camera) {
        stack.pushPose();
        stack.scale(1.0F, 1.0F, 1.0F);
        stack.mulPose(camera.orientation);
        buffer.submitCustomGeometry(stack, RENDER, (pose, consumer) -> {
            vertex(consumer, pose, entity.lightCoords, 0.0F, 0, 0, 1);
            vertex(consumer, pose, entity.lightCoords, 1.0F, 0, 1, 1);
            vertex(consumer, pose, entity.lightCoords, 1.0F, 1, 1, 0);
            vertex(consumer, pose, entity.lightCoords, 0.0F, 1, 0, 0);
        });

        stack.popPose();
        super.submit(entity, stack, buffer, camera);
    }

    private static void vertex(VertexConsumer consumer, PoseStack.Pose pose, int light, float x, int y, int texX, int texY) {
        consumer.addVertex(pose, x - 0.5F, y - 0.25F, 0.0F)
                .setColor(255, 255, 255, 255)
                .setUv(texX, texY)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, 0.0F, 1.0F, 0.0F);
    }
}
