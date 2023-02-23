package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(Main.isPlayerFreeze(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

}
