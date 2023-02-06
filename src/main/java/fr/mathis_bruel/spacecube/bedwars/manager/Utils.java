package fr.mathis_bruel.spacecube.bedwars.manager;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

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
        return new ChatColor[] {ChatColor.RED, ChatColor.BLUE, ChatColor.GREEN, ChatColor.YELLOW, ChatColor.WHITE, ChatColor.BLACK, ChatColor.DARK_RED, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_AQUA, ChatColor.DARK_PURPLE, ChatColor.DARK_GRAY, ChatColor.GOLD, ChatColor.AQUA, ChatColor.LIGHT_PURPLE, ChatColor.GRAY};
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


}
