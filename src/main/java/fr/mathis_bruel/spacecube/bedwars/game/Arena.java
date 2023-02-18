package fr.mathis_bruel.spacecube.bedwars.game;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.generator.Generator;
import fr.mathis_bruel.spacecube.bedwars.generator.GeneratorType;
import fr.mathis_bruel.spacecube.bedwars.teams.GeneratorTeam;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

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
    private int maxPlayers;
    private int minPlayers;

    public Arena(World world, String name){
        this.world = world;
        this.name = name;
        this.playerPerTeam = 1;
        this.teams = new ArrayList<>();
        this.emeraldsGenerators = new ArrayList<>();
        this.diamondsGenerators = new ArrayList<>();
        this.specSpawn = null;
        this.lobbySpawn = null;
        this.maxPlayers = 0;
        this.minPlayers = 0;
    }

    public Arena(World world, String name, int playerPerTeam, ArrayList<Team> teams, ArrayList<Generator> emeraldsGenerators, ArrayList<Generator> diamondsGenerators, Location specSpawn, Location lobbySpawn, int maxPlayers, int minPlayers) {
        this.world = world;
        this.name = name;
        this.playerPerTeam = playerPerTeam;
        this.teams = teams;
        this.emeraldsGenerators = emeraldsGenerators;
        this.diamondsGenerators = diamondsGenerators;
        this.specSpawn = specSpawn;
        this.lobbySpawn = lobbySpawn;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;

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

    public Team getTeamByName(String name){
        for(Team team : teams){
            if(team.getName().equalsIgnoreCase(name))
                return team;
        }
        return null;
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

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
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
    public void delete(){
        // delete file
        File file = new File(Main.getInstance().getDataFolder() + "/arenas/" + this.getName() + ".yml");
        if(file.delete())
            Bukkit.getLogger().info("File " + this.getName() + ".yml deleted");
        else {
            Bukkit.getLogger().warning("The file " + this.getName() + ".yml can't be deleted");
        }
        Main.getInstance().arenas.remove(this);
    }

    public void save() {
        File file = new File("plugins/SC_BedWars/arenas/" + this.getName() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("world", this.getWorld().getName());
        config.set("name", this.getName());
        config.set("playerPerTeam", this.getPlayerPerTeam());
        config.set("maxPlayers", this.getMaxPlayers());
        config.set("minPlayers", this.getMinPlayers());
        if(this.getSpecSpawn() != null) config.set("specSpawn", Utils.parseLocToString(this.getSpecSpawn()));
        if(this.getLobbySpawn() != null) config.set("lobbySpawn", Utils.parseLocToString(this.getLobbySpawn()));
        for(int i = 0; i < this.getTeams().size(); i++) {
            // save all information of team
            Team team = this.getTeams().get(i);
            config.set("teams." + i + ".name", team.getName());
            config.set("teams." + i + ".color", team.getColor().name());
            if(team.getSpawn() != null) config.set("teams." + i + ".spawn", Utils.parseLocToString(team.getSpawn()));
            if(team.getBed() != null) config.set("teams." + i + ".bed", Utils.parseLocToString(team.getBed().getLocation()));
            if(team.getPlayers() != null) config.set("teams." + i + ".players", team.getPlayers());
            for(int j = 0; j < team.getGenerators().size(); j++) {
                // save all information of generator
                GeneratorTeam generator = team.getGenerator(j);
                config.set("teams." + i + ".generators." + j + ".location", Utils.parseLocToString(generator.getLocation()));
            }
            // save pnj
            if(team.getPnjItems() != null) config.set("teams." + i + ".pnjItems", Utils.parseLocToString(team.getPnjItems()));
            if(team.getPnjUpgrades() != null) config.set("teams." + i + ".pnjUpgrades", Utils.parseLocToString(team.getPnjUpgrades()));


        }
        for (int i = 0; i < this.getEmeraldsGenerators().size(); i++) {
            config.set("emeraldsGenerators." + i + ".location", this.getEmeraldsGenerators().get(i).getLocation());
        }
        for (int i = 0; i < this.getDiamondsGenerators().size(); i++) {
            config.set("diamondsGenerators." + i + ".location", this.getDiamondsGenerators().get(i).getLocation());
        }
        try {
            config.save(file);
            Main.getInstance().arenas.add(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
















    public static void init(){
        // load all arenas and add in list in Main class
        File folder = new File("plugins/SC_BedWars/arenas/");
        File[] files = folder.listFiles();
        for (File file : files) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            Arena arena = new Arena(Bukkit.getWorld(config.getString("world")), config.getString("name"));
            arena.setPlayerPerTeam(config.getInt("playerPerTeam"));
            if(config.getString("specSpawn") != null) arena.setSpecSpawn(Utils.parseStringToLoc(config.getString("specSpawn")));
            if(config.getString("lobbySpawn") != null) arena.setLobbySpawn(Utils.parseStringToLoc(config.getString("lobbySpawn")));
            if(config.getInt("maxPlayers") != 0) arena.setMaxPlayers(config.getInt("maxPlayers"));
            if(config.getInt("minPlayers") != 0) arena.setMinPlayers(config.getInt("minPlayers"));

            if (config.getConfigurationSection("teams") != null) for (String key : config.getConfigurationSection("teams").getKeys(false)) {
                Team team = new Team(config.getString("teams." + key + ".name"), Utils.getColor(config.getString("teams." + key + ".color")));
                if(config.getString("teams." + key + ".spawn") != null)team.setSpawn(Utils.parseStringToLoc(config.getString("teams." + key + ".spawn")));
                if(config.getString("teams." + key + ".bed") != null) {
                    Location bedLoc = Utils.parseStringToLoc(config.getString("teams." + key + ".bed"));
                    team.setBed(bedLoc.getBlock());
                }
                if(config.getString("teams." + key + ".generators") != null) {
                    for (String key2 : config.getConfigurationSection("teams." + key + ".generators").getKeys(false)) {
                        GeneratorTeam generator = new GeneratorTeam(Utils.parseStringToLoc(config.getString("teams." + key + ".generators." + key2 + ".location")));
                        team.addGenerator(generator);
                    }
                }
                if(config.getString("teams." + key + ".pnjItems") != null)team.setPnjItems(Utils.parseStringToLoc(config.getString("teams." + key + ".pnjItems")));
                if(config.getString("teams." + key + ".pnjUpgrades") != null)team.setPnjUpgrades(Utils.parseStringToLoc(config.getString("teams." + key + ".pnjUpgrades")));
                arena.addTeam(team);
            }
            if(config.getConfigurationSection("emeraldsGenerators") != null) {
                for (String key : config.getConfigurationSection("emeraldsGenerators").getKeys(false)) {
                    Generator generator = new Generator(GeneratorType.EMERALD, 1, Utils.parseStringToLoc(config.getString("emeraldsGenerators." + key + ".location")));
                    arena.addEmeraldsGenerator(generator);
                }
            }else{
                arena.setEmeraldsGenerators(new ArrayList<Generator>());
            }
            if(config.getConfigurationSection("diamondsGenerators") != null) {
                for (String key : config.getConfigurationSection("diamondsGenerators").getKeys(false)) {
                    Generator generator = new Generator(GeneratorType.DIAMOND, 1, Utils.parseStringToLoc(config.getString("diamondsGenerators." + key + ".location")));
                    arena.addDiamondsGenerator(generator);
                }
            }else{
                arena.setDiamondsGenerators(new ArrayList<Generator>());
            }

            Main.getInstance().arenas.add(arena);
        }
    }

    public static Arena getArenaByName(String name) {
        for (Arena arena : Main.getInstance().arenas) {
            if (arena.getName().equalsIgnoreCase(name)) {
                return arena;
            }
        }
        return null;
    }

    public static Arena getArenaByID(int id) {
        Arena arena = Main.getInstance().arenas.get(id);
        if(arena != null){
            return arena;
        }
        return null;
    }



}
