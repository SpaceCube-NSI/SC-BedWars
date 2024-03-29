package fr.mathis_bruel.spacecube.bedwars.game;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.generator.Generator;
import fr.mathis_bruel.spacecube.bedwars.generator.GeneratorType;
import fr.mathis_bruel.spacecube.bedwars.manager.NPCManager;
import fr.mathis_bruel.spacecube.bedwars.manager.TypeShop;
import fr.mathis_bruel.spacecube.bedwars.teams.GeneratorTeam;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class Arena {
    private World world;
    private String name;
    private int playerPerTeam;
    private ArrayList<Team> teams;
    private ArrayList<Generator> emeraldsGenerators;
    private ArrayList<Generator> diamondsGenerators;
    private Location specSpawn;
    private Location lobbySpawn;
    private Location theSpecialist;
    private Location enchanter;
    private int maxPlayers;
    private int minPlayers;
    private double protectionRadius;
    private Location pos1Map;
    private Location pos2Map;
    private final HashMap<UUID, TypeShop> shops = new HashMap<>();
    private ArrayList<NPC> npcs = new ArrayList<>();
    private boolean enable = false;

    public Arena(World world, String name) {
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
        protectionRadius = 0;
        pos1Map = null;
        pos2Map = null;
    }

    public Arena(World world, String name, int playerPerTeam, ArrayList<Team> teams, ArrayList<Generator> emeraldsGenerators, ArrayList<Generator> diamondsGenerators, Location specSpawn, Location lobbySpawn, int maxPlayers, int minPlayers, Location theSpecialist, Location enchanter, double protectionRadius, Location pos1Map, Location pos2Map) {
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
        this.theSpecialist = theSpecialist;
        this.enchanter = enchanter;
        this.protectionRadius = protectionRadius;
        this.pos1Map = pos1Map;
        this.pos2Map = pos2Map;

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

    public Team getTeamByName(String name) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(name))
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

    public Location getTheSpecialist() {
        return theSpecialist;
    }

    public void setTheSpecialist(Location theSpecialist) {
        this.theSpecialist = theSpecialist;
    }

    public Location getEnchanter() {
        return enchanter;
    }

    public void setEnchanter(Location enchanter) {
        this.enchanter = enchanter;
    }


    public void addDiamondsGenerator(Generator generator) {
        this.diamondsGenerators.add(generator);
    }

    public void removeDiamondsGenerator(Generator generator) {
        this.diamondsGenerators.remove(generator);
    }

    public Generator getDiamondGenerator(Location location) {
        // if ~ 1 blocks
        for (Generator generator : getDiamondsGenerators()) {
            if (generator.getLocation().distance(location) < 1)
                return generator;
        }
        return null;
    }

    public Generator getEmeraldGenerator(Location location) {
        // if ~ 1 blocks
        for (Generator generator : getEmeraldsGenerators()) {
            if (generator.getLocation().distance(location) < 1)
                return generator;
        }
        return null;
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

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Team team : teams) {
            players.addAll(team.getPlayers());
        }
        return players;
    }

    public double getProtectionRadius() {
        return protectionRadius;
    }

    public void setProtectionRadius(double protectionRadius) {
        this.protectionRadius = protectionRadius;
    }

    public Location getPos1Map() {
        return pos1Map;
    }

    public void setPos1Map(Location pos1Map) {
        this.pos1Map = pos1Map;
    }

    public Location getPos2Map() {
        return pos2Map;
    }

    public void setPos2Map(Location pos2Map) {
        this.pos2Map = pos2Map;
    }


    public HashMap<UUID, TypeShop> getShops() {
        return shops;
    }

    public TypeShop getShop(UUID uuid) {
        return shops.get(uuid);
    }

    public void addShop(UUID uuid, TypeShop shop) {
        shops.put(uuid, shop);
    }

    public void removeShop(int id) {
        shops.remove(id);
    }

    public void clearShops() {
        shops.clear();
    }

    public void setNpcs(ArrayList<NPC> npcs) {
        this.npcs = npcs;
    }

    public ArrayList<NPC> getNpcs() {
        return npcs;
    }

    public void addNpc(NPC npc) {
        npcs.add(npc);
    }

    public void removeNpc(NPCManager npc) {
        npcs.remove(npc);
    }

    public void clearNpcs() {
        npcs.clear();
    }

    public boolean isEnabled() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isPossibleForEnable(){
        AtomicReference<Boolean> possibleForEnable = new AtomicReference<>(true);
        if (this.getName() == null) {
            possibleForEnable.set(false);
        }
        if (this.getWorld() == null) {
            possibleForEnable.set(false);
        }
        if (this.getLobbySpawn() == null) {
            possibleForEnable.set(false);
        }
        if (this.getSpecSpawn() == null) {
            possibleForEnable.set(false);
        }
        if (this.getTheSpecialist() == null) {
            possibleForEnable.set(false);
        }
        if (this.getEnchanter() == null) {
            possibleForEnable.set(false);
        }
        if (this.getPos1Map() == null) {
            possibleForEnable.set(false);
        }
        if (this.getPos2Map() == null) {
            possibleForEnable.set(false);
        }
        if (this.getTeams().size() == 0) {
            possibleForEnable.set(false);
        }
        if (this.getEmeraldsGenerators().size() == 0) {
            possibleForEnable.set(false);
        }
        if (this.getDiamondsGenerators().size() == 0) {
            possibleForEnable.set(false);
        }
        if (this.getPlayerPerTeam() == 0) {
            possibleForEnable.set(false);
        }
        if (this.getProtectionRadius() == 0) {
            possibleForEnable.set(false);
        }
        this.teams.forEach(team -> {
            if (team.getSpawn() == null) {
                possibleForEnable.set(false);
            }
            if (team.getBed() == null) {
                possibleForEnable.set(false);
            }
            if(team.getGenerators().size() == 0){
                possibleForEnable.set(false);
            }
            if(team.getPnjItems() == null){
                possibleForEnable.set(false);
            }
            if(team.getPnjUpgrades() == null){
                possibleForEnable.set(false);
            }
        });
        return possibleForEnable.get();

    }

    public ArrayList<String> getReasonNotEnabled(){
        ArrayList<String> reasons = new ArrayList<>();
        if (this.getName() == null) {
            reasons.add("Name");
        }
        if (this.getWorld() == null) {
            reasons.add("World");
        }
        if (this.getLobbySpawn() == null) {
            reasons.add("LobbySpawn");
        }
        if (this.getSpecSpawn() == null) {
            reasons.add("SpecSpawn");
        }
        if (this.getTheSpecialist() == null) {
            reasons.add("TheSpecialist");
        }
        if (this.getEnchanter() == null) {
            reasons.add("Enchanter");
        }
        if (this.getPos1Map() == null) {
            reasons.add("Pos1Map");
        }
        if (this.getPos2Map() == null) {
            reasons.add("Pos2Map");
        }
        if (this.getTeams().size() == 0) {
            reasons.add("Teams");
        }
        if (this.getEmeraldsGenerators().size() == 0) {
            reasons.add("EmeraldsGenerators");
        }
        if (this.getDiamondsGenerators().size() == 0) {
            reasons.add("DiamondsGenerators");
        }
        if (this.getPlayerPerTeam() == 0) {
            reasons.add("PlayerPerTeam");
        }
        if (this.getProtectionRadius() == 0) {
            reasons.add("ProtectionRadius");
        }
        this.teams.forEach(team -> {
            if (team.getSpawn() == null) {
                reasons.add("TeamSpawn " + team.getName());
            }
            if (team.getBed() == null) {
                reasons.add("TeamBed " + team.getName());
            }
            if(team.getGenerators().size() == 0){
                reasons.add("TeamGenerators " + team.getName());
            }
            if(team.getPnjItems() == null){
                reasons.add("TeamPnjItems " + team.getName());
            }
            if(team.getPnjUpgrades() == null){
                reasons.add("TeamPnjUpgrades " + team.getName());
            }
        });
        return reasons;
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
        this.setTheSpecialist(null);
        this.setEnchanter(null);
        this.setProtectionRadius(0);
        this.setPos1Map(null);
        this.setPos2Map(null);

    }

    public void delete() {
        // delete file
        File file = new File(Main.getInstance().getDataFolder() + "/arenas/" + this.getName() + ".yml");
        if (file.delete())
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
        config.set("enabled", this.isEnabled());
        if (this.getSpecSpawn() != null) config.set("specSpawn", Utils.parseLocToString(this.getSpecSpawn()));
        if (this.getLobbySpawn() != null) config.set("lobbySpawn", Utils.parseLocToString(this.getLobbySpawn()));
        for (int i = 0; i < this.getTeams().size(); i++) {
            // save all information of team
            Team team = this.getTeams().get(i);
            config.set("teams." + i + ".name", team.getName());
            config.set("teams." + i + ".color", team.getColor().name());
            if (team.getSpawn() != null) config.set("teams." + i + ".spawn", Utils.parseLocToString(team.getSpawn()));
            if (team.getBed() != null)
                config.set("teams." + i + ".bed", Utils.parseLocToString(team.getBed().getLocation()));
            if (team.getPlayers() != null) config.set("teams." + i + ".players", team.getPlayers());
            for (int j = 0; j < team.getGenerators().size(); j++) {
                // save all information of generator
                GeneratorTeam generator = team.getGenerator(j);
                config.set("teams." + i + ".generators." + j + ".location", Utils.parseLocToString(generator.getLocation()));
            }
            // save pnj
            if (team.getPnjItems() != null)
                config.set("teams." + i + ".pnjItems", Utils.parseLocToString(team.getPnjItems()));
            if (team.getPnjUpgrades() != null)
                config.set("teams." + i + ".pnjUpgrades", Utils.parseLocToString(team.getPnjUpgrades()));


        }
        for (int i = 0; i < this.getEmeraldsGenerators().size(); i++) {
            config.set("emeraldsGenerators." + i + ".location", Utils.parseLocToString(this.getEmeraldsGenerators().get(i).getLocation()));
        }
        for (int i = 0; i < this.getDiamondsGenerators().size(); i++) {
            config.set("diamondsGenerators." + i + ".location", Utils.parseLocToString(this.getDiamondsGenerators().get(i).getLocation()));
        }
        if (this.getTheSpecialist() != null)
            config.set("theSpecialist", Utils.parseLocToString(this.getTheSpecialist()));
        if (this.getEnchanter() != null) config.set("enchanter", Utils.parseLocToString(this.getEnchanter()));
        config.set("protectionRadius", this.getProtectionRadius());
        if (this.getPos1Map() != null) config.set("pos1Map", Utils.parseLocToString(this.getPos1Map()));
        if (this.getPos2Map() != null) config.set("pos2Map", Utils.parseLocToString(this.getPos2Map()));

        try {
            config.save(file);
            Main.getInstance().arenas.add(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void init() {
        // load all arenas and add in list in Main class
        File folder = new File("plugins/SC_BedWars/arenas/");
        File[] files = folder.listFiles();
        for (File file : files) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            Arena arena = new Arena(Bukkit.getWorld(config.getString("world")), config.getString("name"));
            arena.setPlayerPerTeam(config.getInt("playerPerTeam"));
            if (config.getString("specSpawn") != null)
                arena.setSpecSpawn(Utils.parseStringToLoc(config.getString("specSpawn")));
            if (config.getString("lobbySpawn") != null)
                arena.setLobbySpawn(Utils.parseStringToLoc(config.getString("lobbySpawn")));
            if (config.getInt("maxPlayers") != 0) arena.setMaxPlayers(config.getInt("maxPlayers"));
            if (config.getInt("minPlayers") != 0) arena.setMinPlayers(config.getInt("minPlayers"));
            if (config.getBoolean("enabled")) arena.setEnable(config.getBoolean("enabled"));

            if (config.getConfigurationSection("teams") != null)
                for (String key : config.getConfigurationSection("teams").getKeys(false)) {
                    Team team = new Team(config.getString("teams." + key + ".name"), Utils.getColor(config.getString("teams." + key + ".color")));
                    if (config.getString("teams." + key + ".spawn") != null)
                        team.setSpawn(Utils.parseStringToLoc(config.getString("teams." + key + ".spawn")));
                    if (config.getString("teams." + key + ".bed") != null) {
                        Location bedLoc = Utils.parseStringToLoc(config.getString("teams." + key + ".bed"));
                        System.out.println(key + " "+ config.getString("name"));
                        team.setBed(bedLoc.getBlock());
                    }
                    if (config.getString("teams." + key + ".generators") != null) {
                        for (String key2 : config.getConfigurationSection("teams." + key + ".generators").getKeys(false)) {
                            GeneratorTeam generator = new GeneratorTeam(Utils.parseStringToLoc(config.getString("teams." + key + ".generators." + key2 + ".location")));
                            generator.setLevelDiamond(0);
                            generator.setLevelGold(0);
                            generator.setLevelIron(1);
                            team.addGenerator(generator);
                        }
                    }
                    if (config.getString("teams." + key + ".pnjItems") != null)
                        team.setPnjItems(Utils.parseStringToLoc(config.getString("teams." + key + ".pnjItems")));
                    if (config.getString("teams." + key + ".pnjUpgrades") != null)
                        team.setPnjUpgrades(Utils.parseStringToLoc(config.getString("teams." + key + ".pnjUpgrades")));
                    arena.addTeam(team);
                }
            if (config.getConfigurationSection("emeraldsGenerators") != null) {
                for (String key : config.getConfigurationSection("emeraldsGenerators").getKeys(false)) {
                    Generator generator = new Generator(GeneratorType.EMERALD_MAP, 1, Utils.parseStringToLoc(config.getString("emeraldsGenerators." + key + ".location")));
                    arena.addEmeraldsGenerator(generator);
                }
            } else {
                arena.setEmeraldsGenerators(new ArrayList<Generator>());
            }
            if (config.getConfigurationSection("diamondsGenerators") != null) {
                for (String key : config.getConfigurationSection("diamondsGenerators").getKeys(false)) {
                    Generator generator = new Generator(GeneratorType.DIAMOND_MAP, 1, Utils.parseStringToLoc(config.getString("diamondsGenerators." + key + ".location")));
                    arena.addDiamondsGenerator(generator);
                }
            } else {
                arena.setDiamondsGenerators(new ArrayList<Generator>());
            }

            if (config.getString("theSpecialist") != null)
                arena.setTheSpecialist(Utils.parseStringToLoc(config.getString("theSpecialist")));
            if (config.getString("enchanter") != null)
                arena.setEnchanter(Utils.parseStringToLoc(config.getString("enchanter")));
            if (config.getDouble("protectionRadius") != 0)
                arena.setProtectionRadius(config.getDouble("protectionRadius"));
            if (config.getString("pos1Map") != null)
                arena.setPos1Map(Utils.parseStringToLoc(config.getString("pos1Map")));
            if (config.getString("pos2Map") != null)
                arena.setPos2Map(Utils.parseStringToLoc(config.getString("pos2Map")));

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
        if (arena != null) {
            return arena;
        }
        return null;
    }

    // by world
    public static Arena getArenaByWorld(World world) {
        for (Arena arena : Main.getInstance().arenas) {
            if (arena.getWorld().equals(world)) {
                return arena;
            }
        }
        return null;
    }


}
