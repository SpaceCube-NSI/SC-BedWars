package fr.mathis_bruel.spacecube.bedwars.generator;

import fr.mathis_bruel.spacecube.bedwars.teams.GeneratorTeam;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class RunnableGenerators extends BukkitRunnable {
    public Team team;
    public GeneratorTeam generatorTeam;

    public int timerIron = 0;
    public int timerGold = 0;
    public int timerDiamond = 0;
    public int timerDiamondMap = 0;
    public int timerEmeraldMap = 0;

    @Override
    public void run() {

        if (timerIron >= GeneratorType.IRON.getLevel(generatorTeam.getLevelIron()) && generatorTeam.getLevelIron() != 0) {
            timerIron = 0;
            // spawn a iron ingot at the generator location
            Location loc = generatorTeam.getLocation();
            loc.getWorld().dropItem(loc, new ItemStack(Material.IRON_INGOT)).setVelocity(new Vector(0, 0, 0));
        } else {
            timerIron++;
        }

        if (timerGold >= GeneratorType.GOLD.getLevel(generatorTeam.getLevelGold()) && generatorTeam.getLevelGold() != 0) {
            timerGold = 0;
            // spawn a iron ingot at the generator location
            Location loc = generatorTeam.getLocation();
            loc.getWorld().dropItem(loc, new ItemStack(Material.GOLD_INGOT)).setVelocity(new Vector(0, 0, 0));
        } else {
            timerGold++;
        }

        if (timerDiamond >= GeneratorType.DIAMOND.getLevel(generatorTeam.getLevelDiamond()) && generatorTeam.getLevelDiamond() != 0) {
            timerDiamond = 0;
            // spawn a iron ingot at the generator location
            Location loc = generatorTeam.getLocation();
            loc.getWorld().dropItem(loc, new ItemStack(Material.DIAMOND)).setVelocity(new Vector(0, 0, 0));
        } else {
            timerDiamond++;
        }

        if (timerDiamondMap >= GeneratorType.DIAMOND_MAP.getLevel(0)) {
            timerDiamondMap = 0;
            // spawn a iron ingot at the generator location
            Location loc = generatorTeam.getLocation();
            loc.getWorld().dropItem(loc, new ItemStack(Material.DIAMOND)).setVelocity(new Vector(0, 0, 0));
        } else {
            timerDiamondMap++;
        }

        if (timerEmeraldMap >= GeneratorType.EMERALD_MAP.getLevel(0)) {
            timerEmeraldMap = 0;
            // spawn a iron ingot at the generator location
            Location loc = generatorTeam.getLocation();
            loc.getWorld().dropItem(loc, new ItemStack(Material.EMERALD)).setVelocity(new Vector(0, 0, 0));
        } else {
            timerEmeraldMap++;
        }

    }
}
