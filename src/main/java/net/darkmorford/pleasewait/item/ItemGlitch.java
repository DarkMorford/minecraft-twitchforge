package net.darkmorford.pleasewait.item;

import net.darkmorford.pleasewait.PleaseWait;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemGlitch extends Item
{
    public ItemGlitch()
    {
        setRegistryName(PleaseWait.MODID + ":glitch");
        setUnlocalizedName(PleaseWait.MODID + ".glitch");
        setCreativeTab(PleaseWait.tabPleaseWait);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("This will have a use later!");
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
