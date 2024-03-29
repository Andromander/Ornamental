package com.androsa.ornamental.registry.helper;

import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.data.OrnamentalBlockTags;
import com.androsa.ornamental.data.OrnamentalItemTags;
import com.google.common.collect.Lists;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RegistryHelper extends MasterRegistryHelper {

    public RegistryHelper(DeferredRegister<Block> blockreg, DeferredRegister<Item> itemreg) {
        super(blockreg, itemreg);
    }

    @Override
    protected TagHelper stairTags() {
        return new TagHelper(
                this.array(OrnamentalBlockTags.STAIRS),
                this.array(OrnamentalItemTags.STAIRS));
    }

    @Override
    protected TagHelper slabTags() {
        return new TagHelper(
                this.array(OrnamentalBlockTags.SLABS),
                this.array(OrnamentalItemTags.SLABS));
    }

    @Override
    protected TagHelper fenceTags() {
        return new TagHelper(
                this.array(OrnamentalBlockTags.FENCES),
                this.array(OrnamentalItemTags.FENCES));
    }

    @Override
    protected TagHelper trapdoorTags() {
        return new TagHelper(
                this.array(OrnamentalBlockTags.TRAPDOORS),
                this.array(OrnamentalItemTags.TRAPDOORS));
    }

    @Override
    protected TagHelper fencegateTags() {
        return new TagHelper(
                this.array(OrnamentalBlockTags.FENCE_GATES),
                this.array(OrnamentalItemTags.FENCE_GATES));
    }

    @Override
    protected TagHelper doorTags() {
        return new TagHelper(
                this.array(OrnamentalBlockTags.DOORS),
                this.array(OrnamentalItemTags.DOORS));
    }

    @Override
    protected TagHelper poleTags() {
        return new TagHelper(
                this.array(OrnamentalBlockTags.POLES),
                this.array(OrnamentalItemTags.POLES));
    }

    @Override
    protected TagHelper beamTags() {
        return new TagHelper(
                this.array(OrnamentalBlockTags.BEAMS),
                this.array(OrnamentalItemTags.BEAMS));
    }

    @Override
    protected TagHelper wallTags() {
        return new TagHelper(
                this.array(OrnamentalBlockTags.WALLS),
                this.array(OrnamentalItemTags.WALLS));
    }

    @Override
    protected TagHelper saddledoorTags() {
        return new TagHelper(
                this.array(OrnamentalBlockTags.SADDLE_DOORS),
                this.array(OrnamentalItemTags.SADDLE_DOORS));
    }

    @Override
    protected TagHelper supportTags() {
        return new TagHelper(
                this.array(OrnamentalBlockTags.SUPPORTS),
                this.array(OrnamentalItemTags.SUPPORTS));
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#stairs(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public Supplier<OrnamentStair> stairs(OrnamentBuilder builder) {
        return stairs(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public Supplier<OrnamentStair> stairs(OrnamentBuilder builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        return stairs(builder, blocktags, itemtags, OrnamentStair::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#slab(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public Supplier<OrnamentSlab> slab(OrnamentBuilder builder) {
        return slab(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public Supplier<OrnamentSlab> slab(OrnamentBuilder builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        return slab(builder, blocktags, itemtags, OrnamentSlab::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#fence(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public Supplier<OrnamentFence> fence(OrnamentBuilder builder) {
        return fence(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public Supplier<OrnamentFence> fence(OrnamentBuilder builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        return fence(builder, blocktags, itemtags, OrnamentFence::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#trapdoor(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public Supplier<OrnamentTrapDoor> trapdoor(OrnamentBuilder builder) {
        return trapdoor(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public Supplier<OrnamentTrapDoor> trapdoor(OrnamentBuilder builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        return trapdoor(builder, blocktags, itemtags, OrnamentTrapDoor::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#fencegate(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public Supplier<OrnamentFenceGate> fencegate(OrnamentBuilder builder) {
        return fencegate(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public Supplier<OrnamentFenceGate> fencegate(OrnamentBuilder builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        return fencegate(builder, blocktags, itemtags, OrnamentFenceGate::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#door(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public Supplier<OrnamentDoor> door(OrnamentBuilder builder) {
        return door(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public Supplier<OrnamentDoor> door(OrnamentBuilder builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        return door(builder, blocktags, itemtags, OrnamentDoor::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#pole(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public Supplier<OrnamentPole> pole(OrnamentBuilder builder) {
        return pole(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public Supplier<OrnamentPole> pole(OrnamentBuilder builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        return pole(builder, blocktags, itemtags, OrnamentPole::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#beam(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public Supplier<OrnamentBeam> beam(OrnamentBuilder builder) {
        return beam(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public Supplier<OrnamentBeam> beam(OrnamentBuilder builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        return beam(builder, blocktags, itemtags, OrnamentBeam::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#wall(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public Supplier<OrnamentWall> wall(OrnamentBuilder builder) {
        return wall(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public Supplier<OrnamentWall> wall(OrnamentBuilder builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        return wall(builder, blocktags, itemtags, OrnamentWall::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#saddledoor(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public Supplier<OrnamentSaddleDoor> saddledoor(OrnamentBuilder builder) {
        return saddledoor(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public Supplier<OrnamentSaddleDoor> saddledoor(OrnamentBuilder builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        return saddledoor(builder, blocktags, itemtags, OrnamentSaddleDoor::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#support(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public Supplier<OrnamentSupport> support(OrnamentBuilder builder) {
        return support(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public Supplier<OrnamentSupport> support(OrnamentBuilder builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        return support(builder, blocktags, itemtags, OrnamentSupport::new);
    }
}
