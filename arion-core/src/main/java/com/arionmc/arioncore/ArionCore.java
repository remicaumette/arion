package com.arionmc.arioncore;

import com.arionmc.arioncore.api.ArionApi;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.PostgresPlatform;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import com.arionmc.arioncore.api.event.ArionServerTickEvent;
import com.arionmc.arioncore.api.lang.Lang;
import com.arionmc.arioncore.api.scoreboard.ArionScoreboardManager;
import com.arionmc.arioncore.command.ArionCoreCommandManager;
import com.arionmc.arioncore.command.defaults.LangCommand;
import com.arionmc.arioncore.command.defaults.MessageCommand;
import com.arionmc.arioncore.command.defaults.SettingsCommand;
import com.arionmc.arioncore.gui.ArionCoreGuiManager;
import com.arionmc.arioncore.gui.defaults.SettingsGui;
import com.arionmc.arioncore.listener.InventoryListener;
import com.arionmc.arioncore.listener.PlayerListener;
import com.arionmc.arioncore.player.ArionCorePlayer;
import com.arionmc.arioncore.player.ArionCorePlayerManager;
import com.arionmc.arioncore.scoreboard.ArionCoreScoreboardManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;

public class ArionCore extends JavaPlugin implements ArionApi.Impl, Runnable {
    private EbeanServer ebeanServer;
    private ArionCorePlayerManager playerManager;
    private ArionCoreCommandManager commandManager;
    private ArionCoreGuiManager guiManager;
    private ArionCoreScoreboardManager scoreboardManager;
    private long ticks;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        ebeanServer = createEbeanServer();

        Lang.importTranslationFromPlugin(this);

        playerManager = new ArionCorePlayerManager(this);
        commandManager = new ArionCoreCommandManager(this);
        guiManager = new ArionCoreGuiManager(this);
        scoreboardManager = new ArionCoreScoreboardManager(this);

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

        ArionApi.setImpl(null);
        System.gc();
    }

    @Override
    public EbeanServer getDatabase() {
        return ebeanServer;
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

    @Override
    public ArionScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    private EbeanServer createEbeanServer() {
        DataSourceConfig dataSource = new DataSourceConfig();
        ServerConfig config = new ServerConfig();

        config.setName("default");
        config.setRegister(true);
        config.setDefaultServer(true);
        config.setDdlGenerate(true);
        config.setDdlRun(true);

        switch (getConfig().getString("database.driver").toLowerCase()) {
            case "postgres":
                dataSource.setDriver("org.postgresql.Driver");
                dataSource.setUrl("jdbc:postgres://" + getConfig().getString("database.url"));
                dataSource.setUsername(getConfig().getString("database.username"));
                dataSource.setPassword(getConfig().getString("database.password"));

                config.setDatabasePlatform(new PostgresPlatform());
                config.setDataSourceConfig(dataSource);
                break;
            case "sqlite":
                String path = new File(getDataFolder(), getConfig().getString("database.filename")).getAbsolutePath();
                dataSource.setDriver("org.sqlite.JDBC");
                dataSource.setUrl("jdbc:sqlite:" + path);
                dataSource.setUsername("root");
                dataSource.setPassword("root");
                dataSource.setIsolationLevel(Connection.TRANSACTION_SERIALIZABLE);

                config.setDatabasePlatform(new SQLitePlatform());
                config.setDataSourceConfig(dataSource);
                break;
            default:
                throw new RuntimeException("Invalid database driver!");
        }

        config.addClass(ArionCorePlayer.class);

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(getClassLoader());
        EbeanServer server = EbeanServerFactory.create(config);
        Thread.currentThread().setContextClassLoader(currentClassLoader);

        return server;
    }
}
