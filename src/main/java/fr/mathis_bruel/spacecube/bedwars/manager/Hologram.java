package fr.mathis_bruel.spacecube.bedwars.manager;

import fr.mathis_bruel.spacecube.bedwars.Main;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Hologram {

    private Location location;
    private List<String> lines;
    private List<ArmorStand> hologramEntities;
    private List<Player> viewers;
    private boolean isShown;

    public Hologram(Location location, List<String> lines) {
        this.location = location;
        this.lines = lines;
        this.hologramEntities = new ArrayList<>();
        this.viewers = new ArrayList<>();
        this.isShown = false;
        Main.addHologram(this);
    }

    public void addLine(String line) {
        lines.add(line);
        if (isShown) {
            updateHologram();
        }
    }

    public void removeLine(int index) {
        lines.remove(index);
        if (isShown) {
            updateHologram();
        }
    }

    public void insertLine(int index, String line) {
        lines.add(index, line);
        if (isShown) {
            updateHologram();
        }
    }

    public void showHologramTo(Player player) {
        if (!viewers.contains(player)) {
            viewers.add(player);
            if (isShown) {
                sendHologramTo(player);
            }
        }
    }

    public void hideHologramFrom(Player player) {
        if (viewers.contains(player)) {
            viewers.remove(player);
            if (isShown) {
                sendDestroyPacketTo(player);
            }
        }
    }

    public void destroyHolograms(){
        for (ArmorStand entity : hologramEntities) {
            entity.remove();
        }
    }

    public void showHologram() {
        if (!isShown) {
            isShown = true;
            sendHologram();
        }
    }

    public void hideHologram() {
        if (isShown) {
            isShown = false;
            sendDestroyPacket();
        }
    }

    public void updateHologram() {
        for (int i = 0; i < lines.size(); i++) {
            ArmorStand entity = hologramEntities.get(i);
            String line = lines.get(i);
            entity.setCustomName(line);
            entity.setCustomNameVisible(true);
        }
    }

    private void sendHologram() {
        double currentY = location.getY() + 0.25 * (lines.size() - 1);
        for (String line : lines) {
            Location lineLoc = new Location(location.getWorld(), location.getX(), currentY, location.getZ());
            ArmorStand entity = (ArmorStand) location.getWorld().spawnEntity(lineLoc, EntityType.ARMOR_STAND);
            entity.setVisible(false);
            entity.setGravity(false);
            entity.setCanPickupItems(false);
            entity.setCustomName(line);
            entity.setCustomNameVisible(true);
            entity.setMarker(true);
            hologramEntities.add(entity);
            currentY -= 0.25;
        }
        for (Player viewer : viewers) {
            sendHologramTo(viewer);
        }
    }

    private void sendHologramTo(Player player) {
        for (ArmorStand entity : hologramEntities) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityLiving(((CraftArmorStand) entity).getHandle()));
        }
        viewers.add(player);
    }

    private void sendDestroyPacketTo(Player player) {
        for (ArmorStand entity : hologramEntities) {
            Entity bukkitEntity = ((CraftEntity) entity).getHandle().getBukkitEntity();
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(bukkitEntity.getEntityId()));
        }
    }



    private void sendDestroyPacket() {
        for (Player viewer : viewers) {
            for (ArmorStand entity : hologramEntities) {
                entity.remove();
            }
            hologramEntities.clear();
        }
    }
}