package fr.mathis_bruel.spacecube.bedwars.utils;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.CuboidRegion;
import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import org.bukkit.*;
import org.bukkit.Location;
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
    /*public static void saveSchematic(World world, Vector pos1, Vector pos2) throws IOException, WorldEditException {
        CuboidRegion region = new CuboidRegion(toBlockVector(pos1), toBlockVector(pos2));
        com.sk89q.worldedit.world.World weWorld = BukkitAdapter.adapt(world);
        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession( world, -1);
        // create temp.schematic if not exist
        File temp = new File("plugins/SC-BedWars/Save-maps/temp.schematic");
        if (!temp.exists()) {
            temp.createNewFile();
        }
        Clipboard clipboard = ClipboardFormat.SCHEMATIC.getReader(new FileInputStream("plugins/SC-BedWars/Save-maps/temp.schematic")).read(editSession.getWorld().getWorldData());

        File file = new File("plugins/SC-BedWars/Save-maps/", "schematic_" + System.currentTimeMillis() + ".schematic");
        file.getParentFile().mkdirs();

        ClipboardFormat format = ClipboardFormat.SCHEMATIC;
        SchematicWriter writer = (SchematicWriter) format.getWriter(new FileOutputStream(file));
        writer.write(clipboard, editSession.getWorld().getWorldData());
        writer.close();
    }


    private BaseBlock air() {
        return new BaseBlock(BlockID.AIR);
    }

    private static Vector toBlockVector(Vector vector) {
        return Vector.toBlockPoint(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ());
    }*/

    public static void saveSchem(String filename, int x1, int y1, int z1, int x2, int y2, int z2, org.bukkit.World world) throws WorldEditException {
        /*com.sk89q.worldedit.world.World weWorld = new BukkitWorld(world);
        WorldData worldData = weWorld.getWorldData();
        Vector pos1 = new Vector(x1, y1, z1); //First corner of your cuboid
        Vector pos2 = new Vector(x2, y2, z2); //Second corner fo your cuboid
        CuboidRegion cReg = new CuboidRegion(weWorld, pos1, pos2);
        File dataDirectory = new File (Main.getInstance().getDataFolder(), "maps");
        File file = new File(dataDirectory, filename + ".schematic"); // The schematic file
        try {
            BlockArrayClipboard clipboard = new BlockArrayClipboard(cReg);
            Extent source = WorldEdit.getInstance().getEditSessionFactory().getEditSession(weWorld, -1);
            Extent destination = clipboard;
            ForwardExtentCopy copy = new ForwardExtentCopy(source, cReg, clipboard.getOrigin(), destination, pos1);
            copy.setSourceMask(new ExistingBlockMask(source));
            Operations.completeLegacy(copy);
            ClipboardFormat.SCHEMATIC.getWriter(new FileOutputStream(file)).write(clipboard, worldData);
        } catch (IOException | MaxChangedBlocksException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }*/
        Vector min = new Vector(x1, y1, z1);
        Vector max = new Vector(x2, y2, z2);
        LocalWorld localWorld = BukkitAdapter.adapt(world);
        CuboidRegion region = new CuboidRegion(world, min, max);
        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);

        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(region.getWorld(), -1);

        ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());
        forwardExtentCopy.setCopyEntities(true);
        Operations.complete(forwardExtentCopy);
    }
}
