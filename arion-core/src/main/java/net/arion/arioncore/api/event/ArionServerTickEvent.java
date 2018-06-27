package net.arion.arioncore.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArionServerTickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private long ticks;

    public ArionServerTickEvent(long ticks) {
        this.ticks = ticks;
    }

    public long getTicks() {
        return ticks;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
