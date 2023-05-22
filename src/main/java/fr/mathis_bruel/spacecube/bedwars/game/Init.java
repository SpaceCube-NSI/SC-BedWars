package fr.mathis_bruel.spacecube.bedwars.game;

import com.sk89q.worldedit.WorldEditException;
import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.generator.Generator;
import fr.mathis_bruel.spacecube.bedwars.generator.RunnableGenerators;
import fr.mathis_bruel.spacecube.bedwars.manager.Hologram;
import fr.mathis_bruel.spacecube.bedwars.manager.TypeShop;
import fr.mathis_bruel.spacecube.bedwars.teams.GeneratorTeam;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Init extends BukkitRunnable {
    public Manager manager;
    public Arena arena = manager.getArena();

    @Override
    public void run() {
        RunnableGenerators runnableGenerators = new RunnableGenerators();
        runnableGenerators.arena = arena;
        runnableGenerators.runTaskTimer(Main.getInstance(), 0, 20);
        int x1 = arena.getPos1Map().getBlockX();
        int y1 = arena.getPos1Map().getBlockY();
        int z1 = arena.getPos1Map().getBlockZ();
        int x2 = arena.getPos2Map().getBlockX();
        int y2 = arena.getPos2Map().getBlockY();
        int z2 = arena.getPos2Map().getBlockZ();

        try {
            Utils.saveSchem(arena.getName(), x1, y1, z1, x2, y2, z2, arena.getWorld());
        } catch (WorldEditException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Team team : arena.getTeams()) {
            Location location = team.getPnjItems();
            Location loc2 = location.clone().add(0, 1.8, 0);
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, " ");
            //  execute command in console: /npc select <id> /npc sound -n
            npc.spawn(loc2);
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, "npc select " + npc.getId());
            Bukkit.dispatchCommand(console, "npc sound -n");


            //npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("items", Utils.getSkin("6ac084a53d4c445b80ead69aad7cbfcc")[0], Utils.getSkin("6ac084a53d4c445b80ead69aad7cbfcc")[1]);
            //npc.getOrAddTrait(SkinTrait.class).setSkinName("Pewki");
            //npc.data().setPersistent(NPC.PLAYER_SKIN_UUID_METADATA, "6ac084a53d4c445b80ead69aad7cbfcc");
            //npc.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_METADATA, Utils.getSkin("6ac084a53d4c445b80ead69aad7cbfcc")[0]);
            //npc.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_SIGN_METADATA, Utils.getSkin("6ac084a53d4c445b80ead69aad7cbfcc")[1]);
            //npc.data().setPersistent(NPC.PLAYER_SKIN_USE_LATEST, true);

            ArrayList<String> lines = new ArrayList<>();
            lines.add("§6§lSHOP ITEMS");
            lines.add("§7Click to open");
            Hologram hologram = new Hologram(loc2, lines);
            hologram.showHologram();

            Location location2 = team.getPnjUpgrades();
            NPC npc2 = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, " ");
            npc2.data().setPersistent(NPC.Metadata.SILENT, true);
            //npc2.getOrAddTrait(SkinTrait.class).setSkinPersistent("upgrade", Utils.getSkin("75e2a07a19064eadb28ecbe76fbb0a78")[0], Utils.getSkin("75e2a07a19064eadb28ecbe76fbb0a78")[1]);
            //npc2.getOrAddTrait(SkinTrait.class).setSkinName("Ant_playz11");
            //npc2.data().setPersistent(NPC.PLAYER_SKIN_UUID_METADATA, "75e2a07a19064eadb28ecbe76fbb0a78");
            //npc2.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_METADATA, Utils.getSkin("75e2a07a19064eadb28ecbe76fbb0a78")[0]);
            //npc2.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_SIGN_METADATA, Utils.getSkin("75e2a07a19064eadb28ecbe76fbb0a78")[1]);
            //npc2.data().setPersistent(NPC.PLAYER_SKIN_USE_LATEST, true);

            npc2.spawn(location2);
            Bukkit.dispatchCommand(console, "npc select " + npc2.getId());
            Bukkit.dispatchCommand(console, "npc sound -n");
            Location loc = location2.clone().add(0, 1.8, 0);
            ArrayList<String> lines2 = new ArrayList<>();
            lines2.add("§6§lSHOP UPGRADES");
            lines2.add("§7Click to open");
            Hologram hologram2 = new Hologram(loc, lines2);
            hologram2.showHologram();

            arena.addShop(npc.getUniqueId(), TypeShop.ITEMS);
            arena.addShop(npc2.getUniqueId(), TypeShop.UPGRADES);
            arena.addNpc(npc);
            arena.addNpc(npc2);

            team.getGenerators().forEach(generator -> {
                ArrayList<String> lines3 = new ArrayList<>();
                lines3.add(team.getColor() + "§l" + team.getName() + " Summoner");
                lines3.add("§f➀ Iron");
                Hologram hologram3 = new Hologram(generator.getLocation().clone().add(0, 2, 0), lines3);
                hologram3.showHologram();
                team.addGeneratorHologram(hologram3);
            });

            if (!team.isBedAlive()) {
                team.getBed().setType(Material.AIR);
            }

        }

        for (Generator generator : arena.getDiamondsGenerators()) {
            ArrayList<String> lines = new ArrayList<>();
            lines.add("§bDiamonds Summoner");
            lines.add("§7➤➤➤➤➤➤➤➤➤➤§l+");
            Hologram hologram = new Hologram(generator.getLocation().clone().add(0, 2, 0), lines);
            hologram.showHologram();
            generator.setHologram(hologram);
        }

        for (Generator generator : arena.getEmeraldsGenerators()) {
            ArrayList<String> lines = new ArrayList<>();
            lines.add("§aEmeralds Summoner");
            lines.add("§7➤➤➤➤➤➤➤➤➤➤§l+");
            Hologram hologram = new Hologram(generator.getLocation().clone().add(0, 2, 0), lines);
            hologram.showHologram();
            generator.setHologram(hologram);
        }

        // create npc for arena.getTheSpecialist and arena.getEnchanter
        //TruenoNPCSkin skin = TruenoNPCSkinBuilder.fromMineskin(Main.getInstance(), "f1894a3e64e64fb483ade05ddf00fdff");
        Location location = arena.getTheSpecialist();
        Location loc2 = location.clone().add(0, 1.8, 0);
        //TruenoNPC npc = TruenoNPCApi.createNPC(Main.getInstance(), location, skin);
        //NPCManager npc = new NPCManager(location, EntityType.PLAYER, "THE-SPECIALIST", "f1894a3e64e64fb483ade05ddf00fdff");
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.BLAZE, " ");
        npc.data().setPersistent(NPC.Metadata.SILENT, true);
        //npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("specialist", Utils.getSkin("f1894a3e64e64fb483ade05ddf00fdff")[0], Utils.getSkin("f1894a3e64e64fb483ade05ddf00fdff")[1]);
        //npc.getOrAddTrait(SkinTrait.class).setSkinName("XXHENRYXX123");

        //npc.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_METADATA, Utils.getSkin("f1894a3e64e64fb483ade05ddf00fdff")[0]);
        //npc.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_SIGN_METADATA, Utils.getSkin("f1894a3e64e64fb483ade05ddf00fdff")[1]);
        //npc.data().setPersistent(NPC.PLAYER_SKIN_USE_LATEST, true);
        npc.spawn(location);
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, "npc select " + npc.getId());
        Bukkit.dispatchCommand(console, "npc sound -n");
        ArrayList<String> lines = new ArrayList<>();
        lines.add("§6§lTHE SPECIALIST");
        lines.add("§7Click to open");
        Hologram hologram = new Hologram(loc2, lines);
        hologram.showHologram();
        arena.addShop(npc.getUniqueId(), TypeShop.THE_SPECIALIST);
        arena.addNpc(npc);

        //TruenoNPCSkin skin2 = TruenoNPCSkinBuilder.fromMineskin(Main.getInstance(), "473cae4d3c8e4d20857a01f6e52076b7");
        Location location2 = arena.getEnchanter();
        Location loc3 = location2.clone().add(0, 1.8, 0);
        //TruenoNPC npc2 = TruenoNPCApi.createNPC(Main.getInstance(), location2, skin2);
        NPC npc2 = CitizensAPI.getNPCRegistry().createNPC(EntityType.WITCH, " ");
        npc2.data().setPersistent(NPC.Metadata.SILENT, true);
        //npc2.getOrAddTrait(SkinTrait.class).setSkinPersistent("enchanter", Utils.getSkin("473cae4d3c8e4d20857a01f6e52076b7")[0], Utils.getSkin("473cae4d3c8e4d20857a01f6e52076b7")[1]);
        //npc2.getOrAddTrait(SkinTrait.class).setSkinName("Ant_playz11");

        //npc2.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_METADATA, Utils.getSkin("473cae4d3c8e4d20857a01f6e52076b7")[0]);
        //npc2.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_SIGN_METADATA, Utils.getSkin("473cae4d3c8e4d20857a01f6e52076b7")[1]);
        //npc2.data().setPersistent(NPC.PLAYER_SKIN_USE_LATEST, true);
        npc2.spawn(location2);
        Bukkit.dispatchCommand(console, "npc select " + npc2.getId());
        Bukkit.dispatchCommand(console, "npc sound -n");
        ArrayList<String> lines2 = new ArrayList<>();
        lines2.add("§6§lENCHANTER");
        lines2.add("§7Click to open");
        Hologram hologram2 = new Hologram(loc3, lines2);
        hologram2.showHologram();
        arena.addShop(npc2.getUniqueId(), TypeShop.ENCHANTER);
        arena.addNpc(npc2);

        // get all blocks in the arena and add them to the list
        int minX = Math.min(arena.getPos1Map().getBlockX(), arena.getPos2Map().getBlockX());
        int maxX = Math.max(arena.getPos1Map().getBlockX(), arena.getPos2Map().getBlockX());
        int minY = Math.min(arena.getPos1Map().getBlockY(), arena.getPos2Map().getBlockY());
        int maxY = Math.max(arena.getPos1Map().getBlockY(), arena.getPos2Map().getBlockY());
        int minZ = Math.min(arena.getPos1Map().getBlockZ(), arena.getPos2Map().getBlockZ());
        int maxZ = Math.max(arena.getPos1Map().getBlockZ(), arena.getPos2Map().getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = arena.getPos1Map().getWorld().getBlockAt(x, y, z);
                    if (!(block.getType() == Material.AIR || block.getType() == Material.DOUBLE_PLANT || block.getType() == Material.RED_ROSE || block.getType() == Material.YELLOW_FLOWER || block.getType() == Material.LONG_GRASS || block.getType() == Material.SAPLING || block.getType() == Material.DEAD_BUSH || block.getType() == Material.BROWN_MUSHROOM || block.getType() == Material.RED_MUSHROOM || block.getType() == Material.VINE || block.getType() == Material.WATER_LILY || block.getType() == Material.CROPS || block.getType() == Material.CARROT || block.getType() == Material.POTATO || block.getType() == Material.SUGAR_CANE_BLOCK || block.getType() == Material.CACTUS || block.getType() == Material.MELON_STEM || block.getType() == Material.PUMPKIN_STEM || block.getType() == Material.NETHER_WARTS || block.getType() == Material.COCOA || block.getType() == Material.SNOW || block.getType() == Material.LONG_GRASS)) {
                        this.manager.getBlocksNotBreakable().add(block);
                    }
                }
            }
        }

        // get all blocks around team spawn in function of arena.getProtectionRadius()
        for (Team team : arena.getTeams()) {
            Location location3 = team.getSpawn();
            System.out.println(team.toString());
            System.out.println(location3.toString());
            int minX2 = (int) Math.min(location3.getBlockX() - arena.getProtectionRadius(), location3.getBlockX() + arena.getProtectionRadius());
            int maxX2 = (int) Math.max(location3.getBlockX() - arena.getProtectionRadius(), location3.getBlockX() + arena.getProtectionRadius());
            int minY2 = (int) Math.min(location3.getBlockY() - arena.getProtectionRadius(), location3.getBlockY() + arena.getProtectionRadius());
            int maxY2 = (int) Math.max(location3.getBlockY() - arena.getProtectionRadius(), location3.getBlockY() + arena.getProtectionRadius());
            int minZ2 = (int) Math.min(location3.getBlockZ() - arena.getProtectionRadius(), location3.getBlockZ() + arena.getProtectionRadius());
            int maxZ2 = (int) Math.max(location3.getBlockZ() - arena.getProtectionRadius(), location3.getBlockZ() + arena.getProtectionRadius());

            for (int x = minX2; x <= maxX2; x++) {
                for (int y = minY2; y <= maxY2; y++) {
                    for (int z = minZ2; z <= maxZ2; z++) {
                        Block block = location3.getWorld().getBlockAt(x, y, z);
                        if (!(block.getType() == Material.AIR || block.getType() == Material.DOUBLE_PLANT || block.getType() == Material.RED_ROSE || block.getType() == Material.YELLOW_FLOWER || block.getType() == Material.LONG_GRASS || block.getType() == Material.SAPLING || block.getType() == Material.DEAD_BUSH || block.getType() == Material.BROWN_MUSHROOM || block.getType() == Material.RED_MUSHROOM || block.getType() == Material.VINE || block.getType() == Material.WATER_LILY || block.getType() == Material.CROPS || block.getType() == Material.CARROT || block.getType() == Material.POTATO || block.getType() == Material.SUGAR_CANE_BLOCK || block.getType() == Material.CACTUS || block.getType() == Material.MELON_STEM || block.getType() == Material.PUMPKIN_STEM || block.getType() == Material.NETHER_WARTS || block.getType() == Material.COCOA || block.getType() == Material.SNOW || block.getType() == Material.LONG_GRASS)) {
                            this.manager.getBlocksNotBreakable().add(block);
                        }
                    }
                }
            }
            // around generator team
            for (GeneratorTeam generator : team.getGenerators()) {
                Location location4 = generator.getLocation();
                int minX3 = Math.min(location4.getBlockX() - 2, location4.getBlockX() + 2);
                int maxX3 = Math.max(location4.getBlockX() - 2, location4.getBlockX() + 2);
                int minY3 = Math.min(location4.getBlockY() - 2, location4.getBlockY() + 2);
                int maxY3 = Math.max(location4.getBlockY() - 2, location4.getBlockY() + 2);
                int minZ3 = Math.min(location4.getBlockZ() - 2, location4.getBlockZ() + 2);
                int maxZ3 = Math.max(location4.getBlockZ() - 2, location4.getBlockZ() + 2);

                for (int x = minX3; x <= maxX3; x++) {
                    for (int y = minY3; y <= maxY3; y++) {
                        for (int z = minZ3; z <= maxZ3; z++) {
                            Block block = location4.getWorld().getBlockAt(x, y, z);
                            if (!(block.getType() == Material.AIR || block.getType() == Material.DOUBLE_PLANT || block.getType() == Material.RED_ROSE || block.getType() == Material.YELLOW_FLOWER || block.getType() == Material.LONG_GRASS || block.getType() == Material.SAPLING || block.getType() == Material.DEAD_BUSH || block.getType() == Material.BROWN_MUSHROOM || block.getType() == Material.RED_MUSHROOM || block.getType() == Material.VINE || block.getType() == Material.WATER_LILY || block.getType() == Material.CROPS || block.getType() == Material.CARROT || block.getType() == Material.POTATO || block.getType() == Material.SUGAR_CANE_BLOCK || block.getType() == Material.CACTUS || block.getType() == Material.MELON_STEM || block.getType() == Material.PUMPKIN_STEM || block.getType() == Material.NETHER_WARTS || block.getType() == Material.COCOA || block.getType() == Material.SNOW || block.getType() == Material.LONG_GRASS)) {
                                this.manager.getBlocksNotBreakable().add(block);
                            }
                        }
                    }
                }
            }

        }
        for (Generator generator : arena.getDiamondsGenerators()) {
            Location location3 = generator.getLocation();
            int minX2 = Math.min(location3.getBlockX() - 2, location3.getBlockX() + 2);
            int maxX2 = Math.max(location3.getBlockX() - 2, location3.getBlockX() + 2);
            int minY2 = Math.min(location3.getBlockY() - 2, location3.getBlockY() + 2);
            int maxY2 = Math.max(location3.getBlockY() - 2, location3.getBlockY() + 2);
            int minZ2 = Math.min(location3.getBlockZ() - 2, location3.getBlockZ() + 2);
            int maxZ2 = Math.max(location3.getBlockZ() - 2, location3.getBlockZ() + 2);

            for (int x = minX2; x <= maxX2; x++) {
                for (int y = minY2; y <= maxY2; y++) {
                    for (int z = minZ2; z <= maxZ2; z++) {
                        Block block = location3.getWorld().getBlockAt(x, y, z);
                        if (!(block.getType() == Material.AIR || block.getType() == Material.DOUBLE_PLANT || block.getType() == Material.RED_ROSE || block.getType() == Material.YELLOW_FLOWER || block.getType() == Material.LONG_GRASS || block.getType() == Material.SAPLING || block.getType() == Material.DEAD_BUSH || block.getType() == Material.BROWN_MUSHROOM || block.getType() == Material.RED_MUSHROOM || block.getType() == Material.VINE || block.getType() == Material.WATER_LILY || block.getType() == Material.CROPS || block.getType() == Material.CARROT || block.getType() == Material.POTATO || block.getType() == Material.SUGAR_CANE_BLOCK || block.getType() == Material.CACTUS || block.getType() == Material.MELON_STEM || block.getType() == Material.PUMPKIN_STEM || block.getType() == Material.NETHER_WARTS || block.getType() == Material.COCOA || block.getType() == Material.SNOW || block.getType() == Material.LONG_GRASS)) {
                            this.manager.getBlocksNotBreakable().add(block);
                        }
                    }
                }
            }
        }

        for (Generator generator : arena.getEmeraldsGenerators()) {
            Location location3 = generator.getLocation();
            int minX2 = Math.min(location3.getBlockX() - 2, location3.getBlockX() + 2);
            int maxX2 = Math.max(location3.getBlockX() - 2, location3.getBlockX() + 2);
            int minY2 = Math.min(location3.getBlockY() - 2, location3.getBlockY() + 2);
            int maxY2 = Math.max(location3.getBlockY() - 2, location3.getBlockY() + 2);
            int minZ2 = Math.min(location3.getBlockZ() - 2, location3.getBlockZ() + 2);
            int maxZ2 = Math.max(location3.getBlockZ() - 2, location3.getBlockZ() + 2);

            for (int x = minX2; x <= maxX2; x++) {
                for (int y = minY2; y <= maxY2; y++) {
                    for (int z = minZ2; z <= maxZ2; z++) {
                        Block block = location3.getWorld().getBlockAt(x, y, z);
                        if (!(block.getType() == Material.AIR || block.getType() == Material.DOUBLE_PLANT || block.getType() == Material.RED_ROSE || block.getType() == Material.YELLOW_FLOWER || block.getType() == Material.LONG_GRASS || block.getType() == Material.SAPLING || block.getType() == Material.DEAD_BUSH || block.getType() == Material.BROWN_MUSHROOM || block.getType() == Material.RED_MUSHROOM || block.getType() == Material.VINE || block.getType() == Material.WATER_LILY || block.getType() == Material.CROPS || block.getType() == Material.CARROT || block.getType() == Material.POTATO || block.getType() == Material.SUGAR_CANE_BLOCK || block.getType() == Material.CACTUS || block.getType() == Material.MELON_STEM || block.getType() == Material.PUMPKIN_STEM || block.getType() == Material.NETHER_WARTS || block.getType() == Material.COCOA || block.getType() == Material.SNOW || block.getType() == Material.LONG_GRASS)) {
                            this.manager.getBlocksNotBreakable().add(block);
                        }
                    }
                }
            }
        }

        // add 2 block arounds all generators in locationsNotPlaceable
        for (Generator generator : arena.getDiamondsGenerators()) {
            Location location3 = generator.getLocation();
            int minX2 = Math.min(location3.getBlockX() - 2, location3.getBlockX() + 2);
            int maxX2 = Math.max(location3.getBlockX() - 2, location3.getBlockX() + 2);
            int minY2 = Math.min(location3.getBlockY() - 2, location3.getBlockY() + 2);
            int maxY2 = Math.max(location3.getBlockY() - 2, location3.getBlockY() + 2);
            int minZ2 = Math.min(location3.getBlockZ() - 2, location3.getBlockZ() + 2);
            int maxZ2 = Math.max(location3.getBlockZ() - 2, location3.getBlockZ() + 2);

            for (int x = minX2; x <= maxX2; x++) {
                for (int y = minY2; y <= maxY2; y++) {
                    for (int z = minZ2; z <= maxZ2; z++) {
                        Block block = location3.getWorld().getBlockAt(x, y, z);
                        this.manager.getLocationsNotPlaceable().add(block.getLocation());
                    }
                }
            }
        }
        for (Generator generator : arena.getEmeraldsGenerators()) {
            Location location3 = generator.getLocation();
            int minX2 = Math.min(location3.getBlockX() - 2, location3.getBlockX() + 2);
            int maxX2 = Math.max(location3.getBlockX() - 2, location3.getBlockX() + 2);
            int minY2 = Math.min(location3.getBlockY() - 2, location3.getBlockY() + 2);
            int maxY2 = Math.max(location3.getBlockY() - 2, location3.getBlockY() + 2);
            int minZ2 = Math.min(location3.getBlockZ() - 2, location3.getBlockZ() + 2);
            int maxZ2 = Math.max(location3.getBlockZ() - 2, location3.getBlockZ() + 2);

            for (int x = minX2; x <= maxX2; x++) {
                for (int y = minY2; y <= maxY2; y++) {
                    for (int z = minZ2; z <= maxZ2; z++) {
                        Block block = location3.getWorld().getBlockAt(x, y, z);
                        this.manager.getLocationsNotPlaceable().add(block.getLocation());
                    }
                }
            }
        }
        for (Team team : arena.getTeams()) {
            for (GeneratorTeam generator : team.getGenerators()) {
                Location location3 = generator.getLocation();
                int minX2 = Math.min(location3.getBlockX() - 2, location3.getBlockX() + 2);
                int maxX2 = Math.max(location3.getBlockX() - 2, location3.getBlockX() + 2);
                int minY2 = Math.min(location3.getBlockY() - 2, location3.getBlockY() + 2);
                int maxY2 = Math.max(location3.getBlockY() - 2, location3.getBlockY() + 2);
                int minZ2 = Math.min(location3.getBlockZ() - 2, location3.getBlockZ() + 2);
                int maxZ2 = Math.max(location3.getBlockZ() - 2, location3.getBlockZ() + 2);

                for (int x = minX2; x <= maxX2; x++) {
                    for (int y = minY2; y <= maxY2; y++) {
                        for (int z = minZ2; z <= maxZ2; z++) {
                            Block block = location3.getWorld().getBlockAt(x, y, z);
                            this.manager.getLocationsNotPlaceable().add(block.getLocation());
                        }
                    }
                }
            }
        }
        for (Team team : arena.getTeams()) {
            Location location3 = team.getSpawn();
            int minX2 = Math.min(location3.getBlockX() - 2, location3.getBlockX() + 2);
            int maxX2 = Math.max(location3.getBlockX() - 2, location3.getBlockX() + 2);
            int minY2 = Math.min(location3.getBlockY() - 2, location3.getBlockY() + 2);
            int maxY2 = Math.max(location3.getBlockY() - 2, location3.getBlockY() + 2);
            int minZ2 = Math.min(location3.getBlockZ() - 2, location3.getBlockZ() + 2);
            int maxZ2 = Math.max(location3.getBlockZ() - 2, location3.getBlockZ() + 2);

            for (int x = minX2; x <= maxX2; x++) {
                for (int y = minY2; y <= maxY2; y++) {
                    for (int z = minZ2; z <= maxZ2; z++) {
                        Block block = location3.getWorld().getBlockAt(x, y, z);
                        this.manager.getLocationsNotPlaceable().add(block.getLocation());
                    }
                }
            }
        }
        // set in InBase the location of 25 block around spawn of each team
        for (Team team : arena.getTeams()) {
            Location location3 = team.getSpawn();
            int minX2 = Math.min(location3.getBlockX() - 25, location3.getBlockX() + 25);
            int maxX2 = Math.max(location3.getBlockX() - 25, location3.getBlockX() + 25);
            int minY2 = Math.min(location3.getBlockY() - 25, location3.getBlockY() + 25);
            int maxY2 = Math.max(location3.getBlockY() - 25, location3.getBlockY() + 25);
            int minZ2 = Math.min(location3.getBlockZ() - 25, location3.getBlockZ() + 25);
            int maxZ2 = Math.max(location3.getBlockZ() - 25, location3.getBlockZ() + 25);

            for (int x = minX2; x <= maxX2; x++) {
                for (int y = minY2; y <= maxY2; y++) {
                    for (int z = minZ2; z <= maxZ2; z++) {
                        Block block = location3.getWorld().getBlockAt(x, y, z);
                        this.manager.getInBase().add(block.getLocation());
                    }
                }
            }
        }
        this.manager.getPlayers().forEach(player -> {
            this.manager.setPlayerKills(player, 0);
            this.manager.setPlayerDeaths(player, 0);
            this.manager.setPlayerBeds(player, 0);
            for (PotionEffect effect : player.getActivePotionEffects())
                player.removePotionEffect(effect.getType());
        });
    }
}
