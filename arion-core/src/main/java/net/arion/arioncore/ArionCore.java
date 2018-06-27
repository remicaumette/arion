package net.arion.arioncore;

import net.arion.arioncore.api.PracticeApi;
import net.arion.arioncore.command.CoreCommandManager;
import net.arion.arioncore.listener.PlayerListener;
import net.arion.arioncore.player.CorePlayerManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ArionCore extends JavaPlugin implements Listener, PracticeApi.Impl {
    private CorePlayerManager playerManager;
    private CoreCommandManager commandManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        playerManager = new CorePlayerManager(this);
        commandManager = new CoreCommandManager(this);

        playerManager.onEnable();

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        PracticeApi.setImpl(this);
    }

    @Override
    public void onDisable() {
        playerManager.onDisable();

        PracticeApi.setImpl(null);
    }

    @Override
    public CorePlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public CoreCommandManager getCommandManager() {
        return commandManager;
    }
}
