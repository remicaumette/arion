package fr.faygwenn.practice.utils;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class DelayRunnable extends BukkitRunnable {
    public Player player;
    public int delay = 10;

    public DelayRunnable(Player player) {
        this.player = player;
    }
}
