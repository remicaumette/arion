package com.arionmc.arioncore.player;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.event.ArionPlayerJoinEvent;
import com.arionmc.arioncore.api.event.ArionPlayerQuitEvent;
import com.arionmc.arioncore.api.lang.Lang;
import com.arionmc.arioncore.api.player.ArionPlayerManager;
import com.arionmc.arioncore.api.player.ArionPlayerRank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

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

        try {
            plugin.getDataSource()
                    .getConnection()
                    .createStatement()
                    .execute("CREATE TABLE IF NOT EXISTS players (" +
                            "id VARCHAR(255) NOT NULL PRIMARY KEY," +
                            "name VARCHAR(255) NOT NULL," +
                            "lang VARCHAR(255) NOT NULL," +
                            "rank VARCHAR(255) NOT NULL," +
                            "last_connection TIMESTAMP NOT NULL," +
                            "first_connection TIMESTAMP NOT NULL);");
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to create the table!", e);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            onJoin(player);
        }
    }

    public CompletableFuture<Void> onDisable() {
        plugin.getLogger().info("Disabling player manager...");

        return CompletableFuture.allOf();
    }

    public CompletableFuture<ArionCorePlayer> loadPlayer(UUID uniqueId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                PreparedStatement statement = plugin.getDataSource()
                        .getConnection()
                        .prepareStatement("SELECT id, name, lang, rank, last_connection, first_connection " +
                                "FROM players WHERE id = ?;");
                statement.setString(1, uniqueId.toString());
                ResultSet result = statement.executeQuery();

                if (result.getRow() == 1) {
                    return new ArionCorePlayer(
                            UUID.fromString(result.getString(0)),
                            result.getString(1),
                            Lang.valueOf(result.getString(2)),
                            ArionPlayerRank.valueOf(result.getString(3)),
                            result.getDate(4),
                            result.getDate(5));
                }
            } catch (SQLException e) {
                plugin.getLogger().log(Level.SEVERE, "Failed to load the player " + uniqueId + "!", e);
            }

            return null;
        });
    }

    public CompletableFuture<Boolean> savePlayer(ArionCorePlayer player) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                PreparedStatement statement = plugin.getDataSource()
                        .getConnection()
                        .prepareStatement(player.getFirstConnection() == player.getLastConnection() ?
                                "INSERT INTO players (id, name, lang, rank, last_connection, first_connection) " +
                                        "VALUES (?, ?, ?, ?, ?, ?);" :
                                "UPDATE players SET name = ?, lang = ?, rank = ?, last_connection = ? WHERE id = ?;");
                if (player.getFirstConnection() == player.getLastConnection()) {
                    statement.setString(1, player.getUniqueId().toString());
                    statement.setString(2, player.getName());
                    statement.setString(3, player.getLang().name());
                    statement.setString(4, player.getRank().name());
                    statement.setDate(5, new java.sql.Date(player.getLastConnection().getTime()));
                    statement.setDate(6, new java.sql.Date(player.getFirstConnection().getTime()));
                } else {
                    statement.setString(1, player.getName());
                    statement.setString(2, player.getLang().name());
                    statement.setString(3, player.getRank().name());
                    statement.setDate(4, new java.sql.Date(player.getLastConnection().getTime()));
                    statement.setString(5, player.getUniqueId().toString());
                }

                return statement.execute();
            } catch (SQLException e) {
                plugin.getLogger().log(Level.SEVERE, "Failed to save the player " + player.getUniqueId() + "!", e);
            }

            return false;
        });
    }

    public void onJoin(Player bukkitPlayer) {
        loadPlayer(bukkitPlayer.getUniqueId())
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

            savePlayer(player);
        }
    }
}
