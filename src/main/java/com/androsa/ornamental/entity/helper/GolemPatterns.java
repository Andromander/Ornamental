package com.androsa.ornamental.entity.helper;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockMaterialPredicate;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.material.Material;

import java.util.function.Predicate;

public class GolemPatterns {

    private static BlockPattern goldGolem;
    private static BlockPattern diamondGolem;
    private static BlockPattern emeraldGolem;
    private static BlockPattern lapisGolem;
    private static BlockPattern obsidianGolem;
    private static BlockPattern coalGolem;
    private static BlockPattern redstoneGolem;
    private static BlockPattern clayGolem;
    private static BlockPattern dirtGolem;
    private static BlockPattern hayGolem;
    private static BlockPattern brickGolem;
    private static BlockPattern quartzGolem;
    private static BlockPattern boneGolem;
    private static BlockPattern netherBrickGolem;
    private static BlockPattern redNetherBrickGolem;
    private static BlockPattern iceGolem;
    private static BlockPattern packedIceGolem;
    private static BlockPattern blueIceGolem;
    private static BlockPattern netheriteGolem;
    private static BlockPattern copperGolem;
    private static BlockPattern amethystGolem;
    private static BlockPattern magmaGolem;

    private static final Predicate<BlockState> IS_PUMPKIN = (state) ->
            state != null && (state.is(Blocks.CARVED_PUMPKIN ) || state.is(Blocks.JACK_O_LANTERN));

    public static BlockPattern getGoldPattern() {
        if (goldGolem == null) {
            goldGolem = buildPatternTB(Blocks.GOLD_BLOCK).build();
        }
        return goldGolem;
    }

    public static BlockPattern getDiamondPattern() {
        if (diamondGolem == null) {
            diamondGolem = buildPattern(Blocks.DIAMOND_BLOCK)
                    .aisle("~^~", "###")
                    .build();
        }
        return diamondGolem;
    }

    public static BlockPattern getEmeraldPattern() {
        if (emeraldGolem == null) {
            emeraldGolem = buildPatternTB(Blocks.EMERALD_BLOCK).build();
        }
        return emeraldGolem;
    }

    public static BlockPattern getLapisPattern() {
        if (lapisGolem == null) {
            lapisGolem = buildPatternTB(Blocks.LAPIS_BLOCK).build();
        }
        return lapisGolem;
    }

    public static BlockPattern getObsidianPattern() {
        if (obsidianGolem == null) {
            obsidianGolem = buildPattern(Blocks.OBSIDIAN)
                    .aisle("~^~", "###", "###", "###")
                    .build();
        }
        return obsidianGolem;
    }

    public static BlockPattern getCoalPattern() {
        if (coalGolem == null) {
            coalGolem = buildPatternTN(Blocks.COAL_BLOCK).build();
        }
        return coalGolem;
    }

    public static BlockPattern getRedstonePattern() {
        if (redstoneGolem == null) {
            redstoneGolem = buildPattern(Blocks.REDSTONE_BLOCK)
                    .aisle("~^~", "~#~", "###")
                    .build();
        }
        return redstoneGolem;
    }

    public static BlockPattern getClayPattern() {
        if (clayGolem == null) {
            clayGolem = buildPatternTN(Blocks.CLAY).build();
        }
        return clayGolem;
    }

    public static BlockPattern getDirtPattern() {
        if (dirtGolem == null) {
            dirtGolem = buildPatternS(Blocks.DIRT).build();
        }
        return dirtGolem;
    }

    public static BlockPattern getHayPattern() {
        if (hayGolem == null) {
            hayGolem = buildPattern(Blocks.HAY_BLOCK)
                    .aisle("~~^~~",  "#####", "~~#~~", "~~#~~")
                    .build();
        }
        return hayGolem;
    }

    public static BlockPattern getBrickPattern() {
        if (brickGolem == null) {
            brickGolem = buildPatternTN(Blocks.BRICKS).build();
        }
        return brickGolem;
    }

    public static BlockPattern getQuartzPattern() {
        if (quartzGolem == null) {
            quartzGolem = buildPatternTB(Blocks.QUARTZ_BLOCK).build();
        }
        return quartzGolem;
    }

    public static BlockPattern getBonePattern() {
        if (boneGolem == null) {
            boneGolem = buildPattern(Blocks.BONE_BLOCK)
                    .aisle("~^~", "###", "###", "~#~")
                    .build();
        }
        return boneGolem;
    }

    public static BlockPattern getNetherBrickPattern() {
        if (netherBrickGolem == null) {
            netherBrickGolem = buildPatternTN(Blocks.NETHER_BRICKS).build();
        }
        return netherBrickGolem;
    }

    public static BlockPattern getRedNetherBrickPattern() {
        if (redNetherBrickGolem == null) {
            redNetherBrickGolem = buildPatternTN(Blocks.RED_NETHER_BRICKS).build();
        }
        return redNetherBrickGolem;
    }

    public static BlockPattern getIcePattern() {
        if (iceGolem == null) {
            iceGolem = buildPatternTN(Blocks.ICE).build();
        }
        return iceGolem;
    }

    public static BlockPattern getPackedIcePattern() {
        if (packedIceGolem == null) {
            packedIceGolem = buildPatternTN(Blocks.PACKED_ICE).build();
        }
        return packedIceGolem;
    }

    public static BlockPattern getBlueIcePattern() {
        if (blueIceGolem == null) {
            blueIceGolem = buildPatternTN(Blocks.BLUE_ICE).build();
        }
        return blueIceGolem;
    }

    public static BlockPattern getNetheritePattern() {
        if (netheriteGolem == null) {
            netheriteGolem = buildPattern(Blocks.NETHERITE_BLOCK)
                    .aisle("~^~", "###", "###")
                    .build();
        }
        return netheriteGolem;
    }

    public static BlockPattern getCopperPattern() {
        if (copperGolem == null) {
            copperGolem = buildPattern(Blocks.COPPER_BLOCK)
                    .aisle("~^~", "###", "###", "~#~")
                    .build();
        }
        return copperGolem;
    }

    public static BlockPattern getAmethystPattern() {
        if (amethystGolem == null) {
            amethystGolem = buildPatternTB(Blocks.AMETHYST_BLOCK).build();
        }
        return amethystGolem;
    }

    public static BlockPattern getMagmaPattern() {
        if (magmaGolem == null) {
            magmaGolem = buildPatternTN(Blocks.MAGMA_BLOCK).build();
        }
        return magmaGolem;
    }

    //TB: Tall Built, see Iron Golem
    private static BlockPatternBuilder buildPatternTB(Block block) {
        return buildPattern(block)
                .aisle("~^~", "###", "~#~");
    }

    //TN: Tall Narrow, see Snow Golem
    private static BlockPatternBuilder buildPatternTN(Block block) {
        return buildPattern(block)
                .aisle("^", "#", "#");
    }

    //S: Single, one block required
    private static BlockPatternBuilder buildPatternS(Block block) {
        return buildPattern(block)
                .aisle("^", "#");
    }

    private static BlockPatternBuilder buildPattern(Block block) {
        return BlockPatternBuilder.start()
                .where('^', BlockInWorld.hasState(IS_PUMPKIN))
                .where('~', BlockInWorld.hasState(BlockMaterialPredicate.forMaterial(Material.AIR)))
                .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(block)));
    }
}
