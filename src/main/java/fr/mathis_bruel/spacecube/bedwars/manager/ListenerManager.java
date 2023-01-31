package fr.mathis_bruel.spacecube.bedwars.manager;

import fr.mathis_bruel.spacecube.bedwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {

    private List<Listener> listeners = new ArrayList();

    Main main;

    public ListenerManager(Main main) { this.main = main;}

    public void registerListener() {


        this.listeners.forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, main);
        });
    }


}