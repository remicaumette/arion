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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public String getUsage() {
        return usage;
    }

    protected void setUsage(String usage) {
        this.usage = usage;
    }

    public OrionPlayerRank getRequiredRank() {
        return requiredRank;
    }

    protected void setRequiredRank(OrionPlayerRank requiredRank) {
        this.requiredRank = requiredRank;
    }

    public List<String> getAliases() {
        return Collections.unmodifiableList(aliases);
    }

    protected void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    protected void setAliases(String... aliases) {
        setAliases(Arrays.asList(aliases));
    }

    public abstract void handle(OrionPlayer player, OrionCommandArguments arguments) throws CommandException;
}
