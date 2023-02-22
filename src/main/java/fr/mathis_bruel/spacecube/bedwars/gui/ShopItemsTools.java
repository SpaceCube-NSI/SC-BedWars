package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ShopItemsTools {

    public static Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, 9 * 6, "§6§lShop des outils");
        ItemStack glass = new ItemStack(160, 1, (short) 4);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        ItemStack close = new ItemStack(Material.BARRIER, 1, (short) 14);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cClose");
        close.setItemMeta(closeMeta);
        ItemStack back = new ItemStack(Material.ARROW, 1);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§5Back");
        back.setItemMeta(backMeta);
        // Sword
        ItemStack stoneSword = new ItemStack(Material.STONE_SWORD);
        ItemMeta stoneSwordMeta = stoneSword.getItemMeta();
        stoneSwordMeta.setDisplayName("§6Stone Sword");
        stoneSwordMeta.setLore(Arrays.asList("§7Price: §a5 irons", "§7Amount: §a1"));
        stoneSword.setItemMeta(stoneSwordMeta);
        ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
        ItemMeta ironSwordMeta = ironSword.getItemMeta();
        ironSwordMeta.setDisplayName("§6Iron Sword");
        ironSwordMeta.setLore(Arrays.asList("§7Price: §a10 gold", "§7Amount: §a1"));
        ironSword.setItemMeta(ironSwordMeta);
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta diamondSwordMeta = diamondSword.getItemMeta();
        diamondSwordMeta.setDisplayName("§6Diamond Sword");
        diamondSwordMeta.setLore(Arrays.asList("§7Price: §a5 emeralds", "§7Amount: §a1"));
        diamondSword.setItemMeta(diamondSwordMeta);
        // Pickaxe
        ItemStack stonePickaxe = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta stonePickaxeMeta = stonePickaxe.getItemMeta();
        stonePickaxeMeta.setDisplayName("§6Stone Pickaxe");
        stonePickaxeMeta.setLore(Arrays.asList("§7Price: §a10 irons", "§7Amount: §a1"));
        stonePickaxe.setItemMeta(stonePickaxeMeta);
        ItemStack ironPickaxe = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta ironPickaxeMeta = ironPickaxe.getItemMeta();
        ironPickaxeMeta.setDisplayName("§6Iron Pickaxe");
        ironPickaxeMeta.setLore(Arrays.asList("§7Price: §a10 gold", "§7Amount: §a1"));
        ironPickaxe.setItemMeta(ironPickaxeMeta);
        ItemStack diamondPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta diamondPickaxeMeta = diamondPickaxe.getItemMeta();
        diamondPickaxeMeta.setDisplayName("§6Diamond Pickaxe");
        diamondPickaxeMeta.setLore(Arrays.asList("§7Price: §a2 emeralds", "§7Amount: §a1"));
        diamondPickaxe.setItemMeta(diamondPickaxeMeta);
        // Axe
        ItemStack stoneAxe = new ItemStack(Material.STONE_AXE);
        ItemMeta stoneAxeMeta = stoneAxe.getItemMeta();
        stoneAxeMeta.setDisplayName("§6Stone Axe");
        stoneAxeMeta.setLore(Arrays.asList("§7Price: §a5 irons", "§7Amount: §a1"));
        stoneAxe.setItemMeta(stoneAxeMeta);
        ItemStack ironAxe = new ItemStack(Material.IRON_AXE);
        ItemMeta ironAxeMeta = ironAxe.getItemMeta();
        ironAxeMeta.setDisplayName("§6Iron Axe");
        ironAxeMeta.setLore(Arrays.asList("§7Price: §a5 gold", "§7Amount: §a1"));
        ironAxe.setItemMeta(ironAxeMeta);
        ItemStack diamondAxe = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta diamondAxeMeta = diamondAxe.getItemMeta();
        diamondAxeMeta.setDisplayName("§6Diamond Axe");
        diamondAxeMeta.setLore(Arrays.asList("§7Price: §a15 diamonds", "§7Amount: §a1"));
        diamondAxe.setItemMeta(diamondAxeMeta);

        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName("§6Bow");
        bowMeta.setLore(Arrays.asList("§7Price: §a20 diamonds", "§7Amount: §a1"));
        bow.setItemMeta(bowMeta);

        ItemStack arrow = new ItemStack(Material.ARROW, 10);
        ItemMeta arrowMeta = arrow.getItemMeta();
        arrowMeta.setDisplayName("§6Arrow");
        arrowMeta.setLore(Arrays.asList("§7Price: §a20 iron", "§7Amount: §a10"));
        arrow.setItemMeta(arrowMeta);

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName("§6Compass");
        compassMeta.setLore(Arrays.asList("§7Price: ?", "§7Amount: §a1", "§7Description: §aTo find a player"));
        compass.setItemMeta(compassMeta);

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
        inv.setItem(45, close);
        inv.setItem(53, back);
        inv.setItem(11, stoneSword);
        inv.setItem(13, ironSword);
        inv.setItem(15, diamondSword);
        inv.setItem(20, stonePickaxe);
        inv.setItem(22, ironPickaxe);
        inv.setItem(24, diamondPickaxe);
        inv.setItem(29, stoneAxe);
        inv.setItem(31, ironAxe);
        inv.setItem(33, diamondAxe);
        inv.setItem(21, bow);
        inv.setItem(23, arrow);
        inv.setItem(40, compass);

        return inv;
    }


    public static void execute(InventoryClickEvent event) {
        if(!Manager.isCurrentlyInGame((Player) event.getWhoClicked())) return;
        Team team = Manager.getManager((Player) event.getWhoClicked()).getTeam((Player) event.getWhoClicked());

        event.setCancelled(true);
        if(event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getType() == Material.AIR) return;
        if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) return;
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cClose")) {
            event.getWhoClicked().closeInventory();
            return;
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§5Back")) {
            event.getWhoClicked().openInventory(ShopItems.getInventory(team));
            return;
        }

    }
}
