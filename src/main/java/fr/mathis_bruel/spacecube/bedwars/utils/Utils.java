package fr.mathis_bruel.spacecube.bedwars.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.internal.LocalWorldAdapter;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.registry.WorldData;
import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Location;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                lore.add("§7PlayerPerTeam: §a" + manager.getArena().getPlayerPerTeam());
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

    public static void saveSchem(String filename, int x1, int y1, int z1, int x2, int y2, int z2, org.bukkit.World world) throws WorldEditException, FileNotFoundException {

        Vector min = new Vector(x1, y1, z1);
        Vector max = new Vector(x2, y2, z2);
        com.sk89q.worldedit.world.World weWorld = new BukkitWorld(world);
        LocalWorld localWorld = LocalWorldAdapter.adapt(weWorld);
        CuboidRegion region = new CuboidRegion(localWorld, min, max);
        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
        clipboard.setOrigin(min);

        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(region.getWorld(), -1);


        ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());
        forwardExtentCopy.setCopyEntities(false);
        Operations.complete(forwardExtentCopy);
        File dataDirectory = new File(Main.getInstance().getDataFolder(), "maps");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
        File file = new File(dataDirectory, filename + ".schematic"); // The schematic file
        try (ClipboardWriter writer = ClipboardFormat.SCHEMATIC.getWriter(new FileOutputStream(file))) {
            writer.write(clipboard, editSession.getWorld().getWorldData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void restoreMap(String name, World world, int x, int y, int z) throws IOException, WorldEditException {
        // load and paste schematic
        File dataDirectory = new File(Main.getInstance().getDataFolder(), "maps");
        File file = new File(dataDirectory, name + ".schematic"); // The schematic file
        Vector to = new Vector(x, y, z); // Where you want to paste

        com.sk89q.worldedit.world.World weWorld = new BukkitWorld(world);
        WorldData worldData = weWorld.getWorldData();
        Clipboard clipboard = ClipboardFormat.SCHEMATIC.getReader(new FileInputStream(file)).read(worldData);
        Region region = clipboard.getRegion();

        EditSession extent = WorldEdit.getInstance().getEditSessionFactory().getEditSession(weWorld, -1);
        AffineTransform transform = new AffineTransform();

//{ // Uncomment this if you want to rotate the schematic
//    transform = transform.rotateY(90); // E.g. Rotate 90
//    extent = new BlockTransformExtent(clipboard, transform, worldData.getBlockRegistry());
//}

        ForwardExtentCopy copy = new ForwardExtentCopy(clipboard, clipboard.getRegion(), clipboard.getOrigin(), extent, to);
        if (!transform.isIdentity()) copy.setTransform(transform);
        /*if (ignoreAirBlocks) {
            copy.setSourceMask(new ExistingBlockMask(clipboard));
        }*/
        Operations.completeLegacy(copy);
        extent.flushQueue();
    }
    public static List<Block> getBlocksTo(Location startLoc, Location endLoc) {
        org.bukkit.util.Vector tnt = startLoc.toVector().clone();
        org.bukkit.util.Vector block = endLoc.toVector().clone();
        org.bukkit.util.Vector diff = tnt.subtract(block).normalize();
        List<Block> b = new ArrayList<>();
        new BukkitRunnable(){
            @Override
            public void run() {
                BlockIterator BI = new BlockIterator(endLoc.getWorld(), block, diff, 0.0d, (int) startLoc.distance(endLoc));
                while (BI.hasNext()){
                    Block blocks = BI.next();
                    if(blocks.getType() != Material.AIR) {
                        b.add(blocks);
                    }
                }
            }
        }.runTask(Main.getInstance());




        return b;
    }

    public static String[] getSkin(String id) throws IOException, ParseException {
        String skinUrl = "https://api.mineskin.org/get/uuid/" + id;
        URL url = new URL(skinUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestProperty("Accept", "application/json");

        // put in json the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // convert StringBuilder to json
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.toString());

        // get the texture value and signature
        String skinData = (String) ((JSONObject) ((JSONObject) json.get("data")).get("texture")).get("value");
        String signature = (String) ((JSONObject) ((JSONObject) json.get("data")).get("texture")).get("signature");
        return new String[]{skinData, signature};
    }

    public static ItemStack getSkull(String url) {
        ItemStack skull = new ItemStack(Material.SKULL, 1);
        if (url == null || url.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public static void changePseudoColor(Player player, Team team) {
        org.bukkit.scoreboard.Team scoreboardTeam = player.getScoreboard().getTeam(team.getName()+"_"+ Manager.getManager(player).getArena().getName());
        if (scoreboardTeam == null) {
            scoreboardTeam = player.getScoreboard().registerNewTeam(team.getName());
            // set color name
        }
        scoreboardTeam.setPrefix(team.getColor().toString());
        scoreboardTeam.addEntry(player.getName());
        Main.getInstance().teams.put(player, scoreboardTeam);
        // set tablist name
        player.setPlayerListName(team.getColor() + player.getName());
        // set name above head
        player.setCustomName(team.getColor() + player.getName());
        player.setCustomNameVisible(true);

    }

    // Fonction pour réinitialiser le pseudo d'un joueur
    public static void  resetPseudo(Player player) {
        player.setPlayerListName(player.getName());
        player.setCustomName(player.getName());
        player.setCustomNameVisible(false);
        player.getScoreboard().getTeam(Main.getInstance().teams.get(player).getName()).unregister();
        Main.getInstance().teams.remove(player);
    }




}
