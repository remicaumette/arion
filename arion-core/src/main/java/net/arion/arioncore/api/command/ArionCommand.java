package net.arion.arioncore.api.command;

import net.arion.arioncore.api.command.exception.CommandException;
import net.arion.arioncore.api.permission.Rank;
import net.arion.arioncore.api.player.ArionPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class ArionCommand {
    private String name;
    private String description;
    private String usage;
    private Rank requiredRank;
    private List<String> aliases;

    public ArionCommand(String name) {
        this.name = name;
        this.description = "";
        this.usage = "/" + name;
        this.requiredRank = Rank.PLAYER;
        this.aliases = new ArrayList<>();
    }

    public ArionCommand(String name, String description, String usage, Rank requiredRank) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.requiredRank = requiredRank;
        this.aliases = new ArrayList<>();
    }

    public ArionCommand(String name, String description, String usage, Rank requiredRank, String... aliases) {
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

    public Rank getRequiredRank() {
        return requiredRank;
    }

    protected void setRequiredRank(Rank requiredRank) {
        this.requiredRank = requiredRank;
    }

    public List<String> getAliases() {
        return Collections.unmodifiableList(aliases);
    }

    protected void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public abstract void handle(ArionPlayer player, ArionCommandArguments arguments) throws CommandException;
}
