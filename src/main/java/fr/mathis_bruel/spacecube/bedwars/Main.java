package fr.mathis_bruel.spacecube.bedwars;

import fr.mathis_bruel.spacecube.bedwars.commands.Bedwars;
import fr.mathis_bruel.spacecube.bedwars.commands.BedwarsAdmin;
import fr.mathis_bruel.spacecube.bedwars.commands.Test;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.manager.ListenerManager;
import fr.mathis_bruel.spacecube.bedwars.manager.scoreboard.FastBoard;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Main extends JavaPlugin {
    private ListenerManager listenerManager;
    public static Main instance;
    public static String prefix = "§6[§eBedwars§6]§r ";
    public ArrayList<Arena> arenas = new ArrayList<>();
    public static HeadDatabaseAPI hdb;
    public ArrayList<Manager> managers = new ArrayList<>();
    private static final Map<UUID, FastBoard> boards = new HashMap<>();

    @Override
    public void onEnable() {
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





}
