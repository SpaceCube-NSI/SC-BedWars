package fr.mathis_bruel.spacecube.bedwars.teams;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {
    private String name;
    private ChatColor ChatColor;
    private Location spawn;
    private ArrayList<GeneratorTeam> generators;
    private Location pnjItems;
    private Location pnjUpgrades;
    private Block bed;
    private ArrayList<Player> players;

    public Team(String name, ChatColor ChatColor, Location spawn, ArrayList<GeneratorTeam> generators, Location pnjItems, Location pnjUpgrades, Block bed) {
        this.name = name;
        this.ChatColor = ChatColor;
        this.spawn = spawn;
        this.pnjItems = pnjItems;
        this.pnjUpgrades = pnjUpgrades;
        this.bed = bed;
        this.generators = generators;

    }

    public Team(String name, ChatColor ChatColor){
        this.name = name;
        this.ChatColor = ChatColor;
        this.spawn = null;
        this.pnjItems = null;
        this.pnjUpgrades = null;
        this.bed = null;
        this.generators = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatColor getColor() {
        return ChatColor;
    }

    public void setColor(ChatColor ChatColor) {
        this.ChatColor = ChatColor;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public ArrayList<GeneratorTeam> getGenerators() {
        return generators;
    }

    public void addGenerator(GeneratorTeam generatorTeam) {
        this.generators.add(generatorTeam);
    }

    public void removeGenerator(GeneratorTeam generatorTeam) {
        this.generators.remove(generatorTeam);
    }

    public void setGenerators(ArrayList<GeneratorTeam> generatorTeam) {
        this.generators = generatorTeam;
    }

    public GeneratorTeam getGenerator(int index) {
        return this.generators.get(index);
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
