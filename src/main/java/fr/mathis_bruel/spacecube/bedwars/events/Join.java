package fr.mathis_bruel.spacecube.bedwars.events;

import fr.Mathis_Bruel.spacecube.spacecubeapi.api.games.Games;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.games.Stats;
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
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

public class Join implements Listener {
    @EventHandler
    public void joinPlayer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setLevel(0);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.getInventory().setArmorContents(null);
        FastBoard board = new FastBoard(event.getPlayer());
        Stats state = new Stats(Games.BedWars, player.getUniqueId());
        state.init();
        board.updateTitle("§6§lSpaceCube");
        board.updateLines(Arrays.asList(
                "§7§m-----------§6§m-----------",
                "§7             §eBedWars",
                "§f",
                "§fKills: §a" + state.getKills(),
                "§fDeaths: §a" + state.getDeath(),
                "§fWins: §a" + state.getWins(),
                "§fStreak: §a" + 0,
                "§f",
                "§7§m-----------§6§m-----------",
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
        player.getInventory().setItem(2, headPlayer);
        for (PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());
        Utils.resetPseudo(player);
    }
}
