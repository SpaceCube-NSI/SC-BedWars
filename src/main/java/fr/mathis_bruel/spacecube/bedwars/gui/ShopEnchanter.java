package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.utils.Heads;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ShopEnchanter {

    public static Inventory getInventory(){
        Inventory inv = Bukkit.createInventory(null, 9 * 5, "§6Enchanter");
        ItemStack glass = new ItemStack(160, 1, (short) 7);
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
        ItemStack next = Heads.next.getHead();
        // create enchanted book: Fire Aspect 1, KB 1, Sharpness 1, flame, power 1, efficiency 1
        ItemStack fireAspect = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta fireAspectMeta = fireAspect.getItemMeta();
        fireAspectMeta.setDisplayName("§6Fire Aspect 1");
        fireAspectMeta.setLore(Arrays.asList("§7Price: §a15 emeralds", "§7compatibility: §aSword"));
        fireAspect.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);

        fireAspect.setItemMeta(fireAspectMeta);
        ItemStack knockBack = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta knockBackMeta = knockBack.getItemMeta();
        knockBackMeta.setDisplayName("§6Knockback 1");
        knockBackMeta.setLore(Arrays.asList("§7Price: §a10 emeralds", "§7compatibility: §aSword"));
        knockBack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
        knockBack.setItemMeta(knockBackMeta);
        ItemStack sharpness = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta sharpnessMeta = sharpness.getItemMeta();
        sharpnessMeta.setDisplayName("§6Sharpness 1");
        sharpnessMeta.setLore(Arrays.asList("§7Price: §a5 emeralds", "§7compatibility: §aSword"));
        sharpness.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        sharpness.setItemMeta(sharpnessMeta);
        ItemStack flame = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta flameMeta = flame.getItemMeta();
        flameMeta.setDisplayName("§6Flame");
        flameMeta.setLore(Arrays.asList("§7Price: §a10 emeralds", "§7compatibility: §aBow"));
        flame.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
        flame.setItemMeta(flameMeta);
        ItemStack power = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta powerMeta = power.getItemMeta();
        powerMeta.setDisplayName("§6Power 1");
        powerMeta.setLore(Arrays.asList("§7Price: §a5 emeralds", "§7compatibility: §aBow"));
        power.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
        power.setItemMeta(powerMeta);
        ItemStack efficiency = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta efficiencyMeta = efficiency.getItemMeta();
        efficiencyMeta.setDisplayName("§6Efficiency 1");
        efficiencyMeta.setLore(Arrays.asList("§7Price: §a1 emerald", "§7compatibility: §aPickaxe, Axe"));
        efficiency.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        efficiency.setItemMeta(efficiencyMeta);

        for(int i = 0; i < 9; i++) {
            inv.setItem(i, glass);
        }
        for(int i = 9; i < 45; i += 9) {
            inv.setItem(i, glass);
            inv.setItem(i + 8, glass);
        }
        for(int i = 36; i < 45; i++) {
            inv.setItem(i, glass);
        }
        for(int i = 18; i < 27; i++) {
            inv.setItem(i, glass);
        }
        inv.setItem(44, back);
        inv.setItem(36, close);
        inv.setItem(11, glass);
        inv.setItem(12, next);
        inv.setItem(13, next);
        inv.setItem(14, next);
        inv.setItem(15, glass);
        inv.setItem(28, fireAspect);
        inv.setItem(29, knockBack);
        inv.setItem(30, sharpness);
        inv.setItem(31, flame);
        inv.setItem(32, power);
        inv.setItem(33, efficiency);

        return inv;
    }
}
