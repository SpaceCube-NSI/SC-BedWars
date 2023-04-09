package fr.mathis_bruel.spacecube.bedwars.manager;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.mathis_bruel.spacecube.bedwars.Main;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class NPCManager {
    private UUID npcUUID;
    private EntityType entityType;
    private Location location;
    private String npcName;
    private EntityPlayer entityPlayer;
    private String data;
    private String signature;

    public NPCManager(Location location, EntityType entityType, String npcName) throws IOException {
        this.location = location;
        this.entityType = entityType;
        this.npcName = npcName;

        spawnNPC();

    }

    public NPCManager(Location location, EntityType entityType, String npcName, String skinId) throws IOException {
        this.location = location;
        this.entityType = entityType;
        this.npcName = npcName;

        spawnNPC(skinId);

    }

    public void spawnNPC() throws IOException {
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) location.getWorld()).getHandle();
        GameProfile profile = new GameProfile(UUID.randomUUID(), npcName);
        PlayerInteractManager interactManager = new PlayerInteractManager(nmsWorld);

        NPCEntity entityPlayer = new NPCEntity(nmsServer, nmsWorld, profile, interactManager);
        new PlayerConnection(nmsServer, new DummyNetworkManager(EnumProtocolDirection.CLIENTBOUND), entityPlayer);

        entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(),
                location.getPitch());
        //entityPlayer.setCustomNameVisible(false);

        nmsWorld.addEntity(entityPlayer);

        PacketPlayOutPlayerInfo playerInfoAdd = new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer);
        PacketPlayOutNamedEntitySpawn namedEntitySpawn = new PacketPlayOutNamedEntitySpawn(entityPlayer);
        PacketPlayOutEntityHeadRotation headRotation = new PacketPlayOutEntityHeadRotation(entityPlayer,
                (byte) ((location.getYaw() * 256f) / 360f));
        PacketPlayOutPlayerInfo playerInfoRemove = new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer);

        if (data != null && signature != null) {
            GameProfile gameProfile = entityPlayer.getProfile();
            gameProfile.getProperties().put("textures", new Property("textures", data, signature));
            entityPlayer.updateAbilities();
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(playerInfoAdd);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(namedEntitySpawn);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(headRotation);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(playerInfoRemove);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer));
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer));

        }

        npcUUID = entityPlayer.getUniqueID();
        this.entityPlayer = entityPlayer;
    }

    public void spawnNPC(String skinId) throws IOException {
        try {
            String skinUrl = "https://api.mineskin.org/get/uuid/" + skinId;
            URL url = new URL(skinUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept", "application/json");

            // put in json the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // convert StringBuilder to json
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response.toString());

            // get the texture value and signature
            String skinData = (String) ((JSONObject) ((JSONObject) json.get("data")).get("texture")).get("value");
            String signature = (String) ((JSONObject) ((JSONObject) json.get("data")).get("texture")).get("signature");
            MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
            WorldServer nmsWorld = ((CraftWorld) location.getWorld()).getHandle();
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), npcName);
            gameProfile.getProperties().put("textures", new Property("textures", skinData, signature));
            PlayerInteractManager interactManager = new PlayerInteractManager(nmsWorld);
            //entityPlayer.updateAbilities();
            NPCEntity entityPlayer = new NPCEntity(nmsServer, nmsWorld, gameProfile, interactManager);
            entityPlayer.setCustomNameVisible(false); // masque le nom affiché au-dessus de l'entité
            Field ff = gameProfile.getClass().getDeclaredField("name");
            ff.setAccessible(true);
            ff.set(gameProfile, " ");
            new PlayerConnection(nmsServer, new DummyNetworkManager(EnumProtocolDirection.CLIENTBOUND), entityPlayer);

            entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(),
                    location.getPitch());

            nmsWorld.addEntity(entityPlayer);

            PacketPlayOutPlayerInfo playerInfoAdd = new PacketPlayOutPlayerInfo(
                    PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer);
            PacketPlayOutNamedEntitySpawn namedEntitySpawn = new PacketPlayOutNamedEntitySpawn(entityPlayer);
            PacketPlayOutEntityHeadRotation headRotation = new PacketPlayOutEntityHeadRotation(entityPlayer,
                    (byte) ((location.getYaw() * 256f) / 360f));
            PacketPlayOutPlayerInfo playerInfoRemove = new PacketPlayOutPlayerInfo(
                    PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer);


            PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer);
            for (Player player : Bukkit.getOnlinePlayers()) {

                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer));
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer));

                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(playerInfoAdd);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(namedEntitySpawn);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(headRotation);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(playerInfoRemove);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer));
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer));
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
            npcUUID = entityPlayer.getUniqueID();
            this.entityPlayer = entityPlayer;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void edit(String newName, Location newLocation, NBTTagCompound newNbtTag, EntityType newEntityType) {
        // Met à jour l'entité
        entityPlayer.getWorld().removeEntity(entityPlayer);
        this.npcName = newName;
        this.entityType = newEntityType;
        this.location = newLocation;
        this.entityPlayer = createEntityPlayer(newName, newLocation, newNbtTag);
        // Affiche l'entité modifiée pour chaque joueur en ligne
        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            PlayerConnection connection = craftPlayer.getHandle().playerConnection;
            // Supprime l'entité originale
            connection.sendPacket(new PacketPlayOutEntityDestroy(entityPlayer.getId()));
            // Affiche la nouvelle entité
            connection.sendPacket(new PacketPlayOutSpawnEntityLiving(entityPlayer));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));
        }
    }

    private EntityPlayer createEntityPlayer(String name, Location loc, NBTTagCompound nbtTag) {
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        GameProfile profile = new GameProfile(UUID.randomUUID(), name);
        PlayerInteractManager interactManager = new PlayerInteractManager(nmsWorld);

        NPCEntity entityPlayer = new NPCEntity(nmsServer, nmsWorld, profile, interactManager);

        entityPlayer.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());

        if (nbtTag != null) {
            entityPlayer.f(nbtTag);
        }

        nmsWorld.addEntity(entityPlayer);

        return entityPlayer;
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

    public void teleport(Location location) {
        this.location = location;
        entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

        PacketPlayOutEntityTeleport teleportPacket = new PacketPlayOutEntityTeleport(entityPlayer);
        PacketPlayOutEntityHeadRotation headRotation = new PacketPlayOutEntityHeadRotation(entityPlayer, (byte) ((location.getYaw() * 256f) / 360f));

        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(teleportPacket);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(headRotation);
        }
    }

    public void teleportToPlayer(Player player) {
        this.teleport(player.getLocation());
    }

    public void setSkin(String skinId) {
        try {
            String skinUrl = "https://api.mineskin.org/get/uuid/" + skinId;
            URL url = new URL(skinUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept", "application/json");

            // put in json the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // convert StringBuilder to json
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response.toString());

            // get the texture value and signature
            String skinData = (String) ((JSONObject) ((JSONObject) json.get("data")).get("texture")).get("value");
            String signature = (String) ((JSONObject) ((JSONObject) json.get("data")).get("texture")).get("signature");

            GameProfile gameProfile = entityPlayer.getProfile();
            gameProfile.getProperties().put("textures", new Property("textures", skinData, signature));
            entityPlayer.updateAbilities();
            for (Player player : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer));
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void destroy() {
        if (entityPlayer != null) {
            PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(entityPlayer.getId());
            for (Player player : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(destroyPacket);
            }

            entityPlayer.getWorld().removeEntity(entityPlayer);
            entityPlayer = null;
            Main.removeNPC(this);
        }
    }
}
