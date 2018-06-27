package net.arion.arioncore.api.command;

import net.arion.arioncore.api.player.ArionPlayer;
import net.arion.arioncore.api.command.exception.CommandException;

import java.util.Collection;

public interface ArionCommandArguments {
    Collection<String> getArguments();

    String getString(int index) throws CommandException;

    ArionPlayer getPlayer(int index) throws CommandException;
}
