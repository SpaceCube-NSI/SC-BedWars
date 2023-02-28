package fr.mathis_bruel.spacecube.bedwars.generator;

import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class RunnableGenerators extends BukkitRunnable {
    public Arena arena;
    int timerDiamondMap = 0;
    int timerEmeraldMap = 0;
    @Override
    public void run() {

        if (timerDiamondMap >= GeneratorType.DIAMOND_MAP.getLevel(0)) {
            timerDiamondMap = 0;
            for (Generator generator : arena.getDiamondsGenerators()){
                Location loc = generator.getLocation().clone().add(0, 2, 0);
                loc.getWorld().dropItem(loc, new ItemStack(Material.DIAMOND)).setVelocity(new Vector(0, 0, 0));
                generator.updateHologram(timerDiamondMap);
            }
        } else {
            for (Generator generator : arena.getDiamondsGenerators()){
                generator.updateHologram(timerDiamondMap);
            }
            timerDiamondMap++;
        }

        if (timerEmeraldMap >= GeneratorType.EMERALD_MAP.getLevel(0)) {
            timerEmeraldMap = 0;
            for (Generator generator : arena.getEmeraldsGenerators()){
                Location loc = generator.getLocation().clone().add(0, 2, 0);
                loc.getWorld().dropItem(loc, new ItemStack(Material.EMERALD)).setVelocity(new Vector(0, 0, 0));
                generator.updateHologram(timerEmeraldMap);
            }
        } else {
            for (Generator generator : arena.getEmeraldsGenerators()){
                generator.updateHologram(timerEmeraldMap);
            }
            timerEmeraldMap++;
        }
    }
}
