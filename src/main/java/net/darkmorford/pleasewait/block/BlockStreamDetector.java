package net.darkmorford.pleasewait.block;

import net.darkmorford.pleasewait.PleaseWait;
import net.darkmorford.pleasewait.tileentity.TileStreamDetector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStreamDetector extends Block
{
    public BlockStreamDetector()
    {
        super(Material.ROCK);

        setRegistryName(PleaseWait.MODID + ":streamDetector");
        setUnlocalizedName("streamDetector");
        setCreativeTab(PleaseWait.tabPleaseWait);

        setDefaultState(blockState.getBaseState().withProperty(PROPERTYACTIVE, false));
    }

    // Handle a blockstate property for activity
    public static final PropertyBool PROPERTYACTIVE = PropertyBool.create("active");

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, PROPERTYACTIVE);
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    @Deprecated
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state;
    }

    // Set up the tile entity to manage states
    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileStreamDetector();
    }

    // Provide a redstone signal when stream is active
    @Override
    @Deprecated
    public boolean canProvidePower(IBlockState state)
    {
        return true;
    }

    @Override
    @Deprecated
    public int getWeakPower(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        boolean isActive = state.getValue(PROPERTYACTIVE);

        if (isActive)
            return 15;
        else
            return 0;
    }

    @Override
    @Deprecated
    public int getStrongPower(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }
}
