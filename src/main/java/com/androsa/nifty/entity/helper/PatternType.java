package com.androsa.nifty.entity.helper;

import com.androsa.nifty.ModEntities;
import com.androsa.nifty.NiftyBuilder;
import com.androsa.nifty.NiftyBuilders;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum PatternType {
    GOLD             (ModEntities.GOLD_GOLEM, GolemPatterns.getGoldPattern(), NiftyBuilders.GOLD),
    DIAMOND          (ModEntities.DIAMOND_GOLEM, GolemPatterns.getDiamondPattern(), NiftyBuilders.DIAMOND),
    EMERALD          (ModEntities.EMERALD_GOLEM, GolemPatterns.getEmeraldPattern(), NiftyBuilders.EMERALD),
    LAPIS            (ModEntities.LAPIS_GOLEM, GolemPatterns.getLapisPattern(), NiftyBuilders.LAPIS),
    OBSIDIAN         (ModEntities.OBSIDIAN_GOLEM, GolemPatterns.getObsidianPattern(), NiftyBuilders.OBSIDIAN),
    COAL             (ModEntities.COAL_GOLEM, GolemPatterns.getCoalPattern(), NiftyBuilders.COAL),
    REDSTONE         (ModEntities.REDSTONE_GOLEM, GolemPatterns.getRedstonePattern(), NiftyBuilders.REDSTONE),
    CLAY             (ModEntities.CLAY_GOLEM, GolemPatterns.getClayPattern(), NiftyBuilders.CLAY),
    DIRT             (ModEntities.DIRT_GOLEM, GolemPatterns.getDirtPattern(), NiftyBuilders.DIRT),
    HAY              (ModEntities.HAY_GOLEM, GolemPatterns.getHayPattern(), NiftyBuilders.HAY),
    BRICK            (ModEntities.BRICK_GOLEM, GolemPatterns.getBrickPattern(), NiftyBuilders.BRICK),
    QUARTZ           (ModEntities.QUARTZ_GOLEM, GolemPatterns.getQuartzPattern(), NiftyBuilders.QUARTZ),
    BONE             (ModEntities.BONE_GOLEM, GolemPatterns.getBonePattern(), NiftyBuilders.BONE),
    NETHER_BRICK     (ModEntities.NETHER_BRICK_GOLEM, GolemPatterns.getNetherBrickPattern(), NiftyBuilders.NETHER_BRICK),
    RED_NETHER_BRICK (ModEntities.RED_NETHER_BRICK_GOLEM, GolemPatterns.getRedNetherBrickPattern(), NiftyBuilders.RED_NETHER_BRICK),
    ICE              (ModEntities.ICE_GOLEM, GolemPatterns.getIcePattern(), NiftyBuilders.ICE),
    PACKED_ICE       (ModEntities.PACKED_ICE_GOLEM, GolemPatterns.getPackedIcePattern(), NiftyBuilders.PACKED_ICE),
    BLUE_ICE         (ModEntities.BLUE_ICE_GOLEM, GolemPatterns.getBlueIcePattern(), NiftyBuilders.BLUE_ICE);

    private final Supplier<? extends EntityType<? extends GolemEntity>> supplierEntity;
    private final BlockPattern blockPattern;
    private final NiftyBuilder niftyBuilder;

    PatternType(Supplier<? extends EntityType<? extends GolemEntity>> entity, BlockPattern pattern, NiftyBuilder builder) {
        this.supplierEntity = entity;
        this.blockPattern = pattern;
        this.niftyBuilder = builder;
    }

    public Supplier<? extends EntityType<? extends GolemEntity>> getSupplierEntity() {
        return supplierEntity;
    }

    public BlockPattern getBlockPattern() {
        return blockPattern;
    }

    public int getThumb() {
        return blockPattern.getThumbLength();
    }

    public int getPalm() {
        return blockPattern.getPalmLength();
    }

    @Nullable
    public BlockPattern.PatternHelper getMatch(World world, BlockPos pos) {
        return getBlockPattern().match(world, pos);
    }

    public boolean canBuild() {
        return niftyBuilder.booleanValue.get().get();
    }
}
