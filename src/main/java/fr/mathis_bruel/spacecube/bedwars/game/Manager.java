package fr.mathis_bruel.spacecube.bedwars.game;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.Material;
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

    public Manager(Arena arena) {
        this.arena = arena;
        this.players = new ArrayList<>();
        this.specators = new ArrayList<>();
        this.managerState = new ManagerState(arena);
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

    public boolean isSpecator(Player player){
        if(this.specators.contains(player)) return true;
        else return false;
    }

    public boolean isPlayer(Player player){
        if(this.players.contains(player)) return true;
        else return false;
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
