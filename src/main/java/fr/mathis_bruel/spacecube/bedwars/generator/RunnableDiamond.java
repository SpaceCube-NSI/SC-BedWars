package fr.mathis_bruel.spacecube.bedwars.generator;

import fr.mathis_bruel.spacecube.bedwars.teams.GeneratorTeam;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RunnableDiamond extends BukkitRunnable {
    public Team team;
    public GeneratorType generatorType;
    public GeneratorTeam generatorTeam;

    private int timer = 0;
    @Override
    public void run() {

        if(timer >= generatorType.getLevel(generatorTeam.getLevelDiamond()) && generatorTeam.getLevelDiamond() != 0){
            System.out.println(generatorTeam.getLevelDiamond());
            timer = 0;
            // spawn a iron ingot at the generator location
            Location loc = generatorTeam.getLocation();
            loc.getWorld().dropItem(loc, new ItemStack(Material.DIAMOND));
        } else {
            timer++;
        }



    }
}