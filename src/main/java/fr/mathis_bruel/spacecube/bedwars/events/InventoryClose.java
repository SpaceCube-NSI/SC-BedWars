package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.gui.ShopSpeEnchanter;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements org.bukkit.event.Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if(event.getInventory().getName().equals(ShopSpeEnchanter.getInventory().getName())){
            if (event.getInventory().getItem(10) != null && event.getInventory().getItem(10).getType() != Material.AIR) {
                if (Utils.canAddItemInInventory((Player) event.getPlayer(), event.getInventory().getItem(10)))
                    event.getPlayer().getInventory().addItem(event.getInventory().getItem(10));
                else
                    event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), event.getInventory().getItem(10));
            }
            if (event.getInventory().getItem(16) != null && event.getInventory().getItem(16).getType() != Material.AIR) {
                if (Utils.canAddItemInInventory((Player) event.getPlayer(), event.getInventory().getItem(16)))
                    event.getPlayer().getInventory().addItem(event.getInventory().getItem(16));
                else
                    event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), event.getInventory().getItem(16));
            }
        }
    }
}
