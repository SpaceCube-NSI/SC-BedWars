package fr.mathis_bruel.spacecube.bedwars.commands;

import es.eltrueno.npc.TruenoNPC;
import es.eltrueno.npc.TruenoNPCApi;
import es.eltrueno.npc.skin.TruenoNPCSkin;
import es.eltrueno.npc.skin.TruenoNPCSkinBuilder;
import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.manager.TypeShop;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        TruenoNPCSkin skin = TruenoNPCSkinBuilder.fromMineskin(Main.getInstance(), 131234);
        Location location = ((Player) sender).getLocation();
        TruenoNPC npc = TruenoNPCApi.createNPC(Main.getInstance(), location, skin);
        Main.addShop(npc.getNpcID(), TypeShop.ITEMS);
        return false;
    }
}
