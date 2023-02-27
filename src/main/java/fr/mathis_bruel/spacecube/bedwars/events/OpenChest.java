package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class OpenChest implements Listener {

    @EventHandler
    public void onChestOpen(InventoryOpenEvent event){
        if(event.getInventory().getType() == InventoryType.ENDER_CHEST){
            if(Manager.isCurrentlyInGame((Player) event.getPlayer())) {
                event.setCancelled(true);
                //event.getPlayer().closeInventory();
                Team team = Manager.getManager((Player) event.getPlayer()).getTeam((Player) event.getPlayer());
                if(team != null)
                    event.getPlayer().openInventory(team.getEnderchest());
            }

        }
    }

}
