package net.arion.arioncore.api.player;

import org.bukkit.ChatColor;

public enum ArionPlayerRank {
    PLAYER("Player", ChatColor.GRAY, 0),
    ADMIN("Admin", ChatColor.RED, 100);

    private String name;
    private ChatColor color;
    private int power;

    ArionPlayerRank(String name, ChatColor color, int power) {
        this.name = name;
        this.color = color;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }

    public int getPower() {
        return power;
    }
}
