package com.arionmc.arioncore.command.defaults;

import com.arionmc.arioncore.api.command.ArionCommand;
import com.arionmc.arioncore.api.command.ArionCommandArguments;
import com.arionmc.arioncore.api.command.exception.CommandException;
import com.arionmc.arioncore.api.command.exception.InvalidUsageException;
import com.arionmc.arioncore.api.lang.Lang;
import com.arionmc.arioncore.api.player.ArionPlayer;

import java.util.Optional;
import java.util.stream.Stream;

public class LangCommand extends ArionCommand {
    public LangCommand() {
        super("lang");
        setUsage("/lang <fr|en>");
        setAliases("langs");
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
