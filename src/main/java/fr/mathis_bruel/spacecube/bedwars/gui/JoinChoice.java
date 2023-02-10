package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class JoinChoice {

    public static Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, 9 * 6, "§2Choice your game");
        if (Main.getInstance().managers.size() > 21) {
            System.out.println("Too much games");
            return null;
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cClose");
        close.setItemMeta(closeMeta);
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§5Back");
        back.setItemMeta(backMeta);
        ItemStack glass = new ItemStack(160, 1, (short) 7);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 9; i < 54; i += 9) {
            inv.setItem(i, glass);
            inv.setItem(i + 8, glass);
        }
        for (int i = 45; i < 54; i++) {
            inv.setItem(i, glass);
        }
        Main.getInstance().managers.forEach((manager) -> {
            ItemStack item = Utils.getIcon(manager);
            inv.addItem(item);
        });

        ItemStack random = new ItemStack(Material.FIREWORK);
        ItemMeta randomChoiceMeta = random.getItemMeta();
        randomChoiceMeta.setDisplayName("§6Join a random game");
        randomChoiceMeta.setLore(Arrays.asList("CLick for join a random game", "In a random map"));
        random.setItemMeta(randomChoiceMeta);
        inv.setItem(40, random);


        return inv;
    }

    public static void execute() {

    }
}
