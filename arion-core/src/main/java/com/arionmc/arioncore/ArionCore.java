package com.arionmc.arioncore;

import com.arionmc.arioncore.api.ArionApi;
import com.arionmc.arioncore.api.display.ArionDisplayManager;
import com.arionmc.arioncore.api.event.ArionServerTickEvent;
import com.arionmc.arioncore.api.lang.Lang;
import com.arionmc.arioncore.command.ArionCoreCommandManager;
import com.arionmc.arioncore.command.defaults.LangCommand;
import com.arionmc.arioncore.command.defaults.MessageCommand;
import com.arionmc.arioncore.display.ArionCoreDisplayManager;
import com.arionmc.arioncore.gui.ArionCoreGuiManager;
import com.arionmc.arioncore.gui.defaults.SettingsGui;
import com.arionmc.arioncore.listener.InventoryListener;
import com.arionmc.arioncore.listener.PlayerListener;
import com.arionmc.arioncore.player.ArionCorePlayerManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.plugin.java.JavaPlugin;

public class ArionCore extends JavaPlugin implements ArionApi.Impl, Runnable {
    private HikariDataSource dataSource;
    private ArionCorePlayerManager playerManager;
    private ArionCoreCommandManager commandManager;
    private ArionCoreGuiManager guiManager;
    private ArionCoreDisplayManager displayManager;
    private long ticks;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Lang.importTranslationFromPlugin(this);

        dataSource = createDataSource();
        playerManager = new ArionCorePlayerManager(this);
        commandManager = new ArionCoreCommandManager(this);
        guiManager = new ArionCoreGuiManager(this);
        displayManager = new ArionCoreDisplayManager(this);

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
        ArionApi.setImpl(null);

        guiManager.onDisable();
        playerManager.onDisable();
        dataSource.close();

        System.gc();
    }

    @Override
    public void run() {
        getServer().getPluginManager().callEvent(new ArionServerTickEvent(ticks++));
    }

    @Override
    public HikariDataSource getDataSource() {
        return dataSource;
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

    @Override
    public ArionDisplayManager getDisplayManager() {
        return displayManager;
    }

    public HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();

        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setJdbcUrl(getConfig().getString("database.url"));
        config.setUsername(getConfig().getString("database.username"));
        config.setPassword(getConfig().getString("database.password"));
        config.setMinimumIdle(5);

        return new HikariDataSource(config);
    }
}
