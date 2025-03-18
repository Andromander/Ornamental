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
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class ChargeBallRenderer extends EntityRenderer<ChargeBall, EntityRenderState> {
    private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(OrnamentalMod.MODID, "textures/particle/charge_spark.png");
    private static final RenderType RENDER = RenderType.entityCutoutNoCull(LOCATION);

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
    public void render(EntityRenderState entity, PoseStack stack, MultiBufferSource buffer, int light) {
        stack.pushPose();
        stack.scale(1.0F, 1.0F, 1.0F);
        stack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        stack.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose lastpose = stack.last();
        VertexConsumer consumer = buffer.getBuffer(RENDER);

        vertex(consumer, lastpose, light, 0.0F, 0, 0, 1);
        vertex(consumer, lastpose, light, 1.0F, 0, 1, 1);
        vertex(consumer, lastpose, light, 1.0F, 1, 1, 0);
        vertex(consumer, lastpose, light, 0.0F, 1, 0, 0);

        stack.popPose();
        super.render(entity, stack, buffer, light);
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
