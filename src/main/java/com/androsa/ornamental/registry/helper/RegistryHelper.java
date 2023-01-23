package com.androsa.ornamental.registry.helper;

import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.data.OrnamentalBlockTags;
import com.androsa.ornamental.data.OrnamentalItemTags;
import com.androsa.ornamental.items.OrnamentBlockItem;
import com.androsa.ornamental.items.OrnamentTallBlockItem;
import com.androsa.ornamental.registry.handler.CreativeTabHandler;
import com.google.common.collect.Lists;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({"unchecked"})
public class RegistryHelper {

    private final DeferredRegister<Block> blockRegistry;
    private final DeferredRegister<Item> itemRegistry;

    /**
     * A helper class for registration. This covers the basics, but may also be extended for custom methods.
     * Always create your own RegistryHelper, otherwise blocks and items will get registered to the wrong mod ID.
     * @param blockreg Block DeferredRegister.
     * @param itemreg Item DeferredRegister.
     */
    public RegistryHelper(DeferredRegister<Block> blockreg, DeferredRegister<Item> itemreg) {
        this.blockRegistry = blockreg;
        this.itemRegistry = itemreg;
    }

    /**
     * For creating an OrnamentStair object without the need to override the registration method.
     * @param base The base block. All Stair blocks require this.
     * @param props Block Properties.
     * @param builder OrnamentBuilder.
     * @return Supplier of the Block object, directly provided to registration.
     */
    protected <T extends OrnamentStair> Supplier<T> stairsObject(Block base, BlockBehaviour.Properties props, OrnamentBuilder builder) {
        return () -> (T) new OrnamentStair(base::defaultBlockState, props, builder);
    }

    /**
     * For creating an OrnamentSlab object without the need to override the registration method.
     * @param props Block Properties.
     * @param builder OrnamentBuilder.
     * @return Supplier of the Block object, directly provided to registration.
     */
    protected <T extends OrnamentSlab> Supplier<T> slabObject(BlockBehaviour.Properties props, OrnamentBuilder builder) {
        return () -> (T) new OrnamentSlab(props, builder);
    }

    /**
     * For creating an OrnamentFence object without the need to override the registration method.
     * @param props Block Properties.
     * @param builder OrnamentBuilder.
     * @return Supplier of the Block object, directly provided to registration.
     */
    protected <T extends OrnamentFence> Supplier<T> fenceObject(BlockBehaviour.Properties props, OrnamentBuilder builder) {
        return () -> (T) new OrnamentFence(props, builder);
    }

    /**
     * For creating an OrnamentTrapDoor object without the need to override the registration method.
     * @param props Block Properties.
     * @param builder OrnamentBuilder.
     * @return Supplier of the Block object, directly provided to registration.
     */
    protected <T extends OrnamentTrapDoor> Supplier<T> trapdoorObject(BlockBehaviour.Properties props, OrnamentBuilder builder) {
        return () -> (T) new OrnamentTrapDoor(props, builder);
    }

    /**
     * For creating an OrnamentFenceGate object without the need to override the registration method.
     * @param props Block Properties.
     * @param builder OrnamentBuilder.
     * @return Supplier of the Block object, directly provided to registration.
     */
    protected <T extends OrnamentFenceGate> Supplier<T> fencegateObject(BlockBehaviour.Properties props, OrnamentBuilder builder) {
        return () -> (T) new OrnamentFenceGate(props, builder);
    }

    /**
     * For creating an OrnamentDoor object without the need to override the registration method.
     * @param props Block Properties.
     * @param builder OrnamentBuilder.
     * @return Supplier of the Block object, directly provided to registration.
     */
    protected <T extends OrnamentDoor> Supplier<T> doorObject(BlockBehaviour.Properties props, OrnamentBuilder builder) {
        return () -> (T) new OrnamentDoor(props, builder);
    }

    /**
     * For creating an OrnamentPole object without the need to override the registration method.
     * @param props Block Properties.
     * @param builder OrnamentBuilder.
     * @return Supplier of the Block object, directly provided to registration.
     */
    protected <T extends OrnamentPole> Supplier<T> poleObject(BlockBehaviour.Properties props, OrnamentBuilder builder) {
        return () -> (T) new OrnamentPole(props, builder);
    }

    /**
     * For creating an OrnamentBeam object without the need to override the registration method.
     * @param props Block Properties.
     * @param builder OrnamentBuilder.
     * @return Supplier of the Block object, directly provided to registration.
     */
    protected <T extends OrnamentBeam> Supplier<T> beamObject(BlockBehaviour.Properties props, OrnamentBuilder builder) {
        return () -> (T) new OrnamentBeam(props, builder);
    }

    /**
     * For creating an OrnamentWall object without the need to override the registration method.
     * @param props Block Properties.
     * @param builder OrnamentBuilder.
     * @return Supplier of the Block object, directly provided to registration.
     */
    protected <T extends OrnamentWall> Supplier<T> wallObject(BlockBehaviour.Properties props, OrnamentBuilder builder) {
        return () -> (T) new OrnamentWall(props, builder);
    }

    /**
     * For creating an OrnamentSaddleDoor object without the need to override the registration method.
     * @param props Block Properties.
     * @param builder OrnamentBuilder.
     * @return Supplier of the Block object, directly provided to registration.
     */
    protected <T extends OrnamentSaddleDoor> Supplier<T> saddledoorObject(BlockBehaviour.Properties props, OrnamentBuilder builder) {
        return () -> (T) new OrnamentSaddleDoor(props, builder);
    }

    /**
     * Register an OrnamentStair.
     * @param base The base block. All Stair blocks require this.
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentStair.
     */
    public <T extends OrnamentStair> RegistryObject<T> stairs(Block base, OrnamentBuilder builder) {
        return stairs(base, builder, Lists.newArrayList(), Lists.newArrayList());
    }

    /**
     * Register an OrnamentStair with individual tags.
     * @param base The base block. All Stair blocks require this.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @return RegistryObject for an OrnamentStair.
     */
    public <T extends OrnamentStair> RegistryObject<T> stairs(Block base, OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);
        blocktags.addAll(array(OrnamentalBlockTags.STAIRS));
        itemtags.addAll(array(OrnamentalItemTags.STAIRS));

        return registerBlock(builder, "_stairs", stairsObject(base, props, builder), item ->
                registerBlockItem(item, builder, 4), CreativeTabHandler.STAIR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * Register an OrnamentSlab.
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentSlab.
     */
    public <T extends OrnamentSlab> RegistryObject<T> slab(OrnamentBuilder builder) {
        return slab(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    /**
     * Register an OrnamentSlab with individual tags
     * Register an OrnamentSlab with individual tags.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @return RegistryObject for an OrnamentSlab.
     */
    public <T extends OrnamentSlab> RegistryObject<T> slab(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);
        blocktags.addAll(array(OrnamentalBlockTags.SLABS));
        itemtags.addAll(array(OrnamentalItemTags.SLABS));

        return registerBlock(builder, "_slab", slabObject(props, builder), item ->
                registerBlockItem(item, builder, 3), CreativeTabHandler.SLAB_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * Register an OrnamentFence.
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentFence.
     */
    public <T extends OrnamentFence> RegistryObject<T> fence(OrnamentBuilder builder) {
        return fence(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    /**
     * Register an OrnamentFence with individual tags.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @return RegistryObject for an OrnamentFence.
     */
    public <T extends OrnamentFence> RegistryObject<T> fence(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);
        blocktags.addAll(array(OrnamentalBlockTags.FENCES));
        itemtags.addAll(array(OrnamentalItemTags.FENCES));

        return registerBlock(builder, "_fence", fenceObject(props, builder), item ->
                registerBlockItem(item, builder, 1), CreativeTabHandler.FENCE_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * Register an OrnamentTrapDoor.
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentTrapDoor.
     */
    public RegistryObject<OrnamentTrapDoor> trapdoor(OrnamentBuilder builder) {
        return trapdoor(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    /**
     * Register an OrnamentTrapDoor with individual tags.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @return RegistryObject for an OrnamentTrapDoor.
     */
    public RegistryObject<OrnamentTrapDoor> trapdoor(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder)
                .noOcclusion()
                .isValidSpawn((state, reader, pos, type) -> false);
        blocktags.addAll(array(OrnamentalBlockTags.TRAPDOORS));
        itemtags.addAll(array(OrnamentalItemTags.TRAPDOORS));

        return registerBlock(builder, "_trapdoor", trapdoorObject(props, builder), item ->
                registerBlockItem(item, builder, 5), CreativeTabHandler.TRAPDOOR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * Register an OrnamentFenceGate.
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentFenceGate.
     */
    public RegistryObject<OrnamentFenceGate> fencegate(OrnamentBuilder builder) {
        return fencegate(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    /**
     * Register an OrnamentFenceGate with individual tags.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @return RegistryObject for an OrnamentFenceGate.
     */
    public RegistryObject<OrnamentFenceGate> fencegate(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);
        blocktags.addAll(array(OrnamentalBlockTags.FENCE_GATES));
        itemtags.addAll(array(OrnamentalItemTags.FENCE_GATES));

        return registerBlock(builder, "_fence_gate", fencegateObject(props, builder), item ->
                registerBlockItem(item, builder, 2), CreativeTabHandler.FENCE_GATE_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * Register an OrnamentDoor.
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentDoor.
     */
    public RegistryObject<OrnamentDoor> door(OrnamentBuilder builder) {
        return door(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    /**
     * Register an OrnamentDoor with individual tags.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @return RegistryObject for an OrnamentDoor.
     */
    public RegistryObject<OrnamentDoor> door(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder)
                .noOcclusion();
        blocktags.addAll(array(OrnamentalBlockTags.DOORS));
        itemtags.addAll(array(OrnamentalItemTags.DOORS));

        return registerBlock(builder, "_door", doorObject(props, builder), item ->
                registerBlockItemDoor(item, builder, 0), CreativeTabHandler.DOOR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * Register an OrnamentPole.
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentPole.
     */
    public RegistryObject<OrnamentPole> pole(OrnamentBuilder builder) {
        return pole(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    /**
     * Register an OrnamentPole with individual tags.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @return RegistryObject for an OrnamentPole.
     */
    public RegistryObject<OrnamentPole> pole(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);
        blocktags.addAll(array(OrnamentalBlockTags.POLES));
        itemtags.addAll(array(OrnamentalItemTags.POLES));

        return registerBlock(builder, "_pole", poleObject(props, builder), item ->
                registerBlockItem(item, builder, 6), CreativeTabHandler.POLE_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * Register an OrnamentBeam.
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentBeam.
     */
    public RegistryObject<OrnamentBeam> beam(OrnamentBuilder builder) {
        return beam(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    /**
     * Register an OrnamentBeam with individual tags.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @return RegistryObject for an OrnamentBeam.
     */
    public RegistryObject<OrnamentBeam> beam(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);
        blocktags.addAll(array(OrnamentalBlockTags.BEAMS));
        itemtags.addAll(array(OrnamentalItemTags.BEAMS));

        return registerBlock(builder, "_beam", beamObject(props, builder), item ->
                registerBlockItem(item, builder, 7), CreativeTabHandler.BEAM_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * Register an OrnamentWall.
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentWall.
     */
    public RegistryObject<OrnamentWall> wall(OrnamentBuilder builder) {
        return wall(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    /**
     * Register an OrnamentWall with individual tags.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @return RegistryObject for an OrnamentWall.
     */
    public RegistryObject<OrnamentWall> wall(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);
        blocktags.addAll(array(OrnamentalBlockTags.WALLS));
        itemtags.addAll(array(OrnamentalItemTags.WALLS));

        return registerBlock(builder, "_wall", wallObject(props, builder), item ->
                registerBlockItem(item, builder, 8), CreativeTabHandler.WALL_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * Register an OrnamentSaddleDoor.
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentSaddleDoor.
     */
    public RegistryObject<OrnamentSaddleDoor> saddledoor(OrnamentBuilder builder) {
        return saddledoor(builder, Lists.newArrayList(), Lists.newArrayList());
    }

    /**
     * Register an OrnamentSaddleDoor with individual tags.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @return RegistryObject for an OrnamentSaddleDoor.
     */
    public RegistryObject<OrnamentSaddleDoor> saddledoor(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder)
                .noOcclusion();
        blocktags.addAll(array(OrnamentalBlockTags.SADDLE_DOORS));
        itemtags.addAll(array(OrnamentalItemTags.SADDLE_DOORS));

        return registerBlock(builder, "_saddle_door", saddledoorObject(props, builder), item ->
                registerBlockItem(item, builder, 9), CreativeTabHandler.SADDLE_DOOR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * INTERNAL USE
     */
    private <T extends Block> RegistryObject<T> registerBlock(OrnamentBuilder builder, String suffix, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item, List<RegistryObject<Block>> tab, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        String name = builder.name + suffix;
        RegistryObject<T> reg = blockRegistry.register(name, block);
        itemRegistry.register(name, item.apply(reg));
        tab.add((RegistryObject<Block>) reg);

        blocktags.addAll(builder.blockTags);
        for (List<RegistryObject<? extends Block>> tag : blocktags) {
            tag.add(reg);
        }
        itemtags.addAll(builder.itemTags);
        for (List<RegistryObject<? extends Block>> tag : itemtags) {
            tag.add(reg);
        }

        return reg;
    }

    protected <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block, OrnamentBuilder ornament, int fuelindex) {
        return () -> new OrnamentBlockItem(block.get(), PropertiesHelper.createItem(ornament), ornament, fuelindex);
    }

    protected <T extends Block> Supplier<BlockItem> registerBlockItemDoor(final RegistryObject<T> block, OrnamentBuilder ornament, int fuelindex) {
        return () -> new OrnamentTallBlockItem(block.get(), PropertiesHelper.createItem(ornament), ornament, fuelindex);
    }

    @SafeVarargs
    public final ArrayList<List<RegistryObject<? extends Block>>> array(List<RegistryObject<? extends Block>>... lists) {
        return Lists.newArrayList(lists);
    }
}
