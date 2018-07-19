package net.orion.orioncore;

import net.orion.orioncore.api.OrionApi;
import net.orion.orioncore.api.event.OrionServerTickEvent;
import net.orion.orioncore.api.lang.Lang;
import net.orion.orioncore.api.scoreboard.OrionScoreboardManager;
import net.orion.orioncore.command.OrionCoreCommandManager;
import net.orion.orioncore.command.defaults.LangCommand;
import net.orion.orioncore.command.defaults.MessageCommand;
import net.orion.orioncore.command.defaults.SettingsCommand;
import net.orion.orioncore.gui.OrionCoreGuiManager;
import net.orion.orioncore.gui.defaults.SettingsGui;
import net.orion.orioncore.listener.InventoryListener;
import net.orion.orioncore.listener.PlayerListener;
import net.orion.orioncore.player.OrionCorePlayerManager;
import net.orion.orioncore.scoreboard.OrionCoreScoreboardManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OrionCore extends JavaPlugin implements OrionApi.Impl, Runnable {
    private OrionCorePlayerManager playerManager;
    private OrionCoreCommandManager commandManager;
    private OrionCoreGuiManager guiManager;
    private OrionCoreScoreboardManager scoreboardManager;
    private long ticks;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Lang.importTranslationFromPlugin(this);

        playerManager = new OrionCorePlayerManager(this);
        commandManager = new OrionCoreCommandManager(this);
        guiManager = new OrionCoreGuiManager(this);
        scoreboardManager = new OrionCoreScoreboardManager(this);

        playerManager.onEnable();

        commandManager.registerCommand(new MessageCommand());
        commandManager.registerCommand(new LangCommand());
        commandManager.registerCommand(new SettingsCommand());

        guiManager.registerGui(new SettingsGui());

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);

        getServer().getScheduler().runTaskTimer(this, this, 0L, 1L);

        OrionApi.setImpl(this);
    }

    @Override
    public void onDisable() {
        guiManager.onDisable();
        playerManager.onDisable();

        OrionApi.setImpl(null);
        System.gc();
    }

    @Override
    public void run() {
        getServer().getPluginManager().callEvent(new OrionServerTickEvent(ticks++));
    }

    @Override
    public OrionCorePlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public OrionCoreCommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public OrionCoreGuiManager getGuiManager() {
        return guiManager;
    }

    @Override
    public OrionScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }
}
