package fr.faygwenn.practice.api.player;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.api.lang.Lang;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    private Practice plugin;
    private Map<UUID, PracticePlayer> players;

    public PlayerManager(Practice plugin) {
        this.plugin = plugin;
        this.players = new HashMap<>();
    }

    public Collection<PracticePlayer> getPlayers() {
        return players.values();
    }

    public PracticePlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    public PracticePlayer getPlayer(UUID uniqueId) {
        return players.get(uniqueId);
    }

    public void onJoin(Player player) {
        PracticePlayer practicePlayer = new PracticePlayer(player.getUniqueId(), player.getName(), Lang.ENGLISH);

        players.put(player.getUniqueId(), practicePlayer);
    }

    public void onQuit(Player player) {
        players.remove(player.getUniqueId());
    }
}
