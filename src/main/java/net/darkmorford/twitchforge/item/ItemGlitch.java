package net.darkmorford.twitchforge.item;

import net.darkmorford.twitchforge.TwitchForge;
import net.minecraft.item.Item;

public class ItemGlitch extends Item
{
    public ItemGlitch()
    {
        setRegistryName("glitch");
        setUnlocalizedName(TwitchForge.MODID + ".glitch");
        this.setCreativeTab(TwitchForge.tabTwitchForge);
    }
}
