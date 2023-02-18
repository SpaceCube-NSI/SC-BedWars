package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopItems;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class Click implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getItem() != null ) {
            if(event.getItem().getItemMeta().getDisplayName() != null) {
                String name = event.getItem().getItemMeta().getDisplayName();
                switch (name) {
                    case "§4Leave game":
                        event.getPlayer().performCommand("bw leave");
                        return;
                    case "§6Your stats":
                        event.getPlayer().performCommand("bw stats");
                        return;
                    case "§6Spectator Settings":
                        event.getPlayer().sendMessage(Main.getPrefix() + "§fIs currently in development");
                        return;
                }
            }
        }
        System.out.println(1);
        List<Entity> list = event.getPlayer().getNearbyEntities(10, 10, 10);
        System.out.println(event.getPlayer().getNearbyEntities(10, 10, 10));
        Entity entity = list.get(0);
        System.out.println(1);
        Player player = event.getPlayer();
        Team team = Manager.getManager(player).getTeam(player);
        if (team == null) return;
        System.out.println(1);
        if (entity instanceof Player) {
            System.out.println(1);
            EntityPlayer entityPlayer = (EntityPlayer) entity;
            if (entityPlayer.getCustomName().equals("§a§l" + "Items")) {
                player.openInventory(ShopItems.getInventory(team));
            }
            if (entityPlayer.getCustomName().equals("§a§l" + "Upgrades")) {
                player.openInventory(ShopItems.getInventory(team));
            }
        }
    }

}
