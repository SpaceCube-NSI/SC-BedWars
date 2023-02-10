package fr.mathis_bruel.spacecube.bedwars.utils;

import fr.mathis_bruel.spacecube.bedwars.Main;
import org.bukkit.inventory.ItemStack;

public enum Heads {
    next(10195),
    ;


    private int id;

    Heads(int i) {
        this.id = i;
    }

    public int getId() {
        return id;
    }

    public ItemStack getHead(){
        return Main.getHdb().getItemHead(String.valueOf(id));
    }
}
