package fr.mathis_bruel.spacecube.bedwars.teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class Team {
    private String name;
    private ChatColor ChatColor;
    private Location spawn;
    private ArrayList<GeneratorTeam> generators;
    private Location pnjItems;
    private Location pnjUpgrades;
    private Block bed;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Inventory enderchest;
    private Boolean isBedAlive = true;

    public Team(String name, ChatColor ChatColor, Location spawn, ArrayList<GeneratorTeam> generators, Location pnjItems, Location pnjUpgrades, Block bed) {
        this.name = name;
        this.ChatColor = ChatColor;
        this.spawn = spawn;
        this.pnjItems = pnjItems;
        this.pnjUpgrades = pnjUpgrades;
        this.bed = bed;
        this.generators = generators;
        this.enderchest = Bukkit.createInventory(null, 3 * 9, ChatColor + "Enderchest" + name);

    }

    public Team(String name, ChatColor ChatColor) {
        this.name = name;
        this.ChatColor = ChatColor;
        this.spawn = null;
        this.pnjItems = null;
        this.pnjUpgrades = null;
        this.bed = null;
        this.generators = new ArrayList<>();
        this.enderchest = Bukkit.createInventory(null, 3 * 9, ChatColor + "Enderchest " + name);
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

    public GeneratorTeam getGenerator(Location location) {
        // if ~ 1 blocks
        for (GeneratorTeam generatorTeam : generators) {
            if (generatorTeam.getLocation().distance(location) < 1)
                return generatorTeam;
        }
        return null;
    }

    public boolean isGenerator(Location location) {
        return getGenerator(location) != null;
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

    public Inventory getEnderchest() {
        return enderchest;
    }

    public void setEnderchest(Inventory enderchest) {
        this.enderchest = enderchest;
    }

    public void addEnderchestItem(int slot, org.bukkit.inventory.ItemStack itemStack) {
        enderchest.setItem(slot, itemStack);
    }

    public void removeEnderchestItem(int slot) {
        enderchest.setItem(slot, null);
    }

    public void clearEnderchest() {
        enderchest.clear();
    }

    public boolean isPlayerInTeam(Player player) {
        return players.contains(player);
    }

    public boolean isBedAlive() {
        return isBedAlive;
    }

    public void setBedAlive(boolean bedAlive) {
        isBedAlive = bedAlive;
    }

    public void destroyBed(){
        bed.breakNaturally();
        isBedAlive = false;
    }

    public void respawnBed(){
        bed.setType(org.bukkit.Material.BED_BLOCK);
        isBedAlive = true;
    }

    public boolean isBedIsBreak(){
        return bed.getType() != org.bukkit.Material.BED_BLOCK;
    }


}
