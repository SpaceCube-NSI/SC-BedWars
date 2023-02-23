package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.manager.Hologram;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Location location = player.getLocation().add(0, 1.5, 0); // ajouter une hauteur pour que l'hologramme soit au-dessus de la tête du joueur
        List<String> lines = new ArrayList<>();
        lines.add(player.getName());
        lines.add("Niveau : " + player.getLevel());
        Hologram hologram = new Hologram(location, lines);
        hologram.showHologram();
        player.sendMessage("Hologramme créé !");
        return true;
    }
}
