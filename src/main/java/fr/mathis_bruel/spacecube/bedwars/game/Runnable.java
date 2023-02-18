package fr.mathis_bruel.spacecube.bedwars.game;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.manager.scoreboard.FastBoard;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class Runnable extends BukkitRunnable {
    public Manager manager;
    @Override
    public void run() {
        Arena arena = manager.getArena();
        // scoreboards
        if(manager.getManagerState().getCurrentState() == State.WAITING){
            manager.getPlayers().forEach(player -> {
                FastBoard board = Main.getBoard(player.getUniqueId());
                board.updateTitle("§6§lBedWars");
                board.updateLines(Arrays.asList(
                        "§7",
                        "§7Map: §f" + arena.getName(),
                        "§7",
                        "§7Players: §f" + manager.getPlayers().size() + "/" + arena.getMaxPlayers(),
                        "§7",
                        "§7Starting in: §f" + (manager.getStartingTime() == 30 ? "WAITING" : manager.getStartingTime() + "s" ),
                        "§7",
                        "§6§lwww.spacecube.games"

                ));
            });
        }




        if(manager.getManagerState().getCurrentState() == State.WAITING){
            if(manager.getPlayers().size() >= arena.getMinPlayers()){
                manager.getManagerState().setState(State.STARTING);
            }
        }


    }
}
