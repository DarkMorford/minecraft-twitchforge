package net.darkmorford.pleasewait.tileentity;

import net.darkmorford.pleasewait.twitch.TwitchState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

import static net.darkmorford.pleasewait.block.BlockStreamDetector.PROPERTYACTIVE;

public class TileStreamDetector extends TileEntity implements ITickable
{
    private boolean activeLastTick = false;

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

        boolean currentlyActive = TwitchState.isStreamOnline;
        IBlockState currentState = world.getBlockState(getPos());

        if (!activeLastTick && currentlyActive)
        {
            // Stream just came online, turn on redstone signal
            world.setBlockState(getPos(), currentState.withProperty(PROPERTYACTIVE, true));
        }

        if (activeLastTick && !currentlyActive)
        {
            // Stream just went down, turn off signal
            world.setBlockState(getPos(), currentState.withProperty(PROPERTYACTIVE, false));
        }

        activeLastTick = currentlyActive;
    }
}
