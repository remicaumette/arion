package net.orion.orioncore.player;

import net.orion.orioncore.api.lang.Lang;
import net.orion.orioncore.api.player.OrionPlayer;
import net.orion.orioncore.api.player.OrionPlayerRank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class OrionCorePlayer implements OrionPlayer {
    private Player bukkitPlayer;
    private UUID uniqueId;
    private String name;
    private Lang lang;
    private OrionPlayerRank rank;

    public OrionCorePlayer(UUID uniqueId, String name, Lang lang, OrionPlayerRank rank) {
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
    public OrionPlayerRank getRank() {
        return rank;
    }

    @Override
    public void setRank(OrionPlayerRank rank) {
        this.rank = rank;
    }

    @Override
    public Player getBukkitPlayer() {
        if (bukkitPlayer == null) {
            bukkitPlayer = Bukkit.getPlayer(uniqueId);
        }
        return bukkitPlayer;
    }

    @Override
    public void sendRawMessage(String message) {
        getBukkitPlayer().sendMessage(message);
    }

    @Override
    public void sendMessage(String key, String... values) {
        sendRawMessage(lang.tl(key, values));
    }
}
