package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.game.State;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class ItemSpawn implements org.bukkit.event.Listener {

    @EventHandler
    public void onSpawnItem(org.bukkit.event.entity.ItemSpawnEvent event) {
        Location loc = event.getLocation();
        Arena arena = Arena.getArenaByWorld(loc.getWorld());
        if(arena == null) return;
        Manager manager = Manager.getManager(arena);
        if(manager == null) return;
        if(manager.getManagerState().getCurrentState() != State.RUNNING) return;
        if(event.getEntity().getItemStack().getType() == Material.BED) event.setCancelled(true);
    }

}
