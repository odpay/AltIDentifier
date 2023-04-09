package net.odpay.altidentifier.fingerprintUtil;
import com.google.common.base.MoreObjects;

import java.net.InetSocketAddress;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

public class Fingerprint {

    final UUID uuid;
    final String name;
    final String ip;
    final PingTracker ping;
    String brand;
    LinkedHashSet<String> registeredChannels;
    List settings;
    final Instant createdAt;
    private Instant resolvedAt;

    public Fingerprint(final UUID uuid, final String name, final String ip) {
        this.uuid = uuid;
        this.name = name;
        this.ip = ip;
        this.ping = new PingTracker();
        this.brand = null;
        this.registeredChannels = new LinkedHashSet<String>();
        this.settings = null;
        this.createdAt = Instant.now();
        this.resolvedAt = null;
    }

    public Boolean setSettings(List vals) {
        if (vals.equals(this.settings)) {
            return false;
        }
        this.settings = vals;
        return true;
    }

    public void resolve() {
        this.resolvedAt = Instant.now();
    }

    public void recordPing(int latencyMS) {
        if (latencyMS > 0) {
            this.ping.recordPing(latencyMS);
        }
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void regChannel(String channel) {
//        this.registeredChannels.add(channel.split(":")[0]);
        this.registeredChannels.add(channel);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("uuid", uuid)
                .add("name", name)
                .add("ip", ip)
                .add("ping", ping.getAvg())
                .add("brand", brand)
                .add("channels", registeredChannels)
                .add("createdAt", createdAt)
                .add("resolvedAt", resolvedAt)
                .add("settings", settings)
                .toString();
    }
}
