package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.game.State;
import fr.mathis_bruel.spacecube.bedwars.gui.SelectTeam;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements org.bukkit.event.Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getItem() == null) return;
        if(event.getItem().getItemMeta() == null) return;
        if(event.getItem().getItemMeta().getDisplayName() == null) return;
        if(event.getItem().getType() == Material.BED){
            if(Manager.isCurrentlyInGame(event.getPlayer())){
                Manager manager = Manager.getManager(event.getPlayer());
                if(manager == null) return;
                if(manager.getManagerState().getCurrentState() == State.WAITING || manager.getManagerState().getCurrentState() == State.STARTING){
                    event.setCancelled(true);
                    manager.leave(event.getPlayer());
                }
            }
        }
        if(event.getItem().getType() == Material.SKULL_ITEM){
            if(event.getItem().getItemMeta().getDisplayName() == "§6Your stats"){
                event.setCancelled(true);
                event.getPlayer().performCommand("bw stats");
            }
        }
        if(event.getItem().getType() == Material.ARMOR_STAND){
            if(event.getItem().getItemMeta().getDisplayName() =="§6Select your team"){
                event.setCancelled(true);
                event.getPlayer().openInventory(SelectTeam.getInventory(Manager.getManager(event.getPlayer()).getArena(), event.getPlayer()));
            }
        }
    }

}
