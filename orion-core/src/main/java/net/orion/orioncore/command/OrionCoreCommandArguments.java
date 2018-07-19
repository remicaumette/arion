package net.orion.orioncore.command;

import net.orion.orioncore.OrionCore;
import net.orion.orioncore.api.command.OrionCommandArguments;
import net.orion.orioncore.api.command.exception.ArgumentNotFoundException;
import net.orion.orioncore.api.command.exception.CommandException;
import net.orion.orioncore.api.command.exception.PlayerNotFoundException;
import net.orion.orioncore.player.OrionCorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            throw new PlayerNotFoundException(playerName);
        }
        return plugin.getPlayerManager().getPlayer(player);
    }
}
