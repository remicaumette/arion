package com.arionmc.arioncore.api.display;

import com.arionmc.arioncore.api.player.ArionPlayer;

public interface NametagFormatter {
    /**
     * Formatte le nametag du joueur.
     *
     * @param player Le joueur.
     * @return Le nametag formatté.
     */
    String formatNametag(ArionPlayer player);
}
