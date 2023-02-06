package fr.mathis_bruel.spacecube.bedwars.teams;

import org.bukkit.Location;

public class GeneratorTeam {
    private Location location;
    private int levelIron;
    private int levelGold;
    private int levelDiamond;

    public GeneratorTeam(Location location, int levelIron, int levelGold, int levelDiamond) {
        this.location = location;
        this.levelIron = levelIron;
        this.levelGold = levelGold;
        this.levelDiamond = levelDiamond;
    }
    public GeneratorTeam(Location location){
        this.location = location;
        this.levelIron = 1;
        this.levelGold = 1;
        this.levelDiamond = 1;
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
    }

    public int getLevelGold() {
        return levelGold;
    }

    public void setLevelGold(int levelGold) {
        this.levelGold = levelGold;
    }

    public int getLevelDiamond() {
        return levelDiamond;
    }

    public void setLevelDiamond(int levelDiamond) {
        this.levelDiamond = levelDiamond;
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


}
