package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class OrnamentalBlockModelProvider extends BlockModelProvider {

    public OrnamentalBlockModelProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);
    }

    public BlockModelBuilder fencePostColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "fence_post_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "fence_gate_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateOpenColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "fence_gate_open_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateWallColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "fence_gate_wall_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateWallOpenColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "fence_gate_wall_open_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder poleCornerColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "pole_corner_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder slabVerticalColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "slab_vertical_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder poleCrossColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "pole_cross_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder stairsStraightSideColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "stairs_straight_side_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder beamCornerColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "beam_corner_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder beamCrossColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "beam_cross_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder wallPostColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "wall_post_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder wallSideColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "wall_side_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder wallSideTallColumn(String name, ResourceLocation side, ResourceLocation end) {
        return parent(name, "wall_side_tall_column")
                .texture("side", side)
                .texture("end", end);
    }

    public BlockModelBuilder saddleDoor(String name, ResourceLocation texture) {
        return parent(name, "saddle_door")
                .texture("texture", texture);
    }

    public BlockModelBuilder saddleDoorHinge(String name, ResourceLocation texture) {
        return parent(name, "saddle_door")
                .texture("texture", texture);
    }

    private BlockModelBuilder parent(String name, String parent) {
        return withExistingParent(name, new ResourceLocation(OrnamentalMod.MODID, "block/util/" + parent));
    }
}
