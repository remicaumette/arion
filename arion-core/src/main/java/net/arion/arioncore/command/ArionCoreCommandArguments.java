package net.arion.arioncore.command;

import net.arion.arioncore.ArionCore;
import net.arion.arioncore.api.command.ArionCommandArguments;
import net.arion.arioncore.api.command.exception.ArgumentNotFoundException;
import net.arion.arioncore.api.command.exception.CommandException;
import net.arion.arioncore.api.command.exception.PlayerNotFoundException;
import net.arion.arioncore.player.ArionCorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ArionCoreCommandArguments implements ArionCommandArguments {
    private ArionCore plugin;
    private List<String> arguments;

    public ArionCoreCommandArguments(ArionCore plugin, String[] arguments) {
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
            throw new ArgumentNotFoundException(index);
        }
        return arguments.get(index);
    }

    @Override
    public ArionCorePlayer getPlayer(int index) throws CommandException {
        String playerName = getString(index);
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            throw new PlayerNotFoundException(playerName);
        }
        return plugin.getPlayerManager().getPlayer(player);
    }
}
