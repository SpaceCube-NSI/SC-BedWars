package fr.mathis_bruel.spacecube.bedwars.teams;

import fr.mathis_bruel.spacecube.bedwars.generator.RunnableGeneratorsTeams;
import org.bukkit.Location;

public class GeneratorTeam {
    private Location location;
    private int levelIron;
    private int levelGold;
    private int levelDiamond;
    private RunnableGeneratorsTeams runnableGenerators;

    public GeneratorTeam(Location location, int levelIron, int levelGold, int levelDiamond, RunnableGeneratorsTeams runnableGenerators) {
        this.location = location;
        this.levelIron = levelIron;
        this.levelGold = levelGold;
        this.levelDiamond = levelDiamond;
        this.runnableGenerators = runnableGenerators;
    }
    public GeneratorTeam(Location location){
        this.location = location;
        this.levelIron = 1;
        this.levelGold = 0;
        this.levelDiamond = 0;
        this.runnableGenerators = null;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getLevelIron() {
        return levelIron;
    }

    public void setLevelIron(int levelIron) {

        this.levelIron = levelIron;
        if(runnableGenerators != null) {
            this.runnableGenerators.timerDiamond = 0;
            this.runnableGenerators.timerGold = 0;
            this.runnableGenerators.timerIron = 0;
        }
    }

    public int getLevelGold() {
        return levelGold;
    }

    public void setLevelGold(int levelGold) {
        this.levelGold = levelGold;
        if(runnableGenerators != null) {
            this.runnableGenerators.timerDiamond = 0;
            this.runnableGenerators.timerGold = 0;
            this.runnableGenerators.timerIron = 0;
        }
    }

    public int getLevelDiamond() {
        return levelDiamond;
    }

    public void setLevelDiamond(int levelDiamond) {
        this.levelDiamond = levelDiamond;
        if(runnableGenerators != null) {
            this.runnableGenerators.timerDiamond = 0;
            this.runnableGenerators.timerGold = 0;
            this.runnableGenerators.timerIron = 0;
        }
    }

    public void upgradeIron() {
        this.levelIron++;
    }

    public void upgradeGold() {
        this.levelGold++;
    }

    public void upgradeDiamond() {
        this.levelDiamond++;
    }

    public void downgradeIron() {
        this.levelIron--;
    }

    public void downgradeGold() {
        this.levelGold--;
    }

    public void downgradeDiamond() {
        this.levelDiamond--;
    }

    public RunnableGeneratorsTeams getRunnableGenerators() {
        return runnableGenerators;
    }

    public void setRunnableGenerators(RunnableGeneratorsTeams runnableGenerators) {
        this.runnableGenerators = runnableGenerators;
    }


}
