package com.androsa.ornamental.builder;

import com.androsa.ornamental.data.OrnamentalBlockTags;
import com.androsa.ornamental.data.OrnamentalItemTags;
import com.androsa.ornamental.registry.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.List;

public class OrnamentBuilders {

    public static final OrnamentBuilder IRON = new OrnamentBuilder("iron")
            .mapColor(MapColor.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.IRON_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(BlockSetType.IRON)
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED)));

    public static final OrnamentBuilder GOLD = new OrnamentBuilder("gold")
            .mapColor(MapColor.GOLD)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.GOLD_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(BlockSetType.GOLD)
            .instrument(NoteBlockInstrument.BELL)
            .addTags(new ArrayList<>(List.of(OrnamentalBlockTags.PIGLIN_GUARDED, OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.IRON_REQUIRED)),
                    new ArrayList<>(List.of(OrnamentalItemTags.PIGLIN_LOVED)));

    public static final OrnamentBuilder DIAMOND = new OrnamentBuilder("diamond")
            .mapColor(MapColor.DIAMOND)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.DIAMOND_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(new BlockSetBuilder("diamond")
                    .soundType(SoundType.METAL)
                    .ironSounds()
                    .build())
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.IRON_REQUIRED)));

    public static final OrnamentBuilder EMERALD = new OrnamentBuilder("emerald")
            .mapColor(MapColor.EMERALD)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.EMERALD_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(new BlockSetBuilder("emerald")
                    .soundType(SoundType.METAL)
                    .ironSounds()
                    .build())
            .instrument(NoteBlockInstrument.BIT)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.IRON_REQUIRED)));

    public static final OrnamentBuilder LAPIS = new OrnamentBuilder("lapis")
            .mapColor(MapColor.LAPIS)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.LAPIS_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(new BlockSetBuilder("lapis")
                    .ironSounds()
                    .build())
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED)));

    public static final OrnamentBuilder OBSIDIAN = new OrnamentBuilder("obsidian")
            .mapColor(MapColor.COLOR_BLACK)
            .hardnessAndResistance(50.0F, 2000.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.OBSIDIAN)
            .blockSetType(new BlockSetBuilder("obsidian")
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .instrument(NoteBlockInstrument.BASEDRUM)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.DRAGON_IMMUNE, OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.DIAMOND_REQUIRED)));

    public static final OrnamentBuilder COAL = new OrnamentBuilder("coal")
            .mapColor(MapColor.COLOR_BLACK)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .burnTime(10500, 5250, 4000, 8000, 12000, 5250, 4000, 4000, 12000, 5250)
            .stairBaseBlock(() -> Blocks.COAL_BLOCK)
            .blockSetType(new BlockSetBuilder("coal")
                    .openByHand()
                    .openByBreeze()
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .instrument(NoteBlockInstrument.BASEDRUM)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL)));

    public static final OrnamentBuilder REDSTONE = new OrnamentBuilder("redstone")
            .mapColor(MapColor.FIRE)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .hasPower()
            .stairBaseBlock(() -> Blocks.REDSTONE_BLOCK)
            .setRedstoneConduction((state, level, pos) -> false)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(new BlockSetBuilder("redstone")
                    .openByHand()
                    .openByBreeze()
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL)));

    public static final OrnamentBuilder MISSINGNO = new OrnamentBuilder("missingno")
            .mapColor(MapColor.COLOR_MAGENTA)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.IRON_BLOCK)
            .blockSetType(new BlockSetBuilder("missingno")
                    .soundType(SoundType.METAL)
                    .ironSounds()
                    .build())
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.IRON_REQUIRED)));

    public static final OrnamentBuilder CLAY = new OrnamentBuilder("clay")
            .mapColor(MapColor.CLAY)
            .hardnessAndResistance(0.6F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.CLAY)
            .blockSetType(new BlockSetBuilder("clay")
                    .soundType(SoundType.GRAVEL)
                    .weakBlockSet()
                    .build())
            .instrument(NoteBlockInstrument.FLUTE)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.SHOVEL_TOOL)));

    public static final OrnamentBuilder DIRT = new OrnamentBuilder("dirt")
            .mapColor(MapColor.DIRT)
            .hardness(0.5F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.DIRT)
            .blockSetType(new BlockSetBuilder("grass")
                    .soundType(SoundType.GRAVEL)
                    .weakBlockSet()
                    .build())
            .setConversionPredicates(ImmutableList.of(
                    new BlockConverter(
                            (s, l, p, e, h, r) -> !e.getItemInHand(h).isEmpty() && e.getItemInHand(h).getItem() == Items.BONE_MEAL,
                            () -> List.of(ModBlocks.grass_stairs, ModBlocks.grass_slab, ModBlocks.grass_fence, ModBlocks.grass_trapdoor, ModBlocks.grass_fence_gate, ModBlocks.grass_door, ModBlocks.grass_pole, ModBlocks.grass_beam, ModBlocks.grass_wall, ModBlocks.grass_saddle_door, ModBlocks.grass_support),
                            SoundEvents.GRASS_BREAK)))
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.SHOVEL_TOOL)));

    public static final OrnamentBuilder GRASS = new OrnamentBuilder("grass")
            .mapColor(MapColor.GRASS)
            .hardness(0.6F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.GRASS_BLOCK)
            .blockSetType(new BlockSetBuilder("grass")
                    .soundType(SoundType.GRASS)
                    .weakBlockSet()
                    .build())
            .setConversionPredicates(ImmutableList.of(
                    new BlockConverter(
                            (s, l, p, e, h, r) -> !e.getItemInHand(h).isEmpty() && e.getItemInHand(h).getItem() instanceof HoeItem,
                            () -> List.of(ModBlocks.dirt_stairs, ModBlocks.dirt_slab, ModBlocks.dirt_fence, ModBlocks.dirt_trapdoor, ModBlocks.dirt_fence_gate, ModBlocks.dirt_door, ModBlocks.dirt_pole, ModBlocks.dirt_beam, ModBlocks.dirt_wall, ModBlocks.dirt_saddle_door, ModBlocks.dirt_support),
                            SoundEvents.GRAVEL_BREAK),
                    new BlockConverter(
                            (s, l, p, e, h, r) -> !e.getItemInHand(h).isEmpty() && e.getItemInHand(h).getItem() instanceof ShovelItem,
                            () -> List.of(ModBlocks.path_stairs, ModBlocks.path_slab, ModBlocks.path_fence, ModBlocks.path_trapdoor, ModBlocks.path_fence_gate, ModBlocks.path_door, ModBlocks.path_pole, ModBlocks.path_beam, ModBlocks.path_wall, ModBlocks.path_saddle_door, ModBlocks.path_support),
                            SoundEvents.SHOVEL_FLATTEN)))
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.SHOVEL_TOOL)));

    public static final OrnamentBuilder HAY = new OrnamentBuilder("hay")
            .mapColor(MapColor.COLOR_YELLOW)
            .hardness(0.5F)
            .fall(0.2F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.HAY_BLOCK)
            .instrument(NoteBlockInstrument.BANJO)
            .blockSetType(new BlockSetBuilder("hay")
                    .soundType(SoundType.GRASS)
                    .weakBlockSet()
                    .build());

    public static final OrnamentBuilder PATH = new OrnamentBuilder("dirt_path")
            .mapColor(MapColor.DIRT)
            .hardness(0.6F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.DIRT_PATH)
            .blockSetType(new BlockSetBuilder("dirt_path")
                    .soundType(SoundType.GRASS)
                    .weakBlockSet()
                    .build())
            .setConversionPredicates(ImmutableList.of(
                    new BlockConverter(
                            (s, l, p, e, h, r) -> !e.getItemInHand(h).isEmpty() && e.getItemInHand(h).getItem() instanceof HoeItem,
                            () -> List.of(ModBlocks.grass_stairs, ModBlocks.grass_slab, ModBlocks.grass_fence, ModBlocks.grass_trapdoor, ModBlocks.grass_fence_gate, ModBlocks.grass_door, ModBlocks.grass_pole, ModBlocks.grass_beam, ModBlocks.grass_wall, ModBlocks.grass_saddle_door, ModBlocks.grass_support),
                            SoundEvents.GRASS_BREAK)))
            .usePathShapes()
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.SHOVEL_TOOL)));

    public static final OrnamentBuilder BRICK = new OrnamentBuilder("brick")
            .mapColor(MapColor.COLOR_RED)
            .hardnessAndResistance(2.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.BRICKS)
            .blockSetType(new BlockSetBuilder("brick")
                    .openByHand()
                    .openByBreeze()
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .instrument(NoteBlockInstrument.BASEDRUM)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL)));

    public static final OrnamentBuilder QUARTZ = new OrnamentBuilder("quartz")
            .mapColor(MapColor.QUARTZ)
            .hardnessAndResistance(0.8F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.QUARTZ_BLOCK)
            .blockSetType(new BlockSetBuilder("quartz")
                    .openByHand()
                    .openByBreeze()
                    .doorSounds(SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE)
                    .trapdoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .instrument(NoteBlockInstrument.BASEDRUM)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL)));

    public static final OrnamentBuilder BONE = new OrnamentBuilder("bone")
            .mapColor(MapColor.SAND)
            .hardnessAndResistance(2.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.BONE_BLOCK)
            .blockSetType(new BlockSetBuilder("bone")
                    .weakBlockSet()
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .instrument(NoteBlockInstrument.XYLOPHONE)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL)));

    public static final OrnamentBuilder NETHER_BRICK = new OrnamentBuilder("nether_brick")
            .mapColor(MapColor.NETHER)
            .hardnessAndResistance(2.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.NETHER_BRICKS)
            .blockSetType(new BlockSetBuilder("nether_brick")
                    .openByHand()
                    .openByBreeze()
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .instrument(NoteBlockInstrument.BASEDRUM)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL)));

    public static final OrnamentBuilder RED_NETHER_BRICK = new OrnamentBuilder("red_nether_brick")
            .mapColor(MapColor.NETHER)
            .hardnessAndResistance(2.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.RED_NETHER_BRICKS)
            .blockSetType(new BlockSetBuilder("red_nether_brick")
                    .openByHand()
                    .openByBreeze()
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .instrument(NoteBlockInstrument.BASEDRUM)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL)));

    public static final OrnamentBuilder SNOW = new OrnamentBuilder("snow")
            .mapColor(MapColor.SNOW)
            .hardnessAndResistance(0.1F)
            .requiresTool()
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.SNOW_BLOCK)
            .blockSetType(new BlockSetBuilder("snow")
                    .weakBlockSet()
                    .soundType(SoundType.SNOW)
                    .build())
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.SNOW, OrnamentalBlockTags.SHOVEL_TOOL)));

    public static final OrnamentBuilder ICE = new OrnamentBuilder("ice")
            .mapColor(MapColor.ICE)
            .hardnessAndResistance(0.5F)
            .slip(0.98F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.ICE)
            .blockSetType(new BlockSetBuilder("ice")
                    .weakBlockSet()
                    .soundType(SoundType.GLASS)
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .ticks()
            .canMelt(Blocks.WATER, true)
            .notSolid()
            .doBreakableBlockCull()
            .setRedstoneConduction((state, reader, pos) -> false)
            .setCanEntitySpawn((state, reader, pos, type) -> type == EntityType.POLAR_BEAR && state.isFaceSturdy(reader, pos, Direction.UP))
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL)));

    public static final OrnamentBuilder PACKED_ICE = new OrnamentBuilder("packed_ice")
            .mapColor(MapColor.ICE)
            .hardnessAndResistance(0.5F)
            .slip(0.98F)
            .stairBaseBlock(() -> Blocks.PACKED_ICE)
            .blockSetType(new BlockSetBuilder("packed_ice")
                    .weakBlockSet()
                    .soundType(SoundType.GLASS)
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .instrument(NoteBlockInstrument.CHIME)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL)));

    public static final OrnamentBuilder BLUE_ICE = new OrnamentBuilder("blue_ice")
            .mapColor(MapColor.ICE)
            .hardnessAndResistance(2.8F)
            .slip(0.989F)
            .stairBaseBlock(() -> Blocks.BLUE_ICE)
            .blockSetType(new BlockSetBuilder("blue_ice")
                    .weakBlockSet()
                    .soundType(SoundType.GLASS)
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL)));

    public static final OrnamentBuilder NETHERITE = new OrnamentBuilder("netherite")
            .mapColor(MapColor.COLOR_BLACK)
            .hardnessAndResistance(50.0F, 1200.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.NETHERITE_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(new BlockSetBuilder("netherite")
                    .soundType(SoundType.NETHERITE_BLOCK)
                    .ironSounds()
                    .build())
            .isFireproof()
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.DIAMOND_REQUIRED)));

    public static final OrnamentBuilder COPPER = new OrnamentBuilder("copper")
            .mapColor(MapColor.COLOR_ORANGE)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.COPPER_BLOCK)
            .saddledoorSounds(SoundEvents.COPPER_TRAPDOOR_OPEN, SoundEvents.COPPER_TRAPDOOR_CLOSE)
            .blockSetType(BlockSetType.COPPER)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED)));

    public static final OrnamentBuilder EXPOSED_COPPER = new OrnamentBuilder("exposed_copper")
            .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.EXPOSED_COPPER)
            .saddledoorSounds(SoundEvents.COPPER_TRAPDOOR_OPEN, SoundEvents.COPPER_TRAPDOOR_CLOSE)
            .blockSetType(new BlockSetBuilder("exposed_copper")
                    .openByHand()
                    .openByBreeze()
                    .soundType(SoundType.COPPER)
                    .doorSounds(SoundEvents.COPPER_DOOR_CLOSE, SoundEvents.COPPER_DOOR_OPEN)
                    .trapdoorSounds(SoundEvents.COPPER_TRAPDOOR_CLOSE, SoundEvents.COPPER_TRAPDOOR_OPEN)
                    .pressurePlateSounds(SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED)));

    public static final OrnamentBuilder WEATHERED_COPPER = new OrnamentBuilder("weathered_copper")
            .mapColor(MapColor.WARPED_STEM)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.WEATHERED_COPPER)
            .saddledoorSounds(SoundEvents.COPPER_TRAPDOOR_OPEN, SoundEvents.COPPER_TRAPDOOR_CLOSE)
            .blockSetType(new BlockSetBuilder("weathered_copper")
                    .openByHand()
                    .openByBreeze()
                    .soundType(SoundType.COPPER)
                    .doorSounds(SoundEvents.COPPER_DOOR_CLOSE, SoundEvents.COPPER_DOOR_OPEN)
                    .trapdoorSounds(SoundEvents.COPPER_TRAPDOOR_CLOSE, SoundEvents.COPPER_TRAPDOOR_OPEN)
                    .pressurePlateSounds(SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED)));

    public static final OrnamentBuilder OXIDIZED_COPPER = new OrnamentBuilder("oxidized_copper")
            .mapColor(MapColor.WARPED_NYLIUM)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.OXIDIZED_COPPER)
            .saddledoorSounds(SoundEvents.COPPER_TRAPDOOR_OPEN, SoundEvents.COPPER_TRAPDOOR_CLOSE)
            .blockSetType(new BlockSetBuilder("oxidized_copper")
                    .openByHand()
                    .openByBreeze()
                    .soundType(SoundType.COPPER)
                    .doorSounds(SoundEvents.COPPER_DOOR_CLOSE, SoundEvents.COPPER_DOOR_OPEN)
                    .trapdoorSounds(SoundEvents.COPPER_TRAPDOOR_CLOSE, SoundEvents.COPPER_TRAPDOOR_OPEN)
                    .pressurePlateSounds(SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED)));

    public static final OrnamentBuilder AMETHYST = new OrnamentBuilder("amethyst")
            .mapColor(MapColor.COLOR_PURPLE)
            .hardnessAndResistance(1.5F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.AMETHYST_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(new BlockSetBuilder("amethyst")
                    .openByHand()
                    .soundType(SoundType.AMETHYST)
                    .ironSounds()
                    .build())
            .projectileHitSound(ImmutableList.of(SoundEvents.AMETHYST_BLOCK_HIT, SoundEvents.AMETHYST_BLOCK_CHIME))
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.CRYSTAL_SOUNDS, OrnamentalBlockTags.VIBRATIONS, OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED)));

    public static final OrnamentBuilder MAGMA = new OrnamentBuilder("magma")
            .mapColor(MapColor.NETHER)
            .hardnessAndResistance(0.5F)
            .lightLevel(3)
            .requiresTool()
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(new BlockSetBuilder("magma")
                    .openByHand()
                    .doorSounds(SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE)
                    .trapdoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .instrument(NoteBlockInstrument.BASEDRUM)
            .ticks()
            .doPostProcessing()
            .doEmissiveRendering()
            .setCanEntitySpawn((state, reader, pos, type) -> type.fireImmune())
            .floorHazard(new FloorHazard((level, pos, state, entity) -> !entity.isSteppingCarefully() && entity instanceof LivingEntity living && !EnchantmentHelper.hasFrostWalker(living), level -> level.damageSources().hotFloor(), 1.0F))
            .bubbleUnderwater(20, true, true)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.INFINIBURN_OVERWORLD, OrnamentalBlockTags.PICKAXE_TOOL)));

    public static final OrnamentBuilder CALCITE = new OrnamentBuilder("calcite")
            .mapColor(MapColor.TERRACOTTA_WHITE)
            .hardnessAndResistance(0.75F)
            .requiresTool()
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(new BlockSetBuilder("calcite")
                    .openByHand()
                    .openByBreeze()
                    .soundType(SoundType.CALCITE)
                    .doorSounds(SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE)
                    .trapdoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
                    .pressurePlateSounds(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonSounds(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
                    .build())
            .instrument(NoteBlockInstrument.BASEDRUM)
            .addBlockTags(new ArrayList<>(List.of(OrnamentalBlockTags.PICKAXE_TOOL)));
}
