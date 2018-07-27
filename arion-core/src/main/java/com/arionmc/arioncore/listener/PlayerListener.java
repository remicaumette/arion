package com.arionmc.arioncore.listener;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.display.ArionChatFormatter;
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
        ArionCorePlayer player = plugin.getPlayerManager().getPlayer(event.getPlayer());

        if (player != null) {
            ArionChatFormatter formatter = plugin.getDisplayManager().getChatFormatter();

            if (formatter != null) {
                String message = formatter.formatChat(player, event.getMessage());

                if (message != null) {
                    event.setFormat(message);
                } else {
                    event.setCancelled(true);
                }
            }
        } else {
            event.setCancelled(true);
        }
    }
}
