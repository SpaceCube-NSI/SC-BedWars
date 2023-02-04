package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Bedwars implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = Main.getPrefix();

        if(args.length == 0) {
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
        return false;
    }
}
