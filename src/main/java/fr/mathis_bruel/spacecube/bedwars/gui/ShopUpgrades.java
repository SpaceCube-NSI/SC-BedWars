package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ShopUpgrades {

    public static Inventory getInventory(Team team){
        Inventory inv = Bukkit.createInventory(null, 9*3, "§6Shop Upgrades");

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cClose");
        closeMeta.setLore(Arrays.asList("§7Close the shop"));
        close.setItemMeta(closeMeta);

        ItemStack beacon = new ItemStack(Material.BEACON);
        ItemMeta beaconMeta = beacon.getItemMeta();
        beaconMeta.setDisplayName("§6Upgrade Base");
        beaconMeta.setLore(Arrays.asList("§7Upgrade your base with traps, potions effects, etc."));
        beacon.setItemMeta(beaconMeta);
        inv.setItem(12, beacon);

        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemMeta diamondMeta = diamond.getItemMeta();
        diamondMeta.setDisplayName("§6Upgrade Items Summoner");
        diamondMeta.setLore(Arrays.asList("§7Upgrade your items summoner (generators)"));
        diamond.setItemMeta(diamondMeta);
        inv.setItem(14, diamond);

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§5Back");
        backMeta.setLore(Arrays.asList("§7Go back to the shop"));
        back.setItemMeta(backMeta);

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, Utils.getDataColor(team.getColor()));
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        for(int i = 0; i < 9; i++){
            inv.setItem(i, glass);
        }
        inv.setItem(9, glass);
        inv.setItem(17, glass);
        for(int i = 17; i < 27; i++){
            inv.setItem(i, glass);
        }
        inv.setItem(18, close);
        inv.setItem(26, back);



        return inv;


    }

    public static void execute(InventoryClickEvent event) {
        if (!Manager.isCurrentlyInGame((Player) event.getWhoClicked())) return;
        Team team = Manager.getManager((Player) event.getWhoClicked()).getTeam((Player) event.getWhoClicked());

        event.setCancelled(true);
        if (event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cClose")) {
            event.getWhoClicked().closeInventory();
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5Back")) {
            return;
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Upgrade Base")) {
            event.getWhoClicked().closeInventory();
            event.getWhoClicked().sendMessage("§cIn development");
            return;
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Upgrade Items Summoner")) {
            event.getWhoClicked().openInventory(ShopItemsSummoner.getInventory(team));
            return;
        }


    }

}
