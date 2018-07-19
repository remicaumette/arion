package net.orion.orioncore.api.event;

import net.orion.orioncore.api.player.OrionPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Cet événement est appelé quand le joueur va se déconnecter.
 */
public class OrionPlayerQuitEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private OrionPlayer player;

    public OrionPlayerQuitEvent(OrionPlayer player) {
        this.player = player;
    }

    /**
     * @return Le joueur.
     */
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
