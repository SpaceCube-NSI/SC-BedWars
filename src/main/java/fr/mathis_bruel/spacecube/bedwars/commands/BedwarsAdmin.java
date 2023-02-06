package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.manager.Utils;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
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
                    sender.sendMessage(prefix + "§c/bedwars-a arena list §7 - §fList all the arenas");
                    sender.sendMessage(prefix + "§c/bedwars-a arena infos <arena> §7 - §fShow infos about an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena create <arenaName> §7 - §fCreate an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena delete <arenaName> §7 - §fDelete an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena setSpawnSpectator <arenaName> §7 - §fSet the spectator spawn of an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena setLobby <arenaName> §7 - §fSet the lobby of an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena setPlayersPerTeam <arenaName> <playersPerTeam> §7 - §fSet the players per team of an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena addTeam <arenaName> <color> §7 - §fAdd a team to an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena removeTeam <arenaName> §7 - §fRemove a team from an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena teams <arenaName> §7 - §fList all the teams of an arena");
                    sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> setColor <teamName> <color> §7 - §fSet the color of a team");
                    sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> setSpawn <teamName> §7 - §fSet the spawn of a team");
                    sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> setBed <teamName> §7 - §fSet the bed of a team (target block)");
                    sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> setShopItems <teamName> §7 - §fSet the shop items of a team");
                    sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> setShopUpgrades <teamName> §7 - §fSet the shop upgrades of a team");
                    sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> addGenerator <teamName> §7 - §fAdd a generator to a team");
                    sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> removeGenerator <teamName> §7 - §fRemove a generator from a team");
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
                    case "list":
                        sender.sendMessage(prefix + "§cArenas:");
                        sender.sendMessage("--------------------------------");
                        sender.sendMessage(prefix + "§cList of arenas:");
                        for (Arena arena : Main.getInstance().arenas) {
                            sender.sendMessage(prefix + "§c- §f" + arena.getName());

                        }
                        sender.sendMessage("--------------------------------");
                        break;
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
                        sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> setColor <teamName> <color> §7 - §fSet the color of a team");
                        sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> setSpawn <teamName> §7 - §fSet the spawn of a team");
                        sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> setBed <teamName> §7 - §fSet the bed of a team (target block)");
                        sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> setShopItems <teamName> §7 - §fSet the shop items of a team");
                        sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> setShopUpgrades <teamName> §7 - §fSet the shop upgrades of a team");
                        sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> addGenerator <teamName> §7 - §fAdd a generator to a team");
                        sender.sendMessage(prefix + "§c/bedwars-a arena team <arenaName> removeGenerator <teamName> §7 - §fRemove a generator from a team");
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
                case "infos": {
                    Arena arena = Arena.getArenaByName(args[2]);
                    if (arena == null) {
                        sender.sendMessage(prefix + "§cThis arena doesn't exist.");
                        return true;
                    }
                    // PlayerPerTeam, TeamsSize, CoordsLobby, CoordsSpec, EmeraldsGeneratorSize, DiamondGeneratorSize
                    // all coords was clickable and teleport the player to the coords
                    TextComponent lobby = new TextComponent(" §7(§5TP to lobby§7)");
                    TextComponent lobby2 = new TextComponent();
                    if (arena.getLobbySpawn() != null)
                        lobby2 = new TextComponent(prefix + "§cLobby: §f" + arena.getLobbySpawn().getBlockX() + " " + arena.getLobbySpawn().getBlockY() + " " + arena.getLobbySpawn().getBlockZ() + " " + arena.getLobbySpawn().getYaw() + " " + arena.getLobbySpawn().getPitch());
                    lobby.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to teleport to the lobby").create()));
                    if (arena.getLobbySpawn() != null)
                        lobby.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + arena.getLobbySpawn().getX() + " " + arena.getLobbySpawn().getY() + " " + arena.getLobbySpawn().getZ() + " " + arena.getLobbySpawn().getYaw() + " " + arena.getLobbySpawn().getPitch()));

                    TextComponent spec = new TextComponent(" §7(§5TP to spec§7)");
                    TextComponent spec2 = new TextComponent();
                    if (arena.getSpecSpawn() != null)
                        spec2 = new TextComponent(prefix + "§cSpec: §f" + arena.getSpecSpawn().getBlockX() + " " + arena.getSpecSpawn().getBlockY() + " " + arena.getSpecSpawn().getBlockZ());
                    spec.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to teleport to the spec").create()));
                    if (arena.getSpecSpawn() != null)
                        spec.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + arena.getSpecSpawn().getX() + " " + arena.getSpecSpawn().getY() + " " + arena.getSpecSpawn().getZ()));
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena infos command:");
                    sender.sendMessage(prefix + "§cName: §f" + arena.getName());
                    sender.sendMessage(prefix + "§cWorld: §f" + arena.getWorld().getName());
                    sender.sendMessage(prefix + "§cPlayers per team: §f" + arena.getPlayerPerTeam());
                    sender.sendMessage(prefix + "§cTeams: §f" + arena.getTeams().size());
                    if (arena.getLobbySpawn() != null) {
                        if (sender instanceof Player) {
                            player.spigot().sendMessage(lobby2, lobby);
                        } else {
                            sender.sendMessage(prefix + "§cLobby: §f" + arena.getLobbySpawn().getX() + " " + arena.getLobbySpawn().getY() + " " + arena.getLobbySpawn().getZ());
                        }

                    }
                    if (arena.getSpecSpawn() != null) {
                        if (sender instanceof Player) {
                            player.spigot().sendMessage(spec2, spec);
                        } else {
                            sender.sendMessage(prefix + "§cSpec: §f" + arena.getSpecSpawn().getX() + " " + arena.getSpecSpawn().getY() + " " + arena.getSpecSpawn().getZ());
                        }
                    }
                    sender.sendMessage(prefix + "§cEmeralds generator size: §f" + arena.getEmeraldsGenerators().size());
                    sender.sendMessage(prefix + "§cDiamonds generator size: §f" + arena.getDiamondsGenerators().size());
                    sender.sendMessage("--------------------------------");
                    break;
                }

                case "create": {
                    Arena arena = new Arena(player.getWorld(), args[2]);
                    arena.save();
                    sender.sendMessage(prefix + "§aArena created.");
                    break;
                }
                case "delete": {
                    Arena arena = Arena.getArenaByName(args[2]);
                    if (arena == null) {
                        sender.sendMessage(prefix + "§cThis arena doesn't exist.");
                        return true;
                    }
                    arena.delete();
                    sender.sendMessage(prefix + "§aArena deleted.");
                    break;
                }
                case "setspawnspectator": {
                    Arena arena = Arena.getArenaByName(args[2]);
                    if (arena == null) {
                        sender.sendMessage(prefix + "§cThis arena doesn't exist.");
                        return true;
                    }
                    arena.setSpecSpawn(player.getLocation());
                    arena.save();
                    sender.sendMessage(prefix + "§aSpawn spectator set.");
                    break;
                }
                case "setlobby": {
                    Arena arena = Arena.getArenaByName(args[2]);
                    if (arena == null) {
                        sender.sendMessage(prefix + "§cThis arena doesn't exist.");
                        return true;
                    }
                    arena.setLobbySpawn(player.getLocation());
                    arena.save();
                    sender.sendMessage(prefix + "§aSpawn set.");
                    break;
                }
                case "setplayersperteam": {
                    Arena arena = Arena.getArenaByName(args[2]);
                    if (arena == null) {
                        sender.sendMessage(prefix + "§cThis arena doesn't exist.");
                        return true;
                    }
                    // is number
                    if (!Utils.isNumber(args[3])) {
                        sender.sendMessage(prefix + "§cThis is not a number.");
                        return true;
                    }
                    arena.setPlayerPerTeam(Integer.parseInt(args[3]));
                    arena.save();
                    sender.sendMessage(prefix + "§aPlayers per team set.");
                    break;
                }
                case "addteam":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena addTeam command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena addTeam <arenaName> <teamName> <color> §7 - §fAdd a team to an arena");
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

        if (args.length == 4) {
            switch (args[1].toLowerCase()) {
                case "addteam":
                    sender.sendMessage(prefix + "§cBedwarsAdmin arena addTeam command:");
                    sender.sendMessage(prefix + "§c/bedwars-a arena addTeam <arenaName> <teamName> <color> §7 - §fAdd a team to an arena");
                    break;
                default:
                    sender.sendMessage(prefix + "§cUnknown command. Type §f/bedwars-a help §cfor help.");
                    break;
            }
        }
        if (args.length == 5) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + "§cYou must be a player to use this command.");
                return true;
            }
            Player player = (Player) sender;

            switch (args[1].toLowerCase()) {
                case "addteam": {
                    Arena arena = Arena.getArenaByName(args[2]);

                    if (arena == null) {
                        sender.sendMessage(prefix + "§cThis arena doesn't exist.");
                        return true;
                    }
                    ChatColor color = Utils.getColor(args[4].toUpperCase());
                    if (color == null) {
                        sender.sendMessage(prefix + "§cThis color doesn't exist.");
                        //send colors list with color in chat
                        sender.sendMessage(prefix + "§cColors list:");
                        for (ChatColor chatColor : Utils.getAllColor()) {
                            sender.sendMessage(prefix + Utils.getColorString(chatColor) + chatColor.name());
                        }
                        return true;
                    }
                    Team team = new Team(args[3], color);
                    arena.addTeam(team);
                    arena.save();
                    sender.sendMessage(prefix + "§aTeam added.");
                    break;
                }
                case "team": {
                    switch (args[3].toLowerCase()) {
                        Arena arena = Arena.getArenaByName(args[2]);
                        case "setspawn": {
                            Team team = arena.getTeamByName(args[4]);
                            if (team == null) {
                                sender.sendMessage(prefix + "§cThis team doesn't exist.");
                                return true;
                            }
                            team.setSpawn(player.getLocation());
                            arena.save();
                            sender.sendMessage(prefix + "§aSpawn set.");
                            break;
                        }
                        case "setbed": {

                            Team team = arena.getTeamByName(args[4]);
                            if (team == null) {
                                sender.sendMessage(prefix + "§cThis team doesn't exist.");
                                return true;
                            }
                            team.setBed(player.getTargetBlock());
                            arena.save();
                            sender.sendMessage(prefix + "§aBed set.");
                            break;
                        }
                        case "setshop": {
                            if (arena == null) {
                                sender.sendMessage(prefix + "§cThis arena doesn't exist.");
                                return true;
                            }
                            Team team = arena.getTeamByName(args[4]);
                            if (team == null) {
                                sender.sendMessage(prefix + "§cThis team doesn't exist.");
                                return true;
                            }
                            team.setShop(player.getLocation());
                            arena.save();
                            sender.sendMessage(prefix + "§aShop set.");
                            break;
                        }
                        default:
                            sender.sendMessage(prefix + "§cUnknown command. Type §f/bedwars-a help §cfor help.");
                            break;
                    }
                }
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
