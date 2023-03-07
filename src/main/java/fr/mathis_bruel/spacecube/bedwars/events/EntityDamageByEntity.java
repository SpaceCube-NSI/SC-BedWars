package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Death;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.game.State;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import fr.mathis_bruel.spacecube.bedwars.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityDamageByEntity implements org.bukkit.event.Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity() != null) {
            Player player = event.getEntity();
            if (Manager.isCurrentlyInGame(player)) {
                event.setDeathMessage(null);
            }
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(Manager.isCurrentlyInGame(player)){
                if(event.getDamager() instanceof Player){
                    Player damager = (Player) event.getDamager();
                    if(Manager.isCurrentlyInGame(damager)){
                        // if is in the same team
                        if(Manager.getManager(player).getTeam(player) == Manager.getManager(damager).getTeam(damager)){
                            event.setCancelled(true);
                            damager.sendMessage("§cYou can't hurt your own team!");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (player.getHealth() <= event.getFinalDamage()) {
                if (Manager.isCurrentlyInGame(player)) {
                    Manager manager = Manager.getManager(player);
                    Team team = manager.getTeam(player);
                    if (team != null) {
                        List<String> m = new ArrayList<>();
                        switch (event.getCause()) {
                            case BLOCK_EXPLOSION:
                                m.add("was blown up.");
                                m.add("was destroyed by an explosion.");
                                m.add("went boom... §oBOOM!");
                                break;
                            case CONTACT:
                            case CUSTOM:
                                break;
                            case DROWNING:
                                m.add("drowned.");
                                m.add("didn't know how to swim.");
                                m.add("glug glug glug...");
                                break;
                            case ENTITY_ATTACK:
                                Player killer = ((Player) event.getEntity()).getKiller();
                                if(!Manager.isCurrentlyInGame(killer)) return;
                                String s = killer.getName();
                                Manager.getManager(killer).addPlayerKill(killer);
                                killer.playSound(killer.getLocation(), "ORB_PICKUP", 1, 1);
                                m.add("was killed by " + s + "§r.");
                                m.add("was slain by " + s + "§r.");
                                m.add("was murdered by " + s + "§r.");
                                m.add("lost the fight against " + s + "§r.");
                                m.add("lost the battle against " + s + "§r.");
                                m.add("fought " + s + "§r.");
                                m.add("opposed " + s + "§r.");
                                m.add("challenged " + s + "§r and lost...");
                                break;
                            case ENTITY_EXPLOSION:
                                m.add("was blown up.");
                                m.add("doesn't like creepers. ^^'");
                                m.add("went boom... §oBOOM!");
                                break;
                            case FALL:
                                m.add("fell to their death.");
                                m.add("hit the ground too hard.");
                                m.add("fell too fast.");
                                m.add("took a failed dive.");
                                m.add("thought they were a bird.");
                                m.add("turned into a pancake.");
                                break;
                            case FALLING_BLOCK:
                                m.add("was killed by a falling block.");
                                break;
                            case FIRE:
                                m.add("was instantly incinerated!!");
                                break;
                            case FIRE_TICK:
                                m.add("was burned.");
                                m.add("doesn't like flames.");
                                m.add("was consumed by fire.");
                                m.add("was killed by fire.");
                                m.add("wanted to have a barbecue.");
                                break;
                            case LAVA:
                                m.add("swam in lava.");
                                m.add("tried to take a dip in magma.");
                                m.add("wanted to jump in the lava.");
                                break;
                            case LIGHTNING:
                                m.add("was struck by lightning.");
                                m.add("was electrocuted.");
                                m.add("was hit by lightning.");
                                m.add("was zapped by a bolt of lightning.");
                                break;
                            case MAGIC:
                                m.add("was killed by magic.");
                                break;
                            case MELTING:
                                m.add("melted.");
                                break;
                            case POISON:
                                m.add("was poisoned.");
                                break;
                            case PROJECTILE:
                                m.add("was shot.");
                                break;
                            case STARVATION:
                                m.add("died of starvation.");
                                m.add("forgot to eat.");
                                break;
                            case SUFFOCATION:
                                m.add("suffocated.");
                                m.add("asphyxiated.");
                                m.add("choked to death.");
                                break;
                            case SUICIDE:
                                m.add("committed suicide. lol.");
                                break;
                            case THORNS:
                                m.add("was killed by their own attack.");
                                break;
                            case VOID:
                                m.add("fell into the void.");
                                m.add("entered the void.");
                                m.add("fell into the infinite...");
                                break;
                            case WITHER:
                                m.add("was infected by the wither.");
                                break;
                            default:
                                break;
                        }
                        if (m.size() == 0) {
                            m.add("was dead");
                        }
                        manager.addPlayerDeath(player);
                        player.spigot().respawn();
                        player.setHealth(20);
                        player.setFoodLevel(20);
                        for (ItemStack item : player.getInventory().getContents()) {
                            if (item != null) {
                                player.getWorld().dropItemNaturally(player.getLocation(), item);
                            }
                        }
                        player.teleport(manager.getArena().getSpecSpawn());
                        player.setGameMode(GameMode.SPECTATOR);

                        player.getInventory().clear();
                        System.out.println(team.getName() + " " + team.isBedAlive());
                        if(team.isBedAlive()) {
                            manager.broadcast(team.getColor() + player.getName() + " §r§7" + m.get(new Random().nextInt(m.size())));
                            Death death = new Death();
                            death.player = player;
                            death.runTaskTimer(Main.getInstance(), 0, 20);
                        }else{
                            manager.broadcast(team.getColor() + player.getName() + " §r§7" + m.get(new Random().nextInt(m.size()))+ " §7| §r§l§bFINAL ELIMINATION!");
                            manager.broadcast("§7------------------");
                            manager.broadcast("§c§l" + team.getColor() + "§l" + team.getName() + " §r§chas been eliminated!");
                            manager.broadcast("§7------------------");
                            player.sendTitle("§c§lELIMINATED", "§r§7You were eliminated from the game!");
                            team.removePlayer(player);
                            manager.removePlayer(player);
                            manager.addSpecator(player);
                            System.out.println(team.getName() + " " + team.getPlayers().size());
                        }
                    }
                }
            }

            // if spawn protection
            if (Manager.isCurrentlyInGame(player)) {
                if (Main.isGodMode(player)) {
                    if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                        if (event.getEntity() instanceof Player) {
                            Player enemy = (Player) event.getEntity();
                            if (Manager.isCurrentlyInGame(enemy)) {
                                enemy.sendMessage("§c" + player.getName() + " §7is in spawn protection.");
                                event.setCancelled(true);

                            }
                        }
                    }
                }
            }
            // if enemy is in spawn protection
            if (Manager.isCurrentlyInGame(player)) {
                if (Main.isGodMode(player)) {
                    if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                        if (event.getEntity() instanceof Player) {
                            Player enemy = (Player) event.getEntity();
                            if (Manager.isCurrentlyInGame(enemy)) {
                                Main.removeGodMode(enemy);
                            }
                        }
                    }
                }
            }

            // if is not in game and in lobby world
            if (!Manager.isCurrentlyInGame(player)) {
                if (Utils.parseStringToLoc(Main.getInstance().getConfig().getString("lobby")).getWorld() == player.getWorld()) {
                    event.setCancelled(true);
                }
            }

            // if is in game and not State.RUNNING
            if (Manager.isCurrentlyInGame(player)) {
                if (Manager.getManager(player).getManagerState().getCurrentState() != State.RUNNING) {
                    if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                        player.teleport(Manager.getManager(player).getArena().getLobbySpawn());
                    }
                    event.setCancelled(true);
                }
            }
        }
    }


}
