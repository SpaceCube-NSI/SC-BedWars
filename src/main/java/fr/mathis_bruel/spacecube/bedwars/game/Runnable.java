package fr.mathis_bruel.spacecube.bedwars.game;

import com.sk89q.worldedit.WorldEditException;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.games.Games;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.games.Stats;
import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.generator.RunnableGeneratorsTeams;
import fr.mathis_bruel.spacecube.bedwars.manager.NPCManager;
import fr.mathis_bruel.spacecube.bedwars.manager.scoreboard.FastBoard;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Runnable extends BukkitRunnable {
    public Manager manager;
    private int timeEnd = 10;
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
                        team.getBed().setType(Material.AIR);
                    }
                }
                // teleport players to their team's spawn
                for (Team team : arena.getTeams()) {
                    if(team.getPlayers().size() != 0) for (Player player : team.getPlayers()) {
                        player.teleport(team.getSpawn());
                        player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1, 1);
                        player.getInventory().clear();
                        player.getInventory().setArmorContents(null);


                    }
                    team.getGenerators().forEach(generator -> {
                        RunnableGeneratorsTeams runnableGenerators = new RunnableGeneratorsTeams();
                        runnableGenerators.generatorTeam = generator;
                        runnableGenerators.arena = arena;
                        runnableGenerators.team = team;
                        runnableGenerators.runTaskTimer(Main.getInstance(), 0, 20);
                        generator.setRunnableGenerators(runnableGenerators);
                    });
                }
                manager.getManagerState().setState(State.RUNNING);

                try {
                    manager.initGame();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WorldEditException e) {
                    e.printStackTrace();
                }

            } else {
                manager.setStartingTime(manager.getStartingTime() - 1);

            }
        }
        if (manager.getManagerState().getCurrentState() == State.RUNNING) {

            if(manager.isFinish()) {
                manager.getManagerState().setState(State.ENDED);
                for (Player player : manager.getPlayers()){
                    player.setHealth(20);
                    player.setFoodLevel(20);
                }
                manager.broadcast("§7-----------------------------");
                manager.broadcast("§6§lBedWars");
                manager.broadcast("§7-----------------------------");
                manager.broadcast("§7The game is over!");
                manager.broadcast("§7The winners are:");
                for (Player player : manager.getWinners()){
                    manager.broadcast("§f- " + player.getName());
                }
                manager.broadcast("§7-----------------------------");
                manager.broadcast("§6§lwww.spacecube.games");
                manager.broadcast("§7-----------------------------");
            }

        }

        if(manager.getManagerState().getCurrentState() == State.ENDED){
            timeEnd--;
            for (Player player : manager.getWinners()){
                Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                FireworkMeta fireworkMeta = firework.getFireworkMeta();
                fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.RED).withColor(Color.GREEN).withColor(Color.BLUE).with(FireworkEffect.Type.BALL_LARGE).build());
                fireworkMeta.setPower(1);
                firework.setFireworkMeta(fireworkMeta);
            }
            if(timeEnd == 0) manager.getManagerState().setState(State.ENDING);
        }

        if(manager.getManagerState().getCurrentState() == State.ENDING){
            manager.getPlayerKills().forEach((player, integer) -> {
                Stats stats = new Stats(Games.BedWars, player.getUniqueId());
                stats.init();
                stats.addKills(integer);
                stats.updateStats();
            });
            manager.getPlayerDeaths().forEach((player, integer) -> {
                Stats stats = new Stats(Games.BedWars, player.getUniqueId());
                stats.init();
                stats.addDeath(integer);
                stats.updateStats();
            });
            manager.getPlayerBeds().forEach((player, integer) -> {
                Stats stats = new Stats(Games.BedWars, player.getUniqueId());
                stats.init();
                stats.addDivers("Beds", integer);
                stats.updateStats();
            });

            manager.getWinners().forEach(player -> {
                Stats stats = new Stats(Games.BedWars, player.getUniqueId());
                stats.init();
                stats.addWins(1);
                stats.updateStats();
            });
            

            arena.getTeams().forEach(team -> {
                team.getEnderchest().clear();
                team.setAlarm(false);
                team.setHealpool(false);
                team.setLvlSpeed(0);
            });


            for (Player player : manager.getPlayers()){
                player.teleport(Utils.parseStringToLoc(Main.getInstance().getConfig().getString("lobby")));
                player.getActivePotionEffects().clear();
                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
                player.setHealth(20);
                player.setFoodLevel(20);
                player.setGameMode(GameMode.SURVIVAL);
                FastBoard board = new FastBoard(player);
                board.updateTitle("§6§lBedWars");
                Stats state = new Stats(Games.BedWars, player.getUniqueId());
                state.init();
                board.updateLines(Arrays.asList(
                        "§f",
                        "§fNiveau: §f" + 0,
                        "§f",
                        "§fProgrès: §b" + 0 + "§7/§a"+ 0,
                        "§8[§7 §a▊▊▊                §8]",
                        "§f",
                        "§fCoins: §e" + 0,
                        "§f",
                        "§fKills: §a" + state.getKills(),
                        "§fDeaths: §a" + state.getDeath(),
                        "§fWins: §a" + state.getWins(),
                        "§fStreak: §a" + 0,
                        "§f",
                        "§6§lwww.spacecube.games"

                ));
                Main.addBoard(player.getUniqueId(), board);
                player.teleport(Utils.parseStringToLoc(Main.getInstance().getConfig().getString("lobby")));
                player.getInventory().clear();
                ItemStack headPlayer = Utils.getHead(player);
                ItemMeta headPlayerMeta = headPlayer.getItemMeta();
                headPlayerMeta.setDisplayName("§6Your stats");
                headPlayerMeta.setLore(Arrays.asList("Click for see your stats", "§7Kills: §a0", "§7Deaths: §c0", "§7K/D: §e0"));
                headPlayer.setItemMeta(headPlayerMeta);
                player.getInventory().setItem(4, headPlayer);
            }
            for (Player player : manager.getSpecators()){
                player.teleport(Utils.parseStringToLoc(Main.getInstance().getConfig().getString("lobby")));
                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
                player.setHealth(20);
                player.setFoodLevel(20);
                player.setGameMode(GameMode.SURVIVAL);
                FastBoard board = new FastBoard(player);
                board.updateTitle("§6§lBedWars");
                Stats state = new Stats(Games.BedWars, player.getUniqueId());
                state.init();
                board.updateLines(Arrays.asList(
                        "§f",
                        "§fNiveau: §f" + 0,
                        "§f",
                        "§fProgrès: §b" + 0 + "§7/§a"+ 0,
                        "§8[§7 §a▊▊▊                §8]",
                        "§f",
                        "§fCoins: §e" + 0,
                        "§f",
                        "§fKills: §a" + state.getKills(),
                        "§fDeaths: §a" + state.getDeath(),
                        "§fWins: §a" + state.getWins(),
                        "§fStreak: §a" + 0,
                        "§f",
                        "§6§lwww.spacecube.games"

                ));
                Main.addBoard(player.getUniqueId(), board);
                player.teleport(Utils.parseStringToLoc(Main.getInstance().getConfig().getString("lobby")));
                player.getInventory().clear();
                ItemStack headPlayer = Utils.getHead(player);
                ItemMeta headPlayerMeta = headPlayer.getItemMeta();
                headPlayerMeta.setDisplayName("§6Your stats");
                headPlayerMeta.setLore(Arrays.asList("Click for see your stats", "§7Kills: §a0", "§7Deaths: §c0", "§7K/D: §e0"));
                headPlayer.setItemMeta(headPlayerMeta);
                player.getInventory().setItem(4, headPlayer);
            }
            try {
                Utils.restoreMap(arena.getName(), arena.getWorld(), arena.getPos1Map().getBlockX(), arena.getPos1Map().getBlockY(), arena.getPos1Map().getBlockZ());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WorldEditException e) {
                e.printStackTrace();
            }
            manager.getPlayers().clear();
            manager.getSpecators().clear();
            manager.getWinners().clear();
            arena.getDiamondsGenerators().forEach(generator -> {
                generator.getHologram().destroyHolograms();
            });
            arena.getEmeraldsGenerators().forEach(generator -> {
                generator.getHologram().destroyHolograms();
            });
            arena.getTeams().forEach(team -> {
                team.setBedAlive(true);
                team.getPlayers().clear();
                team.getGeneratorHolograms().forEach(generatorHologram -> {
                    generatorHologram.destroyHolograms();
                });
                team.getShopHolograms().forEach(shopHologram -> {
                    shopHologram.destroyHolograms();
                });
                team.getGenerators().forEach(generator -> {
                    generator.setLevelIron(1);
                    generator.setLevelGold(0);
                    generator.setLevelDiamond(0);
                });
            });
            arena.getNpcs().forEach(NPCManager::destroy);

            timeEnd = 10;
            manager.setStartingTime(30);
            manager.getManagerState().setState(State.WAITING);
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
                        teamsLeft.append(team.getColor()).append("█ ");
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
                        "§7Color: " + (team != null ? team.getColor() + team.getName() : "§cEliminated"),
                        "§7Bed: " + (team != null ? (team.isBedAlive() ? "§aAlive" : "§cDead") : "§cEliminated"),
                        "",
                        "§4§lYour Stats",
                        "§7Kills: §f" + manager.getPlayerKills().get(player),
                        "§7Deaths: §f" + manager.getPlayerDeaths().get(player),
                        "§7Beds: §f" + manager.getPlayerBeds().get(player),
                        "§7§n                ",
                        "",
                        "§6§lwww.spacecube.games"

                ));
            });
            manager.getSpecators().forEach(player -> {
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
                        "§7Color: " + (team != null ? team.getColor() + team.getName() : "§cEliminated"),
                        "§7Bed: " + (team != null ? (team.isBedAlive() ? "§aAlive" : "§cDead") : "§cEliminated"),
                        "",
                        "§4§lYour Stats",
                        "§7Kills: §f" + manager.getPlayerKills().get(player),
                        "§7Deaths: §f" + manager.getPlayerDeaths().get(player),
                        "§7Beds: §f" + manager.getPlayerBeds().get(player),
                        "§7§n                ",
                        "",
                        "§6§lwww.spacecube.games"

                ));
            });
        }

    }
}
