package net.arion.arioncore.api.command.exception;

public class PlayerNotFoundException extends CommandException {
    public PlayerNotFoundException(String playerName) {
        super("error.player-not-found", playerName);
    }
}
