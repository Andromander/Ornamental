package com.androsa.ornamental.entity.helper;

import com.androsa.ornamental.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.pattern.BlockPattern;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum PatternType {
    GOLD             (ModEntities.GOLD_GOLEM, GolemPatterns.getGoldPattern()),
    DIAMOND          (ModEntities.DIAMOND_GOLEM, GolemPatterns.getDiamondPattern()),
    EMERALD          (ModEntities.EMERALD_GOLEM, GolemPatterns.getEmeraldPattern()),
    LAPIS            (ModEntities.LAPIS_GOLEM, GolemPatterns.getLapisPattern()),
    OBSIDIAN         (ModEntities.OBSIDIAN_GOLEM, GolemPatterns.getObsidianPattern()),
    COAL             (ModEntities.COAL_GOLEM, GolemPatterns.getCoalPattern()),
    REDSTONE         (ModEntities.REDSTONE_GOLEM, GolemPatterns.getRedstonePattern()),
    CLAY             (ModEntities.CLAY_GOLEM, GolemPatterns.getClayPattern()),
    DIRT             (ModEntities.DIRT_GOLEM, GolemPatterns.getDirtPattern()),
    HAY              (ModEntities.HAY_GOLEM, GolemPatterns.getHayPattern()),
    BRICK            (ModEntities.BRICK_GOLEM, GolemPatterns.getBrickPattern()),
    QUARTZ           (ModEntities.QUARTZ_GOLEM, GolemPatterns.getQuartzPattern()),
    BONE             (ModEntities.BONE_GOLEM, GolemPatterns.getBonePattern()),
    NETHER_BRICK     (ModEntities.NETHER_BRICK_GOLEM, GolemPatterns.getNetherBrickPattern()),
    RED_NETHER_BRICK (ModEntities.RED_NETHER_BRICK_GOLEM, GolemPatterns.getRedNetherBrickPattern()),
    ICE              (ModEntities.ICE_GOLEM, GolemPatterns.getIcePattern()),
    PACKED_ICE       (ModEntities.PACKED_ICE_GOLEM, GolemPatterns.getPackedIcePattern()),
    BLUE_ICE         (ModEntities.BLUE_ICE_GOLEM, GolemPatterns.getBlueIcePattern()),
    NETHERITE        (ModEntities.NETHERITE_GOLEM, GolemPatterns.getNetheritePattern()),
    COPPER           (ModEntities.COPPER_GOLEM, GolemPatterns.getCopperPattern()),
    AMETHYST         (ModEntities.AMETHYST_GOLEM, GolemPatterns.getAmethystPattern());

    private final Supplier<? extends EntityType<? extends AbstractGolem>> supplierEntity;
    private final BlockPattern blockPattern;

    PatternType(Supplier<? extends EntityType<? extends AbstractGolem>> entity, BlockPattern pattern) {
        this.supplierEntity = entity;
        this.blockPattern = pattern;
    }

    public Supplier<? extends EntityType<? extends AbstractGolem>> getSupplierEntity() {
        return supplierEntity;
    }

    public BlockPattern getBlockPattern() {
        return blockPattern;
    }

    public int getHeight() {
        return blockPattern.getHeight();
    }

    public int getWidth() {
        return blockPattern.getWidth();
    }

    @Nullable
    public BlockPattern.BlockPatternMatch getMatch(Level world, BlockPos pos) {
        return getBlockPattern().find(world, pos);
    }
}
