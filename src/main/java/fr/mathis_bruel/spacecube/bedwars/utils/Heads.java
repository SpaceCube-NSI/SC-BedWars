package fr.mathis_bruel.spacecube.bedwars.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public enum Heads {
    next_green("Green Arrow Right", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYzMzlmZjJlNTM0MmJhMThiZGM0OGE5OWNjYTY1ZDEyM2NlNzgxZDg3ODI3MmY5ZDk2NGVhZDNiOGFkMzcwIn19fQ==", true),
    next_orange("Orange Arrow Right", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmU4Y2Q1MzY2NGQ5MzA3YjY4NjliOWFiYmFlMmI3NzM3YWI3NjJiYjE4YmIzNGYzMWM1Y2E4ZjNlZGI2M2I2In19fQ==", true),
    next_red("Red Arrow Right", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNmZTg4NDVhOGQ1ZTYzNWZiODc3MjhjY2M5Mzg5NWQ0MmI0ZmMyZTZhNTNmMWJhNzhjODQ1MjI1ODIyIn19fQ==", true),
    up_red(" Red Arrow Up", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQ5Mjg3NjE2MzQzZDgzM2U5ZTczMTcxNTljYWEyY2IzZTU5NzQ1MTEzOTYyYzEzNzkwNTJjZTQ3ODg4NGZhIn19fQ==", true),
    up_green(" Green Arrow Up", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWRhMDI3NDc3MTk3YzZmZDdhZDMzMDE0NTQ2ZGUzOTJiNGE1MWM2MzRlYTY4YzhiN2JjYzAxMzFjODNlM2YifX19", true),
    ARROW_LEFT_OAK("Oak Left Arrow", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==", true),
    ARROW_RIGHT_OAK("Oak Right Arrow", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTliZjMyOTJlMTI2YTEwNWI1NGViYTcxM2FhMWIxNTJkNTQxYTFkODkzODgyOWM1NjM2NGQxNzhlZDIyYmYifX19", true),
    ARROW_UP_OAK("Oak Up Arrow", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzA0MGZlODM2YTZjMmZiZDJjN2E5YzhlYzZiZTUxNzRmZGRmMWFjMjBmNTVlMzY2MTU2ZmE1ZjcxMmUxMCJ9fX0=", true),
    ARROW_DOWN_OAK("Oak Down Arrow", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQzNzM0NmQ4YmRhNzhkNTI1ZDE5ZjU0MGE5NWU0ZTc5ZGFlZGE3OTVjYmM1YTEzMjU2MjM2MzEyY2YifX19", true),
    GREEN_CHEST("Green Chest", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGZlOGRhZmZlMzVjYTE4MzJmZjk5ZWVhZDQ2MTdhMzY5ZWEwMzNjMjRiODI1M2Y1MTFmZDhiOTE3MzdhN2UifX19", true),
    MISC_EARTH("Earth", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTFiMzE4OGZkNDQ5MDJmNzI2MDJiZDdjMjE0MWY1YTcwNjczYTQxMWFkYjNkODE4NjJjNjllNTM2MTY2YiJ9fX0=", true),
    MISC_HOURGLASS("Hourglass", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYThhOGMyZmUzMGVmOTQwOGZjOTYyYWUwYzVlZGU5YjQ3OGI5MjU1ZDIyZWNjMTMxYTliZTZlYmE1YzFlZDRiNCJ9fX0=", true),
    MISC_WARNING("Warning", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ5YjYzYzNiNzQ1ZjVkZjg1MTE3NTk3MmVlZmQ3N2VjYTUyNjlkNDg2N2M0ZWRhMTU5NGZmM2U2NjM0NjU4In19fQ==", true),
    MISC_PLAY_BUTTON_EMERALD("Emerald Play Button", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmE4ZjZiMTMxZWY4NDdkOTE2MGU1MTZhNmY0NGJmYTkzMjU1NGQ0MGMxOGE4MTc5NmQ3NjZhNTQ4N2I5ZjcxMCJ9fX0=", true),
    MISC_PLAY_BUTTON_SILVER("Silver Play Button", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmMwNzFlMTE5N2JjZWI0ODE5ZDU2ZGRjYmYwYTY4NDE1ODE3MDhlODAzOWZiYWZkZTAzMGNiMzY1NjdjNmVlOSJ9fX0=", true),
    MISC_PLAY_BUTTON_RUBY("Ruby Play Button", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2VjZDA0MWY2MjhjMDA1YTY5MGZjNmI4MjM3ZTczMTFiYjdjM2IzYWFjMTA1MzlmZWZlMzk2YTRjN2M3ODNlNyJ9fX0=", true),
    MISC_ENDER_PEARL_PURPLE("Purple Ender Pearl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzc3ZDRhMjA2ZDc3NTdmNDc5ZjMzMmVjMWEyYmJiZWU1N2NlZjk3NTY4ZGQ4OGRmODFmNDg2NGFlZTdkM2Q5OCJ9fX0=", true),
    MISC_ENDER_PEARL_GREEN("Green Ender Pearl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM4MjYxODFjZTkwMTJiNjY1ODY1ZjNhYzAwNjYzMDdiNGQwMmRhMjgxNTQwMTA0ZTA0NjFmZmVmYTc0NTlmZCJ9fX0=", true),
    MISC_ENDER_PEARL_BLUE("Blue Ender Pearl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzhiZThhYmQ2NmQwOWE1OGNlMTJkMzc3NTQ0ZDcyNmQyNWNhZDdlOTc5ZThjMjQ4MTg2NmJlOTRkM2IzMmYifX19", true),
    autobridge_wool("White Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjkyZGYyMTZlY2QyNzYyNGFjNzcxYmFjZmJmZTAwNmUxZWQ4NGE3OWU5MjcwYmUwZjg4ZTljODc5MWQxZWNlNCJ9fX0=", true),
    autobridge_wood("Wood", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjEzMTdkMTgyMGM3N2UyOWJlMzNhYThhN2MxNzcxY2U3YTk2MmU1ZTc3MGZjYjgyN2Y1MDNkNTRmOTU2NWRhIn19fQ==", true),
    autobridge_stone("Polished Andesite", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODI5ZTkzMmYxMmJhYmRkMmZlMGUzNTUzZGNmYWM0MTJiYTdmNDI0MmU4ZDBlMmY3MTk1ZGQ5NDVlNTExZGZjYSJ9fX0=", true),
    autobridge_endstone("End Stone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDI4MTNjZjhjNGZkMDEzYmFmNWJmNTVhOGM5MzEyMWM0ODJkYTFjZjZlMTA1NGMxODBmZjNlODE3MjdiNjVkZCJ9fX0=", true),
    autobridge_argil("Clay", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjc4MjY4MjllYWI1YWQ2MmYwYzExZDlmYWFmZGM5OTU0MzY0ODcxMTYwZGQ4MzllMWFiNWEzYjIxM2EzMyJ9fX0=", true)
    ;


    private String name;
    private String data;
    private boolean isCustom;

    Heads(String name, String player, boolean isCustom) {
        setName(name);
        setData(player);
        setCustom(isCustom);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean isCustom) {
        this.isCustom = isCustom;
    }

    public ItemStack getSkull() {
        if (isCustom()) {
            return getCustomSkull(data);
        } else {
            return getSkullOf(data);
        }
    }

    public static ItemStack getSkullOf(String owner) {
        return getSkullOf(owner, owner);
    }

    public static ItemStack getSkullOf(String owner, String name) {
        ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) 0, (byte) 3);
        SkullMeta isM = (SkullMeta) is.getItemMeta();
        isM.setOwner(owner);
        isM.setDisplayName("§r§e" + name);
        isM.setLore(Arrays.asList("§8Owner :", "§8 > §7§o" + owner));
        is.setItemMeta(isM);
        return is;
    }

    public static ItemStack getCustomSkull(String base64) {

        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (base64.isEmpty()) return head;

        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        propertyMap.put("textures", new Property("textures", base64));
        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        head.setItemMeta(meta);

        return head;
    }
}
