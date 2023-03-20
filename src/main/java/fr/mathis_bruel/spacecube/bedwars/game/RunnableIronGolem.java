package fr.mathis_bruel.spacecube.bedwars.game;

import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class RunnableIronGolem extends BukkitRunnable {

    public IronGolem golem;
    public List<String> aggroList;
    public Player target;

    @Override
    public void run() {
        double minDistance = Double.MAX_VALUE;
        for (Entity entity : golem.getNearbyEntities(10, 10, 10)) {
            if (entity instanceof Player && aggroList.contains(((Player) entity).getName())) {
                double distance = entity.getLocation().distance(golem.getLocation());
                if (distance < minDistance) {
                    target = (Player) entity;
                    minDistance = distance;
                }
            }
        }
        if (target != null) {
            golem.setTarget(target);
        }
        if(golem.isDead()) {
            cancel();
        }
    }
}
