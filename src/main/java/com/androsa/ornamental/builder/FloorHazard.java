package com.androsa.ornamental.builder;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * This record holds all the information necessary to test and damage an entity standing on the block.
 *
 * @param predicate the predicate to test against. For more information, see {@link FloorHazardPredicate#test(Level, BlockPos, BlockState, Entity)}.
 * @param damage the predicate to apply damage. For more information, see {@link HazardDamagePredicate#damage()}.
 * @param amount the amount of damage to apply.
 */
public record FloorHazard(FloorHazardPredicate predicate, HazardDamagePredicate damage, float amount) {

    public interface FloorHazardPredicate {
        /**
         * The test predicate for whether the entity can step on this block without damage.
         * Example: if an entity is not stepping carefully, is living, and does not have Frost Walker, apply the predicate.
         * @param level the Level.
         * @param pos the BlockPos of the block.
         * @param state the BlockState of the block.
         * @param entity the Entity stepping on the block.
         */
        boolean test(Level level, BlockPos pos, BlockState state, Entity entity);
    }

    public interface HazardDamagePredicate {
        /**
         * To apply the DamageSource, a Level is required, hence why the Predicate.
         * @param level the Level to get DamageSources
         */
        DamageSource apply(Level level);
    }
}
