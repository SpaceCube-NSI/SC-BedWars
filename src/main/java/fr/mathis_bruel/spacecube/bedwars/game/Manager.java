package fr.mathis_bruel.spacecube.bedwars.game;

import es.eltrueno.npc.TruenoNPC;
import es.eltrueno.npc.TruenoNPCApi;
import es.eltrueno.npc.skin.TruenoNPCSkin;
import es.eltrueno.npc.skin.TruenoNPCSkinBuilder;
import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.generator.Generator;
import fr.mathis_bruel.spacecube.bedwars.generator.RunnableGenerators;
import fr.mathis_bruel.spacecube.bedwars.manager.Hologram;
import fr.mathis_bruel.spacecube.bedwars.manager.TypeShop;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class Manager {

    private Arena arena;
    private ArrayList<Player> players;
    private ArrayList<Player> specators;
    private ManagerState managerState;
    private int startingTime;
    private ArrayList<EntityPlayer> npcs = new ArrayList<>();

    public Manager(Arena arena) {
        this.arena = arena;
        this.players = new ArrayList<>();
        this.specators = new ArrayList<>();
        this.managerState = new ManagerState(arena);
        this.startingTime = 5;
        Runnable start = new Runnable();
        start.manager = this;
        start.runTaskTimer(Main.getInstance(), 0, 20);
    }

    public ManagerState getManagerState() {
        return managerState;
    }


    public Arena getArena() {
        return arena;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public void clearPlayers(){
        players.clear();
    }

    public ArrayList<Player> getSpecators(){
        return specators;
    }

    public void addSpecator(Player player){
        this.specators.add(player);
    }

    public void removeSpecator(Player player){
        this.specators.remove(player);
    }

    public void setSpecators(ArrayList<Player> players){
        this.specators = players;
    }

    public void clearSpecators(){
        this.specators.clear();
    }

    public int getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    public void addStartingTime(int startingTime) {
        this.startingTime += startingTime;
    }

    public void removeStartingTime(int startingTime) {
        this.startingTime -= startingTime;
    }

    public void resetStartingTime() {
        this.startingTime = 30;
    }

    public boolean isSpecator(Player player){
        if(this.specators.contains(player)) return true;
        else return false;
    }

    public boolean isPlayer(Player player){
        if(this.players.contains(player)) return true;
        else return false;
    }

    public Team getTeam(Player player){
        if(isPlayer(player)){
            for(Team team : arena.getTeams()){
                if(team.getPlayers().contains(player)) return team;
            }
        }
        return null;
    }

    public ArrayList<EntityPlayer> getNpcs() {
        return npcs;
    }

    public void initGame(){
        RunnableGenerators runnableGenerators = new RunnableGenerators();
        runnableGenerators.arena = arena;
        runnableGenerators.runTaskTimer(Main.getInstance(), 0, 20);

        for(Team team : arena.getTeams()){
            TruenoNPCSkin skin = TruenoNPCSkinBuilder.fromMineskin(Main.getInstance(), "f52dc72a8953457f972c0fecd8fd553d");
            Location location = team.getPnjItems();
            Location loc2 = location.clone().add(0, 1.8, 0);
            TruenoNPC npc = TruenoNPCApi.createNPC(Main.getInstance(), location, skin);
            ArrayList<String> lines = new ArrayList<>();
            lines.add("§6§lSHOP ITEMS");
            lines.add("§7Click to open");
            Hologram hologram = new Hologram(loc2, lines);
            hologram.showHologram();

            Location location2 = team.getPnjUpgrades();

            TruenoNPCSkin skin2 = TruenoNPCSkinBuilder.fromMineskin(Main.getInstance(), "f52dc72a8953457f972c0fecd8fd553d");
            TruenoNPC npc2 = TruenoNPCApi.createNPC(Main.getInstance(), location2, skin2);
            Location loc = location2.clone().add(0, 1.8, 0);
            ArrayList<String> lines2 = new ArrayList<>();
            lines2.add("§6§lSHOP UPGRADES");
            lines2.add("§7Click to open");
            Hologram hologram2 = new Hologram(loc, lines2);
            hologram2.showHologram();

            Main.addShop(npc.getNpcID(), TypeShop.ITEMS);
            Main.addShop(npc2.getNpcID(), TypeShop.UPGRADES);

            team.getGenerators().forEach(generator -> {
                ArrayList<String> lines3 = new ArrayList<>();
                lines3.add(team.getColor()+"§l"+team.getName()+" Summoner");
                lines3.add("§f➀ Iron");
                Hologram hologram3 = new Hologram(generator.getLocation().clone().add(0, 2, 0), lines3);
                hologram3.showHologram();
                team.addGeneratorHologram(hologram3);
            });

        }

        for (Generator generator : arena.getDiamondsGenerators()) {
            ArrayList<String> lines = new ArrayList<>();
            lines.add("§bDiamonds Summoner");
            lines.add("§7➤➤➤➤➤➤➤➤➤➤§l+");
            Hologram hologram = new Hologram(generator.getLocation().clone().add(0, 2, 0), lines);
            hologram.showHologram();
            generator.setHologram(hologram);
        }

        for (Generator generator : arena.getEmeraldsGenerators()) {
            ArrayList<String> lines = new ArrayList<>();
            lines.add("§aEmeralds Summoner");
            lines.add("§7➤➤➤➤➤➤➤➤➤➤§l+");
            Hologram hologram = new Hologram(generator.getLocation().clone().add(0, 2, 0), lines);
            hologram.showHologram();
            generator.setHologram(hologram);
        }

        // create npc for arena.getTheSpecialist and arena.getEnchanter
        TruenoNPCSkin skin = TruenoNPCSkinBuilder.fromMineskin(Main.getInstance(), "f1894a3e64e64fb483ade05ddf00fdff");
        Location location = arena.getTheSpecialist();
        Location loc2 = location.clone().add(0, 1.8, 0);
        TruenoNPC npc = TruenoNPCApi.createNPC(Main.getInstance(), location, skin);
        ArrayList<String> lines = new ArrayList<>();
        lines.add("§6§lTHE SPECIALIST");
        lines.add("§7Click to open");
        Hologram hologram = new Hologram(loc2, lines);
        hologram.showHologram();
        Main.addShop(npc.getNpcID(), TypeShop.THE_SPECIALIST);

        TruenoNPCSkin skin2 = TruenoNPCSkinBuilder.fromMineskin(Main.getInstance(), "473cae4d3c8e4d20857a01f6e52076b7");
        Location location2 = arena.getEnchanter();
        Location loc3 = location2.clone().add(0, 1.8, 0);
        TruenoNPC npc2 = TruenoNPCApi.createNPC(Main.getInstance(), location2, skin2);
        ArrayList<String> lines2 = new ArrayList<>();
        lines2.add("§6§lENCHANTER");
        lines2.add("§7Click to open");
        Hologram hologram2 = new Hologram(loc3, lines2);
        hologram2.showHologram();
        Main.addShop(npc2.getNpcID(), TypeShop.ENCHANTER);



    }

    public void broadcast(String message){
        for(Player player : players){
            player.sendMessage(message);
        }
    }




    public String join(Player player){
        if(isPlayer(player) || isSpecator(player)) return "§cYou are already in this game !";
        if(Manager.isCurrentlyInGame(player)) return "§cYou are already in a game !";
        if(managerState.getCurrentState() == State.WAITING){
            addPlayer(player);
            player.teleport(arena.getLobbySpawn());
            player.getInventory().clear();
            ItemStack leave = new ItemStack(Material.BED, 1);
            ItemMeta leaveMeta = leave.getItemMeta();
            leaveMeta.setDisplayName("§4Leave game");
            leaveMeta.setLore(Arrays.asList("Click for leave this game and return in the hub"));
            leave.setItemMeta(leaveMeta);
            player.getInventory().setItem(8, leave);
            ItemStack headPlayer = Utils.getHead(player);
            ItemMeta headPlayerMeta = headPlayer.getItemMeta();
            headPlayerMeta.setDisplayName("§6Your stats");
            headPlayerMeta.setLore(Arrays.asList("Click for see your stats", "§7Kills: §a0", "§7Deaths: §c0", "§7K/D: §e0"));
            headPlayer.setItemMeta(headPlayerMeta);
            player.getInventory().setItem(4, headPlayer);
            this.players.forEach(p -> {
               p.sendMessage("§a" + player.getName() + " §7joined the game ! §7(§3" + this.players.size() + "§7/§4" + arena.getMaxPlayers() + "§7)");
            });
            return "You joined the game!";
        }
        else if(managerState.getCurrentState() == State.RUNNING){
            this.getPlayers().forEach(p -> {
                p.hidePlayer(player);
            });
            player.getInventory().clear();
            player.teleport(arena.getLobbySpawn());
            this.specators.add(player);
            ItemStack leave = new ItemStack(Material.BED, 1);
            ItemMeta leaveMeta = leave.getItemMeta();
            leaveMeta.setDisplayName("§4Leave game");
            leaveMeta.setLore(Arrays.asList("Click for leave this game and return in the hub"));
            leave.setItemMeta(leaveMeta);
            player.getInventory().setItem(8, leave);
            ItemStack settings = new ItemStack(Material.REDSTONE_COMPARATOR);
            ItemMeta settingMeta = settings.getItemMeta();
            settingMeta.setDisplayName("§6Spectator Settings");
            settingMeta.setLore(Arrays.asList("Click for open menu of spectator settings"));
            settings.setItemMeta(settingMeta);
            player.getInventory().setItem(4, settings);
            ItemStack tp = new ItemStack(Material.COMPASS);
            ItemMeta tpMeta = tp.getItemMeta();
            tpMeta.setDisplayName("§2Teleport to player");
            tpMeta.setLore(Arrays.asList("Click for open menu for teleport to other player"));
            tp.setItemMeta(tpMeta);
            player.getInventory().setItem(0, tp);
            return "You joined the game as a spectator";

        }
        else{
            return "§4Error, you don't joined this game.";
        }

    }

    public void leave(Player player) {
        this.getNpcs().forEach(npc -> {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
        });
        if (isPlayer(player)) {
            this.removePlayer(player);
            player.teleport(Utils.parseStringToLoc(Main.getInstance().getConfig().getString("lobby")));
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setFireTicks(0);
            player.setExp(0);
            player.setLevel(0);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.setGameMode(Main.getInstance().getServer().getDefaultGameMode());
            player.sendMessage("§aYou left the game!");
        } else if (isSpecator(player)) {
            this.removeSpecator(player);
            player.teleport(Utils.parseStringToLoc(Main.getInstance().getConfig().getString("lobby")));
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setFireTicks(0);
            player.setExp(0);
            player.setLevel(0);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.setGameMode(Main.getInstance().getServer().getDefaultGameMode());
            player.sendMessage("§aYou left the game!");
        } else {
            player.sendMessage("§cYou are not in this game!");
        }
    }





    public static void init(){
        Main.getInstance().arenas.forEach(arena -> {
            Main.getInstance().managers.add(new Manager(arena));
        });
    }

    public static Manager getManager(Arena arena){
        for (Manager manager : Main.getInstance().managers) {
            if(manager.getArena() == arena){
                return manager;
            }
        }
        return null;
    }

    public static boolean isCurrentlyInGame(Player player){
        for (Manager manager : Main.getInstance().managers) {
            if(manager.isPlayer(player) || manager.isSpecator(player)){
                return true;
            }
        }
        return false;
    }

    public static Manager getManager(Player player){
        for (Manager manager : Main.getInstance().managers) {
            if(manager.isPlayer(player) || manager.isSpecator(player)){
                return manager;
            }
        }
        return null;
    }




}
