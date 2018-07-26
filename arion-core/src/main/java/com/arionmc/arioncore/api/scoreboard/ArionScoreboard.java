package com.arionmc.arioncore.api.scoreboard;

import com.arionmc.arioncore.api.player.ArionPlayer;

public abstract class ArionScoreboard {
    /**
     * Applique le scoreboard sur un joueur.
     *
     * @param player Le joueur.
     */
    public abstract void apply(ArionPlayer player);
}
