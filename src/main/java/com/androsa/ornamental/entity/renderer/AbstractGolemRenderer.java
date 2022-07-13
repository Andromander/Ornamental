package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.OrnamentalGolem;
import com.androsa.ornamental.entity.model.AbstractGolemModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class AbstractGolemRenderer<T extends OrnamentalGolem, M extends AbstractGolemModel<T>> extends MobRenderer<T,M> {

    private String entityName;

    public AbstractGolemRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, shadow);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        if (entityName == null) {
            entityName = ForgeRegistries.ENTITY_TYPES.getRegistryName().getPath();
        }
        return new ResourceLocation(OrnamentalMod.MODID, "textures/entity/" + entityName + ".png");
    }
}
