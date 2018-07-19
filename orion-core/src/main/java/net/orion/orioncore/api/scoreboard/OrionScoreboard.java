package net.orion.orioncore.api.scoreboard;

import net.orion.orioncore.api.player.OrionPlayer;

public abstract class OrionScoreboard {
    /**
     * Applique le scoreboard sur un joueur.
     *
     * @param player Le joueur.
     */
    public abstract void apply(OrionPlayer player);
}
