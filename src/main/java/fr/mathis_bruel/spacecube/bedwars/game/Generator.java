package fr.mathis_bruel.spacecube.bedwars.game;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public enum Generator {
    //  generatorType(Matrerial.type, Levels[0,1,2,3,..])
    iron(Material.IRON_INGOT, Arrays.asList(5,2.5,2)),
    gold(Material.GOLD_INGOT, Arrays.asList(5,2.5,2)),
    diamond(Material.DIAMOND, Arrays.asList(10,5,2.5)),
    emerald(Material.EMERALD, Arrays.asList(20,15,13.5));




}
