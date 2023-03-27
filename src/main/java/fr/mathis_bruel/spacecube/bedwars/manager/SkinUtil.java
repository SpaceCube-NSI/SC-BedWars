package fr.mathis_bruel.spacecube.bedwars.manager;

import com.google.common.base.Charsets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class SkinUtil {

    public static GameProfile getGameProfile(String name) throws IOException {
        UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8));
        GameProfile profile = new GameProfile(uuid, name);
        PropertyMap propertyMap = profile.getProperties();
        propertyMap.clear();
        Property skinProperty = SkinUtil.getSkinData(name);
        propertyMap.put(skinProperty.getName(), skinProperty);
        return profile;
    }


    public static Property getSkinData(String playerName) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
        URLConnection connection = url.openConnection();
        String json = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
        String uuid = UUID.randomUUID().toString();

        url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
        connection = url.openConnection();
        JsonObject jsonObject = new JsonParser().parse(new BufferedReader(new InputStreamReader(connection.getInputStream()))).getAsJsonObject();
        JsonArray properties = jsonObject.getAsJsonArray("properties");

        if (properties == null) {
            throw new IOException("No properties found for player: " + playerName);
        }

        for (JsonElement propertyElement : properties) {
            JsonObject property = propertyElement.getAsJsonObject();
            String name = property.get("name").getAsString();
            if (name.equals("textures")) {
                String value = property.get("value").getAsString();
                String signature = property.get("signature").getAsString();
                return new Property(name, value, signature);
            }
        }


        throw new IOException("Unable to retrieve skin data for player: " + playerName);
    }


}