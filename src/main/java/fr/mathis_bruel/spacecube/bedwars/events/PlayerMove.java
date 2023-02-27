package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler(priority= EventPriority.MONITOR, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        if(Main.isPlayerFreeze(event.getPlayer())) {
            Location loc = event.getFrom();
            loc.setX(loc.getBlockX()+0.5);
            loc.setY(loc.getBlockY());
            loc.setZ(loc.getBlockZ()+0.5);
            event.getPlayer().teleport(loc);
            event.setCancelled(true);
        }
    }

}
