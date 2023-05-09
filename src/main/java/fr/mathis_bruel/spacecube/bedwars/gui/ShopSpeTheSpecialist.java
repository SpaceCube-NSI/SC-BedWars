package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ShopSpeTheSpecialist {

    public static Inventory getInventory(){
        Inventory inv = Bukkit.createInventory(null, 9 * 4, "§6The Specialist");
        ItemStack glass = new ItemStack(160, 1, (short) 13);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cClose");
        close.setItemMeta(closeMeta);
        ItemStack back = new ItemStack(Material.ARROW, 1);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§5Back");
        back.setItemMeta(backMeta);
        ItemStack pumpkin = new ItemStack(Material.PUMPKIN, 1);
        ItemMeta pumpkinMeta = pumpkin.getItemMeta();
        pumpkinMeta.setDisplayName("§6Pumpkin");
        pumpkinMeta.setLore(Arrays.asList("§7Price: §a64 diamond", "§7Amount: §a1", "§7Description: §a..."));
        pumpkin.setItemMeta(pumpkinMeta);
        ItemStack wolfEgg = new ItemStack(Material.MONSTER_EGG, 1, (short) 95);
        ItemMeta wolfEggMeta = wolfEgg.getItemMeta();
        wolfEggMeta.setDisplayName("§6Wolf Egg");
        wolfEggMeta.setLore(Arrays.asList("§7Price: §a1 emerald", "§7Amount: §a1", "§7Description: §a..."));
        wolfEgg.setItemMeta(wolfEggMeta);
        ItemStack enderPearl = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta enderPearlMeta = enderPearl.getItemMeta();
        enderPearlMeta.setDisplayName("§6Ender Pearl");
        enderPearlMeta.setLore(Arrays.asList("§7Price: §a15 emerald", "§7Amount: §a1"));
        enderPearl.setItemMeta(enderPearlMeta);
        ItemStack tnt = new ItemStack(Material.TNT, 1);
        ItemMeta tntMeta = tnt.getItemMeta();
        tntMeta.setDisplayName("§6TNT");
        tntMeta.setLore(Arrays.asList("§7Price: §a1 emerald", "§7Amount: §a1"));
        tnt.setItemMeta(tntMeta);
        ItemStack gap = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta gapMeta = gap.getItemMeta();
        gapMeta.setDisplayName("§6Golden Apple");
        gapMeta.setLore(Arrays.asList("§7Price: §a5 emerald", "§7Amount: §a1"));
        gap.setItemMeta(gapMeta);
        ItemStack healingPot = new ItemStack(Material.POTION, 1, (short) 16453);
        ItemMeta healingPotMeta = healingPot.getItemMeta();
        healingPotMeta.setDisplayName("§6Healing Potion");
        healingPotMeta.setLore(Arrays.asList("§7Price: §a30 diamond", "§7Amount: §a1"));
        healingPot.setItemMeta(healingPotMeta);
        ItemStack harmingPot = new ItemStack(Material.POTION, 1, (short) 16460);
        ItemMeta harmingPotMeta = harmingPot.getItemMeta();
        harmingPotMeta.setDisplayName("§6Harming Potion");
        harmingPotMeta.setLore(Arrays.asList("§7Price: §a40 diamond", "§7Amount: §a1"));
        harmingPot.setItemMeta(harmingPotMeta);
        ItemStack poisonPot = new ItemStack(Material.POTION, 1, (short) 16388);
        ItemMeta poisonPotMeta = poisonPot.getItemMeta();
        poisonPotMeta.setDisplayName("§6Poison Potion");
        poisonPotMeta.setLore(Arrays.asList("§7Price: §a35 diamond", "§7Amount: §a1"));
        poisonPot.setItemMeta(poisonPotMeta);


        for(int i = 0; i < 9; i++) {
            inv.setItem(i, glass);
        }
        for(int i = 9; i < 36; i += 9) {
            inv.setItem(i, glass);
            inv.setItem(i + 8, glass);
        }
        for(int i = 25; i < 36; i++) {
            inv.setItem(i, glass);
        }
        inv.setItem(35, back);
        inv.setItem(27, close);
        inv.setItem(11, pumpkin);
        inv.setItem(12, wolfEgg);
        inv.setItem(13, enderPearl);
        inv.setItem(14, tnt);
        inv.setItem(15, gap);
        inv.setItem(21, healingPot);
        inv.setItem(22, harmingPot);
        inv.setItem(23, poisonPot);

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
            event.getWhoClicked().openInventory(ShopItems.getInventory(team));
            return;
        }
        /*
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Wool")) {
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 5)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.WOOL, 32, Utils.getDataColor(team.getColor())))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 5));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.WOOL, 32, Utils.getDataColor(team.getColor())));
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron ingots!");
            }
        }
         */
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Pumpkin")) {
            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 64)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.PUMPKIN, 1))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 64));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.PUMPKIN, 1));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
            }
            }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Wolf Egg")) {
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 1)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.MONSTER_EGG, 1, (short) 95))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 1));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 95));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough emeralds!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Ender Pearl")) {
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 15)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.ENDER_PEARL, 1))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 15));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough emeralds!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6TNT")) {
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 1)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.TNT, 1))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 1));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.TNT, 1));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough emeralds!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Healing Potion")) {
            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 30)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.POTION, 1, (short) 16421))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 30));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.POTION, 1, (short) 16421));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
            }
        }
        // "§6Harming Potion"
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Harming Potion")) {
            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 40)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.POTION, 1, (short) 16428))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 40));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.POTION, 1, (short) 16428));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
            }
        }
        // "§6Poison Potion"v
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Poison Potion")) {
            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 35)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.POTION, 1, (short) 16418))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 35));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.POTION, 1, (short) 16418));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
            }
        }
        // golden apple
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Golden Apple")) {
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 5)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.GOLDEN_APPLE, 1))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 5));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough emeralds!");
            }
        }



    }
}
