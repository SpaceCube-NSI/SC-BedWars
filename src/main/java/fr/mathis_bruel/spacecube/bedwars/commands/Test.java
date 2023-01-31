package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Generator;
import fr.mathis_bruel.spacecube.bedwars.game.GeneratorType;
import fr.mathis_bruel.spacecube.bedwars.game.Team;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // create arena test
        Player player = (Player) sender;
        Generator ironSpawner = new Generator(GeneratorType.IRON, 1, player.getLocation());
        Generator goldSpawner = new Generator(GeneratorType.GOLD, 1, player.getLocation());
        Team team = new Team("Test", Color.RED, player.getLocation(), ironSpawner, goldSpawner, player.getLocation(), player.getLocation(), player.getLocation().getBlock());
        Team team2 = new Team("Test2", Color.BLUE, player.getLocation(), ironSpawner, goldSpawner, player.getLocation(), player.getLocation(), player.getLocation().getBlock());
        Team team3 = new Team("Test3", Color.GREEN, player.getLocation(), ironSpawner, goldSpawner, player.getLocation(), player.getLocation(), player.getLocation().getBlock());
        Team team4 = new Team("Test4", Color.YELLOW, player.getLocation(), ironSpawner, goldSpawner, player.getLocation(), player.getLocation(), player.getLocation().getBlock());
        ArrayList<Team> teams = new ArrayList<Team>();
        teams.add(team);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);
        Generator emerald = new Generator(GeneratorType.EMERALD, 1, player.getLocation());
        Generator diamond = new Generator(GeneratorType.DIAMOND, 1, player.getLocation());
        ArrayList<Generator> emeralds = new ArrayList<Generator>();
        emeralds.add(emerald);
        ArrayList<Generator> diamonds = new ArrayList<Generator>();
        diamonds.add(diamond);

        Arena test = new Arena(player.getWorld(), "Test", 4, 2, teams, emeralds, diamonds, player.getLocation(), player.getLocation(),player.getLocation(), player.getLocation());
        test.save();
        return false;
    }
}
