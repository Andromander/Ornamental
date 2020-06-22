package com.androsa.nifty.entity.renderer.layer;

import com.androsa.nifty.entity.FlowerGolemEntity;
import com.androsa.nifty.entity.model.FlowerGolemModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class GolemFlowerLayer<T extends FlowerGolemEntity, M extends FlowerGolemModel<T>> extends LayerRenderer<T, M> {

    public GolemFlowerLayer(IEntityRenderer<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int i, T entity, float v1, float v2, float v3, float v4, float v5, float v6) {
        double[] pos = entity.getFlowerPos();
        if (entity.getHoldFlowerTick() != 0) {
            stack.push();
            ModelRenderer modelrenderer = this.getEntityModel().getArmHoldingRose();
            modelrenderer.rotate(stack);
            stack.translate(pos[0], pos[1], pos[2]);
            stack.translate(0.5D, 0.5D, 0.5D);
            stack.scale(0.5F, 0.5F, 0.5F);
            stack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-90.0F));
            stack.translate(-0.5D, -0.5D, -0.5D);
            Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(entity.getFlower(), stack, buffer, i, OverlayTexture.DEFAULT_UV, EmptyModelData.INSTANCE);
            stack.pop();
        }
    }
}
