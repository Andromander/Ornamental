package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.IceGolem;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SnowGolemHeadLayer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;

public class IceGolemRenderer<T extends IceGolem> extends MobRenderer<T, SnowGolemRenderState, SnowGolemModel> {

    private final String entityName;
    private final BlockModelResolver blockModelResolver;
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

    public IceGolemRenderer(EntityRendererProvider.Context manager, ModelLayerLocation model, String texture) {
        super(manager, new SnowGolemModel(manager.bakeLayer(model)), 0.5F);
        this.addLayer(new SnowGolemHeadLayer(this));
        this.entityName = texture;
        this.blockModelResolver = manager.getBlockModelResolver();
    }

    @Override
    public SnowGolemRenderState createRenderState() {
        return new SnowGolemRenderState();
    }

    @Override
    public void extractRenderState(T entity, SnowGolemRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        if (entity.isPumpkinEquipped()) {
            this.blockModelResolver.update(state.headBlock, Blocks.CARVED_PUMPKIN.defaultBlockState(), BLOCK_DISPLAY_CONTEXT);
        } else {
            state.headBlock.clear();
        }
    }

    @Override
    public Identifier getTextureLocation(SnowGolemRenderState entity) {
        return Identifier.fromNamespaceAndPath(OrnamentalMod.MODID, "textures/entity/" + entityName + ".png");
    }
}
