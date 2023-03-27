package fr.mathis_bruel.spacecube.bedwars.manager;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.UUID;

public class NPCManager {
    private UUID npcUUID;
    private EntityType entityType;
    private Location location;
    private String npcName;

    public NPCManager(Location location, EntityType entityType, String npcName) throws IOException {
        this.location = location;
        this.entityType = entityType;
        this.npcName = npcName;

        spawnNPC();
        System.out.println("Le PNJ " + npcName + " a été créé à l'emplacement " + location.toString());

    }

    public void spawnNPC() throws IOException {
        Player fakePlayer = Bukkit.getServer().getPlayer(npcName);
        if (fakePlayer != null) {
            fakePlayer.kickPlayer("Kick");
            fakePlayer.remove();
        }
        System.out.println(1);
        // get the WorldServer and PlayerInteractManager for the new entity
        WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
        PlayerInteractManager interactManager = new PlayerInteractManager(world);
        System.out.println(2);
        // create GameProfil
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        GameProfile gameProfile = SkinUtil.getGameProfile(npcName);


        System.out.println(3);
        if (gameProfile == null) {
            gameProfile = new GameProfile(UUID.randomUUID(), npcName);
        }
        System.out.println(4);
        // create the EntityPlayer and set its position and name
        EntityPlayer entityPlayer = new EntityPlayer(server, world, gameProfile, interactManager);
        entityPlayer.setPosition(location.getX(), location.getY(), location.getZ());
        System.out.println(5);
        // spawn the entity and add it to the world
        world.addEntity(entityPlayer);
        ((CraftPlayer) entityPlayer.getBukkitEntity()).setPlayerListName(npcName);
        System.out.println(6);
        System.out.println(fakePlayer);
        System.out.println(world);
        System.out.println(gameProfile);
        System.out.println(entityPlayer);

        this.npcUUID = entityPlayer.getUniqueID();
        System.out.println(7);
    }

    public void despawnNPC() {
        Player fakePlayer = Bukkit.getServer().getPlayer(npcUUID);
        if (fakePlayer != null) {
            fakePlayer.kickPlayer("Kick");
            fakePlayer.remove();
        }
    }

    public UUID getNpcUUID() {
        return npcUUID;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public Location getLocation() {
        return location;
    }

    public String getNpcName() {
        return npcName;
    }
}
