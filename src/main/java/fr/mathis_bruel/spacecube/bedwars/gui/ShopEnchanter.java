package fr.mathis_bruel.spacecube.bedwars.gui;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.utils.CustomNBT;
import fr.mathis_bruel.spacecube.bedwars.utils.Heads;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class ShopEnchanter {

    public static Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, 9 * 5, "§6Enchanter");
        ItemStack glass = new ItemStack(160, 1, (short) 7);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        glass = new CustomNBT(glass).setBoolean("clickable", true).build();
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cClose");
        close.setItemMeta(closeMeta);
        close = new CustomNBT(close).setBoolean("clickable", true).build();
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§5Back");
        back.setItemMeta(backMeta);
        back = new CustomNBT(back).setBoolean("clickable", true).build();
        ItemStack next = Heads.next_green.getHead();
        ItemMeta nextMeta = next.getItemMeta();
        nextMeta.setDisplayName("§aEnchant");
        nextMeta.setLore(Arrays.asList("§7Enchant your item", "§5Please set the item in the first slot", "§5and select the enchantment"));
        next.setItemMeta(nextMeta);
        next = new CustomNBT(next).setBoolean("clickable", true).build();
        // create enchanted book: Fire Aspect 1, KB 1, Sharpness 1, flame, power 1, efficiency 1
        ItemStack fireAspect = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta fireAspectMeta = fireAspect.getItemMeta();
        fireAspectMeta.setDisplayName("§6Fire Aspect 1");
        fireAspectMeta.setLore(Arrays.asList("§7Price: §a15 emeralds", "§7compatibility: §aSword"));
        fireAspect.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
        fireAspect.setItemMeta(fireAspectMeta);
        fireAspect = new CustomNBT(fireAspect).setBoolean("clickable", true).build();
        ItemStack knockBack = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta knockBackMeta = knockBack.getItemMeta();
        knockBackMeta.setDisplayName("§6Knockback 1");
        knockBackMeta.setLore(Arrays.asList("§7Price: §a10 emeralds", "§7compatibility: §aSword"));
        knockBack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
        knockBack.setItemMeta(knockBackMeta);
        knockBack = new CustomNBT(knockBack).setBoolean("clickable", true).build();
        ItemStack sharpness = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta sharpnessMeta = sharpness.getItemMeta();
        sharpnessMeta.setDisplayName("§6Sharpness 1");
        sharpnessMeta.setLore(Arrays.asList("§7Price: §a5 emeralds", "§7compatibility: §aSword"));
        sharpness.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        sharpness.setItemMeta(sharpnessMeta);
        sharpness = new CustomNBT(sharpness).setBoolean("clickable", true).build();
        ItemStack flame = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta flameMeta = flame.getItemMeta();
        flameMeta.setDisplayName("§6Flame");
        flameMeta.setLore(Arrays.asList("§7Price: §a10 emeralds", "§7compatibility: §aBow"));
        flame.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
        flame.setItemMeta(flameMeta);
        flame = new CustomNBT(flame).setBoolean("clickable", true).build();
        ItemStack power = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta powerMeta = power.getItemMeta();
        powerMeta.setDisplayName("§6Power 1");
        powerMeta.setLore(Arrays.asList("§7Price: §a5 emeralds", "§7compatibility: §aBow"));
        power.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
        power.setItemMeta(powerMeta);
        power = new CustomNBT(power).setBoolean("clickable", true).build();
        ItemStack efficiency = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta efficiencyMeta = efficiency.getItemMeta();
        efficiencyMeta.setDisplayName("§6Efficiency 1");
        efficiencyMeta.setLore(Arrays.asList("§7Price: §a1 emerald", "§7compatibility: §aPickaxe, Axe"));
        efficiency.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        efficiency.setItemMeta(efficiencyMeta);
        efficiency = new CustomNBT(efficiency).setBoolean("clickable", true).build();
        ItemStack none = new ItemStack(Material.BARRIER);
        ItemMeta noneMeta = none.getItemMeta();
        noneMeta.setDisplayName("§cNone");
        none.setItemMeta(noneMeta);
        none = new CustomNBT(none).setBoolean("clickable", true).build();


        for (int i = 0; i < 9; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 9; i < 45; i += 9) {
            inv.setItem(i, glass);
            inv.setItem(i + 8, glass);
        }
        for (int i = 36; i < 45; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 18; i < 27; i++) {
            inv.setItem(i, glass);
        }
        inv.setItem(44, back);
        inv.setItem(36, close);
        inv.setItem(11, glass);
        inv.setItem(12, next);
        inv.setItem(13, next);
        inv.setItem(14, next);
        inv.setItem(15, glass);
        inv.setItem(28, fireAspect);
        inv.setItem(29, knockBack);
        inv.setItem(30, sharpness);
        inv.setItem(31, flame);
        inv.setItem(32, power);
        inv.setItem(33, efficiency);
        inv.setItem(34, none);

        return inv;
    }


    public static void execute(InventoryClickEvent event) {
        // TODO: Item despawn in slot 16
        // TODO: Not possible to enchant item with more than 1 enchantment
        //if (!Manager.isCurrentlyInGame((Player) event.getWhoClicked())) return;
        //Team team = Manager.getManager((Player) event.getWhoClicked()).getTeam((Player) event.getWhoClicked());
        if (event.isShiftClick()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (event.getInventory().getItem(10) != null) {
                        if (event.getInventory().getItem(10).getType() != Material.AIR) {
                            ItemStack head = Heads.next_orange.getHead();
                            ItemMeta meta = head.getItemMeta();
                            meta.setDisplayName("§aEnchant");
                            meta.setLore(Arrays.asList("§5Select an enchantment to enchant your item with", "§5and click here to enchant it !"));
                            head.setItemMeta(meta);
                            head = new CustomNBT(head).setBoolean("clickable", true).build();
                            event.getInventory().setItem(12, head);
                            event.getInventory().setItem(13, head);
                            event.getInventory().setItem(14, head);
                        } else {
                            if (event.getSlot() == 10 || event.getSlot() == 16)
                                event.getInventory().setContents(getInventory().getContents());
                        }
                    } else {
                        if (event.getSlot() == 10 || event.getSlot() == 16)
                            event.getInventory().setContents(getInventory().getContents());
                    }
                }
            }.runTaskLater(Main.getInstance(), 1);
        } else {
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (event.getSlot() == 10 || event.getSlot() == 16) {
                        if (event.getInventory().getItem(10) != null) {
                            if (event.getInventory().getItem(10).getType() != Material.AIR) {
                                ItemStack head = Heads.next_orange.getHead();
                                ItemMeta meta = head.getItemMeta();
                                meta.setDisplayName("§aEnchant");
                                meta.setLore(Arrays.asList("§5Select an enchantment to enchant your item with", "§5and click here to enchant it !"));
                                head.setItemMeta(meta);
                                head = new CustomNBT(head).setBoolean("clickable", true).build();
                                event.getInventory().setItem(12, head);
                                event.getInventory().setItem(13, head);
                                event.getInventory().setItem(14, head);
                            } else {
                                if (event.getSlot() == 10 || event.getSlot() == 16)
                                    event.getInventory().setContents(getInventory().getContents());
                            }
                        } else {
                            if (event.getSlot() == 10 || event.getSlot() == 16)
                                event.getInventory().setContents(getInventory().getContents());
                        }
                    }

                }
            }.runTaskLater(Main.getInstance(), 1);
        }
        if (event.getSlot() == 10) {
            if (event.getInventory().getItem(16) != null && event.getInventory().getItem(16).getType() != Material.AIR) {
                event.getWhoClicked().sendMessage("§cRecover your item first!");
                event.setCancelled(true);
                return;
            }
            /*if (event.getInventory().getItem(37).getType() == Material.STAINED_GLASS_PANE && event.getInventory().getItem(38).getType() == Material.STAINED_GLASS_PANE && event.getInventory().getItem(39).getType() == Material.STAINED_GLASS_PANE && event.getInventory().getItem(40).getType() == Material.STAINED_GLASS_PANE && event.getInventory().getItem(41).getType() == Material.STAINED_GLASS_PANE && event.getInventory().getItem(42).getType() == Material.STAINED_GLASS_PANE && event.getInventory().getItem(43).getType() == Material.STAINED_GLASS_PANE) {
                ItemStack head = Heads.next_orange.getHead();
                ItemMeta meta = head.getItemMeta();
                meta.setDisplayName("§aEnchant");
                meta.setLore(Arrays.asList("§5Select an enchantment to enchant your item with", "§5and click here to enchant it !"));
                head.setItemMeta(meta);
                head = new CustomNBT(head).setBoolean("clickable", true).build();
                event.getInventory().setItem(12, head);
                event.getInventory().setItem(13, head);
                event.getInventory().setItem(14, head);
            }*/
        }
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getType() == Material.AIR) return;
        /*if (ShopEnchanter.getInventory().contains(event.getCurrentItem()) || event.getSlot() == 10 || event.getSlot() == 16 || 12 < event.getSlot() && event.getSlot() < 14) {
            event.setCancelled(true);
        }*/
        CustomNBT nbt = new CustomNBT(event.getCurrentItem());
        if (nbt.getBoolean("clickable")) {
            System.out.println(1);
            event.setCancelled(true);
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cClose")) {
            event.getWhoClicked().closeInventory();
            return;
        }


        ItemStack current = event.getCurrentItem();

        if (current.getType() == Material.ENCHANTED_BOOK) {
            ItemStack item = event.getInventory().getItem(10);
            if (item == null || item.getType() == Material.AIR) {
                event.getWhoClicked().sendMessage("§cYou need to put an item in the slot!");
                return;
            }
            for (int i = 0; i < 7; i++) {
                ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
                ItemMeta glassMeta = glass.getItemMeta();
                glassMeta.setDisplayName(" ");
                glass.setItemMeta(glassMeta);
                glass = new CustomNBT(glass).setBoolean("clickable", true).build();
                event.getInventory().setItem(37 + i, glass);
            }
            if (item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.IRON_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.GOLD_SWORD || item.getType() == Material.WOOD_SWORD) {
                if (current.getItemMeta().getLore().get(1).equals("§7compatibility: §aSword")) {
                    /*int price = Integer.parseInt(current.getItemMeta().getLore().get(0).split(" ")[1]);
                    if (event.getWhoClicked().getInventory().contains(Material.EMERALD, price)) {
                        event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, price));
                        item.addUnsafeEnchantment(current.getEnchantments().keySet().iterator().next(), current.getEnchantments().values().iterator().next());
                        event.getWhoClicked().sendMessage("§aYou have successfully enchanted your item!");*/
                    ItemStack head2 = Heads.up_green.getHead();
                    ItemMeta meta2 = head2.getItemMeta();
                    meta2.setDisplayName("§aEnchant compatible");
                    meta2.setLore(Arrays.asList("§5You can enchant your item with this enchantment !", "§5Click in the 3 arrows to enchant it !"));
                    head2.setItemMeta(meta2);
                    head2 = new CustomNBT(head2).setBoolean("clickable", true).build();
                    event.getInventory().setItem(event.getSlot() + 9, head2);
                    ItemStack head = Heads.next_green.getHead();
                    ItemMeta meta = head.getItemMeta();
                    meta.setDisplayName("§aEnchant compatible");
                    meta.setLore(Arrays.asList("§7Enchant: " + current.getItemMeta().getDisplayName(), "§7Price: §a" + current.getItemMeta().getLore().get(0).split(" ")[1], "§5You can enchant your item with this enchantment !", "§5Just click here to enchant it !"));
                    head.setItemMeta(meta);
                    head = new CustomNBT(head).setBoolean("clickable", true).build();
                    event.getInventory().setItem(12, head);
                    event.getInventory().setItem(13, head);
                    event.getInventory().setItem(14, head);
                } else {
                    event.getWhoClicked().sendMessage("§cYou can't enchant this item with this enchantment!");
                    ItemStack head2 = Heads.up_red.getHead();
                    ItemMeta meta2 = head2.getItemMeta();
                    meta2.setDisplayName("§cEnchant not compatible");
                    meta2.setLore(Arrays.asList("§cYou can't enchant your item with this enchantment !", "§cThis enchantment is not compatible with your item !"));
                    head2.setItemMeta(meta2);
                    head2 = new CustomNBT(head2).setBoolean("clickable", true).build();
                    event.getInventory().setItem(event.getSlot() + 9, head2);
                    ItemStack head = Heads.next_red.getHead();
                    ItemMeta meta = head.getItemMeta();
                    meta.setDisplayName("§cEnchant not compatible");
                    meta.setLore(Arrays.asList("§cYou can't enchant your item with this enchantment !", "§cThis enchantment is not compatible with your item !"));
                    head.setItemMeta(meta);
                    head = new CustomNBT(head).setBoolean("clickable", true).build();
                    event.getInventory().setItem(12, head);
                    event.getInventory().setItem(13, head);
                    event.getInventory().setItem(14, head);
                }
            }
            if (item.getType() == Material.DIAMOND_PICKAXE || item.getType() == Material.IRON_PICKAXE || item.getType() == Material.STONE_PICKAXE || item.getType() == Material.GOLD_PICKAXE || item.getType() == Material.WOOD_PICKAXE || item.getType() == Material.DIAMOND_AXE || item.getType() == Material.IRON_AXE || item.getType() == Material.STONE_AXE || item.getType() == Material.GOLD_AXE || item.getType() == Material.WOOD_AXE) {
                if (current.getItemMeta().getLore().get(1).equals("§7compatibility: §aPickaxe, Axe")) {
                    ItemStack head2 = Heads.up_green.getHead();
                    ItemMeta meta2 = head2.getItemMeta();
                    meta2.setDisplayName("§aEnchant compatible");
                    meta2.setLore(Arrays.asList("§5You can enchant your item with this enchantment !", "§5Click in the 3 arrows to enchant it !"));
                    head2.setItemMeta(meta2);
                    head2 = new CustomNBT(head2).setBoolean("clickable", true).build();
                    event.getInventory().setItem(event.getSlot() + 9, head2);
                    ItemStack head = Heads.next_green.getHead();
                    ItemMeta meta = head.getItemMeta();
                    meta.setDisplayName("§aEnchant compatible");
                    meta.setLore(Arrays.asList("§7Enchant: " + current.getItemMeta().getDisplayName(), "§7Price: §a" + current.getItemMeta().getLore().get(0).split(" ")[1], "§5You can enchant your item with this enchantment !", "§5Just click here to enchant it !"));
                    head.setItemMeta(meta);
                    head = new CustomNBT(head).setBoolean("clickable", true).build();
                    event.getInventory().setItem(12, head);
                    event.getInventory().setItem(13, head);
                    event.getInventory().setItem(14, head);
                } else {
                    event.getWhoClicked().sendMessage("§cYou can't enchant this item with this enchantment!");
                    ItemStack head2 = Heads.up_red.getHead();
                    ItemMeta meta2 = head2.getItemMeta();
                    meta2.setDisplayName("§cEnchant not compatible");
                    meta2.setLore(Arrays.asList("§cYou can't enchant your item with this enchantment !", "§cThis enchantment is not compatible with your item !"));
                    head2.setItemMeta(meta2);
                    head2 = new CustomNBT(head2).setBoolean("clickable", true).build();
                    event.getInventory().setItem(event.getSlot() + 9, head2);
                    ItemStack head = Heads.next_red.getHead();
                    ItemMeta meta = head.getItemMeta();
                    meta.setDisplayName("§cEnchant not compatible");
                    meta.setLore(Arrays.asList("§cYou can't enchant your item with this enchantment !", "§cThis enchantment is not compatible with your item !"));
                    head.setItemMeta(meta);
                    head = new CustomNBT(head).setBoolean("clickable", true).build();
                    event.getInventory().setItem(12, head);
                    event.getInventory().setItem(13, head);
                    event.getInventory().setItem(14, head);
                }
            }
            // bow
            if (item.getType() == Material.BOW) {
                if (current.getItemMeta().getLore().get(1).equals("§7compatibility: §aBow")) {
                    ItemStack head2 = Heads.up_green.getHead();
                    ItemMeta meta2 = head2.getItemMeta();
                    meta2.setDisplayName("§aEnchant compatible");
                    meta2.setLore(Arrays.asList("§5You can enchant your item with this enchantment !", "§5Click in the 3 arrows to enchant it !"));
                    head2.setItemMeta(meta2);
                    head2 = new CustomNBT(head2).setBoolean("clickable", true).build();
                    event.getInventory().setItem(event.getSlot() + 9, head2);
                    ItemStack head = Heads.next_green.getHead();
                    ItemMeta meta = head.getItemMeta();
                    meta.setDisplayName("§aEnchant compatible");
                    meta.setLore(Arrays.asList("§7Enchant: " + current.getItemMeta().getDisplayName(), "§7Price: §a" + current.getItemMeta().getLore().get(0).split(" ")[1], "§5You can enchant your item with this enchantment !", "§5Just click here to enchant it !"));
                    head.setItemMeta(meta);
                    head = new CustomNBT(head).setBoolean("clickable", true).build();
                    event.getInventory().setItem(12, head);
                    event.getInventory().setItem(13, head);
                    event.getInventory().setItem(14, head);
                } else {
                    event.getWhoClicked().sendMessage("§cYou can't enchant this item with this enchantment!");
                    ItemStack head2 = Heads.up_red.getHead();
                    ItemMeta meta2 = head2.getItemMeta();
                    meta2.setDisplayName("§cEnchant not compatible");
                    meta2.setLore(Arrays.asList("§cYou can't enchant your item with this enchantment !", "§cThis enchantment is not compatible with your item !"));
                    head2.setItemMeta(meta2);
                    head2 = new CustomNBT(head2).setBoolean("clickable", true).build();
                    event.getInventory().setItem(event.getSlot() + 9, head2);
                    ItemStack head = Heads.next_red.getHead();
                    ItemMeta meta = head.getItemMeta();
                    meta.setDisplayName("§cEnchant not compatible");
                    meta.setLore(Arrays.asList("§cYou can't enchant your item with this enchantment !", "§cThis enchantment is not compatible with your item !"));
                    head.setItemMeta(meta);
                    head = new CustomNBT(head).setBoolean("clickable", true).build();
                    event.getInventory().setItem(12, head);
                    event.getInventory().setItem(13, head);
                    event.getInventory().setItem(14, head);
                }
            }
        }

        if (current.getType() == Material.SKULL_ITEM) {
            ItemStack item = event.getInventory().getItem(10);
            if (current.getItemMeta().getDisplayName().equals("§aEnchant compatible")) {
                if (current.getItemMeta().getLore().get(1).equals("§5You can enchant your item with this enchantment !")) {
                    if (current.getItemMeta().getLore().get(2).equals("§5Click in the 3 arrows to enchant it !")) {
                        event.getWhoClicked().sendMessage("§aYou can enchant your item with this enchantment !");
                        event.getWhoClicked().sendMessage("§aJust click in the 3 arrows to enchant it !");
                        return;
                    }
                } else {
                    int price = Integer.parseInt(current.getItemMeta().getLore().get(1).split(" ")[1].replace("§a", ""));
                    if (event.getWhoClicked().getInventory().contains(Material.EMERALD, price)) {
                        event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, price));
                        // §6Fire aspect, §6Knockback, §6Sharpness, §6Flame, §6power, §6Efficiency
                        Enchantment enchantment;
                        System.out.println(current.getItemMeta().getLore().get(0));
                        switch (current.getItemMeta().getLore().get(0)) {
                            case "§7Enchant: §6Fire Aspect 1":
                                enchantment = Enchantment.FIRE_ASPECT;
                                break;
                            case "§7Enchant: §6Knockback 1":
                                enchantment = Enchantment.KNOCKBACK;
                                break;
                            case "§7Enchant: §6Sharpness 1":
                                enchantment = Enchantment.DAMAGE_ALL;
                                break;
                            case "§7Enchant: §6Flame":
                                enchantment = Enchantment.ARROW_FIRE;
                                break;
                            case "§7Enchant: §6power 1":
                                enchantment = Enchantment.ARROW_DAMAGE;
                                break;
                            case "§7Enchant: §6Efficiency 1":
                                enchantment = Enchantment.DIG_SPEED;
                                break;
                            default: {
                                event.getWhoClicked().sendMessage("§cAn error has occurred while enchanting your item !");
                                return;
                            }
                        }
                        item.addUnsafeEnchantment(enchantment, 1);
                        item = item.clone();
                        event.getInventory().setContents(ShopEnchanter.getInventory().getContents());
                        event.getInventory().setItem(16, item);
                        event.getWhoClicked().sendMessage("§aYou have successfully enchanted your item!");
                    } else {
                        event.getWhoClicked().sendMessage("§cYou don't have enough emeralds to enchant your item!");
                    }

                }
            }
            if (current.getItemMeta().getDisplayName().equals("§cEnchant not compatible")) {
                if (current.getItemMeta().getLore().get(1).equals("§cYou can't enchant your item with this enchantment !")) {
                    if (current.getItemMeta().getLore().get(2).equals("§cThis enchantment is not compatible with your item !")) {
                        event.getWhoClicked().sendMessage("§cYou can't enchant your item with this enchantment !");
                        event.getWhoClicked().sendMessage("§cThis enchantment is not compatible with your item !");
                        return;
                    }
                }
            }

        }

    }
}
