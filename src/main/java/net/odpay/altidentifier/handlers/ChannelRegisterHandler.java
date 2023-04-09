package net.odpay.altidentifier.handlers;

import net.odpay.altidentifier.AltIDentifier;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;

import java.util.List;

public class ChannelRegisterHandler implements Listener {
    private final AltIDentifier plugin;

    public ChannelRegisterHandler(AltIDentifier plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerRegisterChannel(PlayerRegisterChannelEvent event) {
        AltIDentifier.manager.regChannel(event.getPlayer().getUniqueId(), event.getChannel());
        System.out.println("Player: '" + event.getPlayer().getName() + "' registered channel: " + event.getChannel());
//        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, event.getChannel(), new PluginMessageListener());
    }
}
