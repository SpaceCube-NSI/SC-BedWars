package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements org.bukkit.event.Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        if(Manager.isCurrentlyInGame(event.getPlayer())) {
            Manager manager = Manager.getManager(event.getPlayer());
            if(manager == null) {
                Bukkit.broadcastMessage("§7[§b" + event.getPlayer().getName() + "§7] §f" + event.getMessage());
                return;
            }
            Arena arena = manager.getArena();
            if(arena == null) {
                Bukkit.broadcastMessage("§7[§b" + event.getPlayer().getName() + "§7] §f" + event.getMessage());
                return;
            }
            Team team = manager.getTeam(event.getPlayer());
            if(team == null) {
                Bukkit.broadcastMessage("§7[§b" + event.getPlayer().getName() + "§7] §f" + event.getMessage());
                return;
            }
            if(arena.getPlayerPerTeam() == 1){
                manager.broadcast("§7["+ team.getColor() + manager.getTeam(event.getPlayer()).getName() + "§7] §b" + event.getPlayer().getName() + "§7: §f" + event.getMessage());
            }else{
                if(event.getMessage().startsWith("!")){
                    manager.broadcast("§7["+ team.getColor() + manager.getTeam(event.getPlayer()).getName() + "§7] §b" + event.getPlayer().getName() + "§7: §f" + event.getMessage().substring(1));
                }else{
                    manager.broadcast("§7[§2T§7] ["+ team.getColor() + manager.getTeam(event.getPlayer()).getName() + "§7] §b" + event.getPlayer().getName() + "§7: §f" + event.getMessage());
                }
            }
        }else{
            Bukkit.broadcastMessage("§7[§b" + event.getPlayer().getName() + "§7] §f" + event.getMessage());
        }
    }

}
