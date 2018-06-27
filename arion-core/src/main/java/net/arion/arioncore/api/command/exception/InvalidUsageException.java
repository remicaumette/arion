package net.arion.arioncore.api.command.exception;

public class InvalidUsageException extends CommandException {
    public InvalidUsageException(String usage) {
        super("error.invalid-usage", usage);
    }
}
