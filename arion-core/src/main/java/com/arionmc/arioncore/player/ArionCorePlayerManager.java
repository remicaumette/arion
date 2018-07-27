package com.arionmc.arioncore.player;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.event.ArionPlayerJoinEvent;
import com.arionmc.arioncore.api.event.ArionPlayerLoadedEvent;
import com.arionmc.arioncore.api.event.ArionPlayerQuitEvent;
import com.arionmc.arioncore.api.event.ArionPlayerSavedEvent;
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

        for (Player bukkitPlayer : Bukkit.getOnlinePlayers()) {
            loadPlayer(bukkitPlayer)
                    .thenAccept(player -> players.put(player.getUniqueId(), player));
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
        loadPlayer(bukkitPlayer)
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

            repository.createOrUpdate(player)
                    .thenAccept(p -> {
                        plugin.getLogger().info("Player " + p.getName() + " saved!");
                        plugin.getServer().getPluginManager().callEvent(new ArionPlayerSavedEvent(p));
                    });
        }
    }

    private CompletableFuture<ArionCorePlayer> loadPlayer(Player bukkitPlayer) {
        return repository.findOne(bukkitPlayer.getUniqueId())
                .thenApply(player -> {
                    if (player == null) {
                        Date date = new Date();
                        return new ArionCorePlayer(bukkitPlayer.getUniqueId(), bukkitPlayer.getName(),
                                Lang.FRENCH, ArionPlayerRank.PLAYER, date, date);
                    }

                    return player;
                })
                .thenApply(player -> {
                    plugin.getLogger().info("Player " + player.getName() + " loaded!");
                    plugin.getServer().getPluginManager().callEvent(new ArionPlayerLoadedEvent(player));

                    return player;
                });
    }
}
