package net.arion.arioncore.player;

import net.arion.arioncore.api.lang.Lang;
import net.arion.arioncore.api.permission.Rank;
import net.arion.arioncore.api.player.PracticePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CorePlayer implements PracticePlayer {
    private UUID uniqueId;
    private String name;
    private Lang lang;
    private Rank rank;
    private long lastEnderPearl;

    public CorePlayer(UUID uniqueId, String name, Lang lang, Rank rank) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.lang = lang;
        this.rank = rank;
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Lang getLang() {
        return lang;
    }

    @Override
    public void setLang(Lang lang) {
        this.lang = lang;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public long getLastEnderPearl() {
        return lastEnderPearl;
    }

    @Override
    public void setLastEnderPearl(long lastEnderPearl) {
        this.lastEnderPearl = lastEnderPearl;
    }

    @Override
    public Player getBukkitPlayer() {
        return Bukkit.getPlayer(uniqueId);
    }

    @Override
    public void heal() {
        Player player = getBukkitPlayer();

        player.setFireTicks(0);
        player.setHealth(player.getMaxHealth());
    }
}
