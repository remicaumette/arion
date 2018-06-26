package fr.faygwenn.practice.command;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.api.command.PracticeCommand;
import fr.faygwenn.practice.api.command.PracticeCommandManager;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

public class CoreCommandManager implements PracticeCommandManager {
    private Practice plugin;

    public CoreCommandManager(Practice plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerCommand(PracticeCommand command) {
        ((CraftServer) plugin.getServer()).getCommandMap().register("", new CoreInternalCommand(command));
    }
}
