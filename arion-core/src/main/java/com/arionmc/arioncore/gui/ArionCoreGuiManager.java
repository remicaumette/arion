package com.arionmc.arioncore.gui;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.gui.Gui;
import com.arionmc.arioncore.api.gui.GuiManager;
import com.arionmc.arioncore.api.player.ArionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class ArionCoreGuiManager implements GuiManager {
    private ArionCore plugin;
    private Map<Class<? extends Gui>, Gui> guis;
    private Map<Integer, Gui> openInventories;

    public ArionCoreGuiManager(ArionCore plugin) {
        this.plugin = plugin;
        this.guis = new HashMap<>();
        this.openInventories = new HashMap<>();
    }

    @Override
    public void registerGui(Gui gui) {
        guis.put(gui.getClass(), gui);
    }

    @Override
    public void openGui(Class<? extends Gui> clazz, ArionPlayer player) {
        Gui gui = guis.get(clazz);
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

    public Map<Integer, Gui> getOpenInventories() {
        return openInventories;
    }
}
