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
}
