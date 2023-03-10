package fr.mathis_bruel.spacecube.bedwars.generator;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum GeneratorType {
    //  generatorType(Matrerial.type, Levels[0,1,2,3,..])
    IRON(Material.IRON_INGOT, Arrays.asList(0.0,5.0, 2.0, 1.0)),
    GOLD(Material.GOLD_INGOT, Arrays.asList(0.0,5.0, 2.0, 1.0)),
    DIAMOND(Material.DIAMOND, Arrays.asList(0.0,5.0, 2.0, 1.0)),
    EMERALD_MAP(Material.EMERALD, Arrays.asList(20.0)),
    DIAMOND_MAP(Material.DIAMOND, Arrays.asList(20.0));

    public Material material;
    public List<Double> levels;

    GeneratorType(Material material, List<Double> levels) {
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

    public static GeneratorType getGenerator(Material material) {
        for (GeneratorType generator : values()) {
            if (generator.getMaterial() == material) {
                return generator;
            }
        }
        return null;
    }

    public static GeneratorType getGenerator(String name) {
        for (GeneratorType generator : values()) {
            if (generator.name().equalsIgnoreCase(name)) {
                return generator;
            }
        }
        return null;
    }


    public static boolean isGenerator(Material material) {
        for (GeneratorType generator : values()) {
            if (generator.getMaterial() == material) {
                return true;
            }
        }
        return false;
    }

    public static boolean isGenerator(String name) {
        for (GeneratorType generator : values()) {
            if (generator.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public String getDisplayName() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }





}
