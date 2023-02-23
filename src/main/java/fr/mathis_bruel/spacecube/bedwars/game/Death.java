package fr.mathis_bruel.spacecube.bedwars.game;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Death extends BukkitRunnable {

    private int time = 5;
    public Player player;
    @Override
    public void run() {
        if(time == 5){
            player.setHealth(20);
            player.setFoodLevel(20);
            Main.addPlayerFreeze(player);
            player.sendTitle("§6Respawn in", "§2➄");
            time --;
        }else if(time == 0) {
            this.cancel();
            Main.removePlayerFreeze(player);
            player.sendMessage("§aYou are respawned!");
        } else {
            // message repawn in x seconds
            player.sendMessage("§cYou will respawn in " + time + " seconds");
            // send titlemesssage "➀", "➁", "➂", "➃", "➄"
            if(time == 4) player.sendTitle("§6Respawn in", "§2➃");
            else if(time == 3) player.sendTitle("§6Respawn in", "§e➂");
            else if(time == 2) player.sendTitle("§6Respawn in", "§6➁");
            else if(time == 1) player.sendTitle("§6Respawn in", "§c➀");

            time--;
        }
    }

    public int getTime() {
        return time;
    }
}
