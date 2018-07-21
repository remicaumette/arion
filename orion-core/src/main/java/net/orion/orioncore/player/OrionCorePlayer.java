package net.orion.orioncore.player;

import net.orion.orioncore.api.lang.Lang;
import net.orion.orioncore.api.player.OrionPlayer;
import net.orion.orioncore.api.player.OrionPlayerRank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "players")
public class OrionCorePlayer implements OrionPlayer {
    @Id
    @Column(name = "id")
    private UUID uniqueId;
    @Column(name = "name")
    private String name;
    @Column(name = "lang")
    @Enumerated(EnumType.STRING)
    private Lang lang;
    @Column(name = "rank")
    @Enumerated(EnumType.STRING)
    private OrionPlayerRank rank;
    @Column(name = "last_connection")
    private Date lastConnection;
    @Column(name = "first_connection")
    private Date firstConnection;
    // FIELDS NON PERSISTANT
    private transient Player bukkitPlayer;
    private transient Map<String, Object> data;

    public OrionCorePlayer() {
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Date getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(Date lastConnection) {
        this.lastConnection = lastConnection;
    }

    @Override
    public Date getFirstConnection() {
        return firstConnection;
    }

    public void setFirstConnection(Date firstConnection) {
        this.firstConnection = firstConnection;
    }

    @Override
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
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
