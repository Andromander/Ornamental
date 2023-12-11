package com.androsa.ornamental.builder;

import com.mojang.datafixers.util.Pair;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

/**
 * A helper class for creating a BlockSetType from scratch.
 * While it is more ideal to create a BlockSetType and then reference it in {@link OrnamentBuilder#blockSetType(BlockSetType)}, this will help in instances where that may not happen.
 */
public class BlockSetBuilder {

    private final String name;
    private boolean openByHand = false;
    private boolean openByBreeze = false;
    private boolean activateByArrow = false;
    private BlockSetType.PressurePlateSensitivity pressureSensitivity = BlockSetType.PressurePlateSensitivity.EVERYTHING;
    private SoundType sound = SoundType.STONE;
    private Pair<SoundEvent, SoundEvent> doorSounds = Pair.of(SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN);
    private Pair<SoundEvent, SoundEvent> trapdoorSounds = Pair.of(SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN);
    private Pair<SoundEvent, SoundEvent> plateSounds = Pair.of(SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON);
    private Pair<SoundEvent, SoundEvent> buttonSounds = Pair.of(SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON);

    /**
     * Create a builder to become a BlockSetType.
     * A name is necessary in order to build a BlockSetType.
     */
    public BlockSetBuilder(String name) {
        this.name = name;
    }

    /**
     * Allows ornaments to be opened by hand.
     */
    public BlockSetBuilder openByHand() {
        this.openByHand = true;
        return this;
    }

    /**
     * Allows ornaments to be opened by a Breeze's wind charge.
     */
    public BlockSetBuilder openByBreeze() {
        this.openByBreeze = true;
        return this;
    }

    /**
     * Allows ornaments to activate with a shot arrow.
     * Note that Ornamental does not add Buttons natively and as such this field is only for future-proofing.
     */
    public BlockSetBuilder activateByArrow() {
        this.activateByArrow = true;
        return this;
    }

    /**
     * Sets the pressure sensitivity of a pressure plate.
     * Note that Ornamental does not add Pressure Plates natively and as such this field is only for future-proofing.
     * @param sensitivity the PressurePlateSensitivity.
     */
    public BlockSetBuilder pressureSensitivity(BlockSetType.PressurePlateSensitivity sensitivity) {
        this.pressureSensitivity = sensitivity;
        return this;
    }

    /**
     * A defaulting method that makes ornaments responsive to all properties.
     */
    public BlockSetBuilder weakBlockSet() {
        this.openByHand = true;
        this.openByBreeze = true;
        this.activateByArrow = true;
        this.pressureSensitivity = BlockSetType.PressurePlateSensitivity.EVERYTHING;
        return this;
    }

    /**
     * Sets the SoundType for the ornaments.
     * @param sound the SoundType to use.
     */
    public BlockSetBuilder soundType(SoundType sound) {
        this.sound = sound;
        return this;
    }

    /**
     * Sets the opening sounds for Doors. This is converted to Pair for internal purposes.
     * @param close the close sound.
     * @param open the open sound.
     */
    public BlockSetBuilder doorSounds(SoundEvent close, SoundEvent open) {
        this.doorSounds = Pair.of(close, open);
        return this;
    }

    /**
     * Sets the opening sounds for Trap Doors. This is converted to Pair for internal purposes.
     * @param close the close sound.
     * @param open the open sound.
     */
    public BlockSetBuilder trapdoorSounds(SoundEvent close, SoundEvent open) {
        this.trapdoorSounds = Pair.of(close, open);
        return this;
    }

    /**
     * Sets the activation sounds for Pressure Plates. This is converted to Pair for internal purposes.
     * Note that Ornamental does not add Pressure Plates natively and as such this field is only for future-proofing.
     * @param off the off sound.
     * @param on the on sound.
     */
    public BlockSetBuilder pressurePlateSounds(SoundEvent off, SoundEvent on) {
        this.plateSounds = Pair.of(off, on);
        return this;
    }

    /**
     * Sets the activation sounds for Pressure Plates. This is converted to Pair for internal purposes.
     * Note that Ornamental does not add Buttons natively and as such this field is only for future-proofing.
     * @param off the off sound.
     * @param on the on sound.
     */
    public BlockSetBuilder buttonSounds(SoundEvent off, SoundEvent on) {
        this.buttonSounds = Pair.of(off, on);
        return this;
    }

    /**
     * A defaulting method that makes ornaments behave like an iron set.
     */
    public BlockSetBuilder ironSounds() {
        this.doorSounds = Pair.of(SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN);
        this.trapdoorSounds = Pair.of(SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN);
        this.plateSounds = Pair.of(SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON);
        this.buttonSounds = Pair.of(SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
        return this;
    }

    public BlockSetType build() {
        return new BlockSetType(this.name, this.openByHand, this.openByBreeze, this.activateByArrow, this.pressureSensitivity, this.sound,
                doorSounds.getFirst(), doorSounds.getSecond(),
                trapdoorSounds.getFirst(), trapdoorSounds.getSecond(),
                plateSounds.getFirst(), plateSounds.getSecond(),
                buttonSounds.getFirst(), buttonSounds.getSecond());
    }
}
