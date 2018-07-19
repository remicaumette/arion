package net.orion.orioncore.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Cet événement est appelé à chaque tick du serveur.
 * Il permet par exemple de créer une tâche récurrente tout les x tick.
 */
public class OrionServerTickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private long ticks;

    public OrionServerTickEvent(long ticks) {
        this.ticks = ticks;
    }

    /**
     * @return Le nombre de tick écoulé depuis le démarrage du plugin.
     */
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
