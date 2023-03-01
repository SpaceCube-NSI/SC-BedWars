package fr.mathis_bruel.spacecube.bedwars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        new ParticleBuilder(ParticleEffect.LAVA, player.getLocation())
                .setOffsetY(1f)
                .setSpeed(0.1f)
                .setAmount(30)
                .display();

        return true;
    }
}
