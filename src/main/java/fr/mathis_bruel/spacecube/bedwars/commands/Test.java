package fr.mathis_bruel.spacecube.bedwars.commands;

import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Arena arena = Manager.getManager(player).getArena();
        /*Vector pos1 = Vector.toBlockPoint(arena.getPos1Map().getX(), arena.getPos1Map().getY(), arena.getPos1Map().getZ());
        Vector pos2 = Vector.toBlockPoint(arena.getPos2Map().getX(), arena.getPos2Map().getY(), arena.getPos2Map().getZ());
        /*LocalWorld world = (LocalWorld) player.getWorld();
        CuboidRegion region = new CuboidRegion(world, pos1, pos2);
        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);

        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(region.getWorld(), -1);

        ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());
        forwardExtentCopy.setCopyingEntities(true)

        try {
            Operations.complete(forwardExtentCopy);
        } catch (WorldEditException e) {
            e.printStackTrace();
        }*/
        // save the schematic
        System.out.println(arena.getPos1Map().getBlockX() + " " + arena.getPos1Map().getBlockY() + " " + arena.getPos1Map().getBlockZ());
        System.out.println(arena.getPos2Map().getBlockX() + " " + arena.getPos2Map().getBlockY() + " " + arena.getPos2Map().getBlockZ());
        Utils.saveSchem("test", arena.getPos1Map().getBlockX(), arena.getPos1Map().getBlockY(), arena.getPos1Map().getBlockZ(), arena.getPos2Map().getBlockX(), arena.getPos2Map().getBlockY(), arena.getPos2Map().getBlockZ(), player.getWorld());


        return true;
    }
}
