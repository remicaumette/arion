package fr.faygwenn.practice.api.player;

import fr.faygwenn.practice.api.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PracticePlayer {
    private UUID uniqueId;
    private String name;
    private Lang lang;
    private long lastEnderPearl;

    public PracticePlayer(UUID uniqueId, String name, Lang lang) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.lang = lang;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uniqueId);
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public long getLastEnderPearl() {
        return lastEnderPearl;
    }

    public void setLastEnderPearl(long lastEnderPearl) {
        this.lastEnderPearl = lastEnderPearl;
    }

    public void heal() {
        Player player = getPlayer();

        player.setFireTicks(0);
        player.setHealth(player.getMaxHealth());
    }
}
