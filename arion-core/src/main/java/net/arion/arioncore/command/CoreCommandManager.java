package net.arion.arioncore.command;

import net.arion.arioncore.ArionCore;
import net.arion.arioncore.api.command.PracticeCommand;
import net.arion.arioncore.api.command.PracticeCommandManager;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

public class CoreCommandManager implements PracticeCommandManager {
    private ArionCore plugin;

    public CoreCommandManager(ArionCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerCommand(PracticeCommand command) {
        ((CraftServer) plugin.getServer()).getCommandMap().register("", new CoreInternalCommand(command));
    }
}
