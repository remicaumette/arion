package net.orion.orioncore.api.command.exception;

/**
 * Quand le joueur demandé est introuvable.
 */
public class PlayerNotFoundException extends CommandException {
    public PlayerNotFoundException(String playerName) {
        super("error.player-not-found", playerName);
    }
}
