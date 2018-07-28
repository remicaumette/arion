package com.arionmc.arioncore.command;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.command.CommandArguments;
import com.arionmc.arioncore.api.command.exception.ArgumentNotFoundException;
import com.arionmc.arioncore.api.command.exception.CommandException;
import com.arionmc.arioncore.api.command.exception.PlayerNotFoundException;
import com.arionmc.arioncore.player.ArionCorePlayer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArionCoreCommandArguments implements CommandArguments {
    private ArionCore plugin;
    private List<String> arguments;

    public ArionCoreCommandArguments(ArionCore plugin, String[] arguments) {
        this.plugin = plugin;
        this.arguments = Arrays.asList(arguments);
    }

    @Override
    public List<String> getArguments() {
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

        return plugin.getPlayerManager()
                .getPlayers()
                .stream()
                .filter(player -> player.getName().equalsIgnoreCase(playerName))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(playerName));
    }
}
