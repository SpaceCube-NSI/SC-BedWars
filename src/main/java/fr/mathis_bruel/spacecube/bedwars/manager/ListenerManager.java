package fr.mathis_bruel.spacecube.bedwars.manager;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.events.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {

    private List<Listener> listeners = new ArrayList();

    Main main;

    public ListenerManager(Main main) { this.main = main;}

    public void registerListener() {

        // Register all listeners here
        this.listeners.add(new BlockPlace());
        this.listeners.add(new BlockBreak());
        this.listeners.add(new InventoryClick());
        this.listeners.add(new Click());
        this.listeners.add(new Join());
        this.listeners.add(new Leave());
        this.listeners.add(new InteractEntity());
        this.listeners.add(new InteractNPCEvent());
        this.listeners.add(new PlayerMove());
        this.listeners.add(new EntityDamageByEntity());
        this.listeners.add(new OpenChest());
        this.listeners.add(new Explose());
        this.listeners.add(new ItemSpawn());
        this.listeners.add(new FoodLevelChange());
        this.listeners.add(new InventoryClose());
        this.listeners.add(new EntitySpawn());
        this.listeners.add(new PlayerInteract());



        this.listeners.forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, main);
        });
    }


}