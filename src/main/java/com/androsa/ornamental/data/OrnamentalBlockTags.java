package com.androsa.ornamental.data;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.data.provider.OrnamentalBlockTagProvider;
import com.androsa.ornamental.registry.ModTags;
import com.google.common.collect.ImmutableSet;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class OrnamentalBlockTags extends OrnamentalBlockTagProvider {

    private static final ImmutableSet<Supplier<? extends Block>> BEACON_BASES = ImmutableSet.of(
            ModBlocks.iron_stairs, ModBlocks.gold_stairs, ModBlocks.diamond_stairs, ModBlocks.emerald_stairs, ModBlocks.netherite_stairs);
    private static final ImmutableSet<Supplier<? extends Block>> CRYSTAL_SOUNDS = ImmutableSet.of(
            ModBlocks.amethyst_stairs, ModBlocks.amethyst_slab, ModBlocks.amethyst_fence, ModBlocks.amethyst_trapdoor, ModBlocks.amethyst_fence_gate,
            ModBlocks.amethyst_door, ModBlocks.amethyst_pole, ModBlocks.amethyst_beam, ModBlocks.amethyst_wall, ModBlocks.amethyst_saddle_door);
    private static final ImmutableSet<Supplier<? extends Block>> DOORS = ImmutableSet.of(
            ModBlocks.gold_door, ModBlocks.diamond_door, ModBlocks.emerald_door, ModBlocks.lapis_door, ModBlocks.obsidian_door, ModBlocks.coal_door, ModBlocks.redstone_door,
            ModBlocks.missingno_door, ModBlocks.clay_door, ModBlocks.dirt_door, ModBlocks.grass_door, ModBlocks.hay_door, ModBlocks.path_door, ModBlocks.brick_door, ModBlocks.quartz_door, ModBlocks.bone_door,
            ModBlocks.nether_brick_door, ModBlocks.red_nether_brick_door, ModBlocks.snow_door, ModBlocks.ice_door, ModBlocks.packed_ice_door, ModBlocks.blue_ice_door, ModBlocks.netherite_door, ModBlocks.amethyst_door,
            ModBlocks.copper_door, ModBlocks.exposed_copper_door, ModBlocks.weathered_copper_door, ModBlocks.oxidized_copper_door);
    private static final ImmutableSet<Supplier<? extends Block>> DRAGON_IMMUNE = ImmutableSet.of(
            ModBlocks.obsidian_stairs, ModBlocks.obsidian_slab, ModBlocks.obsidian_fence, ModBlocks.obsidian_trapdoor, ModBlocks.obsidian_fence_gate,
            ModBlocks.obsidian_door, ModBlocks.obsidian_pole, ModBlocks.obsidian_beam, ModBlocks.obsidian_wall, ModBlocks.obsidian_saddle_door);
    private static final ImmutableSet<Supplier<? extends Block>> FENCES = ImmutableSet.of(
            ModBlocks.iron_fence, ModBlocks.gold_fence, ModBlocks.diamond_fence, ModBlocks.emerald_fence, ModBlocks.lapis_fence, ModBlocks.obsidian_fence, ModBlocks.coal_fence, ModBlocks.redstone_fence,
            ModBlocks.missingno_fence, ModBlocks.clay_fence, ModBlocks.dirt_fence, ModBlocks.grass_fence, ModBlocks.hay_fence, ModBlocks.path_fence, ModBlocks.brick_fence, ModBlocks.quartz_fence,
            ModBlocks.bone_fence, ModBlocks.red_nether_brick_fence, ModBlocks.snow_fence, ModBlocks.ice_fence, ModBlocks.packed_ice_fence, ModBlocks.blue_ice_fence, ModBlocks.netherite_fence, ModBlocks.amethyst_fence,
            ModBlocks.copper_fence, ModBlocks.exposed_copper_fence, ModBlocks.weathered_copper_fence, ModBlocks.oxidized_copper_fence);
    private static final ImmutableSet<Supplier<? extends Block>> FENCE_GATES = ImmutableSet.of(
            ModBlocks.iron_fence_gate, ModBlocks.gold_fence_gate, ModBlocks.diamond_fence_gate, ModBlocks.emerald_fence_gate, ModBlocks.lapis_fence_gate, ModBlocks.obsidian_fence_gate, ModBlocks.coal_fence_gate,
            ModBlocks.redstone_fence_gate, ModBlocks.missingno_fence_gate, ModBlocks.clay_fence_gate, ModBlocks.dirt_fence_gate, ModBlocks.grass_fence_gate, ModBlocks.hay_fence_gate, ModBlocks.path_fence_gate,
            ModBlocks.brick_fence_gate, ModBlocks.quartz_fence_gate, ModBlocks.bone_fence_gate, ModBlocks.nether_brick_fence_gate, ModBlocks.red_nether_brick_fence_gate, ModBlocks.snow_fence_gate, ModBlocks.ice_fence_gate,
            ModBlocks.packed_ice_fence_gate, ModBlocks.blue_ice_fence_gate, ModBlocks.netherite_fence_gate, ModBlocks.amethyst_fence_gate, ModBlocks.copper_fence_gate, ModBlocks.exposed_copper_fence_gate,
            ModBlocks.weathered_copper_fence_gate, ModBlocks.oxidized_copper_fence_gate);
    private static final ImmutableSet<Supplier<? extends Block>> PIGLIN_GUARDED = ImmutableSet.of(
            ModBlocks.gold_stairs, ModBlocks.gold_slab, ModBlocks.gold_fence, ModBlocks.gold_trapdoor, ModBlocks.gold_fence_gate,
            ModBlocks.gold_door, ModBlocks.gold_pole, ModBlocks.gold_beam, ModBlocks.gold_wall, ModBlocks.gold_saddle_door);
    private static final ImmutableSet<Supplier<? extends Block>> SLABS = ImmutableSet.of(
            ModBlocks.iron_slab, ModBlocks.gold_slab, ModBlocks.diamond_slab, ModBlocks.emerald_slab, ModBlocks.lapis_slab, ModBlocks.obsidian_slab, ModBlocks.coal_slab, ModBlocks.redstone_slab,
            ModBlocks.missingno_slab, ModBlocks.clay_slab, ModBlocks.dirt_slab, ModBlocks.grass_slab, ModBlocks.hay_slab, ModBlocks.path_slab, ModBlocks.bone_slab, ModBlocks.snow_slab, ModBlocks.ice_slab,
            ModBlocks.packed_ice_slab, ModBlocks.blue_ice_slab, ModBlocks.netherite_slab, ModBlocks.amethyst_slab, ModBlocks.copper_slab, ModBlocks.exposed_copper_slab, ModBlocks.weathered_copper_slab, ModBlocks.exposed_copper_slab);
    private static final ImmutableSet<Supplier<? extends Block>> SNOW = ImmutableSet.of(
            ModBlocks.snow_stairs, ModBlocks.snow_slab, ModBlocks.snow_fence, ModBlocks.snow_trapdoor, ModBlocks.snow_fence_gate,
            ModBlocks.snow_door, ModBlocks.snow_pole, ModBlocks.snow_beam, ModBlocks.snow_wall, ModBlocks.snow_saddle_door);
    private static final ImmutableSet<Supplier<? extends Block>> STAIRS = ImmutableSet.of(
            ModBlocks.iron_stairs, ModBlocks.gold_stairs, ModBlocks.diamond_stairs, ModBlocks.emerald_stairs, ModBlocks.lapis_stairs, ModBlocks.obsidian_stairs, ModBlocks.coal_stairs, ModBlocks.redstone_stairs,
            ModBlocks.missingno_stairs, ModBlocks.clay_stairs, ModBlocks.dirt_stairs, ModBlocks.grass_stairs, ModBlocks.hay_stairs, ModBlocks.path_stairs, ModBlocks.bone_stairs, ModBlocks.snow_stairs,
            ModBlocks.ice_stairs, ModBlocks.packed_ice_stairs, ModBlocks.blue_ice_stairs, ModBlocks.netherite_stairs, ModBlocks.amethyst_stairs, ModBlocks.copper_stairs, ModBlocks.exposed_copper_stairs,
            ModBlocks.weathered_copper_stairs, ModBlocks.oxidized_copper_stairs);
    private static final ImmutableSet<Supplier<? extends Block>> TRAPDOORS = ImmutableSet.of(
            ModBlocks.gold_trapdoor, ModBlocks.diamond_trapdoor, ModBlocks.emerald_trapdoor, ModBlocks.lapis_trapdoor, ModBlocks.obsidian_trapdoor, ModBlocks.coal_trapdoor, ModBlocks.redstone_trapdoor, ModBlocks.missingno_trapdoor,
            ModBlocks.clay_trapdoor, ModBlocks.dirt_trapdoor, ModBlocks.grass_trapdoor, ModBlocks.hay_trapdoor, ModBlocks.path_trapdoor, ModBlocks.brick_trapdoor, ModBlocks.quartz_trapdoor, ModBlocks.bone_trapdoor,
            ModBlocks.nether_brick_trapdoor, ModBlocks.red_nether_brick_trapdoor, ModBlocks.snow_trapdoor, ModBlocks.ice_trapdoor, ModBlocks.packed_ice_trapdoor, ModBlocks.blue_ice_trapdoor, ModBlocks.netherite_trapdoor,
            ModBlocks.amethyst_trapdoor, ModBlocks.copper_trapdoor, ModBlocks.exposed_copper_trapdoor, ModBlocks.weathered_copper_trapdoor, ModBlocks.oxidized_copper_trapdoor);
    private static final ImmutableSet<Supplier<? extends Block>> WALLS = ImmutableSet.of(
			ModBlocks.iron_wall, ModBlocks.gold_wall, ModBlocks.diamond_wall, ModBlocks.emerald_wall, ModBlocks.lapis_wall, ModBlocks.obsidian_wall, ModBlocks.coal_wall, ModBlocks.redstone_wall, ModBlocks.missingno_wall,
			ModBlocks.clay_wall, ModBlocks.dirt_wall, ModBlocks.grass_wall, ModBlocks.hay_wall, ModBlocks.path_wall, ModBlocks.quartz_wall, ModBlocks.bone_wall, ModBlocks.snow_wall, ModBlocks.ice_wall, ModBlocks.packed_ice_wall,
			ModBlocks.blue_ice_wall, ModBlocks.netherite_wall, ModBlocks.amethyst_wall, ModBlocks.copper_wall, ModBlocks.exposed_copper_wall, ModBlocks.weathered_copper_wall, ModBlocks.oxidized_copper_wall);

    private static final ImmutableSet<Supplier<? extends Block>> PICKAXE_TOOL = ImmutableSet.of(
            ModBlocks.iron_stairs, ModBlocks.iron_slab, ModBlocks.iron_fence, ModBlocks.iron_fence_gate, ModBlocks.iron_pole, ModBlocks.iron_beam, ModBlocks.iron_wall, ModBlocks.iron_saddle_door,
            ModBlocks.gold_stairs, ModBlocks.gold_slab, ModBlocks.gold_fence, ModBlocks.gold_trapdoor, ModBlocks.gold_fence_gate, ModBlocks.gold_door, ModBlocks.gold_pole, ModBlocks.gold_beam, ModBlocks.gold_wall, ModBlocks.gold_saddle_door,
            ModBlocks.diamond_stairs, ModBlocks.diamond_slab, ModBlocks.diamond_fence, ModBlocks.diamond_trapdoor, ModBlocks.diamond_fence_gate, ModBlocks.diamond_door, ModBlocks.diamond_pole, ModBlocks.diamond_beam, ModBlocks.diamond_wall, ModBlocks.diamond_saddle_door,
            ModBlocks.emerald_stairs, ModBlocks.emerald_slab, ModBlocks.emerald_fence, ModBlocks.emerald_trapdoor, ModBlocks.emerald_fence_gate, ModBlocks.emerald_door, ModBlocks.emerald_pole, ModBlocks.emerald_beam, ModBlocks.emerald_wall, ModBlocks.emerald_saddle_door,
            ModBlocks.lapis_stairs, ModBlocks.lapis_slab, ModBlocks.lapis_fence, ModBlocks.lapis_trapdoor, ModBlocks.lapis_fence_gate, ModBlocks.lapis_door, ModBlocks.lapis_pole, ModBlocks.lapis_beam, ModBlocks.lapis_wall, ModBlocks.lapis_saddle_door,
            ModBlocks.obsidian_stairs, ModBlocks.obsidian_slab, ModBlocks.obsidian_fence, ModBlocks.obsidian_trapdoor, ModBlocks.obsidian_fence_gate, ModBlocks.obsidian_door, ModBlocks.obsidian_pole, ModBlocks.obsidian_beam, ModBlocks.obsidian_wall, ModBlocks.obsidian_saddle_door,
            ModBlocks.coal_stairs, ModBlocks.coal_slab, ModBlocks.coal_fence, ModBlocks.coal_trapdoor, ModBlocks.coal_fence_gate, ModBlocks.coal_door, ModBlocks.coal_pole, ModBlocks.coal_beam, ModBlocks.coal_wall, ModBlocks.coal_saddle_door,
            ModBlocks.redstone_stairs, ModBlocks.redstone_slab, ModBlocks.redstone_fence, ModBlocks.redstone_trapdoor, ModBlocks.redstone_fence_gate, ModBlocks.redstone_door, ModBlocks.redstone_pole, ModBlocks.redstone_beam, ModBlocks.redstone_wall, ModBlocks.redstone_saddle_door,
            ModBlocks.missingno_stairs, ModBlocks.missingno_slab, ModBlocks.missingno_fence, ModBlocks.missingno_trapdoor, ModBlocks.missingno_fence_gate, ModBlocks.missingno_door, ModBlocks.missingno_pole, ModBlocks.missingno_beam, ModBlocks.missingno_wall, ModBlocks.missingno_saddle_door,
            ModBlocks.brick_fence, ModBlocks.brick_trapdoor, ModBlocks.brick_fence_gate, ModBlocks.brick_door, ModBlocks.brick_pole, ModBlocks.brick_beam, ModBlocks.brick_saddle_door,
            ModBlocks.quartz_fence, ModBlocks.quartz_trapdoor, ModBlocks.quartz_fence_gate, ModBlocks.quartz_door, ModBlocks.quartz_pole, ModBlocks.quartz_beam, ModBlocks.quartz_wall, ModBlocks.quartz_saddle_door,
            ModBlocks.bone_stairs, ModBlocks.bone_slab, ModBlocks.bone_fence, ModBlocks.bone_trapdoor, ModBlocks.bone_fence_gate, ModBlocks.bone_door, ModBlocks.bone_pole, ModBlocks.bone_beam, ModBlocks.bone_wall, ModBlocks.bone_saddle_door,
            ModBlocks.nether_brick_trapdoor, ModBlocks.nether_brick_fence_gate, ModBlocks.nether_brick_door, ModBlocks.nether_brick_pole, ModBlocks.nether_brick_beam, ModBlocks.nether_brick_saddle_door,
            ModBlocks.red_nether_brick_fence, ModBlocks.red_nether_brick_trapdoor, ModBlocks.red_nether_brick_fence_gate, ModBlocks.red_nether_brick_door, ModBlocks.red_nether_brick_pole, ModBlocks.red_nether_brick_beam, ModBlocks.red_nether_brick_saddle_door,
            ModBlocks.ice_stairs, ModBlocks.ice_slab, ModBlocks.ice_fence, ModBlocks.ice_trapdoor, ModBlocks.ice_fence_gate, ModBlocks.ice_door, ModBlocks.ice_pole, ModBlocks.ice_beam, ModBlocks.ice_wall, ModBlocks.ice_saddle_door,
            ModBlocks.packed_ice_stairs, ModBlocks.packed_ice_slab, ModBlocks.packed_ice_fence, ModBlocks.packed_ice_trapdoor, ModBlocks.packed_ice_fence_gate, ModBlocks.packed_ice_door, ModBlocks.packed_ice_pole, ModBlocks.packed_ice_beam, ModBlocks.packed_ice_wall, ModBlocks.packed_ice_saddle_door,
            ModBlocks.blue_ice_stairs, ModBlocks.blue_ice_slab, ModBlocks.blue_ice_fence, ModBlocks.blue_ice_trapdoor, ModBlocks.blue_ice_fence_gate, ModBlocks.blue_ice_door, ModBlocks.blue_ice_pole, ModBlocks.blue_ice_beam, ModBlocks.blue_ice_wall, ModBlocks.blue_ice_saddle_door,
            ModBlocks.netherite_stairs, ModBlocks.netherite_slab, ModBlocks.netherite_fence, ModBlocks.netherite_trapdoor, ModBlocks.netherite_fence_gate, ModBlocks.netherite_door, ModBlocks.netherite_pole, ModBlocks.netherite_beam, ModBlocks.netherite_wall, ModBlocks.netherite_saddle_door,
            ModBlocks.amethyst_stairs, ModBlocks.amethyst_slab, ModBlocks.amethyst_fence, ModBlocks.amethyst_trapdoor, ModBlocks.amethyst_fence_gate, ModBlocks.amethyst_door, ModBlocks.amethyst_pole, ModBlocks.amethyst_beam, ModBlocks.amethyst_wall, ModBlocks.amethyst_saddle_door,
            ModBlocks.copper_stairs, ModBlocks.copper_slab, ModBlocks.copper_fence, ModBlocks.copper_trapdoor, ModBlocks.copper_fence_gate, ModBlocks.copper_door, ModBlocks.copper_pole, ModBlocks.copper_beam, ModBlocks.copper_wall, ModBlocks.copper_saddle_door,
            ModBlocks.exposed_copper_stairs, ModBlocks.exposed_copper_slab, ModBlocks.exposed_copper_fence, ModBlocks.exposed_copper_trapdoor, ModBlocks.exposed_copper_fence_gate, ModBlocks.exposed_copper_door, ModBlocks.exposed_copper_pole, ModBlocks.exposed_copper_beam, ModBlocks.exposed_copper_wall, ModBlocks.exposed_copper_saddle_door,
            ModBlocks.weathered_copper_stairs, ModBlocks.weathered_copper_slab, ModBlocks.weathered_copper_fence, ModBlocks.weathered_copper_trapdoor, ModBlocks.weathered_copper_fence_gate, ModBlocks.weathered_copper_door, ModBlocks.weathered_copper_pole, ModBlocks.weathered_copper_beam, ModBlocks.weathered_copper_wall, ModBlocks.weathered_copper_saddle_door,
            ModBlocks.oxidized_copper_stairs, ModBlocks.oxidized_copper_slab, ModBlocks.oxidized_copper_fence, ModBlocks.oxidized_copper_trapdoor, ModBlocks.oxidized_copper_fence_gate, ModBlocks.oxidized_copper_door, ModBlocks.oxidized_copper_pole, ModBlocks.oxidized_copper_beam, ModBlocks.oxidized_copper_wall, ModBlocks.oxidized_copper_saddle_door);
    private static final ImmutableSet<Supplier<? extends Block>> SHOVEL_TOOL = ImmutableSet.of(
            ModBlocks.clay_stairs, ModBlocks.clay_slab, ModBlocks.clay_fence, ModBlocks.clay_trapdoor, ModBlocks.clay_fence_gate, ModBlocks.clay_door, ModBlocks.clay_pole, ModBlocks.clay_beam, ModBlocks.clay_wall, ModBlocks.clay_saddle_door,
            ModBlocks.dirt_stairs, ModBlocks.dirt_slab, ModBlocks.dirt_fence, ModBlocks.dirt_trapdoor, ModBlocks.dirt_fence_gate, ModBlocks.dirt_door, ModBlocks.dirt_pole, ModBlocks.dirt_beam, ModBlocks.dirt_wall, ModBlocks.dirt_saddle_door,
            ModBlocks.grass_stairs, ModBlocks.grass_slab, ModBlocks.grass_fence, ModBlocks.grass_trapdoor, ModBlocks.grass_fence_gate, ModBlocks.grass_door, ModBlocks.grass_pole, ModBlocks.grass_beam, ModBlocks.grass_wall, ModBlocks.grass_saddle_door,
            ModBlocks.path_stairs, ModBlocks.path_slab, ModBlocks.path_fence, ModBlocks.path_trapdoor, ModBlocks.path_fence_gate, ModBlocks.path_door, ModBlocks.path_pole, ModBlocks.path_beam, ModBlocks.path_wall, ModBlocks.path_saddle_door,
            ModBlocks.snow_stairs, ModBlocks.snow_slab, ModBlocks.snow_fence, ModBlocks.snow_trapdoor, ModBlocks.snow_fence_gate, ModBlocks.snow_door, ModBlocks.snow_pole, ModBlocks.snow_beam, ModBlocks.snow_wall, ModBlocks.snow_saddle_door);

    public static final ImmutableSet<Supplier<? extends Block>> STONE_REQUIRED = ImmutableSet.of(
            ModBlocks.iron_stairs, ModBlocks.iron_slab, ModBlocks.iron_fence, ModBlocks.iron_fence_gate, ModBlocks.iron_pole, ModBlocks.iron_beam, ModBlocks.iron_wall, ModBlocks.iron_saddle_door,
            ModBlocks.lapis_stairs, ModBlocks.lapis_slab, ModBlocks.lapis_fence, ModBlocks.lapis_trapdoor, ModBlocks.lapis_fence_gate, ModBlocks.lapis_door, ModBlocks.lapis_pole, ModBlocks.lapis_beam, ModBlocks.lapis_wall, ModBlocks.lapis_saddle_door,
            ModBlocks.amethyst_stairs, ModBlocks.amethyst_slab, ModBlocks.amethyst_fence, ModBlocks.amethyst_trapdoor, ModBlocks.amethyst_fence_gate, ModBlocks.amethyst_door, ModBlocks.amethyst_pole, ModBlocks.amethyst_beam, ModBlocks.amethyst_wall, ModBlocks.amethyst_saddle_door,
            ModBlocks.copper_stairs, ModBlocks.copper_slab, ModBlocks.copper_fence, ModBlocks.copper_trapdoor, ModBlocks.copper_fence_gate, ModBlocks.copper_door, ModBlocks.copper_pole, ModBlocks.copper_beam, ModBlocks.copper_wall, ModBlocks.copper_saddle_door,
            ModBlocks.exposed_copper_stairs, ModBlocks.exposed_copper_slab, ModBlocks.exposed_copper_fence, ModBlocks.exposed_copper_trapdoor, ModBlocks.exposed_copper_fence_gate, ModBlocks.exposed_copper_door, ModBlocks.exposed_copper_pole, ModBlocks.exposed_copper_beam, ModBlocks.exposed_copper_wall, ModBlocks.exposed_copper_saddle_door,
            ModBlocks.weathered_copper_stairs, ModBlocks.weathered_copper_slab, ModBlocks.weathered_copper_fence, ModBlocks.weathered_copper_trapdoor, ModBlocks.weathered_copper_fence_gate, ModBlocks.weathered_copper_door, ModBlocks.weathered_copper_pole, ModBlocks.weathered_copper_beam, ModBlocks.weathered_copper_wall, ModBlocks.weathered_copper_saddle_door,
            ModBlocks.oxidized_copper_stairs, ModBlocks.oxidized_copper_slab, ModBlocks.oxidized_copper_fence, ModBlocks.oxidized_copper_trapdoor, ModBlocks.oxidized_copper_fence_gate, ModBlocks.oxidized_copper_door, ModBlocks.oxidized_copper_pole, ModBlocks.oxidized_copper_beam, ModBlocks.oxidized_copper_wall, ModBlocks.oxidized_copper_saddle_door);
    public static final ImmutableSet<Supplier<? extends Block>> IRON_REQUIRED = ImmutableSet.of(
            ModBlocks.gold_stairs, ModBlocks.gold_slab, ModBlocks.gold_fence, ModBlocks.gold_trapdoor, ModBlocks.gold_fence_gate, ModBlocks.gold_door, ModBlocks.gold_pole, ModBlocks.gold_beam, ModBlocks.gold_wall, ModBlocks.gold_saddle_door,
            ModBlocks.diamond_stairs, ModBlocks.diamond_slab, ModBlocks.diamond_fence, ModBlocks.diamond_trapdoor, ModBlocks.diamond_fence_gate, ModBlocks.diamond_door, ModBlocks.diamond_pole, ModBlocks.diamond_beam, ModBlocks.diamond_wall, ModBlocks.diamond_saddle_door,
            ModBlocks.emerald_stairs, ModBlocks.emerald_slab, ModBlocks.emerald_fence, ModBlocks.emerald_trapdoor, ModBlocks.emerald_fence_gate, ModBlocks.emerald_door, ModBlocks.emerald_pole, ModBlocks.emerald_beam, ModBlocks.emerald_wall, ModBlocks.emerald_saddle_door,
            ModBlocks.missingno_stairs, ModBlocks.missingno_slab, ModBlocks.missingno_fence, ModBlocks.missingno_trapdoor, ModBlocks.missingno_fence_gate, ModBlocks.missingno_door, ModBlocks.missingno_pole, ModBlocks.missingno_beam, ModBlocks.missingno_wall, ModBlocks.missingno_saddle_door);
    public static final ImmutableSet<Supplier<? extends Block>> DIAMOND_REQUIRED = ImmutableSet.of(
            ModBlocks.obsidian_stairs, ModBlocks.obsidian_slab, ModBlocks.obsidian_fence, ModBlocks.obsidian_trapdoor, ModBlocks.obsidian_fence_gate, ModBlocks.obsidian_door, ModBlocks.obsidian_pole, ModBlocks.obsidian_beam, ModBlocks.obsidian_wall, ModBlocks.obsidian_saddle_door,
            ModBlocks.netherite_stairs, ModBlocks.netherite_slab, ModBlocks.netherite_fence, ModBlocks.netherite_trapdoor, ModBlocks.netherite_fence_gate, ModBlocks.netherite_door, ModBlocks.netherite_pole, ModBlocks.netherite_beam, ModBlocks.netherite_wall, ModBlocks.netherite_saddle_door);

    public OrnamentalBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, OrnamentalMod.MODID, helper);
    }

    @Override
    protected void addTags() {
        addToTag(BlockTags.BEACON_BASE_BLOCKS, BEACON_BASES);
        addToTag(BlockTags.CRYSTAL_SOUND_BLOCKS, CRYSTAL_SOUNDS);
        addToTag(BlockTags.DOORS, DOORS);
        addToTag(BlockTags.DRAGON_IMMUNE, DRAGON_IMMUNE);
        addToTag(BlockTags.FENCES, FENCES);
        addToTag(Tags.Blocks.FENCES, FENCES);
        addToTag(BlockTags.FENCE_GATES, FENCE_GATES);
        addToTag(Tags.Blocks.FENCE_GATES, FENCE_GATES);
        addToTag(BlockTags.GUARDED_BY_PIGLINS, PIGLIN_GUARDED);
        addToTag(BlockTags.SLABS, SLABS);
        addToTag(BlockTags.SNOW, SNOW);
        addToTag(BlockTags.STAIRS, STAIRS);
        addToTag(BlockTags.TRAPDOORS, TRAPDOORS);
        addToTag(BlockTags.WALLS, WALLS);

        addToTag(BlockTags.MINEABLE_WITH_PICKAXE, PICKAXE_TOOL);
        addToTag(BlockTags.MINEABLE_WITH_SHOVEL, SHOVEL_TOOL);

        addToTag(BlockTags.NEEDS_STONE_TOOL, STONE_REQUIRED);
        addToTag(BlockTags.NEEDS_IRON_TOOL, IRON_REQUIRED);
        addToTag(BlockTags.NEEDS_DIAMOND_TOOL, DIAMOND_REQUIRED);

        tag(Tags.Blocks.FENCES_NETHER_BRICK).add(ModBlocks.red_nether_brick_fence.get());

        tag(ModTags.Blocks.GRASS_GOLEM_FLOWER_PLANTABLE).addTag(BlockTags.SMALL_FLOWERS);
    }
}
