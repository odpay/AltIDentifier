package net.odpay.altidentifier;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import net.odpay.altidentifier.fingerprintUtil.FingerprintManager;
import net.odpay.altidentifier.handlers.ClientInfoHandler;
import net.odpay.altidentifier.handlers.ConnectionHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AltIDentifier extends JavaPlugin {
    private static Logger logger;
    public ProtocolManager protocolManager;

    public static FingerprintManager manager;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        logger = getLogger();


        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
//        saveDefaultConfig();

        protocolManager = ProtocolLibrary.getProtocolManager();
        manager = new FingerprintManager();

        new ConnectionHandler(this);
        if (config.getBoolean("settings.data-points.client-settings")) { protocolManager.addPacketListener(new ClientInfoHandler(this)); }
        logger.info("AltIDentifier initialised!");



    }

    @Override
    public void onDisable() {
        logger.info("Stopping AltIDentifier!");
    }


    public static Logger getLog() {
        return logger;
    }

}
