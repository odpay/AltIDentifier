package net.odpay.altidentifier.handlers;

import net.odpay.altidentifier.AltIDentifier;
import net.odpay.altidentifier.fingerprintUtil.Fingerprint;
import net.odpay.altidentifier.fingerprintUtil.FingerprintManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class ConnectionHandler implements Listener {
    public ConnectionHandler(AltIDentifier plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) { // cannot fetch player client info here, as it hasn't been recieved yet and will just be defaults.
        Player player = event.getPlayer();
        AltIDentifier.manager.add(new Fingerprint(player.getUniqueId(), player.getName(), (AltIDentifier.config.getBoolean("settings.data-points.ip") ? player.getAddress().getAddress().getHostAddress() : null)));
        AltIDentifier.manager.recordPing(player.getUniqueId(), player.getPing());
        System.out.println(AltIDentifier.manager.getAll().toString());
//        Bukkit.getLogger().info("beamaaaed$$$ " + event.getPlayer().getAddress().toString());

    }


    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        System.out.println(AltIDentifier.manager.get(player.getUniqueId()));
        AltIDentifier.manager.remove(player.getUniqueId());
        System.out.println(AltIDentifier.manager.getAll().toString());
//        System.out.println("asdasdasdasd");
    }

    @EventHandler
    public void p(AsyncPlayerChatEvent event) {
        Bukkit.getServer().broadcastMessage(String.valueOf(event.getPlayer().getPing()));
        if (event.getMessage() == "f") {
            Bukkit.getServer().broadcastMessage(AltIDentifier.manager.getAll().toString());
        }
    }
}
