package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.blocks.*;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Either;
import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class OrnamentalBlockStateProvider {

    protected final BlockModelGenerators blockModels;
    private final String modID;
    private final String parentID;

    public static final Map<Integer, Quadrant> INT_TO_ROT = ImmutableMap.of(
            0, Quadrant.R0,
            90, Quadrant.R90,
            180, Quadrant.R180,
            270, Quadrant.R270);

    public static final Identifier SOLID = Identifier.withDefaultNamespace("solid");
    public static final Identifier TRANSLUCENT = Identifier.withDefaultNamespace("translucent");
    public static final Identifier CUTOUT = Identifier.withDefaultNamespace("cutout");
    public static final Identifier CUTOUT_MIPPED = Identifier.withDefaultNamespace("cutout_mipped");

    public static final ModelTemplate FENCE_POST = makeUtilTemplate("fence_post", "_post", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate FENCE_INVENTORY = makeUtilTemplate("fence_inventory", "_inventory", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate FENCE_GATE_OPEN = makeUtilTemplate("fence_gate_open", "_open", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate FENCE_GATE_CLOSED = makeUtilTemplate("fence_gate", null, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate FENCE_GATE_WALL_OPEN = makeUtilTemplate("fence_gate_wall_open", "_wall_open", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate FENCE_GATE_WALL_CLOSED = makeUtilTemplate("fence_gate_wall", "_wall", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate DOOR_BOTTOM_LEFT = makeUtilTemplate("door_bottom_left", "_bottom_left", TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate DOOR_BOTTOM_LEFT_OPEN = makeUtilTemplate("door_bottom_left_open", "_bottom_left_open", TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate DOOR_BOTTOM_RIGHT = makeUtilTemplate("door_bottom_right", "_bottom_right", TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate DOOR_BOTTOM_RIGHT_OPEN = makeUtilTemplate("door_bottom_right_open", "_bottom_right_open", TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate DOOR_TOP_LEFT = makeUtilTemplate("door_top_left", "_top_left", TextureSlot.TOP, TextureSlot.SIDE);
    public static final ModelTemplate DOOR_TOP_LEFT_OPEN = makeUtilTemplate("door_top_left_open", "_top_left_open", TextureSlot.TOP, TextureSlot.SIDE);
    public static final ModelTemplate DOOR_TOP_RIGHT = makeUtilTemplate("door_top_right", "_top_right", TextureSlot.TOP, TextureSlot.SIDE);
    public static final ModelTemplate DOOR_TOP_RIGHT_OPEN = makeUtilTemplate("door_top_right_open", "_top_right_open", TextureSlot.TOP, TextureSlot.SIDE);
    public static final ModelTemplate POLE_WHOLE = makeUtilTemplate("pole_whole", "_whole", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate POLE_HORIZONTAL = makeUtilTemplate("pole_horizontal", "_horizontal", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate POLE_VERTICAL = makeUtilTemplate("pole_vertical", "_vertical", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate POLE_CORNER = makeUtilTemplate("pole_corner", "_corner", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate POLE_INVENTORY = makeUtilTemplate("pole_inventory", "_inventory", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate BEAM_WHOLE = makeUtilTemplate("beam_whole", "_whole", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate BEAM_HORIZONTAL = makeUtilTemplate("beam_horizontal", "_horizontal", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate BEAM_VERTICAL = makeUtilTemplate("beam_vertical", "_vertical", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate BEAM_CORNER = makeUtilTemplate("beam_corner", "_corner", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate BEAM_INVENTORY = makeUtilTemplate("beam_inventory", "_inventory", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate WALL_POST = makeUtilTemplate("wall_post", "_post", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate WALL_SIDE = makeUtilTemplate("wall_side", "_side", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate WALL_SIDE_TALL = makeUtilTemplate("wall_side_tall", "_side_tall", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate WALL_INVENTORY = makeUtilTemplate("wall_inventory", "_inventory", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SADDLE_DOOR_LEFT = makeUtilTemplate("saddle_door_left", "_left", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SADDLE_DOOR_LEFT_OPEN = makeUtilTemplate("saddle_door_left_open", "_left_open", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SADDLE_DOOR_RIGHT = makeUtilTemplate("saddle_door_right", "_right", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SADDLE_DOOR_RIGHT_OPEN = makeUtilTemplate("saddle_door_right_open", "_right_open", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SADDLE_DOOR_INVENTORY = makeUtilTemplate("saddle_door_inventory", "_inventory", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SUPPORT_BASE = makeUtilTemplate("support_base", "_base", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SUPPORT_BASE_TOP = makeUtilTemplate("support_base_top", "_base_top", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SUPPORT_X = makeUtilTemplate("support_horizontal_x", "_horizontal_x", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SUPPORT_X_TOP = makeUtilTemplate("support_horizontal_x_top", "_horizontal_x_top", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SUPPORT_Z = makeUtilTemplate("support_horizontal_z", "_horizontal_z", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SUPPORT_Z_TOP = makeUtilTemplate("support_horizontal_z_top", "_horizontal_z_top", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SUPPORT_Y = makeUtilTemplate("support_vertical", "_vertical", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SUPPORT_Y_TOP = makeUtilTemplate("support_vertical_top", "_vertical_top", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    public static final ModelTemplate SUPPORT_INVENTORY = makeUtilTemplate("support_inventory", "_inventory", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);

    private static ModelTemplate makeUtilTemplate(String model, String suffix, TextureSlot... slots) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(OrnamentalMod.MODID, "block/util/" + model)), suffix != null ? Optional.of(suffix) : Optional.empty(), slots);
    }

    public OrnamentalBlockStateProvider(BlockModelGenerators generator, String modid, String parent) {
        this.blockModels = generator;
        this.modID = modid;
        this.parentID = parent;
    }

    public abstract void runBlockGen();

    protected Identifier locMod(String name) {
        return Identifier.fromNamespaceAndPath(modID, "block/" + name);
    }

    protected Identifier locParent(String name) {
        return Identifier.fromNamespaceAndPath(parentID, "block/" + name);
    }

    protected TextureMapping makeMapping(Identifier side, Identifier bottom, Identifier top) {
        return new TextureMapping()
                .put(TextureSlot.SIDE, side)
                .put(TextureSlot.BOTTOM, bottom)
                .put(TextureSlot.TOP, top);
    }

    /* Stairs */
    public void stairsBasic(Supplier<? extends OrnamentStair> block, String name) {
        stairsBasic(block, name, ModelTemplates.STAIRS_INNER, ModelTemplates.STAIRS_STRAIGHT, ModelTemplates.STAIRS_OUTER);
    }

    public void stairsBasic(Supplier<? extends OrnamentStair> block, String name, ModelTemplate inner, ModelTemplate straight, ModelTemplate outer) {
        stairsBasic(block, locParent(name), inner, straight, outer);
    }

    public void stairsBasic(Supplier<? extends OrnamentStair> block, Identifier name, ModelTemplate inner, ModelTemplate straight, ModelTemplate outer) {
        stairs(block, inner, straight, outer, name, name, name);
    }

    public void stairsColumn(Supplier<? extends OrnamentStair> block, String side, String end) {
        stairs(block, ModelTemplates.STAIRS_INNER, ModelTemplates.STAIRS_STRAIGHT, ModelTemplates.STAIRS_OUTER, locParent(side), locParent(end), locParent(end));
    }

    public void stairs(Supplier<? extends OrnamentStair> block, ModelTemplate innerModel, ModelTemplate straightModel, ModelTemplate outerModel, Identifier side, Identifier bottom, Identifier top) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        Identifier inner = innerModel.create(block.get(), mapping, blockModels.modelOutput);
        Identifier straight = straightModel.create(block.get(), mapping, blockModels.modelOutput);
        Identifier outer = outerModel.create(block.get(), mapping, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(BlockModelGenerators.createStairs(block.get(),
                BlockModelGenerators.plainVariant(inner),
                BlockModelGenerators.plainVariant(straight),
                BlockModelGenerators.plainVariant(outer)));
        blockModels.registerSimpleItemModel(block.get().asItem(), straight);
    }

    /* Slabs */
    public void slabBasic(Supplier<? extends SlabBlock> block, Supplier<? extends Block> blockname) {
        slabBasic(block, blockname, SOLID);
    }

    public void slabBasic(Supplier<? extends SlabBlock> block, Supplier<? extends Block> blockname, Identifier type) {
        String name = BuiltInRegistries.BLOCK.getKey(blockname.get()).getPath();
        slab(block, ModelTemplates.SLAB_BOTTOM, ModelTemplates.SLAB_TOP, Either.right(blockname), locParent(name), locParent(name), locParent(name), type);
    }

    public void slabModel(Supplier<? extends SlabBlock> block, Supplier<? extends Block> blockname, String name, Identifier type) {
        slab(block, ModelTemplates.SLAB_BOTTOM, ModelTemplates.SLAB_TOP, Either.right(blockname), locParent(name), locParent(name), locParent(name), type);
    }

    public void slabModel(Supplier<? extends SlabBlock> block, String blockname, Identifier name, Identifier type) {
        slab(block, ModelTemplates.SLAB_BOTTOM, ModelTemplates.SLAB_TOP, Either.left(locMod(blockname)), name, name, name, type);
    }

    public void slabColumn(Supplier<? extends SlabBlock> block, Supplier<? extends Block> blockname, String side, String end, Identifier type) {
        slab(block, ModelTemplates.SLAB_BOTTOM, ModelTemplates.SLAB_TOP, Either.right(blockname), locParent(side), locParent(end), locParent(end), type);
    }

    public void slab(Supplier<? extends SlabBlock> block, ModelTemplate bottomModel, ModelTemplate topModel, Either<Identifier, Supplier<? extends Block>> doubleModel, Identifier side, Identifier bottom, Identifier top, Identifier type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        Identifier bm, tm, dm;
        if (type != SOLID) {
            bm = bottomModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            tm = topModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            dm = new ExtendedModelTemplateBuilder()
                    .parent(doubleModel.map(
                            r -> r,
                            b -> ModelLocationUtils.getModelLocation(b.get())))
                    .suffix("_double")
                    .renderType(type)
                    .build().create(block.get(), mapping, blockModels.modelOutput);
        } else {
            bm = bottomModel.create(block.get(), mapping, blockModels.modelOutput);
            tm = topModel.create(block.get(), mapping, blockModels.modelOutput);
            dm = doubleModel.map(
                    r -> r,
                    b -> ModelLocationUtils.getModelLocation(b.get()));
        }


        BlockModelDefinitionGenerator slabgen = MultiVariantGenerator.dispatch(block.get())
                .with(PropertyDispatch.initial(BlockStateProperties.SLAB_TYPE)
                        .select(SlabType.BOTTOM, BlockModelGenerators.plainVariant(bm))
                        .select(SlabType.TOP, BlockModelGenerators.plainVariant(tm))
                        .select(SlabType.DOUBLE, BlockModelGenerators.plainVariant(dm)));
        blockModels.blockStateOutput.accept(slabgen);
        blockModels.registerSimpleItemModel(block.get(), bm);
    }

    /* Fences */
    public void fenceBasic(Supplier<? extends FenceBlock> block, String name) {
        fenceBasic(block, locParent(name));
    }

    public void fenceBasic(Supplier<? extends FenceBlock> block, Identifier name) {
        fence(block, FENCE_POST, ModelTemplates.CUSTOM_FENCE_SIDE_NORTH, ModelTemplates.CUSTOM_FENCE_SIDE_EAST, ModelTemplates.CUSTOM_FENCE_SIDE_SOUTH, ModelTemplates.CUSTOM_FENCE_SIDE_WEST, FENCE_INVENTORY, name, name, name);
    }

    public void fenceColumn(Supplier<? extends FenceBlock> block, String side, String top) {
        fence(block, FENCE_POST, ModelTemplates.CUSTOM_FENCE_SIDE_NORTH, ModelTemplates.CUSTOM_FENCE_SIDE_EAST, ModelTemplates.CUSTOM_FENCE_SIDE_SOUTH, ModelTemplates.CUSTOM_FENCE_SIDE_WEST, FENCE_INVENTORY, locParent(side), locParent(top), locParent(top));
    }

    public void fence(Supplier<? extends FenceBlock> block, ModelTemplate post, ModelTemplate north, ModelTemplate east, ModelTemplate south, ModelTemplate west, ModelTemplate inventory, Identifier side, Identifier top, Identifier bottom) {
        TextureMapping postMapping = makeMapping(side, bottom, top).put(TextureSlot.PARTICLE, side);
        TextureMapping railMapping = new TextureMapping().put(TextureSlot.TEXTURE, side);
        MultiVariant p = BlockModelGenerators.plainVariant(post.create(block.get(), postMapping, blockModels.modelOutput));
        MultiVariant n = BlockModelGenerators.plainVariant(north.create(block.get(), railMapping, blockModels.modelOutput));
        MultiVariant e = BlockModelGenerators.plainVariant(east.create(block.get(), railMapping, blockModels.modelOutput));
        MultiVariant s = BlockModelGenerators.plainVariant(south.create(block.get(), railMapping, blockModels.modelOutput));
        MultiVariant w = BlockModelGenerators.plainVariant(west.create(block.get(), railMapping, blockModels.modelOutput));
        Identifier i = inventory.create(block.get(), postMapping, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(BlockModelGenerators.createCustomFence(block.get(), p, n, e, s, w));
        blockModels.registerSimpleItemModel(block.get(), i);
    }

    /* Trapdoors */
    public void trapdoorBasic(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoor(block, locMod(name + "_trapdoor"), true, CUTOUT);
    }

    public void trapdoorParent(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoor(block, locParent(name), false, CUTOUT);
    }

    public void trapdoorBasic(Supplier<? extends TrapDoorBlock> block, String name, Identifier type) {
        trapdoor(block, locMod(name + "_trapdoor"), true, type);
    }

    public void trapdoorParent(Supplier<? extends TrapDoorBlock> block, String name, Identifier type) {
        trapdoor(block, locParent(name), false, type);
    }

    public void trapdoor(Supplier<? extends TrapDoorBlock> block, Identifier texture, boolean orientable, Identifier type) {
        TextureMapping mapping = new TextureMapping().put(TextureSlot.TEXTURE, texture);
        Identifier top, bottom, open;
        if (orientable) {
            top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        } else {
            top = ModelTemplates.TRAPDOOR_TOP.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            bottom = ModelTemplates.TRAPDOOR_BOTTOM.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            open = ModelTemplates.TRAPDOOR_OPEN.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        }
        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createTrapdoor(block.get(),
                BlockModelGenerators.plainVariant(top),
                BlockModelGenerators.plainVariant(bottom),
                BlockModelGenerators.plainVariant(open)));
        this.blockModels.registerSimpleItemModel(block.get(), bottom);
    }

    /* Fence Gates */
    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, String name) {
        fenceGateBasic(block, locParent(name), SOLID);
    }

    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, String name, Identifier type) {
        fenceGateBasic(block, locParent(name), type);
    }

    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, Identifier name, Identifier type) {
        fenceGate(block, FENCE_GATE_CLOSED, FENCE_GATE_OPEN, FENCE_GATE_WALL_CLOSED, FENCE_GATE_WALL_OPEN, name, name, name, type);
    }

    public void fenceGateColumn(Supplier<? extends FenceGateBlock> block, String side, String top, Identifier type) {
        fenceGate(block, FENCE_GATE_CLOSED, FENCE_GATE_OPEN, FENCE_GATE_WALL_CLOSED, FENCE_GATE_WALL_OPEN, locParent(side), locParent(top), locParent(top), type);
    }

    public void fenceGate(Supplier<? extends FenceGateBlock> block, ModelTemplate gate, ModelTemplate opengate, ModelTemplate wall, ModelTemplate openwall, Identifier side, Identifier top, Identifier bottom, Identifier type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        Identifier g, go, w, wo;

        if (type != SOLID) {
            g = gate.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            go = opengate.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            w = wall.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            wo = openwall.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        } else {
            g = gate.create(block.get(), mapping, blockModels.modelOutput);
            go = opengate.create(block.get(), mapping, blockModels.modelOutput);
            w = wall.create(block.get(), mapping, blockModels.modelOutput);
            wo = openwall.create(block.get(), mapping, blockModels.modelOutput);
        }
        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createFenceGate(block.get(),
                BlockModelGenerators.plainVariant(go),
                BlockModelGenerators.plainVariant(g),
                BlockModelGenerators.plainVariant(wo),
                BlockModelGenerators.plainVariant(w),
                true));
        this.blockModels.registerSimpleItemModel(block.get(), g);
    }

    /* Doors */
    public void doorBasic(Supplier<? extends DoorBlock> block, String name) {
        doorBasic(block, name, CUTOUT);
    }

    public void doorHidden(Supplier<? extends DoorBlock> block, String name) {
        doorHidden(block, name, CUTOUT);
    }

    public void doorBasic(Supplier<? extends DoorBlock> block, String name, Identifier type) {
        doorBasic(block, locMod(name + "_door_bottom"), locMod(name + "_door_bottom"), locMod(name + "_door_top"), locMod(name + "_door_top"), type);
    }

    public void doorHidden(Supplier<? extends DoorBlock> block, String name, Identifier type) {
        doorBasic(block, locParent(name), locParent(name), locParent(name), locParent(name), type);
    }

    public void doorBasic(Supplier<? extends DoorBlock> block, Identifier name, Identifier type) {
        doorBasic(block, name, name, name, name, type);
    }

    public void doorBasic(Supplier<? extends DoorBlock> block, Identifier bottomside, Identifier bottom, Identifier topside, Identifier top, Identifier type) {
        door(block, DOOR_BOTTOM_LEFT, DOOR_BOTTOM_LEFT_OPEN, DOOR_BOTTOM_RIGHT, DOOR_BOTTOM_RIGHT_OPEN, DOOR_TOP_LEFT, DOOR_TOP_LEFT_OPEN, DOOR_TOP_RIGHT, DOOR_TOP_RIGHT_OPEN, bottomside, bottom, topside, top, type);
    }

    public void door(Supplier<? extends DoorBlock> block, ModelTemplate bl, ModelTemplate blo, ModelTemplate br, ModelTemplate bro, ModelTemplate tl, ModelTemplate tlo, ModelTemplate tr, ModelTemplate tro, Identifier bottomside, Identifier bottom, Identifier topside, Identifier top, Identifier type) {
        TextureMapping bottomTex = new TextureMapping()
                .put(TextureSlot.SIDE, bottomside)
                .put(TextureSlot.BOTTOM, bottom);
        TextureMapping topTex = new TextureMapping()
                .put(TextureSlot.SIDE, topside)
                .put(TextureSlot.TOP, top);

        MultiVariant bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen;
        bottomLeft = BlockModelGenerators.plainVariant(bl.extend().renderType(type).build().create(block.get(), bottomTex, blockModels.modelOutput));
        bottomLeftOpen = BlockModelGenerators.plainVariant(blo.extend().renderType(type).build().create(block.get(), bottomTex, blockModels.modelOutput));
        bottomRight = BlockModelGenerators.plainVariant(br.extend().renderType(type).build().create(block.get(), bottomTex, blockModels.modelOutput));
        bottomRightOpen = BlockModelGenerators.plainVariant(bro.extend().renderType(type).build().create(block.get(), bottomTex, blockModels.modelOutput));
        topLeft = BlockModelGenerators.plainVariant(tl.extend().renderType(type).build().create(block.get(), topTex, blockModels.modelOutput));
        topLeftOpen = BlockModelGenerators.plainVariant(tlo.extend().renderType(type).build().create(block.get(), topTex, blockModels.modelOutput));
        topRight = BlockModelGenerators.plainVariant(tr.extend().renderType(type).build().create(block.get(), topTex, blockModels.modelOutput));
        topRightOpen = BlockModelGenerators.plainVariant(tro.extend().renderType(type).build().create(block.get(), topTex, blockModels.modelOutput));

        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createDoor(block.get(), bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen));
        this.blockModels.registerSimpleFlatItemModel(block.get().asItem());
    }

    /* Poles */
    public void poleBasic(Supplier<? extends OrnamentPole> block, String fullblock, String name) {
        poleBasic(block, fullblock, name, SOLID);
    }

    public void poleBasic(Supplier<? extends OrnamentPole> block, Supplier<? extends Block> fullblock, String name) {
        poleBasic(block, fullblock, name, SOLID);
    }

    public void poleBasic(Supplier<? extends OrnamentPole> block, String fullblock, String name, Identifier type) {
        poleBasic(block, Either.left(locMod(fullblock)), locMod(name), type);
    }

    public void poleBasic(Supplier<? extends OrnamentPole> block, Supplier<? extends Block> fullblock, String name, Identifier type) {
        poleBasic(block, Either.right(fullblock), locParent(name), type);
    }

    public void poleBasic(Supplier<? extends OrnamentPole> block, Either<Identifier, Supplier<? extends Block>> fullblock, Identifier name, Identifier type) {
        pole(block, POLE_WHOLE, POLE_HORIZONTAL, POLE_VERTICAL, POLE_CORNER, fullblock, name, name, name, type);
    }

    public void poleColumn(Supplier<? extends OrnamentPole> block, Either<Identifier, Supplier<? extends Block>> fullblock, String side, String top, Identifier type) {
        pole(block, POLE_WHOLE, POLE_HORIZONTAL, POLE_VERTICAL, POLE_CORNER, fullblock, locParent(top), locParent(top), locParent(side), type);
    }

    public void pole(Supplier<? extends OrnamentPole> block, ModelTemplate w, ModelTemplate h, ModelTemplate v, ModelTemplate c, Either<Identifier, Supplier<? extends Block>> full, Identifier top, Identifier bottom, Identifier side, Identifier type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        MultiVariant whole, horizon, vertical, corner, fullblock;
        Identifier inventory;

        if (type != SOLID) {
            whole = BlockModelGenerators.plainVariant(w.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            horizon = BlockModelGenerators.plainVariant(h.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            vertical = BlockModelGenerators.plainVariant(v.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            corner = BlockModelGenerators.plainVariant(c.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            fullblock = BlockModelGenerators.plainVariant(new ExtendedModelTemplateBuilder()
                    .parent(full.map(
                            r -> r,
                            b -> ModelLocationUtils.getModelLocation(b.get())))
                    .suffix("_full")
                    .renderType(type)
                    .build().create(block.get(), mapping, blockModels.modelOutput));
            inventory = POLE_INVENTORY.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        } else {
            whole = BlockModelGenerators.plainVariant(w.create(block.get(), mapping, blockModels.modelOutput));
            horizon = BlockModelGenerators.plainVariant(h.create(block.get(), mapping, blockModels.modelOutput));
            vertical = BlockModelGenerators.plainVariant(v.create(block.get(), mapping, blockModels.modelOutput));
            corner = BlockModelGenerators.plainVariant(c.create(block.get(), mapping, blockModels.modelOutput));
            fullblock = BlockModelGenerators.plainVariant(full.map(
                    r -> r,
                    b -> ModelLocationUtils.getModelLocation(b.get())));
            inventory = POLE_INVENTORY.create(block.get(), mapping, blockModels.modelOutput);
        }

        this.blockModels.blockStateOutput.accept(poleBlock(block, whole, horizon, vertical, corner, fullblock));
        this.blockModels.registerSimpleItemModel(block.get(), inventory);
    }

    /* Beams */
    public void beamBasic(Supplier<? extends OrnamentBeam> block, String fullblock, String name) {
        beamBasic(block, Either.left(locMod(fullblock)), locMod(name), SOLID);
    }

    public void beamBasic(Supplier<? extends OrnamentBeam> block, Supplier<? extends Block> fullblock, String name) {
        beamBasic(block, Either.right(fullblock), locParent(name), SOLID);
    }

    public void beamBasic(Supplier<? extends OrnamentBeam> block, String fullblock, String name, Identifier type) {
        beamBasic(block, Either.left(locMod(fullblock)), locMod(name), type);
    }

    public void beamBasic(Supplier<? extends OrnamentBeam> block, Supplier<? extends Block> fullblock, String name, Identifier type) {
        beamBasic(block, Either.right(fullblock), locParent(name), type);
    }

    public void beamBasic(Supplier<? extends OrnamentBeam> block, Either<Identifier, Supplier<? extends Block>> fullblock, Identifier name, Identifier type) {
        beam(block, BEAM_WHOLE, BEAM_HORIZONTAL, BEAM_VERTICAL, BEAM_CORNER, fullblock, name, name, name, type);
    }

    public void beamColumn(Supplier<? extends OrnamentBeam> block, Either<Identifier, Supplier<? extends Block>> fullblock, String top, String side, Identifier type) {
        beam(block, BEAM_WHOLE, BEAM_HORIZONTAL, BEAM_VERTICAL, BEAM_CORNER, fullblock, locParent(top), locParent(top), locParent(side), type);
    }

    public void beam(Supplier<? extends OrnamentBeam> block, ModelTemplate w, ModelTemplate h, ModelTemplate v, ModelTemplate c, Either<Identifier, Supplier<? extends Block>> full, Identifier top, Identifier bottom, Identifier side, Identifier type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        MultiVariant whole, horizon, vertical, corner, fullblock;
        Identifier inventory;

        if (type != SOLID) {
            whole = BlockModelGenerators.plainVariant(w.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            horizon = BlockModelGenerators.plainVariant(h.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            vertical = BlockModelGenerators.plainVariant(v.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            corner = BlockModelGenerators.plainVariant(c.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            fullblock = BlockModelGenerators.plainVariant(new ExtendedModelTemplateBuilder()
                    .parent(full.map(
                            r -> r,
                            b -> ModelLocationUtils.getModelLocation(b.get())))
                    .suffix("_full")
                    .renderType(type)
                    .build().create(block.get(), mapping, blockModels.modelOutput));
            inventory = BEAM_INVENTORY.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        } else {
            whole = BlockModelGenerators.plainVariant(w.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            horizon = BlockModelGenerators.plainVariant(h.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            vertical = BlockModelGenerators.plainVariant(v.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            corner = BlockModelGenerators.plainVariant(c.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            fullblock = BlockModelGenerators.plainVariant(full.map(
                    r -> r,
                    b -> ModelLocationUtils.getModelLocation(b.get())));
            inventory = BEAM_INVENTORY.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        }

        this.blockModels.blockStateOutput.accept(beamBlock(block, whole, horizon, vertical, corner, fullblock));
        this.blockModels.registerSimpleItemModel(block.get(), inventory);
    }

    /* Walls */
    public void wallBasic(Supplier<? extends WallBlock> block, String name) {
        wallBasic(block, locParent(name));
    }

    public void wallBasic(Supplier<? extends WallBlock> block, Identifier name) {
        wall(block, WALL_POST, WALL_SIDE, WALL_SIDE_TALL, name, name, name, SOLID);
    }

    public void wallBasic(Supplier<? extends WallBlock> block, Identifier name, Identifier type) {
        wall(block, WALL_POST, WALL_SIDE, WALL_SIDE_TALL, name, name, name, type);
    }

    public void wallColumn(Supplier<? extends WallBlock> block, String side, String end, Identifier type) {
        wall(block, WALL_POST, WALL_SIDE, WALL_SIDE_TALL, locParent(side), locParent(end), locParent(end), type);
    }

    public void wall(Supplier<? extends WallBlock> block, ModelTemplate post, ModelTemplate sidewall, ModelTemplate sidetall, Identifier side, Identifier top, Identifier bottom, Identifier type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        MultiVariant wallpost,  wallside, walltall;
        Identifier inventory;

        if (type != SOLID) {
            wallpost = BlockModelGenerators.plainVariant(post.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            wallside = BlockModelGenerators.plainVariant(sidewall.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            walltall = BlockModelGenerators.plainVariant(sidetall.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            inventory = WALL_INVENTORY.create(block.get(), mapping, blockModels.modelOutput);
        } else {
            wallpost = BlockModelGenerators.plainVariant(post.create(block.get(), mapping, blockModels.modelOutput));
            wallside = BlockModelGenerators.plainVariant(sidewall.create(block.get(), mapping, blockModels.modelOutput));
            walltall = BlockModelGenerators.plainVariant(sidetall.create(block.get(), mapping, blockModels.modelOutput));
            inventory = WALL_INVENTORY.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        }
        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createWall(block.get(), wallpost, wallside, walltall));
        this.blockModels.registerSimpleItemModel(block.get(), inventory);
    }

    /* Saddle Doors */
    public void saddleDoorBasic(Supplier<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoor(block, locMod(name + "_trapdoor"), CUTOUT);
    }

    public void saddleDoorBasic(Supplier<? extends OrnamentSaddleDoor> block, Identifier name) {
        saddleDoor(block, name, CUTOUT);
    }

    public void saddleDoorBasic(Supplier<? extends OrnamentSaddleDoor> block, String name, Identifier type) {
        saddleDoor(block, locMod(name + "_trapdoor"), type);
    }

    public void saddleDoorBasic(Supplier<? extends OrnamentSaddleDoor> block, Identifier name, Identifier type) {
        saddleDoor(block, name, type);
    }

    public void saddleDoorHidden(Supplier<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoor(block, locParent(name), CUTOUT);
    }

    public void saddleDoor(Supplier<? extends OrnamentSaddleDoor> block, Identifier name, Identifier type) {
        saddleDoor(block, SADDLE_DOOR_LEFT, SADDLE_DOOR_LEFT_OPEN, SADDLE_DOOR_RIGHT, SADDLE_DOOR_RIGHT_OPEN, name, name, name, type);
    }

    public void saddleDoor(Supplier<? extends OrnamentSaddleDoor> block, ModelTemplate leftDoor, ModelTemplate leftDoorOpen, ModelTemplate rightDoor, ModelTemplate rightDoorOpen, Identifier side, Identifier bottom, Identifier top, Identifier type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        MultiVariant left = BlockModelGenerators.plainVariant(leftDoor.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
        MultiVariant leftOpen = BlockModelGenerators.plainVariant(leftDoorOpen.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
        MultiVariant right = BlockModelGenerators.plainVariant(rightDoor.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
        MultiVariant rightOpen = BlockModelGenerators.plainVariant(rightDoorOpen.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
        Identifier inventory = SADDLE_DOOR_INVENTORY.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);

        this.blockModels.blockStateOutput.accept(saddleDoorBlock(block, left, leftOpen, right, rightOpen));
        this.blockModels.registerSimpleItemModel(block.get(), inventory);
    }

    /* Supports */
    public void supportBasic(Supplier<? extends OrnamentSupport> block, String name) {
        supportBasic(block, name, SOLID);
    }

    public void supportBasic(Supplier<? extends OrnamentSupport> block, String name, Identifier type) {
        supportBasic(block, locParent(name), type);
    }

    public void supportBasic(Supplier<? extends OrnamentSupport> block, Identifier name, Identifier type) {
        support(block, SUPPORT_BASE, SUPPORT_BASE_TOP, SUPPORT_Y, SUPPORT_Y_TOP, SUPPORT_X, SUPPORT_X_TOP, SUPPORT_Z, SUPPORT_Z_TOP, name, name, name, type);
    }

    public void supportColumn(Supplier<? extends OrnamentSupport> block, String side, String top, Identifier type) {
        support(block, SUPPORT_BASE, SUPPORT_BASE_TOP, SUPPORT_Y, SUPPORT_Y_TOP, SUPPORT_X, SUPPORT_X_TOP, SUPPORT_Z, SUPPORT_Z_TOP, locParent(side), locParent(top), locParent(top), type);
    }

    public void support(Supplier<? extends OrnamentSupport> block, ModelTemplate baseModel, ModelTemplate baseTModel, ModelTemplate yModel, ModelTemplate yTModel, ModelTemplate xModel, ModelTemplate xTModel, ModelTemplate zModel, ModelTemplate zTModel, Identifier side, Identifier bottom, Identifier top, Identifier type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        MultiVariant base, baseTop, vertical, verticalTop ,horizontalX, horizontalXTop, horizontalZ, horizontalZTop;
        Identifier inventory;

        if (type != SOLID) {
            base = BlockModelGenerators.plainVariant(baseModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            baseTop = BlockModelGenerators.plainVariant(baseTModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            vertical = BlockModelGenerators.plainVariant(yModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            verticalTop = BlockModelGenerators.plainVariant(yTModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            horizontalX = BlockModelGenerators.plainVariant(xModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            horizontalXTop = BlockModelGenerators.plainVariant(xTModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            horizontalZ = BlockModelGenerators.plainVariant(zModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            horizontalZTop = BlockModelGenerators.plainVariant(zTModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput));
            inventory = SUPPORT_INVENTORY.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        } else {
            base = BlockModelGenerators.plainVariant(baseModel.create(block.get(), mapping, blockModels.modelOutput));
            baseTop = BlockModelGenerators.plainVariant(baseTModel.create(block.get(), mapping, blockModels.modelOutput));
            vertical = BlockModelGenerators.plainVariant(yModel.create(block.get(), mapping, blockModels.modelOutput));
            verticalTop = BlockModelGenerators.plainVariant(yTModel.create(block.get(), mapping, blockModels.modelOutput));
            horizontalX = BlockModelGenerators.plainVariant(xModel.create(block.get(), mapping, blockModels.modelOutput));
            horizontalXTop = BlockModelGenerators.plainVariant(xTModel.create(block.get(), mapping, blockModels.modelOutput));
            horizontalZ = BlockModelGenerators.plainVariant(zModel.create(block.get(), mapping, blockModels.modelOutput));
            horizontalZTop = BlockModelGenerators.plainVariant(zTModel.create(block.get(), mapping, blockModels.modelOutput));
            inventory = SUPPORT_INVENTORY.create(block.get(), mapping, blockModels.modelOutput);
        }

        this.blockModels.blockStateOutput.accept(supportBlock(block, base, baseTop, vertical, verticalTop, horizontalX, horizontalXTop, horizontalZ, horizontalZTop));
        this.blockModels.registerSimpleItemModel(block.get(), inventory);
    }

    public BlockModelDefinitionGenerator saddleDoorBlock(Supplier<? extends OrnamentSaddleDoor> block, MultiVariant left, MultiVariant leftOpen, MultiVariant right, MultiVariant rightOpen) {
        return MultiVariantGenerator.dispatch(block.get())
                .with(PropertyDispatch.initial(
                                OrnamentSaddleDoor.FACING,
                                OrnamentSaddleDoor.HINGE,
                                OrnamentSaddleDoor.OPEN)
                        .select(
                                Direction.EAST, DoorHingeSide.LEFT, false,
                                left)
                        .select(
                                Direction.EAST, DoorHingeSide.LEFT, true,
                                leftOpen.with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
                        .select(
                                Direction.EAST, DoorHingeSide.RIGHT, false,
                                right)
                        .select(
                                Direction.EAST, DoorHingeSide.RIGHT, true,
                                rightOpen.with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
                        .select(
                                Direction.NORTH, DoorHingeSide.LEFT, false,
                                left.with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
                        .select(
                                Direction.NORTH, DoorHingeSide.LEFT, true,
                                leftOpen)
                        .select(
                                Direction.NORTH, DoorHingeSide.RIGHT, false,
                                right.with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
                        .select(
                                Direction.NORTH, DoorHingeSide.RIGHT, true,
                                rightOpen.with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
                        .select(
                                Direction.SOUTH, DoorHingeSide.LEFT, false,
                                left.with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
                        .select(
                                Direction.SOUTH, DoorHingeSide.LEFT, true,
                                leftOpen.with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
                        .select(
                                Direction.SOUTH, DoorHingeSide.RIGHT, false,
                                right.with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
                        .select(
                                Direction.SOUTH, DoorHingeSide.RIGHT, true,
                                rightOpen)
                        .select(
                                Direction.WEST, DoorHingeSide.LEFT, false,
                                left.with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
                        .select(
                                Direction.WEST, DoorHingeSide.LEFT, true,
                                leftOpen.with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
                        .select(
                                Direction.WEST, DoorHingeSide.RIGHT, false,
                                right.with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
                        .select(
                                Direction.WEST, DoorHingeSide.RIGHT, true,
                                rightOpen.with(VariantMutator.Y_ROT.withValue(Quadrant.R90))));
    }

    public BlockModelDefinitionGenerator poleBlock(Supplier<? extends OrnamentPole> block, MultiVariant whole, MultiVariant horizon, MultiVariant vertical, MultiVariant corner, MultiVariant fullblock) {
        MultiPartGenerator builder = MultiPartGenerator.multiPart(block.get());
        poleModelWhole(builder, whole, Quadrant.R0, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelWhole(builder, whole, Quadrant.R90, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelWhole(builder, whole, Quadrant.R180, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelWhole(builder, whole, Quadrant.R270, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelLength(builder, horizon, Quadrant.R0, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelLength(builder, horizon, Quadrant.R90, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        poleModelLength(builder, horizon, Quadrant.R180, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        poleModelLength(builder, horizon, Quadrant.R270, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelLength(builder, vertical, Quadrant.R0, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        poleModelLength(builder, vertical, Quadrant.R90, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelLength(builder, vertical, Quadrant.R180, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelLength(builder, vertical, Quadrant.R270, OrnamentPole.BOTTOM_LEFT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        poleModelCorner(builder, corner, Quadrant.R0, true, true, true, false);
        poleModelCorner(builder, corner, Quadrant.R90, true, true, false, true);
        poleModelCorner(builder, corner, Quadrant.R180, false, true, true, true);
        poleModelCorner(builder, corner, Quadrant.R270, true, false, true, true);

        return builder.with(
                BlockModelGenerators.condition().term(OrnamentPole.TOP_LEFT, true).term(OrnamentPole.TOP_RIGHT, true).term(OrnamentPole.BOTTOM_LEFT, true).term(OrnamentPole.BOTTOM_RIGHT, true),
                fullblock);
    }

    public void poleModelWhole(MultiPartGenerator builder, MultiVariant whole, Quadrant yRot, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        poleModelTri(builder, whole, yRot, main, true, c1, false, c2, false);
    }

    public void poleModelLength(MultiPartGenerator builder, MultiVariant length, Quadrant yRot, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        poleModelTri(builder, length, yRot, main, true, c1, true, c2, false);
    }

    public void poleModelTri(MultiPartGenerator builder, MultiVariant model, Quadrant yRot, BooleanProperty main, boolean mFlag, BooleanProperty c1, boolean c1Flag, BooleanProperty c2, boolean c2Flag) {
        builder.with(
                BlockModelGenerators.condition().term(main, mFlag).term(c1, c1Flag).term(c2, c2Flag),
                model.with(VariantMutator.Y_ROT.withValue(yRot))
                        .with(VariantMutator.UV_LOCK.withValue(yRot != Quadrant.R0)));
    }

    public void poleModelCorner(MultiPartGenerator builder, MultiVariant model, Quadrant yRot, boolean tlFlag, boolean trFlag, boolean blFlag, boolean brFlag) {
        builder.with(
                BlockModelGenerators.condition().term(OrnamentPole.TOP_LEFT, tlFlag).term(OrnamentPole.TOP_RIGHT, trFlag).term(OrnamentPole.BOTTOM_LEFT, blFlag).term(OrnamentPole.BOTTOM_RIGHT, brFlag),
                model.with(VariantMutator.Y_ROT.withValue(yRot))
                        .with(VariantMutator.UV_LOCK.withValue(yRot != Quadrant.R0)));
    }

    public BlockModelDefinitionGenerator beamBlock(Supplier<? extends OrnamentBeam> block, MultiVariant whole, MultiVariant horizon, MultiVariant vertical, MultiVariant corner, MultiVariant fullblock) {
        MultiPartGenerator builder = MultiPartGenerator.multiPart(block.get());
        beamModelWhole(builder, whole, Quadrant.R180, Quadrant.R180, Direction.Axis.X, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelWhole(builder, whole, Quadrant.R180, Quadrant.R90, Direction.Axis.Z, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelWhole(builder, whole, Quadrant.R180, Quadrant.R0, Direction.Axis.X, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, Quadrant.R180, Quadrant.R270, Direction.Axis.Z, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, Quadrant.R0, Quadrant.R0, Direction.Axis.X, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, Quadrant.R0, Quadrant.R270, Direction.Axis.Z, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, Quadrant.R0, Quadrant.R180, Direction.Axis.X, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelWhole(builder, whole, Quadrant.R0, Quadrant.R90, Direction.Axis.Z, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, horizon, Quadrant.R180, Quadrant.R180, Direction.Axis.X, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, horizon, Quadrant.R180, Quadrant.R90, Direction.Axis.Z, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, horizon, Quadrant.R180, Quadrant.R0, Direction.Axis.X, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, horizon, Quadrant.R180, Quadrant.R270, Direction.Axis.Z, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, horizon, Quadrant.R0, Quadrant.R0, Direction.Axis.X, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, horizon, Quadrant.R0, Quadrant.R270, Direction.Axis.Z, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, horizon, Quadrant.R0, Quadrant.R180, Direction.Axis.X, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, horizon, Quadrant.R0, Quadrant.R90, Direction.Axis.Z, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, vertical, Quadrant.R180, Quadrant.R180, Direction.Axis.X, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, vertical, Quadrant.R180, Quadrant.R90, Direction.Axis.Z, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, vertical, Quadrant.R180, Quadrant.R0, Direction.Axis.X, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, vertical, Quadrant.R180, Quadrant.R270, Direction.Axis.Z, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, vertical, Quadrant.R0, Quadrant.R0, Direction.Axis.X, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, vertical, Quadrant.R0, Quadrant.R270, Direction.Axis.Z, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, vertical, Quadrant.R0, Quadrant.R180, Direction.Axis.X, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, vertical, Quadrant.R0, Quadrant.R90, Direction.Axis.Z, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelCorner(builder, corner, Quadrant.R180, Quadrant.R180, Direction.Axis.X, true, true, true, false);
        beamModelCorner(builder, corner, Quadrant.R180, Quadrant.R90, Direction.Axis.Z, true, true, true, false);
        beamModelCorner(builder, corner, Quadrant.R180, Quadrant.R0, Direction.Axis.X, true, true, false, true);
        beamModelCorner(builder, corner, Quadrant.R180, Quadrant.R270, Direction.Axis.Z, true, true, false, true);
        beamModelCorner(builder, corner, Quadrant.R0, Quadrant.R180, Direction.Axis.X, false, true, true, true);
        beamModelCorner(builder, corner, Quadrant.R0, Quadrant.R90, Direction.Axis.Z, false, true, true, true);
        beamModelCorner(builder, corner, Quadrant.R0, Quadrant.R0, Direction.Axis.X, true, false, true, true);
        beamModelCorner(builder, corner, Quadrant.R0, Quadrant.R270, Direction.Axis.Z, true, false, true, true);

        return builder.with(
                BlockModelGenerators.condition().term(OrnamentBeam.TOP_LEFT, true).term(OrnamentBeam.TOP_RIGHT, true).term(OrnamentBeam.BOTTOM_LEFT, true).term(OrnamentBeam.BOTTOM_RIGHT, true),
                fullblock);
    }

    public void beamModelWhole(MultiPartGenerator builder, MultiVariant model, Quadrant xRot, Quadrant yRot, Direction.Axis axis, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        beamModelTri(builder, model, xRot, yRot, axis, main, true, c1, false, c2, false);
    }

    public void beamModelLength(MultiPartGenerator builder, MultiVariant model, Quadrant xRot, Quadrant yRot, Direction.Axis axis, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        beamModelTri(builder, model, xRot, yRot, axis, main, true, c1, true, c2, false);
    }

    public void beamModelTri(MultiPartGenerator builder, MultiVariant model, Quadrant xRot, Quadrant yRot, Direction.Axis axis, BooleanProperty main, boolean mFlag, BooleanProperty c1, boolean c1Flag, BooleanProperty c2, boolean c2Flag) {
        builder.with(
                BlockModelGenerators.condition().term(BlockStateProperties.HORIZONTAL_AXIS, axis).term(main, mFlag).term(c1, c1Flag).term(c2, c2Flag),
                model.with(VariantMutator.X_ROT.withValue(xRot))
                        .with(VariantMutator.Y_ROT.withValue(yRot))
                        .with(VariantMutator.UV_LOCK.withValue(xRot != Quadrant.R0 || yRot != Quadrant.R0)));
    }

    public void beamModelCorner(MultiPartGenerator builder, MultiVariant model, Quadrant xRot, Quadrant yRot, Direction.Axis axis, boolean tlFlag, boolean trFlag, boolean blFlag, boolean brFlag) {
        builder.with(
                BlockModelGenerators.condition().term(BlockStateProperties.HORIZONTAL_AXIS, axis)
                        .term(OrnamentBeam.TOP_LEFT, tlFlag).term(OrnamentBeam.TOP_RIGHT, trFlag).term(OrnamentBeam.BOTTOM_LEFT, blFlag).term(OrnamentBeam.BOTTOM_RIGHT, brFlag),
                model.with(VariantMutator.X_ROT.withValue(xRot))
                        .with(VariantMutator.Y_ROT.withValue(yRot))
                        .with(VariantMutator.UV_LOCK.withValue(xRot != Quadrant.R0 || yRot != Quadrant.R0)));
    }

    public BlockModelDefinitionGenerator supportBlock(Supplier<? extends OrnamentSupport> block, MultiVariant base, MultiVariant basetop, MultiVariant vertical, MultiVariant verticaltop, MultiVariant horizontalX, MultiVariant horizontalXtop, MultiVariant horizontalZ, MultiVariant horizontalZtop) {
        MultiPartGenerator builder = MultiPartGenerator.multiPart(block.get());
        supportModelBase(builder, base, OrnamentSupport.CornerType.TOP_LEFT, false, Quadrant.R0);
        supportModelBase(builder, base, OrnamentSupport.CornerType.TOP_RIGHT, false, Quadrant.R90);
        supportModelBase(builder, base, OrnamentSupport.CornerType.BOTTOM_RIGHT, false, Quadrant.R180);
        supportModelBase(builder, base, OrnamentSupport.CornerType.BOTTOM_LEFT, false, Quadrant.R270);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.TOP_LEFT, true, Quadrant.R0);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.TOP_RIGHT, true, Quadrant.R90);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.BOTTOM_RIGHT, true, Quadrant.R180);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.BOTTOM_LEFT, true, Quadrant.R270);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.TB_CONNECT, false, Quadrant.R0);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.TB_CONNECT, false, Quadrant.R90);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.TB_CONNECT, false, Quadrant.R180);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.TB_CONNECT, false, Quadrant.R270);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.TB_CONNECT, true, Quadrant.R0);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.TB_CONNECT, true, Quadrant.R90);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.TB_CONNECT, true, Quadrant.R180);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.TB_CONNECT, true, Quadrant.R270);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.NS_CONNECT, false, Quadrant.R0);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.NS_CONNECT, false, Quadrant.R90);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.NS_CONNECT, false, Quadrant.R180);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.NS_CONNECT, false, Quadrant.R270);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.NS_CONNECT, true, Quadrant.R0);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.NS_CONNECT, true, Quadrant.R90);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.NS_CONNECT, true, Quadrant.R180);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.NS_CONNECT, true, Quadrant.R270);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.EW_CONNECT, false, Quadrant.R0);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.EW_CONNECT, false, Quadrant.R90);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.EW_CONNECT, false, Quadrant.R180);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.EW_CONNECT, false, Quadrant.R270);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.EW_CONNECT, true, Quadrant.R0);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.EW_CONNECT, true, Quadrant.R90);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.EW_CONNECT, true, Quadrant.R180);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.EW_CONNECT, true, Quadrant.R270);
        return builder;
    }

    public void supportModelBase(MultiPartGenerator builder, MultiVariant model, OrnamentSupport.CornerType corner, boolean upper, Quadrant y) {
        builder.with(
                BlockModelGenerators.condition().term(OrnamentSupport.CORNER, corner).term(OrnamentSupport.UPPER_HALF, upper),
                model.with(VariantMutator.Y_ROT.withValue(y)).with(VariantMutator.UV_LOCK.withValue(true)));
    }

    public void supportModelConnect(MultiPartGenerator builder, MultiVariant model, OrnamentSupport.CornerType corner, BooleanProperty connect, boolean upper, Quadrant y) {
        builder.with(
                BlockModelGenerators.condition().term(OrnamentSupport.CORNER, corner).term(OrnamentSupport.UPPER_HALF, upper).term(connect, true),
                model.with(VariantMutator.Y_ROT.withValue(y)).with(VariantMutator.UV_LOCK.withValue(true)));
    }
}
