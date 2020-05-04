package ca.jtai.hydrophobic;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.BoundingBox;

public class Hydrophobic implements Listener {
    private static boolean isInLiquid(Player player) {
        World world = player.getWorld();
        // Loop through all the blocks in the player's bounding box, and check if any are liquid blocks
        BoundingBox box = player.getBoundingBox();
        int minX = (int) Math.floor(box.getMinX()), maxX = (int) Math.floor(box.getMaxX());
        int minY = (int) Math.floor(box.getMinY()), maxY = (int) Math.floor(box.getMaxY());
        int minZ = (int) Math.floor(box.getMinZ()), maxZ = (int) Math.floor(box.getMaxZ());
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if (world.getBlockAt(x, y, z).isLiquid())
                        return true;
                }
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (!player.isGliding())
            return;
        if (!isInLiquid(player))
            return;
        player.setGliding(false);
        player.setSwimming(true);
    }
}
