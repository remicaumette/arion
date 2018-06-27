package net.arion.arioncore.api.player;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public interface ArionPlayerManager {
    Collection<? extends ArionPlayer> getPlayers();

    ArionPlayer getPlayer(Player player);

    ArionPlayer getPlayer(UUID uniqueId);
}
