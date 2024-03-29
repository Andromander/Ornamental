package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.blocks.OrnamentBeam;
import com.androsa.ornamental.blocks.OrnamentPole;
import com.androsa.ornamental.blocks.OrnamentSaddleDoor;
import com.androsa.ornamental.blocks.OrnamentSupport;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public abstract class OrnamentalBlockStateProvider extends BlockStateProvider {

    private final OrnamentalBlockModelProvider blockModels;
    private final String parentID;

    public static final ResourceLocation SOLID = new ResourceLocation("solid");
    public static final ResourceLocation TRANSLUCENT = new ResourceLocation("translucent");
    public static final ResourceLocation CUTOUT = new ResourceLocation("cutout");
    public static final ResourceLocation CUTOUT_MIPPED = new ResourceLocation("cutout_mipped");

    public OrnamentalBlockStateProvider(PackOutput output, String modid, String parent, ExistingFileHelper helper) {
        super(output, modid, helper);
        parentID = parent;

        blockModels = new OrnamentalBlockModelProvider(output, modid, helper) {
            @Override
            protected void registerModels() { }

            @Override
            public String getName() {
                return OrnamentalBlockStateProvider.this.getName();
            }
        };
    }

    @Nonnull
    @Override
    public String getName() {
        return "Ornamental Blockstates and Block Models";
    }

    public String parentLoc() {
        return parentID;
    }

    @Override
    public OrnamentalBlockModelProvider models() {
        return blockModels;
    }

    protected ResourceLocation locMod(String name) {
        return modLoc("block/" + name);
    }

    protected ResourceLocation locParent(String name) {
        return new ResourceLocation(parentLoc(), "block/" + name);
    }

    protected String getKey(Supplier<? extends Block> block) {
        return BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
    }

    /* Stairs */
    public void stairsBasic(Supplier<? extends StairBlock> block, String name) {
        stairsBasic(block, name, SOLID);
    }

    public void stairsBasic(Supplier<? extends StairBlock> block, String name, ResourceLocation type) {
        stairsBasic(block, locParent(name), type);
    }

    public void stairsBasic(Supplier<? extends StairBlock> block, ResourceLocation name, ResourceLocation type) {
        stairs(block, name, name, name, type);
    }

    public void stairsColumn(Supplier<? extends StairBlock> block, String side, String end) {
        stairs(block, locParent(side), locParent(end), locParent(end), SOLID);
    }

    public void stairs(Supplier<? extends StairBlock> block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top, ResourceLocation type) {
        if (type == SOLID) {
            stairsBlock(block.get(), side, bottom, top);
        } else {
            stairsBlockWithRenderType(block.get(), side, bottom, top, type);
        }
    }

    /* Slabs */
    public void slabBasic(Supplier<? extends SlabBlock> block, String name) {
        slabBasic(block, name, SOLID);
    }

    public void slabBasic(Supplier<? extends SlabBlock> block, String name, ResourceLocation type) {
        slabBasic(block, locParent(name), type);
    }

    public void slabBasic(Supplier<? extends SlabBlock> block, ResourceLocation name, ResourceLocation type) {
        slab(block, name, name, name, name, type);
    }

    public void slabModel(Supplier<? extends SlabBlock> block, String model, String name) {
        slab(block, locParent(model), locParent(name), locParent(name), locParent(name), SOLID);
    }

    public void slabColumn(Supplier<? extends SlabBlock> block, String blockname, String side, String end) {
        slab(block, locParent(blockname), locParent(side), locParent(end), locParent(end), SOLID);
    }

    public void slab(Supplier<? extends SlabBlock> block, ResourceLocation model, ResourceLocation side, ResourceLocation bottom, ResourceLocation top, ResourceLocation type) {
        ModelFile slab, slabTop, doubleSlab;
        if (type == SOLID) {
            slab = models().slab(getKey(block), side, bottom, top);
            slabTop = models().slabTop(getKey(block) + "_top", side, bottom, top);
            doubleSlab = models().getExistingFile(model);
        } else {
            slab = models().slab(getKey(block), side, bottom, top).renderType(type);
            slabTop = models().slabTop(getKey(block) + "_top", side, bottom, top).renderType(type);
            doubleSlab = models().forceRenderType(model.getPath(), model, type);
        }
        slabBlock(block.get(), slab, slabTop, doubleSlab);
    }

    /* Fences */
    public void fenceBasic(Supplier<? extends FenceBlock> block, String name) {
        fenceBasic(block, name, SOLID);
    }

    public void fenceBasic(Supplier<? extends FenceBlock> block, String name, ResourceLocation type) {
        fenceBasic(block, locParent(name), type);
    }

    public void fenceBasic(Supplier<? extends FenceBlock> block, ResourceLocation name, ResourceLocation type) {
        fence(block, name, name, name, type);
    }

    public void fenceColumn(Supplier<? extends FenceBlock> block, String side, String top) {
        fence(block, locParent(side), locParent(top), locParent(top), SOLID);
    }

    public void fence(Supplier<? extends FenceBlock> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom, ResourceLocation type) {
        if (type == SOLID) {
            fourWayBlock(block.get(),
                    models().fencePost(getKey(block) + "_post", side, top, bottom),
                    models().fenceSide(getKey(block) + "_side", side));
        } else {
            fourWayBlock(block.get(),
                    models().fencePost(getKey(block) + "_post", side, top, bottom).renderType(type),
                    models().fenceSide(getKey(block) + "_side", side).renderType(type));
        }
    }

    /* Trapdoors */
    public void trapdoorBasic(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoorBasic(block, name, CUTOUT);
    }

    public void trapdoorBasic(Supplier<? extends TrapDoorBlock> block, String name, ResourceLocation type) {
        trapdoor(block, locMod(name + "_trapdoor"), type, true);
    }

    public void trapdoorParent(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoor(block, locParent(name), CUTOUT, false);
    }

    public void trapdoor(Supplier<? extends TrapDoorBlock> block, ResourceLocation texture, ResourceLocation type, boolean orientable) {
        if (type == SOLID) {
            trapdoorBlock(block.get(), texture, orientable);
        } else {
            trapdoorBlockWithRenderType(block.get(), texture, orientable, type);
        }
    }

    /* Fence Gates */
    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, String name) {
        fenceGateBasic(block, name, SOLID);
    }

    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, String name, ResourceLocation type) {
        fenceGateBasic(block, locParent(name), type);
    }

    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, ResourceLocation name, ResourceLocation type) {
        fenceGate(block, name, name, name, type);
    }

    public void fenceGateColumn(Supplier<? extends FenceGateBlock> block, String side, String top) {
        fenceGate(block, locParent(side), locParent(top), locParent(top), SOLID);
    }

    public void fenceGate(Supplier<? extends FenceGateBlock> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom, ResourceLocation type) {
        ModelFile gate, gateOpen, gateWall, gateWallOpen;
        if (type == SOLID) {
            gate =         models().fenceGate(getKey(block), side, top, bottom);
            gateOpen =     models().fenceGateOpen(getKey(block) + "_open", side, top, bottom);
            gateWall =     models().fenceGateWall(getKey(block) + "_wall", side, top, bottom);
            gateWallOpen = models().fenceGateWallOpen(getKey(block) + "_wall_open", side, top, bottom);
        } else {
            gate =         models().fenceGate(getKey(block), side, top, bottom).renderType(type);
            gateOpen =     models().fenceGateOpen(getKey(block) + "_open", side, top, bottom).renderType(type);
            gateWall =     models().fenceGateWall(getKey(block) + "_wall", side, top, bottom).renderType(type);
            gateWallOpen = models().fenceGateWallOpen(getKey(block) + "_wall_open", side, top, bottom).renderType(type);
        }

        fenceGateBlock(block.get(), gate, gateOpen, gateWall, gateWallOpen);
    }

    /* Doors */
    public void doorBasic(Supplier<? extends DoorBlock> block, String name) {
        doorBasic(block, name, CUTOUT);
    }

    public void doorBasic(Supplier<? extends DoorBlock> block, String name, ResourceLocation type) {
        door(block, locMod(name + "_door_bottom"), locMod(name + "_door_bottom"), locMod(name + "_door_top"), locMod(name + "_door_top"), type);
    }

    public void doorHidden(Supplier<? extends DoorBlock> block, String name) {
        door(block, locParent(name), locParent(name), locParent(name), locParent(name), CUTOUT);
    }

    public void door(Supplier<? extends DoorBlock> block, ResourceLocation bottomside, ResourceLocation bottom, ResourceLocation topside, ResourceLocation top, ResourceLocation type) {
        ModelFile bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen;

        if (type == SOLID) {
            bottomLeft = models().doorBottomLeftO(getKey(block) + "_bottom_left", bottomside, bottom);
            bottomLeftOpen = models().doorBottomLeftOpenO(getKey(block) + "_bottom_left_open", bottomside, bottom);
            bottomRight = models().doorBottomRightO(getKey(block) + "_bottom_right", bottomside, bottom);
            bottomRightOpen = models().doorBottomRightOpenO(getKey(block) + "_bottom_right_open", bottomside, bottom);
            topLeft = models().doorTopLeftO(getKey(block) + "_top_left", topside, top);
            topLeftOpen = models().doorTopLeftOpenO(getKey(block) + "_top_left_open", topside, top);
            topRight = models().doorTopRightO(getKey(block) + "_top_right", topside, top);
            topRightOpen = models().doorTopRightOpenO(getKey(block) + "_top_right_open", topside, top);
        } else {
            bottomLeft = models().doorBottomLeftO(getKey(block) + "_bottom_left", bottomside, bottom).renderType(type);
            bottomLeftOpen = models().doorBottomLeftOpenO(getKey(block) + "_bottom_left_open", bottomside, bottom).renderType(type);
            bottomRight = models().doorBottomRightO(getKey(block) + "_bottom_right", bottomside, bottom).renderType(type);
            bottomRightOpen = models().doorBottomRightOpenO(getKey(block) + "_bottom_right_open", bottomside, bottom).renderType(type);
            topLeft = models().doorTopLeftO(getKey(block) + "_top_left", topside, top).renderType(type);
            topLeftOpen = models().doorTopLeftOpenO(getKey(block) + "_top_left_open", topside, top).renderType(type);
            topRight = models().doorTopRightO(getKey(block) + "_top_right", topside, top).renderType(type);
            topRightOpen = models().doorTopRightOpenO(getKey(block) + "_top_right_open", topside, top).renderType(type);
        }

        doorBlock(block.get(), bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen);
    }

    /* Poles */
    public void poleBasic(Supplier<? extends OrnamentPole> block, String name) {
        poleBasic(block, name, SOLID);
    }

    public void poleBasic(Supplier<? extends OrnamentPole> block, String name, ResourceLocation type) {
        poleBasic(block, name, name, type);
    }

    public void poleBasic(Supplier<? extends OrnamentPole> block, String fullblock, String name, ResourceLocation type) {
        poleBasic(block, locParent(fullblock), locParent(name), type);
    }

    public void poleBasic(Supplier<? extends OrnamentPole> block, ResourceLocation fullblock, ResourceLocation name, ResourceLocation type) {
        pole(block, fullblock, name, name, name, type);
    }

    public void poleColumn(Supplier<? extends OrnamentPole> block, String fullblock, String side, String top) {
        pole(block, locParent(fullblock), locParent(top), locParent(top), locParent(side), SOLID);
    }

    public void pole(Supplier<? extends OrnamentPole> block, ResourceLocation full, ResourceLocation top, ResourceLocation bottom, ResourceLocation side, ResourceLocation type) {
        ModelFile whole, horizon, vertical, corner, fullblock;

        if (type == SOLID) {
            whole = models().poleWhole(getKey(block) + "_whole", side, top, bottom);
            horizon = models().poleHorizon(getKey(block) + "_horizontal", side, top, bottom);
            vertical = models().poleVertical(getKey(block) + "_vertical", side, top, bottom);
            corner = models().poleCorner(getKey(block) + "_corner", side, top, bottom);
            fullblock = models().getExistingFile(full);
        } else {
            whole = models().poleWhole(getKey(block) + "_whole", side, top, bottom).renderType(type);
            horizon = models().poleHorizon(getKey(block) + "_horizontal", side, top, bottom).renderType(type);
            vertical = models().poleVertical(getKey(block) + "_vertical", side, top, bottom).renderType(type);
            corner = models().poleCorner(getKey(block) + "_corner", side, top, bottom).renderType(type);
            fullblock = models().forceRenderType(full.getPath(), full, type);
        }

        poleBlock(block, whole, horizon, vertical, corner, fullblock);
    }

    public void beamBasic(Supplier<? extends OrnamentBeam> block, String name) {
        beamBasic(block, name, SOLID);
    }

    public void beamBasic(Supplier<? extends OrnamentBeam> block, String name, ResourceLocation type) {
        beamBasic(block, name, name, type);
    }

    public void beamBasic(Supplier<? extends OrnamentBeam> block, String name, String fullblock, ResourceLocation type) {
        beamBasic(block, locParent(fullblock), locParent(name), type);
    }

    public void beamBasic(Supplier<? extends OrnamentBeam> block, ResourceLocation fullblock, ResourceLocation name, ResourceLocation type) {
        beam(block, fullblock, name, name, name, type);
    }

    public void beamColumn(Supplier<? extends OrnamentBeam> block, String name, String top, String side) {
        beam(block, locParent(name), locParent(top), locParent(top), locParent(side), SOLID);
    }

    public void beam(Supplier<? extends OrnamentBeam> block, ResourceLocation full, ResourceLocation top, ResourceLocation bottom, ResourceLocation side, ResourceLocation type) {
        ModelFile whole, horizon, vertical, corner, fullblock;

        if (type == SOLID) {
            whole = models().beamWhole(getKey(block) + "_whole", side, top, bottom);
            horizon = models().beamHorizontal(getKey(block) + "_horizontal", side, top, bottom);
            vertical = models().beamVertical(getKey(block) + "_vertical", side, top, bottom);
            corner = models().beamCorner(getKey(block) + "_corner", side, top, bottom);
            fullblock = models().getExistingFile(full);
        } else {
            whole = models().beamWhole(getKey(block) + "_whole", side, top, bottom).renderType(type);
            horizon = models().beamHorizontal(getKey(block) + "_horizontal", side, top, bottom).renderType(type);
            vertical = models().beamVertical(getKey(block) + "_vertical", side, top, bottom).renderType(type);
            corner = models().beamCorner(getKey(block) + "_corner", side, top, bottom).renderType(type);
            fullblock = models().forceRenderType(full.getPath(), full, type);
        }

        beamBlock(block, whole, horizon, vertical, corner, fullblock);
    }

    public void wallBasic(Supplier<? extends WallBlock> block, String name) {
        wallBasic(block, name, SOLID);
    }

    public void wallBasic(Supplier<? extends WallBlock> block, String name, ResourceLocation type) {
        wallBasic(block, locParent(name), type);
    }

    public void wallBasic(Supplier<? extends WallBlock> block, ResourceLocation name, ResourceLocation type) {
        wall(block, name, name, name, type);
    }

    public void wallColumn(Supplier<? extends WallBlock> block, String side, String end) {
        wall(block, locParent(side), locParent(end), locParent(end), SOLID);
    }

    public void wall(Supplier<? extends WallBlock> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom, ResourceLocation type) {
        ModelFile wallpost, wallside, walltall;

        if (type == SOLID) {
            wallpost = models().wallPost(getKey(block) + "_post", side, top, bottom);
            wallside = models().wallSide(getKey(block) + "_side", side, top, bottom);
            walltall = models().wallSideTall(getKey(block) + "_side_tall", side, top, bottom);
        } else {
            wallpost = models().wallPost(getKey(block) + "_post", side, top, bottom).renderType(type);
            wallside = models().wallSide(getKey(block) + "_side", side, top, bottom).renderType(type);
            walltall = models().wallSideTall(getKey(block) + "_side_tall", side, top, bottom).renderType(type);
        }

        wallBlock(block.get(), wallpost, wallside, walltall);
    }

    public void saddleDoorBasic(Supplier<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoorBasic(block, name, CUTOUT);
    }

    public void saddleDoorBasic(Supplier<? extends OrnamentSaddleDoor> block, String name, ResourceLocation type) {
        saddleDoor(block, locMod(name + "_trapdoor"), locMod(name + "_trapdoor"), locMod(name + "_trapdoor"), type);
    }

    public void saddleDoorHidden(Supplier<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoor(block, locParent(name), locParent(name), locParent(name), CUTOUT);
    }

    public void saddleDoor(Supplier<? extends OrnamentSaddleDoor> block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top, ResourceLocation type) {
        ModelFile left, leftOpen, right, rightOpen;

        if (type == SOLID) {
            left = models().saddleDoorLeft(getKey(block) + "_left", side, top, bottom);
            leftOpen = models().saddleDoorLeftOpen(getKey(block) + "_left_open", side, top, bottom);
            right = models().saddleDoorRight(getKey(block) + "_right", side, top, bottom);
            rightOpen = models().saddleDoorRightOpen(getKey(block) + "_right_open", side, top, bottom);
        } else {
            left = models().saddleDoorLeft(getKey(block) + "_left", side, top, bottom).renderType(type);
            leftOpen = models().saddleDoorLeftOpen(getKey(block) + "_left_open", side, top, bottom).renderType(type);
            right = models().saddleDoorRight(getKey(block) + "_right", side, top, bottom).renderType(type);
            rightOpen = models().saddleDoorRightOpen(getKey(block) + "_right_open", side, top, bottom).renderType(type);
        }
        saddleDoorBlock(block, left, leftOpen, right, rightOpen);
    }

    /* Supports */
    public void supportBasic(Supplier<? extends OrnamentSupport> block, String name) {
        supportBasic(block, name, SOLID);
    }

    public void supportBasic(Supplier<? extends OrnamentSupport> block, String name, ResourceLocation type) {
        supportBasic(block, locParent(name), type);
    }

    public void supportBasic(Supplier<? extends OrnamentSupport> block, ResourceLocation name, ResourceLocation type) {
        support(block, name, name, name, type);
    }

    public void supportColumn(Supplier<? extends OrnamentSupport> block, String side, String top) {
        support(block, locParent(side), locParent(top), locParent(top), SOLID);
    }

    public void support(Supplier<? extends OrnamentSupport> block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top, ResourceLocation type) {
        ModelFile base, baseTop, vertical, verticalTop, horizontalX, horizontalXTop, horizontalZ, horizontalZTop;

        if (type == SOLID) {
            base = models().supportBase(getKey(block) + "_base", side, top, bottom);
            baseTop = models().supportBaseTop(getKey(block) + "_base_top", side, top, bottom);
            vertical = models().supportVertical(getKey(block) + "_vertical", side, top, bottom);
            verticalTop = models().supportVerticalTop(getKey(block) + "_vertical_top", side, top, bottom);
            horizontalX = models().supportHorizontalX(getKey(block) + "_horizontal_x", side, top, bottom);
            horizontalXTop = models().supportHorizontalXTop(getKey(block) + "_horizontal_x_top", side, top, bottom);
            horizontalZ = models().supportHorizontalZ(getKey(block) + "_horizontal_z", side, top, bottom);
            horizontalZTop = models().supportHorizontalZTop(getKey(block) + "_horizontal_z_top", side, top, bottom);
        } else {
            base = models().supportBase(getKey(block) + "_base", side, top, bottom).renderType(type);
            baseTop = models().supportBase(getKey(block) + "_base_top", side, top, bottom).renderType(type);
            vertical = models().supportVertical(getKey(block) + "_vertical", side, top, bottom).renderType(type);
            verticalTop = models().supportVerticalTop(getKey(block) + "_vertical_top", side, top, bottom).renderType(type);
            horizontalX = models().supportHorizontalX(getKey(block) + "_horizontal_x", side, top, bottom).renderType(type);
            horizontalXTop = models().supportHorizontalXTop(getKey(block) + "_horizontal_x_top", side, top, bottom).renderType(type);
            horizontalZ = models().supportHorizontalZ(getKey(block) + "_horizontal_z", side, top, bottom).renderType(type);
            horizontalZTop = models().supportHorizontalZTop(getKey(block) + "_horizontal_z_top", side, top, bottom).renderType(type);
        }
        supportBlock(block, base, baseTop, vertical, verticalTop, horizontalX, horizontalXTop, horizontalZ, horizontalZTop, false);
    }

    public void saddleDoorBlock(Supplier<? extends OrnamentSaddleDoor> block, ModelFile left, ModelFile leftOpen, ModelFile right, ModelFile rightOpen) {
        getVariantBuilder(block.get()).forAllStatesExcept(state -> {
            int yRot = ((int) state.getValue(OrnamentSaddleDoor.FACING).toYRot()) + 90;
            boolean rh = state.getValue(OrnamentSaddleDoor.HINGE) == DoorHingeSide.RIGHT;
            boolean open = state.getValue(OrnamentSaddleDoor.OPEN);
            if (open) {
                yRot += 90;
            }
            if (rh && open) {
                yRot += 180;
            }
            yRot %= 360;

            ModelFile model = null;
            if (rh && open) {
                model = rightOpen;
            } else if (!rh && open) {
                model = leftOpen;
            }
            if (rh && !open) {
                model = right;
            } else if (!rh && !open) {
                model = left;
            }

            return ConfiguredModel.builder().modelFile(model)
                    .rotationY(yRot)
                    .build();
        }, OrnamentSaddleDoor.POWERED);
    }

    public void poleBlock(Supplier<? extends OrnamentPole> block, ModelFile whole, ModelFile horizon, ModelFile vertical, ModelFile corner, ModelFile fullblock) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block.get());
        poleModelWhole(builder, whole, 0, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelWhole(builder, whole, 90, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelWhole(builder, whole, 180, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelWhole(builder, whole, 270, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelLength(builder, horizon, 0, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelLength(builder, horizon, 90, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        poleModelLength(builder, horizon, 180, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        poleModelLength(builder, horizon, 270, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelLength(builder, vertical, 0, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        poleModelLength(builder, vertical, 90, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelLength(builder, vertical, 180, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelLength(builder, vertical, 270, OrnamentPole.BOTTOM_LEFT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        poleModelCorner(builder, corner, 0, true, true, true, false);
        poleModelCorner(builder, corner, 90, true, true, false, true);
        poleModelCorner(builder, corner, 180, false, true, true, true);
        poleModelCorner(builder, corner, 270, true, false, true, true);

        builder.part()
                .modelFile(fullblock)
                .addModel()
                .condition(OrnamentPole.TOP_LEFT, true)
                .condition(OrnamentPole.TOP_RIGHT, true)
                .condition(OrnamentPole.BOTTOM_LEFT, true)
                .condition(OrnamentPole.BOTTOM_RIGHT, true);
    }

    public void poleModelWhole(MultiPartBlockStateBuilder builder, ModelFile whole, int yRot, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        poleModelTri(builder, whole, yRot, main, true, c1, false, c2, false);
    }

    public void poleModelLength(MultiPartBlockStateBuilder builder, ModelFile length, int yRot, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        poleModelTri(builder, length, yRot, main, true, c1, true, c2, false);
    }

    public void poleModelTri(MultiPartBlockStateBuilder builder, ModelFile model, int yRot, BooleanProperty main, boolean mFlag, BooleanProperty c1, boolean c1Flag, BooleanProperty c2, boolean c2Flag) {
        builder.part()
                .modelFile(model)
                .rotationY(yRot)
                .uvLock(yRot != 0)
                .addModel()
                .condition(main, mFlag)
                .condition(c1, c1Flag)
                .condition(c2, c2Flag);
    }

    public void poleModelCorner(MultiPartBlockStateBuilder builder, ModelFile model, int yRot, boolean tlFlag, boolean trFlag, boolean blFlag, boolean brFlag) {
        builder.part()
                .modelFile(model)
                .rotationY(yRot)
                .uvLock(yRot != 0)
                .addModel()
                .condition(OrnamentPole.TOP_LEFT, tlFlag)
                .condition(OrnamentPole.TOP_RIGHT, trFlag)
                .condition(OrnamentPole.BOTTOM_LEFT, blFlag)
                .condition(OrnamentPole.BOTTOM_RIGHT, brFlag);
    }

    public void beamBlock(Supplier<? extends OrnamentBeam> block, ModelFile whole, ModelFile horizon, ModelFile vertical, ModelFile corner, ModelFile fullblock) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block.get());
        beamModelWhole(builder, whole, 180, 180, Direction.Axis.X, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelWhole(builder, whole, 180, 90, Direction.Axis.Z, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelWhole(builder, whole, 180, 0, Direction.Axis.X, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, 180, 270, Direction.Axis.Z, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, 0, 0, Direction.Axis.X, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, 0, 270, Direction.Axis.Z, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, 0, 180, Direction.Axis.X, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelWhole(builder, whole, 0, 90, Direction.Axis.Z, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelLength(builder, horizon, 180, 180, Direction.Axis.X, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelLength(builder, horizon, 180, 90, Direction.Axis.Z, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelLength(builder, horizon, 180, 0, Direction.Axis.X, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelLength(builder, horizon, 180, 270, Direction.Axis.Z, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelLength(builder, horizon, 0, 0, Direction.Axis.X, OrnamentPole.BOTTOM_LEFT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        beamModelLength(builder, horizon, 0, 270, Direction.Axis.Z, OrnamentPole.BOTTOM_LEFT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        beamModelLength(builder, horizon, 0, 180, Direction.Axis.X, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        beamModelLength(builder, horizon, 0, 90, Direction.Axis.Z, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        beamModelLength(builder, vertical, 180, 180, Direction.Axis.X, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        beamModelLength(builder, vertical, 180, 90, Direction.Axis.Z, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        beamModelLength(builder, vertical, 180, 0, Direction.Axis.X, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        beamModelLength(builder, vertical, 180, 270, Direction.Axis.Z, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        beamModelLength(builder, vertical, 0, 0, Direction.Axis.X, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelLength(builder, vertical, 0, 270, Direction.Axis.Z, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelLength(builder, vertical, 0, 180, Direction.Axis.X, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelLength(builder, vertical, 0, 90, Direction.Axis.Z, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelCorner(builder, corner, 180, 180, Direction.Axis.X, true, true, true, false);
        beamModelCorner(builder, corner, 180, 90, Direction.Axis.Z, true, true, true, false);
        beamModelCorner(builder, corner, 180, 0, Direction.Axis.X, true, true, false, true);
        beamModelCorner(builder, corner, 180, 270, Direction.Axis.Z, true, true, false, true);
        beamModelCorner(builder, corner, 0, 180, Direction.Axis.X, false, true, true, true);
        beamModelCorner(builder, corner, 0, 90, Direction.Axis.Z, false, true, true, true);
        beamModelCorner(builder, corner, 0, 0, Direction.Axis.X, true, false, true, true);
        beamModelCorner(builder, corner, 0, 270, Direction.Axis.Z, true, false, true, true);

        builder.part()
                .modelFile(fullblock)
                .addModel()
                .condition(OrnamentPole.TOP_LEFT, true)
                .condition(OrnamentPole.TOP_RIGHT, true)
                .condition(OrnamentPole.BOTTOM_LEFT, true)
                .condition(OrnamentPole.BOTTOM_RIGHT, true);
    }

    public void beamModelWhole(MultiPartBlockStateBuilder builder, ModelFile model, int xRot, int yRot, Direction.Axis axis, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        beamModelTri(builder, model, xRot, yRot, axis, main, true, c1, false, c2, false);
    }

    public void beamModelLength(MultiPartBlockStateBuilder builder, ModelFile model, int xRot, int yRot, Direction.Axis axis, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        beamModelTri(builder, model, xRot, yRot, axis, main, true, c1, true, c2, false);
    }

    public void beamModelTri(MultiPartBlockStateBuilder builder, ModelFile model, int xRot, int yRot, Direction.Axis axis, BooleanProperty main, boolean mFlag, BooleanProperty c1, boolean c1Flag, BooleanProperty c2, boolean c2Flag) {
        builder.part()
                .modelFile(model)
                .rotationX(xRot)
                .rotationY(yRot)
                .uvLock(xRot != 0 || yRot != 0)
                .addModel()
                .condition(BlockStateProperties.HORIZONTAL_AXIS, axis)
                .condition(main, mFlag)
                .condition(c1, c1Flag)
                .condition(c2, c2Flag);
    }

    public void beamModelCorner(MultiPartBlockStateBuilder builder, ModelFile model, int xRot, int yRot, Direction.Axis axis, boolean tlFlag, boolean trFlag, boolean blFlag, boolean brFlag) {
        builder.part()
                .modelFile(model)
                .rotationX(xRot)
                .rotationY(yRot)
                .uvLock(xRot != 0 || yRot != 0)
                .addModel()
                .condition(BlockStateProperties.HORIZONTAL_AXIS, axis)
                .condition(OrnamentPole.TOP_LEFT, tlFlag)
                .condition(OrnamentPole.TOP_RIGHT, trFlag)
                .condition(OrnamentPole.BOTTOM_LEFT, blFlag)
                .condition(OrnamentPole.BOTTOM_RIGHT, brFlag);
    }

    public void supportBlock(Supplier<? extends OrnamentSupport> block, ModelFile base, ModelFile basetop, ModelFile vertical, ModelFile verticaltop, ModelFile horizontalX, ModelFile horizontalXtop, ModelFile horizontalZ, ModelFile horizontalZtop, boolean topmodels) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block.get());
        supportModelBase(builder, base, OrnamentSupport.CornerType.TOP_LEFT, false, 0, topmodels);
        supportModelBase(builder, base, OrnamentSupport.CornerType.TOP_RIGHT, false, 90, topmodels);
        supportModelBase(builder, base, OrnamentSupport.CornerType.BOTTOM_RIGHT, false, 180, topmodels);
        supportModelBase(builder, base, OrnamentSupport.CornerType.BOTTOM_LEFT, false, 270, topmodels);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.TOP_LEFT, true, 0, topmodels);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.TOP_RIGHT, true, 90, topmodels);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.BOTTOM_RIGHT, true, 180, topmodels);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.BOTTOM_LEFT, true, 270, topmodels);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.TB_CONNECT, false, 0, topmodels);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.TB_CONNECT, false, 90, topmodels);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.TB_CONNECT, false, 180, topmodels);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.TB_CONNECT, false, 270, topmodels);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.TB_CONNECT, true, 0, topmodels);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.TB_CONNECT, true, 90, topmodels);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.TB_CONNECT, true, 180, topmodels);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.TB_CONNECT, true, 270, topmodels);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.NS_CONNECT, false, 0, topmodels);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.NS_CONNECT, false, 90, topmodels);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.NS_CONNECT, false, 180, topmodels);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.NS_CONNECT, false, 270, topmodels);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.NS_CONNECT, true, 0, topmodels);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.NS_CONNECT, true, 90, topmodels);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.NS_CONNECT, true, 180, topmodels);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.NS_CONNECT, true, 270, topmodels);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.EW_CONNECT, false, 0, topmodels);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.EW_CONNECT, false, 90, topmodels);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.EW_CONNECT, false, 180, topmodels);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.EW_CONNECT, false, 270, topmodels);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.EW_CONNECT, true, 0, topmodels);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.EW_CONNECT, true, 90, topmodels);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.EW_CONNECT, true, 180, topmodels);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.EW_CONNECT, true, 270, topmodels);
    }

    public void supportModelBase(MultiPartBlockStateBuilder builder, ModelFile model, OrnamentSupport.CornerType corner, boolean upper, int y, boolean topmodel) {
        builder.part()
                .modelFile(model)
                .rotationY(y)
                .uvLock(true)
                .addModel()
                .condition(OrnamentSupport.CORNER, corner)
                .condition(OrnamentSupport.UPPER_HALF, upper);
    }

    public void supportModelConnect(MultiPartBlockStateBuilder builder, ModelFile model, OrnamentSupport.CornerType corner, BooleanProperty connect, boolean upper, int y, boolean topmodel) {
        builder.part()
                .modelFile(model)
                .rotationY(y)
                .uvLock(true)
                .addModel()
                .condition(OrnamentSupport.CORNER, corner)
                .condition(OrnamentSupport.UPPER_HALF, upper)
                .condition(connect, true);
    }
}
