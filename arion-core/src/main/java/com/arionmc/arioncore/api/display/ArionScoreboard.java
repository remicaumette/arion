package com.arionmc.arioncore.api.display;

import com.arionmc.arioncore.api.player.ArionPlayer;

public interface ArionScoreboard {
    /**
     * Défini le titre du scoreboard en fonction du joueur.
     *
     * @param player Le joueur.
     * @return Le titre.
     */
    String getTitle(ArionPlayer player);

    /**
     * Défini les lignes du scoreboard en fonction du joueur.
     *
     * @param player Le joueur.
     * @return Les lignes.
     */
    String[] getLines(ArionPlayer player);
}
