package net.darkmorford.pleasewait.block;

import net.darkmorford.pleasewait.PleaseWait;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
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
