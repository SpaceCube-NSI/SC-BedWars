package fr.mathis_bruel.spacecube.bedwars.game;

import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RunnableAlarm extends BukkitRunnable {
    int timer = 0;
    public Team team;
    public Player player;

    @Override
    public void run() {
        for(Player p : team.getPlayers()){
            p.playSound(p.getLocation(), "note.pling", 1, 1);
        }
        if(timer == 10){
            cancel();
        }
        timer++;
    }
}
