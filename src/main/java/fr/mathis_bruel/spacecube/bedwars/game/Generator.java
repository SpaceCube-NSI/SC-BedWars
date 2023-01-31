package fr.mathis_bruel.spacecube.bedwars.game;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum Generator {
    //  generatorType(Matrerial.type, Levels[0,1,2,3,..])
    iron(Material.IRON_INGOT, Arrays.asList(5.0, 2.5, 2.0)),
    gold(Material.GOLD_INGOT, Arrays.asList(5.0, 2.5, 2.0)),
    diamond(Material.DIAMOND, Arrays.asList(10.0, 5.0, 2.5)),
    emerald(Material.EMERALD, Arrays.asList(20.0, 15.0, 13.5));

    public Material material;
    public List<Double> levels;
    public int level = 0;
    public Location location;

    Generator(Material material, List<Double> levels) {
        this.material = material;
        this.levels = levels;
    }

    public Material getMaterial() {
        return material;
    }

    public List<Double> getLevels() {
        return levels;
    }

    public double getLevel(int level) {
        return levels.get(level);
    }

    public int getLevelSize() {
        return levels.size();
    }

    public static Generator getGenerator(Material material) {
        for (Generator generator : values()) {
            if (generator.getMaterial() == material) {
                return generator;
            }
        }
        return null;
    }

    public static Generator getGenerator(String name) {
        for (Generator generator : values()) {
            if (generator.name().equalsIgnoreCase(name)) {
                return generator;
            }
        }
        return null;
    }

    public static boolean isGenerator(Material material) {
        for (Generator generator : values()) {
            if (generator.getMaterial() == material) {
                return true;
            }
        }
        return false;
    }

    public static boolean isGenerator(String name) {
        for (Generator generator : values()) {
            if (generator.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static Generator getGeneratorFromLocation(Location location) {
        for (Generator generator : values()) {
            if (generator.location.equals(location)) {
                return generator;
            }
        }
        return null;
    }

    public static boolean isGeneratorLocation(Location location) {
        for (Generator generator : values()) {
            if (generator.location.equals(location)) {
                return true;
            }
        }
        return false;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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




}
