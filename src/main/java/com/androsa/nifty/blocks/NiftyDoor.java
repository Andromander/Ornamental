package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Supplier;

public class NiftyDoor extends DoorBlock {

    protected static final VoxelShape PATH_SOUTH_AABB_TOP = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 3.0D);
    protected static final VoxelShape PATH_NORTH_AABB_TOP = Block.makeCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_WEST_AABB_TOP = Block.makeCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_EAST_AABB_TOP = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 15.0D, 16.0D);

    private float fallDamage;
    private boolean noRedstone;
    private NiftyBlock blockType;
    protected boolean isReplacing = false;

    public NiftyDoor(Properties props, NiftyBlock block) {
        super(props.hardnessAndResistance(block.hardness, block.resistance).sound(block.sound).harvestTool(block.tool).harvestLevel(block.level).nonOpaque());

        this.fallDamage = block.multiplier;
        this.noRedstone = block.canOpen;
        this.blockType = block;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (blockType == NiftyBlock.PATH) {
            Direction direction = state.get(FACING);
            boolean flag = !state.get(OPEN);
            boolean flag1 = state.get(HINGE) == DoorHingeSide.RIGHT;

            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                switch(direction) {
                    case EAST:
                    default:
                        return flag ? PATH_EAST_AABB_TOP : (flag1 ? PATH_NORTH_AABB_TOP : PATH_SOUTH_AABB_TOP);
                    case SOUTH:
                        return flag ? PATH_SOUTH_AABB_TOP : (flag1 ? PATH_EAST_AABB_TOP : PATH_WEST_AABB_TOP);
                    case WEST:
                        return flag ? PATH_WEST_AABB_TOP : (flag1 ? PATH_SOUTH_AABB_TOP : PATH_NORTH_AABB_TOP);
                    case NORTH:
                        return flag ? PATH_NORTH_AABB_TOP : (flag1 ? PATH_WEST_AABB_TOP : PATH_EAST_AABB_TOP);
                }
            } else {
                switch(direction) {
                    case EAST:
                    default:
                        return flag ? EAST_AABB : (flag1 ? NORTH_AABB : SOUTH_AABB);
                    case SOUTH:
                        return flag ? SOUTH_AABB : (flag1 ? EAST_AABB : WEST_AABB);
                    case WEST:
                        return flag ? WEST_AABB : (flag1 ? SOUTH_AABB : NORTH_AABB);
                    case NORTH:
                        return flag ? NORTH_AABB : (flag1 ? WEST_AABB : EAST_AABB);
                }
            }
        }
        return super.getShape(state, worldIn, pos, context);
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
    public boolean hasComparatorInputOverride(BlockState state) {
        return blockType == NiftyBlock.REDSTONE;
    }

    @Override
    @Deprecated
    public int getComparatorInputOverride(BlockState state, World worldIn, BlockPos pos) {
        return state.get(POWERED) ? 10 : 0;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        worldIn.updateComparatorOutputLevel(pos, this);
    }

    @Override
    public ActionResultType onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);

        switch (blockType) {
            case DIRT:
                if (!itemstack.isEmpty() && itemstack.getItem() == Items.BONE_MEAL) {
                    BlockState blockstate =  worldIn.getBlockState(pos);

                    if (blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
                        setBlocks(ModBlocks.grass_door, worldIn, pos, pos.up(), DoubleBlockHalf.UPPER);
                    } else {
                        setBlocks(ModBlocks.grass_door, worldIn, pos, pos.down(), DoubleBlockHalf.LOWER);
                    }
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
                        BlockState blockstate = worldIn.getBlockState(pos);

                        if (blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
                            setBlocks(ModBlocks.dirt_door, worldIn, pos, pos.up(), DoubleBlockHalf.UPPER);
                        } else {
                            setBlocks(ModBlocks.dirt_door, worldIn, pos, pos.down(), DoubleBlockHalf.LOWER);
                        }

                        worldIn.playSound(null, pos, SoundEvents.BLOCK_GRAVEL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                        return ActionResultType.SUCCESS;
                    } else if (itemstack.getItem() instanceof ShovelItem) {
                        BlockState blockstate = worldIn.getBlockState(pos);

                        if (blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
                            setBlocks(ModBlocks.path_door, worldIn, pos, pos.up(), DoubleBlockHalf.UPPER);
                        } else {
                            setBlocks(ModBlocks.path_door, worldIn, pos, pos.down(), DoubleBlockHalf.LOWER);
                        }

                        worldIn.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                        return ActionResultType.SUCCESS;
                    }
                }
                return this.performNormally(state, worldIn, pos, player);

            case PATH:
                if (!itemstack.isEmpty() && itemstack.getItem() instanceof HoeItem) {
                    BlockState blockstate = worldIn.getBlockState(pos);

                    if (blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
                        setBlocks(ModBlocks.grass_door, worldIn, pos, pos.up(), DoubleBlockHalf.UPPER);
                    } else {
                        setBlocks(ModBlocks.grass_door, worldIn, pos, pos.down(), DoubleBlockHalf.LOWER);
                    }

                    worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                    return ActionResultType.SUCCESS;
                }
                return this.performNormally(state, worldIn, pos, player);

            default:
                return this.performNormally(state, worldIn, pos, player);
        }
    }

    public ActionResultType performNormally(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (!noRedstone) {
            return ActionResultType.PASS;
        } else {
            state = state.cycle(OPEN);
            worldIn.setBlockState(pos, state, 10);
            worldIn.playEvent(player, state.get(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
            return ActionResultType.SUCCESS;
        }
    }

    private void setBlocks(Supplier<? extends Block> block, World world, BlockPos selectPos, BlockPos nearPos, DoubleBlockHalf half) {
        BlockState blockstate = world.getBlockState(selectPos);

        isReplacing = true;
        world.removeBlock(selectPos, false);
        world.setBlockState(nearPos, block.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, half), 3);
        world.setBlockState(selectPos, block.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, blockstate.get(HALF)), 3);
        isReplacing = false;
    }

    private int getCloseSound() {
        return this.material == Material.IRON || this.material == Material.ROCK ? 1011 : 1012;
    }

    private int getOpenSound() {
        return this.material == Material.IRON || this.material == Material.ROCK ? 1005 : 1006;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (blockType.booleanValue.get().get()) {
            if (!isReplacing) {
                super.onBlockHarvested(worldIn, pos, state, player);
            }
        }
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        if (blockType == NiftyBlock.ICE) {
            isReplacing = true;
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
            isReplacing = false;
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
            if (world.getBlockState(pos).get(HALF) == DoubleBlockHalf.LOWER) {
                world.setBlockState(pos, Blocks.WATER.getDefaultState());
                world.neighborChanged(pos, Blocks.WATER, pos);
            } else {
                world.setBlockState(pos.offset(Direction.DOWN), Blocks.WATER.getDefaultState());
                world.neighborChanged(pos.offset(Direction.DOWN), Blocks.WATER, pos.offset(Direction.DOWN));
            }
        }
    }
}
