package com.arionmc.arioncore.api.event;

import com.arionmc.arioncore.api.player.ArionPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Cet événement est appelé quand le joueur va se déconnecter.
 */
public class ArionPlayerQuitEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private ArionPlayer player;

    public ArionPlayerQuitEvent(ArionPlayer player) {
        this.player = player;
    }

    /**
     * @return Le joueur.
     */
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
