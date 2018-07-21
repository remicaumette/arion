package net.orion.orioncore.command;

import net.orion.orioncore.OrionCore;
import net.orion.orioncore.api.command.OrionCommandArguments;
import net.orion.orioncore.api.command.exception.ArgumentNotFoundException;
import net.orion.orioncore.api.command.exception.CommandException;
import net.orion.orioncore.api.command.exception.PlayerNotFoundException;
import net.orion.orioncore.player.OrionCorePlayer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrionCoreCommandArguments implements OrionCommandArguments {
    private OrionCore plugin;
    private List<String> arguments;

    public OrionCoreCommandArguments(OrionCore plugin, String[] arguments) {
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
    public OrionCorePlayer getPlayer(int index) throws CommandException {
        String playerName = getString(index);

        return plugin.getPlayerManager()
                .getPlayers()
                .stream()
                .filter(player -> player.getName().equalsIgnoreCase(playerName))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(playerName));
    }
}
