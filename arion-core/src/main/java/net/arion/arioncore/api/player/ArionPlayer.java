package net.arion.arioncore.api.player;

import net.arion.arioncore.api.lang.Lang;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface ArionPlayer {
    UUID getUniqueId();

    String getName();

    Lang getLang();

    void setLang(Lang lang);

    ArionPlayerRank getRank();

    void setRank(ArionPlayerRank rank);

    Player getBukkitPlayer();

    void sendRawMessage(String message);

    void sendMessage(String key, String... values);
}
