package net.arion.arioncore.player;

import net.arion.arioncore.ArionCore;
import net.arion.arioncore.api.lang.Lang;
import net.arion.arioncore.api.permission.Rank;
import net.arion.arioncore.api.player.ArionPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    public void onJoin(Player player) {
        ArionCorePlayer practicePlayer = new ArionCorePlayer(
                player.getUniqueId(),
                player.getName(),
                Lang.FRENCH,
                Rank.ADMIN);

        players.put(player.getUniqueId(), practicePlayer);
    }

    public void onQuit(Player player) {
        players.remove(player.getUniqueId());
    }
}
