package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Heads;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

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
        woolMeta.setLore(Arrays.asList("§7Price: §a5 irons", "§7Amount: §a32"));
        wool.setItemMeta(woolMeta);
        ItemStack clay = new ItemStack(Material.STAINED_CLAY, 32, Utils.getDataColor(team.getColor()));
        ItemMeta clayMeta = clay.getItemMeta();
        clayMeta.setDisplayName("§6Clay");
        clayMeta.setLore(Arrays.asList("§7Price: §a1 diamond", "§7Amount: §a32"));
        clay.setItemMeta(clayMeta);
        ItemStack polishedStone = new ItemStack(Material.STONE, 32, (short) 6);
        ItemMeta polishedStoneMeta = polishedStone.getItemMeta();
        polishedStoneMeta.setDisplayName("§6Polished Stone");
        polishedStoneMeta.setLore(Arrays.asList("§7Price: §a5 gold", "§7Amount: §a32"));
        polishedStone.setItemMeta(polishedStoneMeta);
        ItemStack glassBlock = new ItemStack(Material.STAINED_GLASS, 32, Utils.getDataColor(team.getColor()));
        ItemMeta glassBlockMeta = glassBlock.getItemMeta();
        glassBlockMeta.setDisplayName("§6Glass");
        glassBlockMeta.setLore(Arrays.asList("§7Price: §a5 irons", "§7Amount: §a32"));
        glassBlock.setItemMeta(glassBlockMeta);
        ItemStack endStone = new ItemStack(Material.ENDER_STONE, 32);
        ItemMeta endStoneMeta = endStone.getItemMeta();
        endStoneMeta.setDisplayName("§6End Stone");
        endStoneMeta.setLore(Arrays.asList("§7Price: §a8 gold", "§7Amount: §a32"));
        endStone.setItemMeta(endStoneMeta);
        ItemStack obsidian = new ItemStack(Material.OBSIDIAN, 1);
        ItemMeta obsidianMeta = obsidian.getItemMeta();
        obsidianMeta.setDisplayName("§6Obsidian");
        obsidianMeta.setLore(Arrays.asList("§7Price: §a1 emerald", "§7Amount: §a1"));
        obsidian.setItemMeta(obsidianMeta);
        ItemStack planks = new ItemStack(Material.WOOD, 32);
        ItemMeta planksMeta = planks.getItemMeta();
        planksMeta.setDisplayName("§6Planks");
        planksMeta.setLore(Arrays.asList("§7Price: §a6 irons", "§7Amount: §a32"));
        planks.setItemMeta(planksMeta);
        ItemStack autobridgeWool = Heads.autobridge_wool.getSkull();
        ItemMeta autobridgeWoolMeta = autobridgeWool.getItemMeta();
        autobridgeWoolMeta.setDisplayName("§6Autobridge Wool");
        autobridgeWoolMeta.setLore(Arrays.asList("§7Price: §a25 irons", "§7Amount: §a1"));
        autobridgeWool.setItemMeta(autobridgeWoolMeta);
        ItemStack autobridgeWood = Heads.autobridge_wood.getSkull();
        ItemMeta autobridgeWoodMeta = autobridgeWood.getItemMeta();
        autobridgeWoodMeta.setDisplayName("§6Autobridge Wood");
        autobridgeWoodMeta.setLore(Arrays.asList("§7Price: §a30 irons", "§7Amount: §a1"));
        autobridgeWood.setItemMeta(autobridgeWoodMeta);
        ItemStack autobridgeAndesite = Heads.autobridge_stone.getSkull();
        ItemMeta autobridgeAndesiteMeta = autobridgeAndesite.getItemMeta();
        autobridgeAndesiteMeta.setDisplayName("§6Autobridge Andesite");
        autobridgeAndesiteMeta.setLore(Arrays.asList("§7Price: §a25 golds", "§7Amount: §a1"));
        autobridgeAndesite.setItemMeta(autobridgeAndesiteMeta);
        ItemStack autobridgeEndStone = Heads.autobridge_endstone.getSkull();
        ItemMeta autobridgeEndStoneMeta = autobridgeEndStone.getItemMeta();
        autobridgeEndStoneMeta.setDisplayName("§6Autobridge End Stone");
        autobridgeEndStoneMeta.setLore(Arrays.asList("§7Price: §a30 golds", "§7Amount: §a1"));
        autobridgeEndStone.setItemMeta(autobridgeEndStoneMeta);
        ItemStack autobridgeClay = Heads.autobridge_argil.getSkull();
        ItemMeta autobridgeClayMeta = autobridgeClay.getItemMeta();
        autobridgeClayMeta.setDisplayName("§6Autobridge Clay");
        autobridgeClayMeta.setLore(Arrays.asList("§7Price: §a30 diamonds", "§7Amount: §a1"));
        autobridgeClay.setItemMeta(autobridgeClayMeta);





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
        inv.setItem(11, wool);
        inv.setItem(13, clay);
        inv.setItem(15, polishedStone);
        inv.setItem(20, glassBlock);
        inv.setItem(22, endStone);
        inv.setItem(24, planks);
        inv.setItem(31, obsidian);
        inv.setItem(38, autobridgeWood);
        //inv.setItem(39, autobridgeWool);
        inv.setItem(40, autobridgeAndesite);
        inv.setItem(41, autobridgeEndStone);
        //inv.setItem(42, autobridgeClay);
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
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Wool")) {
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 5)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.WOOL, 32, Utils.getDataColor(team.getColor())))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 5));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.WOOL, 32, Utils.getDataColor(team.getColor())));
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron ingots!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Clay")) {
            if (event.getWhoClicked().getInventory().contains(Material.DIAMOND, 1)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, Utils.getDataColor(team.getColor())))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 1));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.STAINED_CLAY, 32, Utils.getDataColor(team.getColor())));
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                ((Player) event.getWhoClicked()).updateInventory();
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Polished Stone")) {
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 5)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.STONE, 32, (short) 6))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 5));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.STONE, 32, (short) 6));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough gold ingots!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Glass")) {
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 5)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, Utils.getDataColor(team.getColor())))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 5));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.STAINED_GLASS, 32, Utils.getDataColor(team.getColor())));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron ingots!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6End Stone")) {
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 8)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.ENDER_STONE, 32))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 8));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.ENDER_STONE, 32));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough gold ingots!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Obsidian")) {
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 1)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.OBSIDIAN, 1))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 1));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.OBSIDIAN, 1));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough emeralds!");
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Planks")) {
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 5)) {
                if (!Utils.canAddItemInInventory((Player) event.getWhoClicked(), new ItemStack(Material.WOOD, 32))) {
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 5));
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.WOOD, 32));
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron ingots!");
            }
        }
        // auto bridge wool
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Autobridge Wool")){
            if(event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 25)){
                if(!Utils.canAddItemInInventory((Player) event.getWhoClicked(), Heads.autobridge_wool.getSkull())){
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 25));
                ItemStack item = Heads.autobridge_wool.getSkull();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§6Autobridge Wool");
                meta.setLore(Arrays.asList("§7Place this block in floor to", "§7automatically bridge with wool."));
                item.setItemMeta(meta);
                event.getWhoClicked().getInventory().addItem(item);
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron ingots!");
            }
        }
        // auto bridge wood
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Autobridge Wood")){
            if(event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 25)){
                if(!Utils.canAddItemInInventory((Player) event.getWhoClicked(), Heads.autobridge_wood.getSkull())){
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 25));
                ItemStack item = Heads.autobridge_wood.getSkull();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§6Autobridge Wood");
                meta.setLore(Arrays.asList("§7Place this block in floor to", "§7automatically bridge with wood."));
                item.setItemMeta(meta);
                event.getWhoClicked().getInventory().addItem(item);
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough iron ingots!");
            }
        }
        // auto bridge andesite
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Autobridge Andesite")){
            if(event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 25)){
                if(!Utils.canAddItemInInventory((Player) event.getWhoClicked(), Heads.autobridge_stone.getSkull())){
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 25));
                ItemStack item = Heads.autobridge_stone.getSkull();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§6Autobridge Andesite");
                meta.setLore(Arrays.asList("§7Place this block in floor to", "§7automatically bridge with andesite."));
                item.setItemMeta(meta);
                event.getWhoClicked().getInventory().addItem(item);
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough gold ingots!");
            }
        }
        // auto bridge endstone
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Autobridge End Stone")){
            if(event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 25)){
                if(!Utils.canAddItemInInventory((Player) event.getWhoClicked(), Heads.autobridge_endstone.getSkull())){
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 25));
                ItemStack item = Heads.autobridge_endstone.getSkull();
                SkullMeta meta = (SkullMeta) item.getItemMeta();
                meta.setDisplayName("§6Autobridge End Stone");
                meta.setLore(Arrays.asList("§7Place this block in floor to", "§7automatically bridge end stone"));
                item.setItemMeta(meta);
                event.getWhoClicked().getInventory().addItem(item);
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough gold ingots!");
            }
        }
        // auto bridge clay
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Autobridge Clay")){
            if(event.getWhoClicked().getInventory().contains(Material.DIAMOND, 30)){
                if(!Utils.canAddItemInInventory((Player) event.getWhoClicked(), Heads.autobridge_argil.getSkull())){
                    event.getWhoClicked().sendMessage("§cYou don't have enough space in your inventory!");
                    event.getWhoClicked().closeInventory();
                    return;
                }
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.DIAMOND, 25));
                ItemStack item = Heads.autobridge_argil.getSkull();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§6Autobridge Clay");
                meta.setLore(Arrays.asList("§7Place this block in floor to", "§7automatically bridge with clay."));
                item.setItemMeta(meta);
                event.getWhoClicked().getInventory().addItem(item);
                ((Player) event.getWhoClicked()).updateInventory();
                ((Player) event.getWhoClicked()).playSound(((Player) event.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                event.getWhoClicked().sendMessage("§cYou don't have enough diamonds!");
            }
        }




    }
}
