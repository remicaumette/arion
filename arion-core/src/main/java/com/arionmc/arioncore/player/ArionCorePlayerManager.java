package com.arionmc.arioncore.player;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.event.ArionPlayerJoinEvent;
import com.arionmc.arioncore.api.event.ArionPlayerQuitEvent;
import com.arionmc.arioncore.api.lang.Lang;
import com.arionmc.arioncore.api.player.ArionPlayerManager;
import com.arionmc.arioncore.api.player.ArionPlayerRank;
import com.arionmc.arioncore.repository.ArionCorePlayerRepository;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ArionCorePlayerManager implements ArionPlayerManager {
    private ArionCore plugin;
    private ArionCorePlayerRepository repository;
    private Map<UUID, ArionCorePlayer> players;

    public ArionCorePlayerManager(ArionCore plugin) {
        this.plugin = plugin;
        this.repository = new ArionCorePlayerRepository(plugin);
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

    public CompletableFuture<Void> onDisable() {
        plugin.getLogger().info("Disabling player manager...");

        return CompletableFuture.allOf(getPlayers()
                .stream()
                .map(repository::createOrUpdate)
                .toArray(CompletableFuture<?>[]::new));
    }

    public void onJoin(Player bukkitPlayer) {
        repository.findOne(bukkitPlayer.getUniqueId())
                .thenApply(player -> {
                    if (player == null) {
                        Date date = new Date();
                        return new ArionCorePlayer(bukkitPlayer.getUniqueId(), bukkitPlayer.getName(),
                                Lang.FRENCH, ArionPlayerRank.PLAYER, date, date);
                    }
                    return player;
                })
                .thenAccept(player -> {
                    players.put(player.getUniqueId(), player);
                    plugin.getServer().getPluginManager().callEvent(new ArionPlayerJoinEvent(player));
                });
    }

    public void onQuit(Player bukkitPlayer) {
        ArionCorePlayer player = getPlayer(bukkitPlayer);

        if (player != null) {
            plugin.getServer().getPluginManager().callEvent(new ArionPlayerQuitEvent(player));
            players.remove(player.getUniqueId());

            repository.createOrUpdate(player);
        }
    }
}
