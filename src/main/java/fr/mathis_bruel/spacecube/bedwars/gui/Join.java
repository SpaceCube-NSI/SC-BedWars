package fr.mathis_bruel.spacecube.bedwars.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Join {

    public static Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, 9*6, "§6Join game");
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cClose");
        close.setItemMeta(closeMeta);
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§5Back");
        back.setItemMeta(backMeta);
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemMeta glassmeta = glass.getItemMeta();
        glassmeta.setDisplayName(" ");
        glass.setItemMeta(glassmeta);
        for(int i = 0; i < 10; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 17; i <27; i++){
            inv.setItem(i, glass);
        }


        return inv;

    }

    public static void execute(Player player) {

    }
}
