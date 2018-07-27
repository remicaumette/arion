package com.arionmc.arioncore.api.display;

import com.arionmc.arioncore.api.player.ArionPlayer;

public interface ArionChatFormatter {
    /**
     * Formatte le message du joueur.
     *
     * @param player Le joueur.
     * @param message Le message.
     * @return Le message formatté.
     */
    String formatChat(ArionPlayer player, String message);
}
