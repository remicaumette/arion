package com.arionmc.arioncore.command.defaults;

import com.arionmc.arioncore.api.ArionApi;
import com.arionmc.arioncore.api.command.ArionCommand;
import com.arionmc.arioncore.api.command.ArionCommandArguments;
import com.arionmc.arioncore.api.player.ArionPlayer;
import com.arionmc.arioncore.gui.defaults.SettingsGui;

public class SettingsCommand extends ArionCommand {
    public SettingsCommand() {
        super("settings");
        setAliases("setting", "option", "options", "opt", "opts");
    }

    @Override
    public void handle(ArionPlayer player, ArionCommandArguments arguments) {
        ArionApi.getGuiManager().openGui(SettingsGui.class, player);
    }
}
