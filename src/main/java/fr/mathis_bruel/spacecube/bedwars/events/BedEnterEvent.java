package fr.mathis_bruel.spacecube.bedwars.events;

import org.bukkit.event.EventHandler;

public class BedEnterEvent implements org.bukkit.event.Listener {

    @EventHandler
    public void onBedEnter(org.bukkit.event.player.PlayerBedEnterEvent event) {
        event.setCancelled(true);
    }

}
