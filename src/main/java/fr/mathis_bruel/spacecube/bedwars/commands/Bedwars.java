package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.gui.*;
import fr.mathis_bruel.spacecube.bedwars.manager.scoreboard.FastBoard;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Bedwars implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = Main.getPrefix();

        if (args.length == 0) {
            sender.sendMessage("--------------------------------");
            sender.sendMessage(prefix + "§cBedwars commands:");
            sender.sendMessage(prefix + "§c/bedwars join [arena] §7- §fJoin an arena");
            sender.sendMessage(prefix + "§c/bedwars leave §7- §fLeave the arena you are in");
            sender.sendMessage(prefix + "§c/bedwars list §7- §fList all the arenas");
            sender.sendMessage(prefix + "§c/bedwars stats §7- §fShow your stats");
            sender.sendMessage(prefix + "§c/bedwars top §7- §fShow the top 10 players");
            sender.sendMessage(prefix + "§c/bedwars help §7- §fShow this help");
            sender.sendMessage(prefix + "§c/bedwars info §7- §fShow the plugin info");
            sender.sendMessage(prefix + "§c/bedwars-a §7- §fShow the admin commands");
            sender.sendMessage("--------------------------------");

        }
        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "help":
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cBedwars commands:");
                    sender.sendMessage(prefix + "§c/bedwars join <arena> §7- §fJoin an arena");
                    sender.sendMessage(prefix + "§c/bedwars leave §7- §fLeave the arena you are in");
                    sender.sendMessage(prefix + "§c/bedwars list §7- §fList all the arenas");
                    sender.sendMessage(prefix + "§c/bedwars stats §7- §fShow your stats");
                    sender.sendMessage(prefix + "§c/bedwars top §7- §fShow the top 10 players");
                    sender.sendMessage(prefix + "§c/bedwars help §7- §fShow this help");
                    sender.sendMessage(prefix + "§c/bedwars info §7- §fShow the plugin info");
                    sender.sendMessage(prefix + "§c/bedwars-a §7- §fShow the admin commands");
                    sender.sendMessage("--------------------------------");
                    break;
                case "info":
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cBedwars info:");
                    sender.sendMessage(prefix + "§cVersion: §f" + Main.getInstance().getDescription().getVersion());
                    sender.sendMessage(prefix + "§cAuthor: §fMathis_Bruel");
                    sender.sendMessage(prefix + "§cWebsite: §fhttps://mathisbruel.fr");
                    sender.sendMessage("--------------------------------");
                    break;
                case "shop": {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (Manager.isCurrentlyInGame(player)) {
                            Team team = Manager.getManager(player).getTeam(player);
                            if (team != null && player.getLocation().distance(team.getSpawn()) <= 25) {
                                player.openInventory(ShopItems.getInventory(team));
                            } else player.sendMessage(prefix + "§cYou must be in your base to open the shop!");
                        } else sender.sendMessage(prefix + "§cYou are not in a game!");
                    } else sender.sendMessage(prefix + "§cYou must be a player to execute this command!");
                    break;
                }
                case "upgrade": {
                    if(sender instanceof Player) {
                        Player player = (Player) sender;
                        if (Manager.isCurrentlyInGame(player)) {
                            Team team = Manager.getManager(player).getTeam(player);
                            if (team != null && player.getLocation().distance(team.getSpawn()) <= 25) {
                                player.openInventory(ShopUpgrades.getInventory(team));
                            } else player.sendMessage(prefix + "§cYou must be in your base to open the upgrade shop!");
                        } else sender.sendMessage(prefix + "§cYou are not in a game!");
                    } else sender.sendMessage(prefix + "§cYou must be a player to execute this command!");
                    break;
                }
                case "list":
                    sender.sendMessage(prefix + "§cArenas:");
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cList of arenas:");
                    for (Arena arena : Main.getInstance().arenas) {
                        sender.sendMessage(prefix + "§c- §f" + arena.getName() + " §7(§aEnable: §6"+ arena.isEnabled() +"§7)");

                    }
                    sender.sendMessage("--------------------------------");
                    break;
                case "stats":
                    ((Player) sender).openInventory(Stats.getInventory((Player) sender));
                    break;
                case "top":
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cBedwars top:");
                    sender.sendMessage(prefix + "§cComing soon!");
                    sender.sendMessage("--------------------------------");
                    break;
                case "leave": {
                    Player player = (Player) sender;
                    if (Manager.isCurrentlyInGame(player)) {
                        Manager.getManager(player).leave(player);
                    } else sender.sendMessage(prefix + "§cYou are not in a game!");
                    FastBoard board = Main.getBoard(player.getUniqueId());
                    Utils.resetPseudo(player);
                    board.updateLines(Arrays.asList(
                            "§f",
                            "§fNiveau: §f" + 0,
                            "§f",
                            "§fProgrès: §b" + 0 + "§7/§a" + 0,
                            "§8[§7 §a▊▊▊                §8]",
                            "§f",
                            "§fCoins: §e" + 0,
                            "§f",
                            "§fKills: §a" + 0,
                            "§fDeaths: §a" + 0,
                            "§fWins: §a" + 0,
                            "§fStreak: §a" + 0,
                            "§f",
                            "§6§lwww.spacecube.games"

                    ));

                    break;
                }
                case "join": {
                    if ((sender instanceof Player)) {
                        Player player = (Player) sender;
                        player.openInventory(Join.getInventory());
                    } else sender.sendMessage(prefix + "§cYou must be a player to use this command!");
                    break;
                }
                default:
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cNo command found! Type §f/bedwars help §cfor help");
                    sender.sendMessage("--------------------------------");
                    break;
            }
        }
        if (args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "join": {
                    if ((sender instanceof Player)) {
                        Player player = (Player) sender;
                        if (Manager.isCurrentlyInGame(player)) {
                            sender.sendMessage(prefix + "§cYou are already in a game!");
                        } else {
                            if(args[1].equalsIgnoreCase("1")){
                                player.openInventory(JoinChoiceSolo.getInventory());
                            }else if (args[1].equalsIgnoreCase("2")){
                                player.openInventory(JoinChoiceDouble.getInventory());
                            }else if (args[1].equalsIgnoreCase("3")){
                                player.openInventory(JoinChoiceTriple.getInventory());
                            }else if (args[1].equalsIgnoreCase("4")){
                                player.openInventory(JoinChoiceQuatre.getInventory());
                            }else {
                                Arena arena = Arena.getArenaByName(args[1]);
                                if (arena != null) {
                                    Manager manager = Manager.getManager(arena);
                                    if(manager == null) sender.sendMessage(prefix + "§cThis arena is not ready yet!");
                                    else {
                                        if(!manager.getArena().isEnabled()) {
                                            sender.sendMessage(prefix + "§cThis arena is not ready yet!");
                                            return true;
                                        }
                                        if (manager.getPlayers().size() >= manager.getArena().getMaxPlayers()) {
                                            player.sendMessage(Main.getPrefix() + "§cThis game is full.");
                                            return true;
                                        }
                                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                                        manager.join(player);
                                    }
                                }else sender.sendMessage(prefix + "§cThis arena does not exist!");
                            }

                        }
                    } else sender.sendMessage(prefix + "§cYou must be a player to use this command!");
                    break;
                }
                default:
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cNo command found! Type §f/bedwars help §cfor help");
                    sender.sendMessage("--------------------------------");
                    break;
            }
        }
        return false;
    }
}
