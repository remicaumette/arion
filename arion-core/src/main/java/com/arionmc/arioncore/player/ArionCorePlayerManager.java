package com.arionmc.arioncore.player;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.event.ArionPlayerJoinEvent;
import com.arionmc.arioncore.api.event.ArionPlayerQuitEvent;
import com.arionmc.arioncore.api.lang.Lang;
import com.arionmc.arioncore.api.player.ArionPlayerManager;
import com.arionmc.arioncore.api.player.ArionPlayerRank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ArionCorePlayerManager implements ArionPlayerManager {
    private ArionCore plugin;
    private Map<UUID, ArionCorePlayer> players;

    public ArionCorePlayerManager(ArionCore plugin) {
        this.plugin = plugin;
        this.players = new HashMap<>();
    }

    @Override
    public Collection<ArionCorePlayer> getPlayers() {
        return players.values();
    }

    @Override
    public ArionCorePlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    @Override
    public ArionCorePlayer getPlayer(UUID uniqueId) {
        return players.get(uniqueId);
    }

    public void onEnable() {
        plugin.getLogger().info("Enabling player manager...");

        for (Player player : Bukkit.getOnlinePlayers()) {
            onJoin(player);
        }
    }

    public void onDisable() {
        plugin.getLogger().info("Disabling player manager...");

        for (Player player : Bukkit.getOnlinePlayers()) {
            onQuit(player);
        }
    }

    public void onJoin(Player bukkitPlayer) {
        ArionCorePlayer player = plugin.getDatabase().find(ArionCorePlayer.class)
                .where()
                    .idEq(bukkitPlayer.getUniqueId().toString())
                .findUnique();
        if (player == null) {
            player = new ArionCorePlayer();
            player.setUniqueId(bukkitPlayer.getUniqueId());
            player.setName(bukkitPlayer.getName());
            player.setLang(Lang.FRENCH);
            player.setRank(ArionPlayerRank.PLAYER);
            player.setFirstConnection(new Date());
        }

        player.setData(new HashMap<>());
        player.setLastConnection(new Date());

        players.put(player.getUniqueId(), player);
        plugin.getServer().getPluginManager().callEvent(new ArionPlayerJoinEvent(player));
    }

    public void onQuit(Player bukkitPlayer) {
        ArionCorePlayer player = getPlayer(bukkitPlayer);

        plugin.getServer().getPluginManager().callEvent(new ArionPlayerQuitEvent(player));
        plugin.getDatabase().save(player);
        players.remove(player.getUniqueId());
    }
}
