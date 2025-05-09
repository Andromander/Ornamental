package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.blocks.*;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Either;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class OrnamentalBlockStateProvider {

    protected final BlockModelGenerators blockModels;
    private final String modID;
    private final String parentID;

    public static final Map<Integer, VariantProperties.Rotation> INT_TO_ROT = ImmutableMap.of(
            0, VariantProperties.Rotation.R0,
            90, VariantProperties.Rotation.R90,
            180, VariantProperties.Rotation.R180,
            270, VariantProperties.Rotation.R270);

    public static final ResourceLocation SOLID = ResourceLocation.withDefaultNamespace("solid");
    public static final ResourceLocation TRANSLUCENT = ResourceLocation.withDefaultNamespace("translucent");
    public static final ResourceLocation CUTOUT = ResourceLocation.withDefaultNamespace("cutout");
    public static final ResourceLocation CUTOUT_MIPPED = ResourceLocation.withDefaultNamespace("cutout_mipped");

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
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(OrnamentalMod.MODID, "block/util/" + model)), suffix != null ? Optional.of(suffix) : Optional.empty(), slots);
    }

    public OrnamentalBlockStateProvider(BlockModelGenerators generator, String modid, String parent) {
        this.blockModels = generator;
        this.modID = modid;
        this.parentID = parent;
    }

    public abstract void runBlockGen();

    protected ResourceLocation locMod(String name) {
        return ResourceLocation.fromNamespaceAndPath(modID, "block/" + name);
    }

    protected ResourceLocation locParent(String name) {
        return ResourceLocation.fromNamespaceAndPath(parentID, "block/" + name);
    }

    protected TextureMapping makeMapping(ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
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

    public void stairsBasic(Supplier<? extends OrnamentStair> block, ResourceLocation name, ModelTemplate inner, ModelTemplate straight, ModelTemplate outer) {
        stairs(block, inner, straight, outer, name, name, name);
    }

    public void stairsColumn(Supplier<? extends OrnamentStair> block, String side, String end) {
        stairs(block, ModelTemplates.STAIRS_INNER, ModelTemplates.STAIRS_STRAIGHT, ModelTemplates.STAIRS_OUTER, locParent(side), locParent(end), locParent(end));
    }

    public void stairs(Supplier<? extends OrnamentStair> block, ModelTemplate innerModel, ModelTemplate straightModel, ModelTemplate outerModel, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        ResourceLocation inner = innerModel.create(block.get(), mapping, blockModels.modelOutput);
        ResourceLocation straight = straightModel.create(block.get(), mapping, blockModels.modelOutput);
        ResourceLocation outer = outerModel.create(block.get(), mapping, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(BlockModelGenerators.createStairs(block.get(), inner, straight, outer));
        blockModels.registerSimpleItemModel(block.get().asItem(), straight);
    }

    /* Slabs */
    public void slabBasic(DeferredBlock<? extends SlabBlock> block, Supplier<? extends Block> blockname) {
        slabBasic(block, blockname, SOLID);
    }

    public void slabBasic(DeferredBlock<? extends SlabBlock> block, Supplier<? extends Block> blockname, ResourceLocation type) {
        String name = BuiltInRegistries.BLOCK.getKey(blockname.get()).getPath();
        slab(block, ModelTemplates.SLAB_BOTTOM, ModelTemplates.SLAB_TOP, Either.right(blockname), locParent(name), locParent(name), locParent(name), type);
    }

    public void slabModel(DeferredBlock<? extends SlabBlock> block, Supplier<? extends Block> blockname, String name, ResourceLocation type) {
        slab(block, ModelTemplates.SLAB_BOTTOM, ModelTemplates.SLAB_TOP, Either.right(blockname), locParent(name), locParent(name), locParent(name), type);
    }

    public void slabModel(DeferredBlock<? extends SlabBlock> block, String blockname, ResourceLocation name, ResourceLocation type) {
        slab(block, ModelTemplates.SLAB_BOTTOM, ModelTemplates.SLAB_TOP, Either.left(locMod(blockname)), name, name, name, type);
    }

    public void slabColumn(DeferredBlock<? extends SlabBlock> block, Supplier<? extends Block> blockname, String side, String end, ResourceLocation type) {
        slab(block, ModelTemplates.SLAB_BOTTOM, ModelTemplates.SLAB_TOP, Either.right(blockname), locParent(side), locParent(end), locParent(end), type);
    }

    public void slab(DeferredBlock<? extends SlabBlock> block, ModelTemplate bottomModel, ModelTemplate topModel, Either<ResourceLocation, Supplier<? extends Block>> doubleModel, ResourceLocation side, ResourceLocation bottom, ResourceLocation top, ResourceLocation type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        ResourceLocation bm, tm, dm;
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


        BlockStateGenerator slabgen = MultiVariantGenerator.multiVariant(block.get())
                .with(PropertyDispatch.property(BlockStateProperties.SLAB_TYPE)
                        .select(SlabType.BOTTOM, Variant.variant().with(VariantProperties.MODEL, bm))
                        .select(SlabType.TOP, Variant.variant().with(VariantProperties.MODEL, tm))
                        .select(SlabType.DOUBLE, Variant.variant().with(VariantProperties.MODEL, dm)));
        blockModels.blockStateOutput.accept(slabgen);
        blockModels.registerSimpleItemModel(block.get(), bm);
    }

    /* Fences */
    public void fenceBasic(Supplier<? extends FenceBlock> block, String name) {
        fenceBasic(block, locParent(name));
    }

    public void fenceBasic(Supplier<? extends FenceBlock> block, ResourceLocation name) {
        fence(block, FENCE_POST, ModelTemplates.CUSTOM_FENCE_SIDE_NORTH, ModelTemplates.CUSTOM_FENCE_SIDE_EAST, ModelTemplates.CUSTOM_FENCE_SIDE_SOUTH, ModelTemplates.CUSTOM_FENCE_SIDE_WEST, FENCE_INVENTORY, name, name, name);
    }

    public void fenceColumn(Supplier<? extends FenceBlock> block, String side, String top) {
        fence(block, FENCE_POST, ModelTemplates.CUSTOM_FENCE_SIDE_NORTH, ModelTemplates.CUSTOM_FENCE_SIDE_EAST, ModelTemplates.CUSTOM_FENCE_SIDE_SOUTH, ModelTemplates.CUSTOM_FENCE_SIDE_WEST, FENCE_INVENTORY, locParent(side), locParent(top), locParent(top));
    }

    public void fence(Supplier<? extends FenceBlock> block, ModelTemplate post, ModelTemplate north, ModelTemplate east, ModelTemplate south, ModelTemplate west, ModelTemplate inventory, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        TextureMapping postMapping = makeMapping(side, bottom, top).put(TextureSlot.PARTICLE, side);
        TextureMapping railMapping = new TextureMapping().put(TextureSlot.TEXTURE, side);
        ResourceLocation p = post.create(block.get(), postMapping, blockModels.modelOutput);
        ResourceLocation n = north.create(block.get(), railMapping, blockModels.modelOutput);
        ResourceLocation e = east.create(block.get(), railMapping, blockModels.modelOutput);
        ResourceLocation s = south.create(block.get(), railMapping, blockModels.modelOutput);
        ResourceLocation w = west.create(block.get(), railMapping, blockModels.modelOutput);
        ResourceLocation i = inventory.create(block.get(), postMapping, blockModels.modelOutput);

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

    public void trapdoorBasic(Supplier<? extends TrapDoorBlock> block, String name, ResourceLocation type) {
        trapdoor(block, locMod(name + "_trapdoor"), true, type);
    }

    public void trapdoorParent(Supplier<? extends TrapDoorBlock> block, String name, ResourceLocation type) {
        trapdoor(block, locParent(name), false, type);
    }

    public void trapdoor(Supplier<? extends TrapDoorBlock> block, ResourceLocation texture, boolean orientable, ResourceLocation type) {
        TextureMapping mapping = new TextureMapping().put(TextureSlot.TEXTURE, texture);
        ResourceLocation top, bottom, open;
        if (orientable) {
            top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        } else {
            top = ModelTemplates.TRAPDOOR_TOP.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            bottom = ModelTemplates.TRAPDOOR_BOTTOM.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            open = ModelTemplates.TRAPDOOR_OPEN.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        }
        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createTrapdoor(block.get(), top, bottom, open));
        this.blockModels.registerSimpleItemModel(block.get(), bottom);
    }

    /* Fence Gates */
    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, String name) {
        fenceGateBasic(block, locParent(name), SOLID);
    }

    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, String name, ResourceLocation type) {
        fenceGateBasic(block, locParent(name), type);
    }

    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, ResourceLocation name, ResourceLocation type) {
        fenceGate(block, FENCE_GATE_CLOSED, FENCE_GATE_OPEN, FENCE_GATE_WALL_CLOSED, FENCE_GATE_WALL_OPEN, name, name, name, type);
    }

    public void fenceGateColumn(Supplier<? extends FenceGateBlock> block, String side, String top, ResourceLocation type) {
        fenceGate(block, FENCE_GATE_CLOSED, FENCE_GATE_OPEN, FENCE_GATE_WALL_CLOSED, FENCE_GATE_WALL_OPEN, locParent(side), locParent(top), locParent(top), type);
    }

    public void fenceGate(Supplier<? extends FenceGateBlock> block, ModelTemplate gate, ModelTemplate opengate, ModelTemplate wall, ModelTemplate openwall, ResourceLocation side, ResourceLocation top, ResourceLocation bottom, ResourceLocation type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        ResourceLocation g, go, w, wo;

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
        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createFenceGate(block.get(), go, g, wo, w, true));
        this.blockModels.registerSimpleItemModel(block.get(), g);
    }

    /* Doors */
    public void doorBasic(DeferredBlock<? extends DoorBlock> block, String name) {
        doorBasic(block, name, CUTOUT);
    }

    public void doorHidden(DeferredBlock<? extends DoorBlock> block, String name) {
        doorHidden(block, name, CUTOUT);
    }

    public void doorBasic(DeferredBlock<? extends DoorBlock> block, String name, ResourceLocation type) {
        doorBasic(block, locMod(name + "_door_bottom"), locMod(name + "_door_bottom"), locMod(name + "_door_top"), locMod(name + "_door_top"), type);
    }

    public void doorHidden(DeferredBlock<? extends DoorBlock> block, String name, ResourceLocation type) {
        doorBasic(block, locParent(name), locParent(name), locParent(name), locParent(name), type);
    }

    public void doorBasic(DeferredBlock<? extends DoorBlock> block, ResourceLocation name, ResourceLocation type) {
        doorBasic(block, name, name, name, name, type);
    }

    public void doorBasic(DeferredBlock<? extends DoorBlock> block, ResourceLocation bottomside, ResourceLocation bottom, ResourceLocation topside, ResourceLocation top, ResourceLocation type) {
        door(block, DOOR_BOTTOM_LEFT, DOOR_BOTTOM_LEFT_OPEN, DOOR_BOTTOM_RIGHT, DOOR_BOTTOM_RIGHT_OPEN, DOOR_TOP_LEFT, DOOR_TOP_LEFT_OPEN, DOOR_TOP_RIGHT, DOOR_TOP_RIGHT_OPEN, bottomside, bottom, topside, top, type);
    }

    public void door(DeferredBlock<? extends DoorBlock> block, ModelTemplate bl, ModelTemplate blo, ModelTemplate br, ModelTemplate bro, ModelTemplate tl, ModelTemplate tlo, ModelTemplate tr, ModelTemplate tro, ResourceLocation bottomside, ResourceLocation bottom, ResourceLocation topside, ResourceLocation top, ResourceLocation type) {
        TextureMapping bottomTex = new TextureMapping()
                .put(TextureSlot.SIDE, bottomside)
                .put(TextureSlot.BOTTOM, bottom);
        TextureMapping topTex = new TextureMapping()
                .put(TextureSlot.SIDE, topside)
                .put(TextureSlot.TOP, top);

        ResourceLocation bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen;
        bottomLeft = bl.extend().renderType(type).build().create(block.get(), bottomTex, blockModels.modelOutput);
        bottomLeftOpen = blo.extend().renderType(type).build().create(block.get(), bottomTex, blockModels.modelOutput);
        bottomRight = br.extend().renderType(type).build().create(block.get(), bottomTex, blockModels.modelOutput);
        bottomRightOpen = bro.extend().renderType(type).build().create(block.get(), bottomTex, blockModels.modelOutput);
        topLeft = tl.extend().renderType(type).build().create(block.get(), topTex, blockModels.modelOutput);
        topLeftOpen = tlo.extend().renderType(type).build().create(block.get(), topTex, blockModels.modelOutput);
        topRight = tr.extend().renderType(type).build().create(block.get(), topTex, blockModels.modelOutput);
        topRightOpen = tro.extend().renderType(type).build().create(block.get(), topTex, blockModels.modelOutput);

        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createDoor(block.get(), bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen));
        this.blockModels.registerSimpleFlatItemModel(block.asItem());
    }

    /* Poles */
    public void poleBasic(Supplier<? extends OrnamentPole> block, String fullblock, String name) {
        poleBasic(block, fullblock, name, SOLID);
    }

    public void poleBasic(Supplier<? extends OrnamentPole> block, Supplier<? extends Block> fullblock, String name) {
        poleBasic(block, fullblock, name, SOLID);
    }

    public void poleBasic(Supplier<? extends OrnamentPole> block, String fullblock, String name, ResourceLocation type) {
        poleBasic(block, Either.left(locMod(fullblock)), locMod(name), type);
    }

    public void poleBasic(Supplier<? extends OrnamentPole> block, Supplier<? extends Block> fullblock, String name, ResourceLocation type) {
        poleBasic(block, Either.right(fullblock), locParent(name), type);
    }

    public void poleBasic(Supplier<? extends OrnamentPole> block, Either<ResourceLocation, Supplier<? extends Block>> fullblock, ResourceLocation name, ResourceLocation type) {
        pole(block, POLE_WHOLE, POLE_HORIZONTAL, POLE_VERTICAL, POLE_CORNER, fullblock, name, name, name, type);
    }

    public void poleColumn(Supplier<? extends OrnamentPole> block, Either<ResourceLocation, Supplier<? extends Block>> fullblock, String side, String top, ResourceLocation type) {
        pole(block, POLE_WHOLE, POLE_HORIZONTAL, POLE_VERTICAL, POLE_CORNER, fullblock, locParent(top), locParent(top), locParent(side), type);
    }

    public void pole(Supplier<? extends OrnamentPole> block, ModelTemplate w, ModelTemplate h, ModelTemplate v, ModelTemplate c, Either<ResourceLocation, Supplier<? extends Block>> full, ResourceLocation top, ResourceLocation bottom, ResourceLocation side, ResourceLocation type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        ResourceLocation whole, horizon, vertical, corner, fullblock, inventory;

        if (type != SOLID) {
            whole = w.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            horizon = h.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            vertical = v.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            corner = c.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            fullblock = new ExtendedModelTemplateBuilder()
                    .parent(full.map(
                            r -> r,
                            b -> ModelLocationUtils.getModelLocation(b.get())))
                    .suffix("_full")
                    .renderType(type)
                    .build().create(block.get(), mapping, blockModels.modelOutput);
            inventory = POLE_INVENTORY.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        } else {
            whole = w.create(block.get(), mapping, blockModels.modelOutput);
            horizon = h.create(block.get(), mapping, blockModels.modelOutput);
            vertical = v.create(block.get(), mapping, blockModels.modelOutput);
            corner = c.create(block.get(), mapping, blockModels.modelOutput);
            fullblock = full.map(
                    r -> r,
                    b -> ModelLocationUtils.getModelLocation(b.get()));
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

    public void beamBasic(Supplier<? extends OrnamentBeam> block, String fullblock, String name, ResourceLocation type) {
        beamBasic(block, Either.left(locMod(fullblock)), locMod(name), type);
    }

    public void beamBasic(Supplier<? extends OrnamentBeam> block, Supplier<? extends Block> fullblock, String name, ResourceLocation type) {
        beamBasic(block, Either.right(fullblock), locParent(name), type);
    }

    public void beamBasic(Supplier<? extends OrnamentBeam> block, Either<ResourceLocation, Supplier<? extends Block>> fullblock, ResourceLocation name, ResourceLocation type) {
        beam(block, BEAM_WHOLE, BEAM_HORIZONTAL, BEAM_VERTICAL, BEAM_CORNER, fullblock, name, name, name, type);
    }

    public void beamColumn(Supplier<? extends OrnamentBeam> block, Either<ResourceLocation, Supplier<? extends Block>> fullblock, String top, String side, ResourceLocation type) {
        beam(block, BEAM_WHOLE, BEAM_HORIZONTAL, BEAM_VERTICAL, BEAM_CORNER, fullblock, locParent(top), locParent(top), locParent(side), type);
    }

    public void beam(Supplier<? extends OrnamentBeam> block, ModelTemplate w, ModelTemplate h, ModelTemplate v, ModelTemplate c, Either<ResourceLocation, Supplier<? extends Block>> full, ResourceLocation top, ResourceLocation bottom, ResourceLocation side, ResourceLocation type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        ResourceLocation whole, horizon, vertical, corner, fullblock, inventory;

        if (type != SOLID) {
            whole = w.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            horizon = h.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            vertical = v.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            corner = c.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            fullblock = new ExtendedModelTemplateBuilder()
                    .parent(full.map(
                            r -> r,
                            b -> ModelLocationUtils.getModelLocation(b.get())))
                    .suffix("_full")
                    .renderType(type)
                    .build().create(block.get(), mapping, blockModels.modelOutput);
            inventory = BEAM_INVENTORY.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        } else {
            whole = w.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            horizon = h.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            vertical = v.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            corner = c.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            fullblock = full.map(
                    r -> r,
                    b -> ModelLocationUtils.getModelLocation(b.get()));
            inventory = BEAM_INVENTORY.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        }

        this.blockModels.blockStateOutput.accept(beamBlock(block, whole, horizon, vertical, corner, fullblock));
        this.blockModels.registerSimpleItemModel(block.get(), inventory);
    }

    /* Walls */
    public void wallBasic(Supplier<? extends WallBlock> block, String name) {
        wallBasic(block, locParent(name));
    }

    public void wallBasic(Supplier<? extends WallBlock> block, ResourceLocation name) {
        wall(block, WALL_POST, WALL_SIDE, WALL_SIDE_TALL, name, name, name);
    }

    public void wallColumn(Supplier<? extends WallBlock> block, String side, String end) {
        wall(block, WALL_POST, WALL_SIDE, WALL_SIDE_TALL, locParent(side), locParent(end), locParent(end));
    }

    public void wall(Supplier<? extends WallBlock> block, ModelTemplate post, ModelTemplate sidewall, ModelTemplate sidetall, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        ResourceLocation wallpost = post.create(block.get(), mapping, blockModels.modelOutput);
        ResourceLocation wallside = sidewall.create(block.get(), mapping, blockModels.modelOutput);
        ResourceLocation walltall = sidetall.create(block.get(), mapping, blockModels.modelOutput);
        ResourceLocation inventory = WALL_INVENTORY.create(block.get(), mapping, blockModels.modelOutput);

        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createWall(block.get(), wallpost, wallside, walltall));
        this.blockModels.registerSimpleItemModel(block.get(), inventory);
    }

    /* Saddle Doors */
    public void saddleDoorBasic(Supplier<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoor(block, locMod(name + "_trapdoor"), CUTOUT);
    }

    public void saddleDoorBasic(Supplier<? extends OrnamentSaddleDoor> block, ResourceLocation name) {
        saddleDoor(block, name, CUTOUT);
    }

    public void saddleDoorBasic(Supplier<? extends OrnamentSaddleDoor> block, String name, ResourceLocation type) {
        saddleDoor(block, locMod(name + "_trapdoor"), type);
    }

    public void saddleDoorBasic(Supplier<? extends OrnamentSaddleDoor> block, ResourceLocation name, ResourceLocation type) {
        saddleDoor(block, name, type);
    }

    public void saddleDoorHidden(Supplier<? extends OrnamentSaddleDoor> block, String name) {
        saddleDoor(block, locParent(name), CUTOUT);
    }

    public void saddleDoor(Supplier<? extends OrnamentSaddleDoor> block, ResourceLocation name, ResourceLocation type) {
        saddleDoor(block, SADDLE_DOOR_LEFT, SADDLE_DOOR_LEFT_OPEN, SADDLE_DOOR_RIGHT, SADDLE_DOOR_RIGHT_OPEN, name, name, name, type);
    }

    public void saddleDoor(Supplier<? extends OrnamentSaddleDoor> block, ModelTemplate leftDoor, ModelTemplate leftDoorOpen, ModelTemplate rightDoor, ModelTemplate rightDoorOpen, ResourceLocation side, ResourceLocation bottom, ResourceLocation top, ResourceLocation type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        ResourceLocation left = leftDoor.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        ResourceLocation leftOpen = leftDoorOpen.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        ResourceLocation right = rightDoor.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        ResourceLocation rightOpen = rightDoorOpen.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        ResourceLocation inventory = SADDLE_DOOR_INVENTORY.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);

        this.blockModels.blockStateOutput.accept(saddleDoorBlock(block, left, leftOpen, right, rightOpen));
        this.blockModels.registerSimpleItemModel(block.get(), inventory);
    }

    /* Supports */
    public void supportBasic(Supplier<? extends OrnamentSupport> block, String name) {
        supportBasic(block, name, SOLID);
    }

    public void supportBasic(Supplier<? extends OrnamentSupport> block, String name, ResourceLocation type) {
        supportBasic(block, locParent(name), type);
    }

    public void supportBasic(Supplier<? extends OrnamentSupport> block, ResourceLocation name, ResourceLocation type) {
        support(block, SUPPORT_BASE, SUPPORT_BASE_TOP, SUPPORT_Y, SUPPORT_Y_TOP, SUPPORT_X, SUPPORT_X_TOP, SUPPORT_Z, SUPPORT_Z_TOP, name, name, name, type);
    }

    public void supportColumn(Supplier<? extends OrnamentSupport> block, String side, String top, ResourceLocation type) {
        support(block, SUPPORT_BASE, SUPPORT_BASE_TOP, SUPPORT_Y, SUPPORT_Y_TOP, SUPPORT_X, SUPPORT_X_TOP, SUPPORT_Z, SUPPORT_Z_TOP, locParent(side), locParent(top), locParent(top), type);
    }

    public void support(Supplier<? extends OrnamentSupport> block, ModelTemplate baseModel, ModelTemplate baseTModel, ModelTemplate yModel, ModelTemplate yTModel, ModelTemplate xModel, ModelTemplate xTModel, ModelTemplate zModel, ModelTemplate zTModel, ResourceLocation side, ResourceLocation bottom, ResourceLocation top, ResourceLocation type) {
        TextureMapping mapping = makeMapping(side, bottom, top);
        ResourceLocation base, baseTop, vertical, verticalTop ,horizontalX, horizontalXTop, horizontalZ, horizontalZTop, inventory;

        if (type != SOLID) {
            base = baseModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            baseTop = baseTModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            vertical = yModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            verticalTop = yTModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            horizontalX = xModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            horizontalXTop = xTModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            horizontalZ = zModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            horizontalZTop = zTModel.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
            inventory = SUPPORT_INVENTORY.extend().renderType(type).build().create(block.get(), mapping, blockModels.modelOutput);
        } else {
            base = baseModel.create(block.get(), mapping, blockModels.modelOutput);
            baseTop = baseTModel.create(block.get(), mapping, blockModels.modelOutput);
            vertical = yModel.create(block.get(), mapping, blockModels.modelOutput);
            verticalTop = yTModel.create(block.get(), mapping, blockModels.modelOutput);
            horizontalX = xModel.create(block.get(), mapping, blockModels.modelOutput);
            horizontalXTop = xTModel.create(block.get(), mapping, blockModels.modelOutput);
            horizontalZ = zModel.create(block.get(), mapping, blockModels.modelOutput);
            horizontalZTop = zTModel.create(block.get(), mapping, blockModels.modelOutput);
            inventory = SUPPORT_INVENTORY.create(block.get(), mapping, blockModels.modelOutput);
        }

        this.blockModels.blockStateOutput.accept(supportBlock(block, base, baseTop, vertical, verticalTop, horizontalX, horizontalXTop, horizontalZ, horizontalZTop));
        this.blockModels.registerSimpleItemModel(block.get(), inventory);
    }

    public BlockStateGenerator saddleDoorBlock(Supplier<? extends OrnamentSaddleDoor> block, ResourceLocation left, ResourceLocation leftOpen, ResourceLocation right, ResourceLocation rightOpen) {
        return MultiVariantGenerator.multiVariant(block.get())
                .with(PropertyDispatch.properties(
                                OrnamentSaddleDoor.FACING,
                                OrnamentSaddleDoor.HINGE,
                                OrnamentSaddleDoor.OPEN)
                        .select(
                                Direction.EAST, DoorHingeSide.LEFT, false,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, left))
                        .select(
                                Direction.EAST, DoorHingeSide.LEFT, true,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, leftOpen).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(
                                Direction.EAST, DoorHingeSide.RIGHT, false,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, right))
                        .select(
                                Direction.EAST, DoorHingeSide.RIGHT, true,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, rightOpen).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(
                                Direction.NORTH, DoorHingeSide.LEFT, false,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, left).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(
                                Direction.NORTH, DoorHingeSide.LEFT, true,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, leftOpen))
                        .select(
                                Direction.NORTH, DoorHingeSide.RIGHT, false,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, right).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(
                                Direction.NORTH, DoorHingeSide.RIGHT, true,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, rightOpen).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(
                                Direction.SOUTH, DoorHingeSide.LEFT, false,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, left).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(
                                Direction.SOUTH, DoorHingeSide.LEFT, true,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, leftOpen).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(
                                Direction.SOUTH, DoorHingeSide.RIGHT, false,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, right).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(
                                Direction.SOUTH, DoorHingeSide.RIGHT, true,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, rightOpen))
                        .select(
                                Direction.WEST, DoorHingeSide.LEFT, false,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, left).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(
                                Direction.WEST, DoorHingeSide.LEFT, true,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, leftOpen).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(
                                Direction.WEST, DoorHingeSide.RIGHT, false,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, right).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(
                                Direction.WEST, DoorHingeSide.RIGHT, true,
                                Variant.variant()
                                        .with(VariantProperties.MODEL, rightOpen).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)));
    }

    public BlockStateGenerator poleBlock(Supplier<? extends OrnamentPole> block, ResourceLocation whole, ResourceLocation horizon, ResourceLocation vertical, ResourceLocation corner, ResourceLocation fullblock) {
        MultiPartGenerator builder = MultiPartGenerator.multiPart(block.get());
        poleModelWhole(builder, whole, VariantProperties.Rotation.R0, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelWhole(builder, whole, VariantProperties.Rotation.R90, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelWhole(builder, whole, VariantProperties.Rotation.R180, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelWhole(builder, whole, VariantProperties.Rotation.R270, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelLength(builder, horizon, VariantProperties.Rotation.R0, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelLength(builder, horizon, VariantProperties.Rotation.R90, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        poleModelLength(builder, horizon, VariantProperties.Rotation.R180, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        poleModelLength(builder, horizon, VariantProperties.Rotation.R270, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelLength(builder, vertical, VariantProperties.Rotation.R0, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        poleModelLength(builder, vertical, VariantProperties.Rotation.R90, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        poleModelLength(builder, vertical, VariantProperties.Rotation.R180, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        poleModelLength(builder, vertical, VariantProperties.Rotation.R270, OrnamentPole.BOTTOM_LEFT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        poleModelCorner(builder, corner, VariantProperties.Rotation.R0, true, true, true, false);
        poleModelCorner(builder, corner, VariantProperties.Rotation.R90, true, true, false, true);
        poleModelCorner(builder, corner, VariantProperties.Rotation.R180, false, true, true, true);
        poleModelCorner(builder, corner, VariantProperties.Rotation.R270, true, false, true, true);

        return builder.with(
                Condition.condition().term(OrnamentPole.TOP_LEFT, true).term(OrnamentPole.TOP_RIGHT, true).term(OrnamentPole.BOTTOM_LEFT, true).term(OrnamentPole.BOTTOM_RIGHT, true),
                Variant.variant().with(VariantProperties.MODEL, fullblock));
    }

    public void poleModelWhole(MultiPartGenerator builder, ResourceLocation whole, VariantProperties.Rotation yRot, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        poleModelTri(builder, whole, yRot, main, true, c1, false, c2, false);
    }

    public void poleModelLength(MultiPartGenerator builder, ResourceLocation length, VariantProperties.Rotation yRot, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        poleModelTri(builder, length, yRot, main, true, c1, true, c2, false);
    }

    public void poleModelTri(MultiPartGenerator builder, ResourceLocation model, VariantProperties.Rotation yRot, BooleanProperty main, boolean mFlag, BooleanProperty c1, boolean c1Flag, BooleanProperty c2, boolean c2Flag) {
        builder.with(
                Condition.condition().term(main, mFlag).term(c1, c1Flag).term(c2, c2Flag),
                Variant.variant()
                        .with(VariantProperties.MODEL, model)
                        .with(VariantProperties.Y_ROT, yRot)
                        .with(VariantProperties.UV_LOCK, yRot != VariantProperties.Rotation.R0));
    }

    public void poleModelCorner(MultiPartGenerator builder, ResourceLocation model, VariantProperties.Rotation yRot, boolean tlFlag, boolean trFlag, boolean blFlag, boolean brFlag) {
        builder.with(
                Condition.condition().term(OrnamentPole.TOP_LEFT, tlFlag).term(OrnamentPole.TOP_RIGHT, trFlag).term(OrnamentPole.BOTTOM_LEFT, blFlag).term(OrnamentPole.BOTTOM_RIGHT, brFlag),
                Variant.variant()
                        .with(VariantProperties.MODEL, model)
                        .with(VariantProperties.Y_ROT, yRot)
                        .with(VariantProperties.UV_LOCK, yRot != VariantProperties.Rotation.R0));
    }

    public BlockStateGenerator beamBlock(Supplier<? extends OrnamentBeam> block, ResourceLocation whole, ResourceLocation horizon, ResourceLocation vertical, ResourceLocation corner, ResourceLocation fullblock) {
        MultiPartGenerator builder = MultiPartGenerator.multiPart(block.get());
        beamModelWhole(builder, whole, VariantProperties.Rotation.R180, VariantProperties.Rotation.R180, Direction.Axis.X, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelWhole(builder, whole, VariantProperties.Rotation.R180, VariantProperties.Rotation.R90, Direction.Axis.Z, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelWhole(builder, whole, VariantProperties.Rotation.R180, VariantProperties.Rotation.R0, Direction.Axis.X, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, VariantProperties.Rotation.R180, VariantProperties.Rotation.R270, Direction.Axis.Z, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, Direction.Axis.X, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, Direction.Axis.Z, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, whole, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, Direction.Axis.X, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelWhole(builder, whole, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, Direction.Axis.Z, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, horizon, VariantProperties.Rotation.R180, VariantProperties.Rotation.R180, Direction.Axis.X, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, horizon, VariantProperties.Rotation.R180, VariantProperties.Rotation.R90, Direction.Axis.Z, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, horizon, VariantProperties.Rotation.R180, VariantProperties.Rotation.R0, Direction.Axis.X, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, horizon, VariantProperties.Rotation.R180, VariantProperties.Rotation.R270, Direction.Axis.Z, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, horizon, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, Direction.Axis.X, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, horizon, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, Direction.Axis.Z, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, horizon, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, Direction.Axis.X, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, horizon, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, Direction.Axis.Z, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, vertical, VariantProperties.Rotation.R180, VariantProperties.Rotation.R180, Direction.Axis.X, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, vertical, VariantProperties.Rotation.R180, VariantProperties.Rotation.R90, Direction.Axis.Z, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, vertical, VariantProperties.Rotation.R180, VariantProperties.Rotation.R0, Direction.Axis.X, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, vertical, VariantProperties.Rotation.R180, VariantProperties.Rotation.R270, Direction.Axis.Z, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, vertical, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, Direction.Axis.X, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, vertical, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, Direction.Axis.Z, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, vertical, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, Direction.Axis.X, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, vertical, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, Direction.Axis.Z, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelCorner(builder, corner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R180, Direction.Axis.X, true, true, true, false);
        beamModelCorner(builder, corner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R90, Direction.Axis.Z, true, true, true, false);
        beamModelCorner(builder, corner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R0, Direction.Axis.X, true, true, false, true);
        beamModelCorner(builder, corner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R270, Direction.Axis.Z, true, true, false, true);
        beamModelCorner(builder, corner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, Direction.Axis.X, false, true, true, true);
        beamModelCorner(builder, corner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, Direction.Axis.Z, false, true, true, true);
        beamModelCorner(builder, corner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, Direction.Axis.X, true, false, true, true);
        beamModelCorner(builder, corner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, Direction.Axis.Z, true, false, true, true);

        return builder.with(
                Condition.condition().term(OrnamentBeam.TOP_LEFT, true).term(OrnamentBeam.TOP_RIGHT, true).term(OrnamentBeam.BOTTOM_LEFT, true).term(OrnamentBeam.BOTTOM_RIGHT, true),
                Variant.variant().with(VariantProperties.MODEL, fullblock));
    }

    public void beamModelWhole(MultiPartGenerator builder, ResourceLocation model, VariantProperties.Rotation xRot, VariantProperties.Rotation yRot, Direction.Axis axis, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        beamModelTri(builder, model, xRot, yRot, axis, main, true, c1, false, c2, false);
    }

    public void beamModelLength(MultiPartGenerator builder, ResourceLocation model, VariantProperties.Rotation xRot, VariantProperties.Rotation yRot, Direction.Axis axis, BooleanProperty main, BooleanProperty c1, BooleanProperty c2) {
        beamModelTri(builder, model, xRot, yRot, axis, main, true, c1, true, c2, false);
    }

    public void beamModelTri(MultiPartGenerator builder, ResourceLocation model, VariantProperties.Rotation xRot, VariantProperties.Rotation yRot, Direction.Axis axis, BooleanProperty main, boolean mFlag, BooleanProperty c1, boolean c1Flag, BooleanProperty c2, boolean c2Flag) {
        builder.with(
                Condition.condition().term(BlockStateProperties.HORIZONTAL_AXIS, axis).term(main, mFlag).term(c1, c1Flag).term(c2, c2Flag),
                Variant.variant()
                        .with(VariantProperties.MODEL, model)
                        .with(VariantProperties.X_ROT, xRot)
                        .with(VariantProperties.Y_ROT, yRot)
                        .with(VariantProperties.UV_LOCK, xRot != VariantProperties.Rotation.R0 || yRot != VariantProperties.Rotation.R0));
    }

    public void beamModelCorner(MultiPartGenerator builder, ResourceLocation model, VariantProperties.Rotation xRot, VariantProperties.Rotation yRot, Direction.Axis axis, boolean tlFlag, boolean trFlag, boolean blFlag, boolean brFlag) {
        builder.with(
                Condition.condition().term(BlockStateProperties.HORIZONTAL_AXIS, axis)
                        .term(OrnamentBeam.TOP_LEFT, tlFlag).term(OrnamentBeam.TOP_RIGHT, trFlag).term(OrnamentBeam.BOTTOM_LEFT, blFlag).term(OrnamentBeam.BOTTOM_RIGHT, brFlag),
                Variant.variant()
                        .with(VariantProperties.MODEL, model)
                        .with(VariantProperties.X_ROT, xRot)
                        .with(VariantProperties.Y_ROT, yRot)
                        .with(VariantProperties.UV_LOCK, xRot != VariantProperties.Rotation.R0 || yRot != VariantProperties.Rotation.R0));
    }

    public BlockStateGenerator supportBlock(Supplier<? extends OrnamentSupport> block, ResourceLocation base, ResourceLocation basetop, ResourceLocation vertical, ResourceLocation verticaltop, ResourceLocation horizontalX, ResourceLocation horizontalXtop, ResourceLocation horizontalZ, ResourceLocation horizontalZtop) {
        MultiPartGenerator builder = MultiPartGenerator.multiPart(block.get());
        supportModelBase(builder, base, OrnamentSupport.CornerType.TOP_LEFT, false, VariantProperties.Rotation.R0);
        supportModelBase(builder, base, OrnamentSupport.CornerType.TOP_RIGHT, false, VariantProperties.Rotation.R90);
        supportModelBase(builder, base, OrnamentSupport.CornerType.BOTTOM_RIGHT, false, VariantProperties.Rotation.R180);
        supportModelBase(builder, base, OrnamentSupport.CornerType.BOTTOM_LEFT, false, VariantProperties.Rotation.R270);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.TOP_LEFT, true, VariantProperties.Rotation.R0);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.TOP_RIGHT, true, VariantProperties.Rotation.R90);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.BOTTOM_RIGHT, true, VariantProperties.Rotation.R180);
        supportModelBase(builder, basetop, OrnamentSupport.CornerType.BOTTOM_LEFT, true, VariantProperties.Rotation.R270);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.TB_CONNECT, false, VariantProperties.Rotation.R0);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.TB_CONNECT, false, VariantProperties.Rotation.R90);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.TB_CONNECT, false, VariantProperties.Rotation.R180);
        supportModelConnect(builder, vertical, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.TB_CONNECT, false, VariantProperties.Rotation.R270);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.TB_CONNECT, true, VariantProperties.Rotation.R0);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.TB_CONNECT, true, VariantProperties.Rotation.R90);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.TB_CONNECT, true, VariantProperties.Rotation.R180);
        supportModelConnect(builder, verticaltop, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.TB_CONNECT, true, VariantProperties.Rotation.R270);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.NS_CONNECT, false, VariantProperties.Rotation.R0);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.NS_CONNECT, false, VariantProperties.Rotation.R90);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.NS_CONNECT, false, VariantProperties.Rotation.R180);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.NS_CONNECT, false, VariantProperties.Rotation.R270);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.NS_CONNECT, true, VariantProperties.Rotation.R0);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.NS_CONNECT, true, VariantProperties.Rotation.R90);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.NS_CONNECT, true, VariantProperties.Rotation.R180);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.NS_CONNECT, true, VariantProperties.Rotation.R270);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.EW_CONNECT, false, VariantProperties.Rotation.R0);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.EW_CONNECT, false, VariantProperties.Rotation.R90);
        supportModelConnect(builder, horizontalX, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.EW_CONNECT, false, VariantProperties.Rotation.R180);
        supportModelConnect(builder, horizontalZ, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.EW_CONNECT, false, VariantProperties.Rotation.R270);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.TOP_LEFT, OrnamentSupport.EW_CONNECT, true, VariantProperties.Rotation.R0);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.TOP_RIGHT, OrnamentSupport.EW_CONNECT, true, VariantProperties.Rotation.R90);
        supportModelConnect(builder, horizontalXtop, OrnamentSupport.CornerType.BOTTOM_RIGHT, OrnamentSupport.EW_CONNECT, true, VariantProperties.Rotation.R180);
        supportModelConnect(builder, horizontalZtop, OrnamentSupport.CornerType.BOTTOM_LEFT, OrnamentSupport.EW_CONNECT, true, VariantProperties.Rotation.R270);
        return builder;
    }

    public void supportModelBase(MultiPartGenerator builder, ResourceLocation model, OrnamentSupport.CornerType corner, boolean upper, VariantProperties.Rotation y) {
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, corner).term(OrnamentSupport.UPPER_HALF, upper),
                Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, y).with(VariantProperties.UV_LOCK, true)
        );
    }

    public void supportModelConnect(MultiPartGenerator builder, ResourceLocation model, OrnamentSupport.CornerType corner, BooleanProperty connect, boolean upper, VariantProperties.Rotation y) {
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, corner).term(OrnamentSupport.UPPER_HALF, upper).term(connect, true),
                Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, y).with(VariantProperties.UV_LOCK, true)
        );
    }
}
