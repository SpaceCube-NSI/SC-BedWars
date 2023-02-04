package fr.mathis_bruel.spacecube.bedwars;

import fr.mathis_bruel.spacecube.bedwars.commands.Bedwars;
import fr.mathis_bruel.spacecube.bedwars.commands.BedwarsAdmin;
import fr.mathis_bruel.spacecube.bedwars.commands.Test;
import fr.mathis_bruel.spacecube.bedwars.manager.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private ListenerManager listenerManager;
    public static Main instance;
    public static String prefix = "§6[§eBedwars§6]§r ";

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


}
