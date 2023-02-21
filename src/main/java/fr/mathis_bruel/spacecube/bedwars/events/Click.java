package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Click implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onClick(PlayerInteractEvent event) {
        if (event.getItem() != null ) {
            if(event.getItem().getItemMeta().getDisplayName() != null) {
                String name = event.getItem().getItemMeta().getDisplayName();
                switch (name) {
                    case "§4Leave game":
                        event.getPlayer().performCommand("bw leave");
                        return;
                    case "§6Your stats":
                        event.getPlayer().performCommand("bw stats");
                        return;
                    case "§6Spectator Settings":
                        event.getPlayer().sendMessage(Main.getPrefix() + "§fIs currently in development");
                        return;
                }
            }
        }
        /*if(Manager.isCurrentlyInGame(event.getPlayer())) {
            Team team = Manager.getManager(event.getPlayer()).getTeam(event.getPlayer());
            for (NPC npc : Main.getNpcs()) {
                if (npc.getDistanceSquared(event.getPlayer()) < 2) {
                    if (npc.getName().equals("§a§l" + "Items")) {
                        event.getPlayer().openInventory(ShopItems.getInventory(team));
                        return;
                    }
                    if(npc.getName().equals( "§a§l" + "Upgrades")){
                        event.getPlayer().sendMessage(Main.getPrefix() + "§fIs currently in development");
                        return;
                    }

                }
            }
        }*/





    }

}
