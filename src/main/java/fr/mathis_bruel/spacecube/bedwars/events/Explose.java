package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.game.State;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;

import java.util.List;

public class Explose implements org.bukkit.event.Listener {

    @EventHandler
    public void onExplose(org.bukkit.event.entity.EntityExplodeEvent event) {

        if (event.getEntity().getType() == EntityType.PRIMED_TNT) {
            Arena arena = Arena.getArenaByWorld(event.getEntity().getWorld());
            Manager manager = Manager.getManager(arena);
            if(manager.getManagerState().getCurrentState() != State.RUNNING) return;

            List<Block> destroyed = event.blockList();
            destroyed.forEach(block -> {
                if(manager.isBlockNotBreakable(block)) {
                    destroyed.remove(block);
                }
            });

        }

    }

}
