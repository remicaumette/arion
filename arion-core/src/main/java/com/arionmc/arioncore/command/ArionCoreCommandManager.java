package com.arionmc.arioncore.command;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.command.Command;
import com.arionmc.arioncore.api.command.CommandManager;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

public class ArionCoreCommandManager implements CommandManager {
    private ArionCore plugin;

    public ArionCoreCommandManager(ArionCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerCommand(Command command) {
        ((CraftServer) plugin.getServer()).getCommandMap().register("", new InternalBukkitCommand(plugin, command));
    }
}
