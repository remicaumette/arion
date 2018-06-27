package net.arion.arioncore.listener;

import net.arion.arioncore.ArionCore;
import net.arion.arioncore.api.gui.ArionGui;
import net.arion.arioncore.player.ArionCorePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {
    private ArionCore plugin;

    public InventoryListener(ArionCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
            Inventory inventory = event.getClickedInventory();
            ArionGui gui = plugin.getGuiManager().getOpenInventories().get(inventory.hashCode());

            if (gui != null) {
                ArionCorePlayer player = plugin.getPlayerManager().getPlayer((Player) event.getWhoClicked());
                gui.onClick(player, inventory, event.getSlot(), event.getClick());
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        ArionGui gui = plugin.getGuiManager().getOpenInventories().get(inventory.hashCode());

        if (gui != null) {
            ArionCorePlayer player = plugin.getPlayerManager().getPlayer((Player) event.getPlayer());
            gui.onDestroy(player);
            plugin.getGuiManager().getOpenInventories().remove(inventory.hashCode());
        }
    }
}
