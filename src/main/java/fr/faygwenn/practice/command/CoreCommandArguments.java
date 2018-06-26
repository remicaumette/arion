package fr.faygwenn.practice.command;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.api.command.PracticeCommandArguments;
import fr.faygwenn.practice.api.command.exception.CommandException;
import fr.faygwenn.practice.api.command.exception.PlayerNotFoundException;
import fr.faygwenn.practice.player.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CoreCommandArguments implements PracticeCommandArguments {
    private Practice plugin;
    private List<String> arguments;

    public CoreCommandArguments(Practice plugin, String[] arguments) {
        this.plugin = plugin;
        this.arguments = Arrays.asList(arguments);
    }

    @Override
    public Collection<String> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    @Override
    public String getString(int index) throws CommandException {
        if (arguments.size() <= index) {
            throw new CommandException("arg not found");
        }
        return arguments.get(index);
    }

    @Override
    public CorePlayer getPlayer(int index) throws CommandException {
        String playerName = getString(index);
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            throw new PlayerNotFoundException(playerName);
        }
        return plugin.getPlayerManager().getPlayer(player);
    }
}
