package fr.mathis_bruel.spacecube.bedwars.manager;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Hologram {

    private Location location;
    private List<String> lines;
    private List<EntityArmorStand> hologramEntities;
    private List<Player> viewers;
    private boolean isShown;

    public Hologram(Location location, List<String> lines) {
        this.location = location;
        this.lines = lines;
        this.hologramEntities = new ArrayList<>();
        this.viewers = new ArrayList<>();
        this.isShown = false;
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
            EntityArmorStand entity = hologramEntities.get(i);
            String line = lines.get(i);
            entity.setCustomName(line);
            sendPacket(new PacketPlayOutEntityMetadata(entity.getId(), entity.getDataWatcher(), true));
        }
    }

    private void sendHologram() {
        WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
        double currentY = location.getY();
        for (String line : lines) {
            Location lineLoc = new Location(location.getWorld(), location.getX(), currentY, location.getZ());
            EntityArmorStand entity = new EntityArmorStand(world);
            entity.setLocation(lineLoc.getX(), lineLoc.getY(), lineLoc.getZ(), 0, 0);
            entity.setSmall(true);
            entity.setGravity(false);
            entity.setCustomName(line);
            entity.setCustomNameVisible(true);
            entity.setInvisible(true);
            world.addEntity(entity);
            hologramEntities.add(entity);
            currentY -= 0.25;
        }
        for (Player viewer : viewers) {
            sendHologramTo(viewer);
        }
    }
    private void sendHologramTo(Player player) {
        for (EntityArmorStand entity : hologramEntities) {
            sendPacket(new PacketPlayOutSpawnEntityLiving(entity));
            sendPacket(new PacketPlayOutEntityMetadata(entity.getId(), entity.getDataWatcher(), true));
            sendPacket(new PacketPlayOutEntityTeleport(entity));
            if (!viewers.contains(player)) {
                sendPacket(new PacketPlayOutEntityDestroy(entity.getId()));
            }
        }
        viewers.add(player);
    }
    private void sendPacket(Player player, Packet<?> packet) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
    private void sendDestroyPacketTo(Player player) {
        for (EntityArmorStand entity : hologramEntities) {
            int entityId = entity.getId();
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(entityId);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }
    private void sendDestroyPacket() {
        int[] entityIds = new int[hologramEntities.size()];
        for (int i = 0; i < hologramEntities.size(); i++) {
            entityIds[i] = hologramEntities.get(i).getId();
        }
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(entityIds);
        for (Player viewer : viewers) {
            ((CraftPlayer) viewer).getHandle().playerConnection.sendPacket(packet);
        }
        hologramEntities.clear();
    }
    private void sendPacket(Packet<?> packet) {
        for (Player viewer : viewers) {
            sendPacket(viewer, packet);
        }
    }

}