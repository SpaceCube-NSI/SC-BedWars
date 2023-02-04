package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.manager.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BedwarsAdmin implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = Main.getPrefix();

        if (args.length == 0) {
            sender.sendMessage("--------------------------------");
            sender.sendMessage(prefix + "§cBedwarsAdmin commands:");
            sender.sendMessage(prefix + "§c/bedwars-a arena §7 - §fManage arenas");
            sender.sendMessage(prefix + "§c/bedwars-a hologram §7 - §fManage holograms for stats");
            sender.sendMessage(prefix + "§c/bedwars-a setlobby §7 - §fSet the lobby");
            sender.sendMessage(prefix + "§c/bedwars-a reload §7 - §fReload the plugin");
            sender.sendMessage(prefix + "§c/bedwars-a help §7 - §fShow this help");
            sender.sendMessage("--------------------------------");

        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
                Main.getInstance().getPluginLoader().enablePlugin(Main.getInstance());
                sender.sendMessage(prefix + "§aPlugin reloaded!");
                return true;
            }
            switch (args[0]) {
                case "arena":
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena commands:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena create <arenaName> §7 - §fCreate an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena delete <arenaName> §7 - §fDelete an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena setSpawn <arenaName> §7 - §fSet the spawn of an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena setSpawnSpectator <arenaName> §7 - §fSet the spectator spawn of an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena setLobby <arenaName> §7 - §fSet the lobby of an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena setPlayersPerTeam <arenaName> <playersPerTeam> §7 - §fSet the players per team of an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena addTeam <arenaName> <color> §7 - §fAdd a team to an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena removeTeam <arenaName> §7 - §fRemove a team from an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> §7 - §fList all the teams of an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setColor <teamName> <color> §7 - §fSet the color of a team");
                    sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setSpawn <teamName> §7 - §fSet the spawn of a team");
                    sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setBed <teamName> §7 - §fSet the bed of a team (target block)");
                    sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setShopItems <teamName> §7 - §fSet the shop items of a team");
                    sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setShopUpgrades <teamName> §7 - §fSet the shop upgrades of a team");
                    sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setIron <teamName> §7 - §fSet the iron of a team");
                    sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setGold <teamName> §7 - §fSet the gold of a team");
                    sender.sendMessage(prefix + "§c/bedwars-a arena addGenerator <arenaName> <type> §7 - §fAdd a generator to an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena removeGenerator <arenaName> §7 - §fRemove a generator from an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena generators <arenaName> §7 - §fList all the generators of an arena");
                    sender.sendMessage("--------------------------------");
                    break;
                case "hologram":
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cBedwarsAdmin hologram commands:");
                    sender.sendMessage(prefix + "§c/bedwars-a hologram tphere §7 - §fTeleport the hologram stats to you");
                    sender.sendMessage(prefix + "§c/bedwars-a hologram set §7 - §fSet the hologram stats");
                    sender.sendMessage(prefix + "§c/bedwars-a hologram remove §7 - §fRemove the hologram stats");
                    sender.sendMessage("--------------------------------");
                    break;
                case "setlobby":
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        Main.getInstance().getConfig().set("lobby", Utils.parseLocToString(player.getLocation()));
                        Main.getInstance().saveConfig();
                        sender.sendMessage(prefix + "§aLobby set!");
                    } else {
                        sender.sendMessage(prefix + "§cYou must be a player to use this command!");
                    }
                    break;
                case "help":
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cBedwarsAdmin commands:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena §7 - §fManage arenas");
                    sender.sendMessage(prefix + "§c/bedwars-a hologram §7 - §fManage holograms for stats");
                    sender.sendMessage(prefix + "§c/bedwars-a setlobby §7 - §fSet the lobby");
                    sender.sendMessage(prefix + "§c/bedwars-a reload §7 - §fReload the plugin");
                    sender.sendMessage(prefix + "§c/bedwars-a help §7 - §fShow this help");
                    sender.sendMessage("--------------------------------");
                    break;
                default:
                    sender.sendMessage(prefix + "§cUnknown command! Type /bedwars-a help for help");
                    break;


            }
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("arena")) {
                switch (args[1].toLowerCase()) {
                    case "create":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena create command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena create <arenaName> §7 - §fCreate an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "delete":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena delete command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena delete <arenaName> §7 - §fDelete an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "setspawn":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena setSpawn command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena setSpawn <arenaName> §7 - §fSet the spawn of an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "setspawnspectator":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena setSpawnSpectator command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena setSpawnSpectator <arenaName> §7 - §fSet the spectator spawn of an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "setlobby":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena setLobby command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena setLobby <arenaName> §7 - §fSet the lobby of an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "setplayersperteam":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena setPlayersPerTeam command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena setPlayersPerTeam <arenaName> <playersPerTeam> §7 - §fSet the players per team of an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "addteam":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena addTeam command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena addTeam <arenaName> <color> §7 - §fAdd a team to an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "removeteam":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena removeTeam command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena removeTeam <arenaName> §7 - §fRemove a team from an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "teams":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena teams command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> §7 - §fShow the teams of an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "team":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena team command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setColor <teamName> <color> §7 - §fSet the color of a team");
                        sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setSpawn <teamName> §7 - §fSet the spawn of a team");
                        sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setBed <teamName> §7 - §fSet the bed of a team (target block)");
                        sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setShopItems <teamName> §7 - §fSet the shop items of a team");
                        sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setShopUpgrades <teamName> §7 - §fSet the shop upgrades of a team");
                        sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setIron <teamName> §7 - §fSet the iron of a team");
                        sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> setGold <teamName> §7 - §fSet the gold of a team");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "addgenerator":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena addGenerator command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena addGenerator <arenaName> <generatorType> §7 - §fAdd a generator to an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "removegenerator":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena removeGenerator command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena removeGenerator <arenaName> §7 - §fRemove a generator from an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    case "generators":
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cBedwarsAdmin arena generators command:");
                        sender.sendMessage(prefix + "§c/bedwars-a arena generators <arenaName> §7 - §fShow the generators of an arena");
                        sender.sendMessage("--------------------------------");
                        break;
                    default:
                        sender.sendMessage(prefix + "§cUnknown command. Type §f/bedwars-a help §cfor help.");
                        break;
                }
                if (args[0].equalsIgnoreCase("holograms")) {
                    sender.sendMessage(prefix + "§cIn development");
                }
            }
        }
        if (args.length == 3) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + "§cYou must be a player to use this command.");
                return true;
            }
            Player player = (Player) sender;
            switch (args[1].toLowerCase()) {
                case "create":
                    Arena arena = new Arena(player.getWorld(), args[2]);
                    arena.save();
                    sender.sendMessage(prefix + "§aArena created.");
                    break;
                case "delete":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena delete command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena delete <arenaName> §7 - §fDelete an arena");
                    break;
                case "setspawn":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena setSpawn command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena setSpawn <arenaName> §7 - §fSet the spawn of an arena");
                    break;
                case "setspawnspectator":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena setSpawnSpectator command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena setSpawnSpectator <arenaName> §7 - §fSet the spectator spawn of an arena");
                    break;
                case "setlobby":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena setLobby command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena setLobby <arenaName> §7 - §fSet the lobby of an arena");
                    break;
                case "setplayersperteam":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena setPlayersPerTeam command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena setPlayersPerTeam <arenaName> <playersPerTeam> §7 - §fSet the players per team of an arena");
                    break;
                case "addteam":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena addTeam command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena addTeam <arenaName> <color> §7 - §fAdd a team to an arena");
                    break;
                case "removeteam":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena removeTeam command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena removeTeam <arenaName> §7 - §fRemove a team from an arena");
                    break;
                case "teams":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena teams command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> §7 - §fShow the teams of an arena");
                    break;
                case "addgenerator":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena addGenerator command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena addGenerator <arenaName> <generatorType> §7 - §fAdd a generator to an arena");
                    break;
                case "removegenerator":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena removeGenerator command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena removeGenerator <arenaName> §7 - §fRemove a generator from an arena");
                    break;
                case "generators":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena generators command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena generators <arenaName> §7 - §fShow the generators of an arena");
                    break;
                default:
                    sender.sendMessage(prefix + "§cUnknown command. Type §f/bedwars-a help §cfor help.");
                    break;
            }
        }


        return false;
    }

    // tab complete
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("arena");
            list.add("hologram");
            list.add("setlobby");
            list.add("help");
            list.add("reload");
            return list;
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("arena")) {
                List<String> list = new ArrayList<>();
                list.add("create");
                list.add("delete");
                list.add("setSpawn");
                list.add("setSpawnSpectator");
                list.add("setLobby");
                list.add("setPlayersPerTeam");
                list.add("addTeam");
                list.add("removeTeam");
                list.add("teams");
                list.add("addGenerator");
                list.add("removeGenerator");
                list.add("generators");
                return list;
            }
            if (args[0].equalsIgnoreCase("hologram")) {
                List<String> list = new ArrayList<>();
                list.add("tphere");
                list.add("set");
                list.add("remove");
                return list;
            }
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("arena")) {
                if (args[1].equalsIgnoreCase("create") || args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("setSpawn") || args[1].equalsIgnoreCase("setSpawnSpectator") || args[1].equalsIgnoreCase("setLobby") || args[1].equalsIgnoreCase("setPlayersPerTeam") || args[1].equalsIgnoreCase("addTeam") || args[1].equalsIgnoreCase("removeTeam") || args[1].equalsIgnoreCase("teams") || args[1].equalsIgnoreCase("addGenerator") || args[1].equalsIgnoreCase("removeGenerator") || args[1].equalsIgnoreCase("generators")) {
                    List<String> list = new ArrayList<>();
                    /*for (Arena arena : Main.getInstance().getArenaManager().getArenas()) {
                        list.add(arena.getName());
                    }*/
                    return list;
                }
            }
            if (args[0].equalsIgnoreCase("hologram")) {
                if (args[1].equalsIgnoreCase("teams") || args[1].equalsIgnoreCase("addGenerator") || args[1].equalsIgnoreCase("removeGenerator") || args[1].equalsIgnoreCase("generators")) {
                    List<String> list = new ArrayList<>();
                    /*for (Arena arena : Main.getInstance().getArenaManager().getArenas()) {
                        list.add(arena.getName());
                    }*/
                    return list;
                }
            }
        }
        return null;
    }
}
