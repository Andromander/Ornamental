package com.androsa.ornamental.blocks;

import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.state.properties.Half;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class OrnamentTrapDoor extends TrapDoorBlock implements IOrnamentalBlock {

    protected static final VoxelShape PATH_EAST_OPEN_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape PATH_WEST_OPEN_AABB = Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape PATH_SOUTH_OPEN_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape PATH_NORTH_OPEN_AABB = Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape PATH_BOTTOM_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape PATH_TOP_AABB = Block.makeCuboidShape(0.0D, 13.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    private final OrnamentBuilder builder;

    public OrnamentTrapDoor(Properties props, OrnamentBuilder builder) {
        super(props);
        this.builder = builder;
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (builder.isPath || builder.pathShape) {
            if (!state.get(OPEN)) {
                return state.get(HALF) == Half.TOP ? PATH_TOP_AABB : PATH_BOTTOM_AABB;
            } else {
                switch(state.get(HORIZONTAL_FACING)) {
                    case NORTH:
                    default:
                        return PATH_NORTH_OPEN_AABB;
                    case SOUTH:
                        return PATH_SOUTH_OPEN_AABB;
                    case WEST:
                        return PATH_WEST_OPEN_AABB;
                    case EAST:
                        return PATH_EAST_OPEN_AABB;
                }
            }
        } else {
            return super.getShape(state, worldIn, pos, context);
        }
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.onLivingFall(fallDistance, builder.fallMultiplier);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getWeakPower(BlockState blockState, IBlockReader blockReader, BlockPos pos, Direction side) {
        return builder.hasPower ? 5 : 0;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if ((builder.isDirt || builder.mealGrass) && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_trapdoor, SoundEvents.BLOCK_GRASS_BREAK, worldIn, pos, player, hand);
            }

            if ((builder.isGrass || builder.hoeDirt) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_trapdoor, SoundEvents.BLOCK_GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if ((builder.isGrass || builder.shovelPath) && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_trapdoor, SoundEvents.ITEM_SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if ((builder.isPath || builder.hoeGrass) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_trapdoor, SoundEvents.BLOCK_GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return this.performNormally(state, worldIn, pos, player);
    }

    private ActionResultType performNormally(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (!builder.canOpen) {
            return ActionResultType.PASS;
        } else {
            state = state.func_235896_a_(OPEN); //cycle
            world.setBlockState(pos, state, 2);
            if (state.get(WATERLOGGED)) {
                world.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }

            this.playSound(player, world, pos, state.get(OPEN));
            return ActionResultType.SUCCESS;
        }
    }

    private ActionResultType changeBlock(ItemStack itemstack, Supplier<? extends OrnamentTrapDoor> newblock, SoundEvent sound, World worldIn, BlockPos pos, PlayerEntity player, Hand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!player.abilities.isCreativeMode && !itemstack.isDamageable()) {
            itemstack.shrink(1);
        } else {
            itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
        }
        return ActionResultType.SUCCESS;
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends OrnamentTrapDoor> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, block.get().getDefaultState()
                .with(POWERED, state.get(POWERED))
                .with(OPEN, state.get(OPEN))
                .with(HALF, state.get(HALF))
                .with(WATERLOGGED, state.get(WATERLOGGED)));
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        BlockState state = worldIn.getBlockState(pos);

        if (!state.get(OPEN)) {
            if (material == Material.CLAY || material == Material.LEAVES || material == Material.WOOL || material == Material.EARTH || material == Material.ORGANIC) {
                state = state.func_235896_a_(OPEN); //cycle
                worldIn.setBlockState(pos, state, 2);
                this.playSound(null, worldIn, pos, state.get(OPEN));
            }
        }
    }

    @Override
    protected void playSound(@Nullable PlayerEntity player, World worldIn, BlockPos pos, boolean state) {
        if (state) {
            int i = this.material == Material.IRON ? 1037 : 1007;
            worldIn.playEvent(player, i, pos, 0);
        } else {
            int j = this.material == Material.IRON ? 1036 : 1013;
            worldIn.playEvent(player, j, pos, 0);
        }
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (builder.hasConfig) {
            ForgeConfigSpec.BooleanValue val = builder.booleanValue.get();

            if (val == null) {
                throw new NullPointerException(builder.name + " expected a config value but found null.");
            } else {
                if (val.get()) {
                    super.onBlockHarvested(world, pos, state, player);
                }
            }
        }
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);
        if (builder.isIce || builder.canMelt) {
            if (worldIn.getLightFor(LightType.BLOCK, pos) > 11 - state.getOpacity(worldIn, pos)) {
                this.turnIntoWater(worldIn, pos);
            }
        }
    }

    protected void turnIntoWater(World world, BlockPos pos) {
        if (world.func_230315_m_().func_236040_e_() && builder.canVaporise) { //doesWaterVaporize
            world.removeBlock(pos, false);
        } else {
            world.setBlockState(pos, builder.meltResult.getDefaultState());
            world.neighborChanged(pos, builder.meltResult, pos);
        }
    }

    @Override
    @Deprecated
    public PushReaction getPushReaction(BlockState state) {
        return builder.isIce ? PushReaction.NORMAL : builder.pushReaction;
    }
}
