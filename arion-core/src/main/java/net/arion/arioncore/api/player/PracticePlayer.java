package net.arion.arioncore.api.player;

import net.arion.arioncore.api.lang.Lang;
import net.arion.arioncore.api.permission.Rank;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface PracticePlayer {
    UUID getUniqueId();

    String getName();

    Lang getLang();

    void setLang(Lang lang);

    Rank getRank();

    void setRank(Rank rank);

    long getLastEnderPearl();

    void setLastEnderPearl(long lastEnderPearl);

    Player getBukkitPlayer();

    void heal();
}
