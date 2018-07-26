package com.arionmc.arioncore.api.command;

public interface ArionCommandManager {
    /**
     * Enregistre une commande.
     *
     * @param command La commande a enregistrer.
     */
    void registerCommand(ArionCommand command);
}
