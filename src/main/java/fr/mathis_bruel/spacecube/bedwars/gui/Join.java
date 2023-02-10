package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Join {

    public static Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, 9*3, "§6Join game");
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

        ItemStack randomChoice = new ItemStack(Material.BED);
        ItemMeta randomChoiceMeta = randomChoice.getItemMeta();
        randomChoiceMeta.setDisplayName("§6Join a random game");
        randomChoiceMeta.setLore(Arrays.asList("CLick for join a random game", "In a random map"));
        randomChoice.setItemMeta(randomChoiceMeta);
        inv.setItem(12, randomChoice);
        ItemStack choice = new ItemStack(Material.MAP);
        ItemMeta choiceMeta = choice.getItemMeta();
        choiceMeta.setDisplayName("§aSelect a game");
        choiceMeta.setLore(Arrays.asList("Click for join a game", "and select your map"));
        choice.setItemMeta(choiceMeta);
        inv.setItem(14, choice);


        return inv;

    }

    public static void execute(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);
        ItemStack current = e.getCurrentItem();
        if(current.getType() == Material.AIR) return;
        String currentName = current.getItemMeta().getDisplayName();


        if(currentName.equals("§6Join a random game")){
            player.closeInventory();
            player.sendMessage(Main.getPrefix() + "Is currently in development.");
            return;
        }
        if(currentName.equals("§aSelect a game")){
            player.closeInventory();
            player.openInventory(JoinChoice.getInventory());
            return;
        }

        return;


    }
}
