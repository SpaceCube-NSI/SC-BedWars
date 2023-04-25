package fr.mathis_bruel.spacecube.bedwars.generator;

import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.game.State;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class RunnableGenerators extends BukkitRunnable {
    public Arena arena;
    int timerDiamondMap = 0;
    int timerEmeraldMap = 0;
    boolean sendParticule = false;

    @Override
    public void run() {

        if(Manager.getManager(arena).getManagerState().getCurrentState() == State.ENDED) {
            cancel();
            return;
        }

        if (timerDiamondMap >= GeneratorType.DIAMOND_MAP.getLevel(0)) {
            timerDiamondMap = 0;
            sendParticule = true;
            for (Generator generator : arena.getDiamondsGenerators()){
                // get how many items of diamond in generator location
                //int lenght = generator.getLocation().getWorld().getNearbyEntities(generator.getLocation(), 3, 3, 3).stream().filter(entity -> entity instanceof ItemStack && ((ItemStack) entity).getType() == Material.DIAMOND).toArray().length;
                //if(lenght <= 3) {
                    Location loc = generator.getLocation().clone().add(0, 2, 0);
                    loc.getWorld().dropItem(loc, new ItemStack(Material.DIAMOND)).setVelocity(new Vector(0, 0, 0));
                    generator.updateHologram(timerDiamondMap);
                //}
            }
        } else {
            for (Generator generator : arena.getDiamondsGenerators()){
                generator.updateHologram(timerDiamondMap);
            }
            timerDiamondMap++;
        }

        if (timerEmeraldMap >= GeneratorType.EMERALD_MAP.getLevel(0)) {
            timerEmeraldMap = 0;
            sendParticule = true;
            for (Generator generator : arena.getEmeraldsGenerators()){
                int emeraldCount = 0;

                for (Entity entity : generator.getLocation().getWorld().getNearbyEntities(generator.getLocation(), 3, 3, 3)) {
                    if (entity.getType() == EntityType.DROPPED_ITEM) {
                        ItemStack itemStack = ((Item) entity).getItemStack();
                        if (itemStack.getType() == Material.EMERALD) {
                            emeraldCount += itemStack.getAmount();
                        }
                    }
                }
                if(emeraldCount <= 1) {
                    Location loc = generator.getLocation().clone().add(0, 2, 0);
                    loc.getWorld().dropItem(loc, new ItemStack(Material.EMERALD)).setVelocity(new Vector(0, 0, 0));
                    generator.updateHologram(timerEmeraldMap);
                }
            }
        } else {
            for (Generator generator : arena.getEmeraldsGenerators()){
                generator.updateHologram(timerEmeraldMap);
            }
            timerEmeraldMap++;
        }

        if(sendParticule){
            for (Generator generator : arena.getEmeraldsGenerators()){
                Location loc = generator.getLocation().clone().add(0, 1.8, 0);
                new ParticleBuilder(ParticleEffect.FLAME, loc)
                        .setSpeed(0.1f)
                        .setAmount(10)
                        .display();
                loc.getWorld().playSound(loc, Sound.LEVEL_UP, 1, 1);
            }

            for (Generator generator : arena.getDiamondsGenerators()){
                Location loc = generator.getLocation().clone().add(0, 1.8, 0);
                new ParticleBuilder(ParticleEffect.FLAME, loc)
                        .setSpeed(0.1f)
                        .setAmount(10)
                        .display();
                loc.getWorld().playSound(loc, Sound.LEVEL_UP, 1, 1);
            }
            sendParticule = false;


        }
    }
}
