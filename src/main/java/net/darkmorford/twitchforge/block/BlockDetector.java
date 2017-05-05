package net.darkmorford.twitchforge.block;

import net.darkmorford.twitchforge.TwitchForge;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDetector extends Block
{
    public BlockDetector()
    {
        super(Material.ROCK);

        setRegistryName(TwitchForge.MODID + ":streamDetector");
        setUnlocalizedName(TwitchForge.MODID + ".streamDetector");
        setCreativeTab(TwitchForge.tabTwitchForge);
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
