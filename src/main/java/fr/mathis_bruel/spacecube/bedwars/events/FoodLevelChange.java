package fr.mathis_bruel.spacecube.bedwars.events;

import org.bukkit.event.EventHandler;

public class FoodLevelChange implements org.bukkit.event.Listener {

    @EventHandler
    public void onFoodLevelChange(org.bukkit.event.entity.FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

}
