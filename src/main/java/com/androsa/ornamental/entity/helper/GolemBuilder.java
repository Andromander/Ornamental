package com.androsa.ornamental.entity.helper;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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
            ServerWorld world = ((IServerWorld)event.getWorld()).getLevel();
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
        }
    }

    //For Golems with only a height
    private static void checkPatternSmall(PatternType type, World world, BlockPos pos, int y) {
        if (type.canBuild()) {
            BlockPattern.PatternHelper pattern = type.getMatch(world, pos);
            if (pattern != null) {
                setAirSmall(type, pattern, world);
                addGolem(type, world, pattern, 0, y);
                notifySmall(type, pattern, world);
            }
        }
    }

    //For Golems with a width and height
    private static void checkPatternLarge(PatternType type, World world, BlockPos pos, int x, int y) {
        if (type.canBuild()) {
            BlockPattern.PatternHelper pattern = type.getMatch(world, pos);
            if (pattern != null) {
                setAirLarge(type, pattern, world);
                addGolem(type, world, pattern, x, y);
                notifyLarge(type, pattern, world);
            }
        }
    }

    private static void setAirSmall(PatternType type, BlockPattern.PatternHelper pattern, World world) {
        for(int k = 0; k < type.getHeight(); ++k) {
            CachedBlockInfo info = pattern.getBlock(0, k, 0);
            world.setBlock(info.getPos(), Blocks.AIR.defaultBlockState(), 2);
            world.levelEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, info.getPos(), Block.getId(info.getState()));
        }
    }

    private static void setAirLarge(PatternType type, BlockPattern.PatternHelper pattern, World world) {
        for(int j = 0; j < type.getWidth(); ++j) {
            for(int k = 0; k < type.getHeight(); ++k) {
                CachedBlockInfo info = pattern.getBlock(j, k, 0);
                world.setBlock(info.getPos(), Blocks.AIR.defaultBlockState(), 2);
                world.levelEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, info.getPos(), Block.getId(info.getState()));
            }
        }
    }

    private static void addGolem(PatternType type, World world, BlockPattern.PatternHelper pattern, int x, int y) {
        GolemEntity entity = type.getSupplierEntity().get().create(world);
        BlockPos pos = pattern.getBlock(x, y, 0).getPos();
        entity.moveTo((double)pos.getX() + 0.5D, (double)pos.getY() + 0.05D, (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
        world.addFreshEntity(entity);

        for(ServerPlayerEntity player : world.getEntitiesOfClass(ServerPlayerEntity.class, entity.getBoundingBox().inflate(5.0D))) {
            CriteriaTriggers.SUMMONED_ENTITY.trigger(player, entity);
        }
    }

    private static void notifySmall(PatternType type, BlockPattern.PatternHelper pattern, World world) {
        for(int j1 = 0; j1 < type.getHeight(); ++j1) {
            CachedBlockInfo cachedblockinfo1 = pattern.getBlock(0, j1, 0);
            world.blockUpdated(cachedblockinfo1.getPos(), Blocks.AIR);
        }
    }

    private static void notifyLarge(PatternType type, BlockPattern.PatternHelper pattern, World world) {
        for(int i1 = 0; i1 < type.getWidth(); ++i1) {
            for(int j1 = 0; j1 < type.getHeight(); ++j1) {
                CachedBlockInfo cachedblockinfo1 = pattern.getBlock(i1, j1, 0);
                world.blockUpdated(cachedblockinfo1.getPos(), Blocks.AIR);
            }
        }
    }
}
