package fr.faygwenn.practice.command.defaults;

import fr.faygwenn.practice.api.command.PracticeCommand;
import fr.faygwenn.practice.api.command.PracticeCommandArguments;
import fr.faygwenn.practice.api.permission.Rank;
import fr.faygwenn.practice.api.player.PracticePlayer;
import org.bukkit.command.CommandException;

public class HelloCommand extends PracticeCommand {
    public HelloCommand() {
        super("hello", "Hello command", "/hello help", Rank.ADMIN, "hl", "helo");
    }

    @Override
    public void handle(PracticePlayer player, PracticeCommandArguments arguments) throws CommandException {

    }
}
