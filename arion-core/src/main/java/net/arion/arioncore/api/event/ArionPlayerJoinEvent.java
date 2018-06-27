package net.arion.arioncore.api.event;

import net.arion.arioncore.api.player.ArionPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArionPlayerJoinEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private ArionPlayer player;

    public ArionPlayerJoinEvent(ArionPlayer player) {
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
