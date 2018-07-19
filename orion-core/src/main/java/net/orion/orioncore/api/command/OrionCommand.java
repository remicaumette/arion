package net.orion.orioncore.api.command;

import net.orion.orioncore.api.command.exception.CommandException;
import net.orion.orioncore.api.player.OrionPlayer;
import net.orion.orioncore.api.player.OrionPlayerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class OrionCommand {
    private String name;
    private String description;
    private String usage;
    private OrionPlayerRank requiredRank;
    private List<String> aliases;

    public OrionCommand(String name) {
        this.name = name;
        this.description = "";
        this.usage = "/" + name;
        this.requiredRank = OrionPlayerRank.PLAYER;
        this.aliases = new ArrayList<>();
    }

    public OrionCommand(String name, String description, String usage, OrionPlayerRank requiredRank) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.requiredRank = requiredRank;
        this.aliases = new ArrayList<>();
    }

    public OrionCommand(String name, String description, String usage, OrionPlayerRank requiredRank, String... aliases) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.requiredRank = requiredRank;
        this.aliases = Arrays.asList(aliases);
    }

    /**
     * @return Le nom de la commande.
     */
    public String getName() {
        return name;
    }

    /**
     * @return La description de la commande.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Défini la description de la commande.
     *
     * @param description La description de la commande.
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return L'usage de la commande.
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Défini l'usage de la commande.
     *
     * @param usage L'usage de la commande.
     */
    protected void setUsage(String usage) {
        this.usage = usage;
    }

    /**
     * @return Le rang minimum requis pour utiliser cette commande.
     */
    public OrionPlayerRank getRequiredRank() {
        return requiredRank;
    }

    /**
     * Défini le rang minimum requis pour utiliser cette commande.
     *
     * @param requiredRank Le rang minimum requis pour utiliser cette commande.
     */
    protected void setRequiredRank(OrionPlayerRank requiredRank) {
        this.requiredRank = requiredRank;
    }

    /**
     * @return Les alias de la commande.
     */
    public List<String> getAliases() {
        return Collections.unmodifiableList(aliases);
    }

    /**
     * Défini les alias de la commande.
     *
     * @param aliases Les alias de la commande.
     */
    protected void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    /**
     * Défini les alias de la commande.
     *
     * @param aliases Les alias de la commande.
     */
    protected void setAliases(String... aliases) {
        setAliases(Arrays.asList(aliases));
    }

    /**
     * Cette méthode est appelé quand la commande est utilisé.
     *
     * @param player    Le joueur qui utilise la commande.
     * @param arguments Les arguments passé.
     * @throws CommandException Si une erreur se produit lors de l'execution de la commande.
     */
    public abstract void handle(OrionPlayer player, OrionCommandArguments arguments) throws CommandException;
}
