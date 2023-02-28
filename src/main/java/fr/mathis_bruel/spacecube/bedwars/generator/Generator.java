package fr.mathis_bruel.spacecube.bedwars.generator;

import fr.mathis_bruel.spacecube.bedwars.manager.Hologram;
import org.bukkit.Location;

public class Generator {
    private GeneratorType type;
    private int level;
    private Location location;
    private Hologram hologram;

    public Generator(GeneratorType type, int level, Location location) {
        this.type = type;
        this.level = level;
        this.location = location;
    }

    public Generator(GeneratorType type, int level, Location location, Hologram hologram) {
        this.type = type;
        this.level = level;
        this.location = location;
        this.hologram = hologram;
    }

    public Hologram getHologram() {
        return hologram;
    }

    public void setHologram(Hologram hologram) {
        this.hologram = hologram;
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

    public void updateHologram(int time){
        String text = "";
        String color = (type == GeneratorType.DIAMOND_MAP) ? "§b" : "§a";
        if(time == 0) text = color + "➤§7➤➤➤➤➤➤➤➤➤§l+";
        if(time == 2) text = color + "➤➤§7➤➤➤➤➤➤➤➤§l+";
        if(time == 4) text = color + "➤➤➤§7➤➤➤➤➤➤➤§l+";
        if(time == 6) text = color + "➤➤➤➤§7➤➤➤➤➤➤§l+";
        if(time == 8) text = color + "➤➤➤➤➤§7➤➤➤➤➤§l+";
        if(time == 10) text = color + "➤➤➤➤➤➤§7➤➤➤➤§l+";
        if(time == 12) text = color + "➤➤➤➤➤➤➤§7➤➤➤§l+";
        if(time == 14) text = color + "➤➤➤➤➤➤➤➤§7➤➤§l+";
        if(time == 16) text = color + "➤➤➤➤➤➤➤➤➤§7➤§l+";
        if(time == 18) text = color + "➤➤➤➤➤➤➤➤➤➤§7§l+";
        if(time == 19) text = color + "➤➤➤➤➤➤➤➤➤➤§l+";
        if(text != "") {
            hologram.removeLine(1);
            hologram.insertLine(1, text);
        }
    }
}
