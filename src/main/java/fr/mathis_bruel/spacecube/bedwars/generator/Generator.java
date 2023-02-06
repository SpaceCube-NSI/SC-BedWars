package fr.mathis_bruel.spacecube.bedwars.generator;

import org.bukkit.Location;

public class Generator {
    private GeneratorType type;
    private int level;
    private Location location;

    public Generator(GeneratorType type, int level, Location location) {
        this.type = type;
        this.level = level;
        this.location = location;
    }

    public GeneratorType getType() {
        return type;
    }

    public void setType(GeneratorType type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addLevel() {
        this.level++;
    }

    public void removeLevel() {
        this.level--;
    }

    public Location getLocation() {
        return location;
    }

    public Location setLocation(Location location) {
        return this.location = location;
    }
}
