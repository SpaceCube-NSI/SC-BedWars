package fr.mathis_bruel.spacecube.bedwars.game;

import fr.mathis_bruel.spacecube.bedwars.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnProtection extends BukkitRunnable {
    Player player;
    int timer = 5;

    @Override
    public void run() {
        if(!Main.isGodMode(player)) {
            this.cancel();
            return;
        }
        if(timer <= 0){
            Main.removeGodMode(player);
            player.sendMessage("§aYou are no longer protected");
            this.cancel();
        } else {
            player.sendMessage("§aYou are protected for " + timer + " seconds");
            timer--;
        }
    }
}
