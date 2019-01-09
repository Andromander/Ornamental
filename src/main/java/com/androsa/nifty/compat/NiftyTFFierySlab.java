package com.androsa.nifty.compat;

import com.androsa.nifty.util.BlockModelHelper;
import com.androsa.nifty.util.ModelUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NiftyTFFierySlab extends NiftyTFSlab implements BlockModelHelper {

    public NiftyTFFierySlab(boolean isDouble) {
        super(isDouble, Material.IRON, MapColor.IRON, SoundType.METAL, 5.0F);
    }

    @Override
    @Deprecated
    @SideOnly(Side.CLIENT)
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
        return 15728880;
    }

    @Override
    @Deprecated
    public boolean getUseNeighborBrightness(IBlockState state) {
        return true;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if ((!entityIn.isImmuneToFire())
                && entityIn instanceof EntityLivingBase
                && (!EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn))) {
            entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing face) {
        return true;
    }

    @Override
    @Deprecated
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState state = blockAccess.getBlockState(pos.offset(side));
        return state.getBlock() != this;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        if (isDouble()) {
            ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(VARIANT).ignore(HALF).build());
        } else {
            ModelResourceLocation mrl = new ModelResourceLocation(this.getRegistryName(), "inventory_fiery");
            ModelUtil.registerToState(this, 0, getDefaultState());
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, mrl);
        }
    }
}
