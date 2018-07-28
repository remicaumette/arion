package com.arionmc.arioncore.api.display;

import com.arionmc.arioncore.api.player.ArionPlayer;

public interface ArionChatFormatter {
    /**
     * Formatte le message en fonction du joueur.
     *
     * @param sender   Le joueur qui envoye le message.
     * @param receiver Le joueur qui recoit le message.
     * @param message  Le message.
     * @return Le message formatté.
     */
    String formatChat(ArionPlayer sender, ArionPlayer receiver, String message);
}
