package com.androsa.ornamental.entity.model.renderstate;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.world.level.block.state.BlockState;

public class FlowerGolemRenderState extends GolemRenderState {
    public BlockState flower;
    public BlockModelRenderState flowerModel = new BlockModelRenderState();
}
