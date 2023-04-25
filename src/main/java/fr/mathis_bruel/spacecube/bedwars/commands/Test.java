package fr.mathis_bruel.spacecube.bedwars.commands;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {



            Player player = (Player) sender;
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "test");
            npc.getOrAddTrait(SkinTrait.class).setSkinName("mathis_bruel");
            npc.spawn(player.getLocation());
            return true;
        } else {
            sender.sendMessage("Cette commande doit être exécutée par un joueur.");
            return false;
        }
    }
}
