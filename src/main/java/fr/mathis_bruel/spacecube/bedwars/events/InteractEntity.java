package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopItems;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopSpeEnchanter;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopSpeTheSpecialist;
import fr.mathis_bruel.spacecube.bedwars.gui.ShopUpgrades;
import fr.mathis_bruel.spacecube.bedwars.manager.NPCManager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;

public class InteractEntity implements org.bukkit.event.Listener {


    @EventHandler
    public void onInteractEntity(NPCRightClickEvent event){
        if(!Manager.isCurrentlyInGame(event.getClicker())) return;
        Manager manager = Manager.getManager(event.getClicker());
        Team team = manager.getTeam(event.getClicker());

        NPCManager npc = Main.getNpc(event.getNPC().getUniqueId());

        switch(manager.getArena().getShops().get(event.getNPC().getUniqueId())) {
            case ITEMS:
                event.getClicker().openInventory(ShopItems.getInventory(team));
                break;
            case UPGRADES:
                event.getClicker().openInventory(ShopUpgrades.getInventory(team));
                break;
            case THE_SPECIALIST:
                event.getClicker().openInventory(ShopSpeTheSpecialist.getInventory());
                break;
            case ENCHANTER:
                event.getClicker().openInventory(ShopSpeEnchanter.getInventory());
                break;
        }

        if(Main.getInstance().npcSpawn.containsKey(npc)){
            event.getClicker().chat("/bw join " + Main.getInstance().npcSpawn.get(npc));
        }

    }
    @EventHandler
    public void onInteractEntity(NPCLeftClickEvent event){
        if(!Manager.isCurrentlyInGame(event.getClicker())) return;
        Manager manager = Manager.getManager(event.getClicker());
        Team team = manager.getTeam(event.getClicker());

        NPCManager npc = Main.getNpc(event.getNPC().getUniqueId());

        switch(manager.getArena().getShops().get(event.getNPC().getUniqueId())) {
            case ITEMS:
                event.getClicker().openInventory(ShopItems.getInventory(team));
                break;
            case UPGRADES:
                event.getClicker().openInventory(ShopUpgrades.getInventory(team));
                break;
            case THE_SPECIALIST:
                event.getClicker().openInventory(ShopSpeTheSpecialist.getInventory());
                break;
            case ENCHANTER:
                event.getClicker().openInventory(ShopSpeEnchanter.getInventory());
                break;
        }

        if(Main.getInstance().npcSpawn.containsKey(npc)){
            event.getClicker().chat("/bw join " + Main.getInstance().npcSpawn.get(npc));
        }

    }
}
