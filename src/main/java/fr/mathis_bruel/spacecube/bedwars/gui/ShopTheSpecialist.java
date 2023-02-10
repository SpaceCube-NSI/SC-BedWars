package fr.mathis_bruel.spacecube.bedwars.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopTheSpecialist {

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
        pumpkin.setItemMeta(pumpkinMeta);
        ItemStack wolfEgg = new ItemStack(Material.MONSTER_EGG, 1, (short) 95);
        ItemMeta wolfEggMeta = wolfEgg.getItemMeta();
        wolfEggMeta.setDisplayName("§6Wolf Egg");
        wolfEgg.setItemMeta(wolfEggMeta);
        ItemStack enderPearl = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta enderPearlMeta = enderPearl.getItemMeta();
        enderPearlMeta.setDisplayName("§6Ender Pearl");
        enderPearl.setItemMeta(enderPearlMeta);
        ItemStack tnt = new ItemStack(Material.TNT, 1);
        ItemMeta tntMeta = tnt.getItemMeta();
        tntMeta.setDisplayName("§6TNT");
        tnt.setItemMeta(tntMeta);
        ItemStack gap = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta gapMeta = gap.getItemMeta();
        gapMeta.setDisplayName("§6Golden Apple");
        gap.setItemMeta(gapMeta);
        ItemStack healingPot = new ItemStack(Material.POTION, 1, (short) 16453);
        ItemMeta healingPotMeta = healingPot.getItemMeta();
        healingPotMeta.setDisplayName("§6Healing Potion");
        healingPot.setItemMeta(healingPotMeta);
        ItemStack harmingPot = new ItemStack(Material.POTION, 1, (short) 16460);
        ItemMeta harmingPotMeta = harmingPot.getItemMeta();
        harmingPotMeta.setDisplayName("§6Harming Potion");
        harmingPot.setItemMeta(harmingPotMeta);
        ItemStack poisonPot = new ItemStack(Material.POTION, 1, (short) 16388);
        ItemMeta poisonPotMeta = poisonPot.getItemMeta();
        poisonPotMeta.setDisplayName("§6Poison Potion");
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
}
