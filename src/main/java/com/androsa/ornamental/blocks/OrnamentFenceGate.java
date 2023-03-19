package com.androsa.ornamental.blocks;

import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class OrnamentFenceGate extends FenceGateBlock implements OrnamentalBlock {

    protected static final VoxelShape PATH_HITBOX_ZAXIS = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 15.0D, 10.0D);
    protected static final VoxelShape PATH_HITBOX_XAXIS = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_HITBOX_ZAXIS_INWALL = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 12.0D, 10.0D);
    protected static final VoxelShape PATH_HITBOX_XAXIS_INWALL = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 12.0D, 16.0D);
    protected static final VoxelShape PATH_COLLISION_BOX_ZAXIS = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 23.0D, 10.0D);
    protected static final VoxelShape PATH_COLLISION_BOX_XAXIS = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 23.0D, 16.0D);
    protected static final VoxelShape PATH_RENDER_BOX_Z = Shapes.or(Block.box(0.0D, 4.0D, 7.0D, 2.0D, 15.0D, 9.0D), Block.box(14.0D, 4.0D, 7.0D, 16.0D, 15.0D, 9.0D));
    protected static final VoxelShape PATH_RENDER_BOX_X = Shapes.or(Block.box(7.0D, 4.0D, 0.0D, 9.0D, 15.0D, 2.0D), Block.box(7.0D, 4.0D, 14.0D, 9.0D, 15.0D, 16.0D));
    protected static final VoxelShape PATH_RENDER_BOX_INWALL_Z = Shapes.or(Block.box(0.0D, 1.0D, 7.0D, 2.0D, 12.0D, 9.0D), Block.box(14.0D, 1.0D, 7.0D, 16.0D, 12.0D, 9.0D));
    protected static final VoxelShape PATH_RENDER_BOX_INWALL_X = Shapes.or(Block.box(7.0D, 1.0D, 0.0D, 9.0D, 12.0D, 2.0D), Block.box(7.0D, 1.0D, 14.0D, 9.0D, 12.0D, 16.0D));

    private final OrnamentBuilder builder;

    public OrnamentFenceGate(Properties props, OrnamentBuilder builder) {
        super(props, WoodType.OAK); //Dummy value TODO
        this.builder = builder;
    }

    //Necessary evil TODO Delete if patched
    @Override
    @Nonnull
    @Deprecated
    public SoundType getSoundType(BlockState state) {
        return this.builder.blockSetType.soundType();
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    @Nonnull
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (builder.pathShape) {
            if (state.getValue(IN_WALL)) {
                return state.getValue(FACING).getAxis() == Direction.Axis.X ? PATH_HITBOX_XAXIS_INWALL : PATH_HITBOX_ZAXIS_INWALL;
            } else {
                return state.getValue(FACING).getAxis() == Direction.Axis.X ? PATH_HITBOX_XAXIS : PATH_HITBOX_ZAXIS;
            }
        }
        return super.getShape(state, worldIn, pos, context);
    }

    @Override
    @Nonnull
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (builder.pathShape) {
            if (state.getValue(OPEN)) {
                return Shapes.empty();
            } else {
                return state.getValue(FACING).getAxis() == Direction.Axis.Z ? PATH_COLLISION_BOX_ZAXIS : PATH_COLLISION_BOX_XAXIS;
            }
        }
        return super.getCollisionShape(state, worldIn, pos, context);
    }

    @Override
    @Nonnull
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
        if (builder.pathShape) {
            if (state.getValue(IN_WALL)) {
                return state.getValue(FACING).getAxis() == Direction.Axis.X ? PATH_RENDER_BOX_INWALL_X : PATH_RENDER_BOX_INWALL_Z;
            } else {
                return state.getValue(FACING).getAxis() == Direction.Axis.X ? PATH_RENDER_BOX_X : PATH_RENDER_BOX_Z;
            }
        }
        return super.getOcclusionShape(state, worldIn, pos);
    }

    @Override
    public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.causeFallDamage(fallDistance, builder.fallMultiplier, worldIn.damageSources().fall());
    }

    @Override
    @Deprecated
    public boolean isSignalSource(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getSignal(BlockState blockState, BlockGetter blockReader, BlockPos pos, Direction side) {
        return builder.hasPower ? 3 : 0;
    }

    @Override
    @Nonnull
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if (builder.mealGrass && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_fence_gate, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }

            if (builder.hoeDirt && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_fence_gate, SoundEvents.GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if (builder.shovelPath && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_fence_gate, SoundEvents.SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if (builder.hoeGrass && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_fence_gate, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        if (!builder.canOpen) {
            return InteractionResult.PASS;
        } else {
            //TODO: Removed if patched
            if (state.getValue(OPEN)) {
                state = state.setValue(OPEN, Boolean.FALSE);
                worldIn.setBlock(pos, state, 10);
            } else {
                Direction direction = player.getDirection();
                if (state.getValue(FACING) == direction.getOpposite()) {
                    state = state.setValue(FACING, direction);
                }

                state = state.setValue(OPEN, Boolean.TRUE);
                worldIn.setBlock(pos, state, 10);
            }

            boolean flag = state.getValue(OPEN);
            worldIn.playSound(player, pos, flag ? this.builder.fencegateSounds[0] : this.builder.fencegateSounds[1], SoundSource.BLOCKS, 1.0F, worldIn.getRandom().nextFloat() * 0.1F + 0.9F);
            worldIn.gameEvent(player, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        }
    }

    private InteractionResult changeBlock(ItemStack itemstack, Supplier<? extends OrnamentFenceGate> newblock, SoundEvent sound, Level worldIn, BlockPos pos, Player player, InteractionHand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);

        if (!player.getAbilities().instabuild && !itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
        }
        return InteractionResult.SUCCESS;
    }

    private void setBlock(Level world, BlockPos pos, Supplier<? extends OrnamentFenceGate> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockAndUpdate(pos, block.get().defaultBlockState()
                .setValue(FACING, state.getValue(FACING))
                .setValue(OPEN, state.getValue(OPEN))
                .setValue(POWERED, state.getValue(POWERED))
                .setValue(IN_WALL, state.getValue(IN_WALL)));
    }

    //TODO Remove if sounds are patched
    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborpos, boolean piston) {
        if (!level.isClientSide) {
            boolean flag = level.hasNeighborSignal(pos);
            if (state.getValue(POWERED) != flag) {
                level.setBlock(pos, state.setValue(POWERED, flag).setValue(OPEN, flag), 2);
                if (state.getValue(OPEN) != flag) {
                    level.playSound(null, pos, flag ? this.builder.fencegateSounds[0] : this.builder.fencegateSounds[1], SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                    level.gameEvent(null, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
                }
            }
        }
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        super.randomTick(state, worldIn, pos, random);
        if (builder.canMelt) {
            if (worldIn.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(worldIn, pos)) {
                this.turnIntoWater(worldIn, pos);
            }
        }
    }

    protected void turnIntoWater(Level world, BlockPos pos) {
        if (world.dimensionType().ultraWarm() && builder.canVaporise) {
            world.removeBlock(pos, false);
        } else {
            world.setBlockAndUpdate(pos, builder.meltResult.defaultBlockState());
            world.neighborChanged(pos, builder.meltResult, pos);
        }
    }

    @Override
    @Deprecated
    public void onProjectileHit(Level level, BlockState state, BlockHitResult result, Projectile projectile) {
        if (!builder.projectileHitSounds.isEmpty()) {
            if (!level.isClientSide()) {
                BlockPos pos = result.getBlockPos();
                for (SoundEvent sound : builder.projectileHitSounds) {
                    level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
                }
            }
        }
    }

    @Override
    @Nonnull
    @Deprecated
    public PushReaction getPistonPushReaction(BlockState state) {
        return builder.pushReaction;
    }
}
