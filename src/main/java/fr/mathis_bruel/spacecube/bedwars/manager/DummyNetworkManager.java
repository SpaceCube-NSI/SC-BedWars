package fr.mathis_bruel.spacecube.bedwars.manager;

import net.minecraft.server.v1_8_R3.EnumProtocolDirection;
import net.minecraft.server.v1_8_R3.NetworkManager;

public class DummyNetworkManager extends NetworkManager {

    public DummyNetworkManager(EnumProtocolDirection enumprotocoldirection) {
        super(enumprotocoldirection);
    }
}