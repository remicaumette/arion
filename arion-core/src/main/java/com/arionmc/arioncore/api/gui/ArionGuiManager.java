package com.arionmc.arioncore.api.gui;

import com.arionmc.arioncore.api.player.ArionPlayer;

public interface ArionGuiManager {
    /**
     * Enregistre une nouvelle interface.
     *
     * @param gui L'interface à enregistrer.
     */
    void registerGui(ArionGui gui);

    /**
     * Ouvre une interface à un joueur.
     *
     * @param gui    L'interface.
     * @param player Le joueur.
     */
    void openGui(Class<? extends ArionGui> gui, ArionPlayer player);
}
