package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.Mathis_Bruel.spacecube.spacecubeapi.api.games.Games;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Stats {

    public static Inventory getInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9*4, "§6Stats "+ player.getName());
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
        fr.Mathis_Bruel.spacecube.spacecubeapi.api.games.Stats stats = new fr.Mathis_Bruel.spacecube.spacecubeapi.api.games.Stats(Games.BedWars, player.getUniqueId());
        stats.init();


        // create 6 items : Kills (gold sword), Death (dead bush), Bed destroyed (bed), Win (nether star), Loses (head zombie), Game played (note block)
        ItemStack kills = new ItemStack(Material.GOLD_SWORD);
        ItemMeta killsMeta = kills.getItemMeta();
        killsMeta.setDisplayName("§6Kills");
        killsMeta.setLore(Arrays.asList("§7----------------------", "§7Kills : §e"+stats.getKills(), "§7----------------------"));
        kills.setItemMeta(killsMeta);
        ItemStack death = new ItemStack(Material.DEAD_BUSH);
        ItemMeta deathMeta = death.getItemMeta();
        deathMeta.setDisplayName("§6Deaths");
        deathMeta.setLore(Arrays.asList("§7----------------------", "§7Deaths : §e"+stats.getDeath(), "§7----------------------"));
        death.setItemMeta(deathMeta);
        ItemStack bedDestroyed = new ItemStack(Material.BED);
        ItemMeta bedDestroyedMeta = bedDestroyed.getItemMeta();
        bedDestroyedMeta.setDisplayName("§6Bed destroyed");
        bedDestroyedMeta.setLore(Arrays.asList("§7----------------------", "§7Bed destroyed : §e"+stats.getDivers().get("Beds"), "§7----------------------"));
        bedDestroyed.setItemMeta(bedDestroyedMeta);
        ItemStack win = new ItemStack(Material.NETHER_STAR);
        ItemMeta winMeta = win.getItemMeta();
        winMeta.setDisplayName("§6Win");
        winMeta.setLore(Arrays.asList("§7----------------------", "§7Win : §e"+stats.getWins(), "§7----------------------"));
        win.setItemMeta(winMeta);
        ItemStack lose = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
        ItemMeta loseMeta = lose.getItemMeta();
        loseMeta.setDisplayName("§6Loses");
        loseMeta.setLore(Arrays.asList("§7----------------------", "§7Loses : §e"+stats.getLoses(), "§7----------------------"));
        lose.setItemMeta(loseMeta);
        ItemStack gamePlayed = new ItemStack(Material.NOTE_BLOCK);
        ItemMeta gamePlayedMeta = gamePlayed.getItemMeta();
        gamePlayedMeta.setDisplayName("§6Game played");
        gamePlayedMeta.setLore(Arrays.asList("§7----------------------", "§7Game played : §e"+stats.getGames(), "§7----------------------"));
        gamePlayed.setItemMeta(gamePlayedMeta);

        inv.setItem(12, kills);
        inv.setItem(13, gamePlayed);
        inv.setItem(14, death);
        inv.setItem(20, lose);
        inv.setItem(22, bedDestroyed);
        inv.setItem(24, win);
        //inv.setItem(35, back);
        inv.setItem(27, close);
        inv.setItem(17, glass);
        inv.setItem(18, glass);

        return inv;

    }

    public static void execute(InventoryClickEvent e) {
        e.setCancelled(true);
    }
}
