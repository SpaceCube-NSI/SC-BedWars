package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.manager.scoreboard.FastBoard;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Join implements Listener {
    @EventHandler
    public void joinPlayer(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setLevel(0);
        player.setAllowFlight(false);
        player.setFlying(false);
        FastBoard board = new FastBoard(event.getPlayer());
        board.updateTitle("§6§lBedWars");
        board.updateLines(Arrays.asList(
                "§f",
                "§fNiveau: §f" + 0,
                "§f",
                "§fProgrès: §b" + 0 + "§7/§a"+ 0,
                "§8[§7 §a▊▊▊                §8]",
                "§f",
                "§fCoins: §e" + 0,
                "§f",
                "§fKills: §a" + 0,
                "§fDeaths: §a" + 0,
                "§fWins: §a" + 0,
                "§fStreak: §a" + 0,
                "§f",
                "§6§lwww.spacecube.games"

        ));
        Main.addBoard(player.getUniqueId(), board);
        player.teleport(Utils.parseStringToLoc(Main.getInstance().getConfig().getString("lobby")));
        player.getInventory().clear();
        ItemStack headPlayer = Utils.getHead(player);
        ItemMeta headPlayerMeta = headPlayer.getItemMeta();
        headPlayerMeta.setDisplayName("§6Your stats");
        headPlayerMeta.setLore(Arrays.asList("Click for see your stats", "§7Kills: §a0", "§7Deaths: §c0", "§7K/D: §e0"));
        headPlayer.setItemMeta(headPlayerMeta);
        player.getInventory().setItem(4, headPlayer);


    }
}
