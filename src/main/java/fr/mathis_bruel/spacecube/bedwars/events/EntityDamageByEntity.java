package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Death;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageByEntity implements org.bukkit.event.Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(player.getHealth() <= 0) {
                if(Manager.isCurrentlyInGame(player)) {
                    Team team = Manager.getManager(player).getTeam(player);
                    if(team != null) {
                        player.spigot().respawn();
                        System.out.println("Player " + player.getName() + " is dead!");
                        player.teleport(team.getSpawn());
                        Manager.getManager(player).broadcast("§c" + player.getName() + " has been killed by " + event.getDamager().getName());
                        Death death = new Death();
                        death.player = player;
                        death.runTaskTimer(Main.getInstance(), 0, 20);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDamageEvent event) {
        System.out.println(1);
        if(event.getEntity() instanceof Player) {
            System.out.println(2);
            Player player = (Player) event.getEntity();
            if(player.getHealth() < 1) {
                System.out.println(3);
                if(Manager.isCurrentlyInGame(player)) {
                    System.out.println(4);
                    Team team = Manager.getManager(player).getTeam(player);
                    if(team != null) {
                        player.spigot().respawn();
                        System.out.println("Player " + player.getName() + " is dead!");
                        player.teleport(team.getSpawn());
                        Manager.getManager(player).broadcast("§c" + player.getName() + " has been killed by " + event.getCause().name());
                        Death death = new Death();
                        death.player = player;
                        death.runTaskTimer(Main.getInstance(), 0, 20);
                    }
                }
            }
        }
    }


}
