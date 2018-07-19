package net.orion.orioncore.api.command.exception;

/**
 * Quand une erreur s'est produite lors de l'execution de la commande.
 */
public class CommandException extends Exception {
    private String key;
    private String[] values;

    public CommandException() {
        this("error.basic");
    }

    public CommandException(String key, String... values) {
        this.key = key;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public String[] getValues() {
        return values;
    }
}
