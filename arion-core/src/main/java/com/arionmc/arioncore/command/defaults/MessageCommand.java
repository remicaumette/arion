package com.arionmc.arioncore.command.defaults;

import com.arionmc.arioncore.api.command.ArionCommand;
import com.arionmc.arioncore.api.command.ArionCommandArguments;
import com.arionmc.arioncore.api.command.exception.CommandException;
import com.arionmc.arioncore.api.player.ArionPlayer;

public class MessageCommand extends ArionCommand {
    public MessageCommand() {
        super("message");
        setUsage("/message <player> <message>");
        setAliases("msg", "m", "tell");
    }

    @Override
    public void handle(ArionPlayer player, ArionCommandArguments arguments) throws CommandException {
        ArionPlayer target = arguments.getPlayer(0);

        if (target.equals(player)) {
            throw new CommandException("commands.message.yourself");
        }

        StringBuilder builder = new StringBuilder(arguments.getString(1));

        for (int i = 2; i < arguments.getArguments().size(); i++) {
            builder.append(' ');
            builder.append(arguments.getArguments().get(i));
        }

        String message = builder.toString();

        player.sendMessage("commands.message.send", target.getName(), message);
        target.sendMessage("commands.message.receive", player.getName(), message);

        player.getData().put("reply-to", target.getName());
        target.getData().put("reply-to", player.getName());
    }
}
