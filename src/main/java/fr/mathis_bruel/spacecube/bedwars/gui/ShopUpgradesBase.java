package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class ShopUpgradesBase {

    public static Inventory getInventory(Team team){
        Inventory inv = org.bukkit.Bukkit.createInventory(null, 9*4, "§6§lShop Upgrades Bases");
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

        for(int i = 0; i < 9; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 27; i < 35; i++){
            inv.setItem(i, glass);
        }
        inv.setItem(9, glass);
        inv.setItem(17, glass);
        inv.setItem(18, glass);
        inv.setItem(26, glass);
        inv.setItem(27, close);
        inv.setItem(35, back);

        // Items: tripwire (alarm), beacon (healpool), iron boots (speed 1), diamond boots (speed 2)
        ItemStack trap = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta trapMeta = trap.getItemMeta();
        trapMeta.setDisplayName("§6Alarm");
        trapMeta.setLore(Arrays.asList("§7Price: §a20 diamonds", "§7Description: §aThis alarm warns you when an enemy enters your base."));
        if(team.getAlarm()){
            trapMeta.setDisplayName("§aIron lvl 1");
            trapMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            trapMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        trap.setItemMeta(trapMeta);
        inv.setItem(11, trap);

        ItemStack healpool = new ItemStack(Material.BEACON);
        ItemMeta healpoolMeta = healpool.getItemMeta();
        healpoolMeta.setDisplayName("§6Healpool");
        healpoolMeta.setLore(Arrays.asList("§7Price: §a40 diamonds", "§7Description: §a§7This healpool heals you when you are in your base."));
        if(team.getHealpool()){
            healpoolMeta.setDisplayName("§aIron lvl 1");
            healpoolMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            healpoolMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        healpool.setItemMeta(healpoolMeta);
        inv.setItem(15, healpool);

        ItemStack speed1 = new ItemStack(Material.IRON_BOOTS);
        ItemMeta speed1Meta = speed1.getItemMeta();
        speed1Meta.setDisplayName("§6Speed 1");
        speed1Meta.setLore(Arrays.asList("§7Price: §a5 emeralds", "§7Description: §a§7This speed 1 boots gives you speed 1 everywhere."));
        if(team.getLvlSpeed() >= 1){
            speed1Meta.setDisplayName("§aIron lvl 1");
            speed1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            speed1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        speed1.setItemMeta(speed1Meta);
        inv.setItem(13, speed1);

        ItemStack speed2 = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta speed2Meta = speed2.getItemMeta();
        speed2Meta.setDisplayName("§6Speed 2");
        speed2Meta.setLore(Arrays.asList("§7Price: §a5 emeralds", "§7Description: §a§7This speed 2 boots gives you speed 2 everywhere."));
        if(team.getLvlSpeed() >= 2){
            speed2Meta.setDisplayName("§aIron lvl 2");
            speed2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
            speed2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        speed2.setItemMeta(speed2Meta);
        inv.setItem(22, speed2);
        return inv;
    }

    public static void execute(InventoryClickEvent event) {
        if (!Manager.isCurrentlyInGame((Player) event.getWhoClicked())) return;
        Team team = Manager.getManager((Player) event.getWhoClicked()).getTeam((Player) event.getWhoClicked());

        event.setCancelled(true);
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cClose")) {
            event.getWhoClicked().closeInventory();
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5Back")) {
            event.getWhoClicked().openInventory(ShopUpgrades.getInventory(team));
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Alarm")) {
            if(team.getAlarm()){
                event.getWhoClicked().sendMessage("§cYou already have an alarm!");
                event.getWhoClicked().closeInventory();
                return;
            }
            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 20)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.TRIPWIRE_HOOK))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 20));
                team.broadcast("§a" + event.getWhoClicked().getName() + " §7has bought an alarm!");
                team.setAlarm(true);
                team.playSound(Sound.ORB_PICKUP);
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                event.getWhoClicked().sendMessage("§aYou have bought an alarm!");
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().openInventory(getInventory(team));
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Healpool")) {
            if(team.getHealpool()){
                event.getWhoClicked().sendMessage("§cYou already have a healpool!");
                event.getWhoClicked().closeInventory();
                return;
            }
            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 40)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.WATER_BUCKET))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 40));
                team.broadcast("§a" + event.getWhoClicked().getName() + " §7has bought a healpool!");
                team.setHealpool(true);
                team.playSound(Sound.ORB_PICKUP);
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                event.getWhoClicked().sendMessage("§aYou have bought a healpool!");
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().openInventory(getInventory(team));
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Speed 1")) {
            if(team.getLvlSpeed() >= 1){
                event.getWhoClicked().sendMessage("§cYou already have this upgrade!");
                event.getWhoClicked().closeInventory();
                return;
            }
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 5)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.DIAMOND_BOOTS))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 5));
                team.broadcast("§a" + event.getWhoClicked().getName() + " §7has bought speed 1!");
                team.setLvlSpeed(1);
                team.getPlayers().forEach(player -> player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0, true)));
                team.playSound(Sound.ORB_PICKUP);
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                event.getWhoClicked().sendMessage("§aYou have bought speed 1!");
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().openInventory(getInventory(team));
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough emeralds!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Speed 2")) {
            if(team.getLvlSpeed() >= 2){
                event.getWhoClicked().sendMessage("§cYou already have this upgrade!");
                event.getWhoClicked().closeInventory();
                return;
            }
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 5)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.DIAMOND_BOOTS))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 5));
                team.broadcast("§a" + event.getWhoClicked().getName() + " §7has bought speed 2!");
                team.setLvlSpeed(2);
                team.getPlayers().forEach(player -> player.removePotionEffect(PotionEffectType.SPEED));
                team.getPlayers().forEach(player -> player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1, true)));
                team.playSound(Sound.ORB_PICKUP);
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                event.getWhoClicked().sendMessage("§aYou have bought speed 2!");
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().openInventory(getInventory(team));
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough emeralds!");
            }
        }

    }

}
