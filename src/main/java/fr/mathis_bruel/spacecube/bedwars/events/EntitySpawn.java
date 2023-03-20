package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.game.RunnableIronGolem;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.entity.IronGolem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;
import java.util.List;

public class EntitySpawn implements org.bukkit.event.Listener {

    @EventHandler
    public void onIronGolemSpawn(EntitySpawnEvent event) {
        if (event.getEntity() instanceof IronGolem) {
            IronGolem golem = (IronGolem) event.getEntity();
            String name = golem.getCustomName();
            if (name == null || !name.equals("§cIron Golem")) return;
            // "§c§l" + team.getName() + "§r§f's golem"
            String teamName = name.substring(4, name.length() - 12);
            Arena arena = Arena.getArenaByWorld(golem.getWorld());
            if (arena == null) return;
            Manager manager = Manager.getManager(arena);
            if (manager == null) return;
            Team team = manager.getTeam(teamName);
            List<String> aggroList = new ArrayList<>();
            manager.getPlayers().forEach(player -> {
                if (manager.getTeam(player) != team) {
                    aggroList.add(player.getName());
                }
            });
            RunnableIronGolem runnableIronGolem = new RunnableIronGolem();
            runnableIronGolem.aggroList = aggroList;
            runnableIronGolem.golem = golem;
            runnableIronGolem.runTaskTimer(Main.getInstance(), 0, 20);
        }
    }

}
