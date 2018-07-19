package net.orion.orioncore.api.command.exception;

/**
 * Quand l'argument demandé est introuvable.
 */
public class ArgumentNotFoundException extends CommandException {
    public ArgumentNotFoundException(int index) {
        super("error.argument-not-found", String.valueOf(index + 1));
    }
}
