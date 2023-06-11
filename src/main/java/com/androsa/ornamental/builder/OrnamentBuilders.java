package com.androsa.ornamental.builder;

import com.androsa.ornamental.data.OrnamentalBlockTags;
import com.androsa.ornamental.data.OrnamentalItemTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

import java.util.List;

public class OrnamentBuilders {

    public static final OrnamentBuilder IRON = new OrnamentBuilder("iron")
            .mapColor(MapColor.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.IRON_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(BlockSetType.IRON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED));

    public static final OrnamentBuilder GOLD = new OrnamentBuilder("gold")
            .mapColor(MapColor.GOLD)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.GOLD_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(BlockSetType.GOLD)
            .addBlockTags(List.of(OrnamentalBlockTags.PIGLIN_GUARDED, OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.IRON_REQUIRED))
            .addItemTags(List.of(OrnamentalItemTags.PIGLIN_LOVED));

    public static final OrnamentBuilder DIAMOND = new OrnamentBuilder("diamond")
            .mapColor(MapColor.DIAMOND)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.DIAMOND_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.METAL, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.IRON_REQUIRED));

    public static final OrnamentBuilder EMERALD = new OrnamentBuilder("emerald")
            .mapColor(MapColor.EMERALD)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.EMERALD_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.METAL, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.IRON_REQUIRED));

    public static final OrnamentBuilder LAPIS = new OrnamentBuilder("lapis")
            .mapColor(MapColor.LAPIS)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.LAPIS_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.STONE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED));

    public static final OrnamentBuilder OBSIDIAN = new OrnamentBuilder("obsidian")
            .mapColor(MapColor.COLOR_BLACK)
            .hardnessAndResistance(50.0F, 2000.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.OBSIDIAN)
            .blockSetType(SoundType.STONE, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.DRAGON_IMMUNE, OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.DIAMOND_REQUIRED));

    public static final OrnamentBuilder COAL = new OrnamentBuilder("coal")
            .mapColor(MapColor.COLOR_BLACK)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .burnTime(10500, 5250, 4000, 8000, 12000, 5250, 4000, 4000, 12000, 5250)
            .stairBaseBlock(() -> Blocks.COAL_BLOCK)
            .blockSetTypeByHand(SoundType.STONE, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL));

    public static final OrnamentBuilder REDSTONE = new OrnamentBuilder("redstone")
            .mapColor(MapColor.FIRE)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .hasPower()
            .stairBaseBlock(() -> Blocks.REDSTONE_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.METAL, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL));

    public static final OrnamentBuilder MISSINGNO = new OrnamentBuilder("missingno")
            .mapColor(MapColor.COLOR_MAGENTA)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.IRON_BLOCK)
            .blockSetType(SoundType.METAL, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.IRON_REQUIRED));

    public static final OrnamentBuilder CLAY = new OrnamentBuilder("clay")
            .mapColor(MapColor.CLAY)
            .hardnessAndResistance(0.6F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.CLAY)
            .blockSetTypeByHand(SoundType.GRAVEL, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.SHOVEL_TOOL));

    public static final OrnamentBuilder DIRT = new OrnamentBuilder("dirt")
            .mapColor(MapColor.DIRT)
            .hardness(0.5F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.DIRT)
            .blockSetTypeByHand(SoundType.GRAVEL, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON)
            .boneMealToGrass()
            .addBlockTags(List.of(OrnamentalBlockTags.SHOVEL_TOOL));

    public static final OrnamentBuilder GRASS = new OrnamentBuilder("grass")
            .mapColor(MapColor.GRASS)
            .hardness(0.6F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.GRASS)
            .blockSetTypeByHand(SoundType.GRASS, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON)
            .hoeToDirt()
            .shovelToPath()
            .addBlockTags(List.of(OrnamentalBlockTags.SHOVEL_TOOL));

    public static final OrnamentBuilder HAY = new OrnamentBuilder("hay")
            .mapColor(MapColor.COLOR_YELLOW)
            .hardness(0.5F)
            .fall(0.2F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.HAY_BLOCK)
            .blockSetTypeByHand(SoundType.GRASS, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON);

    public static final OrnamentBuilder PATH = new OrnamentBuilder("dirt_path")
            .mapColor(MapColor.DIRT)
            .hardness(0.6F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.DIRT_PATH)
            .blockSetTypeByHand(SoundType.GRASS, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON)
            .hoeToGrass()
            .usePathShapes()
            .addBlockTags(List.of(OrnamentalBlockTags.SHOVEL_TOOL));

    public static final OrnamentBuilder BRICK = new OrnamentBuilder("brick")
            .mapColor(MapColor.COLOR_RED)
            .hardnessAndResistance(2.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.BRICKS)
            .blockSetType(SoundType.STONE, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL));

    public static final OrnamentBuilder QUARTZ = new OrnamentBuilder("quartz")
            .mapColor(MapColor.QUARTZ)
            .hardnessAndResistance(0.8F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.QUARTZ_BLOCK)
            .blockSetType(SoundType.STONE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL));

    public static final OrnamentBuilder BONE = new OrnamentBuilder("bone")
            .mapColor(MapColor.SAND)
            .hardnessAndResistance(2.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.BONE_BLOCK)
            .blockSetTypeByHand(SoundType.STONE, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL));

    public static final OrnamentBuilder NETHER_BRICK = new OrnamentBuilder("nether_brick")
            .mapColor(MapColor.NETHER)
            .hardnessAndResistance(2.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.NETHER_BRICKS)
            .blockSetType(SoundType.STONE, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL));

    public static final OrnamentBuilder RED_NETHER_BRICK = new OrnamentBuilder("red_nether_brick")
            .mapColor(MapColor.NETHER)
            .hardnessAndResistance(2.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.RED_NETHER_BRICKS)
            .blockSetType(SoundType.STONE, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL));

    public static final OrnamentBuilder SNOW = new OrnamentBuilder("snow")
            .mapColor(MapColor.SNOW)
            .hardnessAndResistance(0.1F)
            .requiresTool()
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.SNOW_BLOCK)
            .blockSetTypeByHand(SoundType.SNOW, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.SNOW, OrnamentalBlockTags.SHOVEL_TOOL));

    public static final OrnamentBuilder ICE = new OrnamentBuilder("ice")
            .mapColor(MapColor.ICE)
            .hardnessAndResistance(0.5F)
            .slip(0.98F)
            .canFallThrough()
            .stairBaseBlock(() -> Blocks.ICE)
            .blockSetTypeByHand(SoundType.GLASS, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .ticks()
            .canMelt(Blocks.WATER, true)
            .notSolid()
            .doBreakableBlockCull()
            .setCanEntitySpawn((state, reader, pos, type) -> type == EntityType.POLAR_BEAR && state.isFaceSturdy(reader, pos, Direction.UP))
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL));

    public static final OrnamentBuilder PACKED_ICE = new OrnamentBuilder("packed_ice")
            .mapColor(MapColor.ICE)
            .hardnessAndResistance(0.5F)
            .slip(0.98F)
            .stairBaseBlock(() -> Blocks.PACKED_ICE)
            .blockSetTypeByHand(SoundType.GLASS, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL));

    public static final OrnamentBuilder BLUE_ICE = new OrnamentBuilder("blue_ice")
            .mapColor(MapColor.ICE)
            .hardnessAndResistance(2.8F)
            .slip(0.989F)
            .stairBaseBlock(() -> Blocks.BLUE_ICE)
            .blockSetTypeByHand(SoundType.GLASS, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL));

    public static final OrnamentBuilder NETHERITE = new OrnamentBuilder("netherite")
            .mapColor(MapColor.COLOR_BLACK)
            .hardnessAndResistance(50.0F, 1200.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.NETHERITE_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.NETHERITE_BLOCK, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .isFireproof()
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.DIAMOND_REQUIRED));

    public static final OrnamentBuilder COPPER = new OrnamentBuilder("copper")
            .mapColor(MapColor.COLOR_ORANGE)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.COPPER_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.COPPER, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED));

    public static final OrnamentBuilder EXPOSED_COPPER = new OrnamentBuilder("exposed_copper")
            .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.EXPOSED_COPPER)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.COPPER, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED));

    public static final OrnamentBuilder WEATHERED_COPPER = new OrnamentBuilder("weathered_copper")
            .mapColor(MapColor.WARPED_STEM)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.WEATHERED_COPPER)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.COPPER, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED));

    public static final OrnamentBuilder OXIDIZED_COPPER = new OrnamentBuilder("oxidized_copper")
            .mapColor(MapColor.WARPED_NYLIUM)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.OXIDIZED_COPPER)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.COPPER, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED));

    public static final OrnamentBuilder AMETHYST = new OrnamentBuilder("amethyst")
            .mapColor(MapColor.COLOR_PURPLE)
            .hardnessAndResistance(1.5F)
            .requiresTool()
            .stairBaseBlock(() -> Blocks.AMETHYST_BLOCK)
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.AMETHYST, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .projectileHitSound(ImmutableList.of(SoundEvents.AMETHYST_BLOCK_HIT, SoundEvents.AMETHYST_BLOCK_CHIME))
            .addBlockTags(List.of(OrnamentalBlockTags.CRYSTAL_SOUNDS, OrnamentalBlockTags.PICKAXE_TOOL, OrnamentalBlockTags.STONE_REQUIRED));

    public static final OrnamentBuilder MAGMA = new OrnamentBuilder("magma")
            .mapColor(MapColor.NETHER)
            .hardnessAndResistance(0.5F)
            .lightLevel(3)
            .requiresTool()
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.STONE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .ticks()
            .doPostProcessing()
            .doEmissiveRendering()
            .setCanEntitySpawn((state, reader, pos, type) -> type.fireImmune())
            .floorHazard(((level, pos, state, entity) -> !entity.isSteppingCarefully() && entity instanceof LivingEntity living && !EnchantmentHelper.hasFrostWalker(living)), (level -> level.damageSources().hotFloor()), 1.0F)
            .bubbleUnderwater(20, true, true)
            .addBlockTags(List.of(OrnamentalBlockTags.INFINIBURN_OVERWORLD, OrnamentalBlockTags.PICKAXE_TOOL));

    public static final OrnamentBuilder CALCITE = new OrnamentBuilder("calcite")
            .mapColor(MapColor.TERRACOTTA_WHITE)
            .hardnessAndResistance(0.75F)
            .requiresTool()
            .saddledoorSounds(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE)
            .blockSetType(SoundType.CALCITE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
            .addBlockTags(List.of(OrnamentalBlockTags.PICKAXE_TOOL));
}
