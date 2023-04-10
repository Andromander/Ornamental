package com.androsa.ornamental.builder;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

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
