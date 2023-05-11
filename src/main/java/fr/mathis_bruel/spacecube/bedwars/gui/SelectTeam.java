package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
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

public class SelectTeam {


    public static Inventory getInventory(Arena arena, Player player) {
        Inventory inv = Bukkit.createInventory(null, 9 * 5, "§2Choice your team");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8);
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
        Manager manager = Manager.getManager(arena);
        arena.getTeams().forEach((team) -> {
            ItemStack item = new ItemStack(Material.WOOL, 1, (short) Utils.getDataColor(team.getColor()));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(team.getColor() + team.getName());
            String players = "";
            if (manager.getSelectTeam().get(team) != null) for (Player p : manager.getSelectTeam().get(team)) {
                players += p.getName() + ", ";
            }
            // set lore with players/maxplayers, names of players, is full or not, id of team
            meta.setLore(Arrays.asList("§7Players: " + ((manager.getSelectTeam().get(team) != null) ? manager.getSelectTeam().get(team).size() : 0) + "/" + arena.getPlayerPerTeam(), "§7Players: " + players, "§7Is full: " + (((((manager.getSelectTeam().get(team) != null) ? manager.getSelectTeam().get(team).size() : 0) >= arena.getPlayerPerTeam()) ? "§cYes" : "§aNo")), "§7ID: §e" + arena.getTeams().indexOf(team)));
            item.setItemMeta(meta);
            inv.addItem(item);
        });

        return inv;
    }

    public static void execute(InventoryClickEvent event) {
        if (!Manager.isCurrentlyInGame((Player) event.getWhoClicked())) return;

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
            event.getWhoClicked().closeInventory();
        }

        Manager manager = Manager.getManager((Player) event.getWhoClicked());
        if(manager == null) return;
        Player player = (Player) event.getWhoClicked();
        Team team = manager.getArena().getTeams().get(Integer.parseInt(event.getCurrentItem().getItemMeta().getLore().get(3).split(" ")[1].split("§e")[1]));
        if(team == null) {
            player.sendMessage(Main.prefix + "§cThis team doesn't exist !");
            player.closeInventory();
            return;
        }
        if(manager.getSelectTeam().get(team) != null) {
            if (manager.getSelectTeam().get(team).size() >= manager.getArena().getPlayerPerTeam()) {
                player.sendMessage(Main.getPrefix() + "§cThis team is full !");
                player.closeInventory();
                player.openInventory(getInventory(manager.getArena(), player));
                return;
            }

            if (manager.getSelectTeam().get(team).contains(player)) {
                player.sendMessage(Main.getPrefix() + "§cYou are already in this team !");
                player.closeInventory();
                player.openInventory(getInventory(manager.getArena(), player));
                return;
            }
        }

        manager.addSelectTeam(team, player);
        player.sendMessage(Main.getPrefix()+ "§aYou joined the team " + team.getColor() + team.getName());
        // if in other team, remove from other team
        for (Team t : manager.getArena().getTeams()) {
            if (t == team) continue;
            if (manager.getSelectTeam().get(t) != null && manager.getSelectTeam().get(t).contains(player)) {
                manager.removeSelectTeam(t, player);
                break;
            }
        }
        player.closeInventory();

    }


}
