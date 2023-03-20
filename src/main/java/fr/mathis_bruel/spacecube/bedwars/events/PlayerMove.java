package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.game.RunnableAlarm;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMove implements Listener {

    @EventHandler(priority= EventPriority.MONITOR, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        if(Main.isPlayerFreeze(event.getPlayer())) {
            Location loc = event.getFrom();
            loc.setX(loc.getBlockX()+0.5);
            loc.setY(loc.getBlockY());
            loc.setZ(loc.getBlockZ()+0.5);
            event.getPlayer().teleport(loc);
            event.setCancelled(true);
        }
        Player player = event.getPlayer();
        if(Manager.isCurrentlyInGame(player)){
            Manager manager = Manager.getManager(player);
            if(manager == null) return;
            Team team = manager.getTeam(player);
            if(team == null) return;
            if(team.getHealpool()){
                // around 30 blocks of the spawn of the team add regen effect
                if(player.getLocation().distance(team.getSpawn()) <= 25){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999999, 0, true));
                }else if(player.hasPotionEffect(PotionEffectType.REGENERATION)){
                    player.removePotionEffect(PotionEffectType.REGENERATION);
                }
            }
            /*if(manager.getInBase().contains(player.getLocation())){
                System.out.println(1);
                Team teamInside = manager.getTeam(player.getLocation());
                if(teamInside == null) return;
                System.out.println(2);
                if(team.getAlarm()){
                    System.out.println(3);
                    // alert the team that someone is in their base with sound, message and title
                    if(teamInside != team){
                        teamInside.broadcast("§cSomeone is in your base!");
                        teamInside.broadcast("§cIt's " + team.getColor() + player.getName() + "§c!");
                        for(Player p : teamInside.getPlayers()){
                            p.playSound(p.getLocation(), "mob.enderdragon.growl", 1, 1);
                            p.sendTitle("§cSomeone is in your base!", "§cIt's " + team.getColor() + player.getName() + "§c!");
                        }
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 1, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 1, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10, 1, true));
                }
            }*/
            for (Team team2 : manager.getArena().getTeams()){
                if(team2.getSpawn().distance(player.getLocation()) <= 25){
                    if(team2.getAlarm()){
                        team2.setAlarm(false);
                        // SLOW, BLINDNESS, SLOW_DIGGING pendant 10 secondes
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 300, 0, true));
                        if(team2 != team){
                            team2.broadcast("§cSomeone is in your base!");
                            team2.broadcast("§cIt's " + team.getColor() + player.getName() + "§c!");
                            for(Player p : team2.getPlayers())
                                p.sendTitle("§cSomeone is in your base!", "§cIt's " + team.getColor() + player.getName() + "§c!");
                            RunnableAlarm runnableAlarm = new RunnableAlarm();
                            runnableAlarm.team = team2;
                            runnableAlarm.player = player;
                            runnableAlarm.runTaskTimer(Main.getInstance(), 0, 10);
                        }
                    }
                }
            }

        }
    }

}
