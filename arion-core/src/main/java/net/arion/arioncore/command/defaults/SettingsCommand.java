package net.arion.arioncore.command.defaults;

import net.arion.arioncore.api.ArionApi;
import net.arion.arioncore.api.command.ArionCommand;
import net.arion.arioncore.api.command.ArionCommandArguments;
import net.arion.arioncore.api.player.ArionPlayer;
import net.arion.arioncore.gui.defaults.SettingsGui;

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
