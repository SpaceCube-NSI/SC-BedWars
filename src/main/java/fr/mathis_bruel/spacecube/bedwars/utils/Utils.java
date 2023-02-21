package fr.mathis_bruel.spacecube.bedwars.utils;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;

public class Utils {
    public static Location parseStringToLoc(String string) {
        String[] parsedLoc = string.split(",");
        double x = Double.valueOf(parsedLoc[1]);
        double y = Double.valueOf(parsedLoc[2]);
        double z = Double.valueOf(parsedLoc[3]);
        String world = parsedLoc[0];
        float pitch = Float.valueOf(parsedLoc[4]);
        float yaw = Float.valueOf(parsedLoc[5]);

        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public static String parseLocToString(Location loc) {
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        World world = loc.getWorld();
        float pitch = loc.getPitch();
        float yaw = loc.getYaw();
        return world.getName() + ", " + x + ", " + y + ", " + z + ", " + pitch + ", " + yaw;
    }

    public static ChatColor getColor(String string) {
        switch (string) {
            case "RED":
                return ChatColor.RED;
            case "BLUE":
                return ChatColor.BLUE;
            case "GREEN":
                return ChatColor.GREEN;
            case "YELLOW":
                return ChatColor.YELLOW;
            case "WHITE":
                return ChatColor.WHITE;
            case "BLACK":
                return ChatColor.BLACK;
            case "DARK_RED":
                return ChatColor.DARK_RED;
            case "DARK_BLUE":
                return ChatColor.DARK_BLUE;
            case "DARK_GREEN":
                return ChatColor.DARK_GREEN;
            case "DARK_AQUA":
                return ChatColor.DARK_AQUA;
            case "DARK_PURPLE":
                return ChatColor.DARK_PURPLE;
            case "DARK_GRAY":
                return ChatColor.DARK_GRAY;
            case "GOLD":
                return ChatColor.GOLD;
            case "AQUA":
                return ChatColor.AQUA;
            case "LIGHT_PURPLE":
                return ChatColor.LIGHT_PURPLE;
            case "GRAY":
                return ChatColor.GRAY;
            default:
                return null;
        }
    }

    public static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getColorName(ChatColor color) {
        switch (color) {
            case RED:
                return "Rouge";
            case BLUE:
                return "Bleu";
            case GREEN:
                return "Vert";
            case YELLOW:
                return "Jaune";
            case WHITE:
                return "Blanc";
            case BLACK:
                return "Noir";
            case DARK_RED:
                return "Rouge foncé";
            case DARK_BLUE:
                return "Bleu foncé";
            case DARK_GREEN:
                return "Vert foncé";
            case DARK_AQUA:
                return "Bleu aqua";
            case DARK_PURPLE:
                return "Violet";
            case DARK_GRAY:
                return "Gris foncé";
            case GOLD:
                return "Or";
            case AQUA:
                return "Aqua";
            case LIGHT_PURPLE:
                return "Rose";
            case GRAY:
                return "Gris";
            default:
                return null;

        }
    }


    public static String getColorString(ChatColor color) {
        // return § + color.getChar();
        switch (color) {
            case RED:
                return "§c";
            case BLUE:
                return "§9";
            case GREEN:
                return "§a";
            case YELLOW:
                return "§e";
            case WHITE:
                return "§f";
            case BLACK:
                return "§0";
            case DARK_RED:
                return "§4";
            case DARK_BLUE:
                return "§1";
            case DARK_GREEN:
                return "§2";
            case DARK_AQUA:
                return "§3";
            case DARK_PURPLE:
                return "§5";
            case DARK_GRAY:
                return "§8";
            case GOLD:
                return "§6";
            case AQUA:
                return "§b";
            case LIGHT_PURPLE:
                return "§d";
            case GRAY:
                return "§7";
            default:
                return null;

        }
    }

    public static ChatColor[] getAllColor() {
        return new ChatColor[]{ChatColor.RED, ChatColor.BLUE, ChatColor.GREEN, ChatColor.YELLOW, ChatColor.WHITE, ChatColor.BLACK, ChatColor.DARK_RED, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_AQUA, ChatColor.DARK_PURPLE, ChatColor.DARK_GRAY, ChatColor.GOLD, ChatColor.AQUA, ChatColor.LIGHT_PURPLE, ChatColor.GRAY};
    }

    public static final Block getTargetBlock(Player player, int range) {
        BlockIterator iter = new BlockIterator(player, range);
        Block lastBlock = iter.next();
        while (iter.hasNext()) {
            lastBlock = iter.next();
            if (lastBlock.getType() == Material.AIR) {
                continue;
            }
            break;
        }
        return lastBlock;
    }

    public static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static Short getDataColor(ChatColor color) {
        switch (color.name().toUpperCase()) {
            case "RED":
                return 14;
            case "BLUE":
                return 11;
            case "GREEN":
                return 5;
            case "YELLOW":
                return 4;
            case "WHITE":
                return 0;
            case "BLACK":
                return 15;
            case "DARK_RED":
                return 1;
            case "DARK_BLUE":
                return 11;
            case "DARK_GREEN":
                return 13;
            case "DARK_AQUA":
                return 9;
            case "DARK_PURPLE":
                return 10;
            case "DARK_GRAY":
                return 8;
            case "GOLD":
                return 1;
            case "AQUA":
                return 3;
            case "LIGHT_PURPLE":
                return 2;
            case "GRAY":
                return 7;
            default:
                return null;
        }
    }

    public static ItemStack getHead(Player player) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(player.getName());
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Custom head");
        skull.setLore(lore);
        skull.setOwner(player.getName());
        item.setItemMeta(skull);
        return item;
    }

    public static ItemStack getIcon(Manager manager) {
        switch (manager.getManagerState().getCurrentState()) {
            case WAITING: {
                ItemStack item = new ItemStack(Material.WOOL, 1, (short) 5);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§a" + manager.getArena().getName());
                ArrayList<String> lore = new ArrayList<String>();
                lore.add("§7Players: §a" + manager.getPlayers().size() + "§7/§4" + manager.getArena().getPlayerPerTeam() * manager.getArena().getTeams().size());
                lore.add("§7State: §aWaiting");
                lore.add("§7Id: §a" + Main.getInstance().arenas.indexOf(manager.getArena()));
                meta.setLore(lore);
                item.setItemMeta(meta);
                return item;
            }
            case STARTING: {
                ItemStack item2 = new ItemStack(Material.WOOL, 1, (short) 1);
                ItemMeta meta2 = item2.getItemMeta();
                meta2.setDisplayName("§a" + manager.getArena().getName());
                ArrayList<String> lore2 = new ArrayList<String>();
                lore2.add("§7Players: §a" + manager.getPlayers().size() + "§7/§4" + manager.getArena().getPlayerPerTeam() * manager.getArena().getTeams().size());
                lore2.add("§7State: §aStarting");
                lore2.add("§7Id: §a" + Main.getInstance().arenas.indexOf(manager.getArena()));
                meta2.setLore(lore2);
                item2.setItemMeta(meta2);
                return item2;
            }
            default:
                ItemStack item2 = new ItemStack(Material.WOOL, 1, (short) 4);
                ItemMeta meta2 = item2.getItemMeta();
                meta2.setDisplayName("§a" + manager.getArena().getName());
                ArrayList<String> lore2 = new ArrayList<String>();
                lore2.add("§7Players: §a" + manager.getPlayers().size() + "§7/§4" + manager.getArena().getPlayerPerTeam() * manager.getArena().getTeams().size());
                lore2.add("§7State: §a" + manager.getManagerState().getCurrentState().name());
                lore2.add("§7Id: §a" + Main.getInstance().arenas.indexOf(manager.getArena()));
                meta2.setLore(lore2);
                item2.setItemMeta(meta2);
                return item2;
        }




    }
    public static boolean canAddItemInInventory(Player player, ItemStack item) {
        // if player has space in inventory in function of item and quantity
        if (player.getInventory().firstEmpty() != -1) {
            return true;
        }
        // if player has space in inventory in function of item
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null && itemStack.getType() == item.getType() && itemStack.getDurability() == item.getDurability() && itemStack.getAmount() < itemStack.getMaxStackSize()) {
                return true;
            }
        }
        return false;

    }
}
