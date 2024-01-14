package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public abstract class OrnamentalBlockModelProvider extends BlockModelProvider {

    public OrnamentalBlockModelProvider(PackOutput output, String modid, ExistingFileHelper helper) {
        super(output, modid, helper);
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

    public BlockModelBuilder poleWhole(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "pole_whole")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder poleHorizon(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "pole_horizontal")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder poleVertical(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "pole_vertical")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder poleCorner(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "pole_corner")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder beamWhole(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "beam_whole")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder beamHorizontal(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "beam_horizontal")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder beamVertical(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "beam_vertical")
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

    public BlockModelBuilder supportBase(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "support_base")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder supportBaseTop(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "support_base_top")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder supportVertical(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "support_vertical")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder supportVerticalTop(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "support_vertical_top")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder supportHorizontalX(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "support_horizontal_x")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder supportHorizontalXTop(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "support_horizontal_x_top")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder supportHorizontalZ(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "support_horizontal_z")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder supportHorizontalZTop(String name, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        return parent(name, "support_horizontal_z_top")
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public BlockModelBuilder forceRenderType(String name, ResourceLocation parent, ResourceLocation renderType) {
        return withExistingParent(name, parent).renderType(renderType);
    }

    private BlockModelBuilder parent(String name, String parent) {
        return withExistingParent(name, new ResourceLocation(OrnamentalMod.MODID, "block/util/" + parent));
    }
}
