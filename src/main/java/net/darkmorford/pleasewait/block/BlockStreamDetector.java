package net.darkmorford.pleasewait.block;

import net.darkmorford.pleasewait.PleaseWait;
import net.darkmorford.pleasewait.tileentity.TileStreamDetector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
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
    }

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
