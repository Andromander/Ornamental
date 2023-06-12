package com.androsa.ornamental.builder;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;
import java.util.function.Supplier;

/**
 * This record holds all the information necessary to test and convert a block.
 *
 * @param predicate the predicate to test against. For more information, see {@link BlockConvertPredicate#test(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult)}.
 * @param list a Supplier containing a list of target blocks.
 * @param sound the SoundEvent that plays on conversion.
 */
public record BlockConverter(BlockConvertPredicate predicate, Supplier<List<Supplier<? extends Block>>> list, SoundEvent sound) {

    public interface BlockConvertPredicate {
        /**
         * The test predicate for whether a block should convert into another block.
         * Example: if the item used on this material block is Bone Meal, convert into target block.
         * @param state the BlockState of the target block.
         * @param level the Level.
         * @param pos the BlockPos of the target block.
         * @param player the Player targeting the block.
         * @param hand the Hand used to interact with the block.
         * @param result the BlockHitResult.
         */
        boolean test(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result);
    }
}
