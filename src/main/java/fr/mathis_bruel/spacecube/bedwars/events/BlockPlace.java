package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Arena;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.game.RunnableIronGolem;
import fr.mathis_bruel.spacecube.bedwars.game.State;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftIronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.ArrayList;
import java.util.List;

public class BlockPlace implements org.bukkit.event.Listener {

    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent event) {
        if(!Manager.isCurrentlyInGame(event.getPlayer()) && !event.getPlayer().hasPermission("bw.admin")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§cYou can't place blocks in this world!");
        }
        if(Main.isGodMode(event.getPlayer()))
            Main.removeGodMode(event.getPlayer());
        if(Manager.isCurrentlyInGame(event.getPlayer())) {
            Manager manager = Manager.getManager(event.getPlayer());
            if(manager == null)
                return;
            Arena arena = manager.getArena();
            if(arena == null)
                return;
            if(manager.getManagerState().getCurrentState() != State.RUNNING) event.setCancelled(true);
            Team team = manager.getTeam(event.getPlayer());
            if(team == null)
                return;
            if(manager.isLocationNotPlaceable(event.getBlock().getLocation())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cYou can't place blocks here!");
                return;
            }
            // if is left the region of arena (manager.getArena().getPos1Map() and manager.getArena().getPos2Map())
            if(!isBlockInRegion(event.getBlock().getLocation(), manager.getArena().getPos1Map(), manager.getArena().getPos2Map())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cYou can't place blocks here!");
                return;
            }

            if(event.getBlock().getType() == Material.TNT){
                event.getBlock().setType(Material.AIR);
                event.getBlock().getWorld().spawn(event.getBlock().getLocation(), org.bukkit.entity.TNTPrimed.class);
            }

            if(event.getBlock().getType() == Material.PUMPKIN){
                // create iron golem
                CraftIronGolem golem = (CraftIronGolem) event.getBlock().getWorld().spawn(event.getBlock().getLocation(), org.bukkit.entity.IronGolem.class);
                golem.setPlayerCreated(true);
                golem.setCustomName(team.getColor() + "§l" + team.getName() + "§r§f's golem");
                golem.setCustomNameVisible(true);
                List<String> aggroList = new ArrayList<>();
                manager.getPlayers().forEach(player -> {
                    if (manager.getTeam(player) != team) {
                        aggroList.add(player.getName());
                    }
                });
                RunnableIronGolem runnableIronGolem = new RunnableIronGolem();
                runnableIronGolem.aggroList = aggroList;
                runnableIronGolem.golem = golem;
                runnableIronGolem.runTaskTimer(Main.getInstance(), 0, 20);
                event.getBlock().setType(Material.AIR);
            }

        }
        if(event.getBlock().getType() == Material.SKULL){
            // auto bridging 32 blocks
            new ParticleBuilder(ParticleEffect.SMOKE_LARGE, event.getBlock().getLocation())
                    .setSpeed(0.1f)
                    .setAmount(50)
                    .display();
            event.getBlock().setType(Material.AIR);
            Location locB = event.getBlock().getLocation().clone();
            Location locP = event.getPlayer().getLocation().clone();
            final int[] distance = {0};
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (distance[0] < 32) {
                        Player player = event.getPlayer();
                        Location loc = locB.clone().add(locP.getDirection().multiply(distance[0]).setY(-1));
                        if(loc.getBlock().getType() == Material.AIR) loc.getBlock().setType(Material.WOOL);
                        distance[0]++;
                        new ParticleBuilder(ParticleEffect.FLAME, loc)
                                .setSpeed(0.1f)
                                .setAmount(50)
                                .display();

                    } else {
                        this.cancel();
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0, 2);


        }
    }
    private boolean isBlockInRegion(Location blockLocation, Location pos1, Location pos2) {
        int x1 = Math.min(pos1.getBlockX(), pos2.getBlockX());
        int y1 = Math.min(pos1.getBlockY(), pos2.getBlockY());
        int z1 = Math.min(pos1.getBlockZ(), pos2.getBlockZ());
        int x2 = Math.max(pos1.getBlockX(), pos2.getBlockX());
        int y2 = Math.max(pos1.getBlockY(), pos2.getBlockY());
        int z2 = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        return blockLocation.getBlockX() >= x1 && blockLocation.getBlockX() <= x2
                && blockLocation.getBlockY() >= y1 && blockLocation.getBlockY() <= y2
                && blockLocation.getBlockZ() >= z1 && blockLocation.getBlockZ() <= z2
                && blockLocation.getWorld().equals(pos1.getWorld());
    }

}
