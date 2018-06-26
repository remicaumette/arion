package fr.faygwenn.practice.listener;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.kit.Kit;
import fr.faygwenn.practice.object.AfterSelecting;
import fr.faygwenn.practice.object.Fight;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {
    private Practice plugin;

    public InventoryListener(Practice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (plugin.specInv.contains(player)) {
            event.setCancelled(true);
        } else if (event.getInventory().getName().equals(LangMessages.KITS_INVENTORY_NAME.getFor(player).getLines().get(0))) {
            int slot = event.getSlot();

            if (slot == 0)
                AfterSelecting.afterSelecting(player, Kit.NODEBUFF);
            else if (slot == 1)
                AfterSelecting.afterSelecting(player, Kit.DEBUFF);
            else if (slot == 2)
                AfterSelecting.afterSelecting(player, Kit.G_APPLE);
            else if (slot == 3) {
                LangMessages.CANT_ENTER_BUILDUHC.getFor(player).send(player);
                // AfterSelecting.afterSelecting(player, Kit.BUILD_UHC);
            }

            player.closeInventory();
            event.setCancelled(true);
            return;
        } else {
            Fight fight = Practice.i.manager.getFight(player);

            if (fight == null && !Practice.i.manager.editingPlayers.containsKey(player))
                event.setCancelled(true);

        }

        final Inventory inventory = event.getClickedInventory();

        if (inventory.getName().equalsIgnoreCase("Settings")) {
            if (event.getCurrentItem().getType() == null) {
                player.updateInventory();
                event.setCancelled(false);
            }
        }

        if (inventory.getName().equalsIgnoreCase("Settings")) {
            if (event.getCurrentItem().getType() == Material.RED_ROSE) {
                player.closeInventory();
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        plugin.specInv.remove(player);
    }
}
