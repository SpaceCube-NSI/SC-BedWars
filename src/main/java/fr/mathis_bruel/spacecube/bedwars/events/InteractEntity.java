package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopItems;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopSpeEnchanter;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopSpeTheSpecialist;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopUpgrades;
import fr.mathis_bruel.spacecube.bedwars.manager.NPCManager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.event.EventHandler;

public class InteractEntity implements org.bukkit.event.Listener {


    @EventHandler
    public void onInteractEntity(org.bukkit.event.player.PlayerInteractEntityEvent event){
        System.out.println("InteractEntity, "+ event.getPlayer().getName() + " "+ event.getRightClicked().getName() + " " + event.getRightClicked().getUniqueId());


        if(!Manager.isCurrentlyInGame(event.getPlayer())) return;
        Manager manager = Manager.getManager(event.getPlayer());
        Team team = manager.getTeam(event.getPlayer());

        NPCManager npc = Main.getNpc(event.getRightClicked().getUniqueId());

        switch(manager.getArena().getShops().get(event.getRightClicked().getUniqueId())) {
            case ITEMS:
                event.getPlayer().openInventory(ShopItems.getInventory(team));
                break;
            case UPGRADES:
                event.getPlayer().openInventory(ShopUpgrades.getInventory(team));
                break;
            case THE_SPECIALIST:
                event.getPlayer().openInventory(ShopSpeTheSpecialist.getInventory());
                break;
            case ENCHANTER:
                event.getPlayer().openInventory(ShopSpeEnchanter.getInventory());
                break;
        }

    }
}
