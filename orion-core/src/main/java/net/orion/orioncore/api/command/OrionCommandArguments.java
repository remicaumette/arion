package net.orion.orioncore.api.command;

import net.orion.orioncore.api.command.exception.CommandException;
import net.orion.orioncore.api.player.OrionPlayer;

import java.util.Collection;

public interface OrionCommandArguments {
    Collection<String> getArguments();

    String getString(int index) throws CommandException;

    OrionPlayer getPlayer(int index) throws CommandException;
}
