package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.game.State;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(!Manager.isCurrentlyInGame(event.getPlayer()) && !event.getPlayer().hasPermission("bw.admin")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§cYou can't break blocks in this world!");
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
            if(manager.getManagerState().getCurrentState() != State.RUNNING) event.setCancelled(true);
            if(manager.isBlockNotBreakable(event.getBlock())) {
                if(event.getBlock().getType() == Material.BED_BLOCK){
                    Team teamDestroyBed = manager.getTeam(event.getBlock());
                    if(teamDestroyBed == null)
                        return;
                    if(manager.getTeam(event.getPlayer()) == teamDestroyBed){
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("§cYou can't break your own bed!");
                        return;
                    }else{
                        // not drop item
                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);
                        teamDestroyBed.setBedAlive(false);
                        manager.broadcast("§7------------------------");
                        manager.broadcast("§cThe bed of the team " + teamDestroyBed.getColor() + teamDestroyBed.getName() + "§c has been destroyed!");
                        manager.broadcast("§cBy " + manager.getTeam(event.getPlayer()).getColor() + event.getPlayer().getName() + "§c!");
                        manager.broadcast("§7------------------------");
                        // play dragon sound
                        for(Player player : arena.getPlayers()){
                            player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                        }
                        return;
                    }
                }
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cYou can't break this block!");
            }
        }
    }

}
