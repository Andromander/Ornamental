package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Supplier;

public class NiftyFenceGate extends FenceGateBlock {

    protected static final VoxelShape PATH_HITBOX_ZAXIS = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 15.0D, 10.0D);
    protected static final VoxelShape PATH_HITBOX_XAXIS = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_HITBOX_ZAXIS_INWALL = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 12.0D, 10.0D);
    protected static final VoxelShape PATH_HITBOX_XAXIS_INWALL = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 12.0D, 16.0D);
    protected static final VoxelShape PATH_COLLISION_BOX_ZAXIS = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 23.0D, 10.0D);
    protected static final VoxelShape PATH_COLLISION_BOX_XAXIS = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 23.0D, 16.0D);
    protected static final VoxelShape PATH_RENDER_BOX_Z = VoxelShapes.or(Block.makeCuboidShape(0.0D, 4.0D, 7.0D, 2.0D, 15.0D, 9.0D), Block.makeCuboidShape(14.0D, 4.0D, 7.0D, 16.0D, 15.0D, 9.0D));
    protected static final VoxelShape PATH_RENDER_BOX_X = VoxelShapes.or(Block.makeCuboidShape(7.0D, 4.0D, 0.0D, 9.0D, 15.0D, 2.0D), Block.makeCuboidShape(7.0D, 4.0D, 14.0D, 9.0D, 15.0D, 16.0D));
    protected static final VoxelShape PATH_RENDER_BOX_INWALL_Z = VoxelShapes.or(Block.makeCuboidShape(0.0D, 1.0D, 7.0D, 2.0D, 12.0D, 9.0D), Block.makeCuboidShape(14.0D, 1.0D, 7.0D, 16.0D, 12.0D, 9.0D));
    protected static final VoxelShape PATH_RENDER_BOX_INWALL_X = VoxelShapes.or(Block.makeCuboidShape(7.0D, 1.0D, 0.0D, 9.0D, 12.0D, 2.0D), Block.makeCuboidShape(7.0D, 1.0D, 14.0D, 9.0D, 12.0D, 16.0D));

    private float fallDamage;
    private boolean noRedstone;
    private NiftyBlock blockType;

    public NiftyFenceGate(Properties props, NiftyBlock block) {
        super(Block.Properties.create(block.material, block.color).hardnessAndResistance(block.hardness, block.resistance).sound(block.sound).harvestTool(block.tool).harvestLevel(block.level));
        if (block == NiftyBlock.ICE) props.nonOpaque();

        this.fallDamage = block.multiplier;
        this.noRedstone = block.canOpen;
        this.blockType = block;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (blockType == NiftyBlock.PATH) {
            if (state.get(IN_WALL)) {
                return state.get(HORIZONTAL_FACING).getAxis() == Direction.Axis.X ? PATH_HITBOX_XAXIS_INWALL : PATH_HITBOX_ZAXIS_INWALL;
            } else {
                return state.get(HORIZONTAL_FACING).getAxis() == Direction.Axis.X ? PATH_HITBOX_XAXIS : PATH_HITBOX_ZAXIS;
            }
        }
        return super.getShape(state, worldIn, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (blockType == NiftyBlock.PATH) {
            if (state.get(OPEN)) {
                return VoxelShapes.empty();
            } else {
                return state.get(HORIZONTAL_FACING).getAxis() == Direction.Axis.Z ? PATH_COLLISION_BOX_ZAXIS : PATH_COLLISION_BOX_XAXIS;
            }
        }
        return super.getCollisionShape(state, worldIn, pos, context);
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        if (blockType == NiftyBlock.PATH) {
            if (state.get(IN_WALL)) {
                return state.get(HORIZONTAL_FACING).getAxis() == Direction.Axis.X ? PATH_RENDER_BOX_INWALL_X : PATH_RENDER_BOX_INWALL_Z;
            } else {
                return state.get(HORIZONTAL_FACING).getAxis() == Direction.Axis.X ? PATH_RENDER_BOX_X : PATH_RENDER_BOX_Z;
            }
        }
        return super.getRenderShape(state, worldIn, pos);
    }

    @Override
    public float getSlipperiness(BlockState state, IWorldReader world, BlockPos pos, Entity entity) {
        switch (blockType) {
            case ICE:
            case PACKED_ICE:
                return 0.98F;
            case BLUE_ICE:
                return 0.989F;
            default:
                return super.getSlipperiness(state, world, pos, entity);
        }
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return blockType == NiftyBlock.ICE;
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.handleFallDamage(fallDistance, fallDamage);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(BlockState state) {
        return blockType == NiftyBlock.REDSTONE;
    }

    @Override
    @Deprecated
    public int getWeakPower(BlockState blockState, IBlockReader blockReader, BlockPos pos, Direction side) {
        return blockType == NiftyBlock.REDSTONE ? 3 : 0;
    }

    @Override
    public ActionResultType onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);

        switch (blockType) {
            case DIRT:
                if (!itemstack.isEmpty() && itemstack.getItem() == Items.BONE_MEAL) {
                    this.setBlock(worldIn, pos, ModBlocks.grass_fence_gate);
                    worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                    return ActionResultType.SUCCESS;
                }
                return this.performNormally(state, worldIn, pos, player);

            case GRASS:
                if (!itemstack.isEmpty()) {
                    if (itemstack.getItem() instanceof HoeItem) {
                        this.setBlock(worldIn, pos, ModBlocks.dirt_fence_gate);
                        worldIn.playSound(null, pos, SoundEvents.BLOCK_GRAVEL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                        return ActionResultType.SUCCESS;
                    } else if (itemstack.getItem() instanceof ShovelItem) {
                        this.setBlock(worldIn, pos, ModBlocks.path_fence_gate);
                        worldIn.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                    }
                }
                return this.performNormally(state, worldIn, pos, player);

            case PATH:
                if (!itemstack.isEmpty() && itemstack.getItem() instanceof HoeItem) {
                    this.setBlock(worldIn, pos, ModBlocks.grass_fence_gate);
                    worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                    return ActionResultType.SUCCESS;
                }
                return this.performNormally(state, worldIn, pos, player);

            default:
                return this.performNormally(state, worldIn, pos, player);
        }
    }

    private ActionResultType performNormally(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (!noRedstone) {
            return ActionResultType.PASS;
        } else {
            if (state.get(OPEN)) {
                state = state.with(OPEN, Boolean.FALSE);
                worldIn.setBlockState(pos, state, 10);
            } else {
                Direction enumfacing = player.getHorizontalFacing();
                if (state.get(HORIZONTAL_FACING) == enumfacing.getOpposite()) {
                    state = state.with(HORIZONTAL_FACING, enumfacing);
                }

                state = state.with(OPEN, Boolean.TRUE);
                worldIn.setBlockState(pos, state, 10);
            }

            worldIn.playEvent(player, state.get(OPEN) ? 1008 : 1014, pos, 0);
            return ActionResultType.SUCCESS;
        }
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends Block> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, block.get().getDefaultState()
                .with(HORIZONTAL_FACING, state.get(HORIZONTAL_FACING))
                .with(OPEN, state.get(OPEN))
                .with(POWERED, state.get(POWERED))
                .with(IN_WALL, state.get(IN_WALL)));
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (blockType.booleanValue.get().get()) {
            super.onBlockHarvested(world, pos, state, player);
        }
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        if (blockType == NiftyBlock.ICE) {
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                if (worldIn.dimension.doesWaterVaporize()) {
                    worldIn.removeBlock(pos, false);
                    return;
                }

                Material material = worldIn.getBlockState(pos.down()).getMaterial();
                if (material.blocksMovement() || material.isLiquid()) {
                    worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());
                }
            }
        }
    }

    @Override
    @Deprecated
    public void scheduledTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (blockType == NiftyBlock.ICE) {
            if (worldIn.getLightLevel(LightType.BLOCK, pos) > 11 - state.getOpacity(worldIn, pos)) {
                this.turnIntoWater(worldIn, pos);
            }
        }
    }

    protected void turnIntoWater(World world, BlockPos pos) {
        if (world.dimension.doesWaterVaporize()) {
            world.removeBlock(pos, false);
        } else {
            world.setBlockState(pos, Blocks.WATER.getDefaultState());
            world.neighborChanged(pos, Blocks.WATER, pos);
        }
    }

    @Override
    @Deprecated
    public PushReaction getPushReaction(BlockState state) {
        return blockType == NiftyBlock.ICE ? PushReaction.NORMAL : super.getPushReaction(state);
    }
}
