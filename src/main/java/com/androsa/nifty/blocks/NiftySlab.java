package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.util.BlockModelHelper;
import com.androsa.nifty.util.ModelUtil;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class NiftySlab extends BlockSlab implements BlockModelHelper {

    public static final PropertyEnum<NiftySlabEnum> VARIANT = PropertyEnum.create("variant", NiftySlabEnum.class);

    private final boolean isDouble;
    private final Supplier<Item> dropItem;

    public NiftySlab(boolean isDouble, NiftyBlock block) {
        super(block.material, block.color);

        this.setSoundType(block.sound);
        this.setHardness(block.hardness);
        this.setResistance(block.resistance);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setHarvestLevel(block.tool, block.level);
        this.useNeighborBrightness = true;

        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, NiftySlabEnum.NORMAL);
        if (!this.isDouble()) state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
        this.setDefaultState(state);

        this.isDouble = isDouble;
        this.dropItem = block.dropItem;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return NiftySlabEnum.NORMAL;
    }

    @Override
    public boolean isDouble() {
        return isDouble;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return dropItem.get();
    }

    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(dropItem.get());
    }

    @Override
    public String getTranslationKey(int meta) {
        return super.getTranslationKey();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return this.isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, VARIANT, HALF);
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return this.isDouble() ? this.getDefaultState() : this.getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta % EnumBlockHalf.values().length]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(HALF).ordinal();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        if (this.isDouble())
            ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(VARIANT).ignore(HALF).build());
        else {
            ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(VARIANT).build());
            ModelUtil.registerToState(this, 0, getDefaultState());
        }
    }

    public enum NiftySlabEnum implements IStringSerializable {
        NORMAL;

        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
