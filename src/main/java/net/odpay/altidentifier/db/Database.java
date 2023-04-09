package net.odpay.altidentifier.db;

import net.odpay.altidentifier.AltIDentifier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    Connection c;

    public Database(AltIDentifier plugin) {
        try {
            this.c = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + "/" + plugin.getConfig().getString("settings.database.path"));
        } catch (SQLException e) {
            plugin.getLogger().severe(e.toString());
        }
    }

//    c = DriverManager
}
