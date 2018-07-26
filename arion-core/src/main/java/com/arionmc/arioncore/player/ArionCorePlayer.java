package com.arionmc.arioncore.player;

import com.arionmc.arioncore.api.lang.Lang;
import com.arionmc.arioncore.api.player.ArionPlayer;
import com.arionmc.arioncore.api.player.ArionPlayerRank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArionCorePlayer implements ArionPlayer {
    private UUID uniqueId;
    private String name;
    private Lang lang;
    private ArionPlayerRank rank;
    private Date lastConnection;
    private Date firstConnection;
    private transient Player bukkitPlayer;
    private transient Map<String, Object> data;

    public ArionCorePlayer(UUID uniqueId, String name, Lang lang, ArionPlayerRank rank, Date lastConnection,
                           Date firstConnection) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.lang = lang;
        this.rank = rank;
        this.lastConnection = lastConnection;
        this.firstConnection = firstConnection;
        this.data = new HashMap<>();
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
    public Date getLastConnection() {
        return lastConnection;
    }

    @Override
    public Date getFirstConnection() {
        return firstConnection;
    }

    @Override
    public Map<String, Object> getData() {
        return data;
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
