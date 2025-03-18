package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.IceGolem;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SnowGolemHeadLayer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.resources.ResourceLocation;

public class IceGolemRenderer<T extends IceGolem> extends MobRenderer<T, SnowGolemRenderState, SnowGolemModel> {

    private final String entityName;

    public IceGolemRenderer(EntityRendererProvider.Context manager, ModelLayerLocation model, String texture) {
        super(manager, new SnowGolemModel(manager.bakeLayer(model)), 0.5F);
        this.addLayer(new SnowGolemHeadLayer(this, manager.getBlockRenderDispatcher()));
        this.entityName = texture;
    }

    @Override
    public SnowGolemRenderState createRenderState() {
        return new SnowGolemRenderState();
    }

    @Override
    public void extractRenderState(T entity, SnowGolemRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.hasPumpkin = entity.isPumpkinEquipped();
    }

    @Override
    public ResourceLocation getTextureLocation(SnowGolemRenderState entity) {
        return ResourceLocation.fromNamespaceAndPath(OrnamentalMod.MODID, "textures/entity/" + entityName + ".png");
    }
}
