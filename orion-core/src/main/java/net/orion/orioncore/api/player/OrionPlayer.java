package net.orion.orioncore.api.player;

import net.orion.orioncore.api.lang.Lang;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface OrionPlayer {
    UUID getUniqueId();

    String getName();

    Lang getLang();

    void setLang(Lang lang);

    OrionPlayerRank getRank();

    void setRank(OrionPlayerRank rank);

    Player getBukkitPlayer();

    void sendRawMessage(String message);

    void sendMessage(String key, String... values);
}
