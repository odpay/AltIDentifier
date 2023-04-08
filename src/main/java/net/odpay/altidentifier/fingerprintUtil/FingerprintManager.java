package net.odpay.altidentifier.fingerprintUtil;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.UUID;

public class FingerprintManager {

    public static HashMap<UUID, Fingerprint> fingerprintPool = new HashMap<UUID, Fingerprint>();

    public void add(Fingerprint fingerprint) {
        fingerprintPool.put(fingerprint.uuid, fingerprint);
    }

    public void remove(UUID uuid) {
        fingerprintPool.remove(uuid).resolve();
    }

    private void log(Fingerprint fingerprint) {

    }

    public void recordPing(UUID uuid, int latencyMS) {
        fingerprintPool.get(uuid).recordPing(latencyMS);
    }
    public Fingerprint get(UUID uuid) {
        return fingerprintPool.get(uuid);
    }

    public final HashMap<UUID, Fingerprint> getAll() {
        return fingerprintPool;
    }



}
