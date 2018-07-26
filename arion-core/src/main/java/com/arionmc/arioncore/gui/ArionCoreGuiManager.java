package com.arionmc.arioncore.gui;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.gui.ArionGui;
import com.arionmc.arioncore.api.gui.ArionGuiManager;
import com.arionmc.arioncore.api.player.ArionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class ArionCoreGuiManager implements ArionGuiManager {
    private ArionCore plugin;
    private Map<Class<? extends ArionGui>, ArionGui> guis;
    private Map<Integer, ArionGui> openInventories;

    public ArionCoreGuiManager(ArionCore plugin) {
        this.plugin = plugin;
        this.guis = new HashMap<>();
        this.openInventories = new HashMap<>();
    }

    @Override
    public void registerGui(ArionGui gui) {
        guis.put(gui.getClass(), gui);
    }

    @Override
    public void openGui(Class<? extends ArionGui> clazz, ArionPlayer player) {
        ArionGui gui = guis.get(clazz);
        Inventory inventory = plugin.getServer().createInventory(null, gui.getSize(player), gui.getName(player));
        gui.onCreate(player, inventory);
        player.getBukkitPlayer().openInventory(inventory);
        openInventories.put(inventory.hashCode(), gui);
    }

    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.closeInventory();
        }
    }

    public Map<Integer, ArionGui> getOpenInventories() {
        return openInventories;
    }
}
