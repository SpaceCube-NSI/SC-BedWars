package fr.mathis_bruel.spacecube.bedwars.game;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {
    private String name;
    private Color color;
    private Location spawn;
    private Generator ironSpawner;
    private Generator goldSpawner;
    private Location pnjItems;
    private Location pnjUpgrades;
    private Block bed;
    private ArrayList<Player> players;

    public Team(String name, Color color, Location spawn, Generator ironSpawner, Generator goldSpawner, Location pnjItems, Location pnjUpgrades, Block bed) {
        this.name = name;
        this.color = color;
        this.spawn = spawn;
        this.ironSpawner = ironSpawner;
        this.goldSpawner = goldSpawner;
        this.pnjItems = pnjItems;
        this.pnjUpgrades = pnjUpgrades;
        this.bed = bed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public Generator getIronSpawner() {
        return ironSpawner;
    }

    public void setIronSpawner(Generator ironSpawner) {
        this.ironSpawner = ironSpawner;
    }

    public Generator getGoldSpawner() {
        return goldSpawner;
    }

    public void setGoldSpawner(Generator goldSpawner) {
        this.goldSpawner = goldSpawner;
    }

    public Location getPnjItems() {
        return pnjItems;
    }

    public void setPnjItems(Location pnjItems) {
        this.pnjItems = pnjItems;
    }

    public Location getPnjUpgrades() {
        return pnjUpgrades;
    }

    public void setPnjUpgrades(Location pnjUpgrades) {
        this.pnjUpgrades = pnjUpgrades;
    }

    public Block getBed() {
        return bed;
    }

    public void setBed(Block bed) {
        this.bed = bed;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public boolean isPlayerInTeam(Player player) {
        return players.contains(player);
    }

    public boolean isBedAlive() {
        return bed.getType().toString().contains("BED");
    }

    public void setBedAlive(boolean alive) {
        if(alive) {
            if(bed.getType().toString().contains("BED_BLOCK"))
                bed.setType(bed.getType());
        } else {
            if(bed.getType().toString().contains("BED"))
                bed.setType(bed.getType());
        }
    }

    public void setBedAlive() {
        setBedAlive(isBedAlive());
    }

    public void setBedAlive(boolean alive, boolean force) {
        if(force) {
            if(alive) {
                if(bed.getType().toString().contains("BED_BLOCK"))
                    bed.setType(bed.getType());
            } else {
                if(bed.getType().toString().contains("BED"))
                    bed.setType(bed.getType());
            }
        } else {
            setBedAlive(alive);
        }
    }





}
