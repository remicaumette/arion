package net.arion.arioncore.command.defaults;

import net.arion.arioncore.api.command.ArionCommand;
import net.arion.arioncore.api.command.ArionCommandArguments;
import net.arion.arioncore.api.player.ArionPlayer;

import java.util.Arrays;

public class MessageCommand extends ArionCommand {
    public MessageCommand() {
        super("message");
        setUsage("/message <player> <message>");
        setAliases(Arrays.asList("msg", "m", "tell"));
    }

    @Override
    public void handle(ArionPlayer player, ArionCommandArguments arguments) {

    }
}
