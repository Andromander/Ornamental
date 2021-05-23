package com.androsa.ornamental.entity.renderer.layer;

import com.androsa.ornamental.entity.IceGolemEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IceGolemHeadLayer<T extends IceGolemEntity> extends LayerRenderer<T, SnowManModel<T>> {

    public IceGolemHeadLayer(IEntityRenderer<T, SnowManModel<T>> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int light, T entity, float v1, float v2, float v3, float v4, float v5, float v6) {
        if (!entity.isInvisible() && entity.isPumpkinEquipped()) {
            stack.pushPose();
            this.getParentModel().getHead().translateAndRotate(stack);
            stack.translate(0.0D, -0.34375D, 0.0D);
            stack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            stack.scale(0.625F, -0.625F, -0.625F);
            ItemStack itemstack = new ItemStack(Blocks.CARVED_PUMPKIN);
            Minecraft.getInstance().getItemRenderer().renderStatic(entity, itemstack, ItemCameraTransforms.TransformType.HEAD, false, stack, buffer, entity.level, light, LivingRenderer.getOverlayCoords(entity, 0.0F));
            stack.popPose();
        }
    }
}
