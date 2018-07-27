package com.arionmc.arioncore.repository;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.lang.Lang;
import com.arionmc.arioncore.api.player.ArionPlayerRank;
import com.arionmc.arioncore.api.repository.ArionRepository;
import com.arionmc.arioncore.player.ArionCorePlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

public class ArionCorePlayerRepository implements ArionRepository<UUID, ArionCorePlayer> {
    private ArionCore plugin;

    public ArionCorePlayerRepository(ArionCore plugin) {
        this.plugin = plugin;

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
            plugin.getLogger().log(Level.SEVERE, "Failed to create the players table!", e);
        }
    }

    @Override
    public CompletableFuture<Collection<ArionCorePlayer>> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            Collection<ArionCorePlayer> players = new ArrayList<>();

            try {
                PreparedStatement statement = plugin.getDataSource()
                        .getConnection()
                        .prepareStatement("SELECT id, name, lang, rank, last_connection, first_connection FROM players;");
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    players.add(new ArionCorePlayer(
                            UUID.fromString(result.getString(1)),
                            result.getString(2),
                            Lang.valueOf(result.getString(3)),
                            ArionPlayerRank.valueOf(result.getString(4)),
                            result.getTimestamp(5),
                            result.getTimestamp(6)));
                }

                result.close();
                statement.close();
            } catch (SQLException e) {
                plugin.getLogger().log(Level.WARNING, "Unable to find all players!", e);
            }

            return players;
        });
    }

    @Override
    public CompletableFuture<ArionCorePlayer> findOne(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            ArionCorePlayer player = null;

            try {
                PreparedStatement statement = plugin.getDataSource()
                        .getConnection()
                        .prepareStatement("SELECT id, name, lang, rank, last_connection, first_connection " +
                                "FROM players WHERE id = ?;");
                statement.setString(1, id.toString());
                ResultSet result = statement.executeQuery();

                if (result.next()) {
                    player = new ArionCorePlayer(
                            UUID.fromString(result.getString(1)),
                            result.getString(2),
                            Lang.valueOf(result.getString(3)),
                            ArionPlayerRank.valueOf(result.getString(4)),
                            result.getTimestamp(5),
                            result.getTimestamp(6));
                }

                result.close();
                statement.close();
            } catch (SQLException e) {
                plugin.getLogger().log(Level.WARNING, "Unable to find player " + id + " !", e);
            }

            return player;
        });
    }

    @Override
    public CompletableFuture<ArionCorePlayer> create(ArionCorePlayer player) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                PreparedStatement statement = plugin.getDataSource()
                        .getConnection()
                        .prepareStatement("INSERT INTO players (id, name, lang, rank, last_connection, first_connection) " +
                                "VALUES (?, ?, ?, ?, ?, ?);");

                statement.setString(1, player.getUniqueId().toString());
                statement.setString(2, player.getName());
                statement.setString(3, player.getLang().name());
                statement.setString(4, player.getRank().name());
                statement.setTimestamp(5, new Timestamp(player.getLastConnection().getTime()));
                statement.setTimestamp(6, new Timestamp(player.getFirstConnection().getTime()));

                statement.execute();
                statement.close();
            } catch (SQLException e) {
                plugin.getLogger().log(Level.SEVERE, "Failed to create the player " + player.getUniqueId() + "!", e);
            }

            return player;
        });
    }

    @Override
    public CompletableFuture<ArionCorePlayer> update(ArionCorePlayer player) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                PreparedStatement statement = plugin.getDataSource()
                        .getConnection()
                        .prepareStatement("UPDATE players SET name = ?, lang = ?, rank = ?, last_connection = ? WHERE id = ?;");

                statement.setString(1, player.getName());
                statement.setString(2, player.getLang().name());
                statement.setString(3, player.getRank().name());
                statement.setTimestamp(4, new Timestamp(player.getLastConnection().getTime()));
                statement.setString(5, player.getUniqueId().toString());

                statement.execute();
                statement.close();
            } catch (SQLException e) {
                plugin.getLogger().log(Level.SEVERE, "Failed to update the player " + player.getUniqueId() + "!", e);
            }

            return player;
        });
    }

    @Override
    public CompletableFuture<ArionCorePlayer> createOrUpdate(ArionCorePlayer player) {
        return findOne(player.getUniqueId())
                .thenApplyAsync(find -> find == null ? create(player).join() : update(player).join());
    }

    @Override
    public CompletableFuture<Boolean> delete(ArionCorePlayer player) {
        return CompletableFuture.supplyAsync(() -> {
            boolean result = false;

            try {
                PreparedStatement statement = plugin.getDataSource()
                        .getConnection()
                        .prepareStatement("DELETE FROM players WHERE id = ?;");

                statement.setString(1, player.getUniqueId().toString());
                result = statement.execute();
                statement.close();
            } catch (SQLException e) {
                plugin.getLogger().log(Level.SEVERE, "Failed to delete the player " + player.getUniqueId() + "!", e);
            }

            return result;
        });
    }
}
