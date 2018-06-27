package net.arion.arioncore.api.command;

import net.arion.arioncore.api.command.exception.CommandException;
import net.arion.arioncore.api.player.ArionPlayer;
import net.arion.arioncore.api.player.ArionPlayerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class ArionCommand {
    private String name;
    private String description;
    private String usage;
    private ArionPlayerRank requiredRank;
    private List<String> aliases;

    public ArionCommand(String name) {
        this.name = name;
        this.description = "";
        this.usage = "/" + name;
        this.requiredRank = ArionPlayerRank.PLAYER;
        this.aliases = new ArrayList<>();
    }

    public ArionCommand(String name, String description, String usage, ArionPlayerRank requiredRank) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.requiredRank = requiredRank;
        this.aliases = new ArrayList<>();
    }

    public ArionCommand(String name, String description, String usage, ArionPlayerRank requiredRank, String... aliases) {
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

    public ArionPlayerRank getRequiredRank() {
        return requiredRank;
    }

    protected void setRequiredRank(ArionPlayerRank requiredRank) {
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

    public abstract void handle(ArionPlayer player, ArionCommandArguments arguments) throws CommandException;
}
