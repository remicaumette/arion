package fr.faygwenn.practice.api.command.exception;

public class PlayerNotFoundException extends CommandException {
    public PlayerNotFoundException(String message) {
        super(message);
    }
}
