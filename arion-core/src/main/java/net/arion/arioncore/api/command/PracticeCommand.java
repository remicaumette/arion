package net.arion.arioncore.api.command;

import net.arion.arioncore.api.permission.Rank;
import net.arion.arioncore.api.player.PracticePlayer;
import org.bukkit.command.CommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class PracticeCommand {
    private String name;
    private String description;
    private String usage;
    private Rank requiredRank;
    private List<String> aliases;

    public PracticeCommand(String name, String description, String usage, Rank requiredRank) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.requiredRank = requiredRank;
        this.aliases = new ArrayList<>();
    }

    public PracticeCommand(String name, String description, String usage, Rank requiredRank, String... aliases) {
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

    public String getUsage() {
        return usage;
    }

    public Rank getRequiredRank() {
        return requiredRank;
    }

    public List<String> getAliases() {
        return Collections.unmodifiableList(aliases);
    }

    public abstract void handle(PracticePlayer player, PracticeCommandArguments arguments) throws CommandException;
}
