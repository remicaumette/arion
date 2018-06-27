package net.arion.arioncore.player;

import net.arion.arioncore.api.lang.Lang;
import net.arion.arioncore.api.player.ArionPlayer;
import net.arion.arioncore.api.player.ArionPlayerRank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ArionCorePlayer implements ArionPlayer {
    private Player bukkitPlayer;
    private UUID uniqueId;
    private String name;
    private Lang lang;
    private ArionPlayerRank rank;

    public ArionCorePlayer(UUID uniqueId, String name, Lang lang, ArionPlayerRank rank) {
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
    public ArionPlayerRank getRank() {
        return rank;
    }

    @Override
    public void setRank(ArionPlayerRank rank) {
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
