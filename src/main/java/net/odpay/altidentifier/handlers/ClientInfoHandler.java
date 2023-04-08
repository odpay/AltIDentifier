package net.odpay.altidentifier.handlers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.EquivalentConverter;
import net.odpay.altidentifier.AltIDentifier;
import net.odpay.altidentifier.fingerprintUtil.FingerprintManager;
import org.bukkit.Bukkit;
import org.checkerframework.checker.units.qual.K;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClientInfoHandler extends PacketAdapter {


    public ClientInfoHandler(AltIDentifier plugin) {
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.SETTINGS);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        PacketContainer packet = event.getPacket();
//        System.out.println("hiii packet");
//        System.out.println(event.getPlayer().getPing());
//        System.out.println(event.getPlayer().getPlayerTimeOffset());
        FingerprintManager.fingerprintPool.get(event.getPlayer().getUniqueId()).setSettings(packet.getModifier().getValues());
        System.out.println(FingerprintManager.fingerprintPool.get(event.getPlayer().getUniqueId()));
    }

//    public Map<String, Object> mapSettings(List vals) {
//        final List<String> KEYS = Arrays.asList("language", "viewDistance", "chatVisibility", "chatColors", "modelCustomisation", "mainHand", "textFilteringEnabled", "allowsListing");
//        Map<String, Object> result = IntStream.range(0, KEYS.size())
//                .boxed()
//                .collect(Collectors.toMap(KEYS::get, vals::get));
//        return result;
//    }

}
