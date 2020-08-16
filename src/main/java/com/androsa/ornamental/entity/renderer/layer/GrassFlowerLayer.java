package com.androsa.ornamental.entity.renderer.layer;

import com.androsa.ornamental.entity.GrassGolemEntity;
import com.androsa.ornamental.entity.model.DirtGolemModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class GrassFlowerLayer<T extends GrassGolemEntity, M extends DirtGolemModel<T>> extends LayerRenderer<T, M> {

    public GrassFlowerLayer(IEntityRenderer<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int light, T entity, float v1, float v2, float v3, float v4, float v5, float v6) {
        if (!entity.isInvisible() && entity.hasPoppy()) {
            stack.push();
            this.getEntityModel().getHead().translateRotate(stack);
            stack.translate(0.0D, -0.43D, -0.05D);
            stack.rotate(Vector3f.YP.rotationDegrees(-78.0F));
            stack.scale(-0.5F, -0.5F, 0.5F);
            stack.translate(-0.5D, -0.5D, -0.5D);
            Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(Blocks.POPPY.getDefaultState(), stack, buffer, light, LivingRenderer.getPackedOverlay(entity, 0.0F), EmptyModelData.INSTANCE);
            stack.pop();
        }
    }
}
