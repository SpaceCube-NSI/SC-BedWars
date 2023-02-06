package fr.mathis_bruel.spacecube.bedwars.manager;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;

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

    public static Color getColor(String string){
        switch (string){
            case "RED":
                return Color.RED;
            case "BLUE":
                return Color.BLUE;
            case "GREEN":
                return Color.GREEN;
            case "YELLOW":
                return Color.YELLOW;
            case "WHITE":
                return Color.WHITE;
            case "BLACK":
                return Color.BLACK;
            case "AQUA":
                return Color.AQUA;
            case "FUCHSIA":
                return Color.FUCHSIA;
            case "GRAY":
                return Color.GRAY;
            case "LIME":
                return Color.LIME;
            case "MAROON":
                return Color.MAROON;
            case "NAVY":
                return Color.NAVY;
            case "OLIVE":
                return Color.OLIVE;
            case "ORANGE":
                return Color.ORANGE;
            case "PURPLE":
                return Color.PURPLE;
            case "SILVER":
                return Color.SILVER;
            case "TEAL":
                return Color.TEAL;
            default:
                return Color.WHITE;
        }
    }

    public static boolean isNumber(String string){
        try{
            Integer.parseInt(string);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }


}
