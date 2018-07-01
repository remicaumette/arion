package net.orion.orioncore.player;

import net.orion.orioncore.OrionCore;
import net.orion.orioncore.api.event.OrionPlayerJoinEvent;
import net.orion.orioncore.api.event.OrionPlayerQuitEvent;
import net.orion.orioncore.api.lang.Lang;
import net.orion.orioncore.api.player.OrionPlayerManager;
import net.orion.orioncore.api.player.OrionPlayerRank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrionCorePlayerManager implements OrionPlayerManager {
    private OrionCore plugin;
    private Map<UUID, OrionCorePlayer> players;

    public OrionCorePlayerManager(OrionCore plugin) {
        this.plugin = plugin;
        this.players = new HashMap<>();
    }

    @Override
    public Collection<OrionCorePlayer> getPlayers() {
        return players.values();
    }

    @Override
    public OrionCorePlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    @Override
    public OrionCorePlayer getPlayer(UUID uniqueId) {
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
        OrionCorePlayer player = new OrionCorePlayer(
                bukkitPlayer.getUniqueId(),
                bukkitPlayer.getName(),
                Lang.FRENCH,
                OrionPlayerRank.ADMIN);

        players.put(player.getUniqueId(), player);
        plugin.getServer().getPluginManager().callEvent(new OrionPlayerJoinEvent(player));
    }

    public void onQuit(Player bukkitPlayer) {
        OrionCorePlayer player = getPlayer(bukkitPlayer);

        plugin.getServer().getPluginManager().callEvent(new OrionPlayerQuitEvent(player));
        players.remove(player.getUniqueId());
    }
}
