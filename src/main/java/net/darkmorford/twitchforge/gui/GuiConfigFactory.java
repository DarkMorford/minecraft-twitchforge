package net.darkmorford.twitchforge.gui;

import net.darkmorford.twitchforge.Config;
import net.darkmorford.twitchforge.TwitchForge;
import net.darkmorford.twitchforge.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

import java.util.Set;

public class GuiConfigFactory implements IModGuiFactory
{
    @Override
    public void initialize(Minecraft minecraftInstance)
    {
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass()
    {
        return GuiConfiguration.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }

    @Override
    @Deprecated
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
    {
        return null;
    }

    public static class GuiConfiguration extends GuiConfig
    {
        public GuiConfiguration(GuiScreen parent)
        {
            super(
                    parent,
                    new ConfigElement(CommonProxy.config.getCategory(Config.CATEGORY_GENERAL)).getChildElements(),
                    TwitchForge.MODID,
                    false,
                    false,
                    "TwitchForge Configuration"
            );
        }
    }
}
