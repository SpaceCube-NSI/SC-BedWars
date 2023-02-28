package fr.mathis_bruel.spacecube.bedwars.events;

import fr.mathis_bruel.spacecube.bedwars.Main;
import fr.mathis_bruel.spacecube.bedwars.game.Death;
import fr.mathis_bruel.spacecube.bedwars.game.Manager;
import fr.mathis_bruel.spacecube.bedwars.teams.Team;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityDamageByEntity implements org.bukkit.event.Listener {

   /* @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(player.getHealth() <= 1) {
                if(Manager.isCurrentlyInGame(player)) {
                    Team team = Manager.getManager(player).getTeam(player);
                    if(team != null) {
                        player.spigot().respawn();
                        System.out.println("Player " + player.getName() + " is dead!");
                        player.teleport(team.getSpawn());
                        Manager.getManager(player).broadcast("§c" + player.getName() + " has been killed by " + event.getDamager().getName());
                        Death death = new Death();
                        death.player = player;
                        death.runTaskTimer(Main.getInstance(), 0, 20);
                    }
                }
            }
        }
    }*/

    @EventHandler
    public void onDeath(EntityDamageEvent event) {
        System.out.println(1);
        if(event.getEntity() instanceof Player) {
            System.out.println(2);
            Player player = (Player) event.getEntity();
            if(player.getHealth() <= event.getFinalDamage()) {
                System.out.println(3);
                if(Manager.isCurrentlyInGame(player)) {
                    System.out.println(4);
                    Team team = Manager.getManager(player).getTeam(player);
                    if(team != null) {
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
                                String s = ((Player) event.getEntity()).getKiller().getName();
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
                        Manager.getManager(player).broadcast(team.getColor()+"§l"+player.getName() + " §r§4" + m.get(new Random().nextInt(m.size())));
                        player.spigot().respawn();
                        System.out.println("Player " + player.getName() + " is dead!");
                        player.teleport(Manager.getManager(player).getArena().getSpecSpawn());
                        player.setGameMode(GameMode.SPECTATOR);
                        Death death = new Death();
                        death.player = player;
                        death.runTaskTimer(Main.getInstance(), 0, 20);
                    }
                }
            }
        }
    }


}
