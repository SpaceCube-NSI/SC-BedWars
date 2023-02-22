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
        chainmailHelmetMeta.setLore(Arrays.asList("§7Price: §a10 irons", "§7Amount: §a1"));
        chainmailHelmet.setItemMeta(chainmailHelmetMeta);
        ItemStack chainmailChestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta chainmailChestplateMeta = chainmailChestplate.getItemMeta();
        chainmailChestplateMeta.setDisplayName("§6Chainmail Chestplate");
        chainmailChestplateMeta.setLore(Arrays.asList("§7Price: §a10 gold", "§7Amount: §a1"));
        chainmailChestplate.setItemMeta(chainmailChestplateMeta);
        ItemStack chainmailLeggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta chainmailLeggingsMeta = chainmailLeggings.getItemMeta();
        chainmailLeggingsMeta.setDisplayName("§6Chainmail Leggings");
        chainmailLeggingsMeta.setLore(Arrays.asList("§7Price: §a10 gold", "§7Amount: §a1"));
        chainmailLeggings.setItemMeta(chainmailLeggingsMeta);
        ItemStack chainmailBoots = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta chainmailBootsMeta = chainmailBoots.getItemMeta();
        chainmailBootsMeta.setDisplayName("§6Chainmail Boots");
        chainmailBootsMeta.setLore(Arrays.asList("§7Price: §a10 irons", "§7Amount: §a1"));
        chainmailBoots.setItemMeta(chainmailBootsMeta);
        // iron
        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        ItemMeta ironHelmetMeta = ironHelmet.getItemMeta();
        ironHelmetMeta.setDisplayName("§6Iron Helmet");
        ironHelmetMeta.setLore(Arrays.asList("§7Price: §a50 gold", "§7Amount: §a1"));
        ironHelmet.setItemMeta(ironHelmetMeta);
        ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta ironChestplateMeta = ironChestplate.getItemMeta();
        ironChestplateMeta.setDisplayName("§6Iron Chestplate");
        ironChestplateMeta.setLore(Arrays.asList("§7Price: §a2 emeralds", "§7Amount: §a1"));
        ironChestplate.setItemMeta(ironChestplateMeta);
        ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta ironLeggingsMeta = ironLeggings.getItemMeta();
        ironLeggingsMeta.setDisplayName("§6Iron Leggings");
        ironLeggingsMeta.setLore(Arrays.asList("§7Price: §a20 diamonds", "§7Amount: §a1"));
        ironLeggings.setItemMeta(ironLeggingsMeta);
        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta ironBootsMeta = ironBoots.getItemMeta();
        ironBootsMeta.setDisplayName("§6Iron Boots");
        ironBootsMeta.setLore(Arrays.asList("§7Price: §a50 irons", "§7Amount: §a1"));
        ironBoots.setItemMeta(ironBootsMeta);

        // diamond
        ItemStack diamondHelmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta diamondHelmetMeta = diamondHelmet.getItemMeta();
        diamondHelmetMeta.setDisplayName("§6Diamond Helmet");
        diamondHelmetMeta.setLore(Arrays.asList("§7Price: §a200 gold", "§7Amount: §a1"));
        diamondHelmet.setItemMeta(diamondHelmetMeta);
        ItemStack diamondChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta diamondChestplateMeta = diamondChestplate.getItemMeta();
        diamondChestplateMeta.setDisplayName("§6Diamond Chestplate");
        diamondChestplateMeta.setLore(Arrays.asList("§7Price: §a5 emeralds", "§7Amount: §a1"));
        diamondChestplate.setItemMeta(diamondChestplateMeta);
        ItemStack diamondLeggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta diamondLeggingsMeta = diamondLeggings.getItemMeta();
        diamondLeggingsMeta.setDisplayName("§6Diamond Leggings");
        diamondLeggingsMeta.setLore(Arrays.asList("§7Price: §a50 diamonds", "§7Amount: §a1"));
        diamondLeggings.setItemMeta(diamondLeggingsMeta);
        ItemStack diamondBoots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta diamondBootsMeta = diamondBoots.getItemMeta();
        diamondBootsMeta.setDisplayName("§6Diamond Boots");
        diamondBootsMeta.setLore(Arrays.asList("§7Price: §a200 irons", "§7Amount: §a1"));
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
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Chainmail Helmet")) {
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 10)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.CHAINMAIL_HELMET))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.CHAINMAIL_HELMET));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron ingots!");
            }
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Chainmail Chestplate")) {
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 10)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.CHAINMAIL_CHESTPLATE))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 10));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough gold ingots!");
            }
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Chainmail Leggings")) {
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 10)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.CHAINMAIL_LEGGINGS))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 10));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough gold ingots!");
            }
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Chainmail Boots")) {
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 10)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.CHAINMAIL_BOOTS))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.CHAINMAIL_BOOTS));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron ingots!");
            }
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Iron Helmet")) {
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 50)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.IRON_HELMET))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 50));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.IRON_HELMET));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough gold ingots!");
            }
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Iron Chestplate")) {
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 2)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.IRON_CHESTPLATE))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 2));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough emeralds!");
            }
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Iron Leggings")) {
            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 20)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.IRON_LEGGINGS))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 20));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
            }
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Iron Boots")) {
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 50)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.IRON_BOOTS))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 50));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.IRON_BOOTS));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron ingots!");
            }
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Diamond Helmet")) {
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 200)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.DIAMOND_HELMET))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 200));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough gold ingots!");
            }
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Diamond Chestplate")) {
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 5)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.DIAMOND_CHESTPLATE))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 5));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough emeralds!");
            }
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Diamond Leggings")) {
            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 50)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.DIAMOND_LEGGINGS))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 50));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
            }
        }
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Diamond Boots")) {
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 200)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.DIAMOND_BOOTS))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 200));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.DIAMOND_BOOTS));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron ingots!");
            }
        }


    }

}
