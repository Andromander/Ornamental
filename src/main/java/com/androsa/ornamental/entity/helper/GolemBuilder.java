package com.androsa.ornamental.entity.helper;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GolemBuilder {

    //Mimic behaviour seen in CarvedPumpkinBlock
    @SubscribeEvent
    public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
        if (event.getPlacedBlock().getBlock() instanceof CarvedPumpkinBlock) {
            ServerLevel world = ((ServerLevelAccessor)event.getWorld()).getLevel();
            BlockPos pos = event.getPos();

            checkPatternLarge(PatternType.GOLD, world, pos, 1, 2);
            checkPatternLarge(PatternType.DIAMOND, world, pos, 1, 1);
            checkPatternLarge(PatternType.EMERALD, world, pos, 1, 2);
            checkPatternLarge(PatternType.LAPIS, world, pos, 1, 2);
            checkPatternLarge(PatternType.OBSIDIAN, world, pos, 1, 3);
            checkPatternSmall(PatternType.COAL, world, pos, 2);
            checkPatternLarge(PatternType.REDSTONE, world, pos, 1, 2);
            checkPatternSmall(PatternType.CLAY, world, pos, 2);
            checkPatternSmall(PatternType.DIRT, world, pos, 1);
            checkPatternLarge(PatternType.HAY, world, pos, 2, 3);
            checkPatternSmall(PatternType.BRICK, world, pos, 2);
            checkPatternLarge(PatternType.QUARTZ, world, pos, 1, 2);
            checkPatternLarge(PatternType.BONE, world, pos, 1, 3);
            checkPatternSmall(PatternType.NETHER_BRICK, world, pos, 2);
            checkPatternSmall(PatternType.RED_NETHER_BRICK, world, pos, 2);
            checkPatternSmall(PatternType.ICE, world, pos, 2);
            checkPatternSmall(PatternType.PACKED_ICE, world, pos, 2);
            checkPatternSmall(PatternType.BLUE_ICE, world, pos, 2);
            checkPatternLarge(PatternType.NETHERITE, world, pos, 1, 2);
            checkPatternLarge(PatternType.COPPER, world, pos, 1, 3);
            checkPatternLarge(PatternType.AMETHYST, world, pos, 1, 2);
        }
    }

    //For Golems with only a height
    private static void checkPatternSmall(PatternType type, Level world, BlockPos pos, int y) {
        if (type.canBuild()) {
            BlockPattern.BlockPatternMatch pattern = type.getMatch(world, pos);
            if (pattern != null) {
                setAirSmall(type, pattern, world);
                addGolem(type, world, pattern, 0, y);
                notifySmall(type, pattern, world);
            }
        }
    }

    //For Golems with a width and height
    private static void checkPatternLarge(PatternType type, Level world, BlockPos pos, int x, int y) {
        if (type.canBuild()) {
            BlockPattern.BlockPatternMatch pattern = type.getMatch(world, pos);
            if (pattern != null) {
                setAirLarge(type, pattern, world);
                addGolem(type, world, pattern, x, y);
                notifyLarge(type, pattern, world);
            }
        }
    }

    private static void setAirSmall(PatternType type, BlockPattern.BlockPatternMatch pattern, Level world) {
        for(int k = 0; k < type.getHeight(); ++k) {
            BlockInWorld info = pattern.getBlock(0, k, 0);
            world.setBlock(info.getPos(), Blocks.AIR.defaultBlockState(), 2);
            world.levelEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, info.getPos(), Block.getId(info.getState()));
        }
    }

    private static void setAirLarge(PatternType type, BlockPattern.BlockPatternMatch pattern, Level world) {
        for(int j = 0; j < type.getWidth(); ++j) {
            for(int k = 0; k < type.getHeight(); ++k) {
                BlockInWorld info = pattern.getBlock(j, k, 0);
                world.setBlock(info.getPos(), Blocks.AIR.defaultBlockState(), 2);
                world.levelEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, info.getPos(), Block.getId(info.getState()));
            }
        }
    }

    private static void addGolem(PatternType type, Level world, BlockPattern.BlockPatternMatch pattern, int x, int y) {
        AbstractGolem entity = type.getSupplierEntity().get().create(world);
        BlockPos pos = pattern.getBlock(x, y, 0).getPos();
        entity.moveTo((double)pos.getX() + 0.5D, (double)pos.getY() + 0.05D, (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
        world.addFreshEntity(entity);

        for(ServerPlayer player : world.getEntitiesOfClass(ServerPlayer.class, entity.getBoundingBox().inflate(5.0D))) {
            CriteriaTriggers.SUMMONED_ENTITY.trigger(player, entity);
        }
    }

    private static void notifySmall(PatternType type, BlockPattern.BlockPatternMatch pattern, Level world) {
        for(int j1 = 0; j1 < type.getHeight(); ++j1) {
            BlockInWorld cachedblockinfo1 = pattern.getBlock(0, j1, 0);
            world.blockUpdated(cachedblockinfo1.getPos(), Blocks.AIR);
        }
    }

    private static void notifyLarge(PatternType type, BlockPattern.BlockPatternMatch pattern, Level world) {
        for(int i1 = 0; i1 < type.getWidth(); ++i1) {
            for(int j1 = 0; j1 < type.getHeight(); ++j1) {
                BlockInWorld cachedblockinfo1 = pattern.getBlock(i1, j1, 0);
                world.blockUpdated(cachedblockinfo1.getPos(), Blocks.AIR);
            }
        }
    }
}
