package net.darkmorford.twitchforge.item;

import net.darkmorford.twitchforge.TwitchForge;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGlitch extends Item
{
    public ItemGlitch()
    {
        setRegistryName("glitch");
        setUnlocalizedName(TwitchForge.MODID + ".glitch");
        this.setCreativeTab(TwitchForge.tabTwitchForge);
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
