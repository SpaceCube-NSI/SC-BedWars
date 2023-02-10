package fr.mathis_bruel.spacecube.bedwars.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        stoneSword.setItemMeta(stoneSwordMeta);
        ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
        ItemMeta ironSwordMeta = ironSword.getItemMeta();
        ironSwordMeta.setDisplayName("§6Iron Sword");
        ironSword.setItemMeta(ironSwordMeta);
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta diamondSwordMeta = diamondSword.getItemMeta();
        diamondSwordMeta.setDisplayName("§6Diamond Sword");
        diamondSword.setItemMeta(diamondSwordMeta);
        // Pickaxe
        ItemStack stonePickaxe = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta stonePickaxeMeta = stonePickaxe.getItemMeta();
        stonePickaxeMeta.setDisplayName("§6Stone Pickaxe");
        stonePickaxe.setItemMeta(stonePickaxeMeta);
        ItemStack ironPickaxe = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta ironPickaxeMeta = ironPickaxe.getItemMeta();
        ironPickaxeMeta.setDisplayName("§6Iron Pickaxe");
        ironPickaxe.setItemMeta(ironPickaxeMeta);
        ItemStack diamondPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta diamondPickaxeMeta = diamondPickaxe.getItemMeta();
        diamondPickaxeMeta.setDisplayName("§6Diamond Pickaxe");
        diamondPickaxe.setItemMeta(diamondPickaxeMeta);
        // Axe
        ItemStack stoneAxe = new ItemStack(Material.STONE_AXE);
        ItemMeta stoneAxeMeta = stoneAxe.getItemMeta();
        stoneAxeMeta.setDisplayName("§6Stone Axe");
        stoneAxe.setItemMeta(stoneAxeMeta);
        ItemStack ironAxe = new ItemStack(Material.IRON_AXE);
        ItemMeta ironAxeMeta = ironAxe.getItemMeta();
        ironAxeMeta.setDisplayName("§6Iron Axe");
        ironAxe.setItemMeta(ironAxeMeta);
        ItemStack diamondAxe = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta diamondAxeMeta = diamondAxe.getItemMeta();
        diamondAxeMeta.setDisplayName("§6Diamond Axe");
        diamondAxe.setItemMeta(diamondAxeMeta);

        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName("§6Bow");
        bow.setItemMeta(bowMeta);

        ItemStack arrow = new ItemStack(Material.ARROW, 10);
        ItemMeta arrowMeta = arrow.getItemMeta();
        arrowMeta.setDisplayName("§6Arrow");
        arrow.setItemMeta(arrowMeta);

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName("§6Compass");
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

}
