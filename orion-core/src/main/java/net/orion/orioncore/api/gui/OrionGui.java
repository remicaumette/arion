package net.orion.orioncore.api.gui;

import net.orion.orioncore.api.player.OrionPlayer;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

public abstract class OrionGui {
    public String getName(OrionPlayer player) {
        return "Default name";
    }

    public int getSize(OrionPlayer player) {
        return 9;
    }

    public void onCreate(OrionPlayer player, Inventory inventory) {
    }

    public void onClick(OrionPlayer player, Inventory inventory, int slot, ClickType click) {
    }

    public void onDestroy(OrionPlayer player) {
    }
}
