package com.androsa.nifty.blocks.ice;

import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.blocks.NiftyDoor;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Random;

public class NiftyIceDoor extends NiftyDoor {

    private final float sliperiness;
    private final boolean canMelt;

    public NiftyIceDoor(NiftyBlock block, float slip, boolean melt) {
        super(block);

        sliperiness = slip;
        canMelt = melt;
    }

    @Override
    public float getSlipperiness(BlockState state, IWorldReader world, BlockPos pos, Entity entity) {
        return sliperiness;
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return canMelt;
    }

    /*@Override
    @Deprecated
    @OnlyIn(Dist.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState otherState, Direction direction) {
        return canMelt && (otherState.getBlock() == this || super.isSideInvisible(state, otherState, direction));
    }*/

    @Override
    public BlockRenderLayer getRenderLayer() {
        return canMelt ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        if (canMelt) {
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
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (canMelt) {
            if (worldIn.getLightFor(LightType.BLOCK, pos) > 11 - state.getOpacity(worldIn, pos)) {
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

    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }
}