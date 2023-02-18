package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Click implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        if(event.getItem().getItemMeta() == null) return;
        if(event.getItem().getItemMeta().getDisplayName() == null) return;
        String name = event.getItem().getItemMeta().getDisplayName();
        switch(name){
            case "§4Leave game":
                event.getPlayer().performCommand("bw leave");
                break;
            case "§6Your stats":
                event.getPlayer().performCommand("bw stats");
                break;
            case "§6Spectator Settings":
                event.getPlayer().sendMessage(Main.getPrefix()+"§fIs currently in development");
                break;
        }
    }


}
