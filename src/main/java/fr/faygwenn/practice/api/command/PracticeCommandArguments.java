package fr.faygwenn.practice.api.command;

import fr.faygwenn.practice.api.player.PracticePlayer;
import fr.faygwenn.practice.api.command.exception.CommandException;

import java.util.Collection;

public interface PracticeCommandArguments {
    Collection<String> getArguments();

    String getString(int index) throws CommandException;

    PracticePlayer getPlayer(int index) throws CommandException;
}
