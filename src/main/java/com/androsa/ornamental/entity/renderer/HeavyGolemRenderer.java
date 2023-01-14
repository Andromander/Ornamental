package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.OrnamentalGolem;
import com.androsa.ornamental.entity.model.AbstractGolemModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class HeavyGolemRenderer<T extends OrnamentalGolem, M extends AbstractGolemModel<T>> extends AbstractGolemRenderer<T,M> {

    public HeavyGolemRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, shadow);
    }

    @Override
    protected void setupRotations(T entity, PoseStack stack, float rotationPitch, float rotationYaw, float partialTicks) {
        super.setupRotations(entity, stack, rotationPitch, rotationYaw, partialTicks);
        if (!((double)entity.animationSpeed < 0.01D)) {
            float f1 = entity.animationPosition - entity.animationSpeed * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            stack.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
