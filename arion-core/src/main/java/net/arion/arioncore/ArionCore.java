package net.arion.arioncore;

import net.arion.arioncore.api.ArionApi;
import net.arion.arioncore.api.event.ArionServerTickEvent;
import net.arion.arioncore.command.ArionCoreCommandManager;
import net.arion.arioncore.command.defaults.LangCommand;
import net.arion.arioncore.command.defaults.MessageCommand;
import net.arion.arioncore.command.defaults.SettingsCommand;
import net.arion.arioncore.gui.ArionCoreGuiManager;
import net.arion.arioncore.gui.defaults.SettingsGui;
import net.arion.arioncore.listener.InventoryListener;
import net.arion.arioncore.listener.PlayerListener;
import net.arion.arioncore.player.ArionCorePlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ArionCore extends JavaPlugin implements ArionApi.Impl, Runnable {
    private ArionCorePlayerManager playerManager;
    private ArionCoreCommandManager commandManager;
    private ArionCoreGuiManager guiManager;
    private long ticks;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        playerManager = new ArionCorePlayerManager(this);
        commandManager = new ArionCoreCommandManager(this);
        guiManager = new ArionCoreGuiManager(this);

        playerManager.onEnable();

        commandManager.registerCommand(new MessageCommand());
        commandManager.registerCommand(new LangCommand());
        commandManager.registerCommand(new SettingsCommand());

        guiManager.registerGui(new SettingsGui());

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);

        getServer().getScheduler().runTaskTimer(this, this, 0L, 1L);

        ArionApi.setImpl(this);
    }

    @Override
    public void onDisable() {
        guiManager.onDisable();
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

    @Override
    public ArionCoreGuiManager getGuiManager() {
        return guiManager;
    }
}
