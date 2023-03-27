package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.manager.NPCManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            EntityType entityType = EntityType.PLAYER;
            String npcName = "FakePlayer";

            try {
                NPCManager npcManager = new NPCManager(location, entityType, npcName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            player.sendMessage("Le PNJ " + npcName + " a été créé à votre emplacement.");

            return true;
        } else {
            sender.sendMessage("Cette commande doit être exécutée par un joueur.");
            return false;
        }
    }
}
