package net.orion.orioncore.command;

import net.orion.orioncore.OrionCore;
import net.orion.orioncore.api.command.OrionCommand;
import net.orion.orioncore.api.command.OrionCommandManager;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

public class OrionCoreCommandManager implements OrionCommandManager {
    private OrionCore plugin;

    public OrionCoreCommandManager(OrionCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerCommand(OrionCommand command) {
        ((CraftServer) plugin.getServer()).getCommandMap().register("", new InternalBukkitCommand(plugin, command));
    }
}
