package com.androsa.ornamental.builder;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;

public interface HazardDamagePredicate {
    /**
     * To apply the DamageSource, a Level is required, hence why the Predicate.
     * @param level the Level to get DamageSources
     */
    DamageSource apply(Level level);
}
