package com.androsa.nifty.blocks;

import com.androsa.nifty.util.BlockModelHelper;
import com.androsa.nifty.util.ModelUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Supplier;

public class NiftyRedstoneFenceGate extends NiftyFenceGate implements BlockModelHelper {

    public NiftyRedstoneFenceGate(Supplier<IBlockState> state) {
        super(state, SoundType.METAL, 5.0F, 10.0F, 0);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return 3;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(POWERED).build());
        ModelUtil.registerToState(this, 0, getDefaultState().withProperty(FACING, EnumFacing.NORTH));
    }
}
