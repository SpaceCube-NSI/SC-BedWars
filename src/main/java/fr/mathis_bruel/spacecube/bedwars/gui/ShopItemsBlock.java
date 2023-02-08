package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.manager.Utils;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopItemsBlock {

    public static Inventory getInventory(Team team) {
        Inventory inv = Bukkit.createInventory(null, 9 * 6, "§6Blocks");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
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
        ItemStack wool = new ItemStack(Material.WOOL, 32, Utils.getDataColor(team.getColor()));
        ItemMeta woolMeta = wool.getItemMeta();
        woolMeta.setDisplayName("§6Wool");
        wool.setItemMeta(woolMeta);
        ItemStack clay = new ItemStack(Material.STAINED_CLAY, 32, Utils.getDataColor(team.getColor()));
        ItemMeta clayMeta = clay.getItemMeta();
        clayMeta.setDisplayName("§6Clay");
        clay.setItemMeta(clayMeta);
        ItemStack polishedStone = new ItemStack(Material.STONE, 32, (short) 6);
        ItemMeta polishedStoneMeta = polishedStone.getItemMeta();
        polishedStoneMeta.setDisplayName("§6Polished Stone");
        polishedStone.setItemMeta(polishedStoneMeta);
        ItemStack glassBlock = new ItemStack(Material.STAINED_GLASS, 32, Utils.getDataColor(team.getColor()));
        ItemMeta glassBlockMeta = glassBlock.getItemMeta();
        glassBlockMeta.setDisplayName("§6Glass");
        glassBlock.setItemMeta(glassBlockMeta);
        ItemStack endStone = new ItemStack(Material.ENDER_STONE, 32);
        ItemMeta endStoneMeta = endStone.getItemMeta();
        endStoneMeta.setDisplayName("§6End Stone");
        endStone.setItemMeta(endStoneMeta);
        ItemStack obsidian = new ItemStack(Material.OBSIDIAN, 1);
        ItemMeta obsidianMeta = obsidian.getItemMeta();
        obsidianMeta.setDisplayName("§6Obsidian");
        obsidian.setItemMeta(obsidianMeta);
        ItemStack planks = new ItemStack(Material.WOOD, 32, (short) 1);
        ItemMeta planksMeta = planks.getItemMeta();
        planksMeta.setDisplayName("§6Planks");
        planks.setItemMeta(planksMeta);

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
        inv.setItem(45, close);
        inv.setItem(53, back);
        inv.setItem(11, wool);
        inv.setItem(13, clay);
        inv.setItem(15, polishedStone);
        inv.setItem(20, glassBlock);
        inv.setItem(22, endStone);
        inv.setItem(24, planks);
        inv.setItem(31, obsidian);

        return inv;



    }
}
