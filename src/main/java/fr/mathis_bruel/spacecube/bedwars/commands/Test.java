package fr.mathis_bruel.spacecube.bedwars.commands;

import com.sk89q.worldedit.WorldEditException;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Arena arena = Arena.getArenaByName("Rome");
/*
        System.out.println(arena.getPos1Map().getBlockX() + " " + arena.getPos1Map().getBlockY() + " " + arena.getPos1Map().getBlockZ());
        System.out.println(arena.getPos2Map().getBlockX() + " " + arena.getPos2Map().getBlockY() + " " + arena.getPos2Map().getBlockZ());
        try {
            Utils.saveSchem("test2", arena.getPos1Map().getBlockX(), arena.getPos1Map().getBlockY(), arena.getPos1Map().getBlockZ(), arena.getPos2Map().getBlockX(), arena.getPos2Map().getBlockY(), arena.getPos2Map().getBlockZ(), player.getWorld());
        } catch (WorldEditException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
*/
        try {
            Utils.restoreMap("test2", player.getLocation().getWorld(), arena.getPos1Map().getBlockX(), arena.getPos1Map().getBlockY(), arena.getPos1Map().getBlockZ());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WorldEditException e) {
            e.printStackTrace();
        }


        return true;
    }
}
