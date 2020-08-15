package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBuilder;
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
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Random;
import java.util.function.Supplier;

public class NiftyDoor extends DoorBlock implements INiftyBlock {

    protected static final VoxelShape PATH_SOUTH_AABB_TOP = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 3.0D);
    protected static final VoxelShape PATH_NORTH_AABB_TOP = Block.makeCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_WEST_AABB_TOP = Block.makeCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_EAST_AABB_TOP = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 15.0D, 16.0D);

    private final NiftyBuilder builder;
    protected boolean isReplacing = false;

    public NiftyDoor(Properties props, NiftyBuilder builder) {
        super(props);
        this.builder = builder;
    }

    @Override
    public NiftyBuilder getBuilder() {
        return builder;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (builder.isPath) {
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
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.onLivingFall(fallDistance, builder.fallMultiplier);
    }

    @Override
    @Deprecated
    public boolean hasComparatorInputOverride(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getComparatorInputOverride(BlockState state, World worldIn, BlockPos pos) {
        return builder.hasPower && state.get(POWERED) ? 10 : 0;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        worldIn.updateComparatorOutputLevel(pos, this);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (builder.isDirt) {
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
        }

        if (builder.isGrass) {
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
        }

        if (builder.isPath) {
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
        }

        return this.performNormally(state, worldIn, pos, player);
    }

    public ActionResultType performNormally(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (!builder.canOpen) {
            return ActionResultType.PASS;
        } else {
            state = state.func_235896_a_(OPEN); //cycle
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
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        if (builder.isIce) {
            isReplacing = true;
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                if (worldIn.func_230315_m_().func_236040_e_()) { //doesWaterVaporize
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
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);
        if (builder.isIce) {
            if (worldIn.getLightFor(LightType.BLOCK, pos) > 11 - state.getOpacity(worldIn, pos)) {
                this.turnIntoWater(worldIn, pos);
            }
        }
    }

    protected void turnIntoWater(World world, BlockPos pos) {
        if (world.func_230315_m_().func_236040_e_()) { //doesWaterVaporize
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
