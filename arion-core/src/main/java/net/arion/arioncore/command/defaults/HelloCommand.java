package net.arion.arioncore.command.defaults;

import net.arion.arioncore.api.command.PracticeCommand;
import net.arion.arioncore.api.command.PracticeCommandArguments;
import net.arion.arioncore.api.permission.Rank;
import net.arion.arioncore.api.player.PracticePlayer;
import org.bukkit.command.CommandException;

public class HelloCommand extends PracticeCommand {
    public HelloCommand() {
        super("hello", "Hello command", "/hello help", Rank.ADMIN, "hl", "helo");
    }

    @Override
    public void handle(PracticePlayer player, PracticeCommandArguments arguments) throws CommandException {

    }
}
