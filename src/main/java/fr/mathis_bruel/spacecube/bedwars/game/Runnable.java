package fr.mathis_bruel.spacecube.bedwars.game;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.generator.RunnableGeneratorsTeams;
import fr.mathis_bruel.spacecube.bedwars.manager.scoreboard.FastBoard;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;

public class Runnable extends BukkitRunnable {
    public Manager manager;

    @Override
    public void run() {
        Arena arena = manager.getArena();


        if (manager.getManagerState().getCurrentState() == State.WAITING) {
            if (manager.getPlayers().size() >= arena.getMinPlayers()) {
                manager.getManagerState().setState(State.STARTING);
            }
        }
        if (manager.getManagerState().getCurrentState() == State.STARTING) {
            if (manager.getPlayers().size() < arena.getMinPlayers()) {
                manager.getManagerState().setState(State.WAITING);
                manager.setStartingTime(30);
            } else if (manager.getStartingTime() == 0) {
                // set all players in a random team
                ArrayList<Team> teamsAlreadyUsed = new ArrayList<>();
                for (int i = 0; i < manager.getPlayers().size(); i+= arena.getPlayerPerTeam()) {
                    Team team = arena.getTeams().get(Utils.randomInt(0, arena.getTeams().size() - 1));
                    while (teamsAlreadyUsed.contains(team)) {
                        team = arena.getTeams().get(Utils.randomInt(0, arena.getTeams().size() - 1));
                    }
                    teamsAlreadyUsed.add(team);
                    for (int j = 0; j < arena.getPlayerPerTeam(); j++) {
                        if (i + j < manager.getPlayers().size()) {
                            team.addPlayer(manager.getPlayers().get(i + j));
                        }
                    }
                }


                // if a team has no players, set bed to dead
                for (Team team : arena.getTeams()) {
                    if (team.getPlayers().size() == 0) {
                        team.setBedAlive(false);
                    }
                }
                // teleport players to their team's spawn
                for (Team team : arena.getTeams()) {
                    if(team.getPlayers().size() != 0) for (Player player : team.getPlayers()) {
                        player.teleport(team.getSpawn());
                        player.getInventory().clear();
                    }
                    team.getGenerators().forEach(generator -> {
                        RunnableGeneratorsTeams runnableGenerators = new RunnableGeneratorsTeams();
                        runnableGenerators.generatorTeam = generator;
                        runnableGenerators.runTaskTimer(Main.getInstance(), 0, 20);
                        generator.setRunnableGenerators(runnableGenerators);
                    });
                }
                manager.getManagerState().setState(State.RUNNING);

                manager.initGame();

            } else {
                manager.setStartingTime(manager.getStartingTime() - 1);

            }
        }
        if (manager.getManagerState().getCurrentState() == State.RUNNING) {

        }


        // scoreboards
        if (manager.getManagerState().getCurrentState() == State.WAITING) {
            manager.getPlayers().forEach(player -> {
                FastBoard board = Main.getBoard(player.getUniqueId());
                board.updateTitle("§6§lBedWars");
                board.updateLines(Arrays.asList(
                        "§7",
                        "§7Map: §f" + arena.getName(),
                        "§7",
                        "§7Players: §f" + manager.getPlayers().size() + "/" + arena.getMaxPlayers(),
                        "§7",
                        "§7WAITING...",
                        "§7",
                        "§6§lwww.spacecube.games"

                ));
            });
        } else if (manager.getManagerState().getCurrentState() == State.STARTING) {
            manager.getPlayers().forEach(player -> {
                FastBoard board = Main.getBoard(player.getUniqueId());
                board.updateTitle("§6§lBedWars");
                board.updateLines(Arrays.asList(
                        "§7",
                        "§7Map: §f" + arena.getName(),
                        "§7",
                        "§7Players: §f" + manager.getPlayers().size() + "/" + arena.getMaxPlayers(),
                        "§7",
                        "§7Starting in: §f" + (manager.getStartingTime() == 30 ? "WAITING..." : manager.getStartingTime() + "s"),
                        "§7",
                        "§6§lwww.spacecube.games"

                ));
            });
        } else if (manager.getManagerState().getCurrentState() == State.RUNNING) {
            manager.getPlayers().forEach(player -> {
                FastBoard board = Main.getBoard(player.getUniqueId());
                board.updateTitle("§6§lBedWars");
                StringBuilder teamsLeft = new StringBuilder();
                for (Team team : arena.getTeams()) {
                    if (team.isBedAlive()) {
                        teamsLeft.append(team.getColor()).append("■ ");
                    } else if (team.getPlayers().size() > 0) {
                        teamsLeft.append(team.getColor() + "" + team.getPlayers().size() + " ");
                    }
                }
                Team team = manager.getTeam(player);
                board.updateLines(Arrays.asList(
                        "",
                        "§e§lTeams Left",
                        "§l"+teamsLeft.toString(),
                        "",
                        "§f§lYour Team",
                        "§7Color: " + team.getColor() + team.getName(),
                        "§7Bed: " + (team.isBedAlive() ? "§aAlive" : "§cDead"),
                        "",
                        "§4§lYour Stats",
                        "§7Kills: §f" + 0,
                        "§7Deaths: §f" + 0,
                        "§7Beds: §f" + 0,
                        "§7§n                ",
                        "",
                        "§6§lwww.spacecube.games"

                ));
            });
        }

    }
}
