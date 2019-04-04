package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.util.BlockModelHelper;
import com.androsa.nifty.util.ModelUtil;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NiftyTrapDoor extends BlockTrapDoor implements BlockModelHelper {

    private final MapColor mapColor;

    public NiftyTrapDoor(NiftyBlock block) {
        super(block.material);

        this.setSoundType(block.sound);
        this.setHardness(block.hardness);
        this.setResistance(block.resistance);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHarvestLevel(block.tool, block.level);

        this.mapColor = block.color;
    }

    // Yeah, screw you too BlockTrapDoor
    @Override
    @Deprecated
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return mapColor;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (material == Material.IRON || material == Material.ROCK) {
            //Basically, Iron and Rock Nifty Trapdoors won't open. Wood, Cloth, Leaves, and Slime can
            return false;
        } else {
            state = state.cycleProperty(OPEN);
            worldIn.setBlockState(pos, state, 2);
            this.playSound(playerIn, worldIn, pos, state.getValue(OPEN));
            return true;
        }
    }

    //Entities fall through "weak" materials
    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        IBlockState state = worldIn.getBlockState(pos);

        if (!state.getValue(OPEN)) {
            if (material == Material.CLAY || material == Material.LEAVES || material == Material.CLOTH) {
                state = state.cycleProperty(OPEN);
                worldIn.setBlockState(pos, state, 2);
                this.playSound(null, worldIn, pos, state.getValue(OPEN));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelUtil.registerToState(this, 0, getDefaultState().withProperty(FACING, EnumFacing.NORTH));
    }
}
