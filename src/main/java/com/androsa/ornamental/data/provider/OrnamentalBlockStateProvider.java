package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.blocks.OrnamentBeam;
import com.androsa.ornamental.blocks.OrnamentPole;
import com.androsa.ornamental.blocks.OrnamentSaddleDoor;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public abstract class OrnamentalBlockStateProvider extends BlockStateProvider {

    private final OrnamentalBlockModelProvider blockModels;
    private final String modID;

    public static final ResourceLocation SOLID = new ResourceLocation("solid");
    public static final ResourceLocation TRANSLUCENT = new ResourceLocation("translucent");
    public static final ResourceLocation CUTOUT = new ResourceLocation("cutout");
    public static final ResourceLocation CUTOUT_MIPPED = new ResourceLocation("cutout_mipped");

    public OrnamentalBlockStateProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);
        modID = modid;

        blockModels = new OrnamentalBlockModelProvider(generator, modid, helper) {
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

    public String getModID() {
        return modID;
    }

    public OrnamentalBlockModelProvider models() {
        return blockModels;
    }

    protected ResourceLocation locVanilla(String name) {
        return new ResourceLocation("block/" + name);
    }

    protected ResourceLocation locOrnament(String name) {
        return new ResourceLocation(getModID(), "block/" + name);
    }

    /* Stairs */
    public void stairsBasic(RegistryObject<? extends StairBlock> block, String name) {
        stairsBasic(block, name, SOLID);
    }

    public void stairsBasic(RegistryObject<? extends StairBlock> block, String name, ResourceLocation type) {
        stairsBasic(block, locVanilla(name), type);
    }

    public void stairsMissing(RegistryObject<? extends StairBlock> block) {
        stairsBasic(block, locOrnament("missingno"), SOLID);
    }

    public void stairsBasic(RegistryObject<? extends StairBlock> block, ResourceLocation name, ResourceLocation type) {
        stairs(block, name, name, name, type);
    }

    public void stairsColumn(RegistryObject<? extends StairBlock> block, String side, String end) {
        stairs(block, locVanilla(side), locVanilla(end), locVanilla(end), SOLID);
    }

    public void stairs(RegistryObject<? extends StairBlock> block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top, ResourceLocation type) {
        if (type == SOLID) {
            stairsBlock(block.get(), side, bottom, top);
        } else {
            stairsBlockWithRenderType(block.get(), side, bottom, top, type);
        }
    }

    /* Slabs */
    public void slabBasic(RegistryObject<? extends SlabBlock> block, String name) {
        slabBasic(block, name, SOLID);
    }

    public void slabBasic(RegistryObject<? extends SlabBlock> block, String name, ResourceLocation type) {
        slabBasic(block, locVanilla(name), type);
    }

    public void slabMissing(RegistryObject<? extends SlabBlock> block) {
        slabBasic(block, locOrnament("missingno"), SOLID);
    }

    public void slabBasic(RegistryObject<? extends SlabBlock> block, ResourceLocation name, ResourceLocation type) {
        slab(block, name, name, name, name, type);
    }

    public void slabModel(RegistryObject<? extends SlabBlock> block, String model, String name) {
        slab(block, locVanilla(model), locVanilla(name), locVanilla(name), locVanilla(name), SOLID);
    }

    public void slabColumn(RegistryObject<? extends SlabBlock> block, String blockname, String side, String end) {
        slab(block, locVanilla(blockname), locVanilla(side), locVanilla(end), locVanilla(end), SOLID);
    }

    public void slab(RegistryObject<? extends SlabBlock> block, ResourceLocation model, ResourceLocation side, ResourceLocation bottom, ResourceLocation top, ResourceLocation type) {
        String baseName = block.getId().getPath();
        ModelFile slab, slabTop, doubleSlab;
        if (type == SOLID) {
            slab = models().slab(baseName, side, bottom, top);
            slabTop = models().slabTop(baseName + "_top", side, bottom, top);
            doubleSlab = models().getExistingFile(model);
        } else {
            slab = models().slab(baseName, side, bottom, top).renderType(type);
            slabTop = models().slabTop(baseName + "_top", side, bottom, top).renderType(type);
            doubleSlab = models().forceRenderType(model.getPath(), model, type);
        }
        slabBlock(block.get(), slab, slabTop, doubleSlab);
    }

    /* Fences */
    public void fenceBasic(RegistryObject<? extends FenceBlock> block, String name) {
        fenceBasic(block, name, SOLID);
    }

    public void fenceBasic(RegistryObject<? extends FenceBlock> block, String name, ResourceLocation type) {
        fenceBasic(block, locVanilla(name), type);
    }

    public void fenceMissing(RegistryObject<? extends FenceBlock> block) {
        fenceBasic(block, locOrnament("missingno"), SOLID);
    }

    public void fenceBasic(RegistryObject<? extends FenceBlock> block, ResourceLocation name, ResourceLocation type) {
        fence(block, name, name, name, type);
    }

    public void fenceColumn(RegistryObject<? extends FenceBlock> block, String side, String top) {
        fence(block, locVanilla(side), locVanilla(top), locVanilla(top), SOLID);
    }

    public void fence(RegistryObject<? extends FenceBlock> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom, ResourceLocation type) {
        String baseName = block.getId().getPath();
        if (type == SOLID) {
            fourWayBlock(block.get(),
                    models().fencePost(baseName + "_post", side, top, bottom),
                    models().fenceSide(baseName + "_side", side));
        } else {
            fourWayBlock(block.get(),
                    models().fencePost(baseName + "_post", side, top, bottom).renderType(type),
                    models().fenceSide(baseName + "_side", side).renderType(type));
        }
    }

    /* Trapdoors */
    public void trapdoorBasic(RegistryObject<? extends TrapDoorBlock> block, String name) {
        trapdoorBasic(block, name, CUTOUT);
    }

    public void trapdoorBasic(RegistryObject<? extends TrapDoorBlock> block, String name, ResourceLocation type) {
        trapdoor(block, locOrnament(name + "_trapdoor"), type, true);
    }

    public void trapdoorMissing(RegistryObject<? extends TrapDoorBlock> block) {
        trapdoor(block, locOrnament("missingno"), CUTOUT, false);
    }

    public void trapdoorVanilla(RegistryObject<? extends TrapDoorBlock> block, String name) {
        trapdoor(block, locVanilla(name), CUTOUT, false);
    }

    private void trapdoor(RegistryObject<? extends TrapDoorBlock> block, ResourceLocation texture, ResourceLocation type, boolean orientable) {
        if (type == SOLID) {
            trapdoorBlock(block.get(), texture, orientable);
        } else {
            trapdoorBlockWithRenderType(block.get(), texture, orientable, type);
        }
    }

    /* Fence Gates */
    public void fenceGateBasic(RegistryObject<? extends FenceGateBlock> block, String name) {
        fenceGateBasic(block, name, SOLID);
    }

    public void fenceGateBasic(RegistryObject<? extends FenceGateBlock> block, String name, ResourceLocation type) {
        fenceGateBasic(block, locVanilla(name), type);
    }

    public void fenceGateMissing(RegistryObject<? extends FenceGateBlock> block) {
        fenceGateBasic(block, locOrnament("missingno"), SOLID);
    }

    public void fenceGateBasic(RegistryObject<? extends FenceGateBlock> block, ResourceLocation name, ResourceLocation type) {
        fenceGate(block, name, name, name, type);
    }

    public void fenceGateColumn(RegistryObject<? extends FenceGateBlock> block, String side, String top) {
        fenceGate(block, locVanilla(side), locVanilla(top), locVanilla(top), SOLID);
    }

    public void fenceGate(RegistryObject<? extends FenceGateBlock> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom, ResourceLocation type) {
        String baseName = block.getId().getPath();
        ModelFile gate, gateOpen, gateWall, gateWallOpen;
        if (type == SOLID) {
            gate =         models().fenceGate(baseName, side, top, bottom);
            gateOpen =     models().fenceGateOpen(baseName + "_open", side, top, bottom);
            gateWall =     models().fenceGateWall(baseName + "_wall", side, top, bottom);
            gateWallOpen = models().fenceGateWallOpen(baseName + "_wall_open", side, top, bottom);
        } else {
            gate =         models().fenceGate(baseName, side, top, bottom).renderType(type);
            gateOpen =     models().fenceGateOpen(baseName + "_open", side, top, bottom).renderType(type);
            gateWall =     models().fenceGateWall(baseName + "_wall", side, top, bottom).renderType(type);
            gateWallOpen = models().fenceGateWallOpen(baseName + "_wall_open", side, top, bottom).renderType(type);
        }

        fenceGateBlock(block.get(), gate, gateOpen, gateWall, gateWallOpen);
    }

    /* Doors */
    public void doorBasic(RegistryObject<? extends DoorBlock> block, String name) {
        doorBasic(block, name, CUTOUT);
    }

    public void doorBasic(RegistryObject<? extends DoorBlock> block, String name, ResourceLocation type) {
        door(block, locOrnament(name + "_door_bottom"), locOrnament(name + "_door_bottom"), locOrnament(name + "_door_top"), locOrnament(name + "_door_top"), type);
    }

    public void doorMissing(RegistryObject<? extends DoorBlock> block) {
        door(block, locOrnament("missingno"), locOrnament("missingno"), locOrnament("missingno"), locOrnament("missingno"), CUTOUT);
    }

    public void doorBasicVanilla(RegistryObject<? extends DoorBlock> block, String name) {
        door(block, locVanilla(name), locVanilla(name), locVanilla(name), locVanilla(name), CUTOUT);
    }

    public void door(RegistryObject<? extends DoorBlock> block, ResourceLocation bottomside, ResourceLocation bottom, ResourceLocation topside, ResourceLocation top, ResourceLocation type) {
        String baseName = block.getId().getPath();
        ModelFile bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen;

        if (type == SOLID) {
            bottomLeft = models().doorBottomLeftO(baseName + "_bottom_left", bottomside, bottom);
            bottomLeftOpen = models().doorBottomLeftOpenO(baseName + "_bottom_left_open", bottomside, bottom);
            bottomRight = models().doorBottomRightO(baseName + "_bottom_right", bottomside, bottom);
            bottomRightOpen = models().doorBottomRightOpenO(baseName + "_bottom_right_open", bottomside, bottom);
            topLeft = models().doorTopLeftO(baseName + "_top_left", topside, top);
            topLeftOpen = models().doorTopLeftOpenO(baseName + "_top_left_open", topside, top);
            topRight = models().doorTopRightO(baseName + "_top_right", topside, top);
            topRightOpen = models().doorTopRightOpenO(baseName + "_top_right_open", topside, top);
        } else {
            bottomLeft = models().doorBottomLeftO(baseName + "_bottom_left", bottomside, bottom).renderType(type);
            bottomLeftOpen = models().doorBottomLeftOpenO(baseName + "_bottom_left_open", bottomside, bottom).renderType(type);
            bottomRight = models().doorBottomRightO(baseName + "_bottom_right", bottomside, bottom).renderType(type);
            bottomRightOpen = models().doorBottomRightOpenO(baseName + "_bottom_right_open", bottomside, bottom).renderType(type);
            topLeft = models().doorTopLeftO(baseName + "_top_left", topside, top).renderType(type);
            topLeftOpen = models().doorTopLeftOpenO(baseName + "_top_left_open", topside, top).renderType(type);
            topRight = models().doorTopRightO(baseName + "_top_right", topside, top).renderType(type);
            topRightOpen = models().doorTopRightOpenO(baseName + "_top_right_open", topside, top).renderType(type);
        }

        doorBlock(block.get(), bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen);
    }

    /* Poles */
    public void poleBasic(RegistryObject<? extends OrnamentPole> block, String name) {
        poleBasic(block, name, SOLID);
    }

    public void poleBasic(RegistryObject<? extends OrnamentPole> block, String name, ResourceLocation type) {
        poleBasic(block, name, name, type);
    }

    public void poleBasic(RegistryObject<? extends OrnamentPole> block, String fullblock, String name, ResourceLocation type) {
        poleBasic(block, locVanilla(fullblock), locVanilla(name), type);
    }

    public void poleBasic(RegistryObject<? extends OrnamentPole> block, ResourceLocation fullblock, ResourceLocation name, ResourceLocation type) {
        pole(block, fullblock, name, name, name, type);
    }

    public void poleMissing(RegistryObject<? extends OrnamentPole> block, String name) {
        poleBasic(block, locOrnament(name), locOrnament(name), SOLID);
    }

    public void poleColumn(RegistryObject<? extends OrnamentPole> block, String fullblock, String side, String top) {
        pole(block, locVanilla(fullblock), locVanilla(top), locVanilla(top), locVanilla(side), SOLID);
    }

    public void pole(RegistryObject<? extends OrnamentPole> block, ResourceLocation full, ResourceLocation top, ResourceLocation bottom, ResourceLocation side, ResourceLocation type) {
        String baseName = block.get().getBuilder().name;
        ModelFile whole, horizon, vertical, corner, fullblock;

        if (type == SOLID) {
            whole = models().poleWhole(baseName + "_pole_whole", side, top, bottom);
            horizon = models().poleHorizon(baseName + "_pole_horizontal", side, top, bottom);
            vertical = models().poleVertical(baseName + "_pole_vertical", side, top, bottom);
            corner = models().poleCorner(baseName + "_pole_corner", side, top, bottom);
            fullblock = models().getExistingFile(full);
        } else {
            whole = models().poleWhole(baseName + "_pole_whole", side, top, bottom).renderType(type);
            horizon = models().poleHorizon(baseName + "_pole_horizontal", side, top, bottom).renderType(type);
            vertical = models().poleVertical(baseName + "_pole_vertical", side, top, bottom).renderType(type);
            corner = models().poleCorner(baseName + "_pole_corner", side, top, bottom).renderType(type);
            fullblock = models().forceRenderType(full.getPath(), full, type);
        }

        poleBlock(block, whole, horizon, vertical, corner, fullblock);
    }

    public void beamBasic(RegistryObject<? extends OrnamentBeam> block, String name) {
        beamBasic(block, name, SOLID);
    }

    public void beamBasic(RegistryObject<? extends OrnamentBeam> block, String name, ResourceLocation type) {
        beamBasic(block, name, name, type);
    }

    public void beamBasic(RegistryObject<? extends OrnamentBeam> block, String name, String fullblock, ResourceLocation type) {
        beam(block, locVanilla(fullblock), locVanilla(name), locVanilla(name), locVanilla(name), type);
    }

    public void beamColumn(RegistryObject<? extends OrnamentBeam> block, String name, String top, String side) {
        beam(block, locVanilla(name), locVanilla(top), locVanilla(top), locVanilla(side), SOLID);
    }

    public void beamMissing(RegistryObject<? extends OrnamentBeam> block, String name) {
        beam(block, locOrnament(name), locOrnament(name), locOrnament(name), locOrnament(name), SOLID);
    }

    public void beam(RegistryObject<? extends OrnamentBeam> block, ResourceLocation full, ResourceLocation top, ResourceLocation bottom, ResourceLocation side, ResourceLocation type) {
        String name = block.getId().getPath();
        ModelFile whole, horizon, vertical, corner, fullblock;

        if (type == SOLID) {
            whole = models().beamWhole(name + "_whole", side, top, bottom);
            horizon = models().beamHorizontal(name + "_horizontal", side, top, bottom);
            vertical = models().beamVertical(name + "_vertical", side, top, bottom);
            corner = models().beamCorner(name + "_corner", side, top, bottom);
            fullblock = models().getExistingFile(full);
        } else {
            whole = models().beamWhole(name + "_whole", side, top, bottom).renderType(type);
            horizon = models().beamHorizontal(name + "_horizontal", side, top, bottom).renderType(type);
            vertical = models().beamVertical(name + "_vertical", side, top, bottom).renderType(type);
            corner = models().beamCorner(name + "_corner", side, top, bottom).renderType(type);
            fullblock = models().forceRenderType(full.getPath(), full, type);
        }

        beamBlock(block, whole, horizon, vertical, corner, fullblock);
    }

    public void wallBasic(RegistryObject<? extends WallBlock> block, String name) {
        wallBasic(block, name, SOLID);
    }

    public void wallBasic(RegistryObject<? extends WallBlock> block, String name, ResourceLocation type) {
        wallBasic(block, locVanilla(name), type);
    }

    public void wallBasic(RegistryObject<? extends WallBlock> block, ResourceLocation name, ResourceLocation type) {
        wall(block, name, name, name, type);
    }

    public void wallMissing(RegistryObject<? extends WallBlock> block, String name) {
        wallBasic(block, locOrnament(name), SOLID);
    }

    public void wallColumn(RegistryObject<? extends WallBlock> block, String side, String end) {
        wall(block, locVanilla(side), locVanilla(end), locVanilla(end), SOLID);
    }

    public void wall(RegistryObject<? extends WallBlock> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom, ResourceLocation type) {
        String basename = block.getId().getPath();
        ModelFile wallpost, wallside, walltall;

        if (type == SOLID) {
            wallpost = models().wallPost(basename + "_post", side, top, bottom);
            wallside = models().wallSide(basename + "_side", side, top, bottom);
            walltall = models().wallSideTall(basename + "_side_tall", side, top, bottom);
        } else {
            wallpost = models().wallPost(basename + "_post", side, top, bottom).renderType(type);
            wallside = models().wallSide(basename + "_side", side, top, bottom).renderType(type);
            walltall = models().wallSideTall(basename + "_side_tall", side, top, bottom).renderType(type);
        }

        wallBlock(block.get(), wallpost, wallside, walltall);
    }

    public void saddleDoorBasic(RegistryObject<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoorBasic(block, name, CUTOUT);
    }

    public void saddleDoorBasic(RegistryObject<? extends OrnamentSaddleDoor> block, String name, ResourceLocation type) {
        saddleDoor(block, locOrnament(name + "_trapdoor"), locOrnament(name + "_trapdoor"), locOrnament(name + "_trapdoor"), type);
    }

    public void saddleDoorMissing(RegistryObject<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoor(block, locOrnament(name), locOrnament(name), locOrnament(name), CUTOUT);
    }

    public void saddleDoorVanilla(RegistryObject<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoor(block, locVanilla(name), locVanilla(name), locVanilla(name), CUTOUT);
    }

    public void saddleDoor(RegistryObject<? extends OrnamentSaddleDoor> block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top, ResourceLocation type) {
        String name = block.getId().getPath();
        ModelFile left, leftOpen, right, rightOpen;

        if (type == SOLID) {
            left = models().saddleDoorLeft(name + "_left", side, top, bottom);
            leftOpen = models().saddleDoorLeftOpen(name + "_left_open", side, top, bottom);
            right = models().saddleDoorRight(name + "_right", side, top, bottom);
            rightOpen = models().saddleDoorRightOpen(name + "_right_open", side, top, bottom);
        } else {
            left = models().saddleDoorLeft(name + "_left", side, top, bottom).renderType(type);
            leftOpen = models().saddleDoorLeftOpen(name + "_left_open", side, top, bottom).renderType(type);
            right = models().saddleDoorRight(name + "_right", side, top, bottom).renderType(type);
            rightOpen = models().saddleDoorRightOpen(name + "_right_open", side, top, bottom).renderType(type);
        }
        saddleDoorBlock(block, left, leftOpen, right, rightOpen);
    }

    public void saddleDoorBlock(RegistryObject<? extends OrnamentSaddleDoor> block, ModelFile left, ModelFile leftOpen, ModelFile right, ModelFile rightOpen) {
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

    public void poleBlock(RegistryObject<? extends OrnamentPole> block, ModelFile whole, ModelFile horizon, ModelFile vertical, ModelFile corner, ModelFile fullblock) {
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

    public void beamBlock(RegistryObject<? extends OrnamentBeam> block, ModelFile whole, ModelFile horizon, ModelFile vertical, ModelFile corner, ModelFile fullblock) {
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
}
