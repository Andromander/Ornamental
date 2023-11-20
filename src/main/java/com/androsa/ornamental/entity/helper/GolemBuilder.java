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
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.BlockEvent;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GolemBuilder {

    //Mimic behaviour seen in CarvedPumpkinBlock
    @SubscribeEvent
    public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
        if (event.getPlacedBlock().getBlock() instanceof CarvedPumpkinBlock) {
            ServerLevel world = ((ServerLevelAccessor)event.getLevel()).getLevel();
            BlockPos pos = event.getPos();

            checkPattern(PatternType.GOLD, world, pos, 1, 2);
            checkPattern(PatternType.DIAMOND, world, pos, 1, 1);
            checkPattern(PatternType.EMERALD, world, pos, 1, 2);
            checkPattern(PatternType.LAPIS, world, pos, 1, 2);
            checkPattern(PatternType.OBSIDIAN, world, pos, 1, 3);
            checkPattern(PatternType.COAL, world, pos, 2);
            checkPattern(PatternType.REDSTONE, world, pos, 1, 2);
            checkPattern(PatternType.CLAY, world, pos, 2);
            checkPattern(PatternType.DIRT, world, pos, 1);
            checkPattern(PatternType.HAY, world, pos, 2, 3);
            checkPattern(PatternType.BRICK, world, pos, 2);
            checkPattern(PatternType.QUARTZ, world, pos, 1, 2);
            checkPattern(PatternType.BONE, world, pos, 1, 3);
            checkPattern(PatternType.NETHER_BRICK, world, pos, 2);
            checkPattern(PatternType.RED_NETHER_BRICK, world, pos, 2);
            checkPattern(PatternType.ICE, world, pos, 2);
            checkPattern(PatternType.PACKED_ICE, world, pos, 2);
            checkPattern(PatternType.BLUE_ICE, world, pos, 2);
            checkPattern(PatternType.NETHERITE, world, pos, 1, 2);
            checkPattern(PatternType.COPPER, world, pos, 1, 3);
            checkPattern(PatternType.AMETHYST, world, pos, 1, 2);
            checkPattern(PatternType.MAGMA, world, pos, 2);
            checkPattern(PatternType.CALCITE, world, pos, 2, 3);
        }
    }

    private static void checkPattern(PatternType type, Level world, BlockPos pos, int y) {
        checkPattern(type, world, pos, 0, y);
    }

    private static void checkPattern(PatternType type, Level world, BlockPos pos, int x, int y) {
        BlockPattern.BlockPatternMatch pattern = type.getMatch(world, pos);
        if (pattern != null) {
            setAir(type, pattern, world);
            addGolem(type, world, pattern, x, y);
            notify(type, pattern, world);
        }
    }

    private static void setAir(PatternType type, BlockPattern.BlockPatternMatch pattern, Level world) {
        for(int j = 0; j < type.getWidth(); ++j) {
            for(int k = 0; k < type.getHeight(); ++k) {
                BlockInWorld info = pattern.getBlock(j, k, 0);
                world.setBlock(info.getPos(), Blocks.AIR.defaultBlockState(), 2);
                world.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, info.getPos(), Block.getId(info.getState()));
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

    private static void notify(PatternType type, BlockPattern.BlockPatternMatch pattern, Level world) {
        for(int i1 = 0; i1 < type.getWidth(); ++i1) {
            for(int j1 = 0; j1 < type.getHeight(); ++j1) {
                BlockInWorld cachedblockinfo1 = pattern.getBlock(i1, j1, 0);
                world.blockUpdated(cachedblockinfo1.getPos(), Blocks.AIR);
            }
        }
    }
}
