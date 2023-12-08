package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.game.State;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class Drop implements org.bukkit.event.Listener {

    @EventHandler
    public void onDrop(org.bukkit.event.player.PlayerDropItemEvent event) {
        if(!Manager.isCurrentlyInGame(event.getPlayer()) && !event.getPlayer().hasPermission("bedwars.admin")) {
            event.setCancelled(true);
        } else {
            Manager manager = Manager.getManager(event.getPlayer());
            if(manager != null && manager.getManagerState().getCurrentState() != State.RUNNING){
                event.setCancelled(true);
            }
        }
        if (event.getItemDrop() == null) return;
        if (event.getItemDrop().getItemStack().getItemMeta() == null) return;
        if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName() == null) return;
        if (event.getItemDrop().getType().equals(Material.BED)) {
            event.setCancelled(true);
        }
        if (event.getItemDrop().getType().equals(Material.SKULL_ITEM)) {
            if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName() == "§6Your stats") {
                event.setCancelled(true);
            }
        }
        if (event.getItemDrop().getType().equals(Material.ARMOR_STAND)) {
            if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName() == "§6Select your team") {
                event.setCancelled(true);
            }
        }
    }

}
