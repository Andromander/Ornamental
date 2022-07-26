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

    public BlockModelBuilder fencePost(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "fence_post")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder fenceGate(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "fence_gate")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder fenceGateOpen(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "fence_gate_open")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder fenceGateWall(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "fence_gate_wall")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder fenceGateWallOpen(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "fence_gate_wall_open")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder doorBottomLeftO(String name, ResourceLocation side, ResourceLocation bottom) {
        return parent(name, "door_bottom_left")
                .texture("side", side)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder doorBottomLeftOpenO(String name, ResourceLocation side, ResourceLocation bottom) {
        return parent(name, "door_bottom_left_open")
                .texture("side", side)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder doorBottomRightO(String name, ResourceLocation side, ResourceLocation bottom) {
        return parent(name, "door_bottom_right")
                .texture("side", side)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder doorBottomRightOpenO(String name, ResourceLocation side, ResourceLocation bottom) {
        return parent(name, "door_bottom_right_open")
                .texture("side", side)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder doorTopLeftO(String name, ResourceLocation side, ResourceLocation top) {
        return parent(name, "door_top_left")
                .texture("side", side)
                .texture("top", top);
    }

    public BlockModelBuilder doorTopLeftOpenO(String name, ResourceLocation side, ResourceLocation top) {
        return parent(name, "door_top_left_open")
                .texture("side", side)
                .texture("top", top);
    }

    public BlockModelBuilder doorTopRightO(String name, ResourceLocation side, ResourceLocation top) {
        return parent(name, "door_top_right")
                .texture("side", side)
                .texture("top", top);
    }

    public BlockModelBuilder doorTopRightOpenO(String name, ResourceLocation side, ResourceLocation top) {
        return parent(name, "door_top_right_open")
                .texture("side", side)
                .texture("top", top);
    }

    public BlockModelBuilder poleCorner(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "pole_corner")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder slabVertical(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "slab_vertical")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder poleCross(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "pole_cross")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder stairsStraightSide(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "stairs_straight_side")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder beamCorner(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "beam_corner")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder beamCross(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "beam_cross")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder wallPost(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "wall_post")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder wallSide(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "wall_side")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder wallSideTall(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "wall_side_tall")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder saddleDoorLeft(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "saddle_door_left")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder saddleDoorLeftOpen(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "saddle_door_left_open")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder saddleDoorRight(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "saddle_door_right")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder saddleDoorRightOpen(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "saddle_door_right_open")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    private BlockModelBuilder parent(String name, String parent) {
        return withExistingParent(name, new ResourceLocation(OrnamentalMod.MODID, "block/util/" + parent));
    }
}
