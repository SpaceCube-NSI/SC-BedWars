package fr.mathis_bruel.spacecube.bedwars.game;

import fr.mathis_bruel.spacecube.bedwars.manager.Utils;
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
    private int playerPerTeam;
    private ArrayList<Team> teams;
    private ArrayList<Generator> emeraldsGenerators;
    private ArrayList<Generator> diamondsGenerators;
    private Location specSpawn;
    private Location lobbySpawn;
    private ArrayList<Player> players;

    public Arena(World world, String name){
        this.world = world;
        this.name = name;
        this.playerPerTeam = 1;
        this.teams = new ArrayList<>();
        this.emeraldsGenerators = new ArrayList<>();
        this.diamondsGenerators = new ArrayList<>();
        this.specSpawn = null;
        this.lobbySpawn = null;
        this.players = new ArrayList<>();
    }

    public Arena(World world, String name, int playerPerTeam, ArrayList<Team> teams, ArrayList<Generator> emeraldsGenerators, ArrayList<Generator> diamondsGenerators, Location specSpawn, Location lobbySpawn) {
        this.world = world;
        this.name = name;
        this.playerPerTeam = playerPerTeam;
        this.teams = teams;
        this.emeraldsGenerators = emeraldsGenerators;
        this.diamondsGenerators = diamondsGenerators;
        this.specSpawn = specSpawn;
        this.lobbySpawn = lobbySpawn;
        this.players = new ArrayList<>();
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
        this.setPlayerPerTeam(0);
        this.setWorld(null);
        this.setName(null);
        this.setSpecSpawn(null);
        this.setLobbySpawn(null);
    }

    public void save() {
        File file = new File("plugins/SC_BedWars/arenas/" + this.getName() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("world", this.getWorld().getName());
        config.set("name", this.getName());
        config.set("playerPerTeam", this.getPlayerPerTeam());
        config.set("specSpawn", this.getSpecSpawn());
        config.set("lobbySpawn", this.getLobbySpawn());
        for(int i = 0; i < this.getTeams().size(); i++) {
            // save all information of team
            Team team = this.getTeams().get(i);
            config.set("teams." + i + ".name", team.getName());
            config.set("teams." + i + ".color", team.getColor().toString());
            config.set("teams." + i + ".spawn", Utils.parseLocToString(team.getSpawn()));
            config.set("teams." + i + ".bed", Utils.parseLocToString(team.getBed().getLocation()));
            config.set("teams." + i + ".players", team.getPlayers());

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


    /*public static void init(){
        // load all arenas and add in list in Main class
        File folder = new File("plugins/SC_BedWars/arenas/");
        File[] files = folder.listFiles();
        for (File file : files) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            World world = Bukkit.getWorld(config.getString("world"));
            String name = config.getString("name");
            int playerPerTeam = config.getInt("playerPerTeam");
            Location specSpawn = (Location) config.get("specSpawn");
            Location lobbySpawn = (Location) config.get("lobbySpawn");
            ArrayList<Team> teams = new ArrayList<>();
            for (int i = 0; i < config.getConfigurationSection("teams").getKeys(false).size(); i++) {
                String teamName = config.getString("teams." + i + ".name");
                String teamColor = config.getString("teams." + i + ".color");
                Location teamSpawn = (Location) config.get("teams." + i + ".spawn");
                Block teamBed = (Block) config.get("teams." + i + ".bed");
                teams.add(new Team(teamName, Utils.getColor(teamColor), teamSpawn, teamBed));
            }
            ArrayList<Generator> emeraldsGenerators = new ArrayList<>();
            for (int i = 0; i < config.getConfigurationSection("emeraldsGenerators").getKeys(false).size(); i++) {
                Location location = (Location) config.get("emeraldsGenerators." + i + ".location");
                int level = config.getInt("emeraldsGenerators." + i + ".level");
                emeraldsGenerators.add(new Generator(location, level));
            }
            ArrayList<Generator> diamondsGenerators = new ArrayList<>();
            for (int i = 0; i < config.getConfigurationSection("diamondsGenerators").getKeys(false).size(); i++) {
                Location location = (Location) config.get("diamondsGenerators." + i + ".location");
                int level = config.getInt("diamondsGenerators." + i + ".level");
                diamondsGenerators.add(new Generator(location, level));
            }
            Main.getInstance().arenas.add(new Arena(world, name, playerPerTeam, specSpawn, lobbySpawn, teams, emeraldsGenerators, diamondsGenerators));
        }
    }
*/


}
