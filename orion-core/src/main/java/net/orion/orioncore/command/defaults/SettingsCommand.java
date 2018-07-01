package net.orion.orioncore.command.defaults;

import net.orion.orioncore.api.OrionApi;
import net.orion.orioncore.api.command.OrionCommand;
import net.orion.orioncore.api.command.OrionCommandArguments;
import net.orion.orioncore.api.player.OrionPlayer;
import net.orion.orioncore.gui.defaults.SettingsGui;

public class SettingsCommand extends OrionCommand {
    public SettingsCommand() {
        super("settings");
        setAliases("setting", "option", "options", "opt", "opts");
    }

    @Override
    public void handle(OrionPlayer player, OrionCommandArguments arguments) {
        OrionApi.getGuiManager().openGui(SettingsGui.class, player);
    }
}
