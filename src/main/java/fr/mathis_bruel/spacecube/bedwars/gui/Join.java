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
        Inventory inv = Bukkit.createInventory(null, 9*4, "§6Join game");
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
        for (int i = 26; i <36; i++){
            inv.setItem(i, glass);
        }


        // create 4 item stack for Solo, Doubles, 3v3v3v3, 4v4v4v4 and all maps
        ItemStack solo = new ItemStack(Material.BED);
        ItemMeta soloMeta = solo.getItemMeta();
        soloMeta.setDisplayName("§6Solo");
        soloMeta.setLore(Arrays.asList("Click for join solo game", "And choice the map"));
        solo.setItemMeta(soloMeta);
        inv.setItem(11, solo);
        ItemStack doubles = new ItemStack(Material.BED);
        ItemMeta doublesMeta = doubles.getItemMeta();
        doublesMeta.setDisplayName("§6Doubles");
        doublesMeta.setLore(Arrays.asList("Click for join doubles game", "And choice the map"));
        doubles.setItemMeta(doublesMeta);
        inv.setItem(21, doubles);
        ItemStack three = new ItemStack(Material.BED);
        ItemMeta threeMeta = three.getItemMeta();
        threeMeta.setDisplayName("§63v3v3v3");
        threeMeta.setLore(Arrays.asList("Click for join 3v3v3v3 game", "And choice the map"));
        three.setItemMeta(threeMeta);
        inv.setItem(23, three);
        ItemStack four = new ItemStack(Material.BED);
        ItemMeta fourMeta = four.getItemMeta();
        fourMeta.setDisplayName("§64v4v4v4");
        fourMeta.setLore(Arrays.asList("Click for join 4v4v4v4 game", "And choice the map"));
        four.setItemMeta(fourMeta);
        inv.setItem(15, four);
        ItemStack all = new ItemStack(Material.BUCKET);
        ItemMeta allMeta = all.getItemMeta();
        allMeta.setDisplayName("§6All maps");
        allMeta.setLore(Arrays.asList("Click for join a game", "And choice the map"));
        all.setItemMeta(allMeta);
        inv.setItem(13, all);

        inv.setItem(35, back);
        inv.setItem(27, close);
        inv.setItem(17, glass);
        inv.setItem(18, glass);



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
        if(currentName.equals("§6Solo")){
            player.closeInventory();
            player.openInventory(JoinChoiceSolo.getInventory());
            return;
        }
        if(currentName.equals("§6Doubles")){
            player.closeInventory();
            player.openInventory(JoinChoiceDouble.getInventory());
            return;
        }
        if(currentName.equals("§63v3v3v3")){
            player.closeInventory();
            player.openInventory(JoinChoiceTriple.getInventory());
            return;
        }
        if(currentName.equals("§64v4v4v4")){
            player.closeInventory();
            player.openInventory(JoinChoiceQuatre.getInventory());
            return;
        }
        if(currentName.equals("§6All maps")){
            player.closeInventory();
            player.openInventory(JoinChoice.getInventory());
            return;
        }


        return;


    }
}
