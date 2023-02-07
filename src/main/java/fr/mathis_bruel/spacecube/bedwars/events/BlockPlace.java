package fr.mathis_bruel.spacecube.bedwars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements org.bukkit.event.Listener {

    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent event) {
        /*Block block = event.getBlock();
        Player player = event.getPlayer();
        if (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
            Sign sign = (Sign) block;
            // if contains [bw] in the first line
            if (sign.getLine(0).equalsIgnoreCase("[bw]")) {
                String line2 = sign.getLine(1);
                if (line2 == null) {
                    player.sendMessage(Main.getPrefix() + "§cErreur: §fThe second line is empty!");
                    event.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 1);
                    return;
                }
                Arena arena = Arena.getArenaByName(line2);
                if (arena == null) {
                    player.sendMessage(Main.getPrefix() + "§cErreur: §fThe arena §e" + line2 + " §fdoesn't exist!");
                    event.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 1);
                    return;
                }


            }
        }*/
    }

}
