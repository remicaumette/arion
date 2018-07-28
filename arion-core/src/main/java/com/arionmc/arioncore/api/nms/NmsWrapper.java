package com.arionmc.arioncore.api.nms;

import com.arionmc.arioncore.api.player.ArionPlayer;

public interface NmsWrapper {
    /**
     * Envoi la tablist a un joueur.
     *
     * @param player  Joueur.
     * @param tablist Tablist.
     */
    void sendTablist(ArionPlayer player, Tablist tablist);

    /**
     * Envoi le title a un joueur.
     *
     * @param player Le joueur
     * @param title  Title.
     */
    void sendTitle(ArionPlayer player, Title title);

    /**
     * Envoi un message dans l'actionbar du joueur.
     *
     * @param player  Le joueur.
     * @param message Le message a envoyer.
     */
    void sendActionbar(ArionPlayer player, String message);
}
