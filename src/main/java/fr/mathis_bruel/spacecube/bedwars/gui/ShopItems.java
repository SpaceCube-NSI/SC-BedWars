package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.manager.Utils;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopItems {

    public static Inventory getInventory(Team team){
        Inventory inv = Bukkit.createInventory(null, 9*3, "§6Shop");
        ItemStack blocks = new ItemStack(Material.STAINED_CLAY, 1, Utils.getDataColor(team.getColor()));
        ItemMeta blocksMeta = blocks.getItemMeta();
        blocksMeta.setDisplayName("§6Blocks");
        blocks.setItemMeta(blocksMeta);
        ItemStack armor = new ItemStack(Material.IRON_HELMET);
        ItemMeta armorMeta = armor.getItemMeta();
        armorMeta.setDisplayName("§6Armor");
        armor.setItemMeta(armorMeta);
        ItemStack tools = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta toolsMeta = tools.getItemMeta();
        toolsMeta.setDisplayName("§6Tools");
        tools.setItemMeta(toolsMeta);
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, Utils.getDataColor(team.getColor()));
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
        inv.setItem(9, glass);
        inv.setItem(17, glass);
        inv.setItem(18, close);
        inv.setItem(11, blocks);
        inv.setItem(13, armor);
        inv.setItem(15, tools);
        inv.setItem(19, glass);
        inv.setItem(20, glass);
        inv.setItem(21, glass);
        inv.setItem(22, glass);
        inv.setItem(23, glass);
        inv.setItem(24, glass);
        inv.setItem(25, glass);
        inv.setItem(26, back);

        return inv;
    }


}
