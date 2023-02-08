package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopItems;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopItemsArmor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Bedwars implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = Main.getPrefix();

        if (args.length == 0) {
            Player player = (Player) sender;
            player.openInventory(ShopItems.getInventory());

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
                case "list":
                    sender.sendMessage(prefix + "§cArenas:");
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cList of arenas:");
                    for (Arena arena : Main.getInstance().arenas) {
                        sender.sendMessage(prefix + "§c- §f" + arena.getName());

                    }
                    sender.sendMessage("--------------------------------");
                    break;
                case "stats":
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cBedwars stats:");
                    sender.sendMessage(prefix + "§cComing soon!");
                    sender.sendMessage("--------------------------------");
                    break;
                case "top":
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cBedwars top:");
                    sender.sendMessage(prefix + "§cComing soon!");
                    sender.sendMessage("--------------------------------");
                    break;
                case "leave":
                    sender.sendMessage("--------------------------------");
                    sender.sendMessage(prefix + "§cBedwars leave:");
                    sender.sendMessage(prefix + "§cComing soon!");
                    sender.sendMessage("--------------------------------");
                    break;
                case "shopItems":
                    Player player = (Player) sender;
                    player.openInventory(ShopItems.getInventory(Main.getInstance().arenas.get(0).getTeams().get(0)));
                    break;
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
