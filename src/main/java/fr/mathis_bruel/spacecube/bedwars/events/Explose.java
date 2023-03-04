package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.game.State;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class Explose implements org.bukkit.event.Listener {

    @EventHandler
    public void onExplose(org.bukkit.event.entity.EntityExplodeEvent event) {
        List<Block> destroyed = new ArrayList<>();
        /*if (event.getEntity().getType() == EntityType.PRIMED_TNT) {
            System.out.println(1);
            event.blockList().clear();
            List<Block> toBreak = new ArrayList<>();
            for (Block value : destroyed) {
                System.out.println(2);
                boolean glass = false;
                for (Block blocks : Utils.getBlocksTo(event.getEntity().getLocation(), value.getLocation())) {
                    if (blocks.getType() == Material.STAINED_GLASS) {
                        System.out.println(4);
                        glass = true;
                        break;
                    }
                }
                if (glass) {
                    System.out.println(3);
                    toBreak.add(value);
                }
            }
            toBreak.forEach(block -> {
                System.out.println(5);
                event.getLocation().getWorld().getBlockAt(block.getLocation()).setType(Material.AIR);
            });
        }*/

        if (event.getEntity().getType() == EntityType.PRIMED_TNT) {
            System.out.println(destroyed);
            Arena arena = Arena.getArenaByWorld(event.getEntity().getWorld());
            Manager manager = Manager.getManager(arena);
            if(manager.getManagerState().getCurrentState() != State.RUNNING) return;

            destroyed.removeIf(block -> manager.isBlockNotBreakable(block));


        }

    }


}

