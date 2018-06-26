package fr.faygwenn.practice;

import fr.faygwenn.practice.api.PracticeApi;
import fr.faygwenn.practice.command.CoreCommandManager;
import fr.faygwenn.practice.listener.PlayerListener;
import fr.faygwenn.practice.player.CorePlayerManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Practice extends JavaPlugin implements Listener, PracticeApi.Impl {
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
