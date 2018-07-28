package com.arionmc.arioncore.api.command;

public interface CommandManager {
    /**
     * Enregistre une commande.
     *
     * @param command La commande a enregistrer.
     */
    void registerCommand(Command command);
}
