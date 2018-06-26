package fr.faygwenn.practice.event;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.kit.Kit;
import fr.faygwenn.practice.object.AfterSelecting;
import fr.faygwenn.practice.object.Fight;
import fr.faygwenn.practice.util.LangMessages;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ClickEvents implements Listener {
    @EventHandler
    public void execute(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().equals(LangMessages.KITS_INVENTORY_NAME.getFor(player).getLines().get(0))) {
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
}
