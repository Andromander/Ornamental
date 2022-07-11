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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class OrnamentSlab extends SlabBlock implements OrnamentalBlock {

    protected static final VoxelShape PATH_BOTTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
    protected static final VoxelShape PATH_TOP_SHAPE = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_FULL_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    private final OrnamentBuilder builder;

    public OrnamentSlab(Properties props, OrnamentBuilder builder) {
        super(props);
        this.builder = builder;
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (builder.pathShape) {
            SlabType slabtype = state.getValue(TYPE);
            return switch (slabtype) {
                case DOUBLE -> PATH_FULL_SHAPE;
                case TOP -> PATH_TOP_SHAPE;
                default -> PATH_BOTTOM_SHAPE;
            };
        }
        return super.getShape(state, worldIn, pos, context);
    }

    @Override
    public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.causeFallDamage(fallDistance, builder.fallMultiplier, DamageSource.FALL);
    }

    @Override
    @Deprecated
    public boolean isSignalSource(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getSignal(BlockState state, BlockGetter reader, BlockPos pos, Direction side) {
        if (builder.hasPower)
            return state.getValue(TYPE) == SlabType.DOUBLE ? 15 : 7;
        return super.getSignal(state, reader, pos, side);
    }

    @Override
    @Deprecated
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if (builder.mealGrass && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_slab, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }

            if (builder.hoeDirt && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_slab, SoundEvents.GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if (builder.shovelPath && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_slab, SoundEvents.SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if (builder.hoeGrass && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_slab, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return super.use(state, worldIn, pos, player, hand, result);
    }

    private InteractionResult changeBlock(ItemStack itemstack, Supplier<? extends OrnamentSlab> newblock, SoundEvent sound, Level worldIn, BlockPos pos, Player player, InteractionHand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);

        if (!player.getAbilities().instabuild && !itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
        }
        return InteractionResult.SUCCESS;
    }

    private void setBlock(Level world, BlockPos pos, Supplier<? extends OrnamentSlab> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockAndUpdate(pos, block.get().defaultBlockState()
                .setValue(TYPE, state.getValue(TYPE))
                .setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
    }

    @Override
    @Deprecated
    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState otherState, Direction direction) {
        if (builder.breakableCull) {
            if (otherState.getBlock() instanceof OrnamentSlab otherSlab && state.getBlock() instanceof OrnamentSlab slab) {
                if (otherSlab.getBuilder() == slab.getBuilder() && otherState.getValue(TYPE) == SlabType.DOUBLE && state.getValue(TYPE) == SlabType.DOUBLE) {
                    return true;
                }
            }
        }
        return super.skipRendering(state, otherState, direction);
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
    @Deprecated
    public PushReaction getPistonPushReaction(BlockState state) {
        return builder.pushReaction;
    }
}
