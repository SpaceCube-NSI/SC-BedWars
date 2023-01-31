package fr.mathis_bruel.spacecube.bedwars.game;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Arena {
    private World world;
    private String name;
    private int teamsNumber;
    private int playerPerTeam;
    private ArrayList<Team> teams;
    private ArrayList<Generator> emeraldsGenerators;
    private ArrayList<Generator> diamondsGenerators;
    private Location specSpawn;
    private Location lobbySpawn;
    private Location lobbyPos1;
    private Location lobbyPos2;
    private ArrayList<Player> players;

    public Arena(World world, String name, int teamsNumber, int playerPerTeam, ArrayList<Team> teams, ArrayList<Generator> emeraldsGenerators, ArrayList<Generator> diamondsGenerators, Location specSpawn, Location lobbySpawn, Location lobbyPos1, Location lobbyPos2) {
        this.world = world;
        this.name = name;
        this.teamsNumber = teamsNumber;
        this.playerPerTeam = playerPerTeam;
        this.teams = teams;
        this.emeraldsGenerators = emeraldsGenerators;
        this.diamondsGenerators = diamondsGenerators;
        this.specSpawn = specSpawn;
        this.lobbySpawn = lobbySpawn;
        this.lobbyPos1 = lobbyPos1;
        this.lobbyPos2 = lobbyPos2;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamsNumber() {
        return teamsNumber;
    }

    public void setTeamsNumber(int teamsNumber) {
        this.teamsNumber = teamsNumber;
    }

    public int getPlayerPerTeam() {
        return playerPerTeam;
    }

    public void setPlayerPerTeam(int playerPerTeam) {
        this.playerPerTeam = playerPerTeam;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Generator> getEmeraldsGenerators() {
        return emeraldsGenerators;
    }

    public void setEmeraldsGenerators(ArrayList<Generator> emeraldsGenerators) {
        this.emeraldsGenerators = emeraldsGenerators;
    }

    public ArrayList<Generator> getDiamondsGenerators() {
        return diamondsGenerators;
    }

    public void setDiamondsGenerators(ArrayList<Generator> diamondsGenerators) {
        this.diamondsGenerators = diamondsGenerators;
    }

    public Location getSpecSpawn() {
        return specSpawn;
    }

    public void setSpecSpawn(Location specSpawn) {
        this.specSpawn = specSpawn;
    }

    public Location getLobbySpawn() {
        return lobbySpawn;
    }

    public void setLobbySpawn(Location lobbySpawn) {
        this.lobbySpawn = lobbySpawn;
    }

    public Location getLobbyPos1() {
        return lobbyPos1;
    }

    public void setLobbyPos1(Location lobbyPos1) {
        this.lobbyPos1 = lobbyPos1;
    }

    public Location getLobbyPos2() {
        return lobbyPos2;
    }

    public void setLobbyPos2(Location lobbyPos2) {
        this.lobbyPos2 = lobbyPos2;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public void removeTeam(Team team) {
        this.teams.remove(team);
    }

    public void addEmeraldsGenerator(Generator generator) {
        this.emeraldsGenerators.add(generator);
    }

    public void removeEmeraldsGenerator(Generator generator) {
        this.emeraldsGenerators.remove(generator);
    }

    public void addDiamondsGenerator(Generator generator) {
        this.diamondsGenerators.add(generator);
    }

    public void removeDiamondsGenerator(Generator generator) {
        this.diamondsGenerators.remove(generator);
    }

    public void addPlayer() {
        this.playerPerTeam++;
    }

    public void removePlayer() {
        this.playerPerTeam--;
    }

    public void addTeam() {
        this.teamsNumber++;
    }

    public void removeTeam() {
        this.teamsNumber--;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void clearPlayers() {
        this.players.clear();
    }

    public void clearTeams() {
        this.teams.clear();
    }

    public void clearEmeraldsGenerators() {
        this.emeraldsGenerators.clear();
    }

    public void clearDiamondsGenerators() {
        this.diamondsGenerators.clear();
    }

    public void clearAll() {
        this.clearPlayers();
        this.clearTeams();
        this.clearEmeraldsGenerators();
        this.clearDiamondsGenerators();
    }

    public void reset() {
        this.clearAll();
        this.setTeamsNumber(0);
        this.setPlayerPerTeam(0);
        this.setWorld(null);
        this.setName(null);
        this.setSpecSpawn(null);
        this.setLobbySpawn(null);
        this.setLobbyPos1(null);
        this.setLobbyPos2(null);
    }

    public void save() {
        File file = new File("plugins/BedWars/arenas/" + this.getName() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("world", this.getWorld().getName());
        config.set("name", this.getName());
        config.set("teamsNumber", this.getTeamsNumber());
        config.set("playerPerTeam", this.getPlayerPerTeam());
        config.set("specSpawn", this.getSpecSpawn());
        config.set("lobbySpawn", this.getLobbySpawn());
        config.set("lobbyPos1", this.getLobbyPos1());
        config.set("lobbyPos2", this.getLobbyPos2());
        for (int i = 0; i < this.getTeams().size(); i++) {
            config.set("teams." + i + ".name", this.getTeams().get(i).getName());
            config.set("teams." + i + ".color", this.getTeams().get(i).getColor());
            config.set("teams." + i + ".spawn", this.getTeams().get(i).getSpawn());
            config.set("teams." + i + ".bed", this.getTeams().get(i).getBed());
        }
        for (int i = 0; i < this.getEmeraldsGenerators().size(); i++) {
            config.set("emeraldsGenerators." + i + ".location", this.getEmeraldsGenerators().get(i).getLocation());
            config.set("emeraldsGenerators." + i + ".level", this.getEmeraldsGenerators().get(i).getLevel());
        }
        for (int i = 0; i < this.getDiamondsGenerators().size(); i++) {
            config.set("diamondsGenerators." + i + ".location", this.getDiamondsGenerators().get(i).getLocation());
            config.set("diamondsGenerators." + i + ".level", this.getDiamondsGenerators().get(i).getLevel());
        }
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
