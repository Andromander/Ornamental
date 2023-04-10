package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.MagmaGolem;
import com.androsa.ornamental.entity.model.MagmaGolemModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class MagmaGolemRenderer<T extends MagmaGolem, M extends MagmaGolemModel<T>> extends AbstractGolemRenderer<T,M> {

    public static final String HEATED_TEXTURE = makeTexturePath("heated");
    public static final String COOLED_TEXTURE = makeTexturePath("cooled");

    public MagmaGolemRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, shadow);
    }

    private static String makeTexturePath(String type) {
        return "textures/entity/magma_golem/magma_golem_" + type + ".png";
    }

    @Override
    protected int getBlockLightLevel(T entity, BlockPos pos) {
        return switch (entity.getState()) {
            case 0 -> Math.max(super.getBlockLightLevel(entity, pos), 7);
            case 1 -> 15;
            default -> super.getBlockLightLevel(entity, pos);
        };
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return switch (entity.getState()) {
            case 1 -> getLocation(HEATED_TEXTURE);
            case 2 -> getLocation(COOLED_TEXTURE);
            default -> getLocation("textures/entity/magma_golem/magma_golem.png");
        };
    }

    private static ResourceLocation getLocation(String path) {
        return new ResourceLocation(OrnamentalMod.MODID, path);
    }
}
