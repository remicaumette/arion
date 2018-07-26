package com.arionmc.arioncore.command;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.command.ArionCommand;
import com.arionmc.arioncore.api.command.ArionCommandManager;
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
