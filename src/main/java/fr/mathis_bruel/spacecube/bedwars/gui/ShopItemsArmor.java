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

public class ShopItemsArmor {

    public static Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, 9 * 6, "§6Armor");
        ItemStack glass = new ItemStack(160, 1, (short) 10);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cClose");
        close.setItemMeta(closeMeta);
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§5Back");
        back.setItemMeta(backMeta);
        // chainmail
        ItemStack chainmailHelmet = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta chainmailHelmetMeta = chainmailHelmet.getItemMeta();
        chainmailHelmetMeta.setDisplayName("§6Chainmail Helmet");
        chainmailHelmet.setItemMeta(chainmailHelmetMeta);
        ItemStack chainmailChestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta chainmailChestplateMeta = chainmailChestplate.getItemMeta();
        chainmailChestplateMeta.setDisplayName("§6Chainmail Chestplate");
        chainmailChestplate.setItemMeta(chainmailChestplateMeta);
        ItemStack chainmailLeggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta chainmailLeggingsMeta = chainmailLeggings.getItemMeta();
        chainmailLeggingsMeta.setDisplayName("§6Chainmail Leggings");
        chainmailLeggings.setItemMeta(chainmailLeggingsMeta);
        ItemStack chainmailBoots = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta chainmailBootsMeta = chainmailBoots.getItemMeta();
        chainmailBootsMeta.setDisplayName("§6Chainmail Boots");
        chainmailBoots.setItemMeta(chainmailBootsMeta);
        // iron
        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        ItemMeta ironHelmetMeta = ironHelmet.getItemMeta();
        ironHelmetMeta.setDisplayName("§6Iron Helmet");
        ironHelmet.setItemMeta(ironHelmetMeta);
        ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta ironChestplateMeta = ironChestplate.getItemMeta();
        ironChestplateMeta.setDisplayName("§6Iron Chestplate");
        ironChestplate.setItemMeta(ironChestplateMeta);
        ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta ironLeggingsMeta = ironLeggings.getItemMeta();
        ironLeggingsMeta.setDisplayName("§6Iron Leggings");
        ironLeggings.setItemMeta(ironLeggingsMeta);
        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta ironBootsMeta = ironBoots.getItemMeta();
        ironBootsMeta.setDisplayName("§6Iron Boots");
        ironBoots.setItemMeta(ironBootsMeta);

        // diamond
        ItemStack diamondHelmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta diamondHelmetMeta = diamondHelmet.getItemMeta();
        diamondHelmetMeta.setDisplayName("§6Diamond Helmet");
        diamondHelmet.setItemMeta(diamondHelmetMeta);
        ItemStack diamondChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta diamondChestplateMeta = diamondChestplate.getItemMeta();
        diamondChestplateMeta.setDisplayName("§6Diamond Chestplate");
        diamondChestplate.setItemMeta(diamondChestplateMeta);
        ItemStack diamondLeggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta diamondLeggingsMeta = diamondLeggings.getItemMeta();
        diamondLeggingsMeta.setDisplayName("§6Diamond Leggings");
        diamondLeggings.setItemMeta(diamondLeggingsMeta);
        ItemStack diamondBoots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta diamondBootsMeta = diamondBoots.getItemMeta();
        diamondBootsMeta.setDisplayName("§6Diamond Boots");
        diamondBoots.setItemMeta(diamondBootsMeta);
        for(int i = 0; i < 9; i++) {
            inv.setItem(i, glass);
        }
        for(int i = 9; i < 54; i += 9) {
            inv.setItem(i, glass);
            inv.setItem(i + 8, glass);
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, glass);
        }
        inv.setItem(4, glass);
        inv.setItem(11, chainmailHelmet);
        inv.setItem(13, ironHelmet);
        inv.setItem(15, diamondHelmet);
        inv.setItem(20, chainmailChestplate);
        inv.setItem(22, ironChestplate);
        inv.setItem(24, diamondChestplate);
        inv.setItem(29, chainmailLeggings);
        inv.setItem(31, ironLeggings);
        inv.setItem(33, diamondLeggings);
        inv.setItem(38, chainmailBoots);
        inv.setItem(40, ironBoots);
        inv.setItem(42, diamondBoots);
        inv.setItem(45, close);
        inv.setItem(53, back);
        return inv;
    }


    public static void execute(InventoryClickEvent event) {
            if(!Manager.isCurrentlyInGame((Player) event.getWhoClicked())) return;
            Team team = Manager.getManager((Player) event.getWhoClicked()).getTeam((Player) event.getWhoClicked());

            event.setCancelled(true);
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Blocks")){
                event.getWhoClicked().openInventory(ShopItemsBlock.getInventory(team));
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Armor")){
                event.getWhoClicked().openInventory(ShopItemsArmor.getInventory());
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Tools")){
                event.getWhoClicked().openInventory(ShopItemsTools.getInventory());
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cClose")){
                event.getWhoClicked().closeInventory();
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Back")){
                event.getWhoClicked().openInventory(getInventory());
            }

    }

}
