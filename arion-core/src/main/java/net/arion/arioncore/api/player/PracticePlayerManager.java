package net.arion.arioncore.api.player;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public interface PracticePlayerManager {
    Collection<? extends PracticePlayer> getPlayers();

    PracticePlayer getPlayer(Player player);

    PracticePlayer getPlayer(UUID uniqueId);
}
