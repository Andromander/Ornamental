package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.blocks.OrnamentBeam;
import com.androsa.ornamental.blocks.OrnamentPole;
import com.androsa.ornamental.blocks.OrnamentSaddleDoor;
import com.androsa.ornamental.blocks.PoleType;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
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
        String baseName = block.getId().toString();
        ModelFile doubleSlab = models().getExistingFile(model);
        ModelFile slab, slabTop;
        if (type == SOLID) {
            slab = models().slab(baseName, side, bottom, top);
            slabTop = models().slabTop(baseName + "_top", side, bottom, top);
        } else {
            slab = models().slab(baseName, side, bottom, top).renderType(type);
            slabTop = models().slabTop(baseName + "_top", side, bottom, top).renderType(type);
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
        fence(block, name, name, type);
    }

    public void fenceColumn(RegistryObject<? extends FenceBlock> block, String side, String top) {
        fence(block, locVanilla(side), locVanilla(top), SOLID);
    }

    //TODO: top-bottom model, make this the central generator
    public void fence(RegistryObject<? extends FenceBlock> block, ResourceLocation side, ResourceLocation top, ResourceLocation type) {
        String baseName = block.getId().toString();
        if (type == SOLID) {
            fourWayBlock(block.get(),
                    models().fencePostColumn(baseName + "_post", side, top),
                    models().fenceSide(baseName + "_side", side));
        } else {
            fourWayBlock(block.get(),
                    models().fencePostColumn(baseName + "_post", side, top).renderType(type),
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
        fenceGate(block, name, name, type);
    }

    public void fenceGateColumn(RegistryObject<? extends FenceGateBlock> block, String side, String top) {
        fenceGate(block, locVanilla(side), locVanilla(top), SOLID);
    }

    //TODO: top-bottom model
    public void fenceGate(RegistryObject<? extends FenceGateBlock> block, ResourceLocation side, ResourceLocation top, ResourceLocation type) {
        String baseName = block.getId().toString();
        ModelFile gate, gateOpen, gateWall, gateWallOpen;
        if (type == SOLID) {
            gate =         models().fenceGateColumn(baseName, side, top);
            gateOpen =     models().fenceGateOpenColumn(baseName + "_open", side, top);
            gateWall =     models().fenceGateWallColumn(baseName + "_wall", side, top);
            gateWallOpen = models().fenceGateWallOpenColumn(baseName + "_wall_open", side, top);
        } else {
            gate =         models().fenceGateColumn(baseName, side, top).renderType(type);
            gateOpen =     models().fenceGateOpenColumn(baseName + "_open", side, top).renderType(type);
            gateWall =     models().fenceGateWallColumn(baseName + "_wall", side, top).renderType(type);
            gateWallOpen = models().fenceGateWallOpenColumn(baseName + "_wall_open", side, top).renderType(type);
        }

        fenceGateBlock(block.get(), gate, gateOpen, gateWall, gateWallOpen);
    }

    /* Doors */
    public void doorBasic(RegistryObject<? extends DoorBlock> block, String name) {
        doorBasic(block, name, CUTOUT);
    }

    public void doorBasic(RegistryObject<? extends DoorBlock> block, String name, ResourceLocation type) {
        door(block, locOrnament(name + "_door_bottom"), locOrnament(name + "_door_top"), type);
    }

    public void doorMissing(RegistryObject<? extends DoorBlock> block) {
        door(block, locOrnament("missingno"), locOrnament("missingno"), CUTOUT);
    }

    public void doorBasicVanilla(RegistryObject<? extends DoorBlock> block, String name) {
        door(block, locVanilla(name), locVanilla(name), CUTOUT);
    }

    //TODO: top-bottom model
    public void door(RegistryObject<? extends DoorBlock> block, ResourceLocation bottom, ResourceLocation top, ResourceLocation type) {
        if (type == SOLID) {
            doorBlock(block.get(), bottom, top);
        } else {
            doorBlockWithRenderType(block.get(), bottom, top, type);
        }
    }

    /* Poles */
    public void poleBasic(RegistryObject<? extends OrnamentPole> block, String name) {
        poleBasic(block, name, SOLID);
    }

    public void poleBasic(RegistryObject<? extends OrnamentPole> block, String name, ResourceLocation type) {
        poleBasic(block, name, name, type);
    }

    public void poleBasic(RegistryObject<? extends OrnamentPole> block, String name, String fullblock, ResourceLocation type) {
        poleBasic(block, locVanilla(name), locVanilla(fullblock), type);
    }

    public void poleBasic(RegistryObject<? extends OrnamentPole> block, ResourceLocation name, ResourceLocation fullblock, ResourceLocation type) {
        pole(block, fullblock, name, name, type);
    }

    public void poleMissing(RegistryObject<? extends OrnamentPole> block, String name) {
        poleBasic(block, locOrnament(name), locOrnament(name), SOLID);
    }

    public void poleColumn(RegistryObject<? extends OrnamentPole> block, String name, String side, String top) {
        pole(block, locVanilla(name), locVanilla(top), locVanilla(side), SOLID);
    }

    //TODO: top-bottom model
    public void pole(RegistryObject<? extends OrnamentPole> block, ResourceLocation fullblock, ResourceLocation top, ResourceLocation side, ResourceLocation type) {
        String baseName = block.getId().toString();
        ModelFile corner, half, cross, fill;
        ModelFile full   = models().getExistingFile(fullblock);
        if (type == SOLID) {
            corner = models().poleCornerColumn(baseName + "_corner", side, top);
            half   = models().slabVerticalColumn(baseName + "_half", side, top);
            cross  = models().poleCrossColumn(baseName + "_cross", side, top);
            fill   = models().stairsStraightSideColumn(baseName + "_fill", side, top);
        } else {
            corner = models().poleCornerColumn(baseName + "_corner", side, top).renderType(type);
            half   = models().slabVerticalColumn(baseName + "_half", side, top).renderType(type);
            cross  = models().poleCrossColumn(baseName + "_cross", side, top).renderType(type);
            fill   = models().stairsStraightSideColumn(baseName + "_fill", side, top).renderType(type);
        }

        poleBlock(block, corner, half, cross, fill, full);
    }

    public void beamBasic(RegistryObject<? extends OrnamentBeam> block, String name, boolean vstairs, boolean vslab) {
        beamBasic(block, name, vstairs, vslab, SOLID);
    }

    public void beamBasic(RegistryObject<? extends OrnamentBeam> block, String name, boolean vstairs, boolean vslab, ResourceLocation type) {
        beamBasic(block, name, name, vstairs, vslab, type);
    }

    public void beamBasic(RegistryObject<? extends OrnamentBeam> block, String name, String fullblock, boolean vstairs, boolean vslab, ResourceLocation type) {
        String base = block.get().getBuilder().name;
        String namebottom = base + "_slab";
        String nametop = namebottom + "_top";
        String stairs = base + "_stairs";

        ResourceLocation stairsloc = vstairs ? locVanilla(stairs) : locOrnament(stairs);
        ResourceLocation topslabloc = vslab ? locVanilla(nametop) : locOrnament(nametop);
        ResourceLocation bottomslabloc = vslab ? locVanilla(namebottom) : locOrnament(namebottom);

        beam(block, locVanilla(fullblock), stairsloc, topslabloc, bottomslabloc, locOrnament(base + "_pole_half"), locVanilla(name), locVanilla(name), type);
    }

    public void beamColumn(RegistryObject<? extends OrnamentBeam> block, String name, String top, String side, boolean vstairs, boolean vslab) {
        String base = block.get().getBuilder().name;
        String namebottom = base + "_slab";
        String nametop = namebottom + "_top";
        String stairs = base + "_stairs";

        ResourceLocation stairsloc = vstairs ? locVanilla(stairs) : locOrnament(stairs);
        ResourceLocation topslabloc = vslab ? locVanilla(nametop) : locOrnament(nametop);
        ResourceLocation bottomslabloc = vslab ? locVanilla(namebottom) : locOrnament(namebottom);

        beam(block, locVanilla(name), stairsloc, topslabloc, bottomslabloc, locOrnament(base + "_pole_half"), locVanilla(top), locVanilla(side), SOLID);
    }

    public void beamMissing(RegistryObject<? extends OrnamentBeam> block, String name) {
        beam(block, locOrnament(name), locOrnament(name + "_stairs"), locOrnament(name + "_slab_top"), locOrnament(name + "_slab"), locOrnament(name + "_pole_half"), locOrnament(name), locOrnament(name), SOLID);
    }

    public void beam(RegistryObject<? extends OrnamentBeam> block, ResourceLocation fullblock, ResourceLocation stairs, ResourceLocation tophalf, ResourceLocation bottomhalf, ResourceLocation polehalf, ResourceLocation top, ResourceLocation side, ResourceLocation type) {
        ModelFile corner, cross;
        ModelFile topslab = models().getExistingFile(tophalf);
        ModelFile bottom = models().getExistingFile(bottomhalf);
        ModelFile sideslab = models().getExistingFile(polehalf);
        ModelFile fill   = models().getExistingFile(stairs);
        ModelFile full   = models().getExistingFile(fullblock);

        if (type == SOLID) {
            corner = models().beamCornerColumn(block.getId().toString() + "_corner", side, top);
            cross  = models().beamCrossColumn(block.getId().toString() + "_cross", side, top);
        } else {
            corner = models().beamCornerColumn(block.getId().toString() + "_corner", side, top).renderType(type);
            cross  = models().beamCrossColumn(block.getId().toString() + "_cross", side, top).renderType(type);
        }

        beamBlock(block, corner, topslab, bottom, sideslab, cross, fill, full);
    }

    public void wallBasic(RegistryObject<? extends WallBlock> block, String name) {
        wallBasic(block, name, SOLID);
    }

    public void wallBasic(RegistryObject<? extends WallBlock> block, String name, ResourceLocation type) {
        wallBasic(block, locVanilla(name), type);
    }

    public void wallBasic(RegistryObject<? extends WallBlock> block, ResourceLocation name, ResourceLocation type) {
        wall(block, name, name, type);
    }

    public void wallMissing(RegistryObject<? extends WallBlock> block, String name) {
        wallBasic(block, locOrnament(name), SOLID);
    }

    public void wallColumn(RegistryObject<? extends WallBlock> block, String side, String end) {
        wall(block, locVanilla(side), locVanilla(end), SOLID);
    }

    //TODO: top-bottom model
    public void wall(RegistryObject<? extends WallBlock> block, ResourceLocation side, ResourceLocation end, ResourceLocation type) {
        String basename = block.getId().toString();
        ModelFile wallpost, wallside, walltall;

        if (type == SOLID) {
            wallpost = models().wallPostColumn(basename + "_post", side, end);
            wallside = models().wallSideColumn(basename + "_side", side, end);
            walltall = models().wallSideTallColumn(basename + "_side_tall", side, end);
        } else {
            wallpost = models().wallPostColumn(basename + "_post", side, end).renderType(type);
            wallside = models().wallSideColumn(basename + "_side", side, end).renderType(type);
            walltall = models().wallSideTallColumn(basename + "_side_tall", side, end).renderType(type);
        }

        wallBlock(block.get(), wallpost, wallside, walltall);
    }

    public void saddleDoorBasic(RegistryObject<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoorBasic(block, name, CUTOUT);
    }

    public void saddleDoorBasic(RegistryObject<? extends OrnamentSaddleDoor> block, String name, ResourceLocation type) {
        saddleDoor(block, locOrnament(name + "_trapdoor"), type);
    }

    public void saddleDoorMissing(RegistryObject<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoor(block, locOrnament(name), CUTOUT);
    }

    public void saddleDoorVanilla(RegistryObject<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoor(block, locVanilla(name), CUTOUT);
    }

    public void saddleDoor(RegistryObject<? extends OrnamentSaddleDoor> block, ResourceLocation texture, ResourceLocation type) {
        String name = block.getId().toString();
        ModelFile bottomLeft, bottomRight;

        if (type == SOLID) {
            bottomLeft = models().saddleDoor(name, texture);
            bottomRight = models().saddleDoorHinge(name + "_hinge", texture);
        } else {
             bottomLeft = models().saddleDoor(name, texture).renderType(type);
             bottomRight = models().saddleDoorHinge(name + "_hinge", texture).renderType(type);
        }
        saddleDoorBlock(block, bottomLeft, bottomRight);
    }

    //TODO: left, left open, right, right open
    public void saddleDoorBlock(RegistryObject<? extends OrnamentSaddleDoor> block, ModelFile left, ModelFile right) {
        getVariantBuilder(block.get()).forAllStatesExcept(state -> {
            int yRot = ((int) state.getValue(OrnamentSaddleDoor.FACING).toYRot()) + 90;
            boolean rh = state.getValue(OrnamentSaddleDoor.HINGE) == DoorHingeSide.RIGHT;
            boolean open = state.getValue(OrnamentSaddleDoor.OPEN);
            boolean r = rh ^ open;
            if (open) {
                yRot += 90;
            }
            if (rh && open) {
                yRot += 180;
            }
            yRot %= 360;
            return ConfiguredModel.builder().modelFile(r ? right : left)
                    .rotationY(yRot)
                    .build();
        }, OrnamentSaddleDoor.POWERED);
    }

    public void poleBlock(RegistryObject<? extends OrnamentPole> block, ModelFile corner, ModelFile half, ModelFile cross, ModelFile fill, ModelFile full) {
        getVariantBuilder(block.get())
                .forAllStatesExcept(state -> {
                    PoleType type = state.getValue(OrnamentPole.TYPE);
                    PoleType.Shape shape = type.getShape();
                    ModelFile model;
                    int yRot;

                    model = switch (shape) {
                        case HALF -> half;
                        case CROSS -> cross;
                        case FILL -> fill;
                        case BLOCK -> full;
                        default -> corner;
                    };
                    if (type == PoleType.TR_CORNER || type == PoleType.R_HALF || type == PoleType.TR_BL || type == PoleType.TR_FILL) {
                        yRot = 90;
                    } else if (type == PoleType.BR_CORNER || type == PoleType.B_HALF || type == PoleType.BR_FILL) {
                        yRot = 180;
                    } else if (type == PoleType.BL_CORNER || type == PoleType.L_HALF || type == PoleType.BL_FILL) {
                        yRot = 270;
                    } else {
                        yRot = 0;
                    }
                    boolean uvlock = yRot != 0; // Don't set uvlock for states that have no rotation
                    return ConfiguredModel.builder()
                            .modelFile(model)
                            .rotationY(yRot)
                            .uvLock(uvlock)
                            .build();
                }, OrnamentPole.WATERLOGGED);
    }

    public void beamBlock(RegistryObject<? extends OrnamentBeam> block, ModelFile corner, ModelFile topslab, ModelFile bottomslab, ModelFile halfpole, ModelFile cross, ModelFile fill, ModelFile full) {
        getVariantBuilder(block.get())
                .forAllStatesExcept(state -> {
                    PoleType type = state.getValue(OrnamentBeam.TYPE);
                    Direction.Axis axis = state.getValue(OrnamentBeam.HORIZONTAL_AXIS);
                    PoleType.Shape shape = type.getShape();
                    ModelFile model;
                    int xRot, yRot;

                    switch (shape) {
                        case HALF:
                            if (type == PoleType.T_HALF) model = topslab;
                            else if (type == PoleType.B_HALF) model = bottomslab;
                            else model = halfpole;
                            break;
                        case CROSS: model = cross; break;
                        case FILL: model = fill; break;
                        case BLOCK: model = full; break;
                        default: model = corner; break;
                    }
                    if (type == PoleType.BL_CORNER || type == PoleType.BR_CORNER || type == PoleType.TL_FILL || type == PoleType.TR_FILL) {
                        xRot = 180;
                    } else {
                        xRot = 0;
                    }
                    if (type == PoleType.TL_CORNER || type == PoleType.BR_CORNER || type == PoleType.L_HALF || type == PoleType.TL_BR) {
                        yRot = 270;
                    } else if (type == PoleType.TR_CORNER || type == PoleType.BL_CORNER || type == PoleType.R_HALF || type == PoleType.TR_BL) {
                        yRot = 90;
                    } else if (type == PoleType.TL_FILL || type == PoleType.BL_FILL) {
                        yRot = 180;
                    } else {
                        yRot = 0;
                    }
                    if (type != PoleType.T_HALF && type != PoleType.B_HALF && type != PoleType.FULL) {
                        yRot = axis == Direction.Axis.X ? yRot + 90 : yRot;
                    }
                    yRot = yRot == 360 ? 0 : yRot;
                    boolean uvlock = yRot != 0 || xRot != 0; // Don't set uvlock for states that have no rotation
                    return ConfiguredModel.builder()
                            .modelFile(model)
                            .rotationY(yRot)
                            .rotationX(xRot)
                            .uvLock(uvlock)
                            .build();
                }, OrnamentBeam.WATERLOGGED);
    }
}
