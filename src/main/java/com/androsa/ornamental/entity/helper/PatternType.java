package com.androsa.ornamental.entity.helper;

import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.builder.OrnamentBuilders;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum PatternType {
    GOLD             (ModEntities.GOLD_GOLEM, GolemPatterns.getGoldPattern(), OrnamentBuilders.GOLD),
    DIAMOND          (ModEntities.DIAMOND_GOLEM, GolemPatterns.getDiamondPattern(), OrnamentBuilders.DIAMOND),
    EMERALD          (ModEntities.EMERALD_GOLEM, GolemPatterns.getEmeraldPattern(), OrnamentBuilders.EMERALD),
    LAPIS            (ModEntities.LAPIS_GOLEM, GolemPatterns.getLapisPattern(), OrnamentBuilders.LAPIS),
    OBSIDIAN         (ModEntities.OBSIDIAN_GOLEM, GolemPatterns.getObsidianPattern(), OrnamentBuilders.OBSIDIAN),
    COAL             (ModEntities.COAL_GOLEM, GolemPatterns.getCoalPattern(), OrnamentBuilders.COAL),
    REDSTONE         (ModEntities.REDSTONE_GOLEM, GolemPatterns.getRedstonePattern(), OrnamentBuilders.REDSTONE),
    CLAY             (ModEntities.CLAY_GOLEM, GolemPatterns.getClayPattern(), OrnamentBuilders.CLAY),
    DIRT             (ModEntities.DIRT_GOLEM, GolemPatterns.getDirtPattern(), OrnamentBuilders.DIRT),
    HAY              (ModEntities.HAY_GOLEM, GolemPatterns.getHayPattern(), OrnamentBuilders.HAY),
    BRICK            (ModEntities.BRICK_GOLEM, GolemPatterns.getBrickPattern(), OrnamentBuilders.BRICK),
    QUARTZ           (ModEntities.QUARTZ_GOLEM, GolemPatterns.getQuartzPattern(), OrnamentBuilders.QUARTZ),
    BONE             (ModEntities.BONE_GOLEM, GolemPatterns.getBonePattern(), OrnamentBuilders.BONE),
    NETHER_BRICK     (ModEntities.NETHER_BRICK_GOLEM, GolemPatterns.getNetherBrickPattern(), OrnamentBuilders.NETHER_BRICK),
    RED_NETHER_BRICK (ModEntities.RED_NETHER_BRICK_GOLEM, GolemPatterns.getRedNetherBrickPattern(), OrnamentBuilders.RED_NETHER_BRICK),
    ICE              (ModEntities.ICE_GOLEM, GolemPatterns.getIcePattern(), OrnamentBuilders.ICE),
    PACKED_ICE       (ModEntities.PACKED_ICE_GOLEM, GolemPatterns.getPackedIcePattern(), OrnamentBuilders.PACKED_ICE),
    BLUE_ICE         (ModEntities.BLUE_ICE_GOLEM, GolemPatterns.getBlueIcePattern(), OrnamentBuilders.BLUE_ICE),
    NETHERITE        (ModEntities.NETHERITE_GOLEM, GolemPatterns.getNetheritePattern(), OrnamentBuilders.NETHERITE);

    private final Supplier<? extends EntityType<? extends GolemEntity>> supplierEntity;
    private final BlockPattern blockPattern;
    private final OrnamentBuilder ornamentBuilder;

    PatternType(Supplier<? extends EntityType<? extends GolemEntity>> entity, BlockPattern pattern, OrnamentBuilder builder) {
        this.supplierEntity = entity;
        this.blockPattern = pattern;
        this.ornamentBuilder = builder;
    }

    public Supplier<? extends EntityType<? extends GolemEntity>> getSupplierEntity() {
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
    public BlockPattern.PatternHelper getMatch(World world, BlockPos pos) {
        return getBlockPattern().find(world, pos);
    }

    public boolean canBuild() {
        return ornamentBuilder.booleanValue.get().get();
    }
}
