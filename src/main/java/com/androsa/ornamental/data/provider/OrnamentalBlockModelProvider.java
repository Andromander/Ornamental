package com.androsa.ornamental.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class OrnamentalBlockModelProvider extends BlockModelProvider {

    private static final String ORNAMENT_MODELS = "ornamental:block/util/";

    public OrnamentalBlockModelProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);
    }

    public BlockModelBuilder fencePostColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "fence_post_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "fence_gate_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateOpenColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "fence_gate_open_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateWallColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "fence_gate_wall_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateWallOpenColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "fence_gate_wall_open_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder poleCorner(String name, ResourceLocation texture) {
        return withExistingParent(name, ORNAMENT_MODELS + "pole_corner")
                .texture("texture", texture);
    }

    public BlockModelBuilder poleCornerColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "pole_corner_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder slabVertical(String name, ResourceLocation texture) {
        return withExistingParent(name, ORNAMENT_MODELS + "slab_vertical")
                .texture("texture", texture);
    }

    public BlockModelBuilder slabVerticalColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "slab_vertical_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder poleCross(String name, ResourceLocation texture) {
        return withExistingParent(name, ORNAMENT_MODELS + "pole_cross")
                .texture("texture", texture);
    }

    public BlockModelBuilder poleCrossColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "pole_cross_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder stairsStraightSide(String name, ResourceLocation texture) {
        return withExistingParent(name, ORNAMENT_MODELS + "stairs_straight_side")
                .texture("texture", texture);
    }

    public BlockModelBuilder stairsStraightSideColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "stairs_straight_side_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder beamCorner(String name, ResourceLocation texture) {
        return withExistingParent(name, ORNAMENT_MODELS + "beam_corner")
                .texture("texture", texture);
    }

    public BlockModelBuilder beamCornerColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "beam_corner_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder beamCross(String name, ResourceLocation texture) {
        return withExistingParent(name, ORNAMENT_MODELS + "beam_cross")
                .texture("texture", texture);
    }

    public BlockModelBuilder beamCrossColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "beam_cross_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder wallPostColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "wall_post_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder wallSideColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "wall_side_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder wallSideTallColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "wall_side_tall_column")
                .texture("side", side)
                .texture("end", end);
    }
}
