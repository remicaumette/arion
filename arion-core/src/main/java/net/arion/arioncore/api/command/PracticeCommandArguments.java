package net.arion.arioncore.api.command;

import net.arion.arioncore.api.player.PracticePlayer;
import net.arion.arioncore.api.command.exception.CommandException;

import java.util.Collection;

public interface PracticeCommandArguments {
    Collection<String> getArguments();

    String getString(int index) throws CommandException;

    PracticePlayer getPlayer(int index) throws CommandException;
}
