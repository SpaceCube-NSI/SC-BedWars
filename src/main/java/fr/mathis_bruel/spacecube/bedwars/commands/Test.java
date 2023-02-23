package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Team team = Manager.getManager(player).getTeam(player);
        player.teleport(team.getSpawn());
        return true;
    }
}
