package net.orion.orioncore.gui;

import net.orion.orioncore.OrionCore;
import net.orion.orioncore.api.gui.OrionGui;
import net.orion.orioncore.api.gui.OrionGuiManager;
import net.orion.orioncore.api.player.OrionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class OrionCoreGuiManager implements OrionGuiManager {
    private OrionCore plugin;
    private Map<Class<? extends OrionGui>, OrionGui> guis;
    private Map<Integer, OrionGui> openInventories;

    public OrionCoreGuiManager(OrionCore plugin) {
        this.plugin = plugin;
        this.guis = new HashMap<>();
        this.openInventories = new HashMap<>();
    }

    @Override
    public void registerGui(OrionGui gui) {
        guis.put(gui.getClass(), gui);
    }

    @Override
    public void openGui(Class<? extends OrionGui> clazz, OrionPlayer player) {
        OrionGui gui = guis.get(clazz);
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

    public Map<Integer, OrionGui> getOpenInventories() {
        return openInventories;
    }
}
