package net.arion.arioncore.api.event;

import net.arion.arioncore.api.player.ArionPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArionPlayerQuitEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private ArionPlayer player;

    public ArionPlayerQuitEvent(ArionPlayer player) {
        this.player = player;
    }

    public ArionPlayer getPlayer() {
        return player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
