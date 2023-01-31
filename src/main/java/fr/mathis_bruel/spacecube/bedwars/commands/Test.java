package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test extends Command {
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        // create arena test
        Player player = (Player) sender;
        Arena test = new Arena(player.getWorld());

        return false;
    }
}
