package fr.mathis_bruel.spacecube.bedwars;

import fr.mathis_bruel.spacecube.bedwars.manager.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private ListenerManager listenerManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.listenerManager = new ListenerManager(this);
        this.listenerManager.registerListener();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
