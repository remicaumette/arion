package net.orion.orioncore.api.command.exception;

/**
 * Quand la commande est mal utilisé.
 */
public class InvalidUsageException extends CommandException {
    public InvalidUsageException(String usage) {
        super("error.invalid-usage", usage);
    }
}
