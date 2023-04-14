package fr.mathis_bruel.spacecube.bedwars.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            return true;
        } else {
            sender.sendMessage("Cette commande doit être exécutée par un joueur.");
            return false;
        }
    }
}
