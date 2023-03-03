package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements org.bukkit.event.Listener {

    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent event) {
        if(!Manager.isCurrentlyInGame(event.getPlayer()) && !event.getPlayer().hasPermission("bw.admin")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§cYou can't place blocks in this world!");
        }
        if(Main.isGodMode(event.getPlayer()))
            Main.removeGodMode(event.getPlayer());
        if(Manager.isCurrentlyInGame(event.getPlayer())) {
            Manager manager = Manager.getManager(event.getPlayer());
            if(manager == null)
                return;
            Arena arena = manager.getArena();
            if(arena == null)
                return;
            if(manager.isLocationNotPlaceable(event.getBlock().getLocation())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cYou can't place blocks here!");
            }
            // if is left the region of arena (manager.getArena().getPos1Map() and manager.getArena().getPos2Map())
            if(!isBlockInRegion(event.getBlock().getLocation(), manager.getArena().getPos1Map(), manager.getArena().getPos2Map())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cYou can't place blocks here!");
            }

        }
    }
    private boolean isBlockInRegion(Location blockLocation, Location pos1, Location pos2) {
        int x1 = Math.min(pos1.getBlockX(), pos2.getBlockX());
        int y1 = Math.min(pos1.getBlockY(), pos2.getBlockY());
        int z1 = Math.min(pos1.getBlockZ(), pos2.getBlockZ());
        int x2 = Math.max(pos1.getBlockX(), pos2.getBlockX());
        int y2 = Math.max(pos1.getBlockY(), pos2.getBlockY());
        int z2 = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        return blockLocation.getBlockX() >= x1 && blockLocation.getBlockX() <= x2
                && blockLocation.getBlockY() >= y1 && blockLocation.getBlockY() <= y2
                && blockLocation.getBlockZ() >= z1 && blockLocation.getBlockZ() <= z2
                && blockLocation.getWorld().equals(pos1.getWorld());
    }

}
