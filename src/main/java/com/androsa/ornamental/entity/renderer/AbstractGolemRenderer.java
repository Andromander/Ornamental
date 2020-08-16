package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.AbstractGolemEntity;
import com.androsa.ornamental.entity.model.AbstractGolemModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AbstractGolemRenderer<T extends AbstractGolemEntity, M extends AbstractGolemModel<T>> extends MobRenderer<T,M> {

    public AbstractGolemRenderer(EntityRendererManager manager, M model, float shadow) {
        super(manager, model, shadow);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return new ResourceLocation(OrnamentalMod.MODID, "textures/entity/" + entity.getType().getRegistryName().getPath() + ".png");
    }
}
