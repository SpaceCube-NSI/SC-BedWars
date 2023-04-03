package fr.mathis_bruel.spacecube.bedwars.events;

import org.bukkit.event.Listener;

public class InteractNPCEvent implements Listener {


    /*@EventHandler
    public void onInteractNPC(TruenoNPCInteractEvent event){
        if(!Manager.isCurrentlyInGame(event.getPlayer())) return;
        Team team = Manager.getManager(event.getPlayer()).getTeam(event.getPlayer());
        Arena arena = Manager.getManager(event.getPlayer()).getArena();

        TruenoNPC npc = event.getNPC();
        switch(arena.getShop(npc)){
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
    }*/



}
