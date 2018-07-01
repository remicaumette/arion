package net.orion.orioncore.command.defaults;

import net.orion.orioncore.api.command.OrionCommand;
import net.orion.orioncore.api.command.OrionCommandArguments;
import net.orion.orioncore.api.command.exception.CommandException;
import net.orion.orioncore.api.player.OrionPlayer;

public class MessageCommand extends OrionCommand {
    public MessageCommand() {
        super("message");
        setUsage("/message <player> <message>");
        setAliases("msg", "m", "tell");
    }

    @Override
    public void handle(OrionPlayer player, OrionCommandArguments arguments) throws CommandException {
        OrionPlayer target = arguments.getPlayer(0);
        String message = arguments.getString(1);

        player.sendMessage("message.send", target.getName(), message);
        target.sendMessage("message.receive", player.getName(), message);
    }
}
