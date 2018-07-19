package net.orion.orioncore.api.gui;

import net.orion.orioncore.api.player.OrionPlayer;

public interface OrionGuiManager {
    /**
     * Enregistre une nouvelle interface.
     *
     * @param gui L'interface à enregistrer.
     */
    void registerGui(OrionGui gui);

    /**
     * Ouvre une interface à un joueur.
     *
     * @param gui    L'interface.
     * @param player Le joueur.
     */
    void openGui(Class<? extends OrionGui> gui, OrionPlayer player);
}
