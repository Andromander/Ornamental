package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.IceGolemEntity;
import com.androsa.ornamental.entity.renderer.layer.IceGolemHeadLayer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IceGolemRenderer<T extends IceGolemEntity> extends MobRenderer<T, SnowManModel<T>> {

    public IceGolemRenderer(EntityRendererManager manager) {
        super(manager, new SnowManModel<>(), 0.5F);
        this.addLayer(new IceGolemHeadLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return new ResourceLocation(OrnamentalMod.MODID, "textures/entity/" + entity.getType().getRegistryName().getPath() + ".png");
    }
}
