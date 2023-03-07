package fr.mathis_bruel.spacecube.bedwars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ((Player) sender).playSound(((Player) sender).getLocation(), "minecraft:entity.enderdragon.flap", 1, 1);

        return true;
    }
}
