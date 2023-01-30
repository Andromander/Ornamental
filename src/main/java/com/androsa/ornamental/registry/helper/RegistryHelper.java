package com.androsa.ornamental.registry.helper;

import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.data.OrnamentalBlockTags;
import com.androsa.ornamental.data.OrnamentalItemTags;
import com.google.common.collect.Lists;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class RegistryHelper extends MasterRegistryHelper {

    public RegistryHelper(DeferredRegister<Block> blockreg, DeferredRegister<Item> itemreg) {
        super(blockreg, itemreg);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> stairBlockTags() {
        return array(OrnamentalBlockTags.STAIRS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> stairItemTags() {
        return array(OrnamentalItemTags.STAIRS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> slabBlockTags() {
        return array(OrnamentalBlockTags.SLABS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> slabItemTags() {
        return array(OrnamentalItemTags.SLABS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> fenceBlockTags() {
        return array(OrnamentalBlockTags.FENCES);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> fenceItemTags() {
        return array(OrnamentalItemTags.FENCES);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> trapdoorBlockTags() {
        return array(OrnamentalBlockTags.TRAPDOORS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> trapdoorItemTags() {
        return array(OrnamentalItemTags.TRAPDOORS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> fencegateBlockTags() {
        return array(OrnamentalBlockTags.FENCE_GATES);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> fencegateItemTags() {
        return array(OrnamentalItemTags.FENCE_GATES);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> doorBlockTags() {
        return array(OrnamentalBlockTags.DOORS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> doorItemTags() {
        return array(OrnamentalItemTags.DOORS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> poleBlockTags() {
        return array(OrnamentalBlockTags.POLES);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> poleItemTags() {
        return array(OrnamentalItemTags.POLES);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> beamBlockTags() {
        return array(OrnamentalBlockTags.BEAMS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> beamItemTags() {
        return array(OrnamentalItemTags.BEAMS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> wallBlockTags() {
        return array(OrnamentalBlockTags.WALLS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> wallItemTags() {
        return array(OrnamentalItemTags.WALLS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> saddledoorBlockTags() {
        return array(OrnamentalBlockTags.SADDLE_DOORS);
    }

    @Override
    protected ArrayList<List<RegistryObject<? extends Block>>> saddledoorItemTags() {
        return array(OrnamentalItemTags.SADDLE_DOORS);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#stairs(Block, OrnamentBuilder, ArrayList, ArrayList, StairFactory)}.
     */
    public RegistryObject<OrnamentStair> stairs(Block base, OrnamentBuilder builder) {
        return stairs(base, builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public RegistryObject<OrnamentStair> stairs(Block base, OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        return stairs(base, builder, blocktags, itemtags, OrnamentStair::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#slab(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public RegistryObject<OrnamentSlab> slab(OrnamentBuilder builder) {
        return slab(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public RegistryObject<OrnamentSlab> slab(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        return slab(builder, blocktags, itemtags, OrnamentSlab::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#fence(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public RegistryObject<OrnamentFence> fence(OrnamentBuilder builder) {
        return fence(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public RegistryObject<OrnamentFence> fence(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        return fence(builder, blocktags, itemtags, OrnamentFence::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#trapdoor(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public RegistryObject<OrnamentTrapDoor> trapdoor(OrnamentBuilder builder) {
        return trapdoor(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public RegistryObject<OrnamentTrapDoor> trapdoor(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        return trapdoor(builder, blocktags, itemtags, OrnamentTrapDoor::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#fencegate(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public RegistryObject<OrnamentFenceGate> fencegate(OrnamentBuilder builder) {
        return fencegate(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public RegistryObject<OrnamentFenceGate> fencegate(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        return fencegate(builder, blocktags, itemtags, OrnamentFenceGate::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#door(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public RegistryObject<OrnamentDoor> door(OrnamentBuilder builder) {
        return door(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public RegistryObject<OrnamentDoor> door(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        return door(builder, blocktags, itemtags, OrnamentDoor::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#pole(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public RegistryObject<OrnamentPole> pole(OrnamentBuilder builder) {
        return pole(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public RegistryObject<OrnamentPole> pole(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        return pole(builder, blocktags, itemtags, OrnamentPole::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#beam(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public RegistryObject<OrnamentBeam> beam(OrnamentBuilder builder) {
        return beam(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public RegistryObject<OrnamentBeam> beam(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        return beam(builder, blocktags, itemtags, OrnamentBeam::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#wall(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public RegistryObject<OrnamentWall> wall(OrnamentBuilder builder) {
        return wall(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public RegistryObject<OrnamentWall> wall(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        return wall(builder, blocktags, itemtags, OrnamentWall::new);
    }

    /**
     * Examples of creating template methods of {@link MasterRegistryHelper#saddledoor(OrnamentBuilder, ArrayList, ArrayList, BlockFactory)}.
     */
    public RegistryObject<OrnamentSaddleDoor> saddledoor(OrnamentBuilder builder) {
        return saddledoor(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    public RegistryObject<OrnamentSaddleDoor> saddledoor(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        return saddledoor(builder, blocktags, itemtags, OrnamentSaddleDoor::new);
    }
}
