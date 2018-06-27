package net.arion.arioncore;

import net.arion.arioncore.api.ArionApi;
import net.arion.arioncore.api.event.ArionServerTickEvent;
import net.arion.arioncore.command.ArionCoreCommandManager;
import net.arion.arioncore.command.defaults.LangCommand;
import net.arion.arioncore.command.defaults.MessageCommand;
import net.arion.arioncore.listener.PlayerListener;
import net.arion.arioncore.player.ArionCorePlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ArionCore extends JavaPlugin implements ArionApi.Impl, Runnable {
    private ArionCorePlayerManager playerManager;
    private ArionCoreCommandManager commandManager;
    private long ticks;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        playerManager = new ArionCorePlayerManager(this);
        commandManager = new ArionCoreCommandManager(this);

        playerManager.onEnable();
        commandManager.registerCommand(new MessageCommand());
        commandManager.registerCommand(new LangCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        getServer().getScheduler().runTaskTimer(this, this, 0L, 1L);

        ArionApi.setImpl(this);
    }

    @Override
    public void onDisable() {
        playerManager.onDisable();

        System.gc();
        ArionApi.setImpl(null);
    }

    @Override
    public void run() {
        getServer().getPluginManager().callEvent(new ArionServerTickEvent(ticks++));
    }

    @Override
    public ArionCorePlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public ArionCoreCommandManager getCommandManager() {
        return commandManager;
    }
}
