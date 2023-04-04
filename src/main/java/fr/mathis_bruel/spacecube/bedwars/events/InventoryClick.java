package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.gui.*;
import fr.mathis_bruel.spacecube.bedwars.gui.Join;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals(ShopSpeEnchanter.getInventory().getName())) ShopSpeEnchanter.execute(event);
        if(event.getInventory().getName().equals( Join.getInventory().getName())) Join.execute(event);
        if(event.getInventory().getName().equals(JoinChoice.getInventory().getName())) JoinChoice.execute(event);
        if(event.getInventory().getName().equals(JoinChoiceSolo.getInventory().getName())) JoinChoiceSolo.execute(event);
        if(event.getInventory().getName().equals(JoinChoiceDouble.getInventory().getName())) JoinChoiceDouble.execute(event);
        if(event.getInventory().getName().equals(JoinChoiceTriple.getInventory().getName())) JoinChoiceTriple.execute(event);
        if(event.getInventory().getName().equals(JoinChoiceQuatre.getInventory().getName())) JoinChoiceQuatre.execute(event);
        if(event.getInventory().getName().equals(Stats.getInventory((Player) event.getWhoClicked()).getName())) Stats.execute(event);
        if(Manager.isCurrentlyInGame((Player) event.getWhoClicked())){
            Team team = Manager.getManager((Player) event.getWhoClicked()).getTeam((Player) event.getWhoClicked());
            if(team == null) return;
            if(event.getInventory().getName().equals(ShopItems.getInventory(team).getName())) ShopItems.execute(event);
            if(event.getInventory().getName().equals(ShopItemsBlock.getInventory(team).getName())) ShopItemsBlock.execute(event);
            if(event.getInventory().getName().equals(ShopItemsArmor.getInventory().getName())) ShopItemsArmor.execute(event);
            if(event.getInventory().getName().equals(ShopItemsTools.getInventory().getName())) ShopItemsTools.execute(event);
            if(event.getInventory().getName().equals(ShopUpgrades.getInventory(team).getName())) ShopUpgrades.execute(event);
            if(event.getInventory().getName().equals(ShopSpeTheSpecialist.getInventory().getName())) ShopSpeTheSpecialist.execute(event);
            if(event.getInventory().getName().equals(ShopUpgradesSummoner.getInventory(team).getName())) ShopUpgradesSummoner.execute(event);
            if(event.getInventory().getName().equals(ShopUpgradesBase.getInventory(team).getName())) ShopUpgradesBase.execute(event);
        }


    }

}
