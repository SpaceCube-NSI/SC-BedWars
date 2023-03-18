package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ShopUpgradesSummoner {

    public static Inventory getInventory(Team team){
        Inventory inv = Bukkit.createInventory(null, 9*5, "Items Summoner");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
        ItemMeta glassmeta = glass.getItemMeta();
        glassmeta.setDisplayName(" ");
        glass.setItemMeta(glassmeta);
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cClose");
        close.setItemMeta(closeMeta);
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§5Back");
        back.setItemMeta(backMeta);

        for (int i = 0; i < 9; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 36; i < 45; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 9; i < 45; i += 9) {
            inv.setItem(i, glass);
            inv.setItem(i + 8, glass);
        }
        inv.setItem(36, close);
        inv.setItem(44, back);

        ItemStack iron1 = new ItemStack(Material.IRON_INGOT, 1);
        ItemMeta iron1Meta = iron1.getItemMeta();
        iron1Meta.setDisplayName("§6Iron lvl 1");
        if(team.getGenerator(0).getLevelIron() >= 1) {
            iron1Meta.setDisplayName("§aIron lvl 1");
            iron1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            iron1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        iron1Meta.setLore(Arrays.asList("§7Price: §a/", "§7Level: §a1"));
        iron1.setItemMeta(iron1Meta);
        inv.setItem(11, iron1);

        ItemStack iron2 = new ItemStack(Material.IRON_INGOT, 1);
        ItemMeta iron2Meta = iron2.getItemMeta();
        iron2Meta.setDisplayName("§6Iron lvl 2");
        if(team.getGenerator(0).getLevelIron() >= 2) {
            iron2Meta.setDisplayName("§aIron lvl 2");
            iron2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            iron2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        iron2Meta.setLore(Arrays.asList("§7Price: §a5 irons", "§7Level: §a2"));
        iron2.setItemMeta(iron2Meta);
        inv.setItem(20, iron2);

        ItemStack iron3 = new ItemStack(Material.IRON_INGOT, 1);
        ItemMeta iron3Meta = iron3.getItemMeta();
        iron3Meta.setDisplayName("§6Iron lvl 3");
        if(team.getGenerator(0).getLevelIron() >= 3) {
            iron3Meta.setDisplayName("§aIron lvl 3");
            iron3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            iron3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        iron3Meta.setLore(Arrays.asList("§7Price: §a10 irons", "§7Level: §a3"));
        iron3.setItemMeta(iron3Meta);
        inv.setItem(29, iron3);


        ItemStack gold1 = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta gold1Meta = gold1.getItemMeta();
        gold1Meta.setDisplayName("§6Gold lvl 1");
        if(team.getGenerator(0).getLevelGold() >= 1) {
            gold1Meta.setDisplayName("§aGold lvl 1");
            gold1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            gold1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        gold1Meta.setLore(Arrays.asList("§7Price: §a1 diamond", "§7Level: §a1"));
        gold1.setItemMeta(gold1Meta);
        inv.setItem(13, gold1);

        ItemStack gold2 = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta gold2Meta = gold2.getItemMeta();
        gold2Meta.setDisplayName("§6Gold lvl 2");
        if(team.getGenerator(0).getLevelGold() >= 2) {
            gold2Meta.setDisplayName("§aGold lvl 2");
            gold2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            gold2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        gold2Meta.setLore(Arrays.asList("§7Price: §a5 gold", "§7Level: §a2"));
        gold2.setItemMeta(gold2Meta);
        inv.setItem(22, gold2);

        ItemStack gold3 = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta gold3Meta = gold3.getItemMeta();
        gold3Meta.setDisplayName("§6Gold lvl 3");
        if(team.getGenerator(0).getLevelGold() >= 3) {
            gold3Meta.setDisplayName("§aGold lvl 3");
            gold3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            gold3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        gold3Meta.setLore(Arrays.asList("§7Price: §a15 gold", "§7Level: §a3"));
        gold3.setItemMeta(gold3Meta);
        inv.setItem(31, gold3);

        ItemStack diamond1 = new ItemStack(Material.DIAMOND, 1);
        ItemMeta diamond1Meta = diamond1.getItemMeta();
        diamond1Meta.setDisplayName("§6Diamond lvl 1");
        if(team.getGenerator(0).getLevelDiamond() >= 1) {
            diamond1Meta.setDisplayName("§aDiamond lvl 1");
            diamond1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            diamond1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        diamond1Meta.setLore(Arrays.asList("§7Price: §a5 emeralds", "§7Level: §a1"));
        diamond1.setItemMeta(diamond1Meta);
        inv.setItem(15, diamond1);

        ItemStack diamond2 = new ItemStack(Material.DIAMOND, 1);
        ItemMeta diamond2Meta = diamond2.getItemMeta();
        diamond2Meta.setDisplayName("§6Diamond lvl 2");
        if(team.getGenerator(0).getLevelDiamond() >= 2) {
            diamond2Meta.setDisplayName("§aDiamond lvl 2");
            diamond2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            diamond2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        diamond2Meta.setLore(Arrays.asList("§7Price: §a10 diamonds", "§7Level: §a2"));
        diamond2.setItemMeta(diamond2Meta);
        inv.setItem(24, diamond2);

        ItemStack diamond3 = new ItemStack(Material.DIAMOND, 1);
        ItemMeta diamond3Meta = diamond3.getItemMeta();
        diamond3Meta.setDisplayName("§6Diamond lvl 3");
        if(team.getGenerator(0).getLevelDiamond() >= 3) {
            diamond3Meta.setDisplayName("§aDiamond lvl 3");
            diamond3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            diamond3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        diamond3Meta.setLore(Arrays.asList("§7Price: §a15 diamonds", "§7Level: §a3"));
        diamond3.setItemMeta(diamond3Meta);
        inv.setItem(33, diamond3);

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

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Iron lvl 1")) {
            if (team.getGenerator(0).getLevelIron() >= 1) {
                event.getWhoClicked().sendMessage("§cYou already have this level!");
                return;
            }
            if (team.getGenerator(0).getLevelIron() != 0) {
                event.getWhoClicked().sendMessage("§cYou don't have the previous level!");
                return;
            }

            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 0)) {
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 0));
                event.getWhoClicked().sendMessage("§aYou have upgraded your iron generator to level 1!");
                team.broadcast("§a" + event.getWhoClicked().getName() + " has upgraded the iron generator to level 1!");
                event.getWhoClicked().closeInventory();
                team.getGenerators().forEach(generator -> generator.setLevelIron(1));
                event.getWhoClicked().openInventory(getInventory(team));
                team.updateGeneratorHolograms();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.LEVEL_UP, 1, 1);
                return;
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron!");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Iron lvl 2")) {
            if (team.getGenerator(0).getLevelIron() >= 2) {
                event.getWhoClicked().sendMessage("§cYou already have this level!");
                return;
            }
            if (team.getGenerator(0).getLevelIron() != 1) {
                event.getWhoClicked().sendMessage("§cYou don't have the previous level!");
                return;
            }

            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 5)) {
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 5));
                event.getWhoClicked().sendMessage("§aYou have upgraded your iron generator to level 2!");
                team.broadcast("§a" + event.getWhoClicked().getName() + " has upgraded the iron generator to level 2!");
                event.getWhoClicked().closeInventory();
                team.getGenerators().forEach(generator -> generator.setLevelIron(2));
                event.getWhoClicked().openInventory(getInventory(team));
                team.updateGeneratorHolograms();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.LEVEL_UP, 1, 1);
                return;
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron!");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Iron lvl 3")) {
            if (team.getGenerator(0).getLevelIron() >= 3) {
                event.getWhoClicked().sendMessage("§cYou already have this level!");
                return;
            }
            if (team.getGenerator(0).getLevelIron() != 2) {
                event.getWhoClicked().sendMessage("§cYou don't have the previous level!");
                return;
            }

            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 10)) {
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                event.getWhoClicked().sendMessage("§aYou have upgraded your iron generator to level 3!");
                team.broadcast("§a" + event.getWhoClicked().getName() + " has upgraded the iron generator to level 3!");
                event.getWhoClicked().closeInventory();
                team.getGenerators().forEach(generator -> generator.setLevelIron(3));
                event.getWhoClicked().openInventory(getInventory(team));
                team.updateGeneratorHolograms();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.LEVEL_UP, 1, 1);
                return;
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron!");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Gold lvl 1")) {
            if (team.getGenerator(0).getLevelGold() >= 1) {
                event.getWhoClicked().sendMessage("§cYou already have this level!");
                return;
            }
            if (team.getGenerator(0).getLevelGold() != 0) {
                event.getWhoClicked().sendMessage("§cYou don't have the previous level!");
                return;
            }

            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 1)) {
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 1));
                event.getWhoClicked().sendMessage("§aYou have upgraded your gold generator to level 1!");
                team.broadcast("§a" + event.getWhoClicked().getName() + " has upgraded the gold generator to level 1!");
                event.getWhoClicked().closeInventory();
                team.getGenerators().forEach(generator -> generator.setLevelGold(1));
                event.getWhoClicked().openInventory(getInventory(team));
                team.updateGeneratorHolograms();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.LEVEL_UP, 1, 1);
                return;
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamond!");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Gold lvl 2")) {
            if (team.getGenerator(0).getLevelGold() >= 2) {
                event.getWhoClicked().sendMessage("§cYou already have this level!");
                return;
            }
            if (team.getGenerator(0).getLevelGold() != 1) {
                event.getWhoClicked().sendMessage("§cYou don't have the previous level!");
                return;
            }

            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 5)) {
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 5));
                event.getWhoClicked().sendMessage("§aYou have upgraded your gold generator to level 2!");
                team.broadcast("§a" + event.getWhoClicked().getName() + " has upgraded the gold generator to level 2!");
                event.getWhoClicked().closeInventory();
                team.getGenerators().forEach(generator -> generator.setLevelGold(2));
                event.getWhoClicked().openInventory(getInventory(team));
                team.updateGeneratorHolograms();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.LEVEL_UP, 1, 1);
                return;
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough gold!");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Gold lvl 3")) {
            if (team.getGenerator(0).getLevelGold() >= 3) {
                event.getWhoClicked().sendMessage("§cYou already have this level!");
                return;
            }
            if (team.getGenerator(0).getLevelGold() != 2) {
                event.getWhoClicked().sendMessage("§cYou don't have the previous level!");
                return;
            }

            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 15)) {
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 15));
                event.getWhoClicked().sendMessage("§aYou have upgraded your gold generator to level 3!");
                team.broadcast("§a" + event.getWhoClicked().getName() + " has upgraded the gold generator to level 3!");
                event.getWhoClicked().closeInventory();
                team.getGenerators().forEach(generator -> generator.setLevelGold(3));
                event.getWhoClicked().openInventory(getInventory(team));
                team.updateGeneratorHolograms();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.LEVEL_UP, 1, 1);
                return;
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough gold!");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Diamond lvl 1")) {
            if (team.getGenerator(0).getLevelDiamond() >= 1) {
                event.getWhoClicked().sendMessage("§cYou already have this level!");
                return;
            }
            if (team.getGenerator(0).getLevelDiamond() != 0) {
                event.getWhoClicked().sendMessage("§cYou don't have the previous level!");
                return;
            }

            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 5)) {
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 5));
                event.getWhoClicked().sendMessage("§aYou have upgraded your diamond generator to level 1!");
                team.broadcast("§a" + event.getWhoClicked().getName() + " has upgraded the diamond generator to level 1!");
                event.getWhoClicked().closeInventory();
                team.getGenerators().forEach(generator -> generator.setLevelDiamond(1));
                event.getWhoClicked().openInventory(getInventory(team));
                team.updateGeneratorHolograms();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.LEVEL_UP, 1, 1);
                return;
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough emeralds!");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Diamond lvl 2")) {
            if (team.getGenerator(0).getLevelDiamond() >= 2) {
                event.getWhoClicked().sendMessage("§cYou already have this level!");
                return;
            }
            if (team.getGenerator(0).getLevelDiamond() != 1) {
                event.getWhoClicked().sendMessage("§cYou don't have the previous level!");
                return;
            }

            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 10)) {
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 10));
                event.getWhoClicked().sendMessage("§aYou have upgraded your diamond generator to level 2!");
                team.broadcast("§a" + event.getWhoClicked().getName() + " has upgraded the diamond generator to level 2!");
                event.getWhoClicked().closeInventory();
                team.getGenerators().forEach(generator -> generator.setLevelDiamond(2));
                event.getWhoClicked().openInventory(getInventory(team));
                team.updateGeneratorHolograms();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.LEVEL_UP, 1, 1);
                return;
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Diamond lvl 3")) {
            if (team.getGenerator(0).getLevelDiamond() >= 3) {
                event.getWhoClicked().sendMessage("§cYou already have this level!");
                return;
            }
            if (team.getGenerator(0).getLevelDiamond() != 2) {
                event.getWhoClicked().sendMessage("§cYou don't have the previous level!");
                return;
            }
            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 15)) {
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 15));
                event.getWhoClicked().sendMessage("§aYou have upgraded your diamond generator to level 3!");
                team.broadcast("§a" + event.getWhoClicked().getName() + " has upgraded the diamond generator to level 3!");
                event.getWhoClicked().closeInventory();
                team.getGenerators().forEach(generator -> generator.setLevelDiamond(3));
                event.getWhoClicked().openInventory(getInventory(team));
                team.updateGeneratorHolograms();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.LEVEL_UP, 1, 1);
                return;
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
                return;
            }
        }

        // back
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5Back")) {
            event.getWhoClicked().closeInventory();
            event.getWhoClicked().openInventory(ShopUpgrades.getInventory(team));
            return;
        }
    }

}
