package fr.mathis_bruel.spacecube.bedwars.manager;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;

public class NPCEntity extends EntityPlayer {

    public NPCEntity(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile,
                     PlayerInteractManager playerinteractmanager) {
        super(minecraftserver, worldserver, gameprofile, playerinteractmanager);
        // TODO Auto-generated constructor stub
    }

}