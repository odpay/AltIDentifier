package net.odpay.altidentifier;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import net.odpay.altidentifier.db.Database;
import net.odpay.altidentifier.fingerprintUtil.FingerprintManager;
import net.odpay.altidentifier.handlers.ChannelRegisterHandler;
import net.odpay.altidentifier.handlers.PluginMessageListener;
import net.odpay.altidentifier.handlers.ClientInfoHandler;
import net.odpay.altidentifier.handlers.ConnectionHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.crypto.Data;
import java.util.logging.Logger;

public final class AltIDentifier extends JavaPlugin {
    private static AltIDentifier instance;
    private static Logger logger;
    public Database db;
    public ProtocolManager protocolManager;

    public static FingerprintManager manager;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();


        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
//        saveDefaultConfig();

        db = new Database(this);

        protocolManager = ProtocolLibrary.getProtocolManager();
        manager = new FingerprintManager();

        new ConnectionHandler(this);
        if (config.getBoolean("settings.data-points.client-mods")) { new ChannelRegisterHandler(this); }
        if (config.getBoolean("settings.data-points.client-settings")) { protocolManager.addPacketListener(new ClientInfoHandler(this)); }

        if (config.getBoolean("settings.data-points.brand")) {
            getServer().getMessenger().registerIncomingPluginChannel(this, "minecraft:brand", new PluginMessageListener()); // >=1.13
//            getServer().getMessenger().registerIncomingPluginChannel(this, "MC|Brand", new PluginMessageListener()); // <=1.12.2
        }
        logger.info("AltIDentifier initialised!");



    }

    @Override
    public void onDisable() {
        logger.info("Stopping AltIDentifier!");
    }

    public static AltIDentifier getInstance() {
        return instance;
    }


    public static Logger getLog() {
        return logger;
    }

}
