package net.orion.orioncore;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.PostgresPlatform;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
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
import net.orion.orioncore.player.OrionCorePlayer;
import net.orion.orioncore.player.OrionCorePlayerManager;
import net.orion.orioncore.scoreboard.OrionCoreScoreboardManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;

public class OrionCore extends JavaPlugin implements OrionApi.Impl, Runnable {
    private EbeanServer ebeanServer;
    private OrionCorePlayerManager playerManager;
    private OrionCoreCommandManager commandManager;
    private OrionCoreGuiManager guiManager;
    private OrionCoreScoreboardManager scoreboardManager;
    private long ticks;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        ebeanServer = createEbeanServer();

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
    public EbeanServer getDatabase() {
        return ebeanServer;
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

        config.addClass(OrionCorePlayer.class);

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(getClassLoader());
        EbeanServer server = EbeanServerFactory.create(config);
        Thread.currentThread().setContextClassLoader(currentClassLoader);

        return server;
    }
}
