package net.darkmorford.pleasewait.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileStreamDetector extends TileEntity implements ITickable
{
    @Override
    public void update()
    {
        // Make sure we have a world object
        if (!hasWorldObj())
            return;

        // Only operate on the server side
        World world = getWorld();
        if (world.isRemote)
            return;
    }
}
