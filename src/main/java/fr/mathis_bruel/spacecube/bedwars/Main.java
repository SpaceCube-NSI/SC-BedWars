package fr.mathis_bruel.spacecube.bedwars;

import fr.mathis_bruel.spacecube.bedwars.commands.Bedwars;
import fr.mathis_bruel.spacecube.bedwars.commands.BedwarsAdmin;
import fr.mathis_bruel.spacecube.bedwars.commands.Test;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.manager.Hologram;
import fr.mathis_bruel.spacecube.bedwars.manager.ListenerManager;
import fr.mathis_bruel.spacecube.bedwars.manager.TypeShop;
import fr.mathis_bruel.spacecube.bedwars.manager.scoreboard.FastBoard;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Main extends JavaPlugin implements Listener {
    private ListenerManager listenerManager;
    public static Main instance;
    public static String prefix = "§6[§eBedwars§6]§r ";
    public ArrayList<Arena> arenas = new ArrayList<>();
    public static HeadDatabaseAPI hdb;
    public ArrayList<Manager> managers = new ArrayList<>();
    private static final Map<UUID, FastBoard> boards = new HashMap<>();
    private static final HashMap<Integer, TypeShop> shops = new HashMap<>();
    public static ArrayList<Hologram> holograms = new ArrayList<>();
    public static ArrayList<Player> playersFreeze = new ArrayList<>();
    @Override
    public void onEnable() {

        /*RegisteredListener registeredListener = new RegisteredListener(this, (listener, event) -> {
            System.out.println(event.getEventName());
        }, EventPriority.NORMAL, this, false);
        for (HandlerList handler : HandlerList.getHandlerLists())
            handler.register(registeredListener);*/
        // Plugin startup logic
        this.listenerManager = new ListenerManager(this);
        this.listenerManager.registerListener();
        this.getCommand("test").setExecutor(new Test());
        this.getCommand("bedwars").setExecutor(new Bedwars());
        this.getCommand("bedwars-a").setExecutor(new BedwarsAdmin());
        instance = this;
        saveDefaultConfig();
        Arena.init();
        Manager.init();
        hdb = new HeadDatabaseAPI();
        Bukkit.getOnlinePlayers().forEach(player -> {
            FastBoard board = new FastBoard(player);
            board.updateTitle("§6§lBedWars");
            board.updateLines(Arrays.asList(
                    "§f",
                    "§fNiveau: §f" + 0,
                    "§f",
                    "§fProgrès: §b" + 0 + "§7/§a"+ 0,
                    "§8[§7 §a▊▊▊                §8]",
                    "§f",
                    "§fCoins: §e" + 0,
                    "§f",
                    "§fKills: §a" + 0,
                    "§fDeaths: §a" + 0,
                    "§fWins: §a" + 0,
                    "§fStreak: §a" + 0,
                    "§f",
                    "§6§lwww.spacecube.games"

            ));
            addBoard(player.getUniqueId(), board);
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        holograms.forEach(Hologram::destroyHolograms);
    }

    public static String getPrefix() {
        return prefix;
    }

    public static Main getInstance() {
        return instance;
    }

    public static HeadDatabaseAPI getHdb() {
        return hdb;
    }

    public static  Map<UUID, FastBoard> getBoards() {
        return boards;
    }

    public static FastBoard getBoard(UUID uuid) {
        return boards.get(uuid);
    }

    public static void addBoard(UUID uuid, FastBoard board) {
        boards.put(uuid, board);
    }

    public static void removeBoard(UUID uuid) {
        boards.remove(uuid);
    }

    public static void clearBoards() {
        boards.clear();
    }

    public static HashMap<Integer, TypeShop> getShops() {
        return shops;
    }

    public static TypeShop getShop(int id) {
        return shops.get(id);
    }

    public static void addShop(int id, TypeShop shop) {
        shops.put(id, shop);
    }

    public static void removeShop(int id) {
        shops.remove(id);
    }

    public static void clearShops() {
        shops.clear();
    }

    public static void addHologram(Hologram hologram) {
        holograms.add(hologram);
    }

    public static void removeHologram(Hologram hologram) {
        holograms.remove(hologram);
    }

    public static void clearHolograms() {
        holograms.clear();
    }

    public static void addPlayerFreeze(Player player) {
        playersFreeze.add(player);
    }

    public static void removePlayerFreeze(Player player) {
        playersFreeze.remove(player);
    }

    public static void clearPlayersFreeze() {
        playersFreeze.clear();
    }

    public static boolean isPlayerFreeze(Player player) {
        return playersFreeze.contains(player);
    }



}
