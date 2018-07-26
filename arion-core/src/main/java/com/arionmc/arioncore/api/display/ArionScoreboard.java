package com.arionmc.arioncore.api.display;

import com.arionmc.arioncore.api.player.ArionPlayer;

public interface ArionScoreboard {
    /**
     * Applique le scoreboard sur un joueur.
     *
     * @param player Le joueur.
     */
    void apply(ArionPlayer player);
}
