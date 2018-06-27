package net.arion.arioncore.command.defaults;

import net.arion.arioncore.api.command.ArionCommand;
import net.arion.arioncore.api.command.ArionCommandArguments;
import net.arion.arioncore.api.command.exception.CommandException;
import net.arion.arioncore.api.player.ArionPlayer;

public class MessageCommand extends ArionCommand {
    public MessageCommand() {
        super("message");
        setUsage("/message <player> <message>");
        setAliases("msg", "m", "tell");
    }

    @Override
    public void handle(ArionPlayer player, ArionCommandArguments arguments) throws CommandException {
        ArionPlayer target = arguments.getPlayer(0);
        String message = arguments.getString(1);

        player.sendMessage("message.send", target.getName(), message);
        target.sendMessage("message.receive", player.getName(), message);
    }
}
