package fr.mathis_bruel.spacecube.bedwars.utils;

import fr.mathis_bruel.spacecube.bedwars.Main;
import org.bukkit.inventory.ItemStack;

public enum Heads {
    next_green(10195),
    next_orange(9655),
    next_red(9331),
    up_red(9328),
    up_green(10192)

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
