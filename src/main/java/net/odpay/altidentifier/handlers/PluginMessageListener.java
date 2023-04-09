package net.odpay.altidentifier.handlers;

import net.odpay.altidentifier.AltIDentifier;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PluginMessageListener implements org.bukkit.plugin.messaging.PluginMessageListener {



    public void onPluginMessageReceived(String channel, Player player, byte[] data) {
        byte[] customData = new String(data, StandardCharsets.UTF_8).replace(" (Velocity)", "").getBytes(StandardCharsets.UTF_8);
        String parsedData = new String(customData, StandardCharsets.UTF_8);

        if (Arrays.asList("minecraft:brand", "MC|Brand").contains(channel)) {
            AltIDentifier.manager.setBrand(player.getUniqueId(), parsedData.substring(1)); // for some reason the first char is wacky
        }
    }
}
