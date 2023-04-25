package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(Manager.isCurrentlyInGame(player)){
                player.sendMessage("§cYou can't teleport to spawn while in game!");
                return false;
            }
            player.teleport(Utils.parseStringToLoc(Main.getInstance().getConfig().getString("lobby")));
            if(player.getGameMode() == GameMode.SPECTATOR) player.setGameMode(GameMode.SURVIVAL);
        }else sender.sendMessage("§cYou must be a player to execute this command!");

        return false;
    }
}
