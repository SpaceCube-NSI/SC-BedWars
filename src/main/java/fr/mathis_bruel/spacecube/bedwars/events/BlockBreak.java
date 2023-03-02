package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        System.out.println("BlockBreakEvent");
        if(!Manager.isCurrentlyInGame(event.getPlayer()) && !event.getPlayer().hasPermission("bw.admin")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§cYou can't break blocks in this world!");
        }
        if(Main.isGodMode(event.getPlayer()))
            Main.removeGodMode(event.getPlayer());
        System.out.println("BlockBreakEvent2");
        if(Manager.isCurrentlyInGame(event.getPlayer())) {
            Manager manager = Manager.getManager(event.getPlayer());
            if(manager == null)
                return;
            Arena arena = manager.getArena();
            if(arena == null)
                return;
            //System.out.println(manager.getBlocksNotBreakable());
            if(manager.isBlockNotBreakable(event.getBlock())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cYou can't break this block!");
            }
        }
    }

}
