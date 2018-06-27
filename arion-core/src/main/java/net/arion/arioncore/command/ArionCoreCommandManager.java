package net.arion.arioncore.command;

import net.arion.arioncore.ArionCore;
import net.arion.arioncore.api.command.ArionCommand;
import net.arion.arioncore.api.command.ArionCommandManager;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

public class ArionCoreCommandManager implements ArionCommandManager {
    private ArionCore plugin;

    public ArionCoreCommandManager(ArionCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerCommand(ArionCommand command) {
        ((CraftServer) plugin.getServer()).getCommandMap().register("", new InternalBukkitCommand(plugin, command));
    }
}
