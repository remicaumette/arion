package com.arionmc.arioncore.listener;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.event.ServerTickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ServerListener implements Listener {
    private ArionCore plugin;

    public ServerListener(ArionCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTick(ServerTickEvent event) {
        plugin.getDisplayManager().onTick();
    }
}
