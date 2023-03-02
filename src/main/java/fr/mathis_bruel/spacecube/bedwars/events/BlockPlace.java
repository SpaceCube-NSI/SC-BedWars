package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
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
        }
    }

}
