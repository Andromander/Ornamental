package com.androsa.ornamental.registry.helper;

import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.items.OrnamentBlockItem;
import com.androsa.ornamental.items.OrnamentTallBlockItem;
import com.androsa.ornamental.registry.handler.CreativeTabHandler;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

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
     * @param blockreg Block DeferredRegister
     * @param itemreg Item DeferredRegister
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
     * Register an OrnamentStair
     * @param base The base block. All Stair blocks require this.
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentStair
     */
    public <T extends OrnamentStair> RegistryObject<T> stairs(Block base, OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_stairs", stairsObject(base, props, builder), item ->
                registerBlockItem(item, builder, 4), CreativeTabHandler.STAIR_ORNAMENTS);
    }

    /**
     * Register an OrnamentSlab
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentSlab
     */
    public <T extends OrnamentSlab> RegistryObject<T> slab(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_slab", slabObject(props, builder), item ->
                registerBlockItem(item, builder, 3), CreativeTabHandler.SLAB_ORNAMENTS);
    }

    /**
     * Register an OrnamentFence
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentFence
     */
    public <T extends OrnamentFence> RegistryObject<T> fence(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_fence", fenceObject(props, builder), item ->
                registerBlockItem(item, builder, 1), CreativeTabHandler.FENCE_ORNAMENTS);
    }

    /**
     * Register an OrnamentTrapDoor
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentTrapDoor
     */
    public RegistryObject<OrnamentTrapDoor> trapdoor(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder)
                .noOcclusion()
                .isValidSpawn((state, reader, pos, type) -> false);

        return registerBlock(builder.name + "_trapdoor", trapdoorObject(props, builder), item ->
                registerBlockItem(item, builder, 5), CreativeTabHandler.TRAPDOOR_ORNAMENTS);
    }

    /**
     * Register an OrnamentFenceGate
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentFenceGate
     */
    public RegistryObject<OrnamentFenceGate> fencegate(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_fence_gate", fencegateObject(props, builder), item ->
                registerBlockItem(item, builder, 2), CreativeTabHandler.FENCE_GATE_ORNAMENTS);
    }

    /**
     * Register an OrnamentDoor
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentDoor
     */
    public RegistryObject<OrnamentDoor> door(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder).noOcclusion();

        return registerBlock(builder.name + "_door", doorObject(props, builder), item ->
                registerBlockItemDoor(item, builder, 0), CreativeTabHandler.DOOR_ORNAMENTS);
    }

    /**
     * Register an OrnamentPole
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentPole
     */
    public RegistryObject<OrnamentPole> pole(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_pole", poleObject(props, builder), item ->
                registerBlockItem(item, builder, 6), CreativeTabHandler.POLE_ORNAMENTS);
    }

    /**
     * Register an OrnamentBeam
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentBeam
     */
    public RegistryObject<OrnamentBeam> beam(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_beam", beamObject(props, builder), item ->
                registerBlockItem(item, builder, 7), CreativeTabHandler.BEAM_ORNAMENTS);
    }

    /**
     * Register an OrnamentWall
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentWall
     */
    public RegistryObject<OrnamentWall> wall(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_wall", wallObject(props, builder), item ->
                registerBlockItem(item, builder, 8), CreativeTabHandler.WALL_ORNAMENTS);
    }

    /**
     * Register an OrnamentSaddleDoor
     * @param builder OrnamentBuilder.
     * @return RegistryObject for an OrnamentSaddleDoor
     */
    public RegistryObject<OrnamentSaddleDoor> saddledoor(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder)
                .noOcclusion();

        return registerBlock(builder.name + "_saddle_door", saddledoorObject(props, builder), item ->
                registerBlockItem(item, builder, 9), CreativeTabHandler.SADDLE_DOOR_ORNAMENTS);
    }

    /**
     * INTERNAL USE
     */
    private <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item, List<RegistryObject<Block>> tab) {
        RegistryObject<T> reg = blockRegistry.register(name, block);
        itemRegistry.register(name, item.apply(reg));
        tab.add((RegistryObject<Block>) reg);
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block, OrnamentBuilder ornament, int fuelindex) {
        return () -> new OrnamentBlockItem(block.get(), PropertiesHelper.createItem(ornament), ornament, fuelindex);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemDoor(final RegistryObject<T> block, OrnamentBuilder ornament, int fuelindex) {
        return () -> new OrnamentTallBlockItem(block.get(), PropertiesHelper.createItem(ornament), ornament, fuelindex);
    }
}
