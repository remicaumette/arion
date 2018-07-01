package net.orion.orioncore.api.event;

import net.orion.orioncore.api.player.OrionPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OrionPlayerJoinEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private OrionPlayer player;

    public OrionPlayerJoinEvent(OrionPlayer player) {
        this.player = player;
    }

    public OrionPlayer getPlayer() {
        return player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
