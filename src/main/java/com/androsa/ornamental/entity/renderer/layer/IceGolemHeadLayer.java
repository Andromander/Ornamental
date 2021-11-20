package com.androsa.ornamental.entity.renderer.layer;

import com.androsa.ornamental.entity.IceGolem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IceGolemHeadLayer<T extends IceGolem> extends RenderLayer<T, SnowGolemModel<T>> {

    public IceGolemHeadLayer(RenderLayerParent<T, SnowGolemModel<T>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource buffer, int light, T entity, float v1, float v2, float v3, float v4, float v5, float v6) {
        if (!entity.isInvisible() && entity.isPumpkinEquipped()) {
            stack.pushPose();
            this.getParentModel().getHead().translateAndRotate(stack);
            stack.translate(0.0D, -0.34375D, 0.0D);
            stack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            stack.scale(0.625F, -0.625F, -0.625F);
            ItemStack itemstack = new ItemStack(Blocks.CARVED_PUMPKIN);
            Minecraft.getInstance().getItemRenderer().renderStatic(entity, itemstack, ItemTransforms.TransformType.HEAD, false, stack, buffer, entity.level, light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), entity.getId());
            stack.popPose();
        }
    }
}
