package net.orion.orioncore.api.player;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public interface OrionPlayerManager {
    Collection<? extends OrionPlayer> getPlayers();

    OrionPlayer getPlayer(Player player);

    OrionPlayer getPlayer(UUID uniqueId);
}
