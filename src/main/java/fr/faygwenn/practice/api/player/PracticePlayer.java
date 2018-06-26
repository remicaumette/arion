package fr.faygwenn.practice.api.player;

import fr.faygwenn.practice.api.lang.Lang;
import fr.faygwenn.practice.api.permission.Rank;
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
