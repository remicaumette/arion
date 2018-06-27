package net.arion.arioncore.command.defaults;

import net.arion.arioncore.api.command.ArionCommand;
import net.arion.arioncore.api.command.ArionCommandArguments;
import net.arion.arioncore.api.command.exception.CommandException;
import net.arion.arioncore.api.command.exception.InvalidUsageException;
import net.arion.arioncore.api.lang.Lang;
import net.arion.arioncore.api.player.ArionPlayer;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class LangCommand extends ArionCommand {
    public LangCommand() {
        super("lang");
        setUsage("/lang <fr|en>");
        setAliases(Arrays.asList("lng", "langs"));
    }

    @Override
    public void handle(ArionPlayer player, ArionCommandArguments arguments) throws CommandException {
        String arg = arguments.getString(0);
        Optional<Lang> newLang = Stream.of(Lang.values())
                .filter(lang -> lang.getCode().equalsIgnoreCase(arg))
                .findFirst();

        if (newLang.isPresent()) {
            player.setLang(newLang.get());
            player.sendMessage("lang.updated");
        } else {
            throw new InvalidUsageException(getUsage());
        }
    }
}
