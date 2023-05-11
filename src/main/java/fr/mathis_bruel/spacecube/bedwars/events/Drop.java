package fr.mathis_bruel.spacecube.bedwars.events;

import org.bukkit.Material;

public class Drop implements org.bukkit.event.Listener {

    public void onDrop(org.bukkit.event.player.PlayerDropItemEvent event) {
        if (event.getItemDrop() == null) return;
        if (event.getItemDrop().getItemStack().getItemMeta() == null) return;
        if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName() == null) return;
        if (event.getItemDrop().getType().equals(Material.BED)) {
            event.setCancelled(true);
        }
        if (event.getItemDrop().getType().equals(Material.SKULL_ITEM)) {
            if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName() == "§6Your stats") {
                event.setCancelled(true);
            }
        }
        if (event.getItemDrop().getType().equals(Material.ARMOR_STAND)) {
            if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName() == "§6Select your team") {
                event.setCancelled(true);
            }
        }
    }

}
