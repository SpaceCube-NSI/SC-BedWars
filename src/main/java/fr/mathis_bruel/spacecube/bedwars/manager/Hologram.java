package fr.mathis_bruel.spacecube.bedwars.manager;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Hologram {

    private List<EntityArmorStand> holograms;

    public Hologram() {
        this.holograms = new ArrayList<>();
    }

    public EntityArmorStand createHologram(Location location, String... lines) {
        EntityArmorStand hologram = new EntityArmorStand(((CraftPlayer) Bukkit.getServer().getPlayer("Hologram")).getHandle().getWorld(), location.getX(), location.getY(), location.getZ());
        hologram.setGravity(false);
        hologram.setCustomNameVisible(true);
        hologram.setInvisible(true);
        hologram.setSmall(true);
        for (int i = 0; i < lines.length; i++) {
            hologram.setCustomName(lines[i]);
            sendPacket(new PacketPlayOutSpawnEntityLiving(hologram));
            holograms.add(hologram);
            hologram.setCustomName("");
            hologram.setLocation(location.getX(), location.getY() - (0.25 * i), location.getZ(), location.getYaw(), location.getPitch());
            sendPacket(new PacketPlayOutEntityTeleport(hologram));
        }
        return hologram;
    }

    public void addLine(EntityArmorStand hologram, String line) {
        hologram.setCustomName(line);
        int index = holograms.indexOf(hologram);
        if (index != -1) {
            sendPacket(new PacketPlayOutSpawnEntityLiving(hologram));
            holograms.add(index, hologram);
            updateHologramLocations();
        }
        hologram.setCustomName("");
    }

    public void removeLine(EntityArmorStand hologram) {
        if (holograms.contains(hologram)) {
            holograms.remove(hologram);
            sendPacket(new PacketPlayOutEntityDestroy(hologram.getId()));
            updateHologramLocations();
        }
    }

    public void insertLine(EntityArmorStand hologram, int index, String line) {
        hologram.setCustomName(line);
        if (index >= 0 && index <= holograms.size()) {
            sendPacket(new PacketPlayOutSpawnEntityLiving(hologram));
            holograms.add(index, hologram);
            updateHologramLocations();
        }
        hologram.setCustomName("");
    }

    private void updateHologramLocations() {
        for (int i = 0; i < holograms.size(); i++) {
            EntityArmorStand hologram = holograms.get(i);
            Location location = hologram.getBukkitEntity().getLocation();
            hologram.setLocation(location.getX(), location.getY() - (0.25 * i), location.getZ(), location.getYaw(), location.getPitch());
            sendPacket(new PacketPlayOutEntityTeleport(hologram));
        }
    }
    private void sendPacket(Packet<?> packet) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void deleteHologram(EntityArmorStand hologram) {
        if (holograms.contains(hologram)) {
            holograms.remove(hologram);
            sendPacket(new PacketPlayOutEntityDestroy(hologram.getId()));
        }
    }

    public void deleteAllHolograms() {
        for (EntityArmorStand hologram : holograms) {
            sendPacket(new PacketPlayOutEntityDestroy(hologram.getId()));
        }
        holograms.clear();
    }

}