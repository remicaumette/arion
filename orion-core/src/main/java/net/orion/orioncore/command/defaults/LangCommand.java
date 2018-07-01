package net.orion.orioncore.command.defaults;

import net.orion.orioncore.api.command.OrionCommand;
import net.orion.orioncore.api.command.OrionCommandArguments;
import net.orion.orioncore.api.command.exception.CommandException;
import net.orion.orioncore.api.command.exception.InvalidUsageException;
import net.orion.orioncore.api.lang.Lang;
import net.orion.orioncore.api.player.OrionPlayer;

import java.util.Optional;
import java.util.stream.Stream;

public class LangCommand extends OrionCommand {
    public LangCommand() {
        super("lang");
        setUsage("/lang <fr|en>");
        setAliases("langs");
    }

    @Override
    public void handle(OrionPlayer player, OrionCommandArguments arguments) throws CommandException {
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
