package com.arionmc.arioncore.nms;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.nms.NmsWrapper;
import com.arionmc.arioncore.api.nms.Tablist;
import com.arionmc.arioncore.api.player.ArionPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.logging.Level;

public class ArionCoreNmsWrapper implements NmsWrapper {
    private ArionCore plugin;

    public ArionCoreNmsWrapper(ArionCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void sendTablist(ArionPlayer player, Tablist tablist) {
        try {
            PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

            if (tablist.getHeader() != null) {
                setField(packet, "a", IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + tablist.getHeader() + "\"}"));
            }
            if (tablist.getFooter() != null) {
                setField(packet, "b", IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + tablist.getFooter() + "\"}"));
            }

            sendPacket(player, packet);
        } catch (Exception e) {
            plugin.getLogger().log(Level.WARNING, "Unable to send tablist to player " + player.getName(), e);
        }
    }

    private void sendPacket(ArionPlayer player, Packet packet) {
        Player bukkitPlayer = player.getBukkitPlayer();

        if (bukkitPlayer != null) {
            ((CraftPlayer) bukkitPlayer).getHandle().playerConnection.sendPacket(packet);
        }
    }

    private void setField(Object instance, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = instance.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(instance, value);
        field.setAccessible(false);
    }
}
