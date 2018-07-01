package net.orion.orioncore.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OrionServerTickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private long ticks;

    public OrionServerTickEvent(long ticks) {
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
