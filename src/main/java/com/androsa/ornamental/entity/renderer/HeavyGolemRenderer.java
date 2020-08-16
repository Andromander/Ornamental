package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.AbstractGolemEntity;
import com.androsa.ornamental.entity.model.AbstractGolemModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HeavyGolemRenderer<T extends AbstractGolemEntity, M extends AbstractGolemModel<T>> extends AbstractGolemRenderer<T,M> {

    public HeavyGolemRenderer(EntityRendererManager manager, M model, float shadow) {
        super(manager, model, shadow);
    }

    @Override
    protected void applyRotations(T entity, MatrixStack stack, float rotationPitch, float rotationYaw, float partialTicks) {
        super.applyRotations(entity, stack, rotationPitch, rotationYaw, partialTicks);
        if (!((double)entity.limbSwingAmount < 0.01D)) {
            float f1 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            stack.rotate(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
