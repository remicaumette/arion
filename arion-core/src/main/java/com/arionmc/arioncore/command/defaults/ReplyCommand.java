package com.arionmc.arioncore.command.defaults;

import com.arionmc.arioncore.api.ArionApi;
import com.arionmc.arioncore.api.command.Command;
import com.arionmc.arioncore.api.command.CommandArguments;
import com.arionmc.arioncore.api.command.exception.CommandException;
import com.arionmc.arioncore.api.command.exception.PlayerNotFoundException;
import com.arionmc.arioncore.api.player.ArionPlayer;

import java.util.Optional;

public class ReplyCommand extends Command {
    public ReplyCommand() {
        super("reply");
        setUsage("/reply <message>");
        setAliases("r");
    }

    @Override
    public void handle(ArionPlayer player, CommandArguments arguments) throws CommandException {
        if (player.getData().containsKey("reply-to")) {
            String replyTo = String.valueOf(player.getData().get("reply-to"));

            Optional<? extends ArionPlayer> target = ArionApi.getPlayerManager()
                    .getPlayers()
                    .stream()
                    .filter(p -> p.getName().equals(replyTo))
                    .findFirst();

            if (!target.isPresent()) {
                throw new PlayerNotFoundException(replyTo);
            }

            ArionPlayer targetPlayer = target.get();
            StringBuilder builder = new StringBuilder(arguments.getString(0));

            for (int i = 1; i < arguments.getArguments().size(); i++) {
                builder.append(' ');
                builder.append(arguments.getArguments().get(i));
            }

            String message = builder.toString();

            player.sendMessage("commands.message.send", targetPlayer.getName(), message);
            targetPlayer.sendMessage("commands.message.receive", player.getName(), message);

            player.getData().put("reply-to", targetPlayer.getName());
            targetPlayer.getData().put("reply-to", player.getName());
        } else {
            throw new CommandException("commands.reply.error");
        }
    }
}
