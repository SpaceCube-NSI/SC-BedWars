package fr.mathis_bruel.spacecube.bedwars.events;

import es.eltrueno.npc.TruenoNPC;
import es.eltrueno.npc.event.TruenoNPCInteractEvent;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopEnchanter;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopItems;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopTheSpecialist;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopUpgrades;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InteractNPCEvent implements Listener {


    @EventHandler
    public void onInteractNPC(TruenoNPCInteractEvent event){
        if(!Manager.isCurrentlyInGame(event.getPlayer())) return;
        Team team = Manager.getManager(event.getPlayer()).getTeam(event.getPlayer());
        Arena arena = Manager.getManager(event.getPlayer()).getArena();

        TruenoNPC npc = event.getNPC();
        switch(arena.getShop(npc.getNpcID())){
            case ITEMS:
                event.getPlayer().openInventory(ShopItems.getInventory(team));
                break;
            case UPGRADES:
                event.getPlayer().openInventory(ShopUpgrades.getInventory(team));
                break;
            case THE_SPECIALIST:
                event.getPlayer().openInventory(ShopTheSpecialist.getInventory());
                break;
            case ENCHANTER:
                event.getPlayer().openInventory(ShopEnchanter.getInventory());
                break;
        }
    }



}
