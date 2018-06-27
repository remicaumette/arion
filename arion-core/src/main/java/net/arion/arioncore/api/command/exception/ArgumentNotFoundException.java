package net.arion.arioncore.api.command.exception;

public class ArgumentNotFoundException extends CommandException {
    public ArgumentNotFoundException(int index) {
        super("error.argument-not-found", String.valueOf(index + 1));
    }
}
