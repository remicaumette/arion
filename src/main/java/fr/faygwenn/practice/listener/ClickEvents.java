package fr.faygwenn.practice.listener;

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




    }
}
