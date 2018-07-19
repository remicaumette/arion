package net.orion.orioncore.api.command;

public interface OrionCommandManager {
    /**
     * Enregistre une commande.
     *
     * @param command La commande a enregistrer.
     */
    void registerCommand(OrionCommand command);
}
