package fr.faygwenn.practice.player;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.api.lang.Lang;
import fr.faygwenn.practice.api.permission.Rank;
import fr.faygwenn.practice.api.player.PracticePlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CorePlayerManager implements PracticePlayerManager {
    private Practice plugin;
    private Map<UUID, CorePlayer> players;

    public CorePlayerManager(Practice plugin) {
        this.plugin = plugin;
        this.players = new HashMap<>();
    }

    @Override
    public Collection<CorePlayer> getPlayers() {
        return players.values();
    }

    @Override
    public CorePlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    @Override
    public CorePlayer getPlayer(UUID uniqueId) {
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
        CorePlayer practicePlayer = new CorePlayer(
                player.getUniqueId(),
                player.getName(),
                Lang.ENGLISH,
                Rank.ADMIN);

        players.put(player.getUniqueId(), practicePlayer);
    }

    public void onQuit(Player player) {
        players.remove(player.getUniqueId());
    }
}
