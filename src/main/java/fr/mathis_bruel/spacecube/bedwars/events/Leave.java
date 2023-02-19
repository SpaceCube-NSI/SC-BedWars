package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {
    @EventHandler
    public void leavePlayer(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Manager.isCurrentlyInGame(player))
            Manager.getManager(player).removePlayer(player);
    }
}
