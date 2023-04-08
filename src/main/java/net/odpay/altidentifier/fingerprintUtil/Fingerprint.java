package net.odpay.altidentifier.fingerprintUtil;
import com.google.common.base.MoreObjects;

import java.net.InetSocketAddress;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Fingerprint {

    final UUID uuid;
    final String name;
    final String ip;
    final PingTracker ping;
    final Instant createdAt;
    List settings;
    private Instant resolvedAt;

    public Fingerprint(final UUID uuid, final String name, final String ip) {
        this.uuid = uuid;
        this.name = name;
        this.ip = ip;
        this.ping = new PingTracker();
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
        this.ping.recordPing(latencyMS);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("uuid", uuid)
                .add("name", name)
                .add("ip", ip)
                .add("ping", ping.getAvg())
                .add("createdAt", createdAt)
                .add("resolvedAt", resolvedAt)
                .add("settings", settings)
                .toString();
    }
}
