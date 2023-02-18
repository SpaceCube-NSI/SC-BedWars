package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.gui.Join;
import fr.mathis_bruel.spacecube.bedwars.gui.JoinChoice;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals( Join.getInventory().getName())) Join.execute(event);
        if(event.getInventory().getName().equals(JoinChoice.getInventory().getName())) JoinChoice.execute(event);


    }

}
