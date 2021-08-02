package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.blocks.OrnamentBeam;
import com.androsa.ornamental.blocks.OrnamentPole;
import com.androsa.ornamental.blocks.PoleType;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fmllegacy.RegistryObject;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public abstract class OrnamentalBlockStateProvider extends BlockStateProvider {

    private final OrnamentalBlockModelProvider blockModels;
    private final String modID;

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

    public void stairsBasic(Supplier<? extends StairBlock> block, String name) {
        stairsBlock(block.get(), locVanilla(name));
    }

    public void stairsMissing(Supplier<? extends StairBlock> block) {
        stairsBlock(block.get(), locOrnament("missingno"));
    }

    public void stairsColumn(Supplier<? extends StairBlock> block, String side, String end) {
        stairsBlock(block.get(), locVanilla(side), locVanilla(end), locVanilla(end));
    }

    public void slabBasic(Supplier<? extends SlabBlock> block, String name) {
        slabBlock(block.get(), locVanilla(name), locVanilla(name));
    }

    public void slabMissing(Supplier<? extends SlabBlock> block) {
        slabBlock(block.get(), locOrnament("missingno"), locOrnament("missingno"));
    }

    public void slabModel(Supplier<? extends SlabBlock> block, String model, String name) {
        slabBlock(block.get(), locVanilla(model), locVanilla(name));
    }

    public void slabColumn(Supplier<? extends SlabBlock> block, String blockname, String side, String end) {
        slabBlock(block.get(), locVanilla(blockname), locVanilla(side), locVanilla(end), locVanilla(end));
    }

    public void fenceBasic(Supplier<? extends FenceBlock> block, String name) {
        fenceBlock(block.get(), locVanilla(name));
    }

    public void fenceMissing(Supplier<? extends FenceBlock> block) {
        fenceBlock(block.get(), locOrnament("missingno"));
    }

    public void fenceColumn(Supplier<? extends FenceBlock> block, String side, String top) {
        String baseName = block.get().getRegistryName().toString();
        fourWayBlock(block.get(),
                models().fencePostColumn(baseName + "_post", locVanilla(side), locVanilla(top)),
                models().fenceSide(baseName + "_side", locVanilla(side)));
    }

    public void trapdoorBasic(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoorBlock(block.get(), locOrnament(name + "_trapdoor"), true);
    }

    public void trapdoorMissing(Supplier<? extends TrapDoorBlock> block) {
        trapdoorBlock(block.get(), locOrnament("missingno"), false);
    }

    public void trapdoorVanilla(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoorBlock(block.get(), locVanilla(name), false);
    }

    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, String name) {
        fenceGateBlock(block.get(), locVanilla(name));
    }

    public void fenceGateMissing(Supplier<? extends FenceGateBlock> block) {
        fenceGateBlock(block.get(), locOrnament("missingno"));
    }

    public void fenceGateColumn(Supplier<? extends FenceGateBlock> block, String side, String top) {
        ModelFile gate =         models().fenceGateColumn(block.get().getRegistryName().toString(), locVanilla(side), locVanilla(top));
        ModelFile gateOpen =     models().fenceGateOpenColumn(block.get().getRegistryName().toString() + "_open", locVanilla(side), locVanilla(top));
        ModelFile gateWall =     models().fenceGateWallColumn(block.get().getRegistryName().toString() + "_wall", locVanilla(side), locVanilla(top));
        ModelFile gateWallOpen = models().fenceGateWallOpenColumn(block.get().getRegistryName().toString() + "_wall_open", locVanilla(side), locVanilla(top));

        fenceGateBlock(block.get(), gate, gateOpen, gateWall, gateWallOpen);
    }

    public void doorBasic(Supplier<? extends DoorBlock> block, String name) {
        doorBlock(block.get(), locOrnament(name + "_door_bottom"), locOrnament(name + "_door_top"));
    }

    public void doorMissing(Supplier<? extends DoorBlock> block) {
        doorBlock(block.get(), locOrnament("missingno"), locOrnament("missingno"));
    }

    public void doorBasicVanilla(Supplier<? extends DoorBlock> block, String name) {
        doorBlock(block.get(), locVanilla(name), locVanilla(name));
    }

    public void poleBasic(RegistryObject<? extends OrnamentPole> block, String name) {
        poleBasic(block, name, name);
    }

    public void poleBasic(RegistryObject<? extends OrnamentPole> block, String name, String fullblock) {
        ModelFile corner = models().poleCorner(block.getId().toString() + "_corner", locVanilla(name));
        ModelFile half   = models().slabVertical(block.getId().toString() + "_half", locVanilla(name));
        ModelFile cross  = models().poleCross(block.getId().toString() + "_cross", locVanilla(name));
        ModelFile fill   = models().stairsStraightSide(block.getId().toString() + "_fill", locVanilla(name));
        ModelFile full   = models().getExistingFile(locVanilla(fullblock));

        poleBlock(block, corner, half, cross, fill, full);
    }

    public void poleColumn(RegistryObject<? extends OrnamentPole> block, String name) {
        ModelFile corner = models().poleCornerColumn(block.getId().toString() + "_corner", locVanilla(name + "_side"), locVanilla(name + "_top"));
        ModelFile half   = models().slabVerticalColumn(block.getId().toString() + "_half", locVanilla(name + "_side"), locVanilla(name + "_top"));
        ModelFile cross  = models().poleCrossColumn(block.getId().toString() + "_cross", locVanilla(name + "_side"), locVanilla(name + "_top"));
        ModelFile fill   = models().stairsStraightSideColumn(block.getId().toString() + "_fill", locVanilla(name + "_side"), locVanilla(name + "_top"));
        ModelFile full   = models().getExistingFile(locVanilla(name));

        poleBlock(block, corner, half, cross, fill, full);
    }

    public void poleMissing(RegistryObject<? extends OrnamentPole> block, String name) {
        ModelFile corner = models().poleCorner(block.getId().toString() + "_corner", locOrnament(name));
        ModelFile half   = models().slabVertical(block.getId().toString() + "_half", locOrnament(name));
        ModelFile cross  = models().poleCross(block.getId().toString() + "_cross", locOrnament(name));
        ModelFile fill   = models().stairsStraightSide(block.getId().toString() + "_fill", locOrnament(name));
        ModelFile full   = models().getExistingFile(locOrnament(name));

        poleBlock(block, corner, half, cross, fill, full);
    }

    public void beamBasic(RegistryObject<? extends OrnamentBeam> block, String base, String name, boolean vstairs, boolean vslab) {
        beamBasic(block, base, name, name, vstairs, vslab);
    }

    public void beamBasic(RegistryObject<? extends OrnamentBeam> block, String base, String name, String fullblock, boolean vstairs, boolean vslab) {
        String namebottom = base + "_slab";
        String nametop = namebottom + "_top";
        String namehalf = base + "_pole_half";
        String stairs = base + "_stairs";

        ModelFile corner = models().beamCorner(block.getId().toString() + "_corner", locVanilla(name));
        ModelFile top = models().getExistingFile(vslab ? locVanilla(nametop) : locOrnament(nametop));
        ModelFile bottom = models().getExistingFile(vslab ? locVanilla(namebottom) : locOrnament(namebottom));
        ModelFile side = models().getExistingFile(locOrnament(namehalf));
        ModelFile cross  = models().beamCross(block.getId().toString() + "_cross", locVanilla(name));
        ModelFile fill   = models().getExistingFile(vstairs ? locVanilla(stairs) : locOrnament(stairs));
        ModelFile full   = models().getExistingFile(locVanilla(fullblock));

        beamBlock(block, corner, top, bottom, side, cross, fill, full);
    }

    public void beamColumn(RegistryObject<? extends OrnamentBeam> block, String base, String name, boolean vstairs, boolean vslab) {
        String namebottom = base + "_slab";
        String nametop = namebottom + "_top";
        String namehalf = base + "_pole_half";
        String stairs = base + "_stairs";

        ModelFile corner = models().beamCornerColumn(block.getId().toString() + "_corner", locVanilla(name + "_side"), locVanilla(name + "_top"));
        ModelFile top = models().getExistingFile(vslab ? locVanilla(nametop) : locOrnament(nametop));
        ModelFile bottom = models().getExistingFile(vslab ? locVanilla(namebottom) : locOrnament(namebottom));
        ModelFile side = models().getExistingFile(vslab ? locVanilla(namebottom) : locOrnament(namehalf));
        ModelFile cross  = models().beamCrossColumn(block.getId().toString() + "_cross", locVanilla(name + "_side"), locVanilla(name + "_top"));
        ModelFile fill   = models().getExistingFile(vstairs ? locVanilla(stairs) : locOrnament(stairs));
        ModelFile full   = models().getExistingFile(locVanilla(name));

        beamBlock(block, corner, top, bottom, side, cross, fill, full);
    }

    public void beamMissing(RegistryObject<? extends OrnamentBeam> block, String name) {
        String namebottom = name + "_slab";
        String nametop = namebottom + "_top";
        String namehalf = name + "_pole_half";
        String stairs = name + "_stairs";

        ModelFile corner = models().beamCorner(block.getId().toString() + "_corner", locOrnament(name));
        ModelFile top = models().getExistingFile(locOrnament(nametop));
        ModelFile bottom = models().getExistingFile(locOrnament(namebottom));
        ModelFile side = models().getExistingFile(locOrnament(namehalf));
        ModelFile cross  = models().beamCross(block.getId().toString() + "_cross", locOrnament(name));
        ModelFile fill   = models().getExistingFile(locOrnament(stairs));
        ModelFile full   = models().getExistingFile(locOrnament(name));

        beamBlock(block, corner, top, bottom, side, cross, fill, full);
    }

    public void wallBasic(Supplier<? extends WallBlock> block, String name) {
        wallBlock(block.get(), locVanilla(name));
    }

    public void wallMissing(Supplier<? extends WallBlock> block, String name) {
        wallBlock(block.get(), locOrnament(name));
    }

    public void wallColumn(RegistryObject<? extends WallBlock> block, String side, String end) {
        String basename = block.getId().toString();
        ModelFile wallpost = models().wallPostColumn(basename + "_post", locVanilla(side), locVanilla(end));
        ModelFile wallside = models().wallSideColumn(basename + "_side", locVanilla(side), locVanilla(end));
        ModelFile walltall = models().wallSideTallColumn(basename + "_side_tall", locVanilla(side), locVanilla(end));

        wallBlock(block.get(), wallpost, wallside, walltall);
    }

    public void poleBlock(Supplier<? extends OrnamentPole> block, ModelFile corner, ModelFile half, ModelFile cross, ModelFile fill, ModelFile full) {
        getVariantBuilder(block.get())
                .forAllStatesExcept(state -> {
                    PoleType type = state.getValue(OrnamentPole.TYPE);
                    PoleType.Shape shape = type.getShape();
                    ModelFile model;
                    int yRot;

                    switch (shape) {
                        case HALF: model = half; break;
                        case CROSS: model = cross; break;
                        case FILL: model = fill; break;
                        case BLOCK: model = full; break;
                        default: model = corner; break;
                    }
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

    public void beamBlock(Supplier<? extends OrnamentBeam> block, ModelFile corner, ModelFile topslab, ModelFile bottomslab, ModelFile halfpole, ModelFile cross, ModelFile fill, ModelFile full) {
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
