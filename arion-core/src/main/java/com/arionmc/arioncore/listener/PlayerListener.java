package com.arionmc.arioncore.listener;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.display.ChatFormatter;
import com.arionmc.arioncore.api.event.ArionPlayerLoadedEvent;
import com.arionmc.arioncore.api.event.ArionPlayerQuitEvent;
import com.arionmc.arioncore.player.ArionCorePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private ArionCore plugin;

    public PlayerListener(ArionCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.getPlayerManager().onJoin(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.getPlayerManager().onQuit(player);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        ArionCorePlayer sender = plugin.getPlayerManager().getPlayer(event.getPlayer());

        event.setCancelled(true);

        if (sender != null) {
            plugin.getDisplayManager().onChat(sender, event.getMessage());
        }
    }

    @EventHandler
    public void onLoad(ArionPlayerLoadedEvent event) {
        plugin.getDisplayManager().onLoad(event.getPlayer());
    }

    @EventHandler
    public void onQuit(ArionPlayerQuitEvent event) {
        plugin.getDisplayManager().onQuit(event.getPlayer());
    }
}
