package com.arionmc.arioncore.api.gui;

import com.arionmc.arioncore.api.player.ArionPlayer;

public interface GuiManager {
    /**
     * Enregistre une nouvelle interface.
     *
     * @param gui L'interface à enregistrer.
     */
    void registerGui(Gui gui);

    /**
     * Ouvre une interface à un joueur.
     *
     * @param gui    L'interface.
     * @param player Le joueur.
     */
    void openGui(Class<? extends Gui> gui, ArionPlayer player);
}
