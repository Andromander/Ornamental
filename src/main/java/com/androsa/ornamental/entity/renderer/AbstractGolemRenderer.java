package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.OrnamentalGolem;
import com.androsa.ornamental.entity.model.AbstractGolemModel;
import com.androsa.ornamental.entity.model.renderstate.GolemRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public abstract class AbstractGolemRenderer<T extends OrnamentalGolem, S extends GolemRenderState, M extends AbstractGolemModel<S>> extends MobRenderer<T, S, M> {

    private final String entityName;
    private final boolean isHeavy;

    public AbstractGolemRenderer(EntityRendererProvider.Context manager, M model, String texture, float shadow, boolean heavy) {
        super(manager, model, shadow);
        this.entityName = texture;
        this.isHeavy = heavy;
    }

    @Override
    public void extractRenderState(T entity, S state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.attackTicksRemaining = entity.getAttackTimer() > 0.0F ? (float)entity.getAttackTimer() - partialTicks : 0.0F;
    }

    @Override
    public Identifier getTextureLocation(GolemRenderState entity) {
        return Identifier.fromNamespaceAndPath(OrnamentalMod.MODID, "textures/entity/" + entityName + ".png");
    }

    @Override
    protected void setupRotations(S state, PoseStack stack, float pitch, float yaw) {
        super.setupRotations(state, stack, pitch, yaw);
        if (isHeavy) {
            if (!((double)state.walkAnimationSpeed < 0.01D)) {
                float f1 = state.walkAnimationPos + 6.0F;
                float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
                stack.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
            }
        }
    }
}
