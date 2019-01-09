package com.androsa.nifty.blocks;

import com.androsa.nifty.util.BlockModelHelper;
import com.androsa.nifty.util.ModelUtil;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class NiftyFenceGate extends BlockFenceGate implements BlockModelHelper {

    private final Supplier<IBlockState> blockstate;

    public NiftyFenceGate(Supplier<IBlockState> state, SoundType sound, float hardness, float resistance) {
        super(BlockPlanks.EnumType.OAK);

        this.setSoundType(sound);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(OPEN, false).withProperty(POWERED, false).withProperty(IN_WALL, false));

        blockstate = state;
    }

    public NiftyFenceGate(Supplier<IBlockState> state, SoundType sound, float hardness, float resistance, int harvest) {
        super(BlockPlanks.EnumType.OAK);

        this.setSoundType(sound);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHarvestLevel("pickaxe", harvest);
        this.setDefaultState(this.blockState.getBaseState().withProperty(OPEN, false).withProperty(POWERED, false).withProperty(IN_WALL, false));

        blockstate = state;
    }

    //HATE...
    @Override
    @Deprecated
    public Material getMaterial(IBlockState state) {
        return blockstate.get().getMaterial();
    }

    //...EVERYTHING
    @Override
    @Deprecated
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return blockstate.get().getMapColor(worldIn, pos);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Material material = blockstate.get().getMaterial();

        if (material == Material.IRON || material == Material.ROCK) {
            return false;
        } else {
            if (state.getValue(OPEN)) {
                state = state.withProperty(OPEN, Boolean.FALSE);
                worldIn.setBlockState(pos, state, 10);
            } else {
                EnumFacing enumfacing = EnumFacing.fromAngle((double)playerIn.rotationYaw);

                if (state.getValue(FACING) == enumfacing.getOpposite()) {
                    state = state.withProperty(FACING, enumfacing);
                }

                state = state.withProperty(OPEN, Boolean.TRUE);
                worldIn.setBlockState(pos, state, 10);
            }

            worldIn.playEvent(playerIn, state.getValue(OPEN) ? 1008 : 1014, pos, 0);
            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(POWERED).build());
        ModelUtil.registerToState(this, 0, getDefaultState().withProperty(FACING, EnumFacing.NORTH));
    }
}
