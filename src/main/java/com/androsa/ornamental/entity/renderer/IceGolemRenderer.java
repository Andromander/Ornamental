package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.IceGolem;
import com.androsa.ornamental.entity.renderer.layer.IceGolemHeadLayer;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class IceGolemRenderer<T extends IceGolem> extends MobRenderer<T, SnowGolemModel<T>> {

    private String entityName;

    public IceGolemRenderer(EntityRendererProvider.Context manager, ModelLayerLocation model) {
        super(manager, new SnowGolemModel<>(manager.bakeLayer(model)), 0.5F);
        this.addLayer(new IceGolemHeadLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        if (entityName == null) {
            entityName = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getPath();
        }
        return new ResourceLocation(OrnamentalMod.MODID, "textures/entity/" + entityName + ".png");
    }
}
